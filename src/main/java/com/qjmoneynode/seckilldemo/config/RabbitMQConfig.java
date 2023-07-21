package com.qjmoneynode.seckilldemo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;


/**
 * @author qjmoneynode
 * @version 1.0
 * @description
 * @createTime 2023-07-17  21:16
 */
public class RabbitMQConfig {
    @Bean
    public Queue queue(){
        return new Queue("queue",true);
    }
}
