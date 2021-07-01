package com.du.lin.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.du.lin.bean.ShowLog;
import com.du.lin.service.LoginLogService;
import com.du.lin.service.OperationLogService;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Excel工具类
 */
@Component
public class ExcelUtil {
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private OperationLogService operationLogService;

	/**
	 * 生成excel文件 在D:\
	 * 1生成登陆日志
	 * 2生成操作日志
	 * @param logType
	 * @return
	 */
	public File getExcel(int logType){
		List<ShowLog> list = null;
		if (logType == 1) {
			list = loginLogService.getAllShowLoginLog();			
		}else if(logType == 2){
			list = operationLogService.getAllShowLog();
		}
		
		File file  = new File("d:\\"+ System.currentTimeMillis() +".xls");
		WritableWorkbook workbook = null;
		try {
			workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setBackground(Colour.YELLOW);
			sheet.addCell(new Label(0, 0, "用户名" , wcf));
			sheet.addCell(new Label(1, 0, "操作", wcf));
			sheet.addCell(new Label(2, 0, "时间", wcf));
			sheet.setColumnView(0, 40);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 30);
			for (int row = 0; row < list.size(); row++) {
				sheet.addCell(new Label(0, row+1, list.get(row).getUsername()));
				sheet.addCell(new Label(1, row+1, list.get(row).getLogname()));
				sheet.addCell(new Label(2, row+1, list.get(row).getCreatetime()));
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}finally {
			if (workbook != null) {
				try {
					workbook.write();
					workbook.close();				
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
		}
		
		return file;
		
	}
	
}
