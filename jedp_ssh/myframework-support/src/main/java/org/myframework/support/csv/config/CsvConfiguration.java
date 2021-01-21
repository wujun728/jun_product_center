package org.myframework.support.csv.config;

import java.util.List;

import org.myframework.support.csv.config.CsvTable.CsvField;

/**
 *
 * <ol>获取CSV配置信息，按KEY来查找csv映射关系是否存在
 * <li>{@link CsvConfiguration }</li>
 * <li>{@link CsvTable }</li>
 * <li>{@link CsvField }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月15日
 *
 */
public interface CsvConfiguration {

	/**
	 * 获取所有配置信息
	 * @return
	 */
	List<CsvTable> getCsvTables();

	/**
	 * 根据key来查找配置信息
	 * @param  tableId  ： 配置文件中的ID规则： namespace+id  ； entity实体的ID命名规则：entities.<实体类名>
	 * @return
	 */
	CsvTable getCsvTable(String tableId);

	/**
	 * 根据tableId找到导入列的配置信息
	 * @param tableId
	 * @return
	 */
	List<? extends CsvField> getImportedCsvFields(String tableId);

	/**
	 * 根据tableId找到导出列的配置信息
	 * @param tableId
	 * @return
	 */
	List<? extends CsvField> getExportedCsvFields(String tableId);

	/**
	 * 放入配置信息
	 * @param key
	 * @param element
	 */
	void putCsvTable(String tableId,CsvTable element);

	/**
	 * 删除配置信息
	 * @param tableId
	 */
	void remove(String tableId );

	/**
	 * 查找是否包含配置信息
	 * @param tableId
	 * @return
	 */
	boolean containsKey(String tableId);

}
