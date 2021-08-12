package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.cms.entity.ProductAttr;

public interface ProductAttrService {
	Pagination getPage(String name, int pageNo, int pageSize);

	ProductAttr findById(Long id);

	void save(ProductAttr bean);

	void update(ProductAttr bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);
	/**
	 * 批量保存规格参数
	 * @param list
	 * @param id
	 */
	void saveAll(List<ProductAttr> list, Long productId);
}