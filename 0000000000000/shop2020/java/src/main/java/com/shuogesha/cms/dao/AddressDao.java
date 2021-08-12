package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Address;

public interface AddressDao { 
	
	void saveEntity(Address bean);

	Address findById(Long id);

	void updateById(Address bean);
 
	void removeById(Long id);
	
	List<Address> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	Address findByDefault(Long userId);
}