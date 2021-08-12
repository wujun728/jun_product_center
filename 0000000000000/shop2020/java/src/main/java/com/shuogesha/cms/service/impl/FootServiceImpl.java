package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.FootDao;
import com.shuogesha.cms.entity.Foot;
import com.shuogesha.cms.service.FootService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class FootServiceImpl implements FootService{
	
	@Autowired
	private FootDao dao;

	@Override
	public Foot findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,Long userId,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(userId!=null&&userId>0){
			map.put("userId", userId);
		}
		long totalCount = dao.count(map);
		Pagination<Foot> page = new Pagination<Foot>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Foot> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Foot bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Foot bean) { 
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
	public Foot saveByFoot(Foot foot) {
		Foot bean=dao.findByRefer(foot);
		if (bean == null) {// 已经点赞
			save(foot);
		}else {
			foot.setId(bean.getId());
			update(foot);
		}
		 return foot;
	}

}
