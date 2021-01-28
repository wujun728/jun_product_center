package com.doroodo.work.service;

import java.util.HashMap;
import java.util.List;

import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
import com.doroodo.work.model.*;
public interface VcxSbCurrentMonitorService extends BaseService<VcxSbCurrentMonitor> {
	public HashMap<String,String> cmdCollection(String id);
	public List getConnectionData(String id);
}
