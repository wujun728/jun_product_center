package com.du.lin.service;

import java.util.List;

import com.du.lin.bean.OperationLog;
import com.du.lin.bean.ShowLog;


public interface OperationLogService {
	
	public List<OperationLog> getAllOperationLog();

	public List<ShowLog> getAllShowLog();
	
	public String deleteALLLog();
	
	public String getShowLogJson(int page , int count);
	
	
}
