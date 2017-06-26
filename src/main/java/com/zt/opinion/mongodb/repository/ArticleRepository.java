package com.zt.opinion.mongodb.repository;


import com.zt.opinion.mongodb.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 * <p>Title: ArticleRepository</p>
 * <p>Description: 文章信息数据库接口</p>
 * @author zhangtong
 * @date 2017年6月23日
 */
public interface ArticleRepository extends MongoRepository<Article, String> {

}
