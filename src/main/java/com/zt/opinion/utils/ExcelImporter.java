package com.zt.opinion.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zt.opinion.mongodb.entity.Article;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title: ExcelImporter
 * </p>
 * <p>
 * Description: excel导入工具类
 * </p>
 * 
 * @author wjc
 * @date 2017年1月16日
 */
public class ExcelImporter {

	private static final Logger logger = LoggerFactory.getLogger(ExcelImporter.class);
	/**
	 * 文章信息导入模板映射，key是文章字段的英文名称，value是该字段在excel中的列索引（索引基于0）
	 */
	private static final Map<String, Integer> articleImportTemplateMappings = new HashMap<String, Integer>();
	private static final Map<String, Integer> articleImportTemplateMappings2 = new HashMap<String, Integer>();
	private static final Map<String, Integer> currentArticleImportTemplate = articleImportTemplateMappings2;
	private String fileName;

	static{
		articleImportTemplateMappings.put("title", 0);
		articleImportTemplateMappings.put("url", 1);
		articleImportTemplateMappings.put("publishDateTime", 2);
		articleImportTemplateMappings.put("author", 3);
		articleImportTemplateMappings.put("keywords", 4);
		articleImportTemplateMappings.put("media", 5);
		articleImportTemplateMappings.put("content", 6);
		articleImportTemplateMappings.put("crawlerDateTime", 8);
		articleImportTemplateMappings.put("brief", -1);

		articleImportTemplateMappings2.put("title", 7);
		articleImportTemplateMappings2.put("url", 2);
		articleImportTemplateMappings2.put("publishDateTime", 9);
		articleImportTemplateMappings2.put("author", 6);
		articleImportTemplateMappings2.put("keywords", 14);
		articleImportTemplateMappings2.put("media", 10);
		articleImportTemplateMappings2.put("content", 5);
		articleImportTemplateMappings2.put("crawlerDateTime", 4);
		articleImportTemplateMappings2.put("brief", 12);
	}

	public ExcelImporter(String fileName) {
		this.fileName = fileName;
	}

	/**
	 *
	 * <p>Description: 获取关键字列表</p>
	 * @param fis
	 * @return
	 * @author wjc
	 * @date 2017年1月18日
	 */
	public Map<String, Article> getArticleList(InputStream fis){
		Map<String, Article> result = new HashMap<String, Article>();
		try {
			Workbook book = getWorkbook(fis);
			if(book != null){
				Sheet sheet = book.getSheetAt(0);
				int firstRow = sheet.getFirstRowNum();
				int lastRow = sheet.getLastRowNum();
				// 除去标题行
				for (int i = firstRow + 1; i < lastRow + 1; i++) {
					Row row = sheet.getRow(i);

					int rownum = (i+1);
					boolean hasError = false;
					Article item = new Article();
					int firstCell = row.getFirstCellNum();
					int lastCell = row.getLastCellNum();
					for (int j = firstCell; j < lastCell; j++) {
						Cell cell = row.getCell(j);
						String value = "";
						if (cell != null) {
							if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								value = cell.getStringCellValue();
							}
						}
						if(j == currentArticleImportTemplate.get("title")){
							//标题
							item.setTitle(value);
						}else if(j == currentArticleImportTemplate.get("url")){
							//原文链接
							if(StringUtils.isNotEmpty(value)){
								if(!value.startsWith("http")){
									//如果原文链接字段存储的内容不是url，那么跳过本次循环，不做处理
									logger.error("第{}行的原文链接的内容不是URL，属于无效数据，不做处理", rownum);
									hasError = true;
								}else{
									item.setUrl(value);
									item.setUrlMD5(EncryptionUtils.md5(value));
								}
							}
						}else if(j == currentArticleImportTemplate.get("publishDateTime")){
							//发布时间
							try {
								if(cell != null){
									Date publishDateTime = cell.getDateCellValue();
									item.setPublishDate(DateUtils.formatDate(publishDateTime));
									item.setPublishDateTime(publishDateTime);
								}
							} catch (Exception e) {
								hasError = true;
								logger.error("第{}行获取发布时间出错，异常信息：{}", rownum, e.getMessage());
							}
						}else if(j == currentArticleImportTemplate.get("author")){
							//作者
							item.setAuthor(value);
						}else if(j == currentArticleImportTemplate.get("keywords")){
							//关键词
							item.setKeywords(value);
						}else if(j == currentArticleImportTemplate.get("media")){
							//来源（媒体名称）
							//这里获取的媒体名称可能不准确，需要根据文章的url获取媒体名称
//							item.setMedia(value);
						}else if(j == currentArticleImportTemplate.get("content")){
							//内容
							if(StringUtils.isContainMessyCode(value)){
								logger.error("第{}行的内容包含乱码，属于无效数据，不做处理", rownum);
								hasError = true;
							}else{
								item.setContent(value);
							}
						}else if(j == currentArticleImportTemplate.get("crawlerDateTime")){
							//采集时间
							try {
								if(cell != null){
									Date crawlerDateTime = cell.getDateCellValue();
									item.setCrawlerDate(DateUtils.formatDate(crawlerDateTime));
									item.setCrawlerDateTime(crawlerDateTime);
								}
							} catch (Exception e) {
								hasError = true;
								logger.error("第{}行获取采集时间出错，异常信息：{}", rownum, e.getMessage());
							}
						}else if(j == currentArticleImportTemplate.get("brief")){
							//FIXME 该字段的值暂时没有使用
							item.setBrief(value);
						}
					}
					if(!hasError){
						String content = item.getContent();
						if(StringUtils.isNotEmpty(content)){
							if(content.length() > 300){
								content = content.substring(0, 300);
							}
							if(content.startsWith("没有找到")
									|| (content.indexOf("没有找到与“"+item.getKeywords()+"” 相关的新闻") != -1)){
								//如果内容是以“没有找到”开头的数据，那么系统认为这是一条无效数据，不做处理
								continue;
							}
						}
						if(item.getPublishDateTime() == null){
							item.setPublishDateTime(DateUtils.currentDate());
							item.setPublishDate(DateUtils.formatDate(DateUtils.currentDate()));
						}
						String keywords = item.getKeywords();
						if(StringUtils.isNotEmpty(keywords) && !keywords.endsWith("万元人民币")){
							String urlMD5 = item.getUrlMD5();
							if(StringUtils.isNotEmpty(urlMD5) && result.get(urlMD5) == null){
								result.put(urlMD5, item);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Workbook getWorkbook(InputStream fis) throws IOException{
		Workbook book = null;
		if(isExcel2003()){
			book = new HSSFWorkbook(fis);
		}else if(isExcel2007()){
			book = new XSSFWorkbook(fis);
		}
		return book;
	}

	/**
	 *
	 * <p>Description: 根据文件名判断是否是2007格式的excel</p>
	 * @return
	 * @author wjc
	 * @date 2017年1月17日
	 */
	private boolean isExcel2007(){
		if(fileName == null){
			return false;
		}
		return fileName.toLowerCase().endsWith(".xlsx");
	}

	/**
	 *
	 * <p>Description: 根据文件名判断是否是2007格式的excel</p>
	 * @return
	 * @author wjc
	 * @date 2017年1月17日
	 */
	private boolean isExcel2003(){
		if(fileName == null){
			return false;
		}
		return fileName.toLowerCase().endsWith(".xls");
	}
}
