package com.shuogesha.platform.service;

import java.util.List;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.Dictionary;

public interface DictionaryService {
	Pagination getPage(Integer ctgId,String name, int pageNo, int pageSize);

	Dictionary findById(Long id);

	void save(Dictionary bean);

	void update(Dictionary bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<Dictionary> findByCode(String code);
}