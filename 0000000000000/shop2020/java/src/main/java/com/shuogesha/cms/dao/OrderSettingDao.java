package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.OrderSetting;

public interface OrderSettingDao { 
	
	void saveEntity(OrderSetting bean);

	OrderSetting findById(Long id);

	void updateById(OrderSetting bean);
 
	void removeById(Long id);
	
	List<OrderSetting> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	OrderSetting findOne();
}