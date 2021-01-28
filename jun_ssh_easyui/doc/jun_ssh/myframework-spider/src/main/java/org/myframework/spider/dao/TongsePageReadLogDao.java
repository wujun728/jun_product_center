package org.myframework.spider.dao;


import org.myframework.spider.entity.TongsePageReadLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TongsePageReadLogDao extends CrudRepository<TongsePageReadLog,String> {
	
	@Query("select a from TongsePageReadLog a where isOk = '0' ")
	TongsePageReadLog findNextStart();
}
