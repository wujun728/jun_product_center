package org.myframework.support.echarts.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.myframework.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

/**
 * 
 * <ol>将原始数据行的内容进行分析抽取出需要的数据集
 * <li>传入原始数据，数据格式必须包含3列(LEGEND, CATEGORY,VALUE)分别对应echart的图例,分类,统计值
 * <li>分析原始数据，得出categoryData，legendData，keyValData，strData
 *  分别对应图例列表,分类列表,统计值列表1(name,value格式的)，统计值列表2(value格式的)</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年11月23日
 *
 */
public class DataEtl {
	
	
	private static final Logger logger = LoggerFactory.getLogger(DataEtl.class);
	
	//翻译echart option需要使用到的上下文参数名
	public static final String CATEGORY_DATA = "categoryData";
	public static final String LEGEND_DATA = "legendData";
	public static final String KEYVAL_DATA = "keyValData";
	public static final String STR_DATA = "strData";

	//统计数据列名
	public static final String VALUE = "VALUE";
	public static final String CATEGORY = "CATEGORY";
	public static final String LEGEND = "LEGEND";
	
	//echart关键字
	public static final String ECHARTS_VALUE = "value";
	public static final String ECHARTS_NAME = "name";
	/**
	 * @return
	 */
	public Map<String, Object> getContext(List<Map<String, Object>> data) {
		Map<String, Object> context = new HashMap<String, Object>();

		List<String> categorys = getUniqueFieldList(data, CATEGORY);
		List<String> legends = getUniqueFieldList(data, LEGEND);
		logger.debug("  categorys : " + categorys);
		logger.debug("  legends : " + legends);

		context.put(LEGEND_DATA, JSONArray.toJSON (legends));
		context.put(CATEGORY_DATA, JSONArray.toJSON (categorys));
		
		List<Object> strData =  new ArrayList<Object>();  		
		List<Object> keyValData =  new ArrayList<Object>();
		for (String legend : legends) {
			List<String> data0 = qryStringListByLegendOrderByCategory(data,
					legend, categorys);
			List<Map<String, Object>> data1 = qryMapListByLegendOrderByCategory(
					data, legend, categorys);
			logger.debug("=====================================");
			logger.debug("legend : " + legend);
			logger.debug("  data : " + data0);
			strData.add(JSONArray.toJSON(data0));
			keyValData.add(JSONArray.toJSON(data1));
		}
		context.put(STR_DATA,JSONArray.toJSON(strData));
		context.put(KEYVAL_DATA,JSONArray.toJSON(keyValData));
		logger.debug("return context : " + context);
		return context ;
	}
	
	/**
	 * 根据图例参数查询数据，查询出来的数据跟分类列表中的数据进行排序
	 * @see 和qryMapListByLegendOrderByCategory作用一致，不过本方法查询的数据主要用于折线图和柱状图 
	 * @param data
	 * @param legend 图例
	 * @param categorys 分类
	 * @return
	 */
	public List<String> qryStringListByLegendOrderByCategory(
			List<Map<String, Object>> data, String legend,
			List<String> categorys) {
		List<String> seriesDataList = new ArrayList<String>(categorys.size());
		for (String category : categorys) {
			for (Map<String, Object> row : data) {
				if (legend.equals(row.get(LEGEND))
						&& category.equals(row.get(CATEGORY))) {
					seriesDataList.add(StringUtils.asString(row.get(VALUE)));
				}
			}
		}
		return seriesDataList;
	}

	/**
	 * 根据图例参数查询数据，查询出来的数据跟分类列表中的数据进行排序
	 * @param data 
	 * @param legend 图例
	 * @param categorys 分类
	 * @return
	 */
	public List<Map<String, Object>> qryMapListByLegendOrderByCategory(
			List<Map<String, Object>> data, String legend,
			List<String> categorys) {
		List<Map<String, Object>> seriesDataList = new ArrayList<Map<String, Object>>(
				categorys.size());
		for (String category : categorys) {
			for (Map<String, Object> row : data) {
				if (legend.equals(row.get(LEGEND))
						&& category.equals(row.get(CATEGORY))) {
					Map<String, Object> serieData = new HashMap<String, Object>();
					serieData.put(ECHARTS_NAME, category);
					serieData.put(ECHARTS_VALUE,row.get(VALUE) != null ? row.get(VALUE).toString() : "-"  );
					seriesDataList.add(serieData);
				}
			}
		}
		return seriesDataList;
	}


	/**
	 * 获取数组中某一列的值
	 * @param data
	 * @param fieldName
	 * @return
	 */
	public List<String> getFieldList(List<Map<String, Object>> data,
			String fieldName) {
		List<String> coList = new ArrayList<String>(data.size());
		for (Map<String, Object> map : data) {
			coList.add(StringUtils.asString(map.get(fieldName)));
		}
		return coList;
	}

	/**
	 * 统计数据列值的排重获取
	 * @param data
	 * @param fieldName
	 * @return
	 */
	public List<String> getUniqueFieldList(List<Map<String, Object>> data,
			String fieldName) {
		List<String> rows = getFieldList(data, fieldName);
		return array_unique(rows.toArray(new String[rows.size()]));
	}

	/**
	 * 去除数组中重复的记录
	 * @param a
	 * @return
	 */
	public static List<String> array_unique(String[] a) {
		// array_unique
		List<String> list = new LinkedList<String>();
		for (int i = 0; i < a.length; i++) {
			if (!list.contains(a[i])) {
				list.add(a[i]);
			}
		}
		return list;
	}
	
}
