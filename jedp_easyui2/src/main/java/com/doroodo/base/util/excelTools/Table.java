package com.doroodo.base.util.excelTools;

import java.util.ArrayList;

/**
 * excel表的Table二维结构，所有数据均以字符串（{@link String}）类型存储
 * 
 * @author hyjiacan
 * @see Sheet
 */
public class Table {
 private ArrayList<ArrayList<String>> table = null;
 private String[] header = null;// 表头信息
 private int colSize; // 表列数
 private int rowSize; // 表行数
 private String name;// 表名称

 /**
  * 单元格中的数据和要查找的串完全相同
*/
 public static final int FIND_EQUALS = 0;
 /**
  * 单元格中的数据包含要查找的串
*/
 public static final int FIND_CONTAINS = 1;
 /**
  * 单元格中的数据以要查找的串开头
*/
 public static final int FIND_STARTS_WITH = 2;
 /**
  * 单元格中的数据以要查找的串结尾
*/
 public static final int FIND_ENDS_WITH = 3;

 private Table() {
  table = new ArrayList<ArrayList<String>>();
 }

 /**
  * 通过一个{@link Sheet}对象构建一个Table对象
  * 
  * @param s
  *            Sheet对象
  * @see Sheet
*/
 public Table(Sheet s) {
  this.setHeader(s.getColumnNames());
  this.setName(s.sheet.getSheetName());
  this.setColSize(s.getColumnSize());
  this.setRowSize(s.getRowSize());
  for (int i = s.getFirstRowNum() + 1; i <= this.getRowSize(); i++) {
   this.addRow(s.getRowAt(i));
  }
 }

 /**
  * 返回当前表的名字
  * 
  * @return 表名字
*/
 public String getName() {
  return name;
 }

 /**
  * 设置表名字
  * 
  * @param name
  *            表名字
*/
 void setName(String name) {
  this.name = name;
 }

 /**
  * 返回表列数
  * 
  * @return 表列数
*/
 public int getColSize() {
  return colSize;
 }

 /**
  * 用于构建表的时候设置表列数，若所给列数小于0则抛出 IllegalArgumentException 异常
  * 
  * @param colSize
  *            表要设置为多少列
  * @see IllegalArgumentException
*/
 private void setColSize(int colSize) {
  if (colSize < 0)
   throw new IllegalArgumentException("Wrong column size");
  this.colSize = colSize;
 }

 /**
  * 返回表行数
  * 
  * @return 表行数
*/
 public int getRowSize() {
  return rowSize;
 }

 /**
  * 用于构建表的时候设置表行数，若所给行数小于0则抛出 IllegalArgumentException异常
  * 
  * @param colSize
  *            表要设置为多少行
  * @throws IllegalArgumentException
  * @see IllegalArgumentException
*/
 private void setRowSize(int rowSize) {
  if (rowSize < 0) {
   throw new IllegalArgumentException("Wrong row size");
  }
  this.rowSize = rowSize;
 }

 /**
  * 返回表头（列名称）
  * 
  * @return 表头{@code String[]}
*/
 public String[] getHeader() {
  return header;
 }

 /**
  * 设置表的列名称
  * 
  * @param header
  *            表列名称数组
*/
 void setHeader(String[] header) {
  this.header = header;
 }

 /**
  * 构建一张表，里面存放规范的excel表信息
  * 
  * @param rows
  *            表行数
  * @param cols
  *            表列数
  * @return 新{@code Table}实例
*/
 public static Table build(int rows, int cols) {
  Table t = new Table();
  t.setColSize(cols);
  t.setRowSize(rows);
  return t;
 }

 /**
  * 通过索引获取表内某一行的数据，若指定索引行不存在，抛出 IndexOutOfBoundsException 异常
  * 
  * @param index
  *            行索引
  * @return 指定行数据
  * @throws IndexOutOfBoundsException
  * 
  * @see IndexOutOfBoundsException
*/
 public ArrayList<String> getRowAt(int index) {
  if (index < 0 && index > this.rowSize)
   throw new IndexOutOfBoundsException("指定行不存在");
  return new ArrayList<String>(table.get(index));
 }

 /**
  * 通过索引获取表内某一列的数据，若指定索引列不存在，抛出 IndexOutOfBoundsException 异常
  * 
  * @param index
  *            列索引
  * @return 指定列数据
  * @throws IndexOutOfBoundsException
  * 
  * @see IndexOutOfBoundsException
*/
 public ArrayList<String> getColumn(int index) {
  if (index < 0 && index > this.colSize)
   throw new IndexOutOfBoundsException("指定列不存在");
  ArrayList<String> cols = new ArrayList<String>();
  for (ArrayList<String> a : table) {
   cols.add(a.get(index));
  }

  return cols;
 }

