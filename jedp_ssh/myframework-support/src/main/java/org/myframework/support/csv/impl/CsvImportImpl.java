package org.myframework.support.csv.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.myframework.commons.util.DateUtils;
import org.myframework.commons.util.StringUtils;
import org.myframework.dao.id.UUIDHexGenerator;
import org.myframework.dao.orm.BaseDomain;
import org.myframework.support.base.BaseCrudService;
import org.myframework.support.csv.CsvImport;
import org.myframework.support.csv.config.CsvTable;
import org.myframework.support.csv.config.CsvTable.CsvField;
import org.myframework.support.security.Security2Utils;
import org.springframework.util.Assert;

/**
 *
 * <ol>
 * 导入CSV文件到指定的表
 * <li>{@link CsvTable }</li>
 * <li>{@link CsvImport }</li>
 * </ol>
 *
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月24日
 *
 */
public class CsvImportImpl extends BaseCsvOperations implements CsvImport {

	private static ThreadLocal<BaseCrudService > jpaThreadLocal = new ThreadLocal<BaseCrudService>();

	public static void setJpaBaseServiceImpl(BaseCrudService jpaBaseService) {
		jpaThreadLocal.set(jpaBaseService);
	}
	/**
	 * <ol>
	 * <li>读取CSV所有数据到数组</li>
	 * <li>根据配置文件判断是否有标题行，如果有需要删除标题行，标题行不插入数据库</li>
	 * <li>根据每个字段的数据类型要求将不符合要求的数据剔除</li>
	 * <li>将最终符合要求的数据JDBC批量插入到数据库（该过程大概1秒左右）</li>
	 * </ol>
	 *
	 * @param in
	 * @param tableId
	 *            CSV与数据库SQL的映射配置
	 */
	@Override
	@SuppressWarnings("unchecked")
	public ImportResult impToDb(File in, String tableId) {
		// 1.获取导入配置
		CsvTable table = csvConfig.getCsvTable(tableId);
		Assert.notNull(table, tableId + "对应的配置不存在");
		boolean headerEnabled = getHeaderEnabled(tableId);
		List<? extends CsvField> importedFields = csvConfig.getImportedCsvFields(tableId);
		// 1.读取CSV
		List<String[]> allrecords = parser.parseRows(in);
		int totalCnt = headerEnabled ? allrecords.size() - 1
				: allrecords.size();
		// 2.去掉不符合规则的数据List
		List<String[]> records = removeInvaidData(allrecords, tableId);
		
		// 3.转化为List<Map>方便进行POJO赋值
		int recordsize = records.size();
		List<Map<String, ?>> rows = new ArrayList<Map<String, ?>>(recordsize);
		for (int i = 0; i < recordsize; i++) {
			Map<String, Object> result = toMap(records.get(i), importedFields);
			setDefaultVal(table,result);
			logger.debug(" 导入行 :" + result);
			rows.add(result);
		}
		
		// 4.导入数据库
		String rowFilter = table.getRowFilter();
		File datafile = getInvalidDataFile();
		if (StringUtils.isNullOrBlank(rowFilter)) {
			//不需要逐行检查，执行批量插入
			batchSave(table,rows);
		} else {
			//需要逐行检查，先按rowFilter的校验规则检查是否可以插入，再执行插入操作
			for (int i = 0; i < rows.size(); i++) {
				Map rowMap = rows.get(i);
				if(customValidate(rowFilter,rowMap)) {
					saveObject(table,rowMap);
				}else {
					writeErrorData(datafile, records.get(i),tableId);
					recordsize = recordsize-1 ;
				}
			}
		}
		jpaThreadLocal.remove();
		// 销毁convert列值转化对象
		removeConvertThreadLocal();
		// 4.正常情况下返回提交总记录数和成功数，JDBC提交异常时就直接由统一异常来处理
		return new ImportResult(totalCnt, recordsize, datafile.getName());
	}

