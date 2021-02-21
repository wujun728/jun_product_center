package com.deer.wms.base.system.model.box;


import com.deer.wms.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 组盘表 box_item
 * 
 * @author guo
 * @date 2019-06-03
 */
public class BoxItem extends BaseEntity
{

	/**  */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** 托盘/料箱编码 */
	private String boxCode;
	/** 物料编码 */
	private String itemCode;
	/** 批次 */
	private String batch;
	/**  */
	private Integer quantity;
	/**
	 * 是否是Mes工单备料
	 * 0-否，1-是
	 * */
	@Column(name="work_order_stock_state")
	private Integer workOrderStockState;
	/** 入库单行ID */
	private Integer billInDetailId;

	//详细请参考TaskTypeConstant
	@Column(name="sub_inventory_id")
	private Integer subInventoryId;
	@Column(name="forecast_stock_quantity")
	private Integer forecastStockQuantity;

	private String pd;
	private String exp;
	private String inTime;

	public String getPd() {
		return pd;
	}

	public void setPd(String pd) {
		this.pd = pd;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public Integer getForecastStockQuantity() {
		return forecastStockQuantity;
	}

	public void setForecastStockQuantity(Integer forecastStockQuantity) {
		this.forecastStockQuantity = forecastStockQuantity;
	}

	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setBoxNo(String boxCode)
	{
		this.boxCode = boxCode;
	}

	public String getBoxCode()
	{
		return boxCode;
	}
	public void setItemCode(String itemCode) 
	{
		this.itemCode = itemCode;
	}

	public String getItemCode() 
	{
		return itemCode;
	}
	public void setBatch(String batch) 
	{
		this.batch = batch;
	}

	public String getBatch() 
	{
		return batch;
	}
	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setBillInDetailId(Integer billInDetailId)
	{
		this.billInDetailId = billInDetailId;
	}

	public Integer getBillInDetailId()
	{
		return billInDetailId;
	}

	public Integer getSubInventoryId() {
		return subInventoryId;
	}

	public void setSubInventoryId(Integer subInventoryId) {
		this.subInventoryId = subInventoryId;
	}

	public Integer getWorkOrderStockState() {
		return workOrderStockState;
	}

	public void setWorkOrderStockState(Integer workOrderStockState) {
		this.workOrderStockState = workOrderStockState;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("boxNo", getBoxCode())
            .append("itemCode", getItemCode())
            .append("batch", getBatch())
            .append("quantity", getQuantity())
            .append("workOrderStockState", getWorkOrderStockState())
            .append("billInDetailId", getBillInDetailId())
            .append("subInventoryId", getSubInventoryId())
			.append("pd",getPd())
			.append("exp",getExp())
			.append("inTime",getInTime())
            .toString();
    }

	public BoxItem() {
	}

	public BoxItem(String boxCode, String itemCode, String batch, Integer quantity, Integer billInDetailId, Integer subInventoryId) {
		this.boxCode = boxCode;
		this.itemCode = itemCode;
		this.batch = batch;
		this.quantity = quantity;
		this.billInDetailId = billInDetailId;
		this.subInventoryId = subInventoryId;
	}
}
