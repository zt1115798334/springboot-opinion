package com.zt.opinion.jsoup;

import com.zt.opinion.entity.Group;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangtong on 2017/7/4.
 */
public class JsoupTest {
    private String html = "<html><head><title>First parse</title></head>"
            + "<body><p>Parsed HTML into a doc.</p></body></html>";
    @Test
    public void test1(){
        Document doc = Jsoup.parse(html);
        Element el = doc.body();
        System.out.println(el.toString());
    }

    @Test
    public void test2(){
        Document doc = Jsoup.parseBodyFragment(html);
        Element el = doc.body();
        System.out.println(el.toString());
    }

    @Test
    public void test3() throws IOException {
        Document doc = Jsoup.connect("http://www.baidu.com").get();
        String title = doc.title();
        System.out.println(title);
    }

    @Test
    public void test4(){
        List<Group> list = null;
        Group group = new Group();
//        list.add(group);
        System.out.println(group==null);
        System.out.println(CollectionUtils.isEmpty(list));
        System.out.println(StringUtils.isNotBlank(" "));
        System.out.println(StringUtils.isNotEmpty(" "));

        System.out.println(com.zt.opinion.utils.StringUtils.getWebsiteUrl("http://www.cnblogs.com/pengyingh/"));

        System.out.println("0d46a8e4d38835fa8f86444fefc8dfe8".length());
    }
}
