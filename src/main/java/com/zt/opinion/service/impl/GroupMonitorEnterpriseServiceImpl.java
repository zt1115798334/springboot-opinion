package com.zt.opinion.service.impl;

import java.util.List;

import com.zt.opinion.entity.GroupMonitorEnterprise;
import com.zt.opinion.repository.GroupMonitorEnterpriseRepository;
import com.zt.opinion.service.GroupMonitorEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <p>Title: GroupMonitorEnterpriseServiceImpl</p>
 * <p>Description: 企业分组和监控企业关联信息实现接口类</p>
 * @author zhangtong
 * @date 2017年6月21日
 */
@Transactional
@Service
public class GroupMonitorEnterpriseServiceImpl implements GroupMonitorEnterpriseService {

	@Autowired
	private GroupMonitorEnterpriseRepository groupMonitorEnterpriseRepository;
	
	
	@Override
	public boolean add(GroupMonitorEnterprise entity) {
		groupMonitorEnterpriseRepository.save(entity);
		return true;
	}

	@Override
	public boolean batchAdd(List<GroupMonitorEnterprise> list) {
		groupMonitorEnterpriseRepository.save(list);
		return true;
	}

	@Override
	public boolean deleteByGroupId(Integer groupId) {
		groupMonitorEnterpriseRepository.deleteByGroupId(groupId);
		return true;
	}

	@Override
	public boolean deleteByEnterpriseId(Long enterpriseId) {
		groupMonitorEnterpriseRepository.deleteByEnterpriseId(enterpriseId);
		return true;
	}

	@Override
	public List<GroupMonitorEnterprise> findListByGroupId(Integer groupId) {
		return groupMonitorEnterpriseRepository.findListByGroupId(groupId);
	}

	@Override
	public long countByEnterpriseId(Integer groupId) {
		return groupMonitorEnterpriseRepository.countByGroupId(groupId);
	}
}
