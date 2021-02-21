package com.deer.wms.base.system.model.task;

import javax.persistence.*;

@Table(name = "pick_task")
public class PickTask {
    @Id
    @Column(name = "pick_task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pickTaskId;

    @Column(name = "box_code")
    private String boxCode;

    @Column(name = "pick_quantity")
    private Integer pickQuantity;

    @Column(name = "bill_out_detail_id")
    private Integer billOutDetailId;
    //1-待下发，2-已下发任务,3-点数任务已完成，4-库存已扣减，5-取消
    @Column(name="pick_state")
    private Integer pickState;

    @Column(name="batch")
    private String batch;

    @Column(name="sub_inventory_id")
    private Integer subInventoryId;

    @Column(name="create_time")
    private String createTime;

    @Column(name="out_time")
    private String outTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getPickState() {
        return pickState;
    }

    public void setPickState(Integer pickState) {
        this.pickState = pickState;
    }

    public Integer getBillOutDetailId() {
        return billOutDetailId;
    }

    public void setBillOutDetailId(Integer billOutDetailId) {
        this.billOutDetailId = billOutDetailId;
    }



    /**
     * @return pick_task_id
     */
    public Integer getPickTaskId() {
        return pickTaskId;
    }

    /**
     * @param pickTaskId
     */
    public void setPickTaskId(Integer pickTaskId) {
        this.pickTaskId = pickTaskId;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    /**
     * @return pick_quantity
     */
    public Integer getPickQuantity() {
        return pickQuantity;
    }

    /**
     * @param pickQuantity
     */
    public void setPickQuantity(Integer pickQuantity) {
        this.pickQuantity = pickQuantity;
    }

    public Integer getSubInventoryId() {
        return subInventoryId;
    }

    public void setSubInventoryId(Integer subInventoryId) {
        this.subInventoryId = subInventoryId;
    }

    public PickTask() {
    }

    public PickTask(String boxCode, Integer pickQuantity, Integer billOutDetailId, Integer pickState, String batch,Integer subInventoryId,String createTime) {
        this.boxCode = boxCode;
        this.pickQuantity = pickQuantity;
        this.billOutDetailId = billOutDetailId;
        this.pickState = pickState;
        this.batch = batch;
        this.subInventoryId = subInventoryId;
        this.createTime = createTime;
    }
}