package sy.test.org.apache.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sy.util.base.PoiUtil;

/**
 * 
 * @author Wujun
 * 
 */
public class TestPoi1 {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		testReadExcel();
		testForeachReadExcel();
		testWriteExcel();
	}

	/**
	 * 读取一个已存在的excel
	 * 
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static void testReadExcel() throws InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(new File("D:/temp/1.xlsx"));
		// 获取第一个工作目录,下标从0开始
		Sheet sheet = workbook.getSheetAt(0);
		// 获取该工作目录最后一行的行数
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i <= lastRowNum; i++) {
			// 获取下标为i的行
			Row row = sheet.getRow(i);
			if (row != null) {
				// 获取该行单元格个数
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					// 获取下标为j的单元格
					Cell cell = row.getCell(j);
					// 调用获取方法
					String cellValue = PoiUtil.getCellValue(cell);
					System.out.print("[" + cellValue + "]");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 使用Foreach方式读取Excel
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static void testForeachReadExcel() throws InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(new File("D:/temp/1.xlsx"));
		// 根据sheet的名字获取
		Sheet sheet = workbook.getSheet("Sheet1");
		// 处了上面testReadExcel的方式读取以外,还支持foreach的方式读取
		for (Row row : sheet) {
			for (Cell cell : row) {
				System.out.print("[" + PoiUtil.getCellValue(cell) + "]");
			}
			System.out.println();
		}
	}

	/**
	 * 创建简单的Excel
	 * 
	 * @throws IOException
	 */
	public static void testWriteExcel() throws IOException {
		// 创建一个XSSF的Excel文件
		Workbook workbook = new XSSFWorkbook();
		FileOutputStream fos = new FileOutputStream("D:/temp/test111.xlsx");
		// 创建名称为test的工作目录
		Sheet sheet = workbook.createSheet("test");
		/*
		 * 创建1个10行x10列的工作目录
		 */
		for (int i = 0; i < 10; i++) {
			// 创建一行
			Row row = sheet.createRow(i);
			for (int j = 0; j < 10; j++) {
				// 创建一个单元格
				Cell cell = row.createCell(j);
				// 设置单元格value
				cell.setCellValue("test");
				// 此处为设置Excel的样式,设置单元格内容居中,但这样设置方式并不常用,请留意下面的方法
				CellStyle cs = workbook.createCellStyle();
				cs.setAlignment(CellStyle.ALIGN_CENTER);
				cell.setCellStyle(cs);
			}
		}
		// 将Excel写出到文件流
		workbook.write(fos);
	}

}
