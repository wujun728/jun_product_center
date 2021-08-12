package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Shipping;

public interface ShippingService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Shipping findById(Long id);

	void save(Shipping bean);

	void update(Shipping bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	void removeByOrderId(Long id);
}