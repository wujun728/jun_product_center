package com.jun.plugin.utils.rss.collect.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author Wujun
 * 
 * @Date 2014年3月25日
 */
public class ExcelUtil {
	// excel表格操作
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;

	public ExcelUtil(String sheetname) {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet(sheetname);
	}

	public ExcelUtil() {
		this("sheet");
	}

	public HSSFWorkbook getwork() {
		return workbook;
	}

	public HSSFSheet getSheet() {
		return sheet;
	}

	public void setRow(int rownum) {
		// 创建行
		row = sheet.createRow(rownum);
	}

	public void setCell(int rownum, int cellNumber, String value) {
		// 创建单元格
		row = sheet.createRow(rownum);
		HSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(value);
	}

	public void setCell(int rownum, int cellNumber, int value) {
		row = sheet.createRow(rownum);
		HSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(value);
	}

	public void setCell(int cellNumber, int value) {
		if (row == null)
			row = sheet.createRow(0);// 不输入行号，自动创建为第0行
		HSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(value);
	}

	public void setCell(int cellNumber, String value) {
		if (row == null)
			row = sheet.createRow(0);
		HSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(value);
	}

	public void createFile(String fileName) throws IOException {
		// 生成表格
		FileOutputStream outputStream = new FileOutputStream(fileName);
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
}
