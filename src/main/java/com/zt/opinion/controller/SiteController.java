package com.zt.opinion.controller;

import com.zt.opinion.base.controller.BaseController;
import com.zt.opinion.entity.SiteInfo;
import com.zt.opinion.entity.Sitehandle;
import com.zt.opinion.framework.beans.AjaxResult;
import com.zt.opinion.service.SiteInfoService;
import com.zt.opinion.service.SitehandleService;
import com.zt.opinion.utils.WebsiteUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by zhangtong on 2017/7/11.
 */
@Controller
@RequestMapping("/site")
public class SiteController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SitehandleService sitehandleService;

    @Autowired
    private SiteInfoService siteInfoService;

    @RequestMapping("siteMethod")
    @ResponseBody
    public AjaxResult siteMethod() {

        List<SiteInfo> siteInfoList = siteInfoService.findAll();
        siteInfoList.forEach(siteInfo -> {
            String sitename = siteInfo.getSitename();
            System.out.println("siteInfo.getSitename() = " + sitename);
            String url = "";
            try {
                URL ul = new URL(siteInfo.getSiteurl());
                url = ul.getHost();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            boolean exist = sitehandleService.existName(url);
            System.out.println("exist = " + exist);
            logger.info("exist = " + exist);
            if(!exist){
                String media = WebsiteUtil.getWebSiteName(url);
                try {
                    Thread.sleep(200);//当前线程休眠300毫秒，以免对请求的网站造成太大压力
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (StringUtils.isNotEmpty(media)) {
                    Sitehandle sitehandle = new Sitehandle();
                    sitehandle.setName(siteInfo.getSitename());
                    sitehandle.setUrl(url);
                    sitehandle.setType(siteInfo.getSitetype());
                    sitehandleService.add(sitehandle);
                }
            }
        });

        return success(null);

    }
}
