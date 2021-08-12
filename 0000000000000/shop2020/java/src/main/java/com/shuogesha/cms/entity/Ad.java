package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.util.Date;

public class Ad implements Serializable {
	
		private Long id;
	private String name;
	private String sort;
	private String code;
	private String start_time;
	private String end_time;
	private String img;
	private String url;
	private String status;
	
	private String remark;

	private Adsense adsense;	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getSort() {
		return this.sort;
	}
	public void setSort(String sort) {
		this.sort=sort;
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code=code;
	}
	 
	
	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img=img;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark=remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Adsense getAdsense() {
		return adsense;
	}

	public void setAdsense(Adsense adsense) {
		this.adsense = adsense;
	}

	
}
