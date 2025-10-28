package com.jun.plugin.demo.jpa.tree.main;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jun.plugin.common.tree.enumpath.TreeNodeEntity;


@JsonIgnoreProperties({"iconCls","type","url","permCode","state","createTime",
	"modifyTime","nodeLevel","nodeDegree","version","new","ancestorIds",""})
@Entity
@Access(AccessType.FIELD)
@Table(name = "menu_tree")
public class MenuTree extends TreeNodeEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/** 图标 */
	private String iconCls;

	private String type;

	private String url;

	@Column(name = "perm_code")
	private String permCode;
	
	@Transient
	private String state; 

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPermCode() {
		return permCode;
	}

	public void setPermCode(String permCode) {
		this.permCode = permCode;
	}
}
