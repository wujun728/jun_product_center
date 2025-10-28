package com.jun.plugin.demo.jpa.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.jun.plugin.common.dataaccess.BaseRepository;
import com.jun.plugin.demo.jpa.pojo.User;
import com.slyak.spring.jpa.TemplateQuery;

public interface UserDAO extends BaseRepository<User, java.lang.Integer> {
	public List findByCriteria();
	
	public List testEm();
	
	public void testL1Cache();
	
	@TemplateQuery
	public Page<User> tetFindPage(@Param("account") String account ,Pageable pageable);
	
}
