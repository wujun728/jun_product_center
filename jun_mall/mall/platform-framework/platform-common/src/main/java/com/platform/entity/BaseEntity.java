package com.platform.entity;

import java.util.Date;

/**
 * 对业务实体做公共属性
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class BaseEntity {
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
}
