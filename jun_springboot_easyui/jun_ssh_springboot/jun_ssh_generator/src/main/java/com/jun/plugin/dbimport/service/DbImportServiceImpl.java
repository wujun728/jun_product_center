package com.jun.plugin.dbimport.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.utils.BeanTools;
import com.jun.plugin.dbmeta.ColumnMeta;
import com.jun.plugin.dbmeta.DbMetaCrawler;
import com.jun.plugin.dbmeta.DbMetaCrawlerFactory;
import com.jun.plugin.fieldmeta.entity.EntityField;
import com.jun.plugin.fieldmeta.entity.ImportedTable;
import com.jun.plugin.fieldmeta.entity.ImportedTable_;
import com.jun.plugin.fieldmeta.entity.PageField;
import com.jun.plugin.fieldmeta.entity.Project;
import com.jun.plugin.fieldmeta.entity.Tablemeta;
import com.jun.plugin.fieldmeta.service.ProjectService;
import com.jun.plugin.fieldmeta.service.TablemetaService;
import com.jun.plugin.query.jpa.expr.AExpr;

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
	public List<String> getTableNames() throws SQLException {
		// TODO Auto-generated method stub
		BasicDataSource dataSource = getDataSource();
		DbMetaCrawlerFactory crawlerFactory = new DbMetaCrawlerFactory(dataSource);
		DbMetaCrawler dbMetaCrawler = crawlerFactory.newInstance();

		List<String> tableNames = dbMetaCrawler.getTableNames();
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
		for (int i =0 ; i<columns.size(); i++) {
			ColumnMeta column = columns.get(i);
			if(isBaseField(baseEntityFields, column)){
				continue;
			}
			PageField example = lookupExample(examples, column);
			
			EntityField entityField = new EntityField();
			PageField pageField = new PageField();
			if(example != null){
				BeanTools.copyProperties(pageField, example, "id","entityField");
				BeanTools.copyProperties(entityField,  example.getEntityField(), "id", "columnSort");				
			}
			
			setEntityField(entityField, column);
			entityField.setTableId(tablemeta.getId());			
			entityField.setTableName(tableName);
			entityField.setColumnSort(BigDecimal.valueOf(i));
			entityField.setLength(Long.valueOf(column.getLength()));
			entityField.setDecimalPlaces(Long.valueOf(column.getScale()));
			pageField.setTableId(tablemeta.getId());
			pageField.setTableName(tableName);
			
			tablemetaService.saveFieldPair(entityField, pageField);
		}
	}
	
	private void markTheTable(Tablemeta tablemeta){
		Project defaultProject = projectService.getDefaultProject();
		ImportedTable importedTable = new ImportedTable();
		importedTable.setTableName(tablemeta.getTableName());
		importedTable.setProjectId(defaultProject.getId());
		//importedTable.setDatabaseName(databaseName);
		importedTableService.save(importedTable);
	}
		
	private PageField lookupExample(List<PageField> examples,ColumnMeta column){
		String sqlType = "";
		if(column.getName().endsWith("_" + codeFlag)){
			sqlType = codeFlag;
		}else{
			sqlType = column.getTypeName().replace("UNSIGNED", "").trim().toLowerCase();
//			if(sqlType.equals("bit") && column.getLength() == 1){
//				sqlType = "boolean";
//			}
		}
		
		for(PageField example:examples){
			EntityField entityField = example.getEntityField();
			String tag = entityField.getColumnName().toLowerCase();
			if(tag.contains(sqlType)){
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
