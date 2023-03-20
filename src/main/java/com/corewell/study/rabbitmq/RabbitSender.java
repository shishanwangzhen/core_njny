package com.corewell.study.rabbitmq;/*
package com.corewell.study.rabbitmq;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

*/
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/12/20/17:13
 * @Description:
 *//*


@Component
public class RabbitSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    */
/**
     * 发送消息
     *//*

    public void sendMsg() {
        String msg = "简单队列-hello呀";
        System.out.println("Sender : " + msg);
        this.rabbitTemplate.convertAndSend("luoyong-queue", msg);
    }
}*/
