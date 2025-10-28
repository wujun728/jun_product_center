package sy.util.base;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;

/**
 * 
 * 添加查询条件工具，可以先参考TestQueryFilter类来查看怎样使用
 * 
 * 此类还包含了排序、分页、预先抓取参数设置功能
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class QueryFilter {

	private Integer page;// 要查找第几页
	private Integer pageSize;// 每页显示多少条

	private String sort;// 排序 举例：sort:sendDate desc,orderNum desc,vin desc

	private String[] joinFetch;// 预先抓取属性

	private String[] join;// 关联属性

	private List<Object[]> params = new ArrayList<Object[]>();// 需要过滤的条件，param里面包含字段名称、操作符、字段值

	private Criteria cri = Cnd.cri();// 利用nutz dao的查询条件

	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String[] getJoinFetch() {
		return joinFetch;
	}

	public void setJoinFetch(String[] joinFetch) {
		this.joinFetch = joinFetch;
	}

	public String[] getJoin() {
		return join;
	}

	public void setJoin(String[] join) {
		this.join = join;
	}

	public void setParams(List<Object[]> params) {
		this.params = params;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public QueryFilter() {

	}

	/**
	 * 将前台页面传递过来的参数进行判断，加入条件过滤里面
	 * 
	 * @param request
	 */
	public QueryFilter(HttpServletRequest request) {
		this.request = request;
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = StringUtils.trim(request.getParameter(name));
			if (StringUtils.isNotBlank(value)) {

				addFilter(name, value);

				if (name.equalsIgnoreCase("page")) {
					this.setPage(Integer.parseInt(value));
				}
				if (name.equalsIgnoreCase("pageSize")) {
					this.setPageSize(Integer.parseInt(value));
				}
				if (name.equalsIgnoreCase("sort")) {
					this.setSort(value);
				}
				if (name.equalsIgnoreCase("order") && StringUtils.isNotBlank(this.getSort())) {// 有的前台datagrid插件，传递排序的时候是传递sort和order两个参数
					this.setSort(this.getSort() + " " + value);// 所以这里适配一下其他插件的用法
				}
				if (name.equalsIgnoreCase("joinFetch")) {
					this.setJoinFetch(value.replaceAll(" ", "").split(","));
				}
				if (name.equalsIgnoreCase("join")) {
					this.setJoin(value.replaceAll(" ", "").split(","));
				}
			}
		}
	}

	/**
	 * 添加一个条件过滤
	 * 
	 * 如果name是Q_开头，并且参数可以分为3段或者4段，才添加过滤
	 * 
	 * 第二段是要过滤的属性，第三段是操作符，第四段是参数类型
	 * 
	 * @param name
	 * @param value
	 */
	public void addFilter(String name, String value) {
		if (name.toUpperCase().indexOf("Q_") > -1) {
			String[] commands = name.split("_");
			if (commands.length == 3 || commands.length == 4) {
				// System.out.println(Json.toJson(commands));
				if (commands[2].equalsIgnoreCase("like")) {// like操作符
					Object[] param = new Object[] { commands[1], "LIKE", "%%" + value + "%%" };
					this.params.add(param);
				}
				if (commands[2].equalsIgnoreCase("leftLike")) {// leftlike左模糊操作符
					Object[] param = new Object[] { commands[1], "LIKE", "%%" + value + "" };
					this.params.add(param);
				}
				if (commands[2].equalsIgnoreCase("rightLike")) {// rightlike右模糊操作符
					Object[] param = new Object[] { commands[1], "LIKE", "" + value + "%%" };
					this.params.add(param);
				}
				if (commands[2].equalsIgnoreCase("notLike")) {// not like操作符
					Object[] param = new Object[] { commands[1], "NOT LIKE", "%%" + value + "%%" };
					this.params.add(param);
				}
				if (commands[2].equalsIgnoreCase("notLeftLike")) {// not leftlike左模糊操作符
					Object[] param = new Object[] { commands[1], "NOT LIKE", "%%" + value + "" };
					this.params.add(param);
				}
				if (commands[2].equalsIgnoreCase("notRightLike")) {// not rightlike右模糊操作符
					Object[] param = new Object[] { commands[1], "NOT LIKE", "" + value + "%%" };
					this.params.add(param);
				}
				if ((commands[2].equalsIgnoreCase("=") || commands[2].equalsIgnoreCase("EQ")) && commands.length == 4) {// 等号操作符
					Object _value = value;
					if (commands[3].equalsIgnoreCase("Long")) {
						_value = Long.parseLong(value);
					}
					if (commands[3].equalsIgnoreCase("Short")) {
						_value = Short.parseShort(value);
					}
					if (commands[3].equalsIgnoreCase("Integer") || commands[3].equalsIgnoreCase("int")) {
						_value = Integer.parseInt(value);
					}
					if (commands[3].equalsIgnoreCase("Date")) {
						_value = DateUtil.stringToDate(value, new String[] { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd" });
					}
					if (commands[3].equalsIgnoreCase("DateTime")) {
						_value = DateUtil.stringToDate(value);
					}
					Object[] param = new Object[] { commands[1], "=", _value };
					this.params.add(param);
				}
				if ((commands[2].equalsIgnoreCase("!=") || commands[2].equalsIgnoreCase("NE")) && commands.length == 4) {// 等号操作符
					Object _value = value;
					if (commands[3].equalsIgnoreCase("Long")) {
						_value = Long.parseLong(value);
					}
					if (commands[3].equalsIgnoreCase("Short")) {
						_value = Short.parseShort(value);
					}
					if (commands[3].equalsIgnoreCase("Integer") || commands[3].equalsIgnoreCase("int")) {
						_value = Integer.parseInt(value);
					}
					if (commands[3].equalsIgnoreCase("Date")) {
						_value = DateUtil.stringToDate(value, new String[] { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd" });
					}
					if (commands[3].equalsIgnoreCase("DateTime")) {
						_value = DateUtil.stringToDate(value);
					}
					Object[] param = new Object[] { commands[1], "!=", _value };
					this.params.add(param);
				}
				if ((commands[2].equalsIgnoreCase(">") || commands[2].equalsIgnoreCase("GT")) && commands.length == 4) {// 大于操作符
					Object _value = value;
					if (commands[3].equalsIgnoreCase("Long")) {
						_value = Long.parseLong(value);
					}
					if (commands[3].equalsIgnoreCase("Short")) {
						_value = Short.parseShort(value);
					}
					if (commands[3].equalsIgnoreCase("Integer") || commands[3].equalsIgnoreCase("int")) {
						_value = Integer.parseInt(value);
					}
					if (commands[3].equalsIgnoreCase("Date")) {
						_value = DateUtil.stringToDate(value, new String[] { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd" });
					}
					if (commands[3].equalsIgnoreCase("DateTime")) {
						_value = DateUtil.stringToDate(value);
					}
					Object[] param = new Object[] { commands[1], ">", _value };
					this.params.add(param);
				}
				if ((commands[2].equalsIgnoreCase(">=") || commands[2].equalsIgnoreCase("GE")) && commands.length == 4) {// 大于等于操作符
					Object _value = value;
					if (commands[3].equalsIgnoreCase("Long")) {
						_value = Long.parseLong(value);
					}
					if (commands[3].equalsIgnoreCase("Short")) {
						_value = Short.parseShort(value);
					}
					if (commands[3].equalsIgnoreCase("Integer") || commands[3].equalsIgnoreCase("int")) {
						_value = Integer.parseInt(value);
					}
					if (commands[3].equalsIgnoreCase("Date")) {
						_value = DateUtil.stringToDate(value, new String[] { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd" });
					}
					if (commands[3].equalsIgnoreCase("DateTime")) {
						_value = DateUtil.stringToDate(value);
					}
					Object[] param = new Object[] { commands[1], ">=", _value };
					this.params.add(param);
				}
				if ((commands[2].equalsIgnoreCase("<") || commands[2].equalsIgnoreCase("LT")) && commands.length == 4) {// 小于操作符
					Object _value = value;
					if (commands[3].equalsIgnoreCase("Long")) {
						_value = Long.parseLong(value);
					}
					if (commands[3].equalsIgnoreCase("Short")) {
						_value = Short.parseShort(value);
					}
					if (commands[3].equalsIgnoreCase("Integer") || commands[3].equalsIgnoreCase("int")) {
						_value = Integer.parseInt(value);
					}
					if (commands[3].equalsIgnoreCase("Date")) {
						_value = DateUtil.stringToDate(value, new String[] { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd" });
					}
					if (commands[3].equalsIgnoreCase("DateTime")) {
						_value = DateUtil.stringToDate(value);
					}
					Object[] param = new Object[] { commands[1], "<", _value };
					this.params.add(param);
				}
				if ((commands[2].equalsIgnoreCase("<=") || commands[2].equalsIgnoreCase("LE")) && commands.length == 4) {// 小于等于操作符
					Object _value = value;
					if (commands[3].equalsIgnoreCase("Long")) {
						_value = Long.parseLong(value);
					}
					if (commands[3].equalsIgnoreCase("Short")) {
						_value = Short.parseShort(value);
					}
					if (commands[3].equalsIgnoreCase("Integer") || commands[3].equalsIgnoreCase("int")) {
						_value = Integer.parseInt(value);
					}
					if (commands[3].equalsIgnoreCase("Date")) {
						_value = DateUtil.stringToDate(value, new String[] { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd" });
					}
					if (commands[3].equalsIgnoreCase("DateTime")) {
						_value = DateUtil.stringToDate(value);
					}
					Object[] param = new Object[] { commands[1], "<=", _value };
					this.params.add(param);
				}
				if (commands[2].equalsIgnoreCase("In") && commands.length == 4) {// in操作符
					Object _value = value;
					if (commands[3].equalsIgnoreCase("Long")) {
						List<Long> values = new ArrayList<Long>();
						for (String v : value.split(",")) {
							values.add(Long.parseLong(v));
						}
						_value = values;
					}
					if (commands[3].equalsIgnoreCase("Short")) {
						List<Short> values = new ArrayList<Short>();
						for (String v : value.split(",")) {
							values.add(Short.parseShort(v));
						}
						_value = values;
					}
					if (commands[3].equalsIgnoreCase("Integer") || commands[3].equalsIgnoreCase("int")) {
						List<Integer> values = new ArrayList<Integer>();
						for (String v : value.split(",")) {
							values.add(Integer.parseInt(v));
						}
						_value = values;
					}
					if (commands[3].equalsIgnoreCase("String")) {
						List<String> values = new ArrayList<String>();
						for (String v : value.split(",")) {
							values.add(v);
						}
						_value = values;
					}
					Object[] param = new Object[] { commands[1], "IN", _value };
					this.params.add(param);
				}
				if (commands[2].equalsIgnoreCase("notIn") && commands.length == 4) {// not in操作符
					Object _value = value;
					if (commands[3].equalsIgnoreCase("Long")) {
						List<Long> values = new ArrayList<Long>();
						for (String v : value.split(",")) {
							values.add(Long.parseLong(v));
						}
						_value = values;
					}
					if (commands[3].equalsIgnoreCase("Short")) {
						List<Short> values = new ArrayList<Short>();
						for (String v : value.split(",")) {
							values.add(Short.parseShort(v));
						}
						_value = values;
					}
					if (commands[3].equalsIgnoreCase("Integer") || commands[3].equalsIgnoreCase("int")) {
						List<Integer> values = new ArrayList<Integer>();
						for (String v : value.split(",")) {
							values.add(Integer.parseInt(v));
						}
						_value = values;
					}
					if (commands[3].equalsIgnoreCase("String")) {
						List<String> values = new ArrayList<String>();
						for (String v : value.split(",")) {
							values.add(v);
						}
						_value = values;
					}
					Object[] param = new Object[] { commands[1], "NOT IN", _value };
					this.params.add(param);
				}
				if (commands[2].equalsIgnoreCase("isNull")) {// is null操作符
					Object[] param = new Object[] { commands[1], "IS NULL", null };
					this.params.add(param);
				}
				if (commands[2].equalsIgnoreCase("isNotNull")) {// is not null操作符
					Object[] param = new Object[] { commands[1], "IS NOT NULL", null };
					this.params.add(param);
				}
			}
		}
	}

	public List<Object[]> getParams() {
		return params;
	}

	public Criteria getCondition() {
		for (Object[] param : this.getParams()) {// 拼条件
			cri.where().and(param[0].toString(), param[1].toString(), param[2]);
		}
		return cri;
	}

}
