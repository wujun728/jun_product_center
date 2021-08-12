package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.OrderNote;

public interface OrderNoteDao { 
	
	void saveEntity(OrderNote bean);

	OrderNote findById(Long id);

	void updateById(OrderNote bean);
 
	void removeById(Long id);
	
	List<OrderNote> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<OrderNote> findList(Long orderId);
}