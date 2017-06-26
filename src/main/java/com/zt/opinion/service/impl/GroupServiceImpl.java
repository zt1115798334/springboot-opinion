package com.zt.opinion.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.zt.opinion.entity.Group;
import com.zt.opinion.entity.GroupMonitorEnterprise;
import com.zt.opinion.entity.MonitorEnterprise;
import com.zt.opinion.repository.GroupMonitorEnterpriseRepository;
import com.zt.opinion.repository.GroupRepository;
import com.zt.opinion.repository.MonitorEnterpriseRepository;
import com.zt.opinion.service.GroupService;

import com.zt.opinion.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <p>Title: GroupServiceImpl</p>
 * <p>Description: 企业分组信息接口实现类</p>
 * @author zhangtong
 * @date 2017年6月20日
 */
@Transactional
@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private MonitorEnterpriseRepository monitorEnterpriseRepository;
	
	@Autowired
	private GroupMonitorEnterpriseRepository groupMonitorEnterpriseRepository;
	
	
	
	@Override
	public boolean add(Group entity) {
		groupRepository.save(entity);
		return true;
	}

	@Override
	public boolean del(Integer id) {
		
		List<GroupMonitorEnterprise> listGME = groupMonitorEnterpriseRepository.findListByGroupId(id);
		
		List<MonitorEnterprise> listME = listGME.stream().map(gme -> {
			MonitorEnterprise me = new MonitorEnterprise();
			me.setId(gme.getEnterpriseId());
			return me;
		}).collect(toList());
		groupRepository.delete(id);
		groupMonitorEnterpriseRepository.deleteByGroupId(id);	//删除企业分组和监控企业关联信息
		monitorEnterpriseRepository.delete(listME);	//删除监控企业信息
		return true;
	}

	@Override
	public boolean updateGroupName(Integer id,String groupName) {
		groupRepository.updateGroupName(id, groupName);
		return true;
	}

	@Override
	public List<Group> listGroup(String userAccount) {
		return groupRepository.findListByUserAccount(userAccount);
	}
	
	@Override
	public Group defaultGroup(String userAccount) {
		Group group = groupRepository.findOneByUserAccountAndGroupName(userAccount,"默认分组");
		if(group == null){
			group = new Group();
			group.setCreateDate(DateUtils.currentDate());
			group.setCreateUser(userAccount);
			group.setGroupName("默认分组");
			group.setUserAccount(userAccount);
			group = groupRepository.save(group);
		}
		return group;
	}
	
	@Override
	public Group findOneByUserAccountGroupName(String userAccount, String groupName) {
		return groupRepository.findOneByUserAccountAndGroupName(userAccount,groupName);
	}

	@Override
	public boolean existsByUserAccountAndGroupName(String userAccount, String groupName) {
		Group group = groupRepository.findOneByUserAccountAndGroupName(userAccount, groupName);
		return group == null ? false : true;
	}

	@Override
	public boolean existsByUserAccountAndGroupNameAndIdNot(String userAccount, String groupName, Integer id) {
		Group group = groupRepository.findOneByUserAccountAndGroupNameAndIdNot(userAccount, groupName, id);
		return group == null ? false : true;
	}
}
