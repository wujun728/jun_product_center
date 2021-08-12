package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.AdDao;
import com.shuogesha.cms.entity.Ad;
import com.shuogesha.cms.service.AdService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class AdServiceImpl implements AdService{
	
	@Autowired
	private AdDao dao;

	@Override
	public Ad findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Ad> page = new Pagination<Ad>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Ad> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Ad bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Ad bean) { 
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
	public List<Ad> findList(Integer type) { 
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("type", type);
		return dao.findList(map);
	}

}
