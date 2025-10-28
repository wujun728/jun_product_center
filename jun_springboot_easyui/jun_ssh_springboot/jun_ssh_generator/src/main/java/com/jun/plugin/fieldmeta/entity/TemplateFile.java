package com.jun.plugin.fieldmeta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jun.plugin.common.BaseEntity;
import com.jun.plugin.common.OrderEntity;

/**
 * The persistent class for the wp_template_file database table.
 * 
 */
@Entity
@Table(name = "fm_template_file")
public class TemplateFile extends OrderEntity<Long> {
	private static final long serialVersionUID = 1L;

	private String name;
	@Column(name = "uuid_name")
	private String uuidName;

	/**
	 * 模板文件的目录，以"/"开头,以"/"结束
	 */
	private String dir;

	/**
	 * 生成文件的目录，以"/"开头,以"/"结束
	 */
	@Column(name = "gen_filedir_pattern")
	private String genFiledirPattern;

	@Column(name = "gen_filekey_pattern")
	private String genFilekeyPattern;

	@Column(name = "can_merge")
	private String canMerge;

	public String getCanMerge() {
		return canMerge;
	}

	public void setCanMerge(String canMerge) {
		this.canMerge = canMerge;
	}

	public String getUuidName() {
		return uuidName;
	}

	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}

	public String getGenFilekeyPattern() {
		return genFilekeyPattern;
	}

	public void setGenFilekeyPattern(String genFilekeyPattern) {
		this.genFilekeyPattern = genFilekeyPattern;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getGenFiledirPattern() {
		return genFiledirPattern;
	}

	public void setGenFiledirPattern(String genFiledirPattern) {
		this.genFiledirPattern = genFiledirPattern;
	}

}