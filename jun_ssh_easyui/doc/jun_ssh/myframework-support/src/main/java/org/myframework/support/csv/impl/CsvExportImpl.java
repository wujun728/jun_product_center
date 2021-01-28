package org.myframework.support.csv.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.myframework.support.csv.CsvExport;
import org.myframework.support.csv.config.CsvTable;
import org.myframework.support.csv.config.CsvTable.CsvField;

/**
 *
 * <ol>导出List<? extends Object>记录到CSV文件
 * <li>{@link CsvExport }</li>
 * <li>{@link CsvTable }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月24日
 *
 */
public class CsvExportImpl extends BaseCsvOperations implements CsvExport {

	@Override
	public void expToFile(List<? extends Object> rows, String tableId, File destFile) {
		List<Object[]> records = new ArrayList<Object[]>();
		List<? extends CsvField> fields = csvConfig.getExportedCsvFields(tableId);
		//1.添加标题行
		if (getHeaderEnabled(tableId)) {
			List<Object> header = new ArrayList<Object>();
			for (CsvField csvField : fields) {
				String colInfo =csvField.getDesc();
				header.add(colInfo );
			}
			records.add(header.toArray());
		}

		//2.添加数据行
		if(rows!=null) {
			for (Object data :rows) {
				records.add(getRow(data, fields));
			}
		}
		//销毁convert列值转化对象
		removeConvertThreadLocal();
		//3.写入到文件
		getParser().writeRows(records, destFile.getAbsolutePath());
	}


	@Override
	public void expToFile(List<? extends Object> rows, String tableId, File destFile,List<Integer> options) {
		List<Object[]> records = new ArrayList<Object[]>();
		List<? extends CsvField> oldfields = csvConfig.getExportedCsvFields(tableId);

		//0.根据用户选择输出指定列
		List<CsvField> fields = new ArrayList<CsvField> ();
		for (int i = 0; options!=null&&i < options.size(); i++) {
			int idx = Integer.valueOf(options.get(i));
			fields.add(oldfields.get(idx)) ;
		}

		//1.添加标题行
		if (getHeaderEnabled(tableId)) {
			List<Object> header = new ArrayList<Object>();
			for (CsvField csvField : fields) {
				header.add(csvField.getDesc());
			}
			records.add(header.toArray());
		}

		//2.添加数据行
		for (Object data :rows) {
			records.add(getRow(data, fields));
		}
		//销毁convert列值转化对象
		removeConvertThreadLocal();
		//3.写入到文件
		getParser().writeRows(records, destFile.getAbsolutePath());
	}

}
