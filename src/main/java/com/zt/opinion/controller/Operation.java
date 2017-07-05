package com.zt.opinion.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zt.opinion.mongodb.entity.Article;
import com.zt.opinion.mongodb.service.ArticleService;
import com.zt.opinion.utils.ExcelImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;


/**
 * Created by zhangtong on 2017/7/4.
 */
public class Operation {

    private ArticleService articleService;
    
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    public Operation() {
    }

    public Operation(ArticleService articleService) {
        this.articleService = articleService;
    }

    public List<Article> getReadExcel(String fileName, InputStream fis) {
        StringBuilder messages = new StringBuilder();

        ExcelImporter importer = new ExcelImporter(fileName);
        Map<String, Article> map = importer.getArticleList(fis);
        List<Article> list = Lists.newArrayList();
        if (map != null) {
        	StringBuilder message = null;
			for(Map.Entry<String, Article> entry : map.entrySet()){
				Article item = entry.getValue();
				message = new StringBuilder();
				if(articleService.isExist(item.getUrlMD5())){
					message.append("url[").append(item.getUrl())
						.append("]的文章在系统中已经存在，");
				}
				if(message.length() < 1){
					list.add(item);
				}else{
					messages.append(message);
				}
			}
        }
        logger.info("查询结果：{}", messages);
        return  list;
    }


    public void saveReadExcel(List<Article> list){
        int count = 0;
        int pageSize = 400;
        List<List<Article>> newList = splitList(list, pageSize);
        for(List<Article> subList : newList){
            articleService.batchAdd(subList);
        }
    }

    private static <T> List<List<T>> splitList(List<T> list, int pageSize){
        List<List<T>> result = new ArrayList<List<T>>();
        int size = list.size();
        List<T> subList = new ArrayList<T>();
        for(int i=0; i<size; i++){
            T user = list.get(i);
            if(subList.size() == pageSize){
                result.add(subList);
                subList = new ArrayList<T>();
                subList.add(user);
            }else{
                subList.add(user);
            }
        }
        result.add(subList);

        return result;
    }
}
