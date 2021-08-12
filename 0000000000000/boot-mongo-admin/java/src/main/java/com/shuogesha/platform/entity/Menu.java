package com.shuogesha.platform.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 菜单
 * 
 * @author zhaohaiyuan
 *
 */
@Document(collection="menu")
public class Menu implements Serializable {
	@Id 
	private String id;

	private String name;
	private String icon;

	private String path;

	private String page;

	private String status;

	private Integer sort;

	private String pid="";
	// 子菜单
	private List<Menu> children = new ArrayList<Menu>();
	// 子按钮
	private List<Menu> operate = new ArrayList<Menu>();

	private String type;// 类型
	private String perms;// 权限

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public List<Menu> getOperate() {
		return operate;
	}

	public void setOperate(List<Menu> operate) {
		this.operate = operate;
	}

}
