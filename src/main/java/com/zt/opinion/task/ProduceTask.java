package com.zt.opinion.task;

import com.zt.opinion.mq.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by zhangtong on 2017/7/6.
 */
@Component
public class ProduceTask {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Producer producer;
//  @Scheduled(cron = "0 30 23 * * ?")//每天晚上23:30开始执行
//	@Scheduled(cron = "0 01 17 * * ?")
	@Scheduled(fixedRate = 1)//以间隔2分钟的频率执行一次
//	@Scheduled(fixedDelay=3600000)//下一次和上一次执行时延时1小时后开始执行
    public void execute(){
        logger.info("开始执行生成消息");
        producer.sendMessage("我是一个帅哥");
    }
}
