package com.oracle.vo;

public class OrderDetail {
    private Integer orderdetailid;

    private Integer partsid;

    private Integer orderid;

    private Integer orderpartscount;

    public Integer getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(Integer orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    public Integer getPartsid() {
        return partsid;
    }

    public void setPartsid(Integer partsid) {
        this.partsid = partsid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderpartscount() {
        return orderpartscount;
    }

    public void setOrderpartscount(Integer orderpartscount) {
        this.orderpartscount = orderpartscount;
    }

	@Override
	public String toString() {
		return "OrderDetail [orderdetailid=" + orderdetailid + ", partsid=" + partsid + ", orderid=" + orderid
				+ ", orderpartscount=" + orderpartscount + "]";
	}
    
}