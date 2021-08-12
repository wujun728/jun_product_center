package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.File;

public interface FileService {
	Pagination getPage(String name, int pageNo, int pageSize);

	File findById(Long id);

	void save(File bean);

	void update(File bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);
	/**
	 * 保存图片
	 * @param fileUrl
	 */
	void init(String fileUrl);
	/**
	 * 根据图片名称删除
	 * @param fileUrl
	 */
	void remove(String fileUrl);
}