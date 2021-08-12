package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.FollowDao;
import com.shuogesha.cms.entity.Follow;
import com.shuogesha.cms.service.FollowService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	private FollowDao dao; 

	@Override
	public Follow findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,Long fromUid,Long toUid,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(fromUid!=null&&fromUid>0){
			map.put("from_uid", fromUid);
		}
		if(toUid!=null&&toUid>0){
			map.put("toUid", toUid);
		}
		long totalCount = dao.count(map);
		Pagination<Follow> page = new Pagination<Follow>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Follow> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}
	
	/**
	 * 我的粉丝列表
	 */
	@Override
	public Pagination getFollowPage( String name,Long fromUid,Long toUid,int pageNo, int pageSize) {
	    Map<String, Object> map = new HashMap<String, Object>(); 
	    if(StringUtils.isNotBlank(name)){
	        map.put("name", new StringBuilder("%").append(name).append("%").toString());
	    }
	    if(toUid!=null&&toUid>0){
	        map.put("to_uid", toUid);
	    }
	    long totalCount = dao.count(map);
	    Pagination<Follow> page = new Pagination<Follow>(pageNo, pageSize, totalCount);
	    map.put("pageSize", pageSize);
	    map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
	    List<Follow> datas = dao.queryFollowList(map);
	    page.setDatas(datas);
	    return page; 
	}

	@Override
	public void save(Follow bean) {
		 dao.saveEntity(bean);
 	}

	@Override
	public void update(Follow bean) { 
		 dao.updateById(bean);
	}

	@Override
	public void removeById(Long id) {
		Follow bean= dao.findById(id);
 		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public Follow findByFollw(Follow bean) {
 		return dao.findByFollw(bean);
	}

}
