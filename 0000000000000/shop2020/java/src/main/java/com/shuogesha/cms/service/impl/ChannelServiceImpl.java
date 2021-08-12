package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.ChannelDao;
import com.shuogesha.cms.entity.Channel;
import com.shuogesha.cms.service.ChannelService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	private ChannelDao dao;

	@Override
	public Channel findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,Long pId,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(pId!=null&&pId>0){
			map.put("pid", pId);
		}
		long totalCount = dao.count(map);
		Pagination<Channel> page = new Pagination<Channel>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Channel> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Channel bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Channel bean) { 
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
	public List<Channel> findAll(Long pId) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(pId!=null&&pId>0){
			map.put("pid", pId);
		}
		return dao.findAll(map);
	}

}
