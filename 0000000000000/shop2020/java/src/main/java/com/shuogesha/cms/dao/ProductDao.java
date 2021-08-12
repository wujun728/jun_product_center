package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Product;

public interface ProductDao { 
	
	void saveEntity(Product bean);

	Product findById(Long id);

	void updateById(Product bean);
 
	void removeById(Long id);
	
	List<Product> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	void updateNumById(Long id);

	long countAll(Map<String, Object> map);

	List<Product> queryAllList(Map<String, Object> map);
}