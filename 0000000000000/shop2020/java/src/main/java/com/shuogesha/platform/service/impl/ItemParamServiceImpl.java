package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.ItemParamDao;
import com.shuogesha.platform.entity.ItemParam;
import com.shuogesha.platform.service.ItemParamService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class ItemParamServiceImpl implements ItemParamService{
	
	@Autowired
	private ItemParamDao dao;

	@Override
	public ItemParam findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<ItemParam> page = new Pagination<ItemParam>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<ItemParam> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(ItemParam bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(ItemParam bean) { 
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
	public List<ItemParam> findAll(String path) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(path)){
			map.put("path", path);
		}
		return dao.findAll();
	}

}
