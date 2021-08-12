package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.Captcha;

public interface CaptchaDao { 
	
	void saveEntity(Captcha bean);

	Captcha findById(Long id);

	void updateById(Captcha bean);
 
	void removeById(Long id);
	
	List<Captcha> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	Captcha findByName(String name);

	void removeByName(String name);
}