package com.shuogesha.platform.service;

import java.util.List;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.Dictionary;

public interface DictionaryService {
	Pagination getPage(String ctgId,String name, int pageNo, int pageSize);

	Dictionary findById(String id);

	void save(Dictionary bean);

	void update(Dictionary bean);

	void removeById(String id);
	
	void removeByIds(String[] ids);

	List<Dictionary> findByCode(String code);
}