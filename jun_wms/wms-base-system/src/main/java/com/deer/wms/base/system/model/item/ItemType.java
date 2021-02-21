package com.deer.wms.base.system.model.item;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.deer.wms.common.core.domain.BaseEntity;

import javax.persistence.*;

/**
 * 物料分类表 item_type
 * 
 * @author deer
 * @date 2019-05-08
 */
@Table(name = "item_type")
public class ItemType extends BaseEntity
{
	
	/** ID */
	@Id
	@Column(name = "item_type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemTypeId;
	/** 编码 */
	private String itemTypeCode;
	/** 名称 */
	private String itemTypeName;
	/** 父级ID */
	private Long parentId;
	/** 排序 */
	private Integer orderNum;

	private String parentName;

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public void setItemTypeCode(String itemTypeCode)
	{
		this.itemTypeCode = itemTypeCode;
	}

	public String getItemTypeCode() 
	{
		return itemTypeCode;
	}
	public void setItemTypeName(String itemTypeName) 
	{
		this.itemTypeName = itemTypeName;
	}

	public String getItemTypeName() 
	{
		return itemTypeName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemTypeId", getItemTypeId())
            .append("itemTypeCode", getItemTypeCode())
            .append("itemTypeName", getItemTypeName())
            .append("parentId", getParentId())
            .toString();
    }
}
