package org.myframework.dao.orm;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

public class MyRepositoryFactory extends JpaRepositoryFactory {   
	  
    public MyRepositoryFactory(EntityManager entityManager) {   
        super(entityManager);   
    }   
 
    @Override  
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {   
        return MyRepositoryImpl.class;   
    }   
}  
