package com.doroodo.base.model;

import java.util.List;

public class Tree implements java.io.Serializable {
	// Fields
	private String id;
	private String pid;
	private int sysid;
	public int getSysid() {
		return sysid;
	}

	public void setSysid(int sysid) {
		this.sysid = sysid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	private String text;
	private String state="closed" ;// 是否展开，默认展开open，如果有关闭closed 会默认在去去一次子节点的树
	private boolean checked = false; // 是否选中，默认false
	private Attributes attributes;
	private List children;

	private class Attributes{
		private String routeid;
		private String routeName;
		public String getRouteName() {
			return routeName;
		}
		public void setRouteName(String routeName) {
			this.routeName = routeName;
		}
		public String getRouteid() {
			return routeid;
		}
		public void setRouteid(String routeid) {
			this.routeid = routeid;
		}
		
		public Attributes(String routeid ,String routeName){
			this.routeid = routeid;
			this.routeName = routeName;
		}
		public Attributes(String routeid ){
			this.routeid = routeid;
		}
		
	}
	public Attributes initAb(String routeid){
		return new Attributes(routeid );
	}
	
	public Attributes initAb(String routeid,String routeName){
		return new Attributes(routeid,  routeName );
	}
	
	
	
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List getChildren() {
		return children;
	}
	public void setChildren(List children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
