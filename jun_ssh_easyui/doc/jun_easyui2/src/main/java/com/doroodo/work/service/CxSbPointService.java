package com.doroodo.work.service;

import java.util.List;

import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
import com.doroodo.work.model.*;
public interface CxSbPointService extends BaseService<CxSbPoint> {
	public List getPvByTabWhere(String tab,String field,String fun,String where);
}
