package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cart implements Serializable {
	
		private Long id;
	private String name;
	private BigDecimal  price;
	private Integer num;
	private String img;
	private String dateline;
	private Long userId;
	private Long attrId;
	private String remark;
	private BigDecimal discount;
	
	private Long referid; //对象id

	private String refer; //对象
	public Long getId() {
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
	public BigDecimal getPrice() {
		return this.price;
	}
	public void setPrice(BigDecimal price) {
		this.price=price;
	}
	public Integer getNum() {
		return this.num;
	}
	public void setNum(Integer num) {
		this.num=num;
	}
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img=img;
	}
	public String getDateline() {
		return this.dateline;
	}
	public void setDateline(String dateline) {
		this.dateline=dateline;
	} 
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark=remark;
	}
	public BigDecimal getDiscount() {
		return this.discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount=discount;
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
}
