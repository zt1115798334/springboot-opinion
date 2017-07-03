package com.zt.opinion.controller;

import java.util.List;

import com.zt.opinion.base.controller.BaseController;
import com.zt.opinion.entity.Group;
import com.zt.opinion.framework.beans.AjaxResult;
import com.zt.opinion.service.GroupMonitorEnterpriseService;
import com.zt.opinion.service.GroupService;
import com.zt.opinion.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p>Title: ExcelController</p>
 * <p>Description: 企业分组信息controller</p>
 * @author zhangtong
 * @date 2017年6月20日
 */
@Controller
@RequestMapping("/group")
public class GroupController extends BaseController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupMonitorEnterpriseService groupMonitorEnterpriseService;
	
	/**
	 * 
	 * <p>Description: 添加企业分组信息</p>
	 * @param group
	 * @return
	 * @author zhangtong
	 * @date 2017年6月20日
	 */
	@RequestMapping("addGroup")
	@ResponseBody
	public AjaxResult addGroup(@RequestBody Group group){
		if(null == group){
			return fail("企业分组信息不能为空");
		}
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		String groupName = group.getGroupName();
		boolean existsState = groupService.existsByUserAccountAndGroupName(userAccount, groupName);
		if(existsState){
			return fail("企业分组信息已存在");
		}
		group.setCreateDate(DateUtils.currentDate());
		group.setCreateUser(userAccount);
		group.setUserAccount(userAccount);
		groupService.add(group);
		return success("添加企业分组信息成功");
		
	}
	
	/**
	 * 
	 * <p>Description: 删除企业分组信息</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月20日
	 */
	@RequestMapping("delGroup")
	@ResponseBody
	public AjaxResult delById(@RequestBody Integer id){
		if( null == id){
			return fail("企业分组id不能为空");
		}
		groupService.del(id);
		return success("删除企业分组信息成功");
	}

	/**
	 * 
	 * <p>Description: 根据id更新企业分组信息</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月20日
	 */
	@RequestMapping("updateGroupName")
	@ResponseBody
	public AjaxResult updateGroupName(@RequestBody Group group){
		Integer id = group.getId();
		String groupName = group.getGroupName();
		if( null == id){
			return fail("企业分组id不能为空");
		}
		if(StringUtils.isEmpty(groupName)){
			return fail("企业名称不能为空");
		}
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		boolean existsState = groupService.existsByUserAccountAndGroupNameAndIdNot(userAccount, groupName, id);
		if(existsState){
			return fail("企业分组信息已存在");
		}
		groupService.updateGroupName(id, groupName);
		return success("修改企业分组信息成功");
	}
	
	/**
	 * 
	 * <p>Description: 根据用户帐号获取企业分组信息集合</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月20日
	 */
	@RequestMapping("listGroup")
	@ResponseBody
	public AjaxResult listGroup(){
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		List<Group> result = groupService.listGroup(userAccount);
		return success(result);
	}
	
	/**
	 * 
	 * <p>Description: 根据用户帐号和企业名称判断分组是否存在</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@RequestMapping("existsGroup")
	@ResponseBody
	public AjaxResult existsGroup(@RequestBody String groupName){
		if(StringUtils.isEmpty(groupName)){
			return fail("分组名称不能为空");
		}
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		boolean existsState = groupService.existsByUserAccountAndGroupName(userAccount, groupName);
		return success(existsState);
	}
	
	/**
	 * 
	 * <p>Description: 根据用户帐号和企业名称,不等于该id判断分组是否存在,用于更新</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@RequestMapping("existsGroupNotId")
	@ResponseBody
	public AjaxResult existsGroupNotId(@RequestBody Group group){
		if(null == group){
			return fail("企业分组信息不能为空");
		}
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		String groupName = group.getGroupName();
		Integer id = group.getId();
		boolean existsState = groupService.existsByUserAccountAndGroupNameAndIdNot(userAccount, groupName,id);
		return success(existsState);
	}
	
	/**
	 * 
	 * <p>Description: 查看该分组下面企业数目</p>
	 * @param id
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@RequestMapping("showGroupInsideMonitorEnterpriseCount")
	@ResponseBody
	public AjaxResult showGroupInsideMonitorEnterpriseCount(@RequestBody Integer id) {
		if( null == id){
			return fail("企业分组id不能为空");
		}
		Long count = groupMonitorEnterpriseService.countByEnterpriseId(id);
		return success(count);
	}
}
