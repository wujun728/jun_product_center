package com.jun.plugin.fieldmeta.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jun.plugin.common.BaseEntity;

@Entity
@Table(name = "fm_field_entity")
@Access(AccessType.FIELD)
public class EntityField extends BaseEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "table_id")
	private Long tableId;
	@Column(name = "table_name")
	private String tableName;

	// field begin

	@Column(name = "column_name")
	private String columnName;

	@Column(name = "column_sort")
	private BigDecimal columnSort;

	@Column(name = "column_type")
	private String columnType;

	private String comments;

	@Column(name = "attr_name")
	private String attrName;

	@Column(name = "attr_type")
	private String attrType;

	private Long length;

	// 小数位数
	@Column(name = "decimal_places")
	private Long decimalPlaces;

	@Column(name = "pk_restrict", nullable = false)
	private String pkRestrict;

	@Column(name = "not_null_restrict")
	private String notNullRestrict;

	@Column(name = "unique_restrict")
	private String uniqueRestrict;
	@Column(name = "not_insert_restrict")
	private String notInsertRestrict;
	@Column(name = "not_update_restrict")
	private String notUpdateRestrict;

	@Column(name = "field_valid_code")
	private String fieldValidCode;

	@Transient
	@JsonIgnore
	private String fieldValidation;

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public BigDecimal getColumnSort() {
		return columnSort;
	}

	public void setColumnSort(BigDecimal columnSort) {
		this.columnSort = columnSort;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public Long getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(Long decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public String getPkRestrict() {
		return pkRestrict;
	}

	public void setPkRestrict(String pkRestrict) {
		this.pkRestrict = pkRestrict;
	}

	public String getNotNullRestrict() {
		return notNullRestrict;
	}

	public void setNotNullRestrict(String notNullRestrict) {
		this.notNullRestrict = notNullRestrict;
	}

	public String getUniqueRestrict() {
		return uniqueRestrict;
	}

	public void setUniqueRestrict(String uniqueRestrict) {
		this.uniqueRestrict = uniqueRestrict;
	}

	public String getNotInsertRestrict() {
		return notInsertRestrict;
	}

	public void setNotInsertRestrict(String notInsertRestrict) {
		this.notInsertRestrict = notInsertRestrict;
	}

	public String getNotUpdateRestrict() {
		return notUpdateRestrict;
	}

	public void setNotUpdateRestrict(String notUpdateRestrict) {
		this.notUpdateRestrict = notUpdateRestrict;
	}

	public String getFieldValidCode() {
		return fieldValidCode;
	}

	public void setFieldValidCode(String fieldValidCode) {
		this.fieldValidCode = fieldValidCode;
	}

	public String getFieldValidation() {
		return fieldValidation;
	}

	public void setFieldValidation(String fieldValidation) {
		this.fieldValidation = fieldValidation;
	}

}
