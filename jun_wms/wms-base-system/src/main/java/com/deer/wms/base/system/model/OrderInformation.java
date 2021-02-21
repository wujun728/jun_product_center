package com.deer.wms.base.system.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "order_information")
public class OrderInformation {
    /**
     * EBS抓取订单信息表Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 物料Id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 分配行ID
     */
    @Column(name = "po_distribution_id")
    private Integer poDistributionId;

    /**
     * 分配行号
     */
    @Column(name = "distribution_num")
    private String distributionNum;

    /**
     * 采购单号
     */
    private String segment;

    /**
     * 采购订单头ID
     */
    @Column(name = "po_header_id")
    private Integer poHeaderId;

    /**
     * 采购订单行号
     */
    @Column(name = "line_num")
    private String lineNum;

    /**
     * 采购订单行ID
     */
    @Column(name = "po_line_id")
    private Integer poLineId;

    /**
     * 发运行号
     */
    @Column(name = "shipment_num")
    private String shipmentNum;

    /**
     * 发运行ID
     */
    @Column(name = "line_location_id")
    private Integer lineLocationId;

    /**
     * OU组织ID
     */
    @Column(name = "org_id")
    private Integer orgId;

    /**
     * 接收库存组织ID
     */
    @Column(name = "ship_to_organization_id")
    private Integer shipToOrganizationId;

    /**
     * 物料描述
     */
    @Column(name = "item_description")
    private String itemDescription;

    /**
     * 采购单位
     */
    @Column(name = "unit_meas_lookup_code")
    private String unitMeasLookupCode;

    /**
     * 采购单价
     */
    @Column(name = "unit_price")
    private Double unitPrice;

    /**
     * 发运行数量
     */
    private Integer quantity;

    /**
     * 已接收数量
     */
    @Column(name = "quantity_received")
    private Integer quantityReceived;

    /**
     * 发运行状态
     */
    @Column(name = "closed_code")
    private String closedCode;

    /**
     * 接收类型
     */
    @Column(name = "supply_type_code")
    private String supplyTypeCode;

    /**
     * 可接收数量
     */
    @Column(name = "surplus_received_quantity")
    private Integer surplusReceivedQuantity;

    /**
     * 供应商ID
     */
    @Column(name = "vendor_id")
    private Integer vendorId;

    /**
     * 供应商编码
     */
    @Column(name = "vendor_code")
    private String vendorCode;

    /**
     * 供应商名称
     */
    @Column(name = "vendor_name")
    private String vendorName;

    /**
     * 交货日期
     */
    @Column(name="due_date")
    private String dueDate;

    /**
     * 获取EBS抓取订单信息表Id
     *
     * @return id - EBS抓取订单信息表Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置EBS抓取订单信息表Id
     *
     * @param id EBS抓取订单信息表Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }



    /**
     * 获取分配行ID
     *
     * @return po_distribution_id - 分配行ID
     */
    public Integer getPoDistributionId() {
        return poDistributionId;
    }

    /**
     * 设置分配行ID
     *
     * @param poDistributionId 分配行ID
     */
    public void setPoDistributionId(Integer poDistributionId) {
        this.poDistributionId = poDistributionId;
    }

    /**
     * 获取分配行号
     *
     * @return distribution_num - 分配行号
     */
    public String getDistributionNum() {
        return distributionNum;
    }

    /**
     * 设置分配行号
     *
     * @param distributionNum 分配行号
     */
    public void setDistributionNum(String distributionNum) {
        this.distributionNum = distributionNum;
    }

    /**
     * 获取采购单号
     *
     * @return segment - 采购单号
     */
    public String getSegment() {
        return segment;
    }

    /**
     * 设置采购单号
     *
     * @param segment 采购单号
     */
    public void setSegment(String segment) {
        this.segment = segment;
    }

    /**
     * 获取采购订单头ID
     *
     * @return po_header_id - 采购订单头ID
     */
    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    /**
     * 设置采购订单头ID
     *
     * @param poHeaderId 采购订单头ID
     */
    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    /**
     * 获取采购订单行号
     *
     * @return line_num - 采购订单行号
     */
    public String getLineNum() {
        return lineNum;
    }

    /**
     * 设置采购订单行号
     *
     * @param lineNum 采购订单行号
     */
    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * 获取采购订单行ID
     *
     * @return po_line_id - 采购订单行ID
     */
    public Integer getPoLineId() {
        return poLineId;
    }

    /**
     * 设置采购订单行ID
     *
     * @param poLineId 采购订单行ID
     */
    public void setPoLineId(Integer poLineId) {
        this.poLineId = poLineId;
    }

    /**
     * 获取发运行号
     *
     * @return shipment_num - 发运行号
     */
    public String getShipmentNum() {
        return shipmentNum;
    }

