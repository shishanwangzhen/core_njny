package com.corewell.study.utils;/*
package com.corewell.study.utils;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

*/
/**
 * @author cx
 * @version 1.0
 * <p>
 * 连接建立成功调用的方法
 * <p>
 * 连接关闭调用的方法
 * <p>
 * 给前台推消息
 * @param rs
 * @throws IOException
 * @throws EncodeException
 * <p>
 * 收到前端客户端消息后调用的方方法
 * @param message 客户端发送过来的消息
 * <p>
 * 发生异常时调用
 *//*


@Slf4j
@ServerEndpoint("/websocket")
@Component
public class MyWebsocket {

	//其实这里还可以  写一个连接数的静态变量，当每次连接成功后 ++一次，断开连接时 -- 一次，可以实时观测连接数，根据具体需求去做就可以了
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<MyWebsocket> webSocketSet = new CopyOnWriteArraySet<MyWebsocket>();
	 
    //private volatile static List<Session> sessions = Collections.synchronizedList(new ArrayList());
	private Session session;
    */
/**
 * 连接建立成功调用的方法
 *//*

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);      //将连接添加到安全的线池去
    	System.out.println("连接成功");
    }

    */
/**
 * 连接关闭调用的方法
 *//*

    @OnClose
    public void onClose() {
        System.out.println("有一连接关闭");
    }

    */
/**
 * 给前台推消息
 * @param rs
 * @throws IOException
 * @throws EncodeException
 *//*

    public void sendMessage(String rs) throws IOException {
    	if(this.session.isOpen()) {   //判断session是否是打开状态的，打开状态的才发。
    		this.session.getBasicRemote().sendText(rs);
    	}
    }
    //下面这个方法是对外提供的静态方法，当业务层需要给改变前端显示内容，或给前端推送内容时调用此方法（可根据具体业务逻辑更改此方法）
    public static void sendInfo(WebsocketMessage message) throws IOException {
        String json = JSONArray.toJSON(message).toString();
        if(!message.equals(null)) {
            for (MyWebsocket item : webSocketSet) {   //这是默认给全部的连接发送消息   如果不需要给全部发，可以定义一个变量来区别每次链接的ID 给对应的ID发
                item.sendMessage(json);
            }
        }
    }
    
    */
/**
 * 收到前端客户端消息后调用的方方法
 * @param message 客户端发送过来的消息
 *//*

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
    }

    */
/**
 * 发生异常时调用
 *//*

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生异常");
        error.printStackTrace();
    }

}
*/
