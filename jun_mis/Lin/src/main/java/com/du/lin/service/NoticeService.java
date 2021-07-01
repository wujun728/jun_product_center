package com.du.lin.service;

import java.util.List;

import com.du.lin.bean.Notice;
import com.du.lin.bean.ShowNotice;

public interface NoticeService {
	
	public List<Notice> getAllNotice();
	/**
	 * 主页显示的15个Notice
	 * @return
	 */
	public List<Notice> getIndexNotice();
	
	public List<ShowNotice> getAllShowNotice();
	
	public int sendSystemNotice(String body);
	
	public int sendConmonNotice(String body , String recive);
	
	
	
}
