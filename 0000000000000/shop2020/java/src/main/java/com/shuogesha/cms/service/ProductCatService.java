package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.cms.entity.Channel;
import com.shuogesha.cms.entity.ProductCat;

public interface ProductCatService {
	Pagination getPage(String name, Long pId, int pageNo, int pageSize);

	ProductCat findById(Long id);

	void save(ProductCat bean);

	void update(ProductCat bean);

	void removeById(Long id);

	void removeByIds(Long[] ids);

	List<ProductCat> findAll(Long pId);

	List<ProductCat> findAllProductCats(Long pid);
}