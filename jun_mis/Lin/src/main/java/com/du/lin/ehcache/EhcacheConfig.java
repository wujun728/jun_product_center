package com.du.lin.ehcache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;


@Configuration
@EnableCaching
public class EhcacheConfig{ 
	@Bean
	public EhCacheCacheManager ehCacheManager(CacheManager cm){
		EhCacheCacheManager manager = new EhCacheCacheManager();
		manager.setCacheManager(cm);
		return manager;
	}
	@Bean
	public EhCacheManagerFactoryBean cacheManager(){
		EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		return ehCacheFactoryBean;
	}
	
	
}
