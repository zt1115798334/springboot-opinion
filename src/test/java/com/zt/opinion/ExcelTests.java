package com.zt.opinion;

import org.junit.Test;

public class ExcelTests extends BaseTests{
	private String url ="/excel/";
	private String response;
	/**
	 * 
	 * <p>Description: 监控企业信息导出excel</p>
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@Test
	public void exportMonitorEnterpriseTest(){
		url += "exportMonitorEnterprise";
		Long[] id = {1L,2L};
		response = performAndGetResponse(url, id);
		logger.info("监控企业信息导出excel，参数：{}，响应结果：{}", id, response);
	}
	@Test
	public void test(){
		url += "test";
		response = performAndGetResponse(url, null);
		logger.info("监控企业信息导出excel，参数：{}，响应结果：{}", null, response);
	}

}
