package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Code;

public interface CodeService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Code findById(Long id);

	void save(Code bean);

	void update(Code bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Code findByName(String refer);
}