package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.Paybill;

public interface PaybillDao { 
	
	void saveEntity(Paybill bean);

	Paybill findById(String id);

	void updateById(Paybill bean);
 
	void removeById(String id);
	
	List<Paybill> queryList(Map<String, Object> map);

	long count(Map<String, Object> map); 

	List<Paybill> findByOrderNo(String orderNo);
}