package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.cms.entity.OrderNote;

public interface OrderNoteService {
	Pagination getPage(String name, int pageNo, int pageSize);

	OrderNote findById(Long id);

	void save(OrderNote bean);

	void update(OrderNote bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<OrderNote> findList(Long orderId);
}