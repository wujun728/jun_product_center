package com.ruoyi.file.storage.util;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hslf.extractor.QuickButCruddyTextExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取office文件工具类
 */
public class ReadFileUtils {

    /**
     * 根据文件类型返回文件内容
     *
     * @param fileType    文件类型
     * @param inputStream 输入流
     * @return 结果
     * @throws IOException io异常
     */
    public static String getContentByInputStream(String fileType, InputStream inputStream) throws IOException {
        if ("doc".equals(fileType) || "docx".equals(fileType)) {
            return readWord(inputStream, fileType);
        } else if ("xlsx".equals(fileType) || "xls".equals(fileType)) {
            return readExcel(inputStream, fileType);
        } else if ("txt".equals(fileType)) {
            return readTxt(inputStream, fileType);
        } else if ("pdf".equals(fileType)) {
            return readPdf(inputStream);
        } else if ("ppt".equals(fileType) || "pptx".equals(fileType)) {
            return readPPT(inputStream, fileType);
        } else {
            System.out.println("不支持的文件类型！");
        }
        return "";
    }

    /**
     * 读取pdf内容
     *
     * @param inputStream 输入流
     * @return 结果
     */
    public static String readPdf(InputStream inputStream) {
        PDDocument pdDocument = null;
        String content = "";
        try {
            //创建解析器对象
            PDFParser pdfParser = new PDFParser(new RandomAccessReadBuffer(inputStream));
            pdDocument = pdfParser.parse();
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            content = pdfTextStripper.getText(pdDocument);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(pdDocument);
        }
        return content;
    }

    /**
     * 读取Excel中的内容
     *
     * @param extendName 文件路径
     * @return 返回结果
     * @throws IOException IOException
     */
    private static String readTxt(InputStream inputStream, String extendName) throws IOException {
        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            byte[] result = CharsetUtils.convertTxtCharsetToUTF8(bytes, extendName);
            return IOUtils.toString(result, "UTF-8");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 读取Excel中的内容
     *
     * @param extendName  文件类型
     * @param inputStream 输入流
     * @return 结果
     */
    private static String readExcel(InputStream inputStream, String extendName) {
        Workbook wb = null;
        try {
            //根据文件后缀（xls/xlsx）进行判断
            if ("xls".equalsIgnoreCase(extendName)) {
                wb = new HSSFWorkbook(inputStream);
            } else if ("xlsx".equalsIgnoreCase(extendName)) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                System.out.println("文件类型错误!");
                return "";
            }
            //开始解析,获取页签数
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                sb.append(sheet.getSheetName()).append("_");
                int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                                sb.append(cell);
                            }
                        }
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(wb);
            IOUtils.closeQuietly(inputStream);
        }
        return "";
    }

    /**
     * 读取word
     *
     * @param fileType    文件类型
     * @param inputStream 输入流
     * @return 结果
     */
    public static String readWord(InputStream inputStream, String fileType) {
        String buffer = "";
        try {
            if ("doc".equalsIgnoreCase(fileType)) {
                WordExtractor ex = new WordExtractor(inputStream);
                buffer = ex.getText();
                ex.close();
            } else if ("docx".equalsIgnoreCase(fileType)) {
                XWPFWordExtractor extractor = new XWPFWordExtractor(OPCPackage.open(inputStream));
                buffer = extractor.getText();
                extractor.close();

            } else {
                System.out.println("此文件不是word文件！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return buffer;
    }

    private static String readPPT(InputStream inputStream, String fileType) {
        String buffer = "";
        try {
            if ("ppt".equalsIgnoreCase(fileType)) {
                QuickButCruddyTextExtractor extractor = new QuickButCruddyTextExtractor(inputStream);
                buffer = extractor.getTextAsString();
                extractor.close();
            } else if ("pptx".equalsIgnoreCase(fileType)) {
                XMLSlideShow extractor = new XMLSlideShow(OPCPackage.open(inputStream));
                StringBuilder text = new StringBuilder();
                for (XSLFSlide slide : extractor.getSlides()) {
                    text.append(extractTextFromSlide(slide));
                }
                buffer = text.toString();
                extractor.close();
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        } catch (OpenXML4JException e) {
            e.getMessage();
        }
        return buffer;
    }

    private static String extractTextFromSlide(XSLFSlide slide) {
        StringBuilder text = new StringBuilder();
        for (Object shape : slide.getShapes()) {
            if (shape instanceof XSLFTextShape) {
                XSLFTextShape textShape = (XSLFTextShape) shape;
                for (XSLFTextParagraph paragraph : textShape.getTextParagraphs()) {
                    for (XSLFTextRun run : paragraph.getTextRuns()) {
                        text.append(run.getRawText());
                    }
                }
            }
        }
        return text.toString();
    }
}