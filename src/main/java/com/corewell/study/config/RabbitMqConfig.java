package com.corewell.study.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/12/20/17:12
 * @Description:
 */

@Configuration
public class RabbitMqConfig {

    /**
     * 初始化Queue
     * 创建一个名称为"core-njny-queue"的队列，其他参数使用默认
     * 在创建队列的时候如果要指定其他参数，Queue有多个构造方法可选择
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("core-njny-queue");
    }
}