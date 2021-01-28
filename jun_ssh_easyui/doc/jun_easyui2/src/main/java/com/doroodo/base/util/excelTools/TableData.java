/*
 * 文件名：TableData.java
 * 版权：
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改内容：
 */
package com.doroodo.base.util.excelTools;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 表格数据对象
 * Title:  Excel导出工具<br>
 * Description: 封装了单个Sheet里除了表格标题及副标题等信息外的对象，包括表格表头对象、数据行对象集合及其操作方法等<br>
 * Copyright: eastcom Copyright (C) 2009 <br>
 * 
 * @author <a href="mailto:邮箱">jiangxd</a><br>
 * @e-mail: jiangxd@eastcom-sw.com<br>
 * @version 2.0 <br>
 * @creatdate 2012-11-7 下午03:37:50 <br>
 *
 */
public class TableData {
	
	private static DecimalFormat format = new DecimalFormat("0.##");
	
	/**
	 * 字符串型
	 */
	public static final int STYLE_TYPE_STRING = 0;

	/**
	 * 浮点型，保留2位小数
	 */
	public static final int STYLE_TYPE_FLOAT_2 = 1;

	/**
	 * 浮点型，保留3位小数
	 */
	public static final int STYLE_TYPE_FLOAT_3 = 2;

	/**
	 * 整形
	 */
	public static final int STYLE_TYPE_INTEGER = 3;

	/**
	 * 红色背景
	 */
	public static final int STYLE_TYPE_RED_BG = 10;

	/**
	 * 黄色背景
	 */
	public static final int STYLE_TYPE_YELLOW_BG = 11;

	/**
	 * 绿色背景
	 */
	public static final int STYLE_TYPE_GREEN_BG = 12;

	/**
	 * sheet名称
	 */
	private String sheetTitle;
	
	/**
	 * 列表头对象
	 */
	private TableHeaderMetaData header;

	/**
	 * 行对象集合
	 */
	private LinkedList<TableDataRow> rows;

	/**
	 * 总行数
	 */
	private int totalRows;
	
	public TableData(){}

	public TableData(TableHeaderMetaData header) {
		this.header = header;
		rows = new LinkedList<TableDataRow>();
	}

	/**
	 * 小计
	 */
	public void compute(){
		for (int i = 0; i < header.getColumnCount(); i++){
			TableColumn tc = header.getColumnAt(i);
			if (tc.isVisible() && tc.isGrouped() && tc.isDisplaySummary()){
				buildSummary(i);
			}
		}
	}
	
