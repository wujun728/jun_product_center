package com.deer.wms.base.system.model;

import javax.persistence.*;

@Table(name = "account_alias")
public class AccountAlias {
    /**
     * 账户别名id
     */
    @Id
    @Column(name = "alias_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aliasId;
    /** 账户别名 */
    @Column(name = "account_alias")
    private String accountAlias;
    /** 描述 */
    @Column(name = "description")
    private String description;
    /** 类型 */
    @Column(name="type")
    private String type;
    /** EBS账户别名ID */
    @Column(name="disposition_id")
    private Integer dispositionId;
    /** 有效时间 */
    @Column(name="effective_date")
    private String effectiveDate;
    /** 失效日期 */
    @Column(name="disable_date")
    private String disableDate;
    /** 生效标识 */
    @Column(name="enabled_flag")
    private String enabledFlag;
    /** 库存组织Id */
    @Column(name="organization_id")
    private Integer organizationId;
    @Column(name="update_time")
    private String updateTime;

    /**
     * 获取账户别名id
     *
     * @return alias_id - 账户别名id
     */
    public Integer getAliasId() {
        return aliasId;
    }

    /**
     * 设置账户别名id
     *
     * @param aliasId 账户别名id
     */
    public void setAliasId(Integer aliasId) {
        this.aliasId = aliasId;
    }

    /**
     * @return account_alias
     */
    public String getAccountAlias() {
        return accountAlias;
    }

    /**
     * @param accountAlias
     */
    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public Integer getDispositionId() {
        return dispositionId;
    }

    public void setDispositionId(Integer dispositionId) {
        this.dispositionId = dispositionId;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(String disableDate) {
        this.disableDate = disableDate;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public AccountAlias() {
    }

    public AccountAlias(String accountAlias, String description, Integer dispositionId, String effectiveDate, String disableDate, String enabledFlag, Integer organizationId,String updateTime) {
        this.accountAlias = accountAlias;
        this.description = description;
        this.dispositionId = dispositionId;
        this.effectiveDate = effectiveDate;
        this.disableDate = disableDate;
        this.enabledFlag = enabledFlag;
        this.organizationId = organizationId;
        this.updateTime = updateTime;
    }
}