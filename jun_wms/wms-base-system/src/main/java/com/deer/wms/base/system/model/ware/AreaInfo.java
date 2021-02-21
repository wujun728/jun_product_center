package com.deer.wms.base.system.model.ware;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.deer.wms.common.core.domain.BaseEntity;

import javax.persistence.*;

/**
 * 货区设置表 area_info
 * 
 * @author deer
 * @date 2019-05-08
 */
@Table(name = "area_info")
public class AreaInfo extends BaseEntity
{

	
	/** 货区ID */
	@Id
	@Column(name = "area_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer areaId;
	/** 货区编码 */
	private String areaCode;
	/** 货区名 */
	private String areaName;
	/** 添加时间 */
	private Integer wareId;
	/** 仓库Id */
	private Integer createUserId;
	/**  */
	private String memo;

	public void setAreaId(Integer areaId) 
	{
		this.areaId = areaId;
	}

	public Integer getAreaId() 
	{
		return areaId;
	}
	public void setAreaCode(String areaCode) 
	{
		this.areaCode = areaCode;
	}

	public String getAreaCode() 
	{
		return areaCode;
	}
	public void setAreaName(String areaName) 
	{
		this.areaName = areaName;
	}

	public String getAreaName() 
	{
		return areaName;
	}
	public void setWareId(Integer wareId) 
	{
		this.wareId = wareId;
	}

	public Integer getWareId() 
	{
		return wareId;
	}
	public void setCreateUserId(Integer createUserId) 
	{
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() 
	{
		return createUserId;
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
            .append("areaId", getAreaId())
            .append("areaCode", getAreaCode())
            .append("areaName", getAreaName())
            .append("wareId", getWareId())
            .append("createTime", getCreateTime())
            .append("createUserId", getCreateUserId())
            .append("memo", getMemo())
            .toString();
    }
}
