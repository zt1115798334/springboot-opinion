package com.zt.opinion.mq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by zhangtong on 2017/7/5.
 */
@Component
public class Producer {

    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;
    @Autowired
    private Queue queue;
    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(final String message){
        jmsTemplate.convertAndSend(queue, message);
    }
}