	/**
	 * 给各列设置默认值
	 * @param table
	 * @param result
	 */
	void setDefaultVal(CsvTable table ,Map<String, Object> result){
		//键名对应值为空，设置默认值
		List<? extends CsvField> fields = table.getCsvFields();
		for (CsvField csvField : fields) {
			String name = csvField.getName();
			String defVal = csvField.getDefValue();
			Object val = result.get(name);
			if (val==null&&!StringUtils.isNullOrBlank(defVal)) {
				defVal = defVal.equals(CsvTable.UUID_VALUE)? (String)new UUIDHexGenerator().generateValue():defVal;
				defVal = defVal.equals(CsvTable.TODAY_VALUE)?DateUtils.getCurrentDateAsString():defVal;
				defVal = defVal.equals(CsvTable.NOW_VALUE)?DateUtils.getCurrentDateTimeAsString():defVal;
				defVal = defVal.equals(CsvTable.CREATOR_ID)?Security2Utils.getUserId():defVal;
				result.put(name,defVal);
			}
		}
	}
	/**
	 * 写插入失败数据记录到文件
	 * @param file
	 * @param row
	 * @param tableId
	 */
	void writeErrorData(File file , String[] row,String tableId) {
		List<Object> errorDataList = new ArrayList<Object>(
				row.length + 1);
		errorDataList.addAll(Arrays.asList(row));
		errorDataList.add(getErrmsg());
		logger.debug("writeErrorData to file"+ file.getAbsolutePath());
		parser.writeRows(Collections.singletonList(errorDataList.toArray()),file.getAbsolutePath()); 
	}

	/**
	 * 保存单行数据
	 * @param config
	 * @param rowMap
	 */
	private void saveObject(CsvTable config, Map rowMap) {
		String sqlkey = config.getSqlKey();
		String entityClz = config.getClz();
		if (StringUtils.isNullOrBlank(sqlkey)) {
			BaseCrudService jpa = jpaThreadLocal.get();
			Object entityObj =null;
			try {
				entityObj = Class.forName(entityClz).newInstance();
				BeanUtils.copyProperties(entityObj, rowMap);
			} catch ( Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				throw new RuntimeException( "BeanUtils转化map为pojo发生异常：class=" + entityClz + "map="+rowMap);
			}
			if (entityObj instanceof BaseDomain) {
				jpa.save((BaseDomain)entityObj);
			}else {
				jpa.save(entityObj);
			}
		}else {
			jdbc.update(sqlkey, rowMap);
		}
	}
	
	/**
	 * 保存多行数据
	 * @param config
	 * @param rows
	 */
	private void batchSave(CsvTable config,List<Map<String, ?>> rows) {
		String sqlkey = config.getSqlKey();
		String entityClz = config.getClz();
		if (StringUtils.isNullOrBlank(sqlkey)) {
			BaseCrudService jpa = jpaThreadLocal.get();
			List<Object> objList = new ArrayList<Object>();
			for (int i = 0; i < rows.size(); i++) {
				Map rowMap = rows.get(i);
				try {
					Object entityObj = Class.forName(entityClz)
							.newInstance();
					BeanUtils.copyProperties(entityObj, rowMap);
					objList.add(entityObj);
				} catch (Exception e) {
					logger.error(ExceptionUtils.getFullStackTrace(e));
					throw new RuntimeException( "BeanUtils转化map为pojo发生异常：class=" + entityClz + "map="+rowMap);
				}
			}
			jpa.batchInsert(objList);
		}else {
			Map<String, ?>[] batchValues = (Map<String, ?>[]) rows.toArray(new HashMap[0]);
			jdbc.batchUpdate(sqlkey, batchValues);
		}
	}

	@Override
	public void getImportTemplate(String tableId, File destFile) {
		List<Object[]> records = new ArrayList<Object[]>();
		List<? extends CsvField> fields = csvConfig
				.getImportedCsvFields(tableId);
		// 1.添加标题行

		if (getHeaderEnabled(tableId)) {
			List<Object> header = new ArrayList<Object>();
			for (CsvField csvField : fields) {
				List<String> list = new ArrayList<String>();
				String colInfo = csvField.getDesc();
				String type = csvField.getType();
				String format = type.equalsIgnoreCase(CsvTable.DATE)
						? CsvTable.DATE_FORMAT : type;

				format = ((CsvTable.STRING.equals(type)
						|| CsvTable.CUSTOM.equals(type)) ? ""
								: " 格式要求：" + format);
				//
				format = CsvTable.UUID_VALUE.equals(type) ? "系统自动生成" : format;
				if (csvField.isRequired())
					list.add("必填");
				if (!StringUtils.isNullOrBlank(format))
					list.add(format);
				String validMsg = StringUtils.join(list);
				header.add(colInfo + (StringUtils.isNullOrBlank(validMsg) ? ""
						: "(" + validMsg + ")"));
			}
			records.add(header.toArray());
		}

		// 销毁convert列值转化对象
		removeConvertThreadLocal();
		// 3.写入到文件
		getParser().writeRows(records, destFile.getAbsolutePath());
	}

}
