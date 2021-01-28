package com.doroodo.base.util.excelTools;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 数据行对象
 * Title:  Excel导出工具<br>
 * Description: 封装了数据导出到excel中的行对象，包括行中列数据对象的操作方法等<br>
 * Copyright: eastcom Copyright (C) 2009 <br>
 * 
 * @author <a href="mailto:邮箱">jiangxd</a><br>
 * @e-mail: jiangxd@eastcom-sw.com<br>
 * @version 2.0 <br>
 * @creatdate 2012-11-7 下午03:32:20 <br>
 *
 */
public class TableDataRow {
	/**
	 * 列数据对象集合
	 */
	private LinkedList<TableDataCell> cells;

	/**
	 * 对应的表格数据对象（一对多关系）
	 */
	private TableData table;

	/**
	 * 行样式
	 */
	private int rowStyle = TableColumn.COLUMN_TYPE_STRING;

	public void addCell(TableDataCell cell) {
		cells.add(cell);
	}

	public void addCell(String value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	public void addCell(Integer value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	public void addCell(Double value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	public void addCell(Object value) {
		if (value instanceof String) {
			addCell((String) value);
		} else if (value instanceof Integer) {
			addCell((Integer) value);
		} else if (value instanceof Double) {
			addCell((Double) value);
		} else if (value instanceof BigDecimal) {
			addCell(value.toString());
		} else if (value instanceof Long) {
			addCell(value.toString());
		} else if(value instanceof Date){
			addCell(((Date) value).toLocaleString());
		} else if(value instanceof Timestamp){
			addCell(((Timestamp) value).toLocaleString());
		} else if(value == null){
			addCell("");
		} else
			addCell(value + "");
	}

	/**
	 * 根据列序号获取列数据对象
	 * @param index
	 * @return
	 */
	public TableDataCell getCellAt(int index) {
		return cells.get(index);
	}

	public List<TableDataCell> getCells() {
		return cells;
	}

	public TableData getTable() {
		return table;
	}

	public TableDataRow(TableData table) {
		cells = new LinkedList<TableDataCell>();
		this.table = table;
	}

	public void setRowStyle(int rowStyle) {
		this.rowStyle = rowStyle;
	}

	public int getRowStyle() {
		return rowStyle;
	}
}
