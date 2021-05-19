package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统数据字典实体
 * 表名 sys_dict
 *
 * @author lipengjun
 * @date 2017-12-25 18:26:15
 */
public class SysDictEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;
    /**
     * 码值唯一标识, 例如，SEX、SEX_1、SEX_2
     */
    private String groupCode;
    /**
     * 码值 的数字表示，例如：1，2，3。。。。。、sex
     */
    private String dictKey;
    /**
     * 码值的中文表示， 例如：是、否      、性别
     */
    private String dictValue;
    /**
     * 备注，备用字段
     */
    private String remark;
    /**
     * 创建人id
     */
    private String createUser;
    /**
     * 
     */
    private Date createTime;

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
     * 设置：码值唯一标识, 例如，SEX、SEX_1、SEX_2
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * 获取：码值唯一标识, 例如，SEX、SEX_1、SEX_2
     */
    public String getGroupCode() {
        return groupCode;
    }
    /**
     * 设置：码值 的数字表示，例如：1，2，3。。。。。、sex
     */
    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    /**
     * 获取：码值 的数字表示，例如：1，2，3。。。。。、sex
     */
    public String getDictKey() {
        return dictKey;
    }
    /**
     * 设置：码值的中文表示， 例如：是、否      、性别
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    /**
     * 获取：码值的中文表示， 例如：是、否      、性别
     */
    public String getDictValue() {
        return dictValue;
    }
    /**
     * 设置：备注，备用字段
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注，备用字段
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 设置：创建人id
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取：创建人id
     */
    public String getCreateUser() {
        return createUser;
    }
    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }
}
