package com.shuogesha.platform.service;

import java.util.List;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.DictionaryCtg;

public interface DictionaryCtgService {
	Pagination getPage(String name, int pageNo, int pageSize);

	DictionaryCtg findById(Long id);

	void save(DictionaryCtg bean);

	void update(DictionaryCtg bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	String findBycodeToName(String code, String value);

	boolean codeNotExist(String code);

	List<DictionaryCtg> findList();
}