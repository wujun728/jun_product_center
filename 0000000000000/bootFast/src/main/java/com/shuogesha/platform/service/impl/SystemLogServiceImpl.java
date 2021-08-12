package com.shuogesha.platform.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shuogesha.platform.dao.SystemLogDao;
import com.shuogesha.platform.dao.UnifiedUserDao;
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
	public SystemLog findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<SystemLog> page = new Pagination<SystemLog>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<SystemLog> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(SystemLog bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(SystemLog bean) { 
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
	@Transactional
 	public void log(Long uid, String content, String ipAddr, String type) {
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
