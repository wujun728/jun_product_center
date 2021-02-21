package com.deer.wms.base.system.model.ware;

import com.deer.wms.common.core.domain.BaseEntity;
import com.deer.wms.common.core.service.QueryCriteria;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 货架设置表 shelf_info
 * 
 * @author deer
 * @date 2019-05-08
 */
public class ShelfInfoCriteria extends QueryCriteria
{
	private Integer CellId;
	private String shelfName;

	public Integer getCellId() {
		return CellId;
	}

	public void setCellId(Integer cellId) {
		CellId = cellId;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
}