 /**
  * 获取某个指定单元格的数据，若指定索引单元格不存在，抛出 IndexOutOfBoundsException 异常
  * 
  * @param row
  *            指定行索引
  * @param col
  *            指定列索引
  * @return 指定索引的单元格数据
  * @throws IndexOutOfBoundsException
  * 
  * @see IndexOutOfBoundsException
*/
 public String getCell(int row, int col) {
  if (row < 0 && row > this.rowSize)
   throw new IndexOutOfBoundsException("指定行不存在");
  if (col < 0 && col > this.colSize)
   throw new IndexOutOfBoundsException("指定列不存在");
  return table.get(row).get(col);
 }

 /**
  * 添加 新行到表中
  * 
  * @param row
  *            要添加的新行
*/
 void addRow(ArrayList<String> row) {
  table.add(row);
 }

 /**
  * 通过某个列的值value找到所在的行集合（Table），若指定列不存在，抛出 IllegalArgumentException 异常
  * 
  * @param columnIndex
  *            指定要查找的列索引，以0开始
  * @param value
  *            要查找的单元格的值
  * @return 找到的行的集合
  * @see Table
  * @throws IllegalArgumentException
*/
 public Table findRowByCellValue(int columnIndex, String value) {
  return findRowByCellValue(columnIndex, value, FIND_EQUALS);
 }

 /**
  * 通过某个列的值value找到所在的行集合（Table），若指定列不存在，抛出 IllegalArgumentException 异常
  * 
  * @param columnName
  *            指定要查找的列名称
  * @param value
  *            要查找的单元格的值
  * @return 找到的行的集合
  * @see Table
  * @throws IllegalArgumentException
*/
 public Table findRowByCellValue(String columnName, String value) {
  return findRowByCellValue(columnName, value, FIND_EQUALS);
 }

 /**
  * 通过某个列的值value找到所在的行集合（Table），若指定列不存在，抛出 IllegalArgumentException 异常
  * 
  * @param columnIndex
  *            指定要查找的列索引，以0开始
  * @param value
  *            要查找的单元格的值
  * @param option
  *            查找方式：
  *            <p>
  *            完全匹配 <b>FIND_EQUALS</b><br/>
  *            包含 <b>FIND_CONTAINS</b><br/>
  *            以....开头 <b>FIND_STARTS_WITH</b><br />
  *            以....结尾 <b>FIND_ENDS_WITH</b>
  *            </p>
  *            默认为 <b>FIND_EQUALS</b>
  * @return 找到的行的集合
  * @see Table
  * @throws IllegalArgumentException
*/
 public Table findRowByCellValue(int columnIndex, String value, int option) {
  value = value.toLowerCase();

  Table t = new Table();
  t.setColSize(this.colSize);
  t.setHeader(header);
  t.setName("搜索[" + this.getName() + ">>" + header[columnIndex] + ">>"
    + value + "]的结果");
  switch (option) {
  case FIND_EQUALS:
   for (ArrayList<String> al : table) {
    if (al.get(columnIndex).equalsIgnoreCase(value))
     t.addRow(al);
   }
   break;
  case FIND_CONTAINS:
   for (ArrayList<String> al : table) {
    if (al.get(columnIndex).toLowerCase().contains(value))
     t.addRow(al);
   }
   break;
  case FIND_STARTS_WITH:
   for (ArrayList<String> al : table) {
    if (al.get(columnIndex).toLowerCase().startsWith(value))
     t.addRow(al);
   }
   break;
  case FIND_ENDS_WITH:
   for (ArrayList<String> al : table) {
    if (al.get(columnIndex).toLowerCase().endsWith(value))
     t.addRow(al);
   }
   break;
  default:
   throw new IllegalArgumentException("非法的\"option\"值");
  }
  t.setRowSize(t.table.size());

  return t;
 }

 /**
  * 通过某个列的值value找到所在的行集合（Table），若指定列不存在，抛出 IllegalArgumentException 异常
  * 
  * @param columnName
  *            指定要查找的列名称
  * @param value
  *            要查找的单元格的值
  * @param option
  *            查找方式：
  *            <p>
  *            完全匹配 <b>FIND_EQUALS</b><br/>
  *            包含 <b>FIND_CONTAINS</b><br/>
  *            以....开头 <b>FIND_STARTS_WITH</b><br />
  *            以....结尾 <b>FIND_ENDS_WITH</b><br />
  *            </p>
  *            默认为 <b>FIND_EQUALS</b>
  * @return 找到的行的集合
  * @see Table
  * @throws IllegalArgumentException
*/
 public Table findRowByCellValue(String columnName, String value, int option) {
  int index = -1;
  for (int i = 0; i < header.length; i++) {
   if (header[i].equalsIgnoreCase(columnName)) {
    index = i;
    break;
   }
  }
  if (index == -1)
   throw new IllegalArgumentException("指定列\"" + columnName + "\"不存在");

  return findRowByCellValue(index, value, option);
 }
}