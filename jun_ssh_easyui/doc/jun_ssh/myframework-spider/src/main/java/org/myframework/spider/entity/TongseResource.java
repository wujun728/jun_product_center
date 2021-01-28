package org.myframework.spider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.myframework.commons.util.DateUtils;

@Entity
@Table(name = "TongseResource")
public class TongseResource {
	@Id
	String id ;
	
	String imgAbsUrl;
	
	String playAbsUrl ;
	
	String introduce ;
	
	String htmlPage ;
	
	String timestamp = DateUtils.getCurrentDateTimeAsString();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgAbsUrl() {
		return imgAbsUrl;
	}

	public void setImgAbsUrl(String imgAbsUrl) {
		this.imgAbsUrl = imgAbsUrl;
	}

	public String getPlayAbsUrl() {
		return playAbsUrl;
	}

	public void setPlayAbsUrl(String playAbsUrl) {
		this.playAbsUrl = playAbsUrl;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getHtmlPage() {
		return htmlPage;
	}

	public void setHtmlPage(String htmlPage) {
		this.htmlPage = htmlPage;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp =timestamp ;
	}
	
}
