package com.platform.entity;

import java.io.Serializable;
import java.util.List;


/**
 * 部门管理
 *
 * @author liepngjun
 * @date 2017-09-17 23:58:47
 */
public class SysDeptEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 上级部门ID，一级部门为0
     */
    private String parentId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer orderNum;

    private Integer deptLevel;

    private Integer status;

    private String parentName;
    /**
     * ztree属性
     */
    private Boolean open;

    private List<?> list;

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置：上级部门ID，一级部门为0
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：上级部门ID，一级部门为0
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置：部门名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    public Integer getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
