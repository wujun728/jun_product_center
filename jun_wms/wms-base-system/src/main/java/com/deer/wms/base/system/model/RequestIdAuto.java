package com.deer.wms.base.system.model;

import javax.persistence.*;

@Table(name = "request_id_auto")
public class RequestIdAuto {
    /**
     * 自动递增id
     */
    @Id
    @Column(name = "auto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer autoId;

    /**
     * 备注
     */
    private String memo;

    /**
     * 获取自动递增id
     *
     * @return auto_id - 自动递增id
     */
    public Integer getAutoId() {
        return autoId;
    }

    /**
     * 设置自动递增id
     *
     * @param autoId 自动递增id
     */
    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    /**
     * 获取备注
     *
     * @return memo - 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注
     *
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public RequestIdAuto() {
    }

    public RequestIdAuto(String memo) {
        this.memo = memo;
    }
}