package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.ProductAttr;

public interface ProductAttrDao { 
	
	void saveEntity(ProductAttr bean);

	ProductAttr findById(Long id);

	void updateById(ProductAttr bean);
 
	void removeById(Long id);
	
	List<ProductAttr> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	void removeAll(Map<String, Object> map);

	void addAll(Map<String, Object> map);
}