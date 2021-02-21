package com.deer.wms.base.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sub_inventory")
public class SubInventory {
    /**
     * 子库存表Id
     */
    @Id
    @Column(name = "sub_inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subInventoryId;

    /**
     * 子库名称
     */
    @Column(name = "sub_inventory_name")
    private String subInventoryName;

    /**
     * 子库编码
     */
    @Column(name = "sub_inventory_code")
    private String subInventoryCode;

    /**
     * 货位Id
     */
    private String slotting;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 修改人
     */
    @Column(name = "update_user_id")
    private Integer updateUserId;

    /**
     * 库存组织Id
     */
    @Column(name="organization_id")
    private Integer organizationId;

    /**
     * 获取子库存表Id
     *
     * @return sub_inventory_id - 子库存表Id
     */
    public Integer getSubInventoryId() {
        return subInventoryId;
    }

    /**
     * 设置子库存表Id
     *
     * @param subInventoryId 子库存表Id
     */
    public void setSubInventoryId(Integer subInventoryId) {
        this.subInventoryId = subInventoryId;
    }

    /**
     * 获取子库名称
     *
     * @return sub_inventory_name - 子库名称
     */
    public String getSubInventoryName() {
        return subInventoryName;
    }

    /**
     * 设置子库名称
     *
     * @param subInventoryName 子库名称
     */
    public void setSubInventoryName(String subInventoryName) {
        this.subInventoryName = subInventoryName;
    }

    /**
     * 获取子库编码
     *
     * @return sub_inventory_code - 子库编码
     */
    public String getSubInventoryCode() {
        return subInventoryCode;
    }

    /**
     * 设置子库编码
     *
     * @param subInventoryCode 子库编码
     */
    public void setSubInventoryCode(String subInventoryCode) {
        this.subInventoryCode = subInventoryCode;
    }

    /**
     * 获取货位Id
     *
     * @return slotting - 货位Id
     */
    public String getSlotting() {
        return slotting;
    }

    /**
     * 设置货位Id
     *
     * @param slotting 货位Id
     */
    public void setSlotting(String slotting) {
        this.slotting = slotting;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
}