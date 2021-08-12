package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.CartDao;
import com.shuogesha.cms.entity.Cart;
import com.shuogesha.cms.service.CartService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartDao dao;

	@Override
	public Cart findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,Long userId,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(userId!=null&&userId>0){
			map.put("userId", userId);
		}
		long totalCount = dao.count(map);
		Pagination<Cart> page = new Pagination<Cart>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Cart> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Cart bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Cart bean) { 
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
	public Cart findByRefer(Cart bean) {
		 
		return dao.findByRefer(bean);
	}

	@Override
	public void removeByUserId(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();  
		if(userId!=null&&userId>0){
			map.put("userId", userId);
		}
		dao.removeByUserId(map);
	}

}
