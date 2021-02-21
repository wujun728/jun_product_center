package com.deer.wms.base.system.model.ware;

import com.deer.wms.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;

/**
 * 出入库口表 door
 * 
 * @author deer
 * @date 2019-05-12
 */
public class Door extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 类型：1-入库口 2-出库口 */
	private Integer type;
	/** 状态 */
	private Integer state;
	/** 仓库ID */
	private Integer wareId;
	@Column(name = "address_code")
	private String addressCode;

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}
	public void setWareId(Integer wareId) 
	{
		this.wareId = wareId;
	}

	public Integer getWareId() 
	{
		return wareId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("type", getType())
            .append("state", getState())
            .append("wareId", getWareId())
			.append("addressCode", getAddressCode())
            .toString();
    }

	public Door() {
	}

	public Door(Integer id, String code, String name, Integer type, Integer state, Integer wareId) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
		this.state = state;
		this.wareId = wareId;
	}
}
