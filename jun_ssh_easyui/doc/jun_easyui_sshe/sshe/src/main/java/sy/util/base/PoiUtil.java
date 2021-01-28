package sy.util.base;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

/**
 * POI工具类
 * 
 * @author Wujun
 * 
 */
public class PoiUtil {

	/**
	 * 由于Excel当中的单元格Cell存在类型,若获取类型错误就会产生异常, 所以通过此方法将Cell内容全部转换为String类型
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String str = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			str = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			str = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			str = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			Object value;
			DecimalFormat df = new DecimalFormat("0");// 格式化 number String 字符
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
			DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字

			if ("@".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
				str = (String)value;
				break;
			} else if ("General".equals(cell.getCellStyle()
					.getDataFormatString())) {
				value = nf.format(cell.getNumericCellValue());
				str = (String)value;
				break;
			} else {
				value = sdf.format(HSSFDateUtil.getJavaDate(cell
						.getNumericCellValue()));
				str = (String)value;
				break;
			}
			//str = String.valueOf((long) cell.getNumericCellValue());
			//break;
		case Cell.CELL_TYPE_STRING:
			str = String.valueOf(cell.getStringCellValue());
			break;
		default:
			str = null;
			break;
		}
		return str;
	}

}
