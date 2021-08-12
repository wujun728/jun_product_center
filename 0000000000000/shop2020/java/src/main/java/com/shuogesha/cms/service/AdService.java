package com.shuogesha.cms.service;

import java.util.List;

import com.shuogesha.cms.entity.Ad;
import com.shuogesha.platform.web.mongo.Pagination;

public interface AdService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Ad findById(Long id);

	void save(Ad bean);

	void update(Ad bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);
	
	/**
	 * 获取广告列表
	 * @param type
	 * @return
	 */
	List<Ad> findList(Integer type);
}