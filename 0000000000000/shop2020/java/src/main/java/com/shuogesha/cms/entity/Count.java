package com.shuogesha.cms.entity;

import java.io.Serializable;
/**
 * 对象相关的统计
 * @author zhaohaiyuan
 *
 */
public class Count implements Serializable {
	
		 
	private static final long serialVersionUID = 2276351626347537369L;

	private Long id; 
	private Long referid; //对象id
	private String refer; //对象
	private Integer views=0; //浏览量
	private Integer viewsmonth=0; 
	private Integer viewsweek=0;
 
	private Integer viewsday=0;
 
	private Integer comments=0;//评论数
 
	private Integer commentsmonth=0; 
	private Integer commentsweek=0; 	private Integer commentsday=0;
	private Integer praise=0;//喜欢的数量
	private Integer collect=0;//收藏数量  


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getReferid() {
		return referid;
	}


	public void setReferid(Long referid) {
		this.referid = referid;
	}


	public String getRefer() {
		return refer;
	}


	public void setRefer(String refer) {
		this.refer = refer;
	}


	public Integer getViews() {
		return views;
	}


	public void setViews(Integer views) {
		this.views = views;
	}


	public Integer getViewsmonth() {
		return viewsmonth;
	}


	public void setViewsmonth(Integer viewsmonth) {
		this.viewsmonth = viewsmonth;
	}


	public Integer getViewsweek() {
		return viewsweek;
	}


	public void setViewsweek(Integer viewsweek) {
		this.viewsweek = viewsweek;
	}


	public Integer getViewsday() {
		return viewsday;
	}


	public void setViewsday(Integer viewsday) {
		this.viewsday = viewsday;
	}


	public Integer getComments() {
		return comments;
	}


	public void setComments(Integer comments) {
		this.comments = comments;
	}


	public Integer getCommentsmonth() {
		return commentsmonth;
	}


	public void setCommentsmonth(Integer commentsmonth) {
		this.commentsmonth = commentsmonth;
	}


	public Integer getCommentsweek() {
		return commentsweek;
	}


	public void setCommentsweek(Integer commentsweek) {
		this.commentsweek = commentsweek;
	}


	public Integer getCommentsday() {
		return commentsday;
	}


	public void setCommentsday(Integer commentsday) {
		this.commentsday = commentsday;
	}


	public Integer getPraise() {
		return praise;
	}


	public void setPraise(Integer praise) {
		this.praise = praise;
	}


	public Integer getCollect() {
		return collect;
	}


	public void setCollect(Integer collect) {
		this.collect = collect;
	}
 
	
}
