package com.ruoyi.qixing.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

public class BizCommon extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String desc1;
    private String key1;
    private String value1;
    private Date date1;
    private String remark;
    private String createId;

    public void setId(String id) { this.id = id; }
    public String getId() { return id; }
    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }
    public void setDesc1(String desc1) { this.desc1 = desc1; }
    public String getDesc1() { return desc1; }
    public void setKey1(String key1) { this.key1 = key1; }
    public String getKey1() { return key1; }
    public void setValue1(String value1) { this.value1 = value1; }
    public String getValue1() { return value1; }
    public void setDate1(Date date1) { this.date1 = date1; }
    public Date getDate1() { return date1; }
    @Override
    public void setRemark(String remark) { this.remark = remark; }
    @Override
    public String getRemark() { return remark; }
    public void setCreateId(String createId) { this.createId = createId; }
    public String getCreateId() { return createId; }
}