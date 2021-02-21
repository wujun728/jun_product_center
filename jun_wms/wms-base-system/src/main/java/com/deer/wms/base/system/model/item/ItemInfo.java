package com.deer.wms.base.system.model.item;

import com.deer.wms.common.core.domain.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 物料表 item_info
 * 
 * @author deer
 * @date 2019-05-08
 */
@Table(name = "item_info")
public class ItemInfo extends BaseEntity
{

	
	/** ID */
	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;
	/** 编码 */
	@Column(name = "item_code")
	private String itemCode;
	/** 名称 */
	@Column(name = "item_name")
	private String itemName;
	/** 单位张 **/
	@Column(name="unit")
	private String unit;
	/** 规格 */
	@Column(name = "spec")
	private String spec;
	/** 型号 */
	@Column(name = "model")
	private String model;
	/** 厚度（毫米） */
	@Column(name = "thickness")
	private Double thickness;
	/**单框最大存储数*/
	@Column(name = "max_pack_qty")
	private Integer maxPackQty;

	@Column(name="date_warning")
	private Integer dateWarning;
	/** 类型 */
	@Column(name = "item_type_id")
	private Long itemTypeId;
	/**
	 * EBS物料Id
	 */
	@Column(name="inventory_item_id")
	private Integer inventoryItemId;
	/**
	 * 修改人一
	 */
	@Column(name="card_no_one")
	private String cardNoOne;
	/**
	 * 修改人二
	 */
	@Column(name="card_no_two")
	private String cardNoTwo;

	public Integer getMaxPackQty() {
		return maxPackQty;
	}

	public void setMaxPackQty(Integer maxPackQty) {
		this.maxPackQty = maxPackQty;
	}

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public void setItemId(Integer itemId)
	{
		this.itemId = itemId;
	}

	public Integer getItemId() 
	{
		return itemId;
	}
	public void setItemCode(String itemCode) 
	{
		this.itemCode = itemCode;
	}

	public String getItemCode() 
	{
		return itemCode;
	}
	public void setItemName(String itemName) 
	{
		this.itemName = itemName;
	}

	public String getItemName() 
	{
		return itemName;
	}
	public void setSpec(String spec) 
	{
		this.spec = spec;
	}

	public String getSpec() 
	{
		return spec;
	}
	public void setModel(String model) 
	{
		this.model = model;
	}

	public String getModel() 
	{
		return model;
	}
	public void setThickness(Double thickness) 
	{
		this.thickness = thickness;
	}

	public Double getThickness() 
	{
		return thickness;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getDateWarning() {
		return dateWarning;
	}

	public void setDateWarning(Integer dateWarning) {
		this.dateWarning = dateWarning;
	}

	public Integer getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(Integer inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getCardNoOne() {
		return cardNoOne;
	}

	public void setCardNoOne(String cardNoOne) {
		this.cardNoOne = cardNoOne;
	}

	public String getCardNoTwo() {
		return cardNoTwo;
	}

	public void setCardNoTwo(String cardNoTwo) {
		this.cardNoTwo = cardNoTwo;
	}

	public ItemInfo() {
	}

	public ItemInfo(String itemCode, String itemName) {
		this.itemCode = itemCode;
		this.itemName = itemName;
	}

	public ItemInfo(String itemCode, String itemName, String unit, Integer inventoryItemId) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.unit = unit;
		this.inventoryItemId = inventoryItemId;
	}
}
