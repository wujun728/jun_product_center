package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.Captcha;

public interface CaptchaService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Captcha findById(Long id);

	void save(Captcha bean);

	void update(Captcha bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Captcha findByName(String username);

	void save(String email, String code);
}