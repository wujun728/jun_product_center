package com.shuogesha.platform.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shuogesha.platform.dao.SystemLogDao;
import com.shuogesha.platform.dao.UnifiedUserDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.SystemLog;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.SystemLogService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class SystemLogServiceImpl implements SystemLogService{
	@Autowired
	private UnifiedUserDao unifiedUserDao;
	@Autowired
	private SystemLogDao dao;

	@Override
	public SystemLog findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Pagination<App> page = new Pagination<App>(pageNo, pageSize, 0);   
		Query query = new Query(); 
 		if(StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		} 
		return dao.findPage(page, query);
	}

	@Override
	public void save(SystemLog bean) {
		 bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		 dao.saveEntity(bean);
	}

	@Override
	public void update(SystemLog bean) { 
		 dao.updateEntity(bean,bean.getId());
	}

	@Override
	public void removeById(String id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	@Transactional
 	public void log(String uid, String content, String ipAddr, String type) {
 		UnifiedUser unifiedUser=unifiedUserDao.findById(uid);
		if(unifiedUser!=null){
			SystemLog bean=new SystemLog();
			bean.setContent(content);
			bean.setUsername(unifiedUser.getUsername());
			bean.setName(unifiedUser.getId().toString());
			bean.setIp(ipAddr);
			bean.setType(type);
			bean.setDateline(new Date());
			bean.setUserId(uid);
			save(bean);
 		}
		
	}

}
