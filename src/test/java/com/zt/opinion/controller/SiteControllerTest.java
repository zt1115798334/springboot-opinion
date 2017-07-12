package com.zt.opinion.controller;

import com.zt.opinion.BaseTests;
import org.junit.Test;

/**
 * Created by zhangtong on 2017/7/11.
 */

public class SiteControllerTest extends BaseTests {

    private String url = "/site/";
    private String response;

    @Test
    public void siteMethod() throws Exception {
        url += "siteMethod";
        response = performAndGetResponse(url, null);
        logger.info("添加企业分组，参数：{}，响应结果：{}", null, response);
    }


}
