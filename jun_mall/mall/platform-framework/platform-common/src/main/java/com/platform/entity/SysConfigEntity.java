package com.platform.entity;


import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统配置信息
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class SysConfigEntity {
    private String id;
    @NotBlank(message = "参数名不能为空")
    private String confKey;
    @NotBlank(message = "参数值不能为空")
    private String confValue;
    private Integer status;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfKey() {
        return confKey;
    }

    public void setConfKey(String confKey) {
        this.confKey = confKey;
    }

    public String getConfValue() {
        return confValue;
    }

    public void setConfValue(String confValue) {
        this.confValue = confValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
