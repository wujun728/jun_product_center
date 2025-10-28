package com.jun.plugin.fieldmeta.service;

import java.util.List;

import com.jun.plugin.common.dataaccess.BaseService;
import com.jun.plugin.fieldmeta.entity.EntityField;
import com.jun.plugin.fieldmeta.entity.PageField;
import com.jun.plugin.fieldmeta.entity.Tablemeta;

public interface TablemetaService extends BaseService<Tablemeta, Long>{
	

	void saveFields(Long tableId,List<EntityField> entityFields,List<PageField> pageFields);
	void saveFieldsTbname(String tableName,List<EntityField> entityFields,List<PageField> pageFields);
	
	void saveFieldPair(EntityField entityField , PageField pageField);
	void deleteFieldPair(Long entityFieldId);
	
	List<PageField> getExamples();
	
	String getEntitySuperClassFullName(Tablemeta tablemeta);
	List<EntityField> getBaseEntityFields(Tablemeta tablemeta);
	List<PageField> getBasePageFields(Tablemeta tablemeta);
	void clone(List<Long> tablemetaIds);
	
	
}
