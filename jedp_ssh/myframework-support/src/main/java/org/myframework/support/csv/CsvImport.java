package org.myframework.support.csv;

import java.io.File;

import org.myframework.support.csv.impl.ImportResult;

public interface CsvImport {

	/**
	 * 将CSV文件导入数据库中
	 * @param in
	 * @param tableId  CSV与数据库SQL的映射配置
	 */
	ImportResult impToDb(File in , String tableId);


	


	/**
	 * 获取导入模板
	 * @param tableId
	 * @param destFile
	 */
	public void getImportTemplate(String tableId, File destFile);

}
