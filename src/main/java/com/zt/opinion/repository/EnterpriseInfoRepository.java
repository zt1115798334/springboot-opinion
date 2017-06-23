package com.zt.opinion.repository;

import com.zt.opinion.entity.EnterpriseInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * <p>Title: EnterpriseInfoRepository</p>
 * <p>Description: 企业信息的数据库接口</p>
 * @author wjc
 * @date 2017年6月19日
 */
public interface EnterpriseInfoRepository extends CrudRepository<EnterpriseInfo, Long> {

	EnterpriseInfo findByEnterpriseName(String enterpriseName);
	
}
