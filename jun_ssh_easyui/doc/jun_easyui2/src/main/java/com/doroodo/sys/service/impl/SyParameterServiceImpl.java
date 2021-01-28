package com.doroodo.sys.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.model.DataGrid;
import com.doroodo.sys.model.SyParameter;
import com.doroodo.sys.model.SyParameterUsed;
import com.doroodo.sys.service.SyParameterService;
@Service("syParameterService")
public class SyParameterServiceImpl implements SyParameterService {
	@Autowired
	private BaseDao<SyParameter> syParameterDao;
	@Autowired
	private BaseDao<SyParameterUsed> syParameterUsedDao;
	public void saveOrUpdate(SyParameter syParameter) {
		syParameterDao.saveOrUpdate(syParameter);
	}
	public SyParameter getLastSyParameter() {
		String hql="from SyParameter as t where t.id=(select max(s.id) from SyParameter s)";
		return syParameterDao.get(hql);
	}

	public DataGrid dataGrid(int page, int rows, String searchName,
			String searchKey) {
		DataGrid dg = new DataGrid();
		String hql = "from SyParameter t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key";
			params.put("key", "%%" + searchKey.trim() + "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<SyParameter> l =  null;
		if(rows==0 &&page==0){
			l = syParameterDao.find(hql, params);
		}else{
			l = syParameterDao.find(hql, params,page, rows);
		}
		dg.setTotal(syParameterDao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("syParameter");
		return dg;
	}
	public void setDefult(SyParameterUsed syParameterUsed) {
		 syParameterUsedDao.saveOrUpdate(syParameterUsed);
	}
	public SyParameter getUsedSyParameter() {
		SyParameterUsed syParameterUsed=null;
		try{
			syParameterUsed = syParameterUsedDao.get(SyParameterUsed.class, 1);
		}catch(Exception e){
			e.toString();
		}
		if(syParameterUsed==null) 
			return null;
		else
			return syParameterDao.get(SyParameter.class, Integer.parseInt(syParameterUsed.getSyParameterId()));
	}
	public void delete(String ids) {
		String[] ids_=ids.split(",");
		for(int i=0;i<ids_.length;i++)
		{
			SyParameter syParameter = new SyParameter();
			syParameter.setId(Integer.parseInt(ids_[i]));
			syParameterDao.delete(syParameter);
		}
		
	}
	
	public List<SyParameter> get(SyParameter syParameter) {
		String hql = "from SyParameter t where";
		Map<String, Object> params = new HashMap<String, Object>();
		List<SyParameter> l = null;
		for (int i = 0; i < syParameter.getClass().getMethods().length; i++) {
			Method f = syParameter.getClass().getMethods()[i];
			if (f.getName().startsWith("get")
					&& f.getParameterTypes().length == 0 
					&& (!f.getName().equalsIgnoreCase("getClass"))) {
				String fieldResult;
				String fieldName="";
				try {
					fieldResult = f.invoke(syParameter, null) == null ? "" : f.invoke(syParameter, null).toString();
					for (int j = 0; j < syParameter.getClass().getDeclaredFields().length; j++) {
						String fieldName_=syParameter.getClass().getDeclaredFields()[j].getName();
						if(fieldName_.equalsIgnoreCase(f.getName().substring(3))){
							fieldName = fieldName_;
							break;
						}
					}
					
					if( !fieldResult.trim().equals("")  && !fieldName.trim().isEmpty()){
						if("java.lang.integer".equalsIgnoreCase(f.getReturnType().getName())){
							hql+=" t."+fieldName+" = :"+fieldName+" and";
							params.put(fieldName, Integer.parseInt(fieldResult));
						}else{
							hql+=" t."+fieldName+" = :"+fieldName+" and";
							params.put(fieldName, fieldResult);
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
		if(hql.endsWith("and")){
			hql = hql.substring(0, hql.length()-3);
		}
		try{
			l = syParameterDao.find(hql, params);
		}catch(Exception e){
			e.toString();
		}
		return l;
	}

}
