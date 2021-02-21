package com.deer.wms.base.system.model;

import javax.persistence.*;

@Table(name = "process_record")
public class ProcessRecord {
    /**
     * 处理记录
     */
    @Id
    @Column(name = "process_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer processId;

    /**
     * 物料编码
     */
    @Column(name = "item_code")
    private String itemCode;

    /**
     * 批次
     */
    private String batch;

    /**
     * 失效日期
     */
    private String exp;

    /**
     * 子库
     */
    @Column(name = "sub_inventory_id")
    private Integer subInventoryId;

    /**
     * 流程编号
     */
    @Column(name = "flow_code")
    private String flowCode;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 获取处理记录
     *
     * @return process_id - 处理记录
     */
    public Integer getProcessId() {
        return processId;
    }

    /**
     * 设置处理记录
     *
     * @param processId 处理记录
     */
    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    /**
     * 获取物料编码
     *
     * @return item_code - 物料编码
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置物料编码
     *
     * @param itemCode 物料编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 获取批次
     *
     * @return batch - 批次
     */
    public String getBatch() {
        return batch;
    }

    /**
     * 设置批次
     *
     * @param batch 批次
     */
    public void setBatch(String batch) {
        this.batch = batch;
    }

    /**
     * 获取失效日期
     *
     * @return exp - 失效日期
     */
    public String getExp() {
        return exp;
    }

    /**
     * 设置失效日期
     *
     * @param exp 失效日期
     */
    public void setExp(String exp) {
        this.exp = exp;
    }

    public Integer getSubInventoryId() {
        return subInventoryId;
    }

    public void setSubInventoryId(Integer subInventoryId) {
        this.subInventoryId = subInventoryId;
    }

    /**
     * 获取流程编号
     *
     * @return flow_code - 流程编号
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * 设置流程编号
     *
     * @param flowCode 流程编号
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(Integer type) {
        this.type = type;
    }
}