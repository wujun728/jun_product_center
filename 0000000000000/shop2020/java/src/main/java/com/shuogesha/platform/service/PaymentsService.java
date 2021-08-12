package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.platform.entity.Payments;

public interface PaymentsService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Payments findById(Long id);

	void save(Payments bean);

	void update(Payments bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<Payments> findList(String platform);
}