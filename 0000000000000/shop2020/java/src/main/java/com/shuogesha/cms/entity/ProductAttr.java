package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductAttr implements Serializable {
	
		private Long id;
	private Long productId;
	private String name;
	private String attributes;
	private BigDecimal price;
	private Integer stock;
	private String status="0";//0上架,1下架
	private String icon;
	
	private String img;//状态
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id=id;
	} 
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getAttributes() {
		return this.attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes=attributes;
	}
	public BigDecimal getPrice() {
		return this.price;
	}
	public void setPrice(BigDecimal price) {
		this.price=price;
	}
	public Integer getStock() {
		return this.stock;
	}
	public void setStock(Integer stock) {
		this.stock=stock;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public String getIcon() {
		return this.icon;
	}
	public void setIcon(String icon) {
		this.icon=icon;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
}
