package org.coderfun.fieldmeta.service;

import java.util.List;

import org.coderfun.fieldmeta.entity.EntityField;
import org.coderfun.fieldmeta.entity.PageField;
import org.coderfun.fieldmeta.entity.Tablemeta;

import klg.j2ee.common.dataaccess.BaseService;

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
