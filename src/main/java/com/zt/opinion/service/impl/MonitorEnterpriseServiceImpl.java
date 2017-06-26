package com.zt.opinion.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.dao.CommonSearchDao;
import com.zt.opinion.entity.GroupMonitorEnterprise;
import com.zt.opinion.entity.MonitorEnterprise;
import com.zt.opinion.repository.GroupMonitorEnterpriseRepository;
import com.zt.opinion.repository.MonitorEnterpriseRepository;
import com.zt.opinion.service.MonitorEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * 
 * <p>Title: MonitorEnterpriseServiceImpl</p>
 * <p>Description: 监控企业信息接口实现类</p>
 * @author zhangtong
 * @date 2017年6月21日
 */
@Transactional
@Service
public class MonitorEnterpriseServiceImpl implements MonitorEnterpriseService {
	
	@Autowired
	private MonitorEnterpriseRepository monitorEnterpriseRepository;
	
	@Autowired
	private GroupMonitorEnterpriseRepository groupMonitorEnterpriseRepository;
	
	@Autowired
	private CommonSearchDao commonSearchDao;
	
	@Override
	public boolean add(MonitorEnterprise entity) {
		Integer groupId = entity.getGroupId();
		MonitorEnterprise monitorEnterprise= monitorEnterpriseRepository.save(entity);
		GroupMonitorEnterprise groupMonitorEnterprise = new GroupMonitorEnterprise();
		groupMonitorEnterprise.setEnterpriseId(monitorEnterprise.getId());
		groupMonitorEnterprise.setGroupId(groupId);
		groupMonitorEnterpriseRepository.save(groupMonitorEnterprise);
		return true;
	}

	@Override
	public boolean del(Long id) {
		monitorEnterpriseRepository.delete(id);
		groupMonitorEnterpriseRepository.deleteByEnterpriseId(id);
		return true;
	}
	
	@Override
	public boolean batchDel(List<Long> ids) {
		List<MonitorEnterprise> listME = ids.stream().map(i ->{
			MonitorEnterprise me = new MonitorEnterprise();
			me.setId(i);
			return me;
		}).collect(toList());
		ids.stream().forEach(id->{
			groupMonitorEnterpriseRepository.deleteByEnterpriseId(id);
		});
		monitorEnterpriseRepository.delete(listME);
		return false;
	}
	
	@Override
	public boolean update(MonitorEnterprise entity) {
		monitorEnterpriseRepository.save(entity);
		return true;
	}

	@Override
	public List<MonitorEnterprise> listMonitorEnterprise(String userAccount) {
		List<MonitorEnterprise> list = monitorEnterpriseRepository.findListByUserAccount(userAccount);
		return list;
	}

	@Override
	public boolean batchAdd(List<MonitorEnterprise> list,Integer groupId) {
		List<MonitorEnterprise> ids = (List<MonitorEnterprise>) monitorEnterpriseRepository.save(list);
		List<GroupMonitorEnterprise> listGME = ids.stream().map(MonitorEnterprise::getId).map(id ->{
			GroupMonitorEnterprise gme = new GroupMonitorEnterprise();
			gme.setEnterpriseId(id);
			gme.setGroupId(groupId);
			return gme;
		}).collect(toList());
		groupMonitorEnterpriseRepository.save(listGME);
		return true;
	}

	@Override
	public MonitorEnterprise findOne(Long id) {
		return monitorEnterpriseRepository.findOne(id);
	}
	
	@Override
	public List<MonitorEnterprise> findListByIds(List<Long> ids) {
		Iterable<MonitorEnterprise> iterable = monitorEnterpriseRepository.findAll(ids);
		List<MonitorEnterprise> list = Lists.newArrayList();		
		iterable.forEach(list::add);
		return list;
	}
	
	@Override
	public List<MonitorEnterprise> findByMonitorEnterprise(MonitorEnterprise monitorEnterprise) {
		return commonSearchDao.findListByMonitorEnterprise(monitorEnterprise);
	}
	
	@Override
	public PageResult<MonitorEnterprise> findPageByMonitorEnterprise(MonitorEnterprise monitorEnterprise) {
		return commonSearchDao.findPageByMonitorEnterprise(monitorEnterprise);
	}
	
	@Override
	public boolean existsByUserAccountAndGroupIdAndEnterpriseName(MonitorEnterprise monitorEnterprise) {
		List<MonitorEnterprise> list = commonSearchDao.findListByMonitorEnterprise(monitorEnterprise);
		return CollectionUtils.isEmpty(list) ? false : true;
	}

	@Override
	public boolean existsByUserAccountAndGroupIdAndEnterpriseNameAndIdNot(String userAccount, Integer groupId, String enterpriseName, Long id) {
		MonitorEnterprise monitorEnterprise = monitorEnterpriseRepository.findListByUserAccountAndGroupIdAndEnterpriseNameAndIdNot(userAccount, groupId, enterpriseName, id);
		return monitorEnterprise == null ? false : true;
	}

}
