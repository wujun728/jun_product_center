package com.doroodo.sys.service;

import java.util.List;

import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
import com.doroodo.work.model.*;
public interface SyPointService extends BaseService<SyPoint> {
		public List getPvByTabWhere(String tab,String field,String fun,String where);
}
