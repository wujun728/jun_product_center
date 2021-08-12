package com.oracle.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.PartsRepBillMapper;
import com.oracle.vo.PartsRepBill;

@Service
public class PartsBillService {

	@Autowired
	PartsRepBillMapper dao;
	
	@Transactional
	public List<Map<String, Object>> getAll(){
		
		List<Map<String,Object>> list = dao.getAll();

		return list;
	}
	
	@Transactional
	public List<Map<String,Object>> getJson(String type){
		return dao.getCode(type);
	}
	
	@Transactional
	public List<Map<String,Object>> getBillsByLike(Map<String,Object> map){
		return dao.getBillsByLike(map);
	}
	
	
}
