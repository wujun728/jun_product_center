package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Shipping;

public interface ShippingDao { 
	
	void saveEntity(Shipping bean);

	Shipping findById(Long id);

	void updateById(Shipping bean);
 
	void removeById(Long id);
	
	List<Shipping> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	void removeByOrderId(Long id);
	
	List<Shipping> findByOrderId(Long id);
}