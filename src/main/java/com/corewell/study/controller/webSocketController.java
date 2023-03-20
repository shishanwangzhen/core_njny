package com.corewell.study.controller;

import com.corewell.study.component.WebSocketServer;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.websocket.server.ServerEndpoint;

/**
 * @author 863586395
 */
@RestController
@ServerEndpoint("/api/socket/")
@Api(tags = "websocket")
@ApiIgnore//隐藏swagger接口文档
public class webSocketController {
    @GetMapping("index")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("请求成功");
    }


    @GetMapping("page")
    public ModelAndView page() {
        return new ModelAndView("webSocket");
    }


    @RequestMapping("push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, String toUserId) throws Exception {
        WebSocketServer.sendInfo(message, toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}