    /**
     * 设置发运行号
     *
     * @param shipmentNum 发运行号
     */
    public void setShipmentNum(String shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    /**
     * 获取发运行ID
     *
     * @return line_location_id - 发运行ID
     */
    public Integer getLineLocationId() {
        return lineLocationId;
    }

    /**
     * 设置发运行ID
     *
     * @param lineLocationId 发运行ID
     */
    public void setLineLocationId(Integer lineLocationId) {
        this.lineLocationId = lineLocationId;
    }

    /**
     * 获取OU组织ID
     *
     * @return org_id - OU组织ID
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 设置OU组织ID
     *
     * @param orgId OU组织ID
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取接收库存组织ID
     *
     * @return ship_to_organization_id - 接收库存组织ID
     */
    public Integer getShipToOrganizationId() {
        return shipToOrganizationId;
    }

    /**
     * 设置接收库存组织ID
     *
     * @param shipToOrganizationId 接收库存组织ID
     */
    public void setShipToOrganizationId(Integer shipToOrganizationId) {
        this.shipToOrganizationId = shipToOrganizationId;
    }

    /**
     * 获取物料描述
     *
     * @return item_description - 物料描述
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * 设置物料描述
     *
     * @param itemDescription 物料描述
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * 获取采购单位
     *
     * @return unit_meas_lookup_code - 采购单位
     */
    public String getUnitMeasLookupCode() {
        return unitMeasLookupCode;
    }

    /**
     * 设置采购单位
     *
     * @param unitMeasLookupCode 采购单位
     */
    public void setUnitMeasLookupCode(String unitMeasLookupCode) {
        this.unitMeasLookupCode = unitMeasLookupCode;
    }

    /**
     * 获取采购单价
     *
     * @return unit_price - 采购单价
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置采购单价
     *
     * @param unitPrice 采购单价
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取发运行数量
     *
     * @return quantity - 发运行数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置发运行数量
     *
     * @param quantity 发运行数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取已接收数量
     *
     * @return quantity_received - 已接收数量
     */
    public Integer getQuantityReceived() {
        return quantityReceived;
    }

    /**
     * 设置已接收数量
     *
     * @param quantityReceived 已接收数量
     */
    public void setQuantityReceived(Integer quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    /**
     * 获取发运行状态
     *
     * @return closed_code - 发运行状态
     */
    public String getClosedCode() {
        return closedCode;
    }

    /**
     * 设置发运行状态
     *
     * @param closedCode 发运行状态
     */
    public void setClosedCode(String closedCode) {
        this.closedCode = closedCode;
    }

    /**
     * 获取接收类型
     *
     * @return supply_type_code - 接收类型
     */
    public String getSupplyTypeCode() {
        return supplyTypeCode;
    }

    /**
     * 设置接收类型
     *
     * @param supplyTypeCode 接收类型
     */
    public void setSupplyTypeCode(String supplyTypeCode) {
        this.supplyTypeCode = supplyTypeCode;
    }

    /**
     * 获取可接收数量
     *
     * @return surplus_received_quantity - 可接收数量
     */
    public Integer getSurplusReceivedQuantity() {
        return surplusReceivedQuantity;
    }

    /**
     * 设置可接收数量
     *
     * @param surplusReceivedQuantity 可接收数量
     */
    public void setSurplusReceivedQuantity(Integer surplusReceivedQuantity) {
        this.surplusReceivedQuantity = surplusReceivedQuantity;
    }

    /**
     * 获取供应商ID
     *
     * @return vendor_id - 供应商ID
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * 设置供应商ID
     *
     * @param vendorId 供应商ID
     */
    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * 获取供应商编码
     *
     * @return vendor_code - 供应商编码
     */
    public String getVendorCode() {
        return vendorCode;
    }

    /**
     * 设置供应商编码
     *
     * @param vendorCode 供应商编码
     */
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    /**
     * 获取供应商名称
     *
     * @return vendor_name - 供应商名称
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * 设置供应商名称
     *
     * @param vendorName 供应商名称
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public OrderInformation() {
    }

    public OrderInformation(Integer itemId, Integer poDistributionId, String distributionNum, String segment, Integer poHeaderId, String lineNum, Integer poLineId, String shipmentNum, Integer lineLocationId, Integer orgId, Integer shipToOrganizationId, String itemDescription, String unitMeasLookupCode, Double unitPrice, Integer quantity, Integer quantityReceived, String closedCode, String supplyTypeCode, Integer surplusReceivedQuantity, Integer vendorId, String vendorCode, String vendorName,String dueDate) {
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
        this.dueDate = dueDate;
    }
}