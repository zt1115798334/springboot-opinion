package com.zt.opinion.service;

import java.util.List;

import com.zt.opinion.entity.EnterpriseInfo;

public interface EnterpriseInfoService {

	boolean batchAdd(List<EnterpriseInfo> list);
	
	boolean add(EnterpriseInfo entity);
	
	boolean isExist(String enterpriseName);
	
}
