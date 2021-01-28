package org.myframework.support.csv.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.myframework.commons.util.Assert;
import org.myframework.commons.util.Collections3;
import org.myframework.commons.util.DateUtils;
import org.myframework.commons.util.ExceptionUtils;
import org.myframework.commons.util.StringUtils;
import org.myframework.dao.jdbc.impl.ConfigedJdbc;
import org.myframework.support.csv.Converter;
import org.myframework.support.csv.annotation.CsvConverter;
import org.myframework.support.csv.config.CsvConfiguration;
import org.myframework.support.csv.config.CsvTable;
import org.myframework.support.csv.config.CsvTable.CsvField;
import org.myframework.support.csv.config.LoadPackageClasses;
import org.myframework.support.csv.parser.AbstractParser;
import org.myframework.support.csv.parser.UnivocityParser;
import org.myframework.support.spring.SpringUtils;
import org.myframework.web.commons.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * <ol>
 * 封装CSV导入导出基础方法
 * <li>{@link 数据转化类必须标注 @CsvConverter 注解}</li>
 * </ol>
 *
 * @see CsvConverter CsvConfiguration ConfigedJdbc  AbstractParser
 * @author Wujun
 * @since 1.0
 * @2015年6月24日
 *
 */
public abstract class BaseCsvOperations implements InitializingBean{

	/**
	 * Logger for this class
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected AbstractParser parser = new UnivocityParser();

	protected ConfigedJdbc jdbc;

	protected CsvConfiguration csvConfig;

	/**
	 * 导入使用
	 * 1.去除不合规则的记录 2.转换成键值对数组
	 *
	 * @param in
	 * @param tableId
	 * @return
	 */
	protected List<String[]> removeInvaidData(List<String[]> records,
			String tableId) {
		logger.debug("原始数组长度 :" + records.size());
		boolean headerEnabled = getHeaderEnabled(tableId);
		if (headerEnabled && Collections3.isNotEmpty(records)) {
			records.remove(0);
			logger.debug("去除第一行标题栏后的长度 :" + records.size());
		}
		 
		extraValidRecord(records, tableId);
		logger.debug("符合列值校验规则的记录数 :" + records.size());
		return records;
	}

	/**
	 * <ol>
	 * 导入使用
	 * 单条记录转化为键值对
	 * </ol>
	 * <li>根据type来进行数据转化, 当type=custom,可以配置自定义format来实现转化 ）</li> <li>format填写内容
	 * : 类名或springbeanId</li> <li>
	 * 完整类应该在com.hollycrm.hollybeacon.business.csv.converters包下</li>
	 *
	 * @see Converter
	 * @param record
	 * @param fields
	 * @return
	 */
	protected Map<String, Object> toMap(String[] record,
			List<? extends CsvField> fields) {
		Map<String, Object> map = new HashMap<String, Object>();
		long cellsize = record.length;
		for (int i = 0; i < fields.size(); i++) {
			CsvField field = fields.get(i);
			String column = field.getName();
			String type = field.getType();
			String format = field.getFormat();
			String value = i < cellsize ? record[i] : null;
			//
			if (CsvTable.DATE.equals(type) && value != null) {
				map.put(column, java.sql.Date.valueOf(toDateString(value)));
			} else if (CsvTable.DATE_TIME.equals(type) && value != null) {
				map.put(column, java.sql.Timestamp.valueOf(value));
			} else if (CsvTable.NUMBER.equals(type) && value != null) {
				map.put(column, value);
			} else if (CsvTable.CUSTOM.equals(type)
					&& !StringUtils.isNullOrBlank(format)) {
				if(format.indexOf(METHOD_NAME_SPLIT) != -1) {
					value = StringUtils.asString(invokeMethod(format,  value )) ;
				}else  {
					Converter convert = getConverter(format);
					if (convert != null)
						value = convert.convert(value);
				}
				map.put(column, value);
			} else {
				map.put(column, value);
			}
		}
		return map;
	}

