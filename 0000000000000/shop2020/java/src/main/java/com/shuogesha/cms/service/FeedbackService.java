package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Feedback;

public interface FeedbackService {

	Pagination getPage(String name, int pageNo, int pageSize);

	Feedback findById(Long id);

	void save(Feedback bean);

	void update(Feedback bean);

	void removeById(Long id);

	void removeByIds(Long[] ids);
}