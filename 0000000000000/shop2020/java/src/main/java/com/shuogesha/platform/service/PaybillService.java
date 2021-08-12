package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.Paybill;
import com.shuogesha.platform.entity.Paylog;

public interface PaybillService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Paybill findById(String id);

	void save(Paybill bean);

	void update(Paybill bean);

	void removeById(String id);
	
	void removeByIds(String[] ids);

	Paylog pay(Paylog payLog, Integer status, boolean pay);

	Pagination getPageByAcount(String type, Integer unifiedUserId,int pageNo, int pageSize);

	void re(String orderNo);
}