	/**
	 * 导出时使用
	 * @param data
	 *            必须是map或者JAVABEAN
	 * @param key
	 *            必须是map的key值或者JAVABEAN属性
	 * @return
	 */
	protected Object[] getRow(Object data, List<? extends CsvField> fields) {
		List<Object> row = new ArrayList<Object>(fields.size());
		for (CsvField csvField : fields) {
			String type = csvField.getType();
			String format = csvField.getFormat();
			Object val = getProperty(data, csvField.getName());
			// 日期格式转化
			if (CsvTable.DATE.equals(type)) {
				val = DateUtils.format((Date) val, CsvTable.DATE_FORMAT);
			}

			if (CsvTable.DATE_TIME.equals(type)) {
				val = DateUtils.format((Date) val, CsvTable.DATE_TIME_FORMAT);
			}

			// 数字格式转化
			if (CsvTable.NUMBER.equals(type)) {
				if (!StringUtils.isNullOrBlank(format)) {
					DecimalFormat df1 = new DecimalFormat(format);
					val = df1.format(val);
				}
			}
			// 自定义转化
			if (CsvTable.CUSTOM.equals(type)) {
				if (!StringUtils.isNullOrBlank(format)) {
					if(format.indexOf(METHOD_NAME_SPLIT) != -1) {
						val = invokeMethod(format,  val  );
					}else {
						Converter convert = getConverter(format);
						if (convert != null) {
							val = convert.convert(val);
						}
					}
				}
			}
			//http://www.oschina.net/code/snippet_177666_21979
			//解决 excle 打开csv文件时数字自动变文本格式
			//解决办法： 
			//   1、在生成csv的时候,在数字的前面或后面加上"\t"制表符，再用excel打开问题解决！如 “1234567890	”
			//   2、在生成csv的时候,在数字的前面加上"="，再用excel打开问题解决！如 ="9876543210",=“1234567890”,
			String val0 = StringUtils.asString(val);
			if(val0.matches("^[0-9]{1,}$")) {
				val = String.format("=\"%s\"", val0) ;
			}
			row.add(val);
		}
		return row.toArray();
	}
	
	/**
	 * 是否含有表头信息
	 *
	 * @param tableId
	 * @return
	 */
	protected boolean getHeaderEnabled(String tableId) {
		CsvTable table = csvConfig.getCsvTable(tableId);
		Assert.notNull(table, tableId + "  对应的CSV配置信息不存在");
		return table.getHeaderEnabled();
	}

