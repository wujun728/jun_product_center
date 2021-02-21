package com.deer.wms.base.system.model.ware;

import com.deer.wms.common.core.service.QueryCriteria;

/**
 * 条件查询货位表 cell_info相关
 * 
 * @author deer
 * @date 2019-07-04				markeloff
 */
public class CellInfoCriteria extends QueryCriteria
{
	/** 物料编码*/
	private String itemCode;
	/** 批次*/
	private String batch;
	private Integer typeAndState;
	private String boxCode;
	private String exp;

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}

	public Integer getTypeAndState() {
		return typeAndState;
	}

	public void setTypeAndState(Integer typeAndState) {
		this.typeAndState = typeAndState;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
}
