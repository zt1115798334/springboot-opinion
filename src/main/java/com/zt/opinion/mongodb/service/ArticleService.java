package com.zt.opinion.mongodb.service;


import com.zt.opinion.base.entity.PageResult;
import com.zt.opinion.mongodb.entity.Article;
import com.zt.opinion.mongodb.entity.ArticleMorphia;

import java.util.List;

public interface ArticleService {

	boolean add(Article entity);

	List<Article> findAll();

	boolean deleteAll();

	boolean update(Article entity);

	boolean deleteById(String id);

	boolean batchAdd(List<Article> list);

	Article findById(String id);

	boolean isExist(String urlMD5);

	/**
	 *
	 * <p>Description: 查询唯一的所有企业</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月27日
	 */
	List<String> findOnlyAll();

	/**
	 *
	 * <p>Description: 保存文章信息Morphia</p>
	 * @param entity
	 * @return
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	boolean addArticleMorphia(ArticleMorphia entity);

	/**
	 *
	 * <p>Description: 删除文章信息Morphia</p>
	 * @param entity
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	boolean delArticleMorphia(ArticleMorphia entity);

	/**
	 *
	 * <p>Description: 更新文章信息Morphia</p>
	 * @param entity
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	boolean updateArticleMorphia(ArticleMorphia entity);

	/**
	 *
	 * <p>Description: 文章信息Morphia查询</p>
	 * @param entity
	 * @return
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	PageResult<ArticleMorphia> getArticleMorphiaByEntity (ArticleMorphia entity);
}
