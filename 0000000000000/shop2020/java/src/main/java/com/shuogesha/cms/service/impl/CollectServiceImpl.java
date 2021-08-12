package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.CollectDao;
import com.shuogesha.cms.entity.Collect;
import com.shuogesha.cms.service.CollectService;
import com.shuogesha.cms.service.CountService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class CollectServiceImpl implements CollectService{
	
	@Autowired
	private CollectDao dao;
	@Autowired
	public CountService countService; 

	@Override
	public Collect findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage(String type,Long userId,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(type)){
			map.put("type", type);
		}
		if(userId!=null&&userId>0){
			map.put("userId",userId);
		}
		long totalCount = dao.count(map);
		Pagination<Collect> page = new Pagination<Collect>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Collect> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	} 

	@Override
	public void save(Collect bean) { 
		dao.saveEntity(bean); 
		countService.collectUp(bean.getReferid(),bean.getRefer());  
	}

	@Override
	public void update(Collect bean) { 
		 dao.updateById(bean);
	}

	@Override
	public void removeById(Long id) {
		Collect bean= dao.findById(id);
		countService.collectDown(bean.getReferid(),bean.getRefer());
		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public Collect findBy(Long userId, Long referid, String refer) {
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("userId",userId); 
		map.put("referid",referid); 
		map.put("refer",refer); 
		return  dao.findBy(map);
	}

	@Override
	public Collect findByRefer(Collect bean) {
		return dao.findByRefer(bean);
	}

}
