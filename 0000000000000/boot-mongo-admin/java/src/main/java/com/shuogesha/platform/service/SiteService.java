package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.Site;

public interface SiteService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Site findById(Long id);

	void save(Site bean);

	void update(Site bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Site findByTplSolution(String p);

	Site findMaster();

	void updateCountClearTime(String dateStr, Long id);

	void updateCountCopyTime(String dateStr, Long id);
}