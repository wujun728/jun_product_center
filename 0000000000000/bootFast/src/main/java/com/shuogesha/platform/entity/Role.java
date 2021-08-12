package com.shuogesha.platform.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 角色
 * @author zhaohaiyuan
 *
 */
public class Role implements Serializable {
	
		private Long id;
	private String name;
	private String description;
	private boolean allperms=false;
	private int sort=0;
	
	private List<String> perms= new ArrayList<String>();
	
	private List<Menu> menus= new ArrayList<Menu>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAllperms() {
		return allperms;
	}

	public void setAllperms(boolean allperms) {
		this.allperms = allperms;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<String> getPerms() {
		return perms;
	}

	public void setPerms(List<String> perms) {
		this.perms = perms;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	 
	
}
