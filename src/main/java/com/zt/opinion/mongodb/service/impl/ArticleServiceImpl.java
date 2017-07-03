package com.zt.opinion.mongodb.service.impl;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.dao.ArticleMorphiaSearchDao;
import com.zt.opinion.dao.MongodbSearchDao;
import com.zt.opinion.mongodb.entity.Article;
import com.zt.opinion.mongodb.entity.ArticleMorphia;
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

	@Autowired
	private MongodbSearchDao mongodbSearchDao;

	@Autowired
	private ArticleMorphiaSearchDao morphiaSearchDao;

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

	@Override
	public boolean isExist(String urlMD5) {
		Article article = articleRepository.findByUrlMD5(urlMD5);
		return (article != null);
	}

	@Override
	public List<String> findOnlyAll() {
		return mongodbSearchDao.finArtcileKeywords();
	}

	@Override
	public boolean addArticleMorphia(ArticleMorphia entity) {
		morphiaSearchDao.addArticleMorphia(entity);
		return true;
	}

	@Override
	public boolean delArticleMorphia(ArticleMorphia entity) {
		morphiaSearchDao.delArticleMorphia(entity);
		return true;
	}

	@Override
	public boolean updateArticleMorphia(ArticleMorphia entity) {
		morphiaSearchDao.updateArticleMorphia(entity);
		return true;
	}

	@Override
	public PageResult<ArticleMorphia> getArticleMorphiaByEntity(ArticleMorphia entity) {
		String collectionName = "article";
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.append("publish_date", entity.getPublishDate());
		DBObject sortDBObject = new BasicDBObject();
		sortDBObject.put("publish_date", 1);
		PageResult<ArticleMorphia> page = morphiaSearchDao.findArticleMorphia(entity.getPageNumber(),entity.getPageSize(), basicDBObject, sortDBObject, collectionName);
		return page;
	}
}
