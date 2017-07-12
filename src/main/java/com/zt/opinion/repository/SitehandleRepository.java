package com.zt.opinion.repository;

import com.zt.opinion.entity.Sitehandle;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhangtong on 2017/7/11.
 */
public interface SitehandleRepository extends CrudRepository<Sitehandle, Long> {

    Sitehandle findOneByUrl(String name);
}
