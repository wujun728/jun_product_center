package com.jun.plugin.common.dataaccess;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.slyak.spring.jpa.ContextHolder;
import com.slyak.spring.jpa.GenericJpaRepositoryFactory;

/**
 * 
 * @author klguang
 *
 * @param <R>
 *            Repository type
 * @param <T>
 *            Entity type
 * @param <ID>
 *            Entity id type
 */
public class BaseRepositoryFactoryBean<R extends Repository<T, ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID> implements ApplicationContextAware {

	public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
		// TODO Auto-generated constructor stub
	}

	@Override
	public RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseRepositoryFactory(entityManager);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ContextHolder.appContext = applicationContext;
	}

	private static class BaseRepositoryFactory extends GenericJpaRepositoryFactory {
		private final EntityManager entityManager;

		public BaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		/**
		 * 设置具体的实现类是BaseRepositoryImpl
		 */
		@Override
		protected Object getTargetRepository(RepositoryInformation information) {
			// TODO Auto-generated method stub
			JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
			return new BaseRepositoryImpl<>(entityInformation, entityManager);
		}

		/**
		 * 设置具体的实现类的class
		 */
		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return BaseRepositoryImpl.class;
		}
	}

}
