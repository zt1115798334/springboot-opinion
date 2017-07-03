package com.zt.opinion;

import com.google.common.collect.Maps;
import com.zt.opinion.utils.WebsiteUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangtong on 2017/7/3.
 */
public class CrawlerTest {


    @Test
    public void testPost(){
        List<String> urlList = Arrays.asList(
                "www.baidu.com",
                "www.mi.com",
                "www.meizu.com",
                "www.jd.com",
                "www.tmall.com","www.baiduxiaxiaoxiao.com");
        List<String> siteName = WebsiteUtil.getWebSiteName(urlList);
        siteName.forEach(System.out::println);

//        String site = WebsiteUtil.getWebSiteName("www.baiduxiaxiaoxiao.com");
//        System.out.println(site);
    }
}
