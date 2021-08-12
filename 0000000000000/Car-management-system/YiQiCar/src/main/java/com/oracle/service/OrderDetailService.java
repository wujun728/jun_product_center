package com.oracle.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.OrderDetailMapper;
import com.oracle.vo.OrderDetail;

@Service
public class OrderDetailService {

	@Autowired
	OrderDetailMapper dao;
	
	@Transactional
	public int getOrderId(String ordercode) {
		
		return dao.getOrderId(ordercode);
	}
	
	@Transactional
	public void insert(OrderDetail detail) {
		
		dao.insertSelective(detail);
	}
	
	@Transactional
	public void updateNum(Integer id,Integer count) {
		
		dao.updateNum(id, count);
	}
	
	@Transactional
	public List<Map<String,Object>> getChecks(Map<String,Object> map){
		
		return dao.getChecks(map);
	}
	
}
