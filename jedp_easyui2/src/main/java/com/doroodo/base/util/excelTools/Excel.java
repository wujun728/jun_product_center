package com.doroodo.base.util.excelTools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Excel {
 private HSSFWorkbook wb = null;
 private String name;
 

 /**
  * 通过一个Microsoft Office Excel文件构建一个Excel对象
  * 
  * @param is
  *            Microsoft Office Excel 文件流
  *        filename  文件名
*/
 public Excel(String filename,FileInputStream is) {
	 name=filename;
  try {
   wb = new HSSFWorkbook(is);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 /**
  * 通过一个Microsoft Office Excel文件构建一个Excel对象
  * 
  * @param filename
  *            Microsoft Office Excel 文件名（包含完整路径）
*/
 public Excel(String filename) {
  name = filename;
  try {
   wb = new HSSFWorkbook(new FileInputStream(filename));
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 /**
  * 返回当前Excel文件名
  * 
  * @return 打开的文件名
*/
 public String getName() {
  return name;
 }

 /**
  * 以列表方式返回一个Microsoft office excel 文件内的所有表对象
  * 
  * @return 表对象列表
  * @see HSSFSheet
*/
 public ArrayList<Sheet> getSheetList() {
  if (wb == null) {
   return null;
  }

  ArrayList<Sheet> list = new ArrayList<Sheet>();
  int i = 0;
  while (i < this.length()) {
   list.add(new Sheet(wb.getSheetAt(i++)));
  }
  return list;
 }

 /**
  * 获取当前打开excel文件中的表数量
  * 
  * @return 表数量
*/
 public int length() {
  return wb.getNumberOfSheets();
 }

 /**
  * 返回索引为{@code index}的表，若未找到则抛出异常IllegalArgumentException
  * 
  * @param index
  *            表的索引下标，以0开始
  * @return 索引下标为{@code index}的表对象
  * @throws IllegalArgumentException
*/
 public Sheet getSheet(int index) throws IndexOutOfBoundsException {
  try {
   return new Sheet(wb.getSheetAt(index));
  } catch (IllegalArgumentException e) {
   throw new IndexOutOfBoundsException("在文件[" + name + "]中未找到索引为["
     + index + "]的表");
  }
 }

 /**
  * 返回表名为为{@code name}的表，若未找到则抛出异常IllegalArgumentException
  * 
  * @param name
  *            表的名字
  * @return 表为为{@code name}的表对象
  * @throws IllegalArgumentException
*/
 public Sheet getSheet(String name) throws IllegalArgumentException {
  try {
   return new Sheet(wb.getSheet(name));
  } catch (IllegalArgumentException e) {
   throw new IllegalArgumentException("在文件[" + name + "]中未找到名为["
     + name + "]的表");
  }
 }
}