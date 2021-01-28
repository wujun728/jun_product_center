package org.coderfun.fieldmeta.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fm_field_page")
@Access(AccessType.FIELD)
public class PageField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "table_id")
	private Long tableId;
	@Column(name = "table_name")
	private String tableName;

	@OneToOne()
	@JoinColumn(name = "entity_field_id")
	private EntityField entityField;

	@Column(name = "field_title")
	private String fieldTitle;

	@Column(name = "can_list")
	private String canList;

	@Column(name = "can_query")
	private String canQuery;

	@Column(name = "query_type")
	private String queryType;

	// form
	@Column(name = "can_edit")
	private String canEdit;

	private String required;

	@Column(name = "grid_row_col")
	private String gridRowCol;

	@Column(name = "need_new_line")
	private String needNewLine;

	@Column(name = "code_class")
	private String codeClass;

	@Column(name = "code_name")
	private String codeName;

	@Column(name = "form_type")
	private String formType;

	@Column(name = "field_formatter")
	private String fieldFormatter;

	@Transient
	@JsonIgnore
	private String fieldValidation;

	public Long getId() {
		return id;
	}

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

	public EntityField getEntityField() {
		return entityField;
	}

	public void setEntityField(EntityField entityField) {
		this.entityField = entityField;
	}

	public String getCanList() {
		return canList;
	}

	public void setCanList(String canList) {
		this.canList = canList;
	}

	public String getCanQuery() {
		return canQuery;
	}

	public void setCanQuery(String canQuery) {
		this.canQuery = canQuery;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getCanEdit() {
		return canEdit;
	}

	public void setCanEdit(String canEdit) {
		this.canEdit = canEdit;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getFieldFormatter() {
		return fieldFormatter;
	}

	public void setFieldFormatter(String fieldFormatter) {
		this.fieldFormatter = fieldFormatter;
	}

	public String getGridRowCol() {
		return gridRowCol;
	}

	public void setGridRowCol(String gridRowCol) {
		this.gridRowCol = gridRowCol;
	}

	public String getNeedNewLine() {
		return needNewLine;
	}

	public void setNeedNewLine(String needNewLine) {
		this.needNewLine = needNewLine;
	}

	public String getCodeClass() {
		return codeClass;
	}

	public void setCodeClass(String codeClass) {
		this.codeClass = codeClass;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFieldTitle() {
		return fieldTitle;
	}

	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle = fieldTitle;
	}

	public String getFieldValidation() {
		return fieldValidation;
	}

	public void setFieldValidation(String fieldValidation) {
		this.fieldValidation = fieldValidation;
	}

}
