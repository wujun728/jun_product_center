package com.oracle.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.OrderMapper;
import com.oracle.vo.Order;

@Service
public class OrderService {

	@Autowired
	OrderMapper dao;
	
	@Transactional
	public List<Map<String,Object>> getAll(){
		
		return dao.getAllOrder();	
	}
	
	@Transactional
	public List<Map<String,Object>> getOrders(Map<String,Object> map){
		
		return dao.getOrders(map);
		
	}
	
	@Transactional
	public Map<String,Object> getOrderById(Integer id){
	
		
		return dao.getOrderById(id);
	}
	
	@Transactional
	public List<String> getOrderCode(){
		
		return dao.getOrderCode();
	}
	
	@Transactional
	public void insert(Order order) {
		
		dao.insert(order);
	}
	
	@Transactional
	public List<Map<String,Object>> getAllBills() {
		
		return dao.getAllBills();
	}
	
	@Transactional
	public void updateFlag(Integer id) {
		
		dao.updateFlag( id);
	}
	
	@Transactional
	public void updateFlag1(Integer id) {
		
		dao.updateFlag1( id);
	}
	
	@Transactional
	public List<Map<String,Object>> getBillDetail(Integer orderid){
		
		return dao.getBillDetail(orderid);
	}
	
}
