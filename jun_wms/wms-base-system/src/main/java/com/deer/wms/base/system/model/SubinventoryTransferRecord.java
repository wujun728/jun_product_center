package com.deer.wms.base.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "subinventory_transfer_record")
public class SubinventoryTransferRecord {
    /**
     * 子库存转移记录
     */
    @Id
    @Column(name = "transfer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transferId;

    /**
     * 箱号
     */
    @Column(name = "box_code")
    private String boxCode;

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
     * 数量
     */
    private Integer quantity;

    /**
     * 转移时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 转移人卡号
     */
    @Column(name = "transfer_card_no")
    private String transferCardNo;

    /**
     * 从子库存
     */
    @Column(name = "for_subinventory")
    private Integer forSubinventory;

    /**
     * 转移到子库存
     */
    @Column(name = "to_subinventory")
    private Integer toSubinventory;

    /**
     * 转移原因
     */
    @Column(name="transfer_memo")
    private String transferMemo;

    /**
     * 获取子库存转移记录
     *
     * @return transfer_id - 子库存转移记录
     */
    public Integer getTransferId() {
        return transferId;
    }

    /**
     * 设置子库存转移记录
     *
     * @param transferId 子库存转移记录
     */
    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    /**
     * 获取箱号
     *
     * @return box_code - 箱号
     */
    public String getBoxCode() {
        return boxCode;
    }

    /**
     * 设置箱号
     *
     * @param boxCode 箱号
     */
    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
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
     * 获取转移时间
     *
     * @return create_time - 转移时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置转移时间
     *
     * @param createTime 转移时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取转移人卡号
     *
     * @return transfer_card_no - 转移人卡号
     */
    public String getTransferCardNo() {
        return transferCardNo;
    }

    /**
     * 设置转移人卡号
     *
     * @param transferCardNo 转移人卡号
     */
    public void setTransferCardNo(String transferCardNo) {
        this.transferCardNo = transferCardNo;
    }


    public Integer getForSubinventory() {
        return forSubinventory;
    }

    public void setForSubinventory(Integer forSubinventory) {
        this.forSubinventory = forSubinventory;
    }

    public Integer getToSubinventory() {
        return toSubinventory;
    }

    public void setToSubinventory(Integer toSubinventory) {
        this.toSubinventory = toSubinventory;
    }

    public String getTransferMemo() {
        return transferMemo;
    }

    public void setTransferMemo(String transferMemo) {
        this.transferMemo = transferMemo;
    }

    public SubinventoryTransferRecord() {
    }

    public SubinventoryTransferRecord(String boxCode, String itemCode, String batch, Integer quantity, String createTime, String transferCardNo, Integer forSubinventory, Integer toSubinventory,String transferMemo) {
        this.boxCode = boxCode;
        this.itemCode = itemCode;
        this.batch = batch;
        this.quantity = quantity;
        this.createTime = createTime;
        this.transferCardNo = transferCardNo;
        this.forSubinventory = forSubinventory;
        this.toSubinventory = toSubinventory;
        this.transferMemo = transferMemo;
    }
}