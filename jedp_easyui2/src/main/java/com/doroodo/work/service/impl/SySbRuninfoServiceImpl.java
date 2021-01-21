package com.doroodo.work.service.impl;

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
@Service("sySbRuninfoService")
public class SySbRuninfoServiceImpl extends BaseServiceImpl<SySbRuninfo>  implements SySbRuninfoService {
	@Autowired
	private BaseDao<SySbRuninfo> sySbRuninfoDao;
	public List listTop100(){
		return sySbRuninfoDao.sqlObj("SELECT * FROM sy_sb_runinfo ORDER BY inserttime DESC LIMIT 100 ");
	}
}