package com.example.demo.mq.simplest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimplestConsumer {
    @RabbitListener(queues = SimplestProducer.QUEUE_NAME)
    public void printQueueInfo(String mes){
        System.out.println(mes);
    }
}
