package com.deer.wms.base.system.model.box;

import javax.persistence.*;

@Table(name = "box_info")
public class BoxInfo {
    /**
     * ID
     */
    @Id
    @Column(name = "box_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boxId;

    /**
     * 编码
     */
    @Column(name = "box_code")
    private String boxCode;

    /**
     * 类型：1-料箱  2-托盘
     */
    @Column(name = "box_type")
    private Integer boxType;

    /**
     * 备注
     */
    @Column(name = "box_memo")
    private String boxMemo;

    /**
     * 所属单位
     */
    @Column(name = "box_unit")
    private String boxUnit;


    /**
     * 货位id(外键)
     */
    @Column(name = "box_cell_id")
    private Integer boxCellId;


    /**
     * 状态 0不在货位 1-货位上 2-任务中
     */
    @Column(name = "box_state")
    private Integer boxState;

    /**
     * 状态
     * 0-无货
     * 1-有货
     * 2-
     */
    @Column(name = "has_goods")
    private Integer hasGoods;

    public Integer getHasGoods() {
        return hasGoods;
    }

    public void setHasGoods(Integer hasGoods) {
        this.hasGoods = hasGoods;
    }

    public Integer getBoxState() {
        return boxState;
    }

    public void setBoxState(Integer boxState) {
        this.boxState = boxState;
    }

    public Integer getBoxCellId() {
        return boxCellId;
    }

    public void setBoxCellId(Integer boxCellId) {
        this.boxCellId = boxCellId;
    }

    /**
     * 获取ID
     *
     * @return box_id - ID
     */
    public Integer getBoxId() {
        return boxId;
    }

    /**
     * 设置ID
     *
     * @param boxId ID
     */
    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    /**
     * 获取编码
     *
     * @return box_code - 编码
     */
    public String getBoxCode() {
        return boxCode;
    }

    /**
     * 设置编码
     *
     * @param boxCode 编码
     */
    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    /**
     * 获取类型：1-料箱  2-托盘
     *
     * @return box_type - 类型：1-料箱  2-托盘
     */
    public Integer getBoxType() {
        return boxType;
    }

    /**
     * 设置类型：1-料箱  2-托盘
     *
     * @param boxType 类型：1-料箱  2-托盘
     */
    public void setBoxType(Integer boxType) {
        this.boxType = boxType;
    }

    /**
     * 获取备注
     *
     * @return box_memo - 备注
     */
    public String getBoxMemo() {
        return boxMemo;
    }

    /**
     * 设置备注
     *
     * @param boxMemo 备注
     */
    public void setBoxMemo(String boxMemo) {
        this.boxMemo = boxMemo;
    }

    /**
     * 获取所属单位
     *
     * @return box_unit - 所属单位
     */
    public String getBoxUnit() {
        return boxUnit;
    }

    /**
     * 设置所属单位
     *
     * @param boxUnit 所属单位
     */
    public void setBoxUnit(String boxUnit) {
        this.boxUnit = boxUnit;
    }


    public BoxInfo() {
    }

    /**
     * 有参构造器
     * @param boxId
     * @param boxCode
     * @param boxType
     * @param boxCellId
     * @param boxState
     * @param hasGoods
     */
    public BoxInfo(Integer boxId, String boxCode, Integer boxType, Integer boxCellId, Integer boxState, Integer hasGoods) {
        this.boxId = boxId;
        this.boxCode = boxCode;
        this.boxType = boxType;
        this.boxCellId = boxCellId;
        this.boxState = boxState;
        this.hasGoods = hasGoods;
    }

}