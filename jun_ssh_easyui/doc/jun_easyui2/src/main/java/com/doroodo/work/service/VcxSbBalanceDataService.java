package com.doroodo.work.service;

import java.util.HashMap;

import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
import com.doroodo.work.model.*;
public interface VcxSbBalanceDataService extends BaseService<VcxSbBalanceData> {
	public HashMap<String,String> autoBalance(String addr,String val);
}
