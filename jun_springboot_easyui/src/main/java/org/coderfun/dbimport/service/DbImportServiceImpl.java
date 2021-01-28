package org.coderfun.dbimport.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.coderfun.dbmeta.ColumnMeta;
import org.coderfun.dbmeta.DbMetaCrawler;
import org.coderfun.dbmeta.DbMetaCrawlerFactory;
import org.coderfun.fieldmeta.entity.EntityField;
import org.coderfun.fieldmeta.entity.ImportedTable;
import org.coderfun.fieldmeta.entity.ImportedTable_;
import org.coderfun.fieldmeta.entity.PageField;
import org.coderfun.fieldmeta.entity.Project;
import org.coderfun.fieldmeta.entity.Tablemeta;
import org.coderfun.fieldmeta.service.ProjectService;
import org.coderfun.fieldmeta.service.TablemetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import klg.common.utils.BeanTools;
import klg.j2ee.query.jpa.expr.AExpr;

@Service
public class DbImportServiceImpl implements DbImportService {
	@Autowired
	ProjectService projectService;

	@Autowired
	ImportedTableService importedTableService;
	
	@Autowired
	TablemetaService tablemetaService;

	@Value("${fieldmeta.dbimport.codeFlag}")
	String codeFlag;
	
	//同类方法调用 事务失效
	@Autowired
	DbImportServiceImpl proxyThis;
	
	@Override
	public Set<String> getTableNames() throws SQLException {
		// TODO Auto-generated method stub
		BasicDataSource dataSource = getDataSource();
		DbMetaCrawlerFactory crawlerFactory = new DbMetaCrawlerFactory(dataSource);
		DbMetaCrawler dbMetaCrawler = crawlerFactory.newInstance();

		Set<String> tableNames = dbMetaCrawler.getTableNames();
		Project defaultProject = projectService.getDefaultProject();
		List<ImportedTable> importedTables = importedTableService.findList(AExpr.eq(ImportedTable_.projectId, defaultProject.getId()));

		for (ImportedTable importedTable : importedTables) {
			if (tableNames.contains(importedTable.getTableName())){
				tableNames.remove(importedTable.getTableName());				
			}

		}

		dataSource.close();

		return tableNames;
	}

	private BasicDataSource getDataSource() {
		Project defaultProject = projectService.getDefaultProject();
		BasicDataSource datasource = new BasicDataSource();
		datasource.setUrl(defaultProject.getDbUrl());
		datasource.setUsername(defaultProject.getDbUsername());
		datasource.setPassword(defaultProject.getDbPassword());
		datasource.setInitialSize(1);
		datasource.setMaxActive(20);

		return datasource;
	}
	/**
	 * 比较耗费资源，线程同步
	 */
	
	@Override
	public synchronized void importTable(String tableName, DbImportTableOption option) throws SQLException {
		BasicDataSource dataSource = getDataSource();
		DbMetaCrawlerFactory crawlerFactory = new DbMetaCrawlerFactory(dataSource);
		DbMetaCrawler dbMetaCrawler = crawlerFactory.newInstance();
		List<PageField> examples = tablemetaService.getExamples();
		
		proxyThis.importTable(tableName, option, dbMetaCrawler,examples);

		dataSource.close();
	}
	
	/**
	 * 比较耗费资源，线程同步
	 */
	@Override
	public synchronized void importTables(List<String> tableNames, DbImportTableOption option) throws SQLException {
		// TODO Auto-generated method stub
		BasicDataSource dataSource = getDataSource();
		DbMetaCrawlerFactory crawlerFactory = new DbMetaCrawlerFactory(dataSource);
		DbMetaCrawler dbMetaCrawler = crawlerFactory.newInstance();
		List<PageField> examples = tablemetaService.getExamples();
		for(String tableName:tableNames){
			proxyThis.importTable(tableName, option, dbMetaCrawler,examples);
		}
		dataSource.close();
	}
	
	@Transactional
	public void importTable(String tableName, 
			DbImportTableOption option, 
			DbMetaCrawler dbMetaCrawler,
			List<PageField> examples) throws SQLException {
		//初始化tablemeta，并持久化
		Tablemeta tablemeta = initTablemeta(tableName, option);
		tablemetaService.save(tablemeta);
		//if(1==1) throw new AbortException("test");
		markTheTable(tablemeta);
		
		
		//实体基类的字段
		List<EntityField> baseEntityFields = tablemetaService.getBaseEntityFields(tablemeta);

	
		List<ColumnMeta> columns = dbMetaCrawler.getColumns(tableName);	
		for (ColumnMeta column : columns) {
			if(isBaseField(baseEntityFields, column)){
				continue;
			}
			
			PageField example = null;
			if(column.getName().endsWith("_" + codeFlag)){
				example = lookupExample(examples, codeFlag);
			}else{
				example = lookupExample(examples, column.getTypeName());
			}
			
			EntityField entityField = new EntityField();
			PageField pageField = new PageField();
			if(example != null){
				BeanTools.copyProperties(pageField, example, "id","entityField");
				BeanTools.copyProperties(entityField,  example.getEntityField(), "id");				
			}
			
			setEntityField(entityField, column);
			entityField.setTableId(tablemeta.getId());			
			entityField.setTableName(tableName);
			pageField.setTableId(tablemeta.getId());
			pageField.setTableName(tableName);
			
			tablemetaService.saveFieldPair(entityField, pageField);
		}
	}
	
	private void markTheTable(Tablemeta tablemeta){
		Project defaultProject = projectService.getDefaultProject();
		ImportedTable importedTable = new ImportedTable();
		importedTable.setTableName(tablemeta.getTableName());
		importedTable.setId(defaultProject.getId());
		//importedTable.setDatabaseName(databaseName);
		importedTableService.save(importedTable);
	}
		
	private PageField lookupExample(List<PageField> examples,String sqlType){
		for(PageField example:examples){
			String tag = example.getEntityField().getColumnName().toLowerCase();
			if(tag.equals(new String("eg_"+sqlType).toLowerCase())){
				return example;
			}			
		}
		return null;
	}
	
	private boolean isBaseField(List<EntityField> baseEntityFields,ColumnMeta columnMeta){
		if(baseEntityFields == null)
			return false;
		for(EntityField entityField : baseEntityFields){
			if(entityField.getAttrName().equals(NameUtils.underlineToHump(columnMeta.getName()))){
				return true;
			}
		}
		return false;
	}
	
	
	private Tablemeta initTablemeta(String tableName, DbImportTableOption option){
		//去掉表前缀
		String removeTablePrefix = option.getRemoveTablePrefix();
		String temp = new String(tableName);
		if(StringUtils.isNotEmpty(removeTablePrefix)){
			temp = tableName.substring(removeTablePrefix.trim().length());
		}
		Tablemeta tablemeta = new Tablemeta();
		tablemeta.setTableName(tableName);
		tablemeta.setEntityName(StringUtils.capitalize(NameUtils.underlineToHump(temp)));
		tablemeta.setModuleId(option.getModuleId());
		tablemeta.setModuleName(option.getModuleName());
		tablemeta.setEntitySuperClass(option.getEntitySuperClass());
		return tablemeta;
	}
	
	private void setEntityField(EntityField entityField,ColumnMeta columnMeta){
		entityField.setColumnName(columnMeta.getName());
		entityField.setAttrName(NameUtils.underlineToHump(columnMeta.getName()));
		entityField.setLength(Long.valueOf(columnMeta.getLength()));
		entityField.setComments(columnMeta.getComment());
	}
	



}
