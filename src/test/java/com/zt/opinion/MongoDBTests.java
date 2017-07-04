package com.zt.opinion;

import java.util.ArrayList;
import java.util.List;

import com.zt.opinion.BaseTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zt.opinion.mongodb.entity.Article;
import com.zt.opinion.mongodb.service.ArticleService;
import com.zt.opinion.utils.DateUtils;
import com.zt.opinion.utils.EncryptionUtils;

public class MongoDBTests extends BaseTests {
	
	private String url ="/mongodb/";
	private String response;
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 
	 * <p>Description: 测试添加文章</p>
	 * @author wjc
	 * @date 2017年6月22日
	 */
	@Test
	public void testAddArticle(){
		url += "addArticle";
		Article article = new Article();
		article.setTitle("618引入订单量同比增长1200% 京东开普勒搭建零售业的水电煤网");
		article.setMedia("TechWeb");
		article.setContent("6月19日，也许是史上最火爆的一届618年中购物节落下了帷幕，历史最终定格在京东18天累计下单金额1199亿的画面上。");
		article.setEmotion("positive");
		article.setPublishDateTime(DateUtils.currentDate());
		article.setPublishDate(DateUtils.formatDate(DateUtils.currentDate()));
		article.setHot(234);
		article.setAuthor("TechWeb.com.cn");
		article.setKeywords("京东 开普勒 零售业 水电 订单 ");
		String articleUrl = "http://www.techweb.com.cn/news/2017-06-22/2538014.shtml";
		article.setUrl(articleUrl);
		article.setUrlMD5(EncryptionUtils.md5(articleUrl));
		response = performAndGetResponse(url, article);
		logger.info("添加文章，参数：{}，响应结果：{}", article, response);
	}
	
	@Test
	public void testFindArticleList(){
		url += "findArticleList";
		response = performAndGetResponse(url, null);
		logger.info("查询文章，参数：{}，响应结果：{}", null, response);
	}
	
	@Test
	public void testDeleteAllArticle(){
		url += "deleteAllArticle";
		response = performAndGetResponse(url, null);
		logger.info("删除所有文章，参数：{}，响应结果：{}", null, response);
	}
	
	@Test
	public void testBatchAddArticle(){
		url += "batchAddArticle";
		List<Article> articleList = buildArticleList();
		response = performAndGetResponse(url, articleList);
		logger.info("批量添加文章，参数：{}，响应结果：{}", articleList, response);
	}
	
	private List<Article> buildArticleList(){
		List<Article> result = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("618引入订单量同比增长1200% 京东开普勒搭建零售业的水电煤网");
		article.setMedia("TechWeb");
		article.setContent("6月19日，也许是史上最火爆的一届618年中购物节落下了帷幕，历史最终定格在京东18天累计下单金额1199亿的画面上。");
		article.setEmotion("positive");
		article.setPublishDateTime(DateUtils.currentDate());
		article.setPublishDate(DateUtils.formatDate(DateUtils.currentDate()));
		article.setHot(234);
		article.setAuthor("TechWeb.com.cn");
		article.setKeywords("京东 开普勒 零售业 水电 订单 ");
		String articleUrl = "http://www.techweb.com.cn/news/2017-06-22/2538014.shtml";
		article.setUrl(articleUrl);
		article.setUrlMD5(EncryptionUtils.md5(articleUrl));
		result.add(article);
		
		article = new Article();
		article.setTitle("618引入订单量同比增长1200% 京东开普勒搭建零售业的水电煤网");
		article.setMedia("中国经济网");
		article.setContent("5月25日京东618全民年中购物节启动发布会上，京东集团CMO徐雷宣布：开放的京东要做零售业的“水电煤气”，由渠道商变身为电商基础设施服务提供商。");
		article.setEmotion("positive");
		article.setPublishDateTime(DateUtils.currentDate());
		article.setPublishDate(DateUtils.formatDate(DateUtils.currentDate()));
		article.setHot(234);
		article.setAuthor("TechWeb.com.cn");
		article.setKeywords("京东 开普勒 零售业 水电 订单 ");
		articleUrl = "http://finance.ce.cn/gsxw/201706/22/t20170622_23794029.shtml";
		article.setUrl(articleUrl);
		article.setUrlMD5(EncryptionUtils.md5(articleUrl));
		result.add(article);
		
		return result;
	}
	
	@Test
	public void testDeleteById(){
		url += "deleteById";
		String id = "594c707deaf1a124a4334d5c";
		response = performAndGetResponse(url, id);
		logger.info("根据id删除文章，参数：{}，响应结果：{}", id, response);
	}
	
	@Test
	public void testUpdateArticle(){
		url += "updateArticle";
		String id = "594c726ceaf1a127e0f4baad";
		Article article = articleService.findById(id);
		article.setTitle("京东开普勒搭建零售业的水电煤网");
		article.setContent("5月25日京东618全民年中购物节启动发布会上，京东集团CMO徐雷宣布：开放的京东要做零售业的“水电煤气”，由渠道商变身为电商基础设施服务提供商。");
		article.setMedia("中国经济网");
		String articleUrl = "http://finance.ce.cn/gsxw/201706/22/t20170622_23794029.shtml";
		article.setUrl(articleUrl);
		article.setUrlMD5(EncryptionUtils.md5(articleUrl));
		response = performAndGetResponse(url, article);
		logger.info("更新文章，参数：{}，响应结果：{}", article, response);
	}
}
