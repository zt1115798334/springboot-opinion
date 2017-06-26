package com.zt.opinion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * <p>Title: GroupMonitorEnterprise</p>
 * <p>Description: 企业分组和监控企业关联信息实体类</p>
 * @author zhangtong
 * @date 2017年6月21日
 */
@Entity
@Table(name = "t_group_monitor_enterprise")
public class GroupMonitorEnterprise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;	//主键id，自增1
	
	@Column(name = "group_id", unique = false, nullable = false)
    private Integer groupId;	//分组id，关联t_group表的id
	
	@Column(name = "enterprise_id", unique = false, nullable = false)
    private Long enterpriseId;	//企业id，关联t_monitor_enterprise表的id
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Integer getGroupId() {
		return groupId;
	}



	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}



	public Long getEnterpriseId() {
		return enterpriseId;
	}



	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
