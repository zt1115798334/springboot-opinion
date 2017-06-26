package com.zt.opinion.handler;

import com.zt.opinion.framework.beans.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * <p>Title: OpinionExceptionHandler</p>
 * <p>Description: 统一的异常处理器</p>
 * @author wjc
 * @date 2017年6月20日
 */
@ControllerAdvice
public class OpinionExceptionHandler {
	
    private final static Logger logger = LoggerFactory.getLogger(OpinionExceptionHandler.class);
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AjaxResult handle(Exception e){
    	logger.error("异常信息：{}", e);
    	return AjaxResult.fail("系统错误，请联系管理员解决");
    }
}