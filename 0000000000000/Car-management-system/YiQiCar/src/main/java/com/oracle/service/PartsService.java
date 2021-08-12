package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.PartsMapper;
import com.oracle.vo.Parts;

@Service
public class PartsService {

	@Autowired
	PartsMapper dao;
	
	@Transactional(readOnly = true)
	public List<Parts> getAll(){
		return dao.getAll();
	}
	
	@Transactional
	public void insert(Parts p){
		dao.insert(p);
	}
	
	@Transactional(readOnly = true)
	public List<Parts> getPartsByLike(String PartsName){
		return dao.getPartsByLiike(PartsName);
	}
	
	@Transactional
	public Parts selectById(Integer partsid) {
		return dao.selectByPrimaryKey(partsid);
	}
	
	@Transactional
	public void update(Parts p) {
		dao.updateByPrimaryKey(p);
	}
	
	@Transactional
	public void delete(Integer partsid) {
		dao.deleteByPrimaryKey(partsid);
	}
}
