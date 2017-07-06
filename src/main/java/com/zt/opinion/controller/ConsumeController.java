package com.zt.opinion.controller;

import com.zt.opinion.base.controller.BaseController;
import com.zt.opinion.framework.beans.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangtong on 2017/7/6.
 */
@Controller
@RequestMapping("consume")
public class ConsumeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private com.zt.opinion.mq.service.Consumer consumer;

    @RequestMapping("getConsume")
    @ResponseBody
    public AjaxResult getConsume(){
        logger.info("开始执行接受消息");
        String msg = consumer.receiveQueue();
        return success( msg);
    }
}
