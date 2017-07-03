package com.zt.opinion.dao;


import com.mongodb.DBObject;
import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.mongodb.entity.ArticleMorphia;

public interface ArticleMorphiaSearchDao {
	
	/**
	 * 
	 * <p>Description: 保存文章信息Morphia</p>
	 * @param entity
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	void addArticleMorphia(ArticleMorphia entity);
	
	/**
	 * 
	 * <p>Description: 删除文章信息Morphia</p>
	 * @param entity
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	void delArticleMorphia(ArticleMorphia entity);
	
	/**
	 * 
	 * <p>Description: 更新文章信息Morphia</p>
	 * @param entity
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	void updateArticleMorphia(ArticleMorphia entity);
	
	/**
	 * 
	 * <p>Description: 查询文章信息Morphia</p>
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	PageResult<ArticleMorphia> findArticleMorphia(Integer pageNumber, Integer pageSize, DBObject queryObject, DBObject sortDBObject, String collectionName);
}
