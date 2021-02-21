package com.deer.wms.base.system.model.ware;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.deer.wms.common.core.domain.BaseEntity;

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
public class ShelfInfo extends BaseEntity
{

	
	/** 货架ID */
	@Id
	@Column(name = "shelf_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shelfId;
	/** 货区编码 */
	private Integer areaId;
	/** 货架编码 */
	private String shelfCode;
	/** 货架名 */
	private String shelfName;
	/**  */
	private Integer shelfRow;
	/**  */
	private Integer shelfColumn;
	/**  */
	private String memo;

	public void setShelfId(Integer shelfId) 
	{
		this.shelfId = shelfId;
	}

	public Integer getShelfId() 
	{
		return shelfId;
	}
	public void setAreaId(Integer areaId) 
	{
		this.areaId = areaId;
	}

	public Integer getAreaId() 
	{
		return areaId;
	}
	public void setShelfCode(String shelfCode) 
	{
		this.shelfCode = shelfCode;
	}

	public String getShelfCode() 
	{
		return shelfCode;
	}
	public void setShelfName(String shelfName) 
	{
		this.shelfName = shelfName;
	}

	public String getShelfName() 
	{
		return shelfName;
	}
	public void setShelfRow(Integer shelfRow) 
	{
		this.shelfRow = shelfRow;
	}

	public Integer getShelfRow() 
	{
		return shelfRow;
	}
	public void setShelfColumn(Integer shelfColumn) 
	{
		this.shelfColumn = shelfColumn;
	}

	public Integer getShelfColumn() 
	{
		return shelfColumn;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("shelfId", getShelfId())
            .append("areaId", getAreaId())
            .append("shelfCode", getShelfCode())
            .append("shelfName", getShelfName())
            .append("shelfRow", getShelfRow())
            .append("shelfColumn", getShelfColumn())
            .append("memo", getMemo())
            .toString();
    }
}
