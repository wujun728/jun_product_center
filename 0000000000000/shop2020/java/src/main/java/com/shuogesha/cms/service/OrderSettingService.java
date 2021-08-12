package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.OrderSetting;

public interface OrderSettingService {
	Pagination getPage(String name, int pageNo, int pageSize);

	OrderSetting findById(Long id);

	void save(OrderSetting bean);

	void update(OrderSetting bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	OrderSetting findOne();
}