package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.util.List;

public class ProductCat implements Serializable {
	
		private Long id;
	private String name;
	private ProductCat parent;//上级
	private String img;
	private String icon;
	private String hiden;//是否显示
	private Integer sort;
	private String remark;
	
	private String itemParam;//其他参数
	
	// 子分类
	private List<ProductCat> children; 
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
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img=img;
	}
	public String getIcon() {
		return this.icon;
	}
	public void setIcon(String icon) {
		this.icon=icon;
	}
	public String getHiden() {
		return this.hiden;
	}
	public void setHiden(String hiden) {
		this.hiden=hiden;
	}
	public Integer getSort() {
		return this.sort;
	}
	public void setSort(Integer sort) {
		this.sort=sort;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark=remark;
	}

	public ProductCat getParent() {
		return parent;
	}

	public void setParent(ProductCat parent) {
		this.parent = parent;
	}

	public String getItemParam() {
		return itemParam;
	}

	public void setItemParam(String itemParam) {
		this.itemParam = itemParam;
	}

	public List<ProductCat> getChildren() {
		return children;
	}

	public void setChildren(List<ProductCat> children) {
		this.children = children;
	} 
	
}
