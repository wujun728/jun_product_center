package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.CaptchaDao;
import com.shuogesha.platform.entity.Captcha;
import com.shuogesha.platform.service.CaptchaService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class CaptchaServiceImpl implements CaptchaService{
	
	@Autowired
	private CaptchaDao dao;

	@Override
	public Captcha findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Captcha> page = new Pagination<Captcha>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Captcha> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Captcha bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Captcha bean) { 
		 dao.updateById(bean);
	}

	@Override
	public void removeById(Long id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public Captcha findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public void save(String name, String code) {
		dao.removeByName(name);
		Captcha bean= new Captcha();
		bean.setCaptcha(code);
		bean.setName(name);
		save(bean);
	}

	 

}
