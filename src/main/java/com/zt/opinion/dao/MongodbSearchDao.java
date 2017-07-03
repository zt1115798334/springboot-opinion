package com.zt.opinion.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.mongodb.entity.Article;


public interface MongodbSearchDao {
	

	/**
	 * 
	 * <p>Description: 只查询唯一的keyword值</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月27日
	 */
	List<String> finArtcileKeywords();

	PageResult<Article> findListByEntity(Article entity);
	
}
