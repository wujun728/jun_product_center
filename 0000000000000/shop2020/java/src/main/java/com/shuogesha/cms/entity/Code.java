package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Code implements Serializable {
	
		private Long id; 	private String name; 	private String type; 
	private BigDecimal yaoqing=BigDecimal.valueOf(0.00);//邀请提成
	private BigDecimal yaoqingFee;//充值or消费提成
	private BigDecimal shouyiFee;//徒弟邀约订单的收益提成
	private String remark;//备注
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getYaoqing() {
		return yaoqing;
	}
	public void setYaoqing(BigDecimal yaoqing) {
		this.yaoqing = yaoqing;
	}
	public BigDecimal getYaoqingFee() {
		return yaoqingFee;
	}
	public void setYaoqingFee(BigDecimal yaoqingFee) {
		this.yaoqingFee = yaoqingFee;
	}
	public BigDecimal getShouyiFee() {
		return shouyiFee;
	}
	public void setShouyiFee(BigDecimal shouyiFee) {
		this.shouyiFee = shouyiFee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	} 
	
}
