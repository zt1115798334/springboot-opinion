package com.zt.opinion.mongodb.service.impl;

import java.util.List;

import com.zt.opinion.mongodb.entity.Article;
import com.zt.opinion.mongodb.repository.ArticleRepository;
import com.zt.opinion.mongodb.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * <p>Title: ArticleRepository</p>
 * <p>Description: 文章信息接口实现类</p>
 * @author zhangtong
 * @date 2017年6月23日
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public boolean add(Article entity) {
		articleRepository.insert(entity);
		return true;
	}

	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public boolean deleteAll() {
		articleRepository.deleteAll();
		return true;
	}

	@Override
	public boolean update(Article entity) {
		articleRepository.save(entity);
		return true;
	}

	@Override
	public boolean deleteById(String id) {
		articleRepository.delete(id);
		return true;
	}

	@Override
	public boolean batchAdd(List<Article> list) {
		articleRepository.insert(list);
		return true;
	}

	@Override
	public Article findById(String id) {
		return articleRepository.findOne(id);
	}

}
