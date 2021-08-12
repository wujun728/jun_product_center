package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Cart;

public interface CartService {
	Pagination getPage(String name,Long userId, int pageNo, int pageSize);

	Cart findById(Long id);

	void save(Cart bean);

	void update(Cart bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Cart findByRefer(Cart bean);

	void removeByUserId(Long userId);
}