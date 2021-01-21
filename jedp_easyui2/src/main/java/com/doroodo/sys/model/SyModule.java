package com.doroodo.sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.doroodo.base.model.BaseModel;

/**
 * SyModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sy_module")
public class SyModule extends BaseModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String menuid;
	private String icon;
	private String menuname;
	private String url;
	private String pid;
	private Integer sort;

	// Constructors


	/** default constructor */
	public SyModule() {
	}

	/** full constructor */
	public SyModule(String menuid, String icon, String menuname, String url,
			String pid,int sort) {
		this.menuid = menuid;
		this.icon = icon;
		this.menuname = menuname;
		this.url = url;
		this.pid = pid;
		this.sort=sort;
	}

	// Property accessors
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "Id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "menuid")
	public String getMenuid() {
		return this.menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	@Column(name = "icon")
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "menuname")
	public String getMenuname() {
		return this.menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "pid")
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}