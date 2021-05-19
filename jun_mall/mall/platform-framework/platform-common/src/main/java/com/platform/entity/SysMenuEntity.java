package com.platform.entity;


import java.util.Date;

/**
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class SysMenuEntity extends Tree {

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private String parentId;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 所属域
     */
    private String domainId;

    /**
     * 状态 0：无效 1：有效
     */
    private Integer status;
    /**
     * 新增人
     */
    private String createUser;
    /**
     * 修改者
     */
    private String updateUser;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 所属域
     */
    private String domainName;

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置：父菜单ID，一级菜单为0
     *
     * @param parentId 父菜单ID，一级菜单为0
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父菜单ID，一级菜单为0
     *
     * @return String
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置：菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：菜单名称
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：菜单URL
     *
     * @param url 菜单URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：菜单URL
     *
     * @return String
     */
    public String getUrl() {
        return url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 设置：菜单图标
     *
     * @param icon 菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取：菜单图标
     *
     * @return String
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置：排序
     *
     * @param orderNum 排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     *
     * @return Integer
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
