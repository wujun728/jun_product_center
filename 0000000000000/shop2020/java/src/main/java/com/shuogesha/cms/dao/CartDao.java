package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Cart;

public interface CartDao { 
	
	void saveEntity(Cart bean);

	Cart findById(Long id);

	void updateById(Cart bean);
 
	void removeById(Long id);
	
	List<Cart> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	Cart findByRefer(Cart bean);

	void removeByUserId(Map<String, Object> map);
}