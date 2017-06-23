package com.zt.opinion.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zt.opinion.framework.beans.AjaxResult;

/**
 * 
 * <p>Title: BaseController</p>
 * <p>Description: 提供公共接口方法的基础控制器类</p>
 * @author wjc
 * @date 2017年6月19日
 */
public abstract class BaseController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected AjaxResult success(Object data){
		return AjaxResult.success(data);
	}
	
	protected AjaxResult fail(String message){
		return AjaxResult.fail(message);
	}
	
	protected AjaxResult fail(int status, String message){
		return AjaxResult.fail(status, message);
	}
	
}
