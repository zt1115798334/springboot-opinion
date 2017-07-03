package com.zt.opinion.mongodb.entity;

import java.io.Serializable;
import java.util.Date;

import com.zt.opinion.base.entity.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * 
 * <p>Title: Article</p>
 * <p>Description: 文章信息表 entity</p>
 * @author zhangtong
 * @date 2017年6月23日
 */
@Entity("article")
public class ArticleMorphia  extends BasePageRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9201034849892179274L;
	@Id
	private String id;

	@Property("title")
	private String title;// 标题
	
	@Property("media")
	private String media;// 媒体名称

	@Property("mediaType")
	private String mediaType;	//媒体类型
	
	@Property("content")
	private String content;// 内容
	
	@Property("emotion")
	private String emotion;// 情感
	
	@Property("publish_datetime")
	private Date publishDateTime;// 发布日期时间
	
	@Property("publish_date")
	private String publishDate;// 发布日期
	
	@Property("hot")
	private Integer hot;// 热度
	
	@Property("author")
	private String author;// 作者
	
	@Property("keywords")
	private String keywords;// 关键词
	
	@Property("url")
	private String url;// 原文链接
	
	@Property("url_md5")
	private String urlMD5;// url的MD5值
	
	@Property("crawler_date")
	private String crawlerDate;// 采集时间，格式：yyyy-MM-dd
	
	@Field("crawler_datetime")
	private Date crawlerDateTime;// 采集时间
	
	@Transient
	private String brief;// 内容摘要

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public Date getPublishDateTime() {
		return publishDateTime;
	}

	public void setPublishDateTime(Date publishDateTime) {
		this.publishDateTime = publishDateTime;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlMD5() {
		return urlMD5;
	}

	public void setUrlMD5(String urlMD5) {
		this.urlMD5 = urlMD5;
	}

	public String getCrawlerDate() {
		return crawlerDate;
	}

	public void setCrawlerDate(String crawlerDate) {
		this.crawlerDate = crawlerDate;
	}

	public Date getCrawlerDateTime() {
		return crawlerDateTime;
	}

	public void setCrawlerDateTime(Date crawlerDateTime) {
		this.crawlerDateTime = crawlerDateTime;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
