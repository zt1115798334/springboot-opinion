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

    private String url ="http://icp.chinaz.com/searchs";
    private String charset = "utf-8";

    @Test
    public void testGet(){
        System.out.println(WebsiteUtil.pickGetData(url));
    }

    @Test
    public void testPost(){
        List<String> urlList = Arrays.asList(
                "www.baidu.com",
                "www.mi.com",
                "www.meizu.com",
                "www.jd.com",
                "www.tmall.com");
        StringBuilder sb = new StringBuilder();
        urlList.stream().forEach( item ->{
            sb.append(item);
            sb.append(System.lineSeparator());
        });
        Map<String, String> map = Maps.newHashMap();
        map.put("urls",sb.toString());
        map.put("btn_search","查询");
        String html = WebsiteUtil.pickPostData(url,map,charset);
        List<String> siteName = WebsiteUtil.analyzeHTMLByString(html);
        siteName.forEach(System.out::println);
    }
}
