package jdbctest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class ExcelMain {
    @Test
    public void testExcelRead() throws IOException {
        Workbook workbook = new XSSFWorkbook("D:\\doc\\门禁类1\\设计相关/门禁表设计.xlsx");
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 1; i < numberOfSheets; i++) {
            Sheet sheetAt = workbook.getSheetAt(i);
            int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();
            for (int j = 1; j < physicalNumberOfRows; j++) {
                Row row = sheetAt.getRow(j);
                if (row == null){
                    continue;
                }
                Cell cell1 = row.getCell(1);
                Cell cell2 = row.getCell(2);
                if (cell1 == null){
                    continue;
                }
                String columnName = cell1.getStringCellValue();
                String describe = cellStringValue(cell2);

                Cell cell8 = row.getCell(8);
                String updateDescribe = cellStringValue(cell8);

                Cell cell9 = row.getCell(9);
                String example  = cellStringValue(cell9);

                Cell cell10 = row.getCell(10);
                String enumValues = cellStringValue(cell10);
                enumValues = enumValues.replaceAll("\n",",");
                Cell cell11 = row.getCell(11);
                String remark = cellStringValue(cell11);

                System.out.println(StringUtils.join(Arrays.asList(columnName,describe,updateDescribe,example,enumValues,remark),"\t"));
            }
        }
    }

    private String cellStringValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();
                case Cell.CELL_TYPE_NUMERIC:
                    return  cell.getNumericCellValue() + "";
                case Cell.CELL_TYPE_BLANK:
                    break;
                default:
                    System.out.println("未实现的单元格类型["+cell.getRow().getRowNum()+":"+cell.getColumnIndex()+"] : "+cell.getCellType());
            }
        }
        return "";
    }
}
