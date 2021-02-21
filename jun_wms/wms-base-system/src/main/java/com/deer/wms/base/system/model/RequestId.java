package com.deer.wms.base.system.model;

import javax.persistence.*;

@Table(name = "request_id")
public class RequestId {
    /**
     * 请求Id辅助表
     */
    @Id
    @Column(name = "auto_growing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autoGrowingId;

    /**
     * 备注
     */
    @Column(name="request_id")
    private Integer requestId;

    /**
     * 处理状态
     */
    @Column(name = "process_status")
    private String processStatus;

    /**
     * 最后更新时间
     */
    @Column(name = "last_update_date")
    private String lastUpdateDate;

    /**
     * 最后更新人
     */
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    /**
     * 创建时间
     */
    @Column(name = "creation_date")
    private String creationDate;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 物料Id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 批次
     */
    @Column(name = "lot_number")
    private String lotNumber;

    /**
     * 订单头Id
     */
    @Column(name = "po_header_id")
    private Integer poHeaderId;

    /**
     * 订单行Id
     */
    @Column(name = "po_line_id")
    private Integer poLineId;

    /**
     * 发运行Id
     */
    @Column(name = "po_line_location_id")
    private Integer poLineLocationId;

    /**
     * 分配行Id
     */
    @Column(name = "po_distribution_id")
    private Integer poDistributionId;

    /**
     * 接收日期
     */
    @Column(name = "receipt_date")
    private String receiptDate;

    private Integer id;

    /**
     * 交货处理日期
     */
    @Column(name = "transaction_date")
    private String transactionDate;

    /**
     * 接收单号
     */
    @Column(name = "shipment_num")
    private String shipmentNum;

    /**
     * 错误信息
     */
    @Column(name = "error_msg")
    private String errorMsg;

    /**
     * 子库
     */
    @Column(name = "sub_inventory")
    private String subInventory;

    /**
     * 子库Id
     */
    @Column(name = "locator_id")
    private Integer locatorId;

    /**
     * 库存组织
     */
    @Column(name = "organization_id")
    private Integer organizationId;

    /**
     * 事务处理Id
     */
    @Column(name = "transaction_id")
    private Integer transactionId;

    /**
     * 类型
     * 1-采购接收
     * 2-交货
     * 3-工单出库
     * 4-账户别名事务处理
     * 5-转库
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 状态
     * 1-成功 2-失败需处理 3-失败无需处理 4-EBS处理中 5-已处理
     */
    @Column(name = "state")
    private Integer state;

    @Column(name = "transaction_type_id")
    private String transactionTypeId;

    @Column(name = "wip_entity_id")
    private String wipEntityId;

    @Column(name = "operation_seqnum")
    private String operationSeqnum;

    @Column(name="transaction_uom")
    private String transactionUom;

    @Column(name="trans_sub_inventory")
    private String transSubInventory;

    @Column(name="trans_locator_id")
    private Integer transLocatorId;

    @Column(name="source_header_id")
    private Integer sourceHeaderId;

    @Column(name="source_line_id")
    private Integer sourceLineId;

    @Column(name="trans_source_name")
    private String transSourceName;

    @Column(name="trans_source_id")
    private Integer transSourceId;

    @Column(name="due_date")
    private String dueDate;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取请求Id辅助表
     *
     * @return auto_growing_id - 请求Id辅助表
     */
    public Long getAutoGrowingId() {
        return autoGrowingId;
    }

    /**
     * 设置请求Id辅助表
     *
     * @param autoGrowingId 请求Id辅助表
     */
    public void setAutoGrowingId(Long autoGrowingId) {
        this.autoGrowingId = autoGrowingId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * 获取处理状态
     *
     * @return process_status - 处理状态
     */
    public String getProcessStatus() {
        return processStatus;
    }

    /**
     * 设置处理状态
     *
     * @param processStatus 处理状态
     */
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    /**
     * 获取最后更新时间
     *
     * @return last_update_date - 最后更新时间
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * 设置最后更新时间
     *
     * @param lastUpdateDate 最后更新时间
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * 获取最后更新人
     *
     * @return last_updated_by - 最后更新人
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * 设置最后更新人
     *
     * @param lastUpdatedBy 最后更新人
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * 获取创建时间
     *
     * @return creation_date - 创建时间
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * 设置创建时间
     *
     * @param creationDate 创建时间
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取物料Id
     *
     * @return item_id - 物料Id
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 设置物料Id
     *
     * @param itemId 物料Id
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取数量
     *
     * @return quantity - 数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置数量
     *
     * @param quantity 数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取批次
     *
     * @return lot_number - 批次
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * 设置批次
     *
     * @param lotNumber 批次
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * 获取订单头Id
     *
     * @return po_header_id - 订单头Id
     */
    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    /**
     * 设置订单头Id
     *
     * @param poHeaderId 订单头Id
     */
    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    /**
     * 获取订单行Id
     *
     * @return po_line_id - 订单行Id
     */
    public Integer getPoLineId() {
        return poLineId;
    }

    /**
     * 设置订单行Id
     *
     * @param poLineId 订单行Id
     */
    public void setPoLineId(Integer poLineId) {
        this.poLineId = poLineId;
    }

    /**
     * 获取发运行Id
     *
     * @return po_line_location_id - 发运行Id
     */
    public Integer getPoLineLocationId() {
        return poLineLocationId;
    }

    /**
     * 设置发运行Id
     *
     * @param poLineLocationId 发运行Id
     */
    public void setPoLineLocationId(Integer poLineLocationId) {
        this.poLineLocationId = poLineLocationId;
    }

    /**
     * 获取分配行Id
     *
     * @return po_distribution_id - 分配行Id
     */
    public Integer getPoDistributionId() {
        return poDistributionId;
    }

    /**
     * 设置分配行Id
     *
     * @param poDistributionId 分配行Id
     */
    public void setPoDistributionId(Integer poDistributionId) {
        this.poDistributionId = poDistributionId;
    }

    /**
     * 获取接收日期
     *
     * @return receipt_date - 接收日期
     */
    public String getReceiptDate() {
        return receiptDate;
    }

    /**
     * 设置接收日期
     *
     * @param receiptDate 接收日期
     */
    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取交货处理日期
     *
     * @return transaction_date - 交货处理日期
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * 设置交货处理日期
     *
     * @param transactionDate 交货处理日期
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * 获取接收单号
     *
     * @return shipment_num - 接收单号
     */
    public String getShipmentNum() {
        return shipmentNum;
    }

    /**
     * 设置接收单号
     *
     * @param shipmentNum 接收单号
     */
    public void setShipmentNum(String shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    /**
     * 获取错误信息
     *
     * @return error_msg - 错误信息
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 设置错误信息
     *
     * @param errorMsg 错误信息
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 获取子库
     *
     * @return sub_inventory - 子库
     */
    public String getSubInventory() {
        return subInventory;
    }

    /**
     * 设置子库
     *
     * @param subInventory 子库
     */
    public void setSubInventory(String subInventory) {
        this.subInventory = subInventory;
    }

    /**
     * 获取子库Id
     *
     * @return locator_id - 子库Id
     */
    public Integer getLocatorId() {
        return locatorId;
    }

    /**
     * 设置子库Id
     *
     * @param locatorId 子库Id
     */
    public void setLocatorId(Integer locatorId) {
        this.locatorId = locatorId;
    }

    /**
     * 获取库存组织
     *
     * @return organization_id - 库存组织
     */
    public Integer getOrganizationId() {
        return organizationId;
    }

    /**
     * 设置库存组织
     *
     * @param organizationId 库存组织
     */
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 获取事务处理Id
     *
     * @return transaction_id - 事务处理Id
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * 设置事务处理Id
     *
     * @param transactionId 事务处理Id
     */
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(String transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getWipEntityId() {
        return wipEntityId;
    }

    public void setWipEntityId(String wipEntityId) {
        this.wipEntityId = wipEntityId;
    }

    public String getOperationSeqnum() {
        return operationSeqnum;
    }

    public void setOperationSeqnum(String operationSeqnum) {
        this.operationSeqnum = operationSeqnum;
    }

    public String getTransactionUom() {
        return transactionUom;
    }

    public void setTransactionUom(String transactionUom) {
        this.transactionUom = transactionUom;
    }

    public String getTransSubInventory() {
        return transSubInventory;
    }

    public void setTransSubInventory(String transSubInventory) {
        this.transSubInventory = transSubInventory;
    }

    public Integer getTransLocatorId() {
        return transLocatorId;
    }

    public void setTransLocatorId(Integer transLocatorId) {
        this.transLocatorId = transLocatorId;
    }

    public Integer getSourceHeaderId() {
        return sourceHeaderId;
    }

    public void setSourceHeaderId(Integer sourceHeaderId) {
        this.sourceHeaderId = sourceHeaderId;
    }

    public Integer getSourceLineId() {
        return sourceLineId;
    }

    public void setSourceLineId(Integer sourceLineId) {
        this.sourceLineId = sourceLineId;
    }

    public String getTransSourceName() {
        return transSourceName;
    }

    public void setTransSourceName(String transSourceName) {
        this.transSourceName = transSourceName;
    }

    public Integer getTransSourceId() {
        return transSourceId;
    }

    public void setTransSourceId(Integer transSourceId) {
        this.transSourceId = transSourceId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public RequestId() {
    }

    /**
     * 采购接收请求发送前
     */
    public RequestId(Integer requestId, Integer itemId, Integer quantity, String lotNumber, Integer poHeaderId, Integer poLineId, Integer poLineLocationId, Integer poDistributionId, String receiptDate, Integer organizationId, Integer type,Integer state,String errorMsg,String processStatus) {
        this.requestId = requestId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.poHeaderId = poHeaderId;
        this.poLineId = poLineId;
        this.poLineLocationId = poLineLocationId;
        this.poDistributionId = poDistributionId;
        this.receiptDate = receiptDate;
        this.organizationId = organizationId;
        this.type = type;
        this.state = state;
        this.errorMsg = errorMsg;
        this.processStatus = processStatus;
    }

    /**
     * 采购接收请求发送后
     */
    public RequestId(Integer requestId, String processStatus, String lastUpdateDate, String lastUpdatedBy, String creationDate, String createdBy, Integer itemId, Integer quantity, String lotNumber, Integer poHeaderId, Integer poLineId, Integer poLineLocationId, Integer poDistributionId, String receiptDate, Integer id, Integer organizationId, Integer type,String receiptNum,String dueDate) {
        this.requestId = requestId;
        this.processStatus = processStatus;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.poHeaderId = poHeaderId;
        this.poLineId = poLineId;
        this.poLineLocationId = poLineLocationId;
        this.poDistributionId = poDistributionId;
        this.receiptDate = receiptDate;
        this.id = id;
        this.organizationId = organizationId;
        this.type = type;
        this.shipmentNum = receiptNum;
        this.dueDate = dueDate;
    }

    /**
     * WMS交货前
     */
    public RequestId(Integer requestId, Integer quantity, String lotNumber, String transactionDate, String shipmentNum, String subInventory, Integer locatorId, Integer organizationId, Integer transactionId, Integer type,Integer state,String errorMsg,String processStatus) {
        this.requestId = requestId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.transactionDate = transactionDate;
        this.shipmentNum = shipmentNum;
        this.subInventory = subInventory;
        this.locatorId = locatorId;
        this.organizationId = organizationId;
        this.transactionId = transactionId;
        this.type = type;
        this.state = state;
        this.errorMsg = errorMsg;
        this.processStatus = processStatus;
    }

    /**
     * WMS交货后
     */
    public RequestId(Integer requestId, String processStatus, String lastUpdateDate, String lastUpdatedBy, String creationDate, String createdBy, Integer quantity, String lotNumber, Integer id, String transactionDate, String shipmentNum, String errorMsg, String subInventory, Integer locatorId, Integer organizationId, Integer transactionId, Integer type) {
        this.requestId = requestId;
        this.processStatus = processStatus;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.id = id;
        this.transactionDate = transactionDate;
        this.shipmentNum = shipmentNum;
        this.errorMsg = errorMsg;
        this.subInventory = subInventory;
        this.locatorId = locatorId;
        this.organizationId = organizationId;
        this.transactionId = transactionId;
        this.type = type;
    }

    /**
     * 调用工单发料接口前
     */
    public RequestId(Integer requestId, Integer itemId, Integer quantity, String lotNumber, String subInventory, Integer locatorId, Integer organizationId, String transactionTypeId, String wipEntityId, String operationSeqnum,String transactionDate,String transUom,Integer type, Integer state,String errorMsg,String processStatus) {
        this.requestId = requestId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.subInventory = subInventory;
        this.locatorId = locatorId;
        this.organizationId = organizationId;
        this.transactionTypeId = transactionTypeId;
        this.wipEntityId = wipEntityId;
        this.operationSeqnum = operationSeqnum;
        this.transactionDate = transactionDate;
        this.transactionUom = transUom;
        this.type = type;
        this.state = state;
        this.errorMsg = errorMsg;
        this.processStatus = processStatus;
    }

    /**
     * 调用工单发料接口后
     */
    public RequestId(Integer requestId, String processStatus, String lastUpdateDate, String lastUpdatedBy, String creationDate, String createdBy, Integer itemId, Integer quantity, String lotNumber, Integer id, String transactionDate, String errorMsg, String subInventory, Integer locatorId, Integer organizationId, Integer type, String transactionTypeId, String wipEntityId, String operationSeqnum, String transactionUom) {
        this.requestId = requestId;
        this.processStatus = processStatus;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.id = id;
        this.transactionDate = transactionDate;
        this.errorMsg = errorMsg;
        this.subInventory = subInventory;
        this.locatorId = locatorId;
        this.organizationId = organizationId;
        this.type = type;
        this.transactionTypeId = transactionTypeId;
        this.wipEntityId = wipEntityId;
        this.operationSeqnum = operationSeqnum;
        this.transactionUom = transactionUom;
    }

    /**
     * 调用账户别名出库接口前
     */
    public RequestId(Integer requestId, String processStatus, Integer itemId, Integer quantity, String lotNumber, String transactionDate, String errorMsg, String subInventory, Integer locatorId, Integer organizationId, Integer type, Integer state, String transactionTypeId, String transactionUom, Integer sourceHeaderId, Integer sourceLineId, String transSourceName, Integer transSourceId) {
        this.requestId = requestId;
        this.processStatus = processStatus;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.transactionDate = transactionDate;
        this.errorMsg = errorMsg;
        this.subInventory = subInventory;
        this.locatorId = locatorId;
        this.organizationId = organizationId;
        this.type = type;
        this.state = state;
        this.transactionTypeId = transactionTypeId;
        this.transactionUom = transactionUom;
        this.sourceHeaderId = sourceHeaderId;
        this.sourceLineId = sourceLineId;
        this.transSourceName = transSourceName;
        this.transSourceId = transSourceId;
    }

    /**
     * 调用账户别名出库接口后
     */
    public RequestId(Integer requestId, String processStatus, String lastUpdateDate, String lastUpdatedBy, String creationDate, String createdBy, Integer itemId, Integer quantity, String lotNumber, Integer id, String transactionDate, String errorMsg, String subInventory, Integer locatorId, Integer organizationId, Integer type, String transactionTypeId, String transactionUom, Integer sourceHeaderId, Integer sourceLineId, String transSourceName, Integer transSourceId) {
        this.requestId = requestId;
        this.processStatus = processStatus;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.id = id;
        this.transactionDate = transactionDate;
        this.errorMsg = errorMsg;
        this.subInventory = subInventory;
        this.locatorId = locatorId;
        this.organizationId = organizationId;
        this.type = type;
        this.transactionTypeId = transactionTypeId;
        this.transactionUom = transactionUom;
        this.sourceHeaderId = sourceHeaderId;
        this.sourceLineId = sourceLineId;
        this.transSourceName = transSourceName;
        this.transSourceId = transSourceId;
    }

    /**
     *调用子库转移接口前
     */
    public RequestId(Integer requestId, Integer itemId, Integer quantity, String lotNumber, String transactionDate, String errorMsg, String subInventory, Integer locatorId, Integer organizationId, Integer type, Integer state, String transactionTypeId, String transactionUom, String transSubInventory, Integer transLocatorId, Integer sourceHeaderId, Integer sourceLineId,String processStatus) {
        this.requestId = requestId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.transactionDate = transactionDate;
        this.errorMsg = errorMsg;
        this.subInventory = subInventory;
        this.locatorId = locatorId;
        this.organizationId = organizationId;
        this.type = type;
        this.state = state;
        this.transactionTypeId = transactionTypeId;
        this.transactionUom = transactionUom;
        this.transSubInventory = transSubInventory;
        this.transLocatorId = transLocatorId;
        this.sourceHeaderId = sourceHeaderId;
        this.sourceLineId = sourceLineId;
        this.processStatus = processStatus;
    }

    /**
     * 调用子库转移接口后
     * */
    public RequestId(Integer requestId, String processStatus, String lastUpdateDate, String lastUpdatedBy, String creationDate, String createdBy, Integer itemId, Integer quantity, String lotNumber, Integer id, String transactionDate, String errorMsg, String subInventory, Integer locatorId, Integer organizationId, Integer type, String transactionTypeId, String transactionUom, String transSubInventory, Integer transLocatorId, Integer sourceHeaderId, Integer sourceLineId) {
        this.requestId = requestId;
        this.processStatus = processStatus;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.itemId = itemId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.id = id;
        this.transactionDate = transactionDate;
        this.errorMsg = errorMsg;
        this.subInventory = subInventory;
        this.locatorId = locatorId;
        this.organizationId = organizationId;
        this.type = type;
        this.transactionTypeId = transactionTypeId;
        this.transactionUom = transactionUom;
        this.transSubInventory = transSubInventory;
        this.transLocatorId = transLocatorId;
        this.sourceHeaderId = sourceHeaderId;
        this.sourceLineId = sourceLineId;
    }

}