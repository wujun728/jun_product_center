package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Follow;

public interface FollowService {
	Pagination getPage(String name, Long fromUid,Long toUid,int pageNo, int pageSize);

	Follow findById(Long id);

	void save(Follow bean);

	void update(Follow bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Follow findByFollw(Follow bean);

    Pagination getFollowPage(String name, Long fromUid,Long toUid,int pageNo, int pageSize);
}