	/**
	 * @param data
	 *            必须是map或者JAVABEAN
	 * @param key
	 *            必须是map的key值或者JAVABEAN属性
	 * @return
	 */
	protected Object getProperty(Object data, String key) {
		Object str;
		try {
			str = PropertyUtils.getProperty(data, key);
			return str;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 *
	 * 按CSVCONFIG配置文件的验证规则将不符合规则的记录去除
	 *
	 * @param records
	 * @param tableId
	 */
	protected void extraValidRecord(List<String[]> records, String tableId) {
		List<? extends CsvField> fields = csvConfig.getImportedCsvFields(tableId);
		List<String[]> removeList = new ArrayList<String[]>();
		//无法导入的数据集
		List<Object[]> invalidDataList = new ArrayList<Object[]>();
		for (int i = 0; i < records.size(); i++) {
			String[] columnVal = records.get(i);
			if (!isValid(columnVal, fields)) {
				logger.error("不合规则的数据>>"
						+ Collections3.convertToString(
								Arrays.asList(columnVal), ","));
				removeList.add(columnVal);
				
				//将要记录到异常数据文件中的行数据
				List<Object> newColumnValList = new ArrayList<Object>(
						columnVal.length + 1);
				newColumnValList.addAll(Arrays.asList(columnVal));
				newColumnValList.add(getErrmsg());
				invalidDataList.add(newColumnValList.toArray());
			}
		}
		records.removeAll(removeList);
		//异常数据写入文件
		writeInvalidDataFile(invalidDataList,tableId);
	}
	
	/**
	 * 写入异常数据
	 * @param invalidDataList
	 * @param tableId
	 */
	protected void writeInvalidDataFile(List<Object[]> invalidDataList,  String tableId) {
		List<Object[]> rows = new ArrayList<Object[]>();
		if (getHeaderEnabled(tableId)) {
			List<? extends CsvField> importedFields = csvConfig
					.getImportedCsvFields(tableId);
			List<Object> header = new ArrayList<Object>();
			for (CsvField csvField : importedFields) {
				header.add(csvField.getDesc());
			}
			header.add("导入失败原因");
			rows.add(header.toArray());
		}
		rows.addAll(invalidDataList);
		File tmpFile = createTmpFile();
		File file = parser.writeRows(rows,tmpFile.getAbsolutePath()); 
		setInvalidDataFile(file);
	}
	
	/**
	 * 创建存储异常数据的临时文件
	 * @return
	 */
	private File createTmpFile() {
		String filename =  UUID.randomUUID().toString() ;
		File tmpFile = null;
		RequestAttributes requestAttributes = RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
			HttpServletRequest request = servletRequestAttributes.getRequest();
			File tempDir = WebUtils.getTempDir(request.getServletContext());
			tmpFile = new File(tempDir, filename+ ".csv");
		}else {
			try {
				tmpFile = File.createTempFile(filename,  ".csv");
			} catch (IOException e) {
				 logger.error("创建临时文件失败！");
			}
		}
		return tmpFile;
	}

	private static  ThreadLocal<File> invalidDataFile = new ThreadLocal<File>();
	
	private static  ThreadLocal<String> errmsg = new ThreadLocal<String>();
	
	public static  void  setInvalidDataFile(File datafile) {
		invalidDataFile.set(datafile);
	}

	public static  File  getInvalidDataFile() {
		File dataFile = invalidDataFile.get();
		invalidDataFile.remove();
		return dataFile;
	}

	public static  void  setErrmsg(String errorMsg) {
		 errmsg.set(errorMsg);
	}

	public static  String  getErrmsg() {
		String errorMsg = errmsg.get();
		errmsg.remove();
		return errorMsg;
	}



	/**
	 * 单条记录的验证
	 *
	 * @param columnVal
	 * @param fields
	 * @return
	 */
	protected boolean isValid(String[] columnVal,
			List<? extends CsvField> fields) {
		String errorMsg =null;
		for (int i = 0; i < fields.size(); i++) {
			CsvField csvField = fields.get(i);
			String value = i < columnVal.length ? columnVal[i] : null;
			boolean required = csvField.isRequired();
			if ( required ) {
				if (GenericValidator.isBlankOrNull(value)) {
					errorMsg = csvField.getDesc() + "不可为空";
					errmsg.set(errorMsg);
					return false;
				}

			}

			// 字段是否符合数据类型的格式要求
			String type = csvField.getType();
			String validator = csvField.getValidator();
			if (!StringUtils.isNullOrBlank(value)&&CsvTable.EMAIL.equals(type) && !GenericValidator.isEmail(value)) {
				errorMsg = csvField.getDesc() + "邮箱格式不合法";
				errmsg.set(errorMsg);
				return false;
			}

			if (!StringUtils.isNullOrBlank(value)&&CsvTable.URL.equals(type) && !GenericValidator.isUrl(value)) {
				errorMsg = csvField.getDesc() + "URL格式不合法";
				errmsg.set(errorMsg);
				return false;
			}
			// 数字类型的判断
			if (!StringUtils.isNullOrBlank(value)&&CsvTable.NUMBER.equals(type)
					&& !GenericValidator.isDouble(value)
					&& !GenericValidator.isLong(value)) {
				errorMsg = csvField.getDesc() + "必须是数字";
				errmsg.set(errorMsg);
				return false;
			}

			String max = csvField.getMax();
			String min = csvField.getMin();
			// 数字范围的判断
			if (CsvTable.NUMBER.equals(type)) {
				// 最大值判断
				if (!StringUtils.isNullOrBlank(value)
						&& !StringUtils.isNullOrBlank(max)
						&& !GenericValidator.maxValue(Double.valueOf(value),
								Double.valueOf(max))) {
					errorMsg = csvField.getDesc() + "必须是数字，不可大于" +Double.valueOf(max) ;
					errmsg.set(errorMsg);
					return false;
				}
				// 最小值判断
				if (!StringUtils.isNullOrBlank(value)
						&& !StringUtils.isNullOrBlank(min)
						&& !GenericValidator.minValue(Double.valueOf(value),
								Double.valueOf(min))) {
					errorMsg = csvField.getDesc() + "必须是数字，不可小于" +Double.valueOf(min) ;
					errmsg.set(errorMsg);
					return false;
				}

			}

			// 日期格式的判断
			if (!StringUtils.isNullOrBlank(value)
					&& CsvTable.DATE.equals(type)
					&& !GenericValidator.isDate(toDateString(value), CsvTable.DATE_FORMAT,
							true)) {
				errorMsg = csvField.getDesc() + "日期格式为：" +CsvTable.DATE_FORMAT ;
				errmsg.set(errorMsg);
				return false;
			}

			if (!StringUtils.isNullOrBlank(value)
					&& CsvTable.DATE_TIME.equals(type)
					&& !GenericValidator.isDate(value,
							CsvTable.DATE_TIME_FORMAT, true)) {
				errorMsg = csvField.getDesc() + "日期格式为：" +CsvTable.DATE_TIME_FORMAT ;
				errmsg.set(errorMsg);
				return false;
			}

			// 字符串是否过长的判断
			String maxlength = csvField.getMaxLength();
			if (!StringUtils.isNullOrBlank(value)&&CsvTable.STRING.equals(type)
					&& !GenericValidator.isBlankOrNull(maxlength)
					&& !GenericValidator.maxLength(value,
							Integer.valueOf(maxlength))) {
				errorMsg = csvField.getDesc() + "字符串过长，最长为：" + Integer.valueOf(maxlength) ;
				errmsg.set(errorMsg);
				return false;
			}

			//自定义校验规则
			if (!GenericValidator.isBlankOrNull(validator)) {
				if(!customValidate(validator,value)) {
					return false;
				}
			}
		}
		return true;
	}
	
	protected boolean customValidate(String validator ,Object value ) {
		Object isValid = invokeMethod(validator, value);
		Assert.isTrue("true".equals(isValid)||"false".equals(isValid)||isValid instanceof Boolean,validator+ "执行结果必须是 true 或者 false ");
		if(isValid instanceof Boolean)
			return (Boolean)isValid;
		return Boolean.valueOf(isValid.toString()) ;
	}

	String toDateString(String str) {
		String[] ymd = str.split("/");
		if(ymd.length==3) {
			 return ymd[0]+"-"+ (ymd[1].length()==1?"0"+ymd[1]:ymd[1])+"-"+ (ymd[2].length()==1?"0"+ymd[2]:ymd[2]);
		}
		return str;
	}


	//##################使用Converter接口实现类来进行数据转化########################
	/**
	 * 获取数据转化器，默认根据clz名字newInstance,否则尝试从spring中获取
	 *
	 * @param clz
	 *            (className 或者springbeanId)
	 * @return
	 */
	protected Converter getConverter(String clz) {
		try {
//			String clzName = (clz.indexOf(".") == -1) ? (DEF_PACKAGE + "." + clz)
//					: clz;
			Object convert = findClass(clz).newInstance();
			return (Converter) convert;
		} catch (Exception e) {
			try {
				return SpringUtils.getBean(clz );
			} catch (Exception e1) {
				return null;
			}
		}
	}

	//################### 使用类静态方法来进行数据转化的相关代码####################

	public static final String METHOD_NAME_SPLIT = "!";

	private String[] packagesToScan = {"com.hollycrm"} ;

	//数据转化方法对应的类（类上必须标注@CsvConverter）集合
	Set<Class<?>> converterClasses ;

	private Object invokeMethod(String methodName, Object param) {
		String[] clzMethod = methodName.split(METHOD_NAME_SPLIT);
		Class clz = findClass(clzMethod[0]);
		Assert.notNull(clz, clzMethod[0]+"无法找到匹配的类");
		Method method = MethodUtils.getAccessibleMethod(clz, clzMethod[1] , Object.class);
		if (Modifier.isStatic(method.getModifiers())) {
			return invokeStaticMethod(methodName,param);
		} else {
			return invokeObjectMethod(methodName,param);
		}
	}

	/**
	 * 使用类静态方法来进行数据转化
	 * @param methodName
	 * @param param
	 * @return
	 */
	private Object invokeStaticMethod(String methodName, Object param) {
		String[] clzMethod = methodName.split(METHOD_NAME_SPLIT);
		try {
			Class clz = findClass(clzMethod[0]);
			logger.debug(clz.getName() +"执行静态方法"+clzMethod[1] +" ,入参 :"+param);
			return MethodUtils.invokeStaticMethod(clz, clzMethod[1], param==null?"":param);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new IllegalArgumentException(methodName + "对应的类方法执行失败！" +e.getMessage());
		}
	}

	/**
	 * 使用对象实例方法来进行数据转化
	 * @param methodName
	 * @param param
	 * @return
	 */
	private Object invokeObjectMethod(String methodName, Object param) {
		String[] clzMethod = methodName.split(METHOD_NAME_SPLIT);
		try {
			Class clz = findClass(clzMethod[0]);
			Object converter = convertThreadLocal.get() ;
			if(converter==null) {
				converter = clz.newInstance();
				logger.info("初始化convertThreadLocal中的列值转化对象，以便下一行数据继续使用："+clz.getName()+":"+converter);
				convertThreadLocal.set(converter);
			}
			logger.debug(clz.getName() +"执行对象实例方法"+clzMethod[1] +" ,入参 :"+param);
			return MethodUtils.invokeMethod(converter, clzMethod[1], param==null?"":param);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new IllegalArgumentException(methodName + "对应的类方法执行失败！" +e.getMessage());
		}
	}

	private static ThreadLocal<Object> convertThreadLocal = new ThreadLocal<Object>();

	protected  void  removeConvertThreadLocal() {
		logger.debug("销毁convert对象 :"+convertThreadLocal.get() );
		convertThreadLocal.remove();
	}

	/**
	 * 根据配置的format书写查找静态方法所对应的类
	 * @param clzNameToSearch
	 * @return
	 */
	private Class findClass(String clzNameToSearch) {
		for (Class<?> class1 : converterClasses) {
			String clzName  =class1.getName();
			boolean ismatch = PatternMatchUtils.simpleMatch("*" + clzNameToSearch,
					clzName);
			if(ismatch) {
				logger.debug(clzNameToSearch + " 匹配的类 :" + class1.getName());
				return class1 ;
			}
		}
		return null ;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LoadPackageClasses loader = new LoadPackageClasses(packagesToScan, CsvConverter.class);
		converterClasses = loader.getClassSet();
	}

	//###################使用类静态方法来进行数据转化的相关代码####################


	public void setCsvConfig(CsvConfiguration csvConfig) {
		this.csvConfig = csvConfig;
	}

	public void setParser(AbstractParser parser) {
		this.parser = parser;
	}

	public AbstractParser getParser() {
		return parser;
	}

	public ConfigedJdbc getJdbc() {
		return jdbc;
	}

	public CsvConfiguration getCsvConfig() {
		return csvConfig;
	}

 

	public void setJdbc(ConfigedJdbc jdbc) {
		this.jdbc = jdbc;
	}

 

	public void setPackagesToScan(String... packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

}
