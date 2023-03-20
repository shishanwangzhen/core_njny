/**
 * @program:
 * @description: 获取session
 * @author: Xzj
 * @create: 2021-01-20 15:05
 **/
package com.corewell.study.component;


import javax.websocket.server.ServerEndpointConfig;

public class HttpSessionWSHelper extends ServerEndpointConfig.Configurator {
   /* @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        System.out.println("调用modifyHandshake方法...");
        HttpSession session = (HttpSession) request.getHttpSession();//session有可能为空
        if (session!=null){
            System.out.println("获取到session id:"+session.getId());
            sec.getUserProperties().put(HttpSession.class.getName(),session);
        }else{
            System.out.println("modifyHandshake 获取到null session");
        }
    }*/
}
