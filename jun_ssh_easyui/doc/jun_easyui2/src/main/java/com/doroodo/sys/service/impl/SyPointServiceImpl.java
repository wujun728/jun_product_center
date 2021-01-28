package com.doroodo.sys.service.impl;

import java.util.List;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.work.model.*;
import com.doroodo.work.service.*;
@Service("syPointService")
public class SyPointServiceImpl extends BaseServiceImpl<SyPoint>  implements SyPointService {
	@Autowired
	private BaseDao<SyPoint> syPointDao;
	public List getPvByTabWhere(String tab,String field,String fun,String where){
		if(fun.equals("val")){
			field=field+" val";
		}else if(fun.equals("last")){
			field=field+" val";
			where +=" order by Id desc";
		}else{
			field=fun+"("+field+") val";
		}
		String sql="select "+field+" from "+tab+" "+where;
		return syPointDao.sqlObj(sql);
	}
}