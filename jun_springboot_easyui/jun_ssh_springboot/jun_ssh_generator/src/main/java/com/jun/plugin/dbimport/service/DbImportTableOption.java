package com.jun.plugin.dbimport.service;

public class DbImportTableOption {
	private String removeTablePrefix;
	private Long moduleId;
	private String moduleName;
	private String entitySuperClass;

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getRemoveTablePrefix() {
		return removeTablePrefix;
	}

	public void setRemoveTablePrefix(String removeTablePrefix) {
		this.removeTablePrefix = removeTablePrefix;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getEntitySuperClass() {
		return entitySuperClass;
	}

	public void setEntitySuperClass(String entitySuperClass) {
		this.entitySuperClass = entitySuperClass;
	}

}
