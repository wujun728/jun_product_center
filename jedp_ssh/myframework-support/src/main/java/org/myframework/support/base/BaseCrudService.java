package org.myframework.support.base;

import java.io.Serializable;

import org.myframework.dao.jdbc.impl.ConfigedJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public abstract class BaseCrudService<T, ID extends Serializable> {
	@Autowired
	protected ConfigedJdbc jdbc ;
	
	@Autowired
	protected CrudRepository<T, ID> crudRepository;
	
	public void setCrudRepository(CrudRepository<T, ID> CrudRepository) {
		this.crudRepository = CrudRepository;
	}

	public CrudRepository<T, ID> getCrudRepository() {
		return crudRepository;
	}

	public <S extends T> S save(S entity) {
		return crudRepository.save(entity);
	}
	
	public <S extends T> Iterable<S> batchInsert(Iterable<S> entities ) {
		return crudRepository.save(entities);
	}

	public <S extends T> S update(S entity) {
		return crudRepository.save(entity);
	}

	public T findOne(ID id) {
		try {
			return crudRepository.findOne(id);
		} catch (Exception e) {
			return null;
		}
	}
	
 
}
