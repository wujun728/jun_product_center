package com.shuogesha.cms.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.CountDao;
import com.shuogesha.cms.entity.Count;
import com.shuogesha.cms.service.CountService;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.service.SiteService;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
 
@Service
public class CountServiceImpl implements CountService {

	@Autowired
	private CountDao dao;
	@Autowired
	public StringRedisTemplate strRedisTemplate;
	@Autowired
	public RedisTemplate redisTemplate;
	@Autowired
	private SiteService siteService;

	@Override
	public Count findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(name)) {
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Count> page = new Pagination<Count>(pageNo, pageSize, totalCount);
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize) * ((Integer.valueOf(pageNo) - 1)));
		List<Count> datas = dao.queryList(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public void save(Count bean) {
		dao.saveEntity(bean);
	}

	@Override
	public void update(Count bean) {
		dao.updateById(bean);
	}

	@Override
	public void removeById(Long id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public int freshCacheToDB(Ehcache cache) throws ParseException {
		Site site = siteService.findMaster();
		clearCount(site);
		int count = freshCache(cache);
		// copyCount(sites.get(0));
		return count;
	}

	private int clearCount(Site site) throws ParseException {
		Calendar curr = Calendar.getInstance();
		Calendar last = Calendar.getInstance();
		last.setTime(RequestUtils.parse(site.getCountClearTime()));
		int currDay = curr.get(Calendar.DAY_OF_YEAR);
		int lastDay = last.get(Calendar.DAY_OF_YEAR);
		if (currDay != lastDay) {
			int currWeek = curr.get(Calendar.WEEK_OF_YEAR);
			int lastWeek = last.get(Calendar.WEEK_OF_YEAR);
			int currMonth = curr.get(Calendar.MONTH);
			int lastMonth = last.get(Calendar.MONTH);
			siteService.updateCountClearTime(RequestUtils.getDateStr(curr.getTime()), site.getId());
			return clearCountDB(currWeek != lastWeek, currMonth != lastMonth);
		} else {
			return 0;
		}
	}

	private int copyCount(Site site) throws ParseException {
		long curr = System.currentTimeMillis();
		long last = RequestUtils.parse(site.getCountCopyTime()).getTime();
		if (curr > interval + last) {
			siteService.updateCountCopyTime(RequestUtils.getDateStr(new Date(curr)), site.getId());
			return copyCountDB();
		} else {
			return 0;
		}
	}

	private int copyCountDB() {
		return 0;
	}

	public int clearCountDB(boolean week, boolean month) {
		Query query = new Query();
		Map<String, Object> map = new HashMap<>();
		map.put("viewsday", 0);
		if (week) {
			map.put("viewsweek", 0);
		}
		if (month) {
			map.put("viewsmonth", 0);
		}
		dao.updateAll(map);
		return 0;
	}

	public int freshCache(Ehcache cache) {
		List<String> keys = cache.getKeys();
		if (keys.size() <= 0) {
			return 0;
		}
		Element e;
		Integer views;
		int i = 0;
		for (String id : keys) {
			e = cache.get(id);
			if (e != null) {
				views = (Integer) e.getValue();
				if (views != null) {
					Map<String, Object> map = new HashMap<>();
					map.put("views", views);
					map.put("viewsday", views);
					map.put("viewsweek", views);
					map.put("viewsmonth", views);
					map.put("id", id);
					dao.updateAll(map);
					cache.remove(id);// 移除当前的值
//					Count count =dao.findById(Integer.valueOf(id));
//					if(count!=null){
//						i++;
//						//开始更新原集合数据 TODO
//					}
				}
			}
		}
		return i;
	}

	@Override
	public Count saveCount(Long referid, String refer) {
		Map<String, Object> map = new HashMap<>();
		map.put("referid", referid);
		map.put("refer", refer);
		if (dao.count(map) <= 0) {
			Count entity = new Count();
			entity.setRefer(refer);
			entity.setReferid(referid);
			dao.saveEntity(entity);
			return entity;
		} else {
			return dao.findByRefer(map);
		}
	}

	private int interval = 12 * 60 * 60 * 1000; // 一小时

	/**
	 * 设置拷贝间隔时间。默认一小时。
	 * 
	 * @param interval 单位分钟
	 */
	public void setInterval(int interval) {
		this.interval = interval * 60 * 1000;
	}

	@Override
	public void commentUp(Long referid, String refer) {
		Map<String, Object> map = new HashMap<>();
		map.put("referid", referid);
		map.put("refer", refer);
		Count bean = dao.findByRefer(map);
		if (bean != null) {
			int count = bean.getComments();
			bean.setComments(count + 1);
			dao.updateById(bean);
		}

	}

	@Override
	public void commentDown(Long referid, String refer) {
		Map<String, Object> map = new HashMap<>();
		map.put("referid", referid);
		map.put("refer", refer);
		Count bean = dao.findByRefer(map);
		if (bean != null) {
			int count = bean.getComments();
			if (count <= 1) {
				count = 1;
			}
			bean.setComments(count - 1);
			dao.updateById(bean);
		}

	}

	@Override
	public void praiseUp(Long referid, String refer) {
		Map<String, Object> map = new HashMap<>();
		map.put("referid", referid);
		map.put("refer", refer);
		Count bean = dao.findByRefer(map);
		if (bean != null) {
			int count = bean.getPraise();
			bean.setPraise(count + 1);
			dao.updateById(bean);
		}

	}

	@Override
	public void praiseDown(Long referid, String refer) {
		Map<String, Object> map = new HashMap<>();
		map.put("referid", referid);
		map.put("refer", refer);
		Count bean = dao.findByRefer(map);
		if (bean != null) {
			int count = bean.getPraise();
			if (count <= 1) {
				count = 1;
			}
			bean.setPraise(count - 1);
			dao.updateById(bean);
		}

	}

	@Override
	public void collectUp(Long referid, String refer) {
		Map<String, Object> map = new HashMap<>();
		map.put("referid", referid);
		map.put("refer", refer);
		Count bean = dao.findByRefer(map);
		if (bean != null) {
			int count = bean.getCollect();
			bean.setCollect(count + 1);
			dao.updateById(bean);
		}

	}

	@Override
	public void collectDown(Long referid, String refer) {
		Map<String, Object> map = new HashMap<>();
		map.put("referid", referid);
		map.put("refer", refer);
		Count bean = dao.findByRefer(map);
		if (bean != null) {
			int count = bean.getCollect();
			if (count <= 1) {
				count = 1;
			}
			bean.setCollect(count - 1);
			dao.updateById(bean);
		}

	}

	@Override
	public int freshRedisCacheToDB(String root) throws ParseException {
		Site site = siteService.findMaster();
		clearCount(site);
		int count = freshRedisCache(root);
		return count;
	}

	private int freshRedisCache(String root) {
		int i = 0;
		Integer views;
		Cursor<Map.Entry<Object, Object>> cursor = strRedisTemplate.opsForHash().scan(root, ScanOptions.NONE);
		while (cursor.hasNext()) {
			Map.Entry<Object, Object> bb = cursor.next(); // 将浏览量数量存储在
			Long id = Long.valueOf((String) bb.getKey());
			views = Integer.valueOf((String) bb.getValue());
			if (views != null) {
				Map<String, Object> map = new HashMap<>();
				map.put("views", views);
				map.put("viewsday", views);
				map.put("viewsweek", views);
				map.put("viewsmonth", views);
				map.put("id", id);
				dao.updateAllAdd(map);
			}
			i++;
			strRedisTemplate.opsForHash().delete(root, String.valueOf(id));
			redisTemplate.opsForHash().delete("com.shuogesha.cms.entity.Count",
					new String[] { "findById" + String.valueOf(id) });
		}

		return i;
	}

}
