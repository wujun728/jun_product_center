package org.myframework.dao.orm;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends  EntityQuery<T> , JpaRepository<T, ID> {
	String sharedCustomMethod();
}
