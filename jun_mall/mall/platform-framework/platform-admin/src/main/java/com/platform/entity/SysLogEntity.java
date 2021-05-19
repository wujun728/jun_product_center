package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统日志
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class SysLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法
     */
    private String title;
    /**
     * 请求参数
     */
    private String params;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 创建时间
     */
    private Date createDate;

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
     * 设置：用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置：用户操作
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * 获取：用户操作
     */
    public String getOperation() {
        return operation;
    }

    /**
     * 设置：请求方法
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：请求方法
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：请求参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取：请求参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置：IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取：IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }
}
