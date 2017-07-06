package com.zt.opinion.mq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by zhangtong on 2017/7/6.
 */
@Component
public class Consumer {

    @Autowired
    private Queue queue;

    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;

    public String receiveQueue(){
        String msg = "";
        Message<?> obj = jmsTemplate.receive(queue);
        if (null != obj) {
            msg = obj.getPayload().toString();
        }
        return msg;
    }
}
