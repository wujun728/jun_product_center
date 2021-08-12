package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;

import net.sf.ehcache.Ehcache;

import java.text.ParseException;

import com.shuogesha.cms.entity.Count;

public interface CountService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Count findById(Long id);

	void save(Count bean);

	void update(Count bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);
	
	Count saveCount(Long referId, String refer);
	
	int freshCacheToDB(Ehcache cache) throws ParseException ;

	void commentUp(Long referid, String refer);

	void commentDown(Long referid, String refer);

	void praiseUp(Long referid, String refer);

	void praiseDown(Long referid, String refer);

	int freshRedisCacheToDB(String root) throws ParseException;

	void collectUp(Long referid, String refer);

	void collectDown(Long referid, String refer);
}