	class SummaryData{
		String key;
		int index;
		int count;
		TableDataRow row;
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append(key).append("\t").append(index).append("\t").append(count)
				.append("\t");
			for (TableDataCell cell:row.getCells()){
				sb.append(cell.getValue()).append("\t");
			}
			return sb.toString();
		}		
	}
	
	/**
	 * 新建小计行
	 * @param key 小计描述关键字(通常是需要小计的分组值)
	 * @param index
	 * @return
	 */
	private TableDataRow buildNewRow(String key, int index){
		TableDataRow nrow = new TableDataRow(this);
		for (int i = 0; i < index; i++){
			nrow.addCell(""); //添加空单元格
		}
		nrow.addCell("("+key+")小计");
		for (int i = index+1; i < header.getColumnCount(); i++){
			TableColumn thc = header.getColumnAt(i);
			if (thc.getAggregateRule() != null){
				nrow.addCell(0);
			} else {
				nrow.addCell("");
			}
		}
		return nrow;
	}
	
	private void buildSummary(int index){		
		LinkedList<SummaryData> lst = new LinkedList<SummaryData>();
		String okey = null; SummaryData sd = null;
		for (int j = 0; j < rows.size(); j++){
			TableDataRow row = rows.get(j);
			String key = row.getCellAt(index).getValue();
			
			if (okey == null){
				okey = key;				
				sd = new SummaryData();
				sd.key = key;
				sd.count = 0;
				sd.row = buildNewRow(key, index);
			}
			
			if (okey!=null && !okey.equals(key)){
				for (int i = index; i < header.getColumnCount(); i++){
					TableColumn thc = header.getColumnAt(i);
					if (thc.getAggregateRule() != null){		
						String value = sd.row.getCellAt(i).getValue();
						if (thc.getAggregateRule().equalsIgnoreCase("avg")){
							if (sd.count > 0){
								sd.row.getCellAt(i).setValue("平均："+format.format(Double.parseDouble(value)/sd.count));
							} else {
								sd.row.getCellAt(i).setValue("平均："+0);
							}
						}else if (thc.getAggregateRule().equalsIgnoreCase("max")){
							sd.row.getCellAt(i).setValue("最大值："+value);
						}else if (thc.getAggregateRule().equalsIgnoreCase("min")){
							sd.row.getCellAt(i).setValue("最小值："+value);
						}else if (thc.getAggregateRule().equalsIgnoreCase("sum")){
							sd.row.getCellAt(i).setValue("求和："+value);
						}else if (thc.getAggregateRule().equalsIgnoreCase("count")){
							sd.row.getCellAt(i).setValue("共"+value+"行");
						}
					}
				}
				sd.index = j;
				
				FormulaProcessor.getInstance().fillValue(sd.row);
				lst.add(sd);
				
				okey = key;				
				sd = new SummaryData();
				sd.key = key;
				sd.count = 0;
				sd.row = buildNewRow(key, index);
			}
			sd.count++;
			for (int i = index+1; i < header.getColumnCount(); i++){
				TableColumn thc = header.getColumnAt(i);
				if (thc.getColumnType() != TableColumn.COLUMN_TYPE_FORMULA && thc.getAggregateRule() != null){					
					Double value = Double.parseDouble(sd.row.getCellAt(i).getValue());
					Double cellValue = null;
					try {
						cellValue = Double.valueOf(row.getCellAt(i).getValue());
					} catch (NumberFormatException e) {
						cellValue = null;
					}
					if(cellValue == null)
						continue;
					if (thc.getAggregateRule().equalsIgnoreCase("max")){
						if (value < cellValue){
							value = cellValue;
						}
					} else if (thc.getAggregateRule().equalsIgnoreCase("min")){
						if (value > cellValue){
							value = cellValue;
						}
					} else if (thc.getAggregateRule().equalsIgnoreCase("count")){
						value++;
					} else if (thc.getAggregateRule().equalsIgnoreCase("sum")){
						value += cellValue;
					} else if (thc.getAggregateRule().equalsIgnoreCase("avg")){
						value += cellValue;						
					}
					sd.row.getCellAt(i).setValue(format.format(value));
				}
			}
		}		
		
		if (sd != null){
			for (int i = index; i < header.getColumnCount(); i++){
				TableColumn thc = header.getColumnAt(i);
				if (thc.getAggregateRule() != null){	
					String value = sd.row.getCellAt(i).getValue();
					if (thc.getAggregateRule().equalsIgnoreCase("avg")){
						if (sd.count > 0){
							sd.row.getCellAt(i).setValue("平均："+format.format(Double.parseDouble(value)/sd.count));
						} else {
							sd.row.getCellAt(i).setValue("平均："+0);
						}
					}else if (thc.getAggregateRule().equalsIgnoreCase("max")){
						sd.row.getCellAt(i).setValue("最大值："+value);
					}else if (thc.getAggregateRule().equalsIgnoreCase("min")){
						sd.row.getCellAt(i).setValue("最小值："+value);
					}else if (thc.getAggregateRule().equalsIgnoreCase("sum")){
						sd.row.getCellAt(i).setValue("求和："+value);
					}else if (thc.getAggregateRule().equalsIgnoreCase("count")){
						sd.row.getCellAt(i).setValue("共"+value+"行");
					}
				}
			}
			FormulaProcessor.getInstance().fillValue(sd.row);
			lst.add(sd);
			sd.index = rows.size();
		}
		for (int i = 0; i < lst.size(); i++){
			SummaryData sda = lst.get(i);
			rows.add(sda.index+i, sda.row);
		}
	}
	
	//GETTER和SETTER方法
	/**
	 * 获取列表头对象 
	 * 
	 * @return
	 */
	public TableHeaderMetaData getTableHeader() {
		return header;
	}

	/**
	 * 添加行对象
	 * @param row 行对象
	 */
	public void addRow(TableDataRow row) {
		rows.add(row);
	}

	public List<TableDataRow> getRows() {
		return rows;
	}

	/**
	 * 根据行号获取行对象
	 * @param index 行号
	 * @return
	 */
	public TableDataRow getRowAt(int index) {
		return rows.get(index);
	}

	/**
	 * 获取数据行数
	 * @return
	 */
	public int getRowCount() {
		return rows.size();
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setHeader(TableHeaderMetaData header) {
		this.header = header;
	}

	public void setRows(LinkedList<TableDataRow> rows) {
		this.rows = rows;
	}

	public String getSheetTitle() {
		return sheetTitle;
	}

	public void setSheetTitle(String sheetTitle) {
		this.sheetTitle = sheetTitle;
	}

}
