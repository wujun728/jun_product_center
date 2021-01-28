package org.myframework.spider.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.myframework.commons.util.DateUtils;


@Entity
@Table(name = "TongsePageReadLog")
public class TongsePageReadLog {
	@Id
	String pageId ;
	
	String currentpage;
	
	String nextpage ;
	
	String isOk ;
	
	 
	String timestamp = DateUtils.getCurrentDateTimeAsString();


	public String getPageId() {
		return pageId;
	}


	public void setPageId(String pageId) {
		this.pageId = pageId;
	}


	public String getCurrentpage() {
		return currentpage;
	}


	public void setCurrentpage(String currentpage) {
		this.currentpage = currentpage;
	}


	public String getNextpage() {
		return nextpage;
	}


	public void setNextpage(String nextpage) {
		this.nextpage = nextpage;
	}


	public String getIsOk() {
		return isOk;
	}


	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}


	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
