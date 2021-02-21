package com.deer.wms.base.system.model;

import com.deer.wms.common.annotation.Excel;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sluggish_overdue")
public class SluggishOverdue {
    /**
     * 呆滞过期报表
     */
    @Id
    @Column(name = "sluggish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sluggishId;

    /**
     * 物料编码
     */
    @Excel(name="物料编码",type=Excel.Type.EXPORT,column = 0)
    @Column(name = "item_code")
    private String itemCode;

    /**
     * 批次
     */
    @Excel(name="批次",type=Excel.Type.EXPORT,column = 3)
    private String batch;

    /**
     * 上次申报数量
     */
    @Column(name = "last_quantity")
    private Integer lastQuantity;

    /**
     * 当前呆滞数量
     */
    @Excel(name="库存数量",type=Excel.Type.EXPORT,column = 6)
    private Integer quantity;

    /**
     * 生产日期
     */
    @Excel(name="生产日期",type=Excel.Type.EXPORT,column = 4)
    @Column(name = "production_date")
    private String productionDate;

    /**
     * 失效日期
     */
    @Excel(name="失效日期",type=Excel.Type.EXPORT,column = 5)
    private String exp;

    /**
     * 最后入库日期
     */
    @Excel(name="最后入库日期",type=Excel.Type.EXPORT,column = 9)
    @Column(name = "last_time")
    private String lastTime;

    /**
     * 当前时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 最后出库日期
     */
    @Excel(name="最后发放日期",type=Excel.Type.EXPORT,column = 10)
    @Column(name = "last_out_time")
    private String lastOutTime;

    /**
     * 呆滞类型
     */
    @Excel(name="呆滞类型",type=Excel.Type.EXPORT,column = 11,readConverterExp = "1=入库4个月未使用,2=有效期剩余180天")
    @Column(name = "sluggish_type")
    private Integer sluggishType;

    /**
     * 第一次申报时间
     */
    @Excel(name="第一次申报时间",type=Excel.Type.EXPORT,column = 12)
    @Column(name = "first_declare_time")
    private String firstDeclareTime;

    /**
     * 第一次申报数量
     */
    @Excel(name="第一次申报数量",type=Excel.Type.EXPORT,column = 13)
    @Column(name = "first_declare_quantity")
    private Integer firstDeclareQuantity;



    /**
     * 获取呆滞过期报表
     *
     * @return sluggish_id - 呆滞过期报表
     */
    public Integer getSluggishId() {
        return sluggishId;
    }

    /**
     * 设置呆滞过期报表
     *
     * @param sluggishId 呆滞过期报表
     */
    public void setSluggishId(Integer sluggishId) {
        this.sluggishId = sluggishId;
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
     * 获取上次申报数量
     *
     * @return last_quantity - 上次申报数量
     */
    public Integer getLastQuantity() {
        return lastQuantity;
    }

    /**
     * 设置上次申报数量
     *
     * @param lastQuantity 上次申报数量
     */
    public void setLastQuantity(Integer lastQuantity) {
        this.lastQuantity = lastQuantity;
    }

    /**
     * 获取当前呆滞数量
     *
     * @return quantity - 当前呆滞数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置当前呆滞数量
     *
     * @param quantity 当前呆滞数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取生产日期
     *
     * @return production_date - 生产日期
     */
    public String getProductionDate() {
        return productionDate;
    }

    /**
     * 设置生产日期
     *
     * @param productionDate 生产日期
     */
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
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

    /**
     * 获取上次申报日期
     *
     * @return last_time - 最后入库日期
     */
    public String getLastTime() {
        return lastTime;
    }

    /**
     * 设置最后入库日期
     *
     * @param lastTime 最后入库日期
     */
    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取当前时间
     *
     * @return create_time - 当前时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置当前时间
     *
     * @param createTime 当前时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastOutTime() {
        return lastOutTime;
    }

    public void setLastOutTime(String lastOutTime) {
        this.lastOutTime = lastOutTime;
    }

    public Integer getSluggishType() {
        return sluggishType;
    }

    public void setSluggishType(Integer sluggishType) {
        this.sluggishType = sluggishType;
    }

    public String getFirstDeclareTime() {
        return firstDeclareTime;
    }

    public void setFirstDeclareTime(String firstDeclareTime) {
        this.firstDeclareTime = firstDeclareTime;
    }

    public Integer getFirstDeclareQuantity() {
        return firstDeclareQuantity;
    }

    public void setFirstDeclareQuantity(Integer firstDeclareQuantity) {
        this.firstDeclareQuantity = firstDeclareQuantity;
    }

    public SluggishOverdue() {
    }

    public SluggishOverdue(String itemCode, String batch, Integer lastQuantity, Integer quantity, String productionDate, String exp, String lastTime, String createTime, String lastOutTime, Integer sluggishType, String firstDeclareTime, Integer firstDeclareQuantity) {
        this.itemCode = itemCode;
        this.batch = batch;
        this.lastQuantity = lastQuantity;
        this.quantity = quantity;
        this.productionDate = productionDate;
        this.exp = exp;
        this.lastTime = lastTime;
        this.createTime = createTime;
        this.lastOutTime = lastOutTime;
        this.sluggishType = sluggishType;
        this.firstDeclareTime = firstDeclareTime;
        this.firstDeclareQuantity = firstDeclareQuantity;
    }
}