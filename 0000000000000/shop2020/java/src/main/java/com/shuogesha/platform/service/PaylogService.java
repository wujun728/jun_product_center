package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.Paylog;

public interface PaylogService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Paylog findById(String id);

	void save(Paylog bean);

	void update(Paylog bean);

	void removeById(String id);
	
	void removeByIds(String[] ids);

	Paylog create(String orderNo, String model, String status);
	
	Paylog add(String out_trade_no, String trade_no, String trade_status);
}