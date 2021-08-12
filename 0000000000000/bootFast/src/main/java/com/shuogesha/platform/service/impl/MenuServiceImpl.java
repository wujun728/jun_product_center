package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.MenuDao;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.service.MenuService;
import com.shuogesha.platform.web.mongo.Pagination;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao dao;

	@Override
	public Menu findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(name)) {
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		} 
		long totalCount = dao.count(map);
		Pagination<Menu> page = new Pagination<Menu>(pageNo, pageSize, totalCount);
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize) * ((Integer.valueOf(pageNo) - 1)));
		List<Menu> datas = dao.queryList(map);
		page.setDatas(datas);
		return page;
	}

	@Override
	public void save(Menu bean) {
		dao.saveEntity(bean);
	}

	@Override
	public void update(Menu bean) {
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
	public List<Menu> queryMenuList(Long pid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pid != null && pid > 0) {
			map.put("pid", pid);
		}
		return dao.queryMenuList(map);
	}

	@Override
	public List<Menu> findAll(Long pid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pid != null && pid > 0) {
			map.put("pid", pid);
		}
		return dao.findAll(map);
	}

	@Override
	public List<Menu> getAllMenus(Long pid, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pid != null && pid > 0) {
			map.put("pid", pid);
		}
		if (StringUtils.isNotBlank(type)) {
			map.put("type", type);
		}
		return dao.getAllMenus(map);
	}

}
