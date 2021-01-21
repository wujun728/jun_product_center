package com.doroodo.base.vo.web;

public class Web {
	private String title;
	private String c_m_navtitle;
	private String i_u_username;
	private String i_u_userid;
	private String i_u_userrole;
	private String i_u_userip;
	private String i_u_login_header;
	private String i_u_login_foot;
	public String getI_u_userrole() {
		return i_u_userrole;
	}
	public void setI_u_userrole(String i_u_userrole) {
		this.i_u_userrole = i_u_userrole;
	}
	public String getI_u_userid() {
		return i_u_userid;
	}
	public void setI_u_userid(String i_u_userid) {
		this.i_u_userid = i_u_userid;
	}
	public String getI_u_login_header() {
		return i_u_login_header;
	}
	public void setI_u_login_header(String i_u_login_header) {
		this.i_u_login_header = i_u_login_header;
	}
	public String getI_u_login_foot() {
		return i_u_login_foot;
	}
	public void setI_u_login_foot(String i_u_login_foot) {
		this.i_u_login_foot = i_u_login_foot;
	}
	private LeftMenu leftMenu;
	private String c_m_footer;
	private String themename;

	public LeftMenu getLeftMenu() {
		return leftMenu;
	}
	public String getThemename() {
		return themename;
	}
	public void setThemename(String themename) {
		this.themename = themename;
	}
	public void setLeftMenu(LeftMenu leftMenu) {
		this.leftMenu = leftMenu;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getC_m_navtitle() {
		return c_m_navtitle;
	}
	public void setC_m_navtitle(String c_m_navtitle) {
		this.c_m_navtitle = c_m_navtitle;
	}
	public String getI_u_username() {
		return i_u_username;
	}
	public void setI_u_username(String i_u_username) {
		this.i_u_username = i_u_username;
	}
	
	public String getI_u_userip() {
		return i_u_userip;
	}
	public void setI_u_userip(String i_u_userip) {
		this.i_u_userip = i_u_userip;
	}

	public String getC_m_footer() {
		return c_m_footer;
	}
	public void setC_m_footer(String c_m_footer) {
		this.c_m_footer = c_m_footer;
	}
	
}
