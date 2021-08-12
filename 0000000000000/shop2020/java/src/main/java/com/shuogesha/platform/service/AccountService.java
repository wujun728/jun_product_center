package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.Account;

public interface AccountService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Account findById(Long id);

	void save(Account bean);

	void update(Account bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Account init(Long id);
}