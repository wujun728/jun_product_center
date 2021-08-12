package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.platform.entity.ItemParam;

public interface ItemParamService {
	Pagination getPage(String name, int pageNo, int pageSize);

	ItemParam findById(Long id);

	void save(ItemParam bean);

	void update(ItemParam bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<ItemParam> findAll(String path);
}