package com.oracle.vo;

import java.util.Date;

public class User {
    private Integer userid;

    private String loginname;

    private String loginpwd;

    private Date logintime;

    private Integer eid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd == null ? null : loginpwd.trim();
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

	@Override
	public String toString() {
		return "User [userid=" + userid + ", loginname=" + loginname + ", loginpwd=" + loginpwd + ", logintime="
				+ logintime + ", eid=" + eid + "]";
	}
    
    
}