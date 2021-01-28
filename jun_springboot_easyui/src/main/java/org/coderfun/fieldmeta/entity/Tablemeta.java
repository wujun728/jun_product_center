package org.coderfun.fieldmeta.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.coderfun.common.BaseEntity;

/**
 * The persistent class for the fm_tablemeta database table.
 * 
 */
@Entity
@Table(name = "fm_tablemeta")
@Access(AccessType.FIELD)
public class Tablemeta extends BaseEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "module_id")
	private Long moduleId;
	@Column(name = "module_name")
	private String moduleName;

	@Column(name = "entity_name")
	private String entityName;

	/**
	 * 只跟权限有关
	 */

	@Column(name = "business_name")
	private String businessName;

	@Column(name = "simple_name")
	private String simpleName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "can_delete")
	private String canDelete;

	@Column(name = "can_edit")
	private String canEdit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	private Date modifyTime;

	@Lob
	private String options;

	@Column(name = "parent_table_fk_name")
	private String parentTableFkName;

	@Column(name = "parent_table_name")
	private String parentTableName;

	@Column(name = "table_name")
	private String tableName;

	@Column(name = "entity_super_class")
	private String entitySuperClass;

	public Tablemeta() {
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(String canDelete) {
		this.canDelete = canDelete;
	}

	public String getCanEdit() {
		return canEdit;
	}

	public void setCanEdit(String canEdit) {
		this.canEdit = canEdit;
	}

	public String getEntitySuperClass() {
		return entitySuperClass;
	}

	public void setEntitySuperClass(String entitySuperClass) {
		this.entitySuperClass = entitySuperClass;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getOptions() {
		return this.options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getParentTableFkName() {
		return this.parentTableFkName;
	}

	public void setParentTableFkName(String parentTableFkName) {
		this.parentTableFkName = parentTableFkName;
	}

	public String getParentTableName() {
		return this.parentTableName;
	}

	public void setParentTableName(String parentTableName) {
		this.parentTableName = parentTableName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

}