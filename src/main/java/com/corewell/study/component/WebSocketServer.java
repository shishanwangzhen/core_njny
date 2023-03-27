package com.corewell.study.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author 863586395
 */
@ServerEndpoint(value = "/socketServer/{userId}", configurator = HttpSessionWSHelper.class)
@Component
@Slf4j
public class WebSocketServer {
    static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */

    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客服端对应的MyWebSocket对象。
     */

    private static ConcurrentHashMap<String, WebSocketServer> webSocketServerConcurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 与某个客服端的连接会话，需要通过它来给客服端发送数据
     */

    private Session session;

    /**
     * 接受userId
     */

    private String userId;

    /**
     * 连接建立成功调用的方法
     */

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        log.info("+++++" + userId);
        log.info("webSocketServerConcurrentHashMap   :" + JSON.toJSONString(webSocketServerConcurrentHashMap));
        if (webSocketServerConcurrentHashMap.containsKey(userId)) {
            webSocketServerConcurrentHashMap.remove(userId);
            webSocketServerConcurrentHashMap.put(userId, this);
            //加入set
        } else {
            webSocketServerConcurrentHashMap.put(userId, this);
            //加入set
            addOnlineCount();
            //线程数加1
        }
        log.info("用户连接" + userId + "，当前在线人数：" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (Exception e) {
            log.info("用户：" + userId + ",网络异常！！！！！！！！！！！");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketServerConcurrentHashMap.containsKey(userId)) {
            //从set中删除
            webSocketServerConcurrentHashMap.remove(userId);
            subOnlineCount();
        }

        log.info("用户退出：" + userId + ",当前在线人数为：" + getOnlineCount());
    }

    /**
     * 收到客服端消息后调用的方法
     *
     * @param message 客服端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息：" + userId + ",报文：" + message);
        //可以群发消息
        //消息保存到数据库，redis
        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人（防止串改）
                jsonObject.put("fromUserId", this.userId);
                String toUserId = jsonObject.getString("toUserId");
                //传送给对应的toUserId用户的webSocket
                if (StringUtils.isNotBlank(toUserId) && webSocketServerConcurrentHashMap.containsKey(toUserId)) {
                    webSocketServerConcurrentHashMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                } else {
                    log.error("请求的userId：" + toUserId + "不在该服务器上");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("用户错误：" + this.userId + ",原因" + throwable.getMessage());
        throwable.printStackTrace();
    }


    /**
     * 实现服务器的主动推送
     */

    private void sendMessage(String message) throws Exception {
        synchronized (session) {
            session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 实现服务器的主动推送全部用户
     */

    public void sendMessageAllUser(String message) throws Exception {
        log.info("实现服务器的主动推送全部用户: " + JSONObject.toJSON(message));
        for (WebSocketServer webSocketServer : webSocketServerConcurrentHashMap.values()) {
            webSocketServer.sendMessage(message);
        }
    }

    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) throws Exception {
        log.info("发送消息到：" + userId + ",报文：" + message);
        if (StringUtils.isNotBlank(userId) && webSocketServerConcurrentHashMap.containsKey(userId)) {
            webSocketServerConcurrentHashMap.get(userId).sendMessage(message);

        } else {
            log.error("用户" + userId + "，不在线！！！！");
        }

    }


    private static synchronized int getOnlineCount() {
        return onlineCount;
    }


    private static synchronized void addOnlineCount() {
        onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        onlineCount--;
    }

}
