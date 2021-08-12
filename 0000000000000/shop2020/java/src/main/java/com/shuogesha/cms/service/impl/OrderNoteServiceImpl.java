package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.OrderNoteDao;
import com.shuogesha.cms.entity.OrderNote;
import com.shuogesha.cms.service.OrderNoteService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class OrderNoteServiceImpl implements OrderNoteService{
	
	@Autowired
	private OrderNoteDao dao;

	@Override
	public OrderNote findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<OrderNote> page = new Pagination<OrderNote>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<OrderNote> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(OrderNote bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(OrderNote bean) { 
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
	public List<OrderNote> findList(Long orderId) { 
		return dao.findList(orderId);
	}

}
