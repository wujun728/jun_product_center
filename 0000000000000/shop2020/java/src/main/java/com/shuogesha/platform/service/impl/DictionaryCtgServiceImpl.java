package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.DictionaryCtgDao;
import com.shuogesha.platform.entity.DictionaryCtg;
import com.shuogesha.platform.service.DictionaryCtgService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class DictionaryCtgServiceImpl implements DictionaryCtgService{
	
	@Autowired
	private DictionaryCtgDao dao;

	@Override
	public DictionaryCtg findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<DictionaryCtg> page = new Pagination<DictionaryCtg>(pageNo, pageSize, totalCount);
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<DictionaryCtg> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(DictionaryCtg bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(DictionaryCtg bean) { 
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
	public String findBycodeToName(String code, String value) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("code", code);
		map.put("value", value);
		return dao.findBycodeToName(map);
	}

	@Override
	public boolean codeNotExist(String code) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("code", code);
 		return dao.countByCode(map) <= 0;
	}

	@Override
	public List<DictionaryCtg> findList() {
		return dao.findList();
	}

}
