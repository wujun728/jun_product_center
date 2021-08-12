package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.Payments;

public interface PaymentsDao { 
	
	void saveEntity(Payments bean);

	Payments findById(Long id);

	void updateById(Payments bean);
 
	void removeById(Long id);
	
	List<Payments> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<Payments> findList(Map<String, Object> map);
}