package org.coderfun.sys.dict.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.coderfun.common.OrderEntity;

@Entity
@Table(name = "sys_codeitem")
@Access(AccessType.FIELD)
public class CodeItem extends OrderEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "class_code")
	private String classCode;
	private String code;
	private String name;
	private String value;

	private String extension;

	private String pinyin;

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
}
