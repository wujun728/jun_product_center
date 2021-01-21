package org.myframework.support.idgenerator.service.impl;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.myframework.support.idgenerator.entities.CurrentMax;
import org.myframework.support.idgenerator.entities.Series;
import org.myframework.support.idgenerator.entities.CurrentMax.CurrentMaxPK;
import org.myframework.support.idgenerator.service.ID;
import org.myframework.support.idgenerator.service.IDUtil;
import org.myframework.support.idgenerator.util.IDGeneratorException;
import org.springframework.stereotype.Service;

@Service("idGenerator")
public class IDImpl implements ID {
	private long randNum = System.currentTimeMillis();

	public IDImpl() {
		
	}

	private Map<String,Series> seriesCache = new HashMap<String,Series>();
	
	/**
	 * 取下一个序列号(主键流水号)
	 * 
	 * @param seriesID
	 *            String
	 * @param arguments
	 *            String[]
	 * @return String
	 */
	public String getNext(String seriesID, String[] arguments) {
		if (seriesID == null) {
			throw new IDGeneratorException("参数seriesID的值不能为空", "407100000");
		}
		Series series = getSeries(seriesID);
		if (series == null) {
			throw new IDGeneratorException("未发现此流水号的定义", "407100001");
		}
		// 当前最大的number 字符容器型
		StringBuffer numberString = new StringBuffer();
		// 当前最大的number长度；缺省是10
		int length = 10;
		// 结果字符串
		List<Object> result = new ArrayList<Object>();
		// 最大号的maxID。这个值不是id值，而是id的标识。
		String maxID = "";
		int numNum = 0;
		// 规则字符串
		String rule = series.getRule();
		if (rule == null || "".equals(rule.trim())) {
			throw new IDGeneratorException("规则不能为空", "407100002");
		} else {
			try {
				Document doc = DocumentHelper.parseText(rule);
				Element root = doc.getRootElement();
				// 参数个数
				int argNum = 0;
				// 序列号个数

				for (Iterator<?> iter = root.elementIterator(); iter
						.hasNext();) {
					Element e = (Element) iter.next();
					// 元素名称
					String name = e.getName();
					if ("solidString".equals(name)) {
						String content = e.attribute("content").getValue();
						maxID = maxID + content;
						result.add(content);
					} else if ("argument".equals(name)) {
						if (arguments == null || arguments.length <= argNum) {
							throw new IDGeneratorException("参数数量不够",
									"407100003");
						}
						maxID = maxID + arguments[argNum];
						result.add(arguments[argNum]);
						argNum++;
					} else if ("argument-invisible".equals(name)) {
						if (arguments == null || arguments.length <= argNum) {
							throw new IDGeneratorException("参数数量不够",
									"407100003");
						}
						maxID = maxID + arguments[argNum];
						argNum++;
					} else if ("num".equals(name)) {
						if (numNum > 1) {
							throw new IDGeneratorException("规则定义中的序列数不能多于一个",
									"407100004");
						}
						numNum++;
						length = Integer
								.parseInt(e.attribute("length").getValue());
						result.add(numberString);
					} else if ("randNum".equals(name)) {
						int len = Integer
								.parseInt(e.attribute("length").getValue());
						String randStr = String.valueOf(randNum++);
						if (len < randStr.length())
							result.add(
									randStr.substring(randStr.length() - len));
						else {
							StringBuffer buff = new StringBuffer(randStr);
							for (int i = 0; i < len - randStr.length(); i++) {
								buff.append('0');
							}
							result.add(buff.toString());
						}
					} else if ("date".equals(name)) {
						String pattern = e.attribute("format").getValue();
						try {
							Format f = new SimpleDateFormat(pattern);
							String date = f.format(new Date());
							maxID = maxID + date;
							result.add(date);
						} catch (RuntimeException re) {
							throw new IDGeneratorException("规则定义中的日期格式不正确",
									"407100005", re);
						}
					}
				}
			} catch (DocumentException e) {
				throw new IDGeneratorException("解析流水号规则失败", "407100006", e);
			} catch (IDGeneratorException e) {
				throw e;
			} catch (RuntimeException e) {
				throw new IDGeneratorException(
						"应用流水号生成规则时失败,原因:流水号规则定义未遵守流水号规范", "407100007", e);
			}
		}
		// 如果规则为空,则maxID = "*"
		if ("".equals(maxID)) {
			maxID = "*";
		}
		if (numNum > 0) {
			// 新的当前最大号
			BigDecimal newMaxNumber = genNextNumber(seriesID, maxID);
			// 转化为固定长度的字符串
			numberString.append(
					StringUtils.leftPad(newMaxNumber.toString(), length, '0'));
		}
		// 新的主键
		StringBuffer newID = new StringBuffer();
		for (Iterator<?> iter = result.iterator(); iter.hasNext();) {
			newID.append(iter.next());
		}
		return newID.toString();
	}

	/**
	 * 生成一个新的最大号，此方法需要在一个新的事物方法中运行 如果想避免插入数据并发冲突，可以对series加锁，不过性能将大受影响
	 * 
	 * @param series
	 *            Series
	 * @param maxID
	 *            <any>
	 * @return BigDecimal
	 */
	public BigDecimal genNextNumber(String sequenceId, String maxID) {
		BigDecimal newValue;
		boolean isNew = false;
		// CurrentMax cm = service.getCurrentMaxForced(series, maxID);
		CurrentMaxPK pk = util.genCurrentMaxPK(sequenceId, maxID);
		CurrentMax cm = util.getCurrentMax(pk);
		if (cm == null) {
			isNew = true;
			cm = util.genCurrentMax(pk);
		}
		BigDecimal oldValue = cm.getValue();
		if (oldValue == null) {
			oldValue = new BigDecimal("0");
		}
		newValue = oldValue.abs().setScale(0, BigDecimal.ROUND_UP)
				.add(new java.math.BigDecimal("1"));
		cm.setValue(newValue);
		// service.saveCurrentMax(cm);
		if (isNew) {
			try {
				util.createCurrentMax(cm);
			} catch (RuntimeException e) {
				if (util.getCurrentMax(pk) != null) // 是主键重复，则迭代此函数
					newValue = genNextNumber(sequenceId, maxID);
				else
					throw e;
			}
		} else
			util.updateCurrentMax(cm);
		return newValue;
	}

	/**
	 * 取流水号定义，用cache缓存
	 * 
	 * @param seriesID
	 *            String
	 * @return Series
	 */
	public Series getSeries(String seriesID) {
		Series series =   seriesCache.get(  seriesID);
		if (series == null) {
			series = util.getSeries(seriesID);
			seriesCache.put(seriesID, series);
		}
		return series;
	}

 

	/**
	 * dao工具类
	 */
	@Resource(name = "idUtil")
	private IDUtil util;

	public final String CACHE = "IDCache";
}
