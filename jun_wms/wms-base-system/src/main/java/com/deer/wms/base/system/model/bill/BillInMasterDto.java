package com.deer.wms.base.system.model.bill;


/**
 * 入库单表 bill_in_master
 * 
 * @author guo
 * @date 2019-05-13
 */
public class BillInMasterDto extends BillInMaster
{
	 private String wareName;
	 private String supplierName;
	 private Integer quantity;
	 private Integer inStorageQuantity;
	 private Integer billInDetailId;
	/**  分配行ID */
	private Integer poDistributionId;
	/**  分配行号 **/
	private String distributionNum;
	/** 采购单号  **/
	private String segment;
	/** 采购订单头ID **/
	private Integer poHeaderId;
	/** 采购订单行号 **/
	private String lineNum;
	/** 采购订单行ID **/
	private Integer poLineId;
	/** 发运行号 **/
	private String shipmentNum;
	/** 发运行ID **/
	private Integer lineLocationId;
	/** 物料编码Id */
	private Integer itemId;
	private String itemCode;
	/** 物料描述 **/
	private String itemDescription;
	/** 采购单位 **/
	private String unitMeasLookupCode;
	/**  OU组织ID */
	private Integer orgId;
	/** 接收库存组织ID  */
	private Integer shipToOrganizationId;

	/**  已接收数量  **/
	private Integer quantityReceived;
	/**  可接收数量 */
	private Integer surplusReceivedQuantity;
	/** 供应商编码 */
	private String vendorCode;
	/**  供应商名称 */
	private String vendorName;
	/** 预计到货日期 */
	private String expectedArrivalDate;


	public Integer getBillInDetailId() {
		return billInDetailId;
	}

	public void setBillInDetailId(Integer billInDetailId) {
		this.billInDetailId = billInDetailId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getInStorageQuantity() {
		return inStorageQuantity;
	}

	public void setInStorageQuantity(Integer inStorageQuantity) {
		this.inStorageQuantity = inStorageQuantity;
	}

	public String getWareName() {
		return wareName;
	}

	public void setWareName(String wareName) {
		this.wareName = wareName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getPoDistributionId() {
		return poDistributionId;
	}

	public void setPoDistributionId(Integer poDistributionId) {
		this.poDistributionId = poDistributionId;
	}

	public String getDistributionNum() {
		return distributionNum;
	}

	public void setDistributionNum(String distributionNum) {
		this.distributionNum = distributionNum;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public String getLineNum() {
		return lineNum;
	}

	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	public String getShipmentNum() {
		return shipmentNum;
	}

	public void setShipmentNum(String shipmentNum) {
		this.shipmentNum = shipmentNum;
	}

	public Integer getLineLocationId() {
		return lineLocationId;
	}

	public void setLineLocationId(Integer lineLocationId) {
		this.lineLocationId = lineLocationId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getShipToOrganizationId() {
		return shipToOrganizationId;
	}

	public void setShipToOrganizationId(Integer shipToOrganizationId) {
		this.shipToOrganizationId = shipToOrganizationId;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getUnitMeasLookupCode() {
		return unitMeasLookupCode;
	}

	public void setUnitMeasLookupCode(String unitMeasLookupCode) {
		this.unitMeasLookupCode = unitMeasLookupCode;
	}

	public Integer getQuantityReceived() {
		return quantityReceived;
	}

	public void setQuantityReceived(Integer quantityReceived) {
		this.quantityReceived = quantityReceived;
	}

	public Integer getSurplusReceivedQuantity() {
		return surplusReceivedQuantity;
	}

	public void setSurplusReceivedQuantity(Integer surplusReceivedQuantity) {
		this.surplusReceivedQuantity = surplusReceivedQuantity;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getExpectedArrivalDate() {
		return expectedArrivalDate;
	}

	public void setExpectedArrivalDate(String expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
}
