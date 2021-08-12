package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.ProductCat;

public interface ProductCatDao { 
	
	void saveEntity(ProductCat bean);

	ProductCat findById(Long id);

	void updateById(ProductCat bean);
 
	void removeById(Long id);
	
	List<ProductCat> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<ProductCat> findAll(Map<String, Object> map);

	List<ProductCat> findAllProductCats(Map<String, Object> map);
}