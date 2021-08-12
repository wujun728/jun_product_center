package com.oracle.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.PartsRepBillMapper;
import com.oracle.dao.PartsRepertoryMapper;
import com.oracle.vo.PartsRepBill;

@Service
public class PartsInAndOutService {

	@Autowired
	PartsRepertoryMapper dao;
	
	@Autowired
	PartsRepBillMapper dao1;
	
	@Transactional
	public List<Map<String,Object>> getAll() {
		
		return dao.getAll();	
	}
	
	@Transactional
	public List<Map<String,Object>> getJson(String type){
		return dao.getCode(type);
	}
	
	@Transactional
	public List<Map<String,Object>> getJson1(){
		return dao.getName();
	}
	
	@Transactional
	public List<Map<String,Object>> getJson2(){
		return dao.getEmps();
	}
	
	@Transactional
	public void insertBill(PartsRepBill bill) {
		dao1.insert(bill);
	}
	
	@Transactional
	public List<Integer> getPartsid(){
		
		return dao.getPartsid();
	}
	
	@Transactional
	public void updateCount1(Integer partsidreqcount,Integer partsid){
		
		dao.updateCount1(partsidreqcount, partsid);
	}
	
	@Transactional
	public void updateCount2(Integer partsidreqcount,Integer partsid){
		
		dao.updateCount2(partsidreqcount, partsid);
	}
	
	@Transactional
	public void insertBill1(Integer count,Integer id) {
		
		dao.insertBill(count, id);
	}
	
	@Transactional
	public List<Map<String,Object>> getParts(Map<String,Object> map){
		
		return dao.getPartsByLike(map);
	}
	
	@Transactional
	public Map<String,Object> getSelected(Integer partsid){
		
		return dao.getSelected(partsid);
	}
	
}
