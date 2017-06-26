package com.zt.opinion.jms;

import com.zt.opinion.BaseTests;
import org.junit.Test;

import com.zt.opinion.entity.Group;

public class GroupTests extends BaseTests {
	
	private String url ="/group/";
	private String response;
	/**
	 * 
	 * <p>Description: 添加企业分组信息测试</p>
	 * @author zhangtong
	 * @date 2017年6月20日
	 */
	@Test
	public void addGroupTest(){
		url += "addGroup";
		Group group = new Group();
		group.setGroupName("默认分组12");
		response = performAndGetResponse(url, group);
		logger.info("添加企业分组，参数：{}，响应结果：{}", group, response);
	}
	/**
	 * 
	 * <p>Description: 删除企业分组信息测试</p>
	 * @author zhangtong
	 * @date 2017年6月20日
	 */
	@Test
	public void delGroupTest(){
		url += "delGroup";
		Integer id = 9;
		response = performAndGetResponse(url, id);
		logger.info("删除企业分组，参数：{}，响应结果：{}", id, response);
	}
	
	/**
	 * 
	 * <p>Description: 更新企业分组信息测试</p>
	 * @author zhangtong
	 * @date 2017年6月20日
	 */
	@Test
	public void updateGroupNameTest(){
		url += "updateGroupName";
		Integer id = 9;
		Group group = new Group();
		group.setId(id);
		group.setGroupName("默认分组1");
		response = performAndGetResponse(url, group);
		logger.info("修改企业分组，参数：{}，响应结果：{}", group, response);
	}
	/**
	 * 
	 * <p>Description: 根据用户帐号查询企业分组信息测试</p>
	 * @author zhangtong
	 * @date 2017年6月20日
	 */
	@Test
	public void listGroupTest(){
		url += "listGroup";
		response = performAndGetResponse(url, null);
		logger.info("根据用户查询企业分组，参数：{}，响应结果：{}", null, response);
	}
	/**
	 * 
	 * <p>Description: 根据分组名称查询是否存在测试</p>
	 * @throws Exception
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@Test
	public void existsGroup() throws Exception {
		url += "existsGroup";
		String groupName = "默认分组";
		response = performAndGetResponse(url, groupName);
		logger.info("根据用户帐号和企业名称判断分组是否存在，参数：{}，响应结果：{}", groupName, response);
	}
	/**
	 * 
	 * <p>Description: 根据用户帐号和分组名称查询,不等于该id是否存在测试</p>
	 * @throws Exception
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@Test
	public void existsGroupNotIdTest() throws Exception {
		url += "existsGroupNotId";
		Group group = new Group();
		Integer id = 9;
		group.setId(id);
		group.setGroupName("默认分组12");
		response = performAndGetResponse(url, group);
		logger.info("根据用户帐号和分组名称查询,不等于该id是否存在，参数：{}，响应结果：{}", group, response);
	}
	
	/**
	 * 
	 * <p>Description: 查看该分组下面企业数目</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@Test
	public void showGroupInsideMonitorEnterpriseCountTest(){
		url += "showGroupInsideMonitorEnterpriseCount";
		Integer id = 9;
		response = performAndGetResponse(url, id);
		logger.info("查看该分组下面企业数目，参数：{}，响应结果：{}", id, response);
	}
}
