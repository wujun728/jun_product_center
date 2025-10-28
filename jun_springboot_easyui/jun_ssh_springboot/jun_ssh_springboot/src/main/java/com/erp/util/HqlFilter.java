package com.erp.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;

/**
 * HQL过滤器，用于添加where条件和排序，过滤结果集
 * 
 * 添加规则使用addFilter方法
 * 
 * 举例：QUERY_t#id_S_EQ = 0 //最终连接出的HQL是 and t.id = :id id的值是0通过参数传递给Dao
 * 
 * 格式说明QUERY前缀就说明要添加过滤条件
 * 
 * t#id 就是t.id
 * 
 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float
 * 
 * EQ 是操作符
 * 
 * // EQ 相等 // NE 不等 // LT 小于 // GT 大于 // LE 小于等于 // GE 大于等于 // LK 模糊 // RLK 右模糊 // LLK 左模糊
 * 
 * @author 孙宇
 * 
 */
public class HqlFilter {

	private HttpServletRequest request;// 为了获取request里面传过来的动态参数
	private Map<String, Object> params = new HashMap<String, Object>();// 条件参数
	private StringBuffer hql = new StringBuffer();
	private String sort;// 排序字段
	private String order = "asc";// asc/desc

	/**
	 * 默认构造
	 */
	public HqlFilter() {

	}

	/**
	 * 带参构造
	 * 
	 * @param request
	 */
	public HqlFilter(HttpServletRequest request) {
		this.request = request;
		addFilter(request);
	}

	/**
	 * 添加排序字段
	 * 
	 * @param sort
	 */
	public void addSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 添加排序方法，默认asc升序
	 * 
	 * @param order
	 */
	public void addOrder(String order) {
		this.order = order;
	}

	/**
	 * 转换SQL操作符
	 * 
	 * @param operator
	 * @return
	 */
	private String getSqlOperator(String operator) {
		String o = operator.trim().toUpperCase();
		if (o.equals("EQ")) {
			return " = ";
		}
		if (o.equals("NE")) {
			return " != ";
		}
		if (o.equals("LT")) {
			return " < ";
		}
		if (o.equals("GT")) {
			return " > ";
		}
		if (o.equals("LE")) {
			return " <= ";
		}
		if (o.equals("GE")) {
			return " >= ";
		}
		if (o.equals("LK") || o.equals("RLK") || o.equals("LLK")) {
			return " like ";
		}
		return "";
	}

	/**
	 * 获得添加过滤字段后的HQL
	 * 
	 * @return
	 */
	public String getWhereHql() {
		return hql.toString();
	}

	/**
	 * 获得添加过滤字段后加上排序字段的HQL
	 * 
	 * @return
	 */
	public String getWhereAndOrderHql() {
		if (sort != null && order != null) {
			if (sort.indexOf(".") < 1) {
				sort = "t." + sort;
			}
			hql.append(" order by " + sort + " " + order + " ");// 添加排序信息
		} else {
			if (request != null) {
				String s = request.getParameter("sort");
				String o = request.getParameter("order");
				if (s != null) {
					sort = s;
				}
				if (o != null) {
					order = o;
				}
				if (sort != null && order != null) {
					if (sort.indexOf(".") < 1) {
						sort = "t." + sort;
					}
					hql.append(" order by " + sort + " " + order + " ");// 添加排序信息
				}
			}
		}
		return hql.toString();
	}

	/**
	 * 获得过滤字段参数和值
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * 添加过滤
	 * 
	 * @param request
	 */
	public void addFilter(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			addFilter(name, value);
		}
	}

	/**
	 * 添加过滤
	 * 
	 * 举例，name传递：QUERY_t#id_S_EQ
	 * 
	 * 举例，value传递：0
	 * 
	 * @param params
	 */
	public void addFilter(String name, String value) {
		if (name != null && value != null) {
			if (name.startsWith("QUERY_")) {// 如果有需要过滤的字段
				String columnName = name.substring(name.indexOf("_") + 1, name.indexOf("_", 6)).replaceAll("#", ".");// 字段名
				String valueType = name.substring(name.indexOf("_", 6) + 1, name.lastIndexOf("_"));// 字段类型
				String operator = name.substring(name.lastIndexOf("_") + 1);// 操作符
				String placeholder = UUID.randomUUID().toString().replace("-", "");// 生成一个随机的参数名称

				if (hql.toString().indexOf(" where 1=1") < 0) {
					hql.append("  where 1=1 ");
				}

				hql.append(" and " + columnName + " " + getSqlOperator(operator) + " :c" + placeholder + " ");// 拼HQL
				params.put("c" + placeholder, getObjValue(valueType, operator, value));// 添加参数
			}
		}
	}

	/**
	 * 将String值转换成Object
	 * 
	 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float
	 * 
	 * @param valueType
	 * @param operator
	 * @param value
	 * @return
	 */
	private Object getObjValue(String valueType, String operator, String value) {
		if (valueType.equals("S")) {
			if (operator.equals("LK")) {
				value = "%%" + value + "%%";
			} else if (operator.equals("RLK")) {
				value = value + "%%";
			} else if (operator.equals("LLK")) {
				value = "%%" + value;
			}
			return value;
		}
		if (valueType.equals("L")) {
			return Long.parseLong(value);
		}
		if (valueType.equals("I")) {
			return Integer.parseInt(value);
		}
		if (valueType.equals("D")) {
			try {
				return DateUtils.parseDate(value, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm" });
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (valueType.equals("ST")) {
			return Short.parseShort(value);
		}
		if (valueType.equals("BD")) {
			return BigDecimal.valueOf(Long.parseLong(value));
		}
		if (valueType.equals("FT")) {
			return Float.parseFloat(value);
		}
		return null;
	}

}
