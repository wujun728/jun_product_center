package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Address;

public interface AddressService {
	Pagination getPage(String name,Long userId, int pageNo, int pageSize);

	Address findById(Long id);

	void save(Address bean);

	void update(Address bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Address findByDefault(Long userId);
}