package com.shuogesha.cms.service;

import java.util.List;

import com.shuogesha.cms.entity.Adsense;
import com.shuogesha.platform.web.mongo.Pagination;

public interface AdsenseService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Adsense findById(Long id);

	void save(Adsense bean);

	void update(Adsense bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<Adsense> findAll();
}