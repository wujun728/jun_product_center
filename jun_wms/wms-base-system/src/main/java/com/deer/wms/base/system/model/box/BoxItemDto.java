package com.deer.wms.base.system.model.box;

/**
 *
 * 
 * @author cai
 * @date 2019-7-2
 */
public class BoxItemDto extends BoxItem
{

	private static final long serialVersionUID = 1L;

	/**  行(层) */
	private Integer sRow;
	/**  列 */
	private Integer sColumn;

	/**  货架名 */
	private String shelfName;

	/**
	 * 货位id(外键)
	 */
	private Integer boxCellId;

	/**  物料名称 */
	private String itemName;

	/**  已选择数量 */
	private Double pickQuantity;

	/**  容器状态 */
	private Integer boxState;

	private Integer maxPackQty;
	private String location;
	private Integer cellState;
	private String unit;
	private Integer type;
	private String supplierCode;
	private String supplierName;
	private Integer surplusDay;
	private Integer inventoryItemId;
	private String subInventoryCode;
	private String subInventoryName;
	private String slotting;
	private Double totalThickness;
	private Integer billId;

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getSlotting() {
		return slotting;
	}

	public void setSlotting(String slotting) {
		this.slotting = slotting;
	}

	public Integer getSurplusDay() {
		return surplusDay;
	}

	public void setSurplusDay(Integer surplusDay) {
		this.surplusDay = surplusDay;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCellState() {
		return cellState;
	}

	public void setCellState(Integer cellState) {
		this.cellState = cellState;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getMaxPackQty() {
		return maxPackQty;
	}

	public void setMaxPackQty(Integer maxPackQty) {
		this.maxPackQty = maxPackQty;
	}

	public Integer getBoxState() {
		return boxState;
	}

	public void setBoxState(Integer boxState) {
		this.boxState = boxState;
	}

	public Double getPickQuantity() {
		return pickQuantity;
	}

	public void setPickQuantity(Double pickQuantity) {
		this.pickQuantity = pickQuantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getBoxCellId() {
		return boxCellId;
	}

	public void setBoxCellId(Integer boxCellId) {
		this.boxCellId = boxCellId;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public Integer getsRow() {
		return sRow;
	}

	public void setsRow(Integer sRow) {
		this.sRow = sRow;
	}

	public Integer getsColumn() {
		return sColumn;
	}

	public void setsColumn(Integer sColumn) {
		this.sColumn = sColumn;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(Integer inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getSubInventoryCode() {
		return subInventoryCode;
	}

	public void setSubInventoryCode(String subInventoryCode) {
		this.subInventoryCode = subInventoryCode;
	}

	public String getSubInventoryName() {
		return subInventoryName;
	}

	public void setSubInventoryName(String subInventoryName) {
		this.subInventoryName = subInventoryName;
	}

	public Double getTotalThickness() {
		return totalThickness;
	}

	public void setTotalThickness(Double totalThickness) {
		this.totalThickness = totalThickness;
	}
}
