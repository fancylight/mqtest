package com.example.demo.mq;

import com.example.demo.mq.simplest.SimplestProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;

import javax.annotation.Resource;

@SpringBootTest

class DemoApplicationTests {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RabbitAdmin rabbitAdmin;
    @Resource
    private Queue transientQueue;
    @Test
    public void simpleTest(){
        rabbitTemplate.convertAndSend(SimplestProducer.QUEUE_NAME,"简单发送");
        while (true) {

        }
    }



    /**
     * 发送一个消息但是自己不消费
     */
    @Test
    public void sendMesAndNotUse(){
        rabbitAdmin.declareQueue(transientQueue);
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(SimplestProducer.SELF_KEY,"第" + i + "次发送数据");
        }
    }

    /**
     * 消费{@link SimplestProducer#SELF_KEY}中的消息
     */
    @Test
    public void consumer(){
        sendMesAndNotUse();
        while (true){
            String data = rabbitTemplate.receiveAndConvert(SimplestProducer.SELF_KEY,10000, ParameterizedTypeReference.forType(String.class));
            System.out.println(data);
        }
    }
}
