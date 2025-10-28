package com.jun.plugin.fieldmeta.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jun.plugin.common.BaseEntity;

/**
 * The persistent class for the fm_module database table.
 * 
 */
@Entity
@Table(name = "fm_module")
@Access(AccessType.FIELD)
public class Module extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	@Column(name = "module_name")
	private String moduleName;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	private String description;

	@Column(name = "package_name")
	private String packageName;


	private String author;
	@Column(name = "copy_right")
	private String copyRight;

	public Module() {
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCopyRight() {
		return this.copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}