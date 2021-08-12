package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Product;

public interface ProductService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Product findById(Long id);

	void save(Product bean);

	void update(Product bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	void updateNumById(Long id);
	
	Pagination getAllPage(String name, Long cat_id,Integer filterIndex,Integer priceOrder,int pageNo, int pageSize);

}