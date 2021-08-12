package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.Site;

public interface SiteDao { 
	
	void saveEntity(Site bean);

	Site findById(Long id);

	void updateById(Site bean);
 
	void removeById(Long id);
	
	List<Site> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	Site findMaster();

	Site findByTplSolution(String tplsolution);

	void updateOne(Map<String, Object> map);
}