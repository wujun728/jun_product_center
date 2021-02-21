package com.deer.wms.base.system.model.ware;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.deer.wms.common.core.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 货位设置表 cell_info
 * 
 * @author deer
 * @date 2019-05-08
 */
public class CellInfo
{

	
	/** 货位ID */
	@Id
	@Column(name = "cell_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cellId;
	/** 货架编码 */
	private Integer shelfId;
	/** 货物编码 */
	private String cellCode;
	/** 备注 */
	private String memo;
	/**  行(层) */
	private Integer sRow;
	/**  列 */
	private Integer sColumn;
	/** 0-无货；1-有货；2-锁定；3-故障 */
	private Integer state;

	public void setCellId(Integer cellId) 
	{
		this.cellId = cellId;
	}

	public Integer getCellId() 
	{
		return cellId;
	}
	public void setShelfId(Integer shelfId) 
	{
		this.shelfId = shelfId;
	}

	public Integer getShelfId() 
	{
		return shelfId;
	}
	public void setCellCode(String cellCode) 
	{
		this.cellCode = cellCode;
	}

	public String getCellCode() 
	{
		return cellCode;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setSRow(Integer sRow) 
	{
		this.sRow = sRow;
	}

	public Integer getSRow() 
	{
		return sRow;
	}
	public void setSColumn(Integer sColumn) 
	{
		this.sColumn = sColumn;
	}

	public Integer getSColumn() 
	{
		return sColumn;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cellId", getCellId())
            .append("shelfId", getShelfId())
            .append("cellCode", getCellCode())
            .append("memo", getMemo())
            .append("sRow", getSRow())
            .append("sColumn", getSColumn())
            .append("state", getState())
            .toString();
    }
}
