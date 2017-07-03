package com.zt.opinion.dao;

import java.util.List;

import com.zt.opinion.base.entity.PageResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Repository
public abstract class DataStoreFactory<T> {

	private boolean toEnsureIndexes = false; // 是否确认索引存在，默认false
	private boolean toEnsureCaps = false; // 是否确认caps存在，默认false
	protected Datastore datastore;

	private Morphia morphia;

	@Autowired
	private MongoProperties mongoProperties;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public DataStoreFactory() {
		morphia = new Morphia();
	}

	protected Datastore createInstance() {
		Datastore ds = morphia.createDatastore(new MongoClient(), mongoProperties.getDatabase());
        if(toEnsureIndexes){
            ds.ensureIndexes();
        }
        if(toEnsureCaps){
            ds.ensureCaps();
        }
        return ds;
	}

	
	
	public Morphia getMorphia() {
		return morphia;
	}

	public boolean isToEnsureIndexes() {
		return toEnsureIndexes;
	}

	public void setToEnsureIndexes(boolean toEnsureIndexes) {
		this.toEnsureIndexes = toEnsureIndexes;
	}

	public boolean isToEnsureCaps() {
		return toEnsureCaps;
	}

	public void setToEnsureCaps(boolean toEnsureCaps) {
		this.toEnsureCaps = toEnsureCaps;
	}
	
	public PageResult<T> findMorphiaPage(Integer pageNumber, Integer pageSize, DBObject queryObject, DBObject sortDBObject, String collectionName, Class<T> tClass){
		DBCursor dbCursor = mongoTemplate.getCollection(collectionName).find(queryObject);
		// 排序
		dbCursor.sort(sortDBObject);
		Integer skip = (pageNumber-1)*pageSize;
		// 分页查询
		dbCursor.skip(skip).limit(pageNumber);

		// 总数
		Integer count = dbCursor.count();
		// 循环指针
		List<T> list = Lists.newArrayList();
		while (dbCursor.hasNext()) {
			list.add(getMorphia().fromDBObject(createInstance(), tClass,
			 dbCursor.next()));
		}
		
		Double totalPages = 0D;
		Long totalElements = 0L;
		if(count != null){
			totalPages = Double.parseDouble(count.toString());
			totalElements = Long.valueOf(count.toString());
		}
		totalPages = Math.ceil(totalPages/pageSize);
		PageResult<T> page = new PageResult<>(list, totalPages.longValue(), totalElements, list.size());
		return page;
	}
}
