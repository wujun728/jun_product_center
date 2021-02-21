package com.deer.wms.base.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bill_in_record")
public class BillInRecord {
    /**
     * 入库记录表
     */
    @Id
    @Column(name = "bill_in_record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billInRecordId;

    /**
     * 出库单id
     */
    @Column(name = "bill_in_detail_id")
    private Integer billInDetailId;

    /**
     * 入库数量
     */
    @Column(name = "accept_quantity")
    private Integer acceptQuantity;

    @Column(name = "accept_time")
    private String acceptTime;

    @Column(name = "box_code")
    private String boxCode;

    /** 扫描二维码得到的信息 **/
    @Column(name="bar_code")
    private String barCode;

    /** 生产日期 **/
    @Column(name="pd")
    private String pd;
    /** 失效日期 */
    @Column(name="exp")
    private String exp;
    /** 批次 */
    @Column(name="batch")
    private String batch;
    /**
     * 状态
     * 1-未回传EBS
     * 2-已回传EBS
     */
    @Column(name="state")
    private Integer state;

    /**
     * 接收单号
     */
    @Column(name="receipt_num")
    private String receiptNum;

    /**
     * 获取入库记录表
     *
     * @return bill_in_record_id - 入库记录表
     */
    public Integer getBillInRecordId() {
        return billInRecordId;
    }

    /**
     * 设置入库记录表
     *
     * @param billInRecordId 入库记录表
     */
    public void setBillInRecordId(Integer billInRecordId) {
        this.billInRecordId = billInRecordId;
    }


    /**
     * 获取入库数量
     *
     * @return accept_quantity - 入库数量
     */
    public Integer getAcceptQuantity() {
        return acceptQuantity;
    }

    /**
     * 设置入库数量
     *
     * @param acceptQuantity 入库数量
     */
    public void setAcceptQuantity(Integer acceptQuantity) {
        this.acceptQuantity = acceptQuantity;
    }

    /**
     * @return accept_time
     */
    public String getAcceptTime() {
        return acceptTime;
    }

    /**
     * @param acceptTime
     */
    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * @return box_code
     */
    public String getBoxCode() {
        return boxCode;
    }

    /**
     * @param boxCode
     */
    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public Integer getBillInDetailId() {
        return billInDetailId;
    }

    public void setBillInDetailId(Integer billInDetailId) {
        this.billInDetailId = billInDetailId;
    }

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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public BillInRecord() {
    }

    public BillInRecord(Integer billInDetailId, Integer acceptQuantity, String acceptTime, String boxCode, String barCode, String pd, String exp, String batch, Integer state) {
        this.billInDetailId = billInDetailId;
        this.acceptQuantity = acceptQuantity;
        this.acceptTime = acceptTime;
        this.boxCode = boxCode;
        this.barCode = barCode;
        this.pd = pd;
        this.exp = exp;
        this.batch = batch;
        this.state = state;
    }

    public BillInRecord(Integer billInDetailId, Integer acceptQuantity, String acceptTime, String boxCode, String barCode, String pd, String exp, String batch, Integer state, String receiptNum) {
        this.billInDetailId = billInDetailId;
        this.acceptQuantity = acceptQuantity;
        this.acceptTime = acceptTime;
        this.boxCode = boxCode;
        this.barCode = barCode;
        this.pd = pd;
        this.exp = exp;
        this.batch = batch;
        this.state = state;
        this.receiptNum = receiptNum;
    }
}