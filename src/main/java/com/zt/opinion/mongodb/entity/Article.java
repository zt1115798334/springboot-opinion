package com.zt.opinion.mongodb.entity;

import java.io.Serializable;
import java.util.Date;

import com.zt.opinion.base.entity.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * <p>Title: Article</p>
 * <p>Description: 文章信息表 entity</p>
 * @author zhangtong
 * @date 2017年6月23日
 */
@Document(collection = "article")
public class Article  extends BasePageRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9201034849892179274L;
	@Id
	@Indexed(unique=true)
	private String id;
	@Field("title")
	private String title;// 标题
	@Field("media")
	private String media;// 媒体名称
	@Field("content")
	private String content;// 内容
	@Field("emotion")
	private String emotion;// 情感
	@Field("publishDateTime")
	private Date publishDateTime;// 发布日期时间
	@Field("publishDate")
	private String publishDate;// 发布日期
	@Field("hot")
	private Integer hot;// 热度
	@Field("author")
	private String author;// 作者
	@Field("keywords")
	private String keywords;// 关键词
	@Field("url")
	private String url;// 原文链接
	@Field("urlMD5")
	private String urlMD5;// url的MD5值

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
