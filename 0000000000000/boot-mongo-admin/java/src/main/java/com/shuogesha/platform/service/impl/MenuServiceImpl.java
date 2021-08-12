package com.shuogesha.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.DBRef;
import com.shuogesha.platform.dao.MenuDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.service.MenuService;
import com.shuogesha.platform.web.mongo.Pagination;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao dao;

	@Override
	public Menu findById(String id) {
		return dao.findById(id);
	}

	@Override
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination<App> page = new Pagination<App>(pageNo, pageSize, 0);   
		Query query = new Query(); 
 		if(StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		} 
		return dao.findPage(page, query);
	}

	@Override
	public void save(Menu bean) {
		bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		dao.saveEntity(bean);
		if(StringUtils.isNotBlank(bean.getPid())) { 
			Update update= new Update();
			update.addToSet("children", new DBRef("menu", bean.getId()));
			Query query = new Query(); 
 			query.addCriteria(Criteria.where("_id").is(bean.getPid()));
			dao.updateAdd(query, update);
		}
	}

	@Override
	public void update(Menu bean) {
		 dao.updateEntity(bean,bean.getId());
		 if(StringUtils.isNotBlank(bean.getPid())) { 
			Update update= new Update();
			update.addToSet("children", new DBRef("menu", bean.getId()));
			Query query = new Query(); 
 			query.addCriteria(Criteria.where("_id").is(bean.getPid()));
			dao.updateAdd(query, update);
		}
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
	public List<Menu> queryMenuList(String pid) { 
		Query query = new Query(); 
		if (pid != null ) {
			query.addCriteria(Criteria.where("pid").is(pid));
		} 
		List<Order> orders = new ArrayList<>();  
		orders.add(new Order(Direction.ASC, "sort")); 
 		query.with(Sort.by(orders));
		return dao.find(query);
	}

	@Override
	public List<Menu> findAll(String pid) {
		Query query = new Query(); 
		if (pid != null ) {
			query.addCriteria(Criteria.where("pid").is(pid));
		} 
		List<Order> orders = new ArrayList<>();  
		orders.add(new Order(Direction.ASC, "sort")); 
 		query.with(Sort.by(orders));
		return dao.find(query);
	}

	@Override
	public List<Menu> getAllMenus(String pid, String type) { 
		Query query = new Query(); 
		if (pid != null ) {
			query.addCriteria(Criteria.where("pid").is(pid));
		} 
		if (StringUtils.isNotBlank(type)) {
			query.addCriteria(Criteria.where("type").is(type));
		}
		List<Order> orders = new ArrayList<>();  
		orders.add(new Order(Direction.ASC, "sort")); 
 		query.with(Sort.by(orders));
		return dao.find(query);
 	}

	@Override
	public List<String> getAllPerms() { 
		return dao.getAllPerms();
	}

}
