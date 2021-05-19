package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class SysOssEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * URL地址
     */
    private String url;
    /**
     * 创建时间
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
     * 设置：URL地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：URL地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createDate) {
        this.createTime = createDate;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
}
