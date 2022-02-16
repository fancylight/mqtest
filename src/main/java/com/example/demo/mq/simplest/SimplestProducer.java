package com.example.demo.mq.simplest;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimplestProducer {
    public final static String QUEUE_NAME = "simplest";

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }
    //--------------------创建一个队列--------------------------
    /**
     * 貌似springAMQP必须存在监听器否则不创建队列,可以在rabbitMq ui中手动创建
     * @see org.springframework.amqp.rabbit.annotation.RabbitListenerAnnotationBeanPostProcessor#postProcessAfterInitialization
     * 或者调用{@link org.springframework.amqp.rabbit.core.RabbitAdmin#declareQueue(Queue)} 创建
     */
    public static final String SELF_KEY= "self_queue";
    @Bean
    public Queue transientQueue(){
        return new Queue(SimplestProducer.SELF_KEY,true,false,false);
    }
}
