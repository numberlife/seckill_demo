package com.qjmoneynode.seckilldemo.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author qjmoneynode
 * @version 1.0
 * @description
 * @createTime 2023-07-17  21:22
 */
@Service
@Slf4j
public class MQReceiver {

    @RabbitListener(queues = "queue")
    public void receive(Object msg){
        log.info("接收消息："+msg);
    }

}
