package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Order;

public interface OrderDao { 
	
	void saveEntity(Order bean);

	Order findById(Long id);

	void updateById(Order bean);
 
	void removeById(Long id);
	
	List<Order> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	long countFront(Map<String, Object> map);

	List<Order> queryFrontList(Map<String, Object> map);

	Order findByOrderNo(Map<String, Object> map);

	void cancelAllScan(Integer payTimeout);
}