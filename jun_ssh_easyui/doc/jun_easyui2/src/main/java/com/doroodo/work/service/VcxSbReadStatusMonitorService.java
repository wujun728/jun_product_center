package com.doroodo.work.service;

import java.util.HashMap;

import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
import com.doroodo.work.model.*;
public interface VcxSbReadStatusMonitorService extends BaseService<VcxSbReadStatusMonitor> {
	HashMap<String,String>cmdCollection(String id);
}
