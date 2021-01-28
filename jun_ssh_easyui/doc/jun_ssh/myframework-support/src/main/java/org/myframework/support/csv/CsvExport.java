package org.myframework.support.csv;

import java.io.File;
import java.util.List;

public interface CsvExport {

	/**
	 * @param data 需要被导出的数据
	 * @param tableId  CSV格式配置
	 * @param destFile 目标文件
	 */
	void expToFile(List<? extends Object> data, String tableId, File destFile);


	/**
	 * @param rows
	 * @param tableId
	 * @param destFile
	 * @param options 需要展现的列号(列号从0开始)
	 */
	public void expToFile(List<? extends Object> rows, String tableId, File destFile,List<Integer> options);

}
