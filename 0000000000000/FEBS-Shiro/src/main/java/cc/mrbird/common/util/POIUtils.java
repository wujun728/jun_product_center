package cc.mrbird.common.util;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Apache POI SXSS相关API的简易封装
 */
public class POIUtils {

    private static Logger log = LoggerFactory.getLogger(POIUtils.class);

    protected POIUtils() {

    }

    private static final int MDEFAULTROWACCESSWINDOWSIZE = 100;

    private static SXSSFWorkbook newSXSSFWorkbook(int rowAccessWindowSize) {
        return new SXSSFWorkbook(rowAccessWindowSize);
    }

    static SXSSFWorkbook newSXSSFWorkbook() {
        return newSXSSFWorkbook(MDEFAULTROWACCESSWINDOWSIZE);
    }

    static SXSSFSheet newSXSSFSheet(SXSSFWorkbook wb, String sheetName) {
        return wb.createSheet(sheetName);
    }

    static SXSSFRow newSXSSFRow(SXSSFSheet sheet, int index) {
        return sheet.createRow(index);
    }

    static SXSSFCell newSXSSFCell(SXSSFRow row, int index) {
        return row.createCell(index);
    }

    /**
     * 设定单元格宽度 (手动/自动)
     *
     * @param sheet 工作薄对象
     * @param index 单元格索引
     * @param width 指定宽度,-1为自适应
     * @param value 自适应需要单元格内容进行计算
     */
    static void setColumnWidth(SXSSFSheet sheet, int index, short width, String value) {
        if (width == -1 && value != null && !"".equals(value)) {
            sheet.setColumnWidth(index, (short) (value.length() * 512));
        } else {
            width = width == -1 ? 200 : width;
            sheet.setColumnWidth(index, (short) (width * 35.7));
        }
    }

    static void writeByLocalOrBrowser(HttpServletResponse response, String fileName, SXSSFWorkbook wb, OutputStream out) {
        try {
            ZipSecureFile.setMinInflateRatio(0L);
            if (response != null) {
                // response对象不为空,响应到浏览器下载
                response.setContentType(FebsConstant.XLSX_CONTENT_TYPE);
                response.setHeader("Content-disposition", "attachment; filename="
                        + URLEncoder.encode(String.format("%s%s", fileName, FebsConstant.XLSX_SUFFIX), "UTF-8"));
                if (out == null) {
                    out = response.getOutputStream();
                }
            }
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    public static void checkExcelFile(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("excel文件不存在.");
        }

        checkExcelFile(file.getAbsolutePath());
    }

    private static void checkExcelFile(String file) {
        if (!file.endsWith(FebsConstant.XLSX_SUFFIX)) {
            throw new IllegalArgumentException("抱歉,目前ExcelKit仅支持.xlsx格式的文件.");
        }
    }

}
