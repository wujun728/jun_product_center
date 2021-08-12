package com.shuogesha.cms.service;

import java.text.ParseException;

public interface CountCacheService {
	/**
	 * 浏览一次
	 * 
	 * @param id
	 *            内容ID
	 * @return 返回浏览次数，评论次数，顶次数，踩次数。
	 */
	public int[] viewAndGet(Long id) throws ParseException ;
}
