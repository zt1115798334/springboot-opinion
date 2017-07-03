package com.zt.opinion.controller;

import java.util.Arrays;
import java.util.List;

import com.zt.opinion.base.controller.BaseController;
import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.entity.MonitorEnterprise;
import com.zt.opinion.framework.beans.AjaxResult;
import com.zt.opinion.service.MonitorEnterpriseService;
import com.zt.opinion.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p>Title: MonitorEnterpriseController</p>
 * <p>Description: 监控企业信息controller</p>
 * @author zhangtong
 * @date 2017年6月21日
 */
@Controller
@RequestMapping("/monitorEnterprise")
public class MonitorEnterpriseController extends BaseController {

	@Autowired
	private MonitorEnterpriseService monitorEnterpriseService;
	
	/**
	 * 
	 * <p>Description: 添加监控企业信息</p>
	 * @param monitorEnterprise
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@RequestMapping("addMonitorEnterprise")
	@ResponseBody
	public AjaxResult addMonitorEnterprise(@RequestBody MonitorEnterprise monitorEnterprise) {
		if(null == monitorEnterprise){
			return fail("监控企业信息不能为空");
		}
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		monitorEnterprise.setUserAccount(userAccount);
		boolean existsState = monitorEnterpriseService.existsByUserAccountAndGroupIdAndEnterpriseName(monitorEnterprise);
		if(existsState){
			return fail("该分组下监控企业名称已存在");
		}
		monitorEnterprise.setCreateUser(userAccount);
		monitorEnterprise.setCreateDate(DateUtils.currentDate());
		monitorEnterprise.setStatus("监控中");
		monitorEnterprise.setUpdateDate(DateUtils.currentDate());
		monitorEnterprise.setUpdateUser(userAccount);
		monitorEnterpriseService.add(monitorEnterprise);
		return success("添加监控企业信息成功");
	}

	/**
	 * 
	 * <p>Description: 删除监控企业信息</p>
	 * @param id
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@RequestMapping("delMonitorEnterprise")
	@ResponseBody
	public AjaxResult delMonitorEnterprise(@RequestBody Long id) {
		if(null == id){
			return fail("监控企业信息Id不能为空");
		}
		monitorEnterpriseService.del(id);
		return success("删除监控企业信息成功");
	}

	/**
	 * 
	 * <p>Description: 批量删除监控企业信息</p>
	 * @param id
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@RequestMapping("batchDelMonitorEnterprise")
	@ResponseBody
	public AjaxResult batchDelMonitorEnterprise(@RequestBody Long[] id) {
		if(null == id){
			return fail("监控企业信息Id不能为空");
		}
		List<Long> idList = Arrays.asList(id);
		
		monitorEnterpriseService.batchDel(idList);
		return success("删除监控企业信息成功");
	}
	
	/**
	 * 
	 * <p>Description: 更新监控企业信息</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@RequestMapping("updateMonitorEnterprise")
	@ResponseBody
	public AjaxResult updateMonitorEnterprise(@RequestBody MonitorEnterprise monitorEnterprise) {
		if(null == monitorEnterprise){
			return fail("监控企业信息不能为空");
		}
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		String enterpriseName = monitorEnterprise.getEnterpriseName();
		Integer groupId = monitorEnterprise.getGroupId();
		Long id = monitorEnterprise.getId();
		boolean existsState = monitorEnterpriseService.existsByUserAccountAndGroupIdAndEnterpriseNameAndIdNot(userAccount, groupId, enterpriseName,id);
		if(existsState){
			return fail("该分组下监控企业名称已存在");
		}
		MonitorEnterprise old = monitorEnterpriseService.findOne(id);
		monitorEnterprise.setCreateDate(old.getCreateDate());
		monitorEnterprise.setCreateUser(old.getCreateUser());
		monitorEnterprise.setUserAccount(old.getUserAccount());
		monitorEnterprise.setUpdateDate(DateUtils.currentDate());
		monitorEnterprise.setUpdateUser(userAccount);
		monitorEnterpriseService.update(monitorEnterprise);
		return success("更新监控企业信息成功");
	}

	/**
	 * 
	 * <p>Description: 根据账户用户查询监控企业信息</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月21日
	 */
	@RequestMapping("listMonitorEnterprise")
	@ResponseBody
	public AjaxResult listMonitorEnterprise() {
		String userAccount = "1115798334";
		if(null == userAccount){
			return fail("用户账户不能为空");
		}
		List<MonitorEnterprise> list = monitorEnterpriseService.listMonitorEnterprise(userAccount);
		return success(list);
	}
	
	/**
	 * 
	 * <p>Description: 根据账户查询监控企业中的信息多条件查询</p>
	 * @param monitorEnterprise
	 * @return
	 * @author zhangtong
	 * @date 2017年6月23日
	 */
	@RequestMapping("listMonitorEnterpriseByEntity")
	@ResponseBody
	public AjaxResult listMonitorEnterpriseByEntity(@RequestBody MonitorEnterprise monitorEnterprise){
		if(null == monitorEnterprise){
			return fail("监控企业信息不能为空");
		}
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		monitorEnterprise.setUserAccount(userAccount);
		PageResult<MonitorEnterprise> list = monitorEnterpriseService.findPageByMonitorEnterprise(monitorEnterprise);
		return success(list);
	}
	
	/**
	 * 
	 * <p>Description: 根据分组id，监控企业名称和用户账户查询监控企业是否存在</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@RequestMapping("existsMonitorEnterprise")
	@ResponseBody
 	public AjaxResult existsMonitorEnterprise(@RequestBody MonitorEnterprise monitorEnterprise){
		if(null == monitorEnterprise){
			return fail("监控企业信息不能为空");
		}
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		monitorEnterprise.setUserAccount(userAccount);
		boolean existsState = monitorEnterpriseService.existsByUserAccountAndGroupIdAndEnterpriseName(monitorEnterprise);
		return success(existsState);
	
	}
	
	/**
	 * 
	 * <p>Description: 根据分组id，监控企业名称和用户账户查询监控企业是否存在，用于更新</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月22日
	 */
	@RequestMapping("existsMonitorEnterpriseNotId")
	@ResponseBody
	public AjaxResult existsMonitorEnterpriseNotId(Integer groupId, String enterpriseName, Long id){
		if(null == groupId){
			return fail("分组id不能为空");
		}
		if(StringUtils.isEmpty(enterpriseName)){
			return fail("监控企业名称不能为空");
		}
		if(null == id){
			return fail("企业id不能为空");
		}
		String userAccount = "1115798334";
		if(StringUtils.isEmpty(userAccount)){
			return fail("用户帐号不能为空");
		}
		boolean existsState = monitorEnterpriseService.existsByUserAccountAndGroupIdAndEnterpriseNameAndIdNot(userAccount, groupId, enterpriseName,id);
		return success(existsState);
	}
}
