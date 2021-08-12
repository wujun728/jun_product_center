package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.PraiseDao;
import com.shuogesha.cms.entity.Praise;
import com.shuogesha.cms.service.CountService;
import com.shuogesha.cms.service.PraiseService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class PraiseServiceImpl implements PraiseService{
	
	@Autowired
	private PraiseDao dao;
	
	@Autowired
	public CountService countService;

	@Override
	public Praise findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Praise> page = new Pagination<Praise>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Praise> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Praise bean) {
		 dao.saveEntity(bean);
		 countService.praiseUp(bean.getReferid(),bean.getRefer()); 
	}

	@Override
	public void update(Praise bean) { 
		 dao.updateById(bean);
	}

	@Override
	public void removeById(Long id) {
		Praise bean= dao.findById(id);
		countService.praiseDown(bean.getReferid(),bean.getRefer());
		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public Praise findByRefer(Praise bean) { 
		return  dao.findByRefer(bean);
	}

	@Override
	public Praise findBy(Long userId, Long referid, String refer) {
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("userId",userId); 
		map.put("referid",referid); 
		map.put("refer",refer); 
		return  dao.findBy(map);
	}
	
	

}
