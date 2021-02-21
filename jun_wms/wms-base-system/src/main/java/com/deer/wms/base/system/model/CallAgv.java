package com.deer.wms.base.system.model;

import javax.persistence.*;

@Table(name = "call_agv")
public class CallAgv {
    /**
     * 呼叫空载具任务
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 编码
     */
    private String code;

    /**
     * 状态 1-已呼叫  2-已完成
     */
    private Integer state;

    /**
     * 获取呼叫空载具任务
     *
     * @return id - 呼叫空载具任务
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置呼叫空载具任务
     *
     * @param id 呼叫空载具任务
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取编码
     *
     * @return code - 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     *
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取状态 1-已呼叫  2-已完成
     *
     * @return state - 状态 1-已呼叫  2-已完成
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态 1-已呼叫  2-已完成
     *
     * @param state 状态 1-已呼叫  2-已完成
     */
    public void setState(Integer state) {
        this.state = state;
    }

    public CallAgv() {
    }

    public CallAgv(String code, Integer state) {
        this.code = code;
        this.state = state;
    }
}