package com.zt.opinion.morphia;

import com.zt.opinion.BaseTests;
import com.zt.opinion.mongodb.entity.ArticleMorphia;
import com.zt.opinion.utils.DateUtils;
import com.zt.opinion.utils.EncryptionUtils;
import org.junit.Test;


public class MorphiaTests extends BaseTests {

	private String url ="/mongodb/";
	private String response;
	
	/**
	 * 
	 * <p>Description: 分页查询显示文章信息Morphia</p>
	 * @throws Exception
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	@Test
	public void articleMorphiaTest() throws Exception {
		url += "articleMorphiaTest";
		ArticleMorphia articleMorphia = new ArticleMorphia();
		articleMorphia.setPageNumber(2);
		articleMorphia.setPageSize(1);
		articleMorphia.setPublishDate("2017-06-30");
		response = performAndGetResponse(url, articleMorphia);
		logger.info("添加企业分组，参数：{}，响应结果：{}", articleMorphia, response);
	}
	
	/**
	 * 
	 * <p>Description: 保存文章信息Morphia</p>
	 * @return
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	@Test
	public void addArticleMorphiaTest(){
		url += "addArticleMorphia";
		ArticleMorphia articleMorphia = new ArticleMorphia();
		articleMorphia.setTitle("双十一天猫大战京东112");
		articleMorphia.setMedia("TechWeb");
		articleMorphia.setContent("11月11日，也许是史上最火爆的一届618年中购物节落下了帷幕，历史最终定格在京东18天累计下单金额1199亿的画面上。");
		articleMorphia.setEmotion("positive");
		articleMorphia.setPublishDateTime(DateUtils.currentDate());
		articleMorphia.setPublishDate(DateUtils.formatDate(DateUtils.currentDate()));
		articleMorphia.setHot(234);
		articleMorphia.setAuthor("TechWeb.com.cn");
		articleMorphia.setKeywords("京东 开普勒 零售业 水电 订单 ");
		String articleUrl = "http://www.techweb.com.cn/news/2017-06-22/2538014.shtml";
		articleMorphia.setUrl(articleUrl);
		articleMorphia.setUrlMD5(EncryptionUtils.md5(articleUrl));
		response = performAndGetResponse(url, articleMorphia);
		logger.info("添加企业分组，参数：{}，响应结果：{}", articleMorphia, response);
	}
	/**
	 * 
	 * <p>Description: 删除文章信息Morphia</p>
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	@Test
	public void delArticleMorphiaTest(){
		url += "delArticleMorphia";
		ArticleMorphia articleMorphia = new ArticleMorphia();
		articleMorphia.setId("595af0aeee0ef54ac00d72ae");
		response = performAndGetResponse(url, articleMorphia);
		logger.info("添加企业分组，参数：{}，响应结果：{}", articleMorphia, response);
	}
	
	/**
	 * 
	 * <p>Description: 更新文章信息Morphia</p>
	 * @author zhangtong
	 * @date 2017年6月30日
	 */
	@Test
	public void updateArticleMorphiaTest(){
		url += "updateArticleMorphia";
		ArticleMorphia articleMorphia = new ArticleMorphia();
		articleMorphia.setId("595607759e62e61da80c0fae");
		articleMorphia.setTitle("双十一京东创下11亿利润");
		response = performAndGetResponse(url, articleMorphia);
		logger.info("添加企业分组，参数：{}，响应结果：{}", articleMorphia, response);
	}
}
