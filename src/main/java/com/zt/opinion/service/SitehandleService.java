package com.zt.opinion.service;

import com.zt.opinion.entity.Sitehandle;

/**
 * Created by zhangtong on 2017/7/11.
 */

public interface SitehandleService {

    void add(Sitehandle entity);

    boolean existName(String name);

}
