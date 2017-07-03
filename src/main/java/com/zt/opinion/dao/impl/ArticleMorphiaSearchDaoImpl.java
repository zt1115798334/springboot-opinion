package com.zt.opinion.dao.impl;

import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.dao.ArticleMorphiaSearchDao;
import com.zt.opinion.dao.DataStoreFactory;
import com.zt.opinion.mongodb.entity.ArticleMorphia;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;

@Repository
public class ArticleMorphiaSearchDaoImpl extends DataStoreFactory<ArticleMorphia> implements ArticleMorphiaSearchDao {

	public ArticleMorphiaSearchDaoImpl() {
		super();
		super.getMorphia().map(ArticleMorphia.class);
	}
	
	@Override
	public void addArticleMorphia(ArticleMorphia entity) {
		createInstance().save(entity);
	}

	@Override
	public void delArticleMorphia(ArticleMorphia entity) {
		ObjectId objectId = new ObjectId(entity.getId());
		Query<ArticleMorphia> query = createInstance().createQuery(ArticleMorphia.class).field("_id").equal(objectId);;
		createInstance().delete(query);
	}

	@Override
	public void updateArticleMorphia(ArticleMorphia entity) {
		ObjectId objectId = new ObjectId(entity.getId());
		Query<ArticleMorphia> query = createInstance().createQuery(ArticleMorphia.class).field("_id").equal(objectId);
		UpdateOperations<ArticleMorphia> operations = createInstance().createUpdateOperations(ArticleMorphia.class).set("title", entity.getTitle());
		createInstance().update(query, operations);
	}

	@Override
	public PageResult<ArticleMorphia> findArticleMorphia(Integer pageNumber, Integer pageSize, DBObject queryObject, DBObject sortDBObject, String collectionName) {
		PageResult<ArticleMorphia> page = findMorphiaPage(pageNumber,pageSize,queryObject,sortDBObject,collectionName,ArticleMorphia.class);
		return page;
	}

}
