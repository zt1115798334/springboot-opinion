package com.zt.opinion.mq.service;

import org.springframework.stereotype.Component;

/**
 * Created by zhangtong on 2017/7/5.
 */
@Component
public class ConsumerListener {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
//    @JmsListener(destination = "myeq")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:"+text);
    }
}
