package com.jun.plugin.fieldmeta.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.jun.plugin.common.BaseEntity;

/**
 * The persistent class for the fm_type_mapping database table.
 * 
 */
@Entity
@Table(name = "fm_type_mapping")
@Access(AccessType.FIELD)
public class TypeMapping extends BaseEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name = "full_java_type")
	private String fullJavaType;

	@Column(name = "java_type")
	private String javaType;

	@Column(name = "sql_type")

	private String sqlType;

	@Lob
	@Column(name = "options")
	private String options;

	public TypeMapping() {
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getFullJavaType() {
		return this.fullJavaType;
	}

	public void setFullJavaType(String fullJavaType) {
		this.fullJavaType = fullJavaType;
	}

	public String getJavaType() {
		return this.javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getSqlType() {
		return this.sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

}