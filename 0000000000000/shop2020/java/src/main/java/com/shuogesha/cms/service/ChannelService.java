package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.cms.entity.Channel;

public interface ChannelService {
	Pagination getPage(String name,Long pId, int pageNo, int pageSize);

	Channel findById(Long id);

	void save(Channel bean);

	void update(Channel bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<Channel> findAll(Long pId);
}