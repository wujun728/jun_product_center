package com.jun.plugin.common.dataaccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.jun.plugin.query.jpa.expr.AttrExpression;
import com.jun.plugin.query.sql.expr.SQLExpression;

public class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T, ID>{
	@Autowired
	private BaseRepository<T, ID> baseRepository;

	
	//-----------------------------------@Transactional--------------------
	@Override
	@Transactional
	public T save(T entity) {
		return baseRepository.save(entity);
	}
	
	
	@Override
	@Transactional
	public List<T> save(Iterable<T> entities) {
		// TODO Auto-generated method stub
		return baseRepository.save(entities);
	}
	
	@Override
	@Transactional
	public T update(T entity, String... ignoreProperties) {
		return baseRepository.update(entity, ignoreProperties);
	}

	@Override
	@Transactional
	public T update(T entity) {
		return baseRepository.update(entity);
	}

	@Override
	@Transactional
	public void delete(ID id) {
		baseRepository.delete(id);
	}
	
	@Override
	@Transactional
	public boolean delete(ID id, boolean checkRelation) {
		// TODO Auto-generated method stub
		return baseRepository.delete(id, checkRelation);
	}
	
	@Override
	@Transactional
	public void deleteIds(Iterable<ID> ids) {
		baseRepository.deleteIds(ids);
	}

	//-----------------------------------@Transactional--------------------
	@Override
	public T getById(ID id) {
		return baseRepository.getById(id);
	}

	@Override
	public List<T> findAll() {
		return baseRepository.findAll();
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		// TODO Auto-generated method stub
		return baseRepository.findAll(ids);
	}
	
	@Override
	public List<T> findAll(Sort sort) {
		return baseRepository.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	@Override
	public List<T> findList(Iterable<ID> ids) {
		List<T> result = new ArrayList<T>();
		if (ids != null) {
			for (ID id : ids) {
				T entity = getById(id);
				if (entity != null) {
					result.add(entity);
				}
			}
		}
		return result;
	}


	@Override
	public Page<T> findPage(T example, Pageable pageable) {
		return baseRepository.findPage(example,pageable);
	}

	@Override
	public List<T> findList(T example, Sort sort) {
		return baseRepository.findList(example,sort);
	}

	@Override
	public List<T> findList(T example) {
		return baseRepository.findList(example);
	}

	@Override
	public T getOne(T example) {
		return baseRepository.getOne(example);
	}

	@Override
	public long count(T example) {
		return baseRepository.count(example);
	}

	@Override
	public Page<T> findPage(Pageable pageable, AttrExpression... exprs) {
		return baseRepository.findPage(pageable, exprs);
	}

	@Override
	public List<T> findList(Sort sort, AttrExpression... exprs) {
		return baseRepository.findList(sort,exprs);
	}

	@Override
	public List<T> findList(AttrExpression... exprs) {
		return baseRepository.findList(exprs);
	}

	@Override
	public T getOne(AttrExpression... exprs) {
		return baseRepository.getOne(exprs);
	}

	@Override
	public long count(AttrExpression... exprs) {
		return baseRepository.count(exprs);
	}

	@Override
	public Page<T> findPage(Example<T> example, Pageable pageable) {
		return baseRepository.findAll(example, pageable);
	}

	@Override
	public List<T> findList(Example<T> example, Sort sort) {
		return baseRepository.findAll(example, sort);
	}

	@Override
	public List<T> findList(Example<T> example) {
		return baseRepository.findAll(example);
	}

	@Override
	public long count(Example<T> example) {
		return baseRepository.count(example);
	}
	@Override
	public List<T> random(int n) {
		return baseRepository.random(n);
	}
	@Override
	public List<T> random(int n, SQLExpression... exprs) {
		return baseRepository.random(n, exprs);
	}

	
	@Override
	@Transactional
	public long executeUpdate(String updateName, Object obj) {
		// TODO Auto-generated method stub
		return baseRepository.executeUpdate(updateName,obj);
	}



}
