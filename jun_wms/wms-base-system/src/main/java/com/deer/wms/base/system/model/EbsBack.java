package com.deer.wms.base.system.model;

public class EbsBack {
    private String success;
    private String msg;
    private Integer total;
    private String rows;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public EbsBack() {
    }

    public EbsBack(String success, String msg, Integer total, String rows) {
        this.success = success;
        this.msg = msg;
        this.total = total;
        this.rows = rows;
    }

}
