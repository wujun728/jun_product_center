package com.deer.wms.base.system.model;

import com.deer.wms.common.core.service.QueryCriteria;

/**
* Created by  on 2019/11/21.
*/
public class SluggishOverdueCriteria extends QueryCriteria {
    private Integer sluggishExportParam;
    private String exp;
    private String itemCode;
    private String batch;
    private String createTime;

    public Integer getSluggishExportParam() {
        return sluggishExportParam;
    }

    public void setSluggishExportParam(Integer sluggishExportParam) {
        this.sluggishExportParam = sluggishExportParam;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public SluggishOverdueCriteria() {
    }

    public SluggishOverdueCriteria( String itemCode, String batch,String exp) {
        this.exp = exp;
        this.itemCode = itemCode;
        this.batch = batch;
    }

    public SluggishOverdueCriteria(String createTime) {
        this.createTime = createTime;
    }
}
