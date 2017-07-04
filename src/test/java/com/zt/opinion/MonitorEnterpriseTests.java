package com.zt.opinion;

import java.util.Map;

import com.zt.opinion.BaseTests;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.zt.opinion.entity.MonitorEnterprise;
import com.zt.opinion.utils.DateUtils;

public class MonitorEnterpriseTests extends BaseTests {
	
	private String url ="/monitorEnterprise/";
	private String response;
	
	/**
	 * 
	 * <p>Description: 添加</p>
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@Test
	public void addMonitorEnterpriseTestTest(){
		url +="addMonitorEnterprise";
		MonitorEnterprise monitorEnterprise = new MonitorEnterprise();
		monitorEnterprise.setEnterpriseName("小米科技");
		monitorEnterprise.setGroupId(1);
		monitorEnterprise.setExpireDate(DateUtils.currentDateAddMonth(3));
		response = performAndGetResponse(url, monitorEnterprise);
		logger.info("添加监控企业信息，参数：{}，响应结果：{}", monitorEnterprise, response);
	}
	
	/**
	 * 
	 * <p>Description: 删除</p>
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@Test
	public void delMonitorEnterpriseTest(){
		url +="delMonitorEnterprise";
		Long id = 1L;
		response = performAndGetResponse(url, id);
		logger.info("删除监控企业信息，参数：{}，响应结果：{}", id, response);
	}
	
	/**
	 * 
	 * <p>Description: 批量删除监控企业信息</p>
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@Test
	public void batchDelMonitorEnterpriseTest() {
		url += "batchDelMonitorEnterprise";
		Long[] id = {2L,3L,4L};
		response = performAndGetResponse(url, id);
		logger.info("批量删除监控企业信息，参数：{}，响应结果：{}", id, response);
	}
	
	/**
	 * 
	 * <p>Description: 更新</p>
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@Test
	public void updateMonitorEnterpriseTest(){
		url +="updateMonitorEnterprise";
		MonitorEnterprise monitorEnterprise = new MonitorEnterprise();
		monitorEnterprise.setId(2L);
		monitorEnterprise.setEnterpriseName("小米科技有限公司");
		monitorEnterprise.setExpireDate(DateUtils.currentDateAddMonth(3));
		monitorEnterprise.setStatus("已到期");
		monitorEnterprise.setGroupId(1);
		response = performAndGetResponse(url, monitorEnterprise);
		logger.info("更新监控企业信息，参数：{}，响应结果：{}", monitorEnterprise, response);
	}
	
	/**
	 * 
	 * <p>Description: 根据用户帐号查询</p>
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@Test
	public void listMonitorEnterpriseTest(){
		url +="listMonitorEnterprise";
		response = performAndGetResponse(url, null);
		logger.info("根据用户账户查询监控企业信息，参数：{}，响应结果：{}", null, response);
	}
	
	/**
	 * 
	 * <p>Description: 根据账户查询监控企业中的信息多条件查询</p>
	 * @throws Exception
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@Test
	public void listMonitorEnterpriseByEntityTest(){
		url +="listMonitorEnterpriseByEntity";
		Integer groupId = 9;
		String enterpriseName = "";
		String status = "监控中";
		MonitorEnterprise  monitorEnterprise = new MonitorEnterprise();
		monitorEnterprise.setGroupId(groupId);
		monitorEnterprise.setEnterpriseName(enterpriseName);
		monitorEnterprise.setStatus(status);
		monitorEnterprise.setPageNumber(2);
		monitorEnterprise.setPageSize(2);
		response = performAndGetResponse(url, monitorEnterprise);
		logger.info("根据用户账户查询监控企业信息，参数：{}，响应结果：{}", monitorEnterprise, response);
	}
	
	/**
	 * 
	 * <p>Description: 根据用户账户，分组id和企业名称查询监控企业是否存在</p>
	 * @throws Exception
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@Test
	public void existsMonitorEnterpriseTest(){
		url +="existsMonitorEnterprise";
		Integer groupId = 9;
		String enterpriseName = "沭阳县胡集先军农业开发有限公司";
		MonitorEnterprise  monitorEnterprise = new MonitorEnterprise();
		monitorEnterprise.setGroupId(groupId);
		monitorEnterprise.setEnterpriseName(enterpriseName);
		response = performAndGetResponse(url, monitorEnterprise);
		logger.info("根据用户账户查询监控企业信息，参数：{}，响应结果：{}", monitorEnterprise, response);
	}
	
	/**
	 * 
	 * <p>Description: 根据分组id，监控企业名称和用户账户查询监控企业是否存在，用于更新</p>
	 * @throws Exception
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@Test
	public void existsMonitorEnterpriseNotIdTest(){
		url +="existsMonitorEnterpriseNotId";
		Map<String, Object> params = Maps.newHashMap();
		Integer groupId = 1;
		String enterpriseName = "江苏裕顺汽车电子传动科技有限公司";
		Long id = 2L;
		params.put("groupId", groupId);
		params.put("enterpriseName", enterpriseName);
		params.put("id", id);
		response = performAndGetResponse(url, params);
		logger.info("根据用户账户查询监控企业信息，参数：{}，响应结果：{}", params, response);
	}
}
