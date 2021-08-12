package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.AppDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.service.AppService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class AppServiceImpl implements AppService{
	
	@Autowired
	private AppDao dao;

	@Override
	public App findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<App> page = new Pagination<App>(pageNo, pageSize, totalCount); 
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<App> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(App bean) {
		bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		dao.saveEntity(bean);
	}

	@Override
	public void update(App bean) { 
		 dao.updateById(bean);
	}

	@Override
	public void removeById(String id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public long countById(String appid) { 
		return dao.countById(appid);
	}

}
