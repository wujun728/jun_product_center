package com.jun.plugin.gen.service;

import java.util.List;
import java.util.Set;

import com.jun.plugin.fieldmeta.entity.EntityField;
import com.jun.plugin.fieldmeta.entity.Module;
import com.jun.plugin.fieldmeta.entity.PageField;
import com.jun.plugin.fieldmeta.entity.Tablemeta;

public class CodeModel {

	private Tablemeta tablemeta;
	//实体名首字母小写
	private String entityNameOfFirstLowcase;
	//实体名全小写
	private String entityNameOfAllLowcase;
	//实体基类
	private String entitySuperClassFullName;
	//实体类导入包列表
	private Set<String> entityImportList;
	//实体主键
	private EntityField pkColumn;

	private List<EntityField> entityFields;
	private List<EntityField> baseEntityFields;

	private List<PageField> pageFields;
	private List<PageField> basePageFields;

	//所有实体字段
	private List<EntityField> allEntityFields;
	// 所有pageField
	private List<PageField> allPageFields;
	//所属模块
	private Module module;
	//权限前缀，module.moduleName + tablemeta.businessName
	private String permissionPrefix;
	//当前时间
	private String nowTime;

	public String getPermissionPrefix() {
		return permissionPrefix;
	}

	public void setPermissionPrefix(String permissionPrefix) {
		this.permissionPrefix = permissionPrefix;
	}

	public String getEntitySuperClassFullName() {
		return entitySuperClassFullName;
	}

	public void setEntitySuperClassFullName(String entitySuperClassFullName) {
		this.entitySuperClassFullName = entitySuperClassFullName;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Tablemeta getTablemeta() {
		return tablemeta;
	}

	public void setTablemeta(Tablemeta tablemeta) {
		this.tablemeta = tablemeta;
	}

	public String getEntityNameOfFirstLowcase() {
		return entityNameOfFirstLowcase;
	}

	public void setEntityNameOfFirstLowcase(String entityNameOfFirstLowcase) {
		this.entityNameOfFirstLowcase = entityNameOfFirstLowcase;
	}

	public String getEntityNameOfAllLowcase() {
		return entityNameOfAllLowcase;
	}

	public void setEntityNameOfAllLowcase(String entityNameOfAllLowcase) {
		this.entityNameOfAllLowcase = entityNameOfAllLowcase;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public EntityField getPkColumn() {
		return pkColumn;
	}

	public void setPkColumn(EntityField pkColumn) {
		this.pkColumn = pkColumn;
	}

	public List<EntityField> getEntityFields() {
		return entityFields;
	}

	public void setEntityFields(List<EntityField> entityFields) {
		this.entityFields = entityFields;
	}

	public List<EntityField> getBaseEntityFields() {
		return baseEntityFields;
	}

	public void setBaseEntityFields(List<EntityField> baseEntityFields) {
		this.baseEntityFields = baseEntityFields;
	}

	public List<PageField> getPageFields() {
		return pageFields;
	}

	public void setPageFields(List<PageField> pageFields) {
		this.pageFields = pageFields;
	}

	public List<PageField> getBasePageFields() {
		return basePageFields;
	}

	public void setBasePageFields(List<PageField> basePageFields) {
		this.basePageFields = basePageFields;
	}

	public Set<String> getEntityImportList() {
		return entityImportList;
	}

	public void setEntityImportList(Set<String> entityImportList) {
		this.entityImportList = entityImportList;
	}

	public List<PageField> getAllPageFields() {
		return allPageFields;
	}

	public void setAllPageFields(List<PageField> allPageFields) {
		this.allPageFields = allPageFields;
	}

	public List<EntityField> getAllEntityFields() {
		return allEntityFields;
	}

	public void setAllEntityFields(List<EntityField> allEntityFields) {
		this.allEntityFields = allEntityFields;
	}
}
