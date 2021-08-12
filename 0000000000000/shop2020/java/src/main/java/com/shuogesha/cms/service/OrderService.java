package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Order;

public interface OrderService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Order findById(Long id);

	void save(Order bean);

	void update(Order bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Order cancel(Order bean);

	Pagination findFrontPage(String name, Long userId,Integer state, int pageNo, int pageSize);

	Order findByOrderNo(String orderNo);
	/**
	 * 取消多少分钟前的订单数据
	 * @param pay_timeout
	 */
	void cancelAllScan(Integer pay_timeout);
}