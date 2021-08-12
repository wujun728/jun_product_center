package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.SiteDao;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.service.SiteService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class SiteServiceImpl implements SiteService{
	
	@Autowired
	private SiteDao dao;

	@Override
	public Site findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		long totalCount = dao.count(map);
		Pagination<Site> page = new Pagination<Site>(pageNo, pageSize, totalCount);
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Site> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Site bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Site bean) { 
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
	public Site findByTplSolution(String tplsolution) {
		return dao.findByTplSolution(tplsolution);
	}

	@Override
	public Site findMaster() {
		return dao.findMaster();
	}

	@Override
	public void updateCountClearTime(String dateStr, Long id) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("countClearTime", dateStr);
		map.put("id", id);
		dao.updateOne(map);
	}

	@Override
	public void updateCountCopyTime(String dateStr, Long id) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("countCopyTime", dateStr);
		map.put("id", id);
		dao.updateOne(map);
	}

}
