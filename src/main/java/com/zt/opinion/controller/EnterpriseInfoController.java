package com.zt.opinion.controller;

import com.zt.opinion.base.controller.BaseController;
import com.zt.opinion.entity.EnterpriseInfo;
import com.zt.opinion.framework.beans.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zt.opinion.service.EnterpriseInfoService;

@Controller
@RequestMapping("/enterprise")
public class EnterpriseInfoController extends BaseController {

	@Autowired
	private EnterpriseInfoService enterpriseInfoService;
	
	@RequestMapping("/addEnterprise")
	@ResponseBody
	public AjaxResult addEnterprise(@RequestBody EnterpriseInfo enterpriseInfo) {
		try {
			if (null == enterpriseInfo) {
				return fail("企业信息不能为空");
			}
			
			enterpriseInfoService.add(enterpriseInfo);
			
			return success("添加成功");
		} catch (Exception e) {
			logger.error("添加企业信息出错，异常信息：{}", e);
		}
		return fail("添加企业信息失败，请联系管理员解决");
	}
	
}
