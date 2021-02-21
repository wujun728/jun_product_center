package com.deer.wms.base.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bill_in_check_record")
public class BillInCheckRecord {
    /**
     * 检验记录
     */
    @Id
    @Column(name = "check_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkId;

    /**
     * 组织Id
     */
    @Column(name = "organization_id")
    private Integer organizationId;

    /**
     * 接收号
     */
    @Column(name = "receipt_num")
    private String receiptNum;

    /**
     * 检验ID(为交货接口的源标志号)
     */
    @Column(name = "transaction_id")
    private Integer transactionId;

    /**
     * 采购单头Id
     */
    @Column(name = "po_header_id")
    private Integer poHeaderId;

    /**
     * 采购单行ID
     */
    @Column(name = "po_line_id")
    private Integer poLineId;

    /**
     * 采购单发运行ID
     */
    @Column(name = "po_line_location_id")
    private Integer poLineLocationId;

    /**
     * 采购单分配行ID
     */
    @Column(name = "po_distribution_id")
    private Integer poDistributionId;

    /**
     * 物料编码
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 接收数量
     */
    private Integer quantity;

    /**
     * 检验结果
     */
    private String transaction;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 批次
     */
    @Column(name="batch")
    private String batch;

    /**
     * 状态   1-未交货  2-交货
     */
    @Column(name="state")
    private Integer state;

    /**
     * 获取检验记录
     *
     * @return check_id - 检验记录
     */
    public Integer getCheckId() {
        return checkId;
    }

    /**
     * 设置检验记录
     *
     * @param checkId 检验记录
     */
    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    /**
     * 获取组织Id
     *
     * @return organization_id - 组织Id
     */
    public Integer getOrganizationId() {
        return organizationId;
    }

    /**
     * 设置组织Id
     *
     * @param organizationId 组织Id
     */
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 获取接收号
     *
     * @return receipt_num - 接收号
     */
    public String getReceiptNum() {
        return receiptNum;
    }

    /**
     * 设置接收号
     *
     * @param receiptNum 接收号
     */
    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    /**
     * 获取检验ID(为交货接口的源标志号)
     *
     * @return transaction_id - 检验ID(为交货接口的源标志号)
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * 设置检验ID(为交货接口的源标志号)
     *
     * @param transactionId 检验ID(为交货接口的源标志号)
     */
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 获取采购单头Id
     *
     * @return po_header_id - 采购单头Id
     */
    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    /**
     * 设置采购单头Id
     *
     * @param poHeaderId 采购单头Id
     */
    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    /**
     * 获取采购单行ID
     *
     * @return po_line_id - 采购单行ID
     */
    public Integer getPoLineId() {
        return poLineId;
    }

    /**
     * 设置采购单行ID
     *
     * @param poLineId 采购单行ID
     */
    public void setPoLineId(Integer poLineId) {
        this.poLineId = poLineId;
    }

    /**
     * 获取采购单发运行ID
     *
     * @return po_line_location_id - 采购单发运行ID
     */
    public Integer getPoLineLocationId() {
        return poLineLocationId;
    }

    /**
     * 设置采购单发运行ID
     *
     * @param poLineLocationId 采购单发运行ID
     */
    public void setPoLineLocationId(Integer poLineLocationId) {
        this.poLineLocationId = poLineLocationId;
    }

    /**
     * 获取采购单分配行ID
     *
     * @return po_distribution_id - 采购单分配行ID
     */
    public Integer getPoDistributionId() {
        return poDistributionId;
    }

    /**
     * 设置采购单分配行ID
     *
     * @param poDistributionId 采购单分配行ID
     */
    public void setPoDistributionId(Integer poDistributionId) {
        this.poDistributionId = poDistributionId;
    }

    /**
     * 获取物料编码
     *
     * @return item_id - 物料编码
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 设置物料编码
     *
     * @param itemId 物料编码
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取接收数量
     *
     * @return quantity - 接收数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置接收数量
     *
     * @param quantity 接收数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取检验结果
     *
     * @return transaction - 检验结果
     */
    public String getTransaction() {
        return transaction;
    }

    /**
     * 设置检验结果
     *
     * @param transaction 检验结果
     */
    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BillInCheckRecord() {
    }

    public BillInCheckRecord(Integer organizationId, String receiptNum, Integer transactionId, Integer poHeaderId, Integer poLineId, Integer poLineLocationId, Integer poDistributionId, Integer itemId, Integer quantity, String transaction, String createTime, String batch,Integer state) {
        this.organizationId = organizationId;
        this.receiptNum = receiptNum;
        this.transactionId = transactionId;
        this.poHeaderId = poHeaderId;
        this.poLineId = poLineId;
        this.poLineLocationId = poLineLocationId;
        this.poDistributionId = poDistributionId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.transaction = transaction;
        this.createTime = createTime;
        this.batch = batch;
        this.state = state;
    }
}