package com.zt.opinion.mongodb.service;


import com.zt.opinion.mongodb.entity.Article;

import java.util.List;

public interface ArticleService {

	boolean add(Article entity);
	
	List<Article> findAll();
	
	boolean deleteAll();
	
	boolean update(Article entity);
	
	boolean deleteById(String id);
	
	boolean batchAdd(List<Article> list);
	
	Article findById(String id);
	
}
