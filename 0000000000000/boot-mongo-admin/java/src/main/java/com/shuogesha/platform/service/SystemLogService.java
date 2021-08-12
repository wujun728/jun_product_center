package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.SystemLog;

public interface SystemLogService {
	Pagination getPage(String name, int pageNo, int pageSize);

	SystemLog findById(String id);

	void save(SystemLog bean);

	void update(SystemLog bean);

	void removeById(String id);
	
	void removeByIds(String[] ids);
	/**
	 * 直接存数据库
	 * @param uid
	 * @param string
	 * @param ipAddr
	 * @param pc
	 */
	void log(String uid, String string, String ipAddr, String pc);
}