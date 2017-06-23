package com.zt.opinion.service.impl;

import java.util.List;

import com.zt.opinion.entity.EnterpriseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zt.opinion.repository.EnterpriseInfoRepository;
import com.zt.opinion.service.EnterpriseInfoService;

@Service
public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {

	@Autowired
	private EnterpriseInfoRepository enterpriseInfoRepository;
	
	@Override
	public boolean batchAdd(List<EnterpriseInfo> list) {
		enterpriseInfoRepository.save(list);
		return true;
	}

	@Override
	public boolean add(EnterpriseInfo entity) {
		enterpriseInfoRepository.save(entity);
		return true;
	}

	@Override
	public boolean isExist(String enterpriseName) {
		EnterpriseInfo entity = enterpriseInfoRepository.findByEnterpriseName(enterpriseName);
		return (entity != null);
	}

}
