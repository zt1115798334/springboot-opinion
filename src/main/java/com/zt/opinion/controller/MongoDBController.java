package com.zt.opinion.controller;

import java.util.List;

import com.zt.opinion.base.controller.BaseController;
import com.zt.opinion.framework.beans.AjaxResult;
import com.zt.opinion.mongodb.entity.Article;
import com.zt.opinion.mongodb.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * <p>Title: MongoDBController</p>
 * <p>Description: 仅供测试使用</p>
 * @author wjc
 * @date 2017年6月23日
 */
@Controller
@RequestMapping("/mongodb")
public class MongoDBController extends BaseController {
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 
	 * <p>Description: </p>
	 * @return
	 * @author wjc
	 * @date 2017年6月22日
	 */
	@RequestMapping("addArticle")
	@ResponseBody
	public AjaxResult addArticle(@RequestBody Article entity) {
		articleService.add(entity);
		return success("添加成功");
	}
	
	@RequestMapping("findArticleList")
	@ResponseBody
	public AjaxResult findArticleList() {
		List<Article> list = articleService.findAll();
		return success(list);
	}
	
	@RequestMapping("deleteAllArticle")
	@ResponseBody
	public AjaxResult deleteAllArticle() {
		articleService.deleteAll();
		return success("删除所有文章成功");
	}
	
	@RequestMapping("batchAddArticle")
	@ResponseBody
	public AjaxResult batchAddArticle(@RequestBody List<Article> list) {
		articleService.batchAdd(list);
		return success("批量添加成功");
	}
	
	@RequestMapping("deleteById")
	@ResponseBody
	public AjaxResult deleteById(@RequestBody String id) {
		articleService.deleteById(id);
		return success("删除成功");
	}
	
	@RequestMapping("updateArticle")
	@ResponseBody
	public AjaxResult updateArticle(@RequestBody Article article) {
		articleService.update(article);
		return success("更新成功");
	}
	
}
