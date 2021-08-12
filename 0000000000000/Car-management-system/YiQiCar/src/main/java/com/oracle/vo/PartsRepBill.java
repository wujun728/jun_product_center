package com.oracle.vo;

import java.sql.Date;

public class PartsRepBill {
    private Integer billid;

    private String billflag;

    private String billtype;

    private Integer partsid;

    private Integer billcount;

    private Date billtime;

    private Integer billuser;
    
    private String descript;

    public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }

    public String getBillflag() {
        return billflag;
    }

    public void setBillflag(String billflag) {
        this.billflag = billflag == null ? null : billflag.trim();
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public Integer getPartsid() {
        return partsid;
    }

    public void setPartsid(Integer partsid) {
        this.partsid = partsid;
    }

    public Integer getBillcount() {
        return billcount;
    }

    public void setBillcount(Integer billcount) {
        this.billcount = billcount;
    }

    public Date getBilltime() {
        return billtime;
    }

    public void setBilltime(Date billtime) {
        this.billtime = billtime;
    }

    public Integer getBilluser() {
        return billuser;
    }

    public void setBilluser(Integer billuser) {
        this.billuser = billuser;
    }

	@Override
	public String toString() {
		return "PartsRepBill [billid=" + billid + ", billflag=" + billflag + ", billtype=" + billtype + ", partsid="
				+ partsid + ", billcount=" + billcount + ", billtime=" + billtime + ", billuser=" + billuser
				+ ", descript=" + descript + "]";
	}

	
}