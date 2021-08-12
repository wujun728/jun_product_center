package com.shuogesha.cms.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.shuogesha.platform.entity.UnifiedUser;
/**
 * 会员邀请码
 * @author zhaohaiyuan
 *
 */
public class Yaoqing implements Serializable {
	
		private Long id;
	private String name;//师傅邀请码
	private BigDecimal money=BigDecimal.valueOf(0.00);
	private String status;
	private Integer tudi_num=0;
 	
	private Long sid0;//师傅id
	
	private BigDecimal jiangli;//注册奖励
	
	private String code; 

	private UnifiedUser unifiedUser;   
	
	private String dateline;

	public Yaoqing() {
		super();
	}

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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTudi_num() {
		return tudi_num;
	}

	public void setTudi_num(Integer tudi_num) {
		this.tudi_num = tudi_num;
	}

	public Long getSid0() {
		return sid0;
	}

	public void setSid0(Long sid0) {
		this.sid0 = sid0;
	}

	public BigDecimal getJiangli() {
		return jiangli;
	}

	public void setJiangli(BigDecimal jiangli) {
		this.jiangli = jiangli;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UnifiedUser getUnifiedUser() {
		return unifiedUser;
	}

	public void setUnifiedUser(UnifiedUser unifiedUser) {
		this.unifiedUser = unifiedUser;
	}

	public String getDateline() {
		return dateline;
	}

	public void setDateline(String dateline) {
		this.dateline = dateline;
	}

	 
	
}
