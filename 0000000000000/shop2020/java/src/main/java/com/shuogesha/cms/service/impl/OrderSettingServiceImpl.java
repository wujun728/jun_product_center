package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.OrderSettingDao;
import com.shuogesha.cms.entity.OrderSetting;
import com.shuogesha.cms.service.OrderSettingService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class OrderSettingServiceImpl implements OrderSettingService{
	
	@Autowired
	private OrderSettingDao dao;

	@Override
	public OrderSetting findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<OrderSetting> page = new Pagination<OrderSetting>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<OrderSetting> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(OrderSetting bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(OrderSetting bean) { 
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
	public OrderSetting findOne() { 
		return dao.findOne();
	}

}
