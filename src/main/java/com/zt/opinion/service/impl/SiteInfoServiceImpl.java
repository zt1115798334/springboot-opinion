package com.zt.opinion.service.impl;

import com.google.common.collect.Lists;
import com.zt.opinion.entity.SiteInfo;
import com.zt.opinion.repository.SiteInfoRepository;
import com.zt.opinion.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangtong on 2017/7/11.
 */
@Service
public class SiteInfoServiceImpl implements SiteInfoService {

    @Autowired
    private SiteInfoRepository siteInfoRepository;

    @Override
    public List<SiteInfo> findAll() {
        Iterable<SiteInfo> iterable = siteInfoRepository.findAll();
        List<SiteInfo> list = Lists.newArrayList();
        iterable.forEach(list::add);
        return  list;
    }
}
