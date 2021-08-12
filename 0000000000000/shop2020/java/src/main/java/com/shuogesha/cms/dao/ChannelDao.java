package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Channel;

public interface ChannelDao { 
	
	void saveEntity(Channel bean);

	Channel findById(Long id);

	void updateById(Channel bean);
 
	void removeById(Long id);
	
	List<Channel> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<Channel> findAll(Map<String, Object> map);
}