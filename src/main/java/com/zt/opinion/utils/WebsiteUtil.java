package com.zt.opinion.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zt.opinion.constants.SysConst;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * <p>Title: WebsiteUtil</p>
 * <p>Description: 爬虫工具类</p>
 * @author zhangtong
 * @date 2017年7月3日
 */
public class WebsiteUtil {

    private static final String url ="http://icp.chinaz.com/searchs";
    
    private WebsiteUtil(){

    }

    public static String getWebSiteName(String urlP){
    	Map<String, String> map = Maps.newHashMap();
        map.put("urls",urlP);
        map.put("btn_search","查询");
        String html = pickPostData(url,map, SysConst.ENCODING_UTF_8);
        List<String> result = analyzeHTMLByString(html);

        return result.size()>0 ? result.get(0):null;
    }

    public static List<String> getWebSiteName(List<String> urlP){
        StringBuilder sb = new StringBuilder();
        urlP.stream().forEach( item ->{
            sb.append(item);
            sb.append(System.lineSeparator());
        });
        Map<String, String> map = Maps.newHashMap();
        map.put("urls",sb.toString());
        map.put("btn_search","查询");
        String html = pickPostData(url,map,SysConst.ENCODING_UTF_8);
        return analyzeHTMLByString(html);
    }

    /*
     * 爬取网页信息(get)
     */
    private static String pickPostData(String url, Map<String,String> map, String charset) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = Lists.newArrayList();
            map.forEach((key,value)->{
                list.add(new BasicNameValuePair(key,value));
            });
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static List<String> analyzeHTMLByString(String html){
    	if(StringUtils.isEmpty(html)){
    		return new ArrayList<String>();
    	}
        Document document = Jsoup.parse(html);
        Element resutltData=document.getElementById("result_table");
        if(resutltData!=null){
            List<Node> listNode = resutltData.childNodes();
            List<String> result =listNode.stream()
                    .filter(item -> StringUtils.isNotBlank(item.toString())
                    ).map(item ->{
                        if(item.toString().contains("未备案或者备案取消")){
                            return  null;
                        }else{
                            return item.childNode(7).childNode(0).toString();
                        }

                    }).collect(Collectors.toList());
            return result;
        }else{
            return  Lists.newArrayList();
        }

    }

}
