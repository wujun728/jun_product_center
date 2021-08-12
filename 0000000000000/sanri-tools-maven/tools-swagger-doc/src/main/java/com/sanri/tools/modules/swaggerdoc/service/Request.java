package com.sanri.tools.modules.swaggerdoc.service;


import java.io.Serializable;

/**
 * Created by XiuYin.Cui on 2018/1/11.
 */
public class Request implements Serializable{

    /**
     * 参数名
     */
    private String name;

    /**
     * 数据类型
     */
    private String type;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 是否必填
     */
    private Boolean require;

    /**
     * 说明
     */
    private String remark;

    /**
     * 复杂对象引用
     */
    private ModelAttr modelAttr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public Boolean getRequire() {
        return require;
    }

    public void setRequire(Boolean require) {
        this.require = require;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ModelAttr getModelAttr() {
        return modelAttr;
    }

    public void setModelAttr(ModelAttr modelAttr) {
        this.modelAttr = modelAttr;
    }
}
