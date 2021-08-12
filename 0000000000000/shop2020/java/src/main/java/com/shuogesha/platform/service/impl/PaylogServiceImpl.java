package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.PaylogDao;
import com.shuogesha.platform.entity.Paylog;
import com.shuogesha.platform.service.PaylogService;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;


@Service
public class PaylogServiceImpl implements PaylogService{
	
	@Autowired
	private PaylogDao dao;

	@Override
	public Paylog findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Paylog> page = new Pagination<Paylog>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Paylog> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Paylog bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Paylog bean) { 
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
	
	
	public Paylog create(String orderNo, String model, String status) {  
		Paylog entity = findById(orderNo);
		if(entity!=null){
			entity.setName(model);
			entity.setStatus(status);
			dao.updateById(entity);
		}else{
			entity = new Paylog();
			entity.setId(orderNo);
			entity.setDateline(com.shuogesha.platform.web.util.RequestUtils.getNow());
			entity.setName(model);
			entity.setStatus(status);
			dao.saveEntity(entity);
			
		}
		return entity;
	}
	
	@Override
	public Paylog add(String out_trade_no, String trade_no, String trade_status){
		//String out_trade_no, String trade_no, String trade_status
		Paylog entity = findById(out_trade_no);
		if(entity!=null){ 
			entity.setTradeNo(trade_no);
			entity.setTradeStatus(trade_status);
			dao.updateById(entity);
		}else {
			entity = new Paylog();
			entity.setId(out_trade_no);
			entity.setDateline(RequestUtils.getNow());
			entity.setTradeNo(trade_no);
			entity.setTradeStatus(trade_status);
			dao.saveEntity(entity);
		} 
		return entity;
	}

}
