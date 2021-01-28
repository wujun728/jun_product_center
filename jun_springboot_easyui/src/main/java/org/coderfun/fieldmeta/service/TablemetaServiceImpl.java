package org.coderfun.fieldmeta.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.PropertyUtils;
import org.coderfun.common.exception.AppException;
import org.coderfun.common.exception.ErrorCodeEnum;
import org.coderfun.dbimport.service.ImportedTableService;
import org.coderfun.fieldmeta.dao.EntityFieldDAO;
import org.coderfun.fieldmeta.dao.PageFieldDAO;
import org.coderfun.fieldmeta.dao.TablemetaDAO;
import org.coderfun.fieldmeta.entity.EntityField;
import org.coderfun.fieldmeta.entity.EntityField_;
import org.coderfun.fieldmeta.entity.PageField;
import org.coderfun.fieldmeta.entity.PageField_;
import org.coderfun.fieldmeta.entity.Tablemeta;
import org.coderfun.sys.dict.DictReader;
import org.coderfun.sys.dict.SystemCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import klg.common.utils.BeanTools;
import klg.j2ee.common.dataaccess.BaseServiceImpl;
import klg.j2ee.query.jpa.expr.AExpr;

@Service
public class TablemetaServiceImpl  extends BaseServiceImpl<Tablemeta, Long> implements TablemetaService{
	@Autowired
	TablemetaDAO tablemetaDAO;
	
	@Autowired
	PageFieldDAO pageFieldDAO ;

	@Autowired
	EntityFieldDAO entityFieldDAO;
	
	/**
	 * 样例字段，从数据库导入时使用
	 */
	@Value("${fieldmeta.dbimport.exampleFlag}")
	String exampleFlag;
	
	@Autowired
	ImportedTableService importedTableService;
	
	@Autowired
	ProjectService projectService;
	
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		super.delete(id);
		List<EntityField> entityFields = entityFieldDAO.findList(AExpr.eq(EntityField_.tableId,id));
		for(EntityField entityField : entityFields){
			deleteFieldPair(entityField.getId());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PageField> getExamples(){
		String sql = "SELECT fp.* FROM fm_field_page fp INNER JOIN fm_field_entity fe ON fp.entity_field_id=fe.id  WHERE fe.attr_name LIKE '" + exampleFlag+ "%'";
		
		return pageFieldDAO.getEntityManager().createNativeQuery(sql,PageField.class).getResultList();
	}
	
	

	
	@Override
	@Transactional
	public synchronized void saveFieldsTbname(String tableName, List<EntityField> entityFields, List<PageField> pageFields) {
		// TODO Auto-generated method stub
		
		for(int i=0 ; i <entityFields.size();i++){
			EntityField entityField = entityFields.get(i);
			
			if(entityField.getId() == null){
				EntityField unique = entityFieldDAO.getOne(
						AExpr.eq(EntityField_.tableName, tableName),
						AExpr.eq(EntityField_.attrName, entityField.getAttrName()));
				if(unique != null){
					continue;
				}
			}
	
			entityField.setTableName(tableName);
			entityField.setColumnSort(BigDecimal.valueOf(i));

			PageField pageField=pageFields.get(i);
			pageField.setTableName(tableName);
			
			saveFieldPair(entityField, pageField);
		}
	}


	@Transactional
	@Override
	public synchronized void saveFields(Long tableId, List<EntityField> entityFields, List<PageField> pageFields) {
		// TODO Auto-generated method stub
		
		Tablemeta tablemeta = tablemetaDAO.getById(tableId);
		if(tablemeta == null){
			throw new AppException(ErrorCodeEnum.DATA_NOTEXIST);
		}
		for(int i=0 ; i <entityFields.size();i++){
			EntityField entityField = entityFields.get(i);	
			entityField.setTableName(tablemeta.getTableName());
			entityField.setTableId(tablemeta.getId());
			entityField.setColumnSort(BigDecimal.valueOf(i));
			
			
			PageField pageField=pageFields.get(i);
			pageField.setTableName(tablemeta.getTableName());
			pageField.setTableId(tablemeta.getId());
			
			saveFieldPair(entityField, pageField);
		}
	}	
	
	@Override
	@Transactional
	public void saveFieldPair(EntityField entityField , PageField pageField){
		
		entityFieldDAO.save(entityField);
		
		pageField.setEntityField(entityField);
		
		pageFieldDAO.save(pageField);
		
	}
	
	@Override
	@Transactional
	public void deleteFieldPair(Long entityFieldId) {
		// TODO Auto-generated method stub
		EntityField entityField = BeanTools.newAndSet(EntityField.class, "id", entityFieldId);
		PageField pageField = pageFieldDAO.getOne(AExpr.eq(PageField_.entityField, entityField));
		if(pageField !=null){
			pageFieldDAO.delete(pageField.getId());	
		}
		entityFieldDAO.delete(entityFieldId);
	}
	
	@Override
	public List<EntityField> getBaseEntityFields(Tablemeta tablemeta) {
		// TODO Auto-generated method stub
		Sort efSort = new Sort(Direction.ASC, "columnSort");
		if(!tablemeta.getEntitySuperClass().equals("Object")){
			return entityFieldDAO.findList(efSort, AExpr.eq(EntityField_.tableName, getEntitySuperClassFullName(tablemeta)));			
		}
		return new ArrayList<>();
	}
	@Override
	public List<PageField> getBasePageFields(Tablemeta tablemeta) {
		// TODO Auto-generated method stub
		Sort pfSort = new Sort(Direction.ASC, "entityField.columnSort");
		if(!tablemeta.getEntitySuperClass().equals("Object")){
			return pageFieldDAO.findList(pfSort, AExpr.eq(PageField_.tableName, getEntitySuperClassFullName(tablemeta)));			
		}
		return new ArrayList<>();
	}

	@Override
	public String getEntitySuperClassFullName(Tablemeta tablemeta) {
		// TODO Auto-generated method stub
		return DictReader.getCodeItem(tablemeta.getEntitySuperClass(),SystemCode.ClassCode.ENTITY_SUPER_CLASS).getValue();
	}

	@Override
	@Transactional
	public void clone(List<Long> tablemetaIds) {
		// TODO Auto-generated method stub
		for(Long tablemetaId:tablemetaIds){
			Tablemeta orig = getById(tablemetaId);
			Tablemeta dest = new Tablemeta();
			try {
				PropertyUtils.copyProperties(dest, orig);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dest.setId(null);
			dest.setTableName(orig.getTableName() + "_clone");
			tablemetaDAO.save(dest);
			List<PageField> examples = pageFieldDAO.findList(AExpr.eq(PageField_.tableId, tablemetaId));
			for(PageField example:examples){
				EntityField entityField = new EntityField();
				PageField pageField = new PageField();
				if(example != null){
					BeanTools.copyProperties(pageField, example, "id","entityField");
					BeanTools.copyProperties(entityField,  example.getEntityField(), "id");				
				}
				entityField.setTableId(dest.getId());			
				entityField.setTableName(dest.getTableName());
				pageField.setTableId(dest.getId());
				pageField.setTableName(dest.getTableName());
				
				saveFieldPair(entityField, pageField);
			}
			
		}
	}
	
}
