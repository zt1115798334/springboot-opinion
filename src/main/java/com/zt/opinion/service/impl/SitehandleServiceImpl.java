package com.zt.opinion.service.impl;

import com.zt.opinion.entity.Sitehandle;
import com.zt.opinion.repository.SitehandleRepository;
import com.zt.opinion.service.SitehandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangtong on 2017/7/11.
 */
@Transactional
@Service
public class SitehandleServiceImpl implements SitehandleService {

    @Autowired
    private SitehandleRepository sitehandleRepository;

    @Override
    public void add(Sitehandle entity) {
        sitehandleRepository.save(entity);
    }

    @Override
    public boolean existName(String name) {
        Sitehandle sitehandle = sitehandleRepository.findOneByUrl(name);
        return (sitehandle != null);
    }
}
