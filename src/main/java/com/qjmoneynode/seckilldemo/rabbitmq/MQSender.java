package com.qjmoneynode.seckilldemo.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qjmoneynode
 * @version 1.0
 * @description
 * @createTime 2023-07-17  21:17
 */
@Service
@Slf4j
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object msg){
        log.info("发送消息："+msg);
        rabbitTemplate.convertAndSend("queue", msg);
    }
}
