package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.Account;

public interface AccountDao { 
	
	void saveEntity(Account bean);

	Account findById(Long id);

	void updateById(Account bean);
 
	void removeById(Long id);
	
	List<Account> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);
}