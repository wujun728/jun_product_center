package com.doroodo.base.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.model.DataGrid;
import com.doroodo.base.service.BaseService;
import com.doroodo.base.service.GenericsUtils;
import com.doroodo.code.provider.db.table.TableFactory;
import com.doroodo.code.provider.db.table.model.Column;
import com.doroodo.code.provider.db.table.model.Table;
import com.doroodo.code.util.StringHelper;
import com.doroodo.util.SmarteUntil;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	protected BaseDao<T> dao;

	public BaseDao<T> getDao() {
		return dao;
	}

	private String modalName = "";

	protected Class<T> entityClass;

	protected Class<?> getEntityClass() {
		return entityClass;
	}

	public BaseServiceImpl() {
		entityClass = GenericsUtils.getGenericClass(getClass());
		modalName = entityClass.getName();
		modalName = modalName.substring(modalName.lastIndexOf(".") + 1);
	}

	private String where(String fieldName, String fieldResult, String type) {
		String hql = "";
		List<String> whers = SmarteUntil.match(fieldResult, "查询", "条件");
		List<String> vals = SmarteUntil.match(fieldResult, "查询", "值");
		if ((whers.size() > 0) && (whers.size() == vals.size())) {
			if ("java.lang.integer".equalsIgnoreCase(type)) {
				for (int i = 0; i < whers.size(); i++) {
					hql += " t." + fieldName + " " + whers.get(i)
							+ Integer.parseInt(vals.get(i)) + " and";
				}
			} else {
				for (int i = 0; i < whers.size(); i++) {
					hql += " t." + fieldName + " " + whers.get(i) + "'"
							+ vals.get(i) + "' and";
				}
			}
		}
		return hql;
	}

	public Map getHqlByObject(T o) {
		String hql = "from " + modalName + " t where";
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < o.getClass().getMethods().length; i++) {
			Method f = o.getClass().getMethods()[i];
			if (f.getName().startsWith("get")
					&& f.getParameterTypes().length == 0
					&& (!f.getName().equalsIgnoreCase("getClass"))) {
				String fieldResult;
				String fieldName = "";
				try {
					fieldResult = f.invoke(o, null) == null ? "" : f.invoke(o,
							null).toString();
					for (int j = 0; j < o.getClass().getDeclaredFields().length; j++) {
						String fieldName_ = o.getClass().getDeclaredFields()[j]
								.getName();
						if (fieldName_.equalsIgnoreCase(f.getName()
								.substring(3))) {
							fieldName = fieldName_;
							break;
						}
					}

					if (!fieldResult.trim().equals("")
							&& !fieldName.trim().isEmpty()) {
						String swhere = where(fieldName, fieldResult, f
								.getReturnType().getName());
						if (swhere.isEmpty()) {
							if ("java.lang.integer".equalsIgnoreCase(f
									.getReturnType().getName())) {
								hql += " t." + fieldName + " = :" + fieldName
										+ " and";
								params.put(fieldName,
										Integer.parseInt(fieldResult));
							} else {
								hql += " t." + fieldName + " = :" + fieldName
										+ " and";
								params.put(fieldName, fieldResult);
							}
						} else {
							hql += swhere;
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		if (hql.endsWith("and")) {
			hql = hql.substring(0, hql.length() - 3);
		}
		Map<String, Object> m = new HashMap();
		m.put("hql", hql);
		m.put("params", params);
		return m;
	}

	public List<T> get(T o) {
		Map<String, Object> m = getHqlByObject(o);
		List<T> l = dao.find(m.get("hql").toString(),
				(Map<String, Object>) m.get("params"));
		return l;
	}

	public DataGrid dataGrid(int page, int rows, String searchName,
			String searchKey) {
		searchName = searchName.trim();
		searchKey = searchKey.trim();
		DataGrid dg = new DataGrid();
		String hql = "from " + modalName + " t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (!searchName.equals("") && !searchKey.equals("")) {
			TableFactory tf = TableFactory.getInstance();
			@SuppressWarnings("unchecked")
			List<Table> tblist = tf.getAllTableNames();
			Table bo = null;
			for (Table tb : tblist) {
				if (tb.getClassName().equals(modalName)) {
					bo = tf.getTable(tb.getSqlName());
				}
			}
			if (searchName.contains(",")) {
				// 妯＄硦鏌ヨ
				hql += "where ";
				String[] columnsFields = searchName.split(",");
				for (String columnsField : columnsFields) {
					if (columnsField.equalsIgnoreCase(""))
						continue;
					Column col = bo.getColumnsMap().get(columnsField);
					String map = col.getMap();
					if (!map.isEmpty() && map.split("\\|").length > 2) {

						String mapTab = map.split("\\|")[0];
						String mapField = map.split("\\|")[1];
						String mapTextField = map.split("\\|")[2];
						String mapModel = tf.getTable(mapTab).getClassName();
						if (mapTab.toLowerCase().equals("sy_sysdomain")) {
							hql += " "
									+ col.getSqlName()
									+ " in (select  fieldValue  from SySysdomain where tableName=:tableName and fieldName=:fieldName and fieldDesc like :"
									+ mapTextField + ") ";
							hql += "or";
							params.put("tableName", mapField);
							params.put("fieldName", mapTextField);
							params.put(mapTextField, "%%" + searchKey + "%%");
						} else {
							// HashMap<String ,Column>
							// tt=tf.getTable(mapTab).getColumnsMap();
							// mapField=tt.get(mapField).getSqlName();
							// mapTextField=tt.get(mapTextField).getSqlName();

							hql += " " + col.getSqlName() + " in (select "
									+ mapField + " from " + mapModel
									+ " where " + mapTextField + " like :"
									+ mapTextField + ") ";
							hql += "or";
							params.put(mapTextField, "%%" + searchKey + "%%");
						}
					} else {
						hql += " " + col.getSqlName() + " like :"
								+ col.getColumnName() + " ";
						hql += "or";
						params.put(col.getColumnName(), "%%" + searchKey + "%%");
					}
				}

				hql = hql.substring(0, hql.length() - 2);
			} else {
				Column col = bo.getColumnsMap().get(searchName);
				String map = col.getMap();
				if (!map.isEmpty() && map.split("\\|").length > 2) {
					String mapTab = map.split("\\|")[0];
					String mapField = map.split("\\|")[1];
					String mapTextField = map.split("\\|")[2];
					String mapModel = tf.getTable(mapTab).getClassName();

					if (mapTab.toLowerCase().equals("sy_sysdomain")) {
						
						hql += " where "
								+ col.getSqlName()
								+ " in (select  fieldValue  from SySysdomain where tableName=:tableName and fieldName=:fieldName and fieldDesc like :"
								+ mapTextField + ") ";
						params.put("tableName", mapField);
						params.put("fieldName", mapTextField);
						params.put(mapTextField, "%%" + searchKey + "%%");
					} else {
						hql += "where t." + searchName + " in (select "
								+ mapField + " from " + mapModel + " where "
								+ mapTextField + " like :key)";
						;
						params.put("key", "%%" + searchKey + "%%");
					}

				} else {
					hql += "where t." + searchName + " like :key";
					params.put("key", "%%" + searchKey + "%%");
				}
			}
		}
		String totalHql = "select count(*) " + hql;
		// hql+=" order by t.logDate desc";
		List<T> l = null;
		try {
			if (rows == 0 && page == 0) {
				l = dao.find(hql, params);
			} else {
				l = dao.find(hql, params, page, rows);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			l = convert(l);
		} catch (Exception e) {
			e.printStackTrace();
		}

		dg.setTotal(dao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName(StringHelper.uncapitalize(modalName));
		return dg;
	}

	public boolean isSearchConvert(String searchName) {
		TableFactory tf = TableFactory.getInstance();
		@SuppressWarnings("unchecked")
		List<Table> tblist = tf.getAllTableNames();
		// String map="";
		// map= tb.getRemarks().split("^");
		for (Table tb : tblist) {
			if (tb.getClassName().equals(modalName)) {
				// tb.getRemarks().
				Column col = tf.getTable(tb.getSqlName()).getColumnByName(
						searchName);
				String map = col.getMap();
				if (!map.isEmpty() && map.split("\\|").length > 2) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public List<T> convert(List<T> l) throws Exception {
		List<T> l_vo = new ArrayList();
		int mapCount = 0;
		// modal -map
		TableFactory tf = TableFactory.getInstance();
		@SuppressWarnings("unchecked")
		List<Table> tblist = tf.getAllTableNames();
		// String map="";
		// map= tb.getRemarks().split("^");
		for (Table tb : tblist) {
			if (tb.getClassName().equals(modalName)) {
				// tb.getRemarks().
				LinkedHashSet<Column> cols = tf.getTable(tb.getSqlName())
						.getColumns();
				for (Column col : cols) {
					// System.out.println(col.getSqlTypeName());
					String map = col.getMap();
					if (!map.isEmpty() && map.split("\\|").length > 2) {
						mapCount++;
						String mapTab = map.split("\\|")[0];
						String mapField = map.split("\\|")[1];
						String mapTextField = map.split("\\|")[2];

						String mapModel = tf.getTable(mapTab).getClassName();
						if (mapTab.toLowerCase().equals("sy_sysdomain")) {

							//HashMap<String, Column> tt = tf.getTable(mapTab).getColumnsMap();
							//String mapFieldSql = tt.get(mapField).getSqlName();
							//String mapTextFieldSql = tt.get(mapTextField).getSqlName();

							// select fieldValue from CxSbSysdomian where
							// tableName=:tableName

							if (mapCount > 1) {
								for (T data : l_vo) {
									String val = "";
									String mapHql = " from SySysdomain where tableName=:tableName and fieldName=:fieldName and fieldValue=:fieldValue";
									// + mapField + "=:mapField";
									Map<String, Object> mapParams = new HashMap<String, Object>();

									val = objectGet(data, col.getColumnName());
									mapParams.put("tableName", mapField);
									mapParams.put("fieldName", mapTextField);
									mapParams.put("fieldValue", val);

									List<T> vals = dao.find(mapHql, mapParams);
									String val1 = objectGet(vals.get(0),"fieldDesc");
									objectSet(data, col.getColumnName(), val1);
								}
							} else {
								for (T data : l) {
									@SuppressWarnings("unchecked")
									T dt = (T) Class.forName(
											data.getClass().getName() + "_Vo")
											.newInstance();
									BeanUtils.copyProperties(dt, data);
									String val = "";
									String mapHql = " from SySysdomain where tableName=:tableName and fieldName=:fieldName and fieldValue=:fieldValue";
									// + mapField + "=:mapField";
									Map<String, Object> mapParams = new HashMap<String, Object>();

									val = objectGet(data, col.getColumnName());
									mapParams.put("tableName", mapField);
									mapParams.put("fieldName", mapTextField);
									mapParams.put("fieldValue", val);

									List<T> vals = dao.find(mapHql, mapParams);
									String val1 = objectGet(vals.get(0),"fieldDesc");
									objectSet(dt, col.getColumnName(), val1);
									l_vo.add(dt);
								}
							}

						} else {
							if (mapCount > 1) {
								for (T data : l_vo) {
									String val = "";
									String mapHql = " from " + mapModel
											+ " where " + mapField
											+ "=:mapField";
									Map<String, Object> mapParams = new HashMap<String, Object>();

									val = objectGet(data, col.getColumnName());
									if (tf.getTable(mapTab).getColumnsMap()
											.get(mapField).getSqlTypeName()
											.contains("INT")) {
										mapParams.put("mapField",
												Integer.parseInt(val));
									} else {
										mapParams.put("mapField", val);
									}
									/*
									 * if (col.getSqlTypeName().equals("INT")) {
									 * mapParams.put("mapField",
									 * Integer.parseInt(val)); } else {
									 * mapParams.put("mapField", val); }
									 */

									List<T> vals = dao.find(mapHql, mapParams);
									String val1 = objectGet(vals.get(0),
											mapTextField);
									objectSet(data, col.getColumnName(), val1);
								}
							} else {
								for (T data : l) {
									@SuppressWarnings("unchecked")
									T dt = (T) Class.forName(
											data.getClass().getName() + "_Vo")
											.newInstance();
									BeanUtils.copyProperties(dt, data);
									String val = "";
									String mapHql = " from " + mapModel
											+ " where " + mapField
											+ "=:mapField";
									Map<String, Object> mapParams = new HashMap<String, Object>();
									val = objectGet(data, col.getColumnName());
									if (tf.getTable(mapTab).getColumnsMap()
											.get(mapField).getSqlTypeName()
											.contains("INT")) {
										mapParams.put("mapField",
												Integer.parseInt(val));
									} else {
										mapParams.put("mapField", val);
									}
									/*
									 * if (col.getSqlTypeName().equals("INT")) {
									 * mapParams.put("mapField",
									 * Integer.parseInt(val)); } else {
									 * mapParams.put("mapField", val); }
									 */

									List<T> vals = dao.find(mapHql, mapParams);
									String val1 = objectGet(vals.get(0),
											mapTextField);
									objectSet(dt, col.getColumnName(), val1);
									// ((Object)
									// data).getString(col.getColumnName());
									// l.get(0).getString(col.getColumnName())
									l_vo.add(dt);
								}
							}
						}
					}
				}
				break;
			}
		}
		if (mapCount > 0)
			return l_vo;
		else
			return l;
	}

	public String objectGet(T o, String column) throws Exception {
		String val = "";
		for (Method f : o.getClass().getMethods()) {
			if (f.getName().toLowerCase().equals("get" + column.toLowerCase())
					&& f.getName().startsWith("get")
					&& f.getParameterTypes().length == 0
					&& (!f.getName().equalsIgnoreCase("getClass"))) {
				val = (String) f.invoke(o);
			}
		}
		return val;
	}

	public String objectGetFunType(T o, String column) throws Exception {
		String val = "";
		for (Method f : o.getClass().getMethods()) {
			if (f.getName().toLowerCase().equals("get" + column.toLowerCase())) {
				val = (String) f.getReturnType().getName();
				return val;
			}
		}
		return val;
	}

	public void objectSet(T o, String column, String val) throws Exception {
		for (Method f : o.getClass().getMethods()) {
			if (f.getName().toLowerCase().equals("set" + column.toLowerCase())) {
				f.invoke(o, val);
			}
		}
	}

	public DataGrid dataGrid(int page, int rows, T o) {
		DataGrid dg = new DataGrid();
		Map<String, Object> m = getHqlByObject(o);
		String hql = m.get("hql").toString();
		Map<String, Object> params = (Map<String, Object>) m.get("params");
		String totalHql = "select count(*) " + hql;
		// hql+=" order by t.logDate desc";
		List<T> l = null;
		if (rows == 0 && page == 0) {
			l = dao.find(hql, params);
		} else {
			l = dao.find(hql, params, page, rows);
		}
		try {
			convert(l);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dg.setTotal(dao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName(StringHelper.uncapitalize(modalName));
		return dg;
	}

	public void saveOrUpdate(T o) {
		dao.saveOrUpdate(o);
	}

	public void delete(String ids) {
		String[] ids_ = ids.split(",");
		for (int i = 0; i < ids_.length; i++) {
			dao.delete(dao.get((Class<T>) getEntityClass(),
					Integer.parseInt(ids_[i])));
		}

	}

	public List<T> listAll() {
		String hql = "from " + modalName + " t ";
		List<T> l = dao.find(hql);
		return l;
	}
	
	public T getLast(T o){
		String hql="from " + modalName + " t order by Id desc";
		List<T> l = dao.find(hql);
		try{
			return l.get(0);
		}catch(Exception e ){
			return null;
		}
	}
}
