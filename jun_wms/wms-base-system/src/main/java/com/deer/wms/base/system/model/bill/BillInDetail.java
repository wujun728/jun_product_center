package com.deer.wms.base.system.model.bill;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * 入库单详情表 bill_in_detail
 * 
 * @author guo
 * @date 2019-05-13
 */
@Table(name = "bill_in_detail")
public class BillInDetail
{

	
	/** ID */
	@Id
	@Column(name = "bill_in_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billInDetailId;
	/** 入库单ID */
	@Column(name = "bill_id")
	private Integer billId;
	/** 物料编码 */
	@Column(name = "item_id")
	private Integer itemId;
	/**  分配行ID */
	@Column(name="po_distribution_id")
	private Integer poDistributionId;
	/**  分配行号 **/
	@Column(name="distribution_num")
	private String distributionNum;
	/** 采购单号  **/
	@Column(name="segment")
	private String segment;
	/** 采购订单头ID **/
	@Column(name="po_header_id")
	private Integer poHeaderId;
	/** 采购订单行号 **/
	@Column(name="line_num")
	private String lineNum;
	/** 采购订单行ID **/
	@Column(name="po_line_id")
	private Integer poLineId;
	/** 发运行号 **/
	@Column(name="shipment_num")
	private String shipmentNum;
	/** 发运行ID **/
	@Column(name="line_location_id")
	private Integer lineLocationId;
	/**  OU组织ID */
	@Column(name="org_id")
	private Integer orgId;
	/** 接收库存组织ID  */
	@Column(name="ship_to_organization_id")
	private Integer shipToOrganizationId;
	/** 物料描述 **/
	@Column(name="item_description")
	private String itemDescription;
	/** 采购单位 **/
	@Column(name="unit_meas_lookup_code")
	private String unitMeasLookupCode;
	/**  采购单价 */
	@Column(name="unit_price")
	private Double unitPrice;
	/** 发运行数量 **/
	@Column(name="quantity")
	private Integer quantity;
	/**  已接收数量  **/
	@Column(name="quantity_received")
	private Integer quantityReceived;
	/**  发运行状态 **/
	@Column(name="closed_code")
	private String closedCode;
	/**  接收类型 */
	@Column(name="supply_type_code")
	private String supplyTypeCode;
	/**  可接收数量 */
	@Column(name="surplus_received_quantity")
	private Integer surplusReceivedQuantity;
	/**  供应商Id **/
	@Column(name="vendor_id")
	private Integer vendorId;
	/** 供应商编码 */
	@Column(name="vendor_code")
	private String vendorCode;
	/**  供应商名称 */
	@Column(name="vendor_name")
	private String vendorName;

	/**
	 * 状态
	 * 1-暂未入库
	 * 2-待检
	 * 3-已检
	 *
	 * */
	@Column(name="bill_in_state")
	private Integer billInState;

	@Column(name="expected_arrival_date")
	private String expectedArrivalDate;

	public Integer getBillInDetailId() {
		return billInDetailId;
	}

	public void setBillInDetailId(Integer billInDetailId) {
		this.billInDetailId = billInDetailId;
	}

	public void setBillId(Integer billId)
	{
		this.billId = billId;
	}

	public Integer getBillId() 
	{
		return billId;
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantityReceived() {
		return quantityReceived;
	}

	public void setQuantityReceived(Integer quantityReceived) {
		this.quantityReceived = quantityReceived;
	}

	public String getClosedCode() {
		return closedCode;
	}

	public void setClosedCode(String closedCode) {
		this.closedCode = closedCode;
	}

	public String getSupplyTypeCode() {
		return supplyTypeCode;
	}

	public void setSupplyTypeCode(String supplyTypeCode) {
		this.supplyTypeCode = supplyTypeCode;
	}

	public Integer getSurplusReceivedQuantity() {
		return surplusReceivedQuantity;
	}

	public void setSurplusReceivedQuantity(Integer surplusReceivedQuantity) {
		this.surplusReceivedQuantity = surplusReceivedQuantity;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
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

	public Integer getBillInState() {
		return billInState;
	}

	public void setBillInState(Integer billInState) {
		this.billInState = billInState;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("billInDetailId", getBillInDetailId())
            .append("billId", getBillId())

            .toString();
    }

	public BillInDetail() {
	}

	public BillInDetail(Integer billId, Integer itemId, Integer poDistributionId, String distributionNum, String segment, Integer poHeaderId, String lineNum, Integer poLineId, String shipmentNum, Integer lineLocationId, Integer orgId, Integer shipToOrganizationId, String itemDescription, String unitMeasLookupCode, Double unitPrice, Integer quantity, Integer quantityReceived, String closedCode, String supplyTypeCode, Integer surplusReceivedQuantity, Integer vendorId, String vendorCode, String vendorName, Integer billInState,String expectedArrivalDate) {
		this.billId = billId;
		this.itemId = itemId;
		this.poDistributionId = poDistributionId;
		this.distributionNum = distributionNum;
		this.segment = segment;
		this.poHeaderId = poHeaderId;
		this.lineNum = lineNum;
		this.poLineId = poLineId;
		this.shipmentNum = shipmentNum;
		this.lineLocationId = lineLocationId;
		this.orgId = orgId;
		this.shipToOrganizationId = shipToOrganizationId;
		this.itemDescription = itemDescription;
		this.unitMeasLookupCode = unitMeasLookupCode;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.quantityReceived = quantityReceived;
		this.closedCode = closedCode;
		this.supplyTypeCode = supplyTypeCode;
		this.surplusReceivedQuantity = surplusReceivedQuantity;
		this.vendorId = vendorId;
		this.vendorCode = vendorCode;
		this.vendorName = vendorName;
		this.billInState = billInState;
		this.expectedArrivalDate = expectedArrivalDate;
	}
}
