package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 域对象实体
 * 表名 sys_domain
 *
 * @author lipengjun
 * @date 2017-11-20 18:05:59
 */
public class SysDomainEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 域编码
     */
    private String domainCode;
    /**
     * 域名称
     */
    private String domainName;
    /**
     * 域地址
     */
    private String domainUrl;
    /**
     * 状态  0：无效   1：有效
     */
    private Integer domainStatus;
    /**
     * 创建者ID
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者ID
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

    private String icon;

    /**
     * 设置：
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public String getId() {
        return id;
    }

    /**
     * 设置：域编码
     */
    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode;
    }

    /**
     * 获取：域编码
     */
    public String getDomainCode() {
        return domainCode;
    }

    /**
     * 设置：域名称
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * 获取：域名称
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * 设置：域地址
     */
    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    /**
     * 获取：域地址
     */
    public String getDomainUrl() {
        return domainUrl;
    }

    /**
     * 设置：状态  0：无效   1：有效
     */
    public void setDomainStatus(Integer domainStatus) {
        this.domainStatus = domainStatus;
    }

    /**
     * 获取：状态  0：无效   1：有效
     */
    public Integer getDomainStatus() {
        return domainStatus;
    }

    /**
     * 设置：创建者ID
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取：创建者ID
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：更新者ID
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取：更新者ID
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置：更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取：
     */
    public String getIcon() {
        return icon;
    }
}
