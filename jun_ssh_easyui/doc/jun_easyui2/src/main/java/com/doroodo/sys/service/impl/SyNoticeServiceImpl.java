package com.doroodo.sys.service.impl;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.model.DataGrid;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.doroodo.base.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

@Service("syNoticeService")
public class SyNoticeServiceImpl extends BaseServiceImpl<SyNotice> implements SyNoticeService {

	public DataGrid dataGrid(int page, int rows, String searchName,
			String searchKey, String where) {
		DataGrid dg = new DataGrid();
		String hql = "from SyNotice t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key";
			params.put("key", "%%" + searchKey.trim() + "%%");
			hql+=" and t."+where;
		}else{
			hql+="where t."+where;
		}
		String totalHql = "select count(*) " + hql;
		List<SyNotice> l=null;
		if(rows==0 &&page==0){
			l = this.getDao().find(hql, params);
		}else{
			l = this.getDao().find(hql, params,page, rows);
		}
		dg.setTotal(this.getDao().count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("syNotice");
		return dg;
	}
}