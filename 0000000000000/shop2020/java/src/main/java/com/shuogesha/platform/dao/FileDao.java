package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.File;

public interface FileDao { 
	
	void saveEntity(File bean);

	File findById(Long id);

	void updateById(File bean);
 
	void removeById(Long id);
	
	List<File> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	void removeByName(Map<String, Object> map);
}