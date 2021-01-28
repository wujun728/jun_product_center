package com.doroodo.work.service;

import java.util.HashMap;

import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
import com.doroodo.work.model.*;
public interface CxSbTerminalConfigService extends BaseService<CxSbTerminalConfig> {
	public HashMap<String,String> config(String id,String leakage,String over,String time);
	public int batchUpdate(String ids,String leakage,String over,String time);
	public HashMap<String,String> batchConfig(String ids,String leakage,String over,String time);
}
