package com.doroodo.base.util.excelTools;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Excel中的一个Workbook对象，依赖于 Apache POI包
 * 
 * @author hyjiacan
 * 
 */
public class Sheet {
 HSSFSheet sheet = null;
 private int firstRowNum;// 第一行号
 private int lastRowNum;// 最后行号

 /**
  * 由一个HSSFSheet构建一个Sheet对象
  * 
  * @param sheet
*/
 public Sheet(HSSFSheet sheet) {
  this.sheet = sheet;
  this.firstRowNum = sheet.getFirstRowNum();
  this.lastRowNum = sheet.getLastRowNum();
 }

 /**
  * 返回表的第一个非空行行号
  * 
  * @return 第一个非空行的行号
*/
 public int getFirstRowNum() {
  return firstRowNum;
 }

 /**
  * 返回表的最后一个非空行行号
  * 
  * @return 最后一个非空行行号
*/
 public int getLastRowNum() {
  return lastRowNum;
 }

 /**
  * 当前打开的表名字
  * 
  * @return 表名字
*/
 public String getName() {
  return sheet.getSheetName();
 }

 /**
  * 获取表数据行数
  * 
  * @return 行数
  * @see HSSFSheet
*/
 public int getRowSize() {
  return sheet.getLastRowNum() - sheet.getFirstRowNum();
 }

 /**
  * 获取表数据列数
  * 
  * @return 列数
  * @see HSSFSheet
*/
 public int getColumnSize() {
  HSSFRow row = sheet.getRow(this.getFirstRowNum());
  return row.getLastCellNum() - row.getFirstCellNum();
 }

 /**
  * 返回当前表的列名数组
  * 
  * @return 列名数组
*/
 public String[] getColumnNames() {
  ArrayList<String> h = getRowAt(this.firstRowNum);
  String[] head = new String[h.size()];
  int i = 0;
  for (String o : h) {
   head[i++] = String.valueOf(o);
  }
  return head;
 }

 /**
  * 获取指定行的数据
  * 
  * @param index
  *            指定行号
  * @return 指定行数据
  * @see HSSFRow
  * @see HSSFCell
*/
 public ArrayList<String> getRowAt(int index) {
  HSSFRow row = sheet.getRow(index);
  ArrayList<String> cells = new ArrayList<String>();
  if(row==null) {
	  cells.add("");
	  return cells;
  }
  int i = row.getFirstCellNum();
  while (i < this.getColumnSize()) {
   HSSFCell cell = row.getCell(i++);
   if (cell == null)
    cells.add("");
   else {
    Object val = null;
    switch (cell.getCellType()) {
    case Cell.CELL_TYPE_BOOLEAN:
     val = cell.getBooleanCellValue();
     break;
    case Cell.CELL_TYPE_FORMULA:
     val = cell.getCellFormula();
     break;
    case Cell.CELL_TYPE_NUMERIC:
     val = cell.getNumericCellValue();
     break;
    case Cell.CELL_TYPE_STRING:
     val = cell.getStringCellValue();
    default:
     val = cell.getRichStringCellValue();
    }

    cells.add(String.valueOf(val));
   }
  }
  return cells;
 }

 /**
  * 返回表的Table视图
  * 
  * @return 表的table视图
  * @see Table
*/
 public Table getAsTable() {
  Table table = Table.build(this.getRowSize(), this.getColumnSize());
  table.setHeader(getColumnNames());
  table.setName(sheet.getSheetName());

  for (int i = this.getFirstRowNum() + 1; i <= this.getRowSize(); i++) {
   table.addRow(this.getRowAt(i));
  }

  return table;
 }
}