package com.du.lin.service;

import java.util.List;

import com.du.lin.bean.LoginLog;
import com.du.lin.bean.ShowLog;


public interface LoginLogService {
	
	public List<LoginLog> getAllLoginLog();

	public List<ShowLog> getAllShowLoginLog();
	
	public String deleteALLLoginLog();
	
	public String getShowLogJson(int page, int count);
	
}
