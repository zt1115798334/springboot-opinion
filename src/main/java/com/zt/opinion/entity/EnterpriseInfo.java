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
 * <p>Title: EnterpriseInfo</p>
 * <p>Description: 企业信息实体类</p>
 * @author wjc
 * @date 2017年6月19日
 */
@Entity
@Table(name = "t_enterprise_info")
public class EnterpriseInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "enterprise_name", unique = false, nullable = false)
	private String enterpriseName;//企业名称（全称）
	@Column(name = "registered_capital", unique = false, nullable = false)
	private String registeredCapital;//注册资本
	@Column(name = "shareholder", unique = false, nullable = true)
	private String shareholder;//股东
	@Column(name = "foreign_investment", unique = false, nullable = true)
	private String foreignInvestment;//对外投资
	@Column(name = "business_scope", unique = false, nullable = true)
	private String businessScope;//经营范围
	@Column(name = "director_senior_supervisor", unique = false, nullable = true)
	private String directorSeniorSupervisor;//董高监（董事、高层管理人员、监事）
	@Column(name = "legal_person", unique = false, nullable = true)
	private String legalPerson;//法人
	@Column(name = "enterprise_abbreviation", unique = false, nullable = true)
	private String enterpriseAbbreviation;//公司简称
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getShareholder() {
		return shareholder;
	}
	public void setShareholder(String shareholder) {
		this.shareholder = shareholder;
	}
	public String getForeignInvestment() {
		return foreignInvestment;
	}
	public void setForeignInvestment(String foreignInvestment) {
		this.foreignInvestment = foreignInvestment;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getDirectorSeniorSupervisor() {
		return directorSeniorSupervisor;
	}
	public void setDirectorSeniorSupervisor(String directorSeniorSupervisor) {
		this.directorSeniorSupervisor = directorSeniorSupervisor;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getEnterpriseAbbreviation() {
		return enterpriseAbbreviation;
	}
	public void setEnterpriseAbbreviation(String enterpriseAbbreviation) {
		this.enterpriseAbbreviation = enterpriseAbbreviation;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
