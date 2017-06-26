package com.zt.opinion.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.dao.CommonSearchDao;
import com.zt.opinion.entity.MonitorEnterprise;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;


/**
 * 
 * <p>Title: CommonSearchDaoImpl</p>
 * <p>Description: 公共的查询Dao接口实现类</p>
 * @author wjc
 * @date 2017年1月3日
 */
@Repository
public class CommonSearchDaoImpl implements CommonSearchDao {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorEnterprise> findListByMonitorEnterprise(MonitorEnterprise monitorEnterprise) {
		String userAccount=monitorEnterprise.getUserAccount();
		Integer groupId = monitorEnterprise.getGroupId();
		String enterpriseName = monitorEnterprise.getEnterpriseName();
		String status = monitorEnterprise.getStatus();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.* FROM t_monitor_enterprise a INNER JOIN t_group_monitor_enterprise b ON a.id = b.id "
				+ "WHERE 1=1");
		Map<String, Object> map = Maps.newHashMap();
		if(StringUtils.isNotEmpty(userAccount)){
			sql.append(" AND a.user_account =:userAccount");
			map.put("userAccount", userAccount);
		}
		if(null != groupId){
			sql.append(" AND b.group_id =:groupId");
			map.put("groupId", groupId);
		}
		if(StringUtils.isNotEmpty(enterpriseName)){
			sql.append(" AND a.enterprise_name =:enterpriseName");
			map.put("enterpriseName", enterpriseName);
		}
		if(StringUtils.isNotEmpty(status)){
			sql.append(" AND a.status =:status");
			map.put("status", status);
		}
		Query query = em.createNativeQuery(sql.toString(), MonitorEnterprise.class);
		map.forEach((k,v)->{
			query.setParameter(k, v);
		});
		List<MonitorEnterprise> list = query.getResultList();
		em.clear();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageResult<MonitorEnterprise> findPageByMonitorEnterprise(MonitorEnterprise monitorEnterprise) {
		String userAccount=monitorEnterprise.getUserAccount();
		Integer groupId = monitorEnterprise.getGroupId();
		String enterpriseName = monitorEnterprise.getEnterpriseName();
		String status = monitorEnterprise.getStatus();
		StringBuilder sql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		sql.append("SELECT a.* FROM t_monitor_enterprise a INNER JOIN t_group_monitor_enterprise b ON a.id = b.id "
				+ "WHERE 1=1");
		countSql.append("SELECT count(1) FROM t_monitor_enterprise a INNER JOIN t_group_monitor_enterprise b ON a.id = b.id "
				+ "WHERE 1=1");
		Map<String, Object> map = Maps.newHashMap();
		if(StringUtils.isNotEmpty(userAccount)){
			sql.append(" AND a.user_account =:userAccount");
			countSql.append(" AND a.user_account =:userAccount");
			map.put("userAccount", userAccount);
		}
		if(null != groupId){
			sql.append(" AND b.group_id =:groupId");
			countSql.append(" AND b.group_id =:groupId");
			map.put("groupId", groupId);
		}
		if(StringUtils.isNotEmpty(enterpriseName)){
			sql.append(" AND a.enterprise_name =:enterpriseName");
			countSql.append(" AND a.enterprise_name =:enterpriseName");
			map.put("enterpriseName", enterpriseName);
		}
		if(StringUtils.isNotEmpty(status)){
			sql.append(" AND a.status =:status");
			countSql.append(" AND a.status =:status");
			map.put("status", status);
		}
		Query query = em.createNativeQuery(sql.toString(), MonitorEnterprise.class);
		Query countQ = em.createNativeQuery(countSql.toString());
		map.forEach((k,v)->{
			query.setParameter(k, v);
			countQ.setParameter(k, v);
		});
		Integer pageNumber = monitorEnterprise.getPageNumber();
		Integer pageSize = monitorEnterprise.getPageSize();
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		List<MonitorEnterprise> list = query.getResultList();
		Object totalPageObj = countQ.getSingleResult();
		Double totalPages = 0D;
		Long totalFilterElement = 0L;
		if(StringUtils.isNotEmpty(totalPageObj.toString())){
            totalPages = Double.parseDouble(totalPageObj.toString());
            totalFilterElement = Long.parseLong(totalPageObj.toString());
        }
		totalPages = Math.ceil(totalPages/pageSize);
		em.clear();
		PageResult<MonitorEnterprise> page = new PageResult<MonitorEnterprise>(list,totalPages.longValue(), totalFilterElement,0);
		return page;
	}
	
}
