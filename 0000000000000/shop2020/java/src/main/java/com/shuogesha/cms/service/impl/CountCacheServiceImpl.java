package com.shuogesha.cms.service.impl;

import java.text.ParseException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.entity.Count;
import com.shuogesha.cms.service.CountCacheService;
import com.shuogesha.cms.service.CountService;
import com.shuogesha.common.util.EhCacheUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
  
@Service
public class CountCacheServiceImpl implements CountCacheService, DisposableBean {

	public int[] viewAndGet(Long id) throws ParseException {
		Count count = countService.findById(id);
		if (count == null) {
			count = new Count();
			count.setId(id);
			countService.save(count);
		}
		Integer views = 1;
		if (TYPE.equals("Ehcache")) {// Ehcache的缓存策略
			Cache cache=ehCacheUtils.cache("count");
			Element e = cache.get(String.valueOf(id));
			if (e != null) {
				views = (Integer) e.getValue() + 1;
			} else if (count != null) {
				views = count.getViews() + 1;
			} else {
				views = 1;
			}
			cache.put(new Element(String.valueOf(id), views),true);
 			refreshToDB();
		} else {// redis的缓存
			String view = (String) strRedisTemplate.opsForHash().get(COUNT_LIST, String.valueOf(id));
			if (view == null) {// 如果不存在那么直接插入
				strRedisTemplate.opsForHash().put(COUNT_LIST, String.valueOf(id), views.toString());
			} else {
				strRedisTemplate.opsForHash().increment(COUNT_LIST, String.valueOf(id), views);
				views = Integer.valueOf(view) + views;
			}
			refreshRedisToDB();
		}
		return new int[] { views };
	}

	/**
	 * 刷新redis的缓存到mysql
	 * 
	 * @throws ParseException
	 */
	private void refreshRedisToDB() throws ParseException {
		long time = System.currentTimeMillis();
		if (time > refreshTime + interval) {
			refreshTime = time;
			countService.freshRedisCacheToDB(COUNT_LIST);
			// 清除缓存
		}
	}

	/**
	 * 刷新ecahce的数据到mysql
	 * 
	 * @throws ParseException
	 */
	private void refreshToDB() throws ParseException {
		long time = System.currentTimeMillis();
		if (time > refreshTime + interval) {
			refreshTime = time;
			Cache cache=ehCacheUtils.cache("count");
			int count = countService.freshCacheToDB(cache);
			// 清除缓存
			cache.removeAll();
		}
	}

	@Override
	public void destroy() throws Exception {
		Cache cache=ehCacheUtils.cache("count");
		int count = countService.freshCacheToDB(cache);
	}

	private static String TYPE = "Ehcache"; // 如果缓存的是Ehcache、Redis
	private static String COUNT_LIST = "COUNT_LIST"; // 如果缓存的是redis

	@Autowired
	public StringRedisTemplate strRedisTemplate;

	// 间隔时间
	private int interval = 1 * 60 * 1000; // 1分钟
	// 最后刷新时间
	private long refreshTime = System.currentTimeMillis(); 

	@Autowired
	private CountService countService;  
	@Autowired
	private EhCacheUtils ehCacheUtils;  
 	

}
