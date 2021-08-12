package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.DictionaryDao;
import com.shuogesha.platform.entity.Dictionary;
import com.shuogesha.platform.service.DictionaryService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class DictionaryServiceImpl implements DictionaryService{
	
	@Autowired
	private DictionaryDao dao;

	@Override
	public Dictionary findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage(Integer ctgId, String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("ctgId", ctgId);
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Dictionary> page = new Pagination<Dictionary>(pageNo, pageSize, totalCount);
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Dictionary> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Dictionary bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Dictionary bean) { 
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
	public List<Dictionary> findByCode(String code) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("code", code);
 		return dao.findByCode(map);
	}

}
