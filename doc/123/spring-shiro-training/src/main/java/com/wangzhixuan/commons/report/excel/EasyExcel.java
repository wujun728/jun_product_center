package com.wangzhixuan.commons.report.excel;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.wangzhixuan.commons.utils.StringUtils;

/**
 *
 * 快速简单操作Excel的工具
 *
 * 参考：http://git.oschina.net/pyinjava/fastexcel
 * 
 * @author L.cm
 */
public final class EasyExcel implements Closeable {
	private static final Logger logger = LogManager.getLogger(EasyExcel.class);
	
	// 缓存类信息，提高性能
	private static final ConcurrentMap<String, List<ExcelFiledInfo>> filedInfo = new ConcurrentHashMap<String, List<ExcelFiledInfo>>();
	// spring的类型转换
	private static final ConversionService convert = DefaultConversionService.getSharedInstance();
	// 表达式处理
	private static final ExpressionParser elParser = new SpelExpressionParser();
	private static final TemplateParserContext elContext = new TemplateParserContext();
	// 默认的格式化
	public static final String DEFAULT_NUM_FORMAT = "#.##";
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	public static final String DEFAULT_BOOL_FORMAT = "true:false";
	
	private int startRow;
	private String sheetName;
	private String excelFilePath;
	private final Workbook workbook;
	
	/**
	 * 构造方法，传入需要操作的excel文件路径
	 *
	 * @param excelFilePath
	 *            需要操作的excel文件的路径
	 * @throws IOException
	 *             IO流异常
	 * @throws InvalidFormatException
	 *             非法的格式异常
	 */
	public EasyExcel(String excelFilePath) throws IOException, InvalidFormatException {
		this.startRow = 0;
		this.sheetName = "Sheet1";
		this.excelFilePath = excelFilePath;
		this.workbook = createWorkbook();
	}
	
	/**
	 * 通过数据流操作excel，仅用于读取数据
	 *
	 * @param inputStream
	 *            excel数据流
	 * @throws IOException
	 *             IO流异常
	 * @throws InvalidFormatException
	 *             非法的格式异常
	 */
	public EasyExcel(InputStream inputStream) throws IOException, InvalidFormatException {
		this.startRow = 0;
		this.sheetName = "Sheet1";
		this.excelFilePath = "";
		this.workbook = WorkbookFactory.create(inputStream);
	}
	
	/**
	 * 通过数据流操作excel
	 *
	 * @param inputStream
	 *            excel数据流
	 * @param outFilePath
	 *            输出的excel文件路径
	 * @throws IOException
	 *             IO流异常
	 * @throws InvalidFormatException
	 *             非法的格式异常
	 */
	public EasyExcel(InputStream inputStream, String outFilePath) throws IOException, InvalidFormatException {
		this(inputStream);
		this.excelFilePath = outFilePath;
	}
	
	/**
	 * 开始读取的行数，这里指的是标题内容行的行数，不是数据开始的那行
	 *
	 * @param startRow
	 *            开始行数
	 */
	public void setStartRow(int startRow) {
		if (startRow < 1) {
			throw new RuntimeException("最小为1");
		}
		this.startRow = --startRow;
	}
	
	/**
	 * 解析读取excel文件
	 *
	 * @param clazz 对应的映射类型
	 * @param <T> 泛型
	 * @return 读取结果
	 */
	public <T> List<T> parse(Class<T> clazz) {
		List<T> resultList = null;
		try {
			Sheet sheet = workbook.getSheet(this.sheetName);
			if (null != sheet) {
				resultList = new ArrayList<T>(sheet.getLastRowNum() - 1);
				Row row = sheet.getRow(this.startRow);

				// 获取bean的配置信息
				List<ExcelFiledInfo> infoList = getFiledInfo(clazz);
				Map<String, ExcelFiledInfo> infoMap = new HashMap<String, ExcelFiledInfo>();
				for (ExcelFiledInfo info : infoList) {
					String cellName = info.getCellName();
					cellName = StringUtils.isBlank(cellName) ? info.getField().getName() : cellName;
					infoMap.put(cellName, info);
				}
				Map<String, String> titleMap = new HashMap<String, String>();
				for (Cell title : row) {
					CellReference cellRef = new CellReference(title);
					titleMap.put(cellRef.getCellRefParts()[2], title.getRichStringCellValue().getString());
				}

				for (int i = this.startRow + 1; i <= sheet.getLastRowNum(); i++) {
					T t = clazz.newInstance();
					Row dataRow = sheet.getRow(i);
					for (Cell data : dataRow) {
						CellReference cellRef = new CellReference(data);
						String cellTag = cellRef.getCellRefParts()[2];
						String name = titleMap.get(cellTag);
						ExcelFiledInfo info = infoMap.get(name);
						if (null == info)
							continue;
						getCellValue(data, t, info);
					}
					resultList.add(t);
				}
			} else {
				throw new RuntimeException("sheetName:" + this.sheetName + " is not exist");
			}
		} catch (InstantiationException e) {
			logger.error("初始化异常", e);
		} catch (IllegalAccessException e) {
			logger.error("初始化异常", e);
		} catch (ParseException e) {
			logger.error("时间格式化异常", e);
		} catch (Exception e) {
			logger.error("其他异常", e);
		}
		return resultList;
	}
	
	private void getCellValue(Cell cell, Object object, ExcelFiledInfo info)
			throws IllegalAccessException, ParseException {
		Field field = info.getField();
		Class<?> fieldType = field.getType();
		ExcelCellType excelCellType = info.getType();
		String format = info.getFormat();
		switch (cell.getCellTypeEnum()) {
		case BLANK:
			break;
		case BOOLEAN:
			field.set(object, cell.getBooleanCellValue());
			break;
		case ERROR:
			field.set(object, cell.getErrorCellValue());
			break;
		case FORMULA:
			field.set(object, cell.getCellFormula());
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				if (excelCellType == ExcelCellType.DATE) {
					field.set(object, cell.getDateCellValue());
				} else {
					format = StringUtils.isBlank(format) ? DEFAULT_DATE_FORMAT : format;
					SimpleDateFormat dateFormat = new SimpleDateFormat(format);
					field.set(object, dateFormat.format(cell.getDateCellValue()));
				}
			} else {
				if (excelCellType == ExcelCellType.TEXT) {
					String s = String.valueOf(cell.getNumericCellValue());
					if (s.contains("E")) {
						s = s.trim();
						BigDecimal bigDecimal = new BigDecimal(s);
						s = bigDecimal.toPlainString();
					}
					field.set(object, s);
				} else {
					Object value = convert.convert(cell.getNumericCellValue(), fieldType);
					field.set(object, value);
				}
			}
			break;
		case STRING:
			if (excelCellType == ExcelCellType.NUMBER) {
				Object value = convert.convert(cell.getNumericCellValue(), fieldType);
				field.set(object, value);
			} else if (excelCellType == ExcelCellType.TEXT) {
				field.set(object, cell.getRichStringCellValue().getString());
			} else if (excelCellType == ExcelCellType.DATE) {
				format = StringUtils.isBlank(format) ? DEFAULT_DATE_FORMAT : format;
				SimpleDateFormat dateFormat = new SimpleDateFormat(format);
				field.set(object, dateFormat.parse(cell.getRichStringCellValue().getString()));
			} else if (excelCellType == ExcelCellType.BOOL) {
				format = StringUtils.isBlank(format) ? DEFAULT_BOOL_FORMAT : format;
				String[] boolValues = format.split(":|:");
				String value = cell.getStringCellValue();
				field.set(object, !boolValues[1].equals(value));
			}
			break;
		default:
			field.set(object, cell.getStringCellValue());
			break;
		}
	}
	
	private Workbook createWorkbook() throws IOException, InvalidFormatException {
		File file = new File(this.excelFilePath);
		Workbook workbook = null;
		if (!file.exists()) {
			logger.warn("文件:{} 不存在！创建此文件！", this.excelFilePath);
			if (!file.createNewFile()) {
				throw new IOException("文件创建失败");
			}
			workbook = new XSSFWorkbook();
		} else {
			workbook = WorkbookFactory.create(file);
		}
		return workbook;
	}
	
	/**
	 * 将数据写入excel文件
	 *
	 * @param list 数据列表
	 * @param <T> 泛型
	 * @return 写入结果
	 */
	public <T> boolean createExcel(List<T> list) {
		if (null == this.excelFilePath || "".equals(this.excelFilePath))
			throw new NullPointerException("excelFilePath is null");
		if (null == list || list.isEmpty()) {
			return false;
		}

		// 获取bean的配置信息
		T test = list.get(0);
		List<ExcelFiledInfo> infoList = getFiledInfo(test.getClass());
		// 排序
		Collections.sort(infoList);

		FileOutputStream fileOutputStream = null;
		try {
			Sheet sheet = workbook.createSheet(this.sheetName);
			// 生成标题行
			Row titleRow = sheet.createRow(0);
			int fieldSize = infoList.size();
			for (int i = 0; i < fieldSize; i++) {
				Cell cell = titleRow.createCell(i);
				ExcelFiledInfo info = infoList.get(i);
				String cellName = info.getCellName();
				if (StringUtils.isBlank(cellName)) {
					cellName = info.getField().getName();
				}
				cell.setCellValue(cellName);
			}
			// 生成数据行
			for (int i = 0, length = list.size(); i < length; i++) {
				Row row = sheet.createRow(i + 1);
				T t = list.get(i);
				for (int j = 0; j < fieldSize; j++) {
					Cell cell = row.createCell(j);
					ExcelFiledInfo info = infoList.get(j);
					String format = info.getFormat();
					Field field   = info.getField();
					String el     = info.getEl();
					Object object;
					// 如果存在el表达式，计算他
					if (StringUtils.isNotBlank(el)) {
						Expression expression = elParser.parseExpression(el, elContext);
						object = expression.getValue(t, field.getType());
					} else {
						object = field.get(t);
					}

					CellStyle cellStyle = null;
					switch (info.getType()) {
					case TEXT:
						cell.setCellType(CellType.STRING);
						cell.setCellValue(object.toString());
						break;
					case NUMBER:
						cell.setCellType(CellType.NUMERIC);
						cellStyle = workbook.createCellStyle();
						format = StringUtils.isBlank(format) ? DEFAULT_NUM_FORMAT : format;
						cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(format));
						cell.setCellStyle(cellStyle);
						if (object instanceof Integer) {
							cell.setCellValue((Integer) object);
						} else if (object instanceof Short) {
							cell.setCellValue((Short) object);
						} else if (object instanceof Float) {
							cell.setCellValue((Float) object);
						} else if (object instanceof Byte) {
							cell.setCellValue((Byte) object);
						} else if (object instanceof Double) {
							cell.setCellValue((Double) object);
						} else {
							cell.setCellValue((String) object);
						}
						break;
					case DATE:
						cell.setCellType(CellType.STRING);
						cellStyle = workbook.createCellStyle();
						format = StringUtils.isBlank(format) ? DEFAULT_DATE_FORMAT : format;
						DataFormat dateFormat = workbook.createDataFormat();
						cellStyle.setDataFormat(dateFormat.getFormat(format));
						cell.setCellStyle(cellStyle);
						cell.setCellValue((Date) object);
						break;
					case BOOL:
						cell.setCellType(CellType.STRING);
						format = StringUtils.isBlank(format) ? DEFAULT_BOOL_FORMAT : format;
						String[] boolValues = format.split(":|:");
						cell.setCellValue(object == null ? boolValues[0] : (Boolean) object ? boolValues[0] : boolValues[1]);
						break;
					}
				}
			}
			File file = new File(this.excelFilePath);
			if (!file.exists()) {
				if (!file.createNewFile()) {
					throw new IOException("文件创建失败");
				}
			}
			fileOutputStream = new FileOutputStream(file);
			workbook.write(fileOutputStream);
		} catch (IOException e) {
			logger.error("流异常", e);
		} catch (IllegalAccessException e) {
			logger.error("反射异常", e);
		} catch (Exception e) {
			logger.error("其他异常", e);
		} finally {
			IOUtils.closeQuietly(fileOutputStream);
		}
		return true;
	}
	
	@Override
	public void close() throws IOException {
		this.workbook.close();
	}
	
	/**
	 * 获取类注解信息
	 * @param clazz
	 * @return ExcelFiledInfo集合
	 */
	private List<ExcelFiledInfo> getFiledInfo(Class<?> clazz) {
		String name = clazz.getName();
		if (filedInfo.containsKey(name)) {
			return filedInfo.get(name);
		} else {
			List<ExcelFiledInfo> infoList = loadFiledInfo(clazz);
			filedInfo.put(name, infoList);
			return infoList;
		}
	}
	
	/**
	 * 获取类注解信息
	 * @param clazz
	 * @return ExcelFiledInfo集合
	 */
	private List<ExcelFiledInfo> loadFiledInfo(Class<?> clazz) {
		List<ExcelFiledInfo> infoList = new ArrayList<ExcelFiledInfo>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(ExcelField.class)) {
				ExcelField excelField = field.getAnnotation(ExcelField.class);
				field.setAccessible(true);
				infoList.add(new ExcelFiledInfo(field, excelField, i));
			}
		}
		return infoList;
	}
	
	/**
	 * 设置需要读取的sheet名字，不设置默认的名字是Sheet1，也就是excel默认给的名字，所以如果文件没有自已修改，这个方法也就不用调了
	 *
	 * @param sheetName 需要读取的Sheet名字
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
