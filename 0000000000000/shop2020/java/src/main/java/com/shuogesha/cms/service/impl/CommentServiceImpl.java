package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.CommentDao;
import com.shuogesha.cms.entity.Comment;
import com.shuogesha.cms.service.CommentService;
import com.shuogesha.cms.service.CountService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao dao;

	@Override
	public Comment findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage(String name,String refer, Long referid,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(StringUtils.isNotBlank(refer)){
			map.put("refer", refer);
		}
		if(referid!=null){
			map.put("referid", referid);
		}
		long totalCount = dao.count(map);
		Pagination<Comment> page = new Pagination<Comment>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Comment> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Comment bean) {
		 dao.saveEntity(bean);
		 countService.commentUp(bean.getReferid(),bean.getRefer());
	}

	@Override
	public void update(Comment bean) { 
		 dao.updateById(bean);
	}

	@Override
	public void removeById(Long id) {
		Comment bean= dao.findById(id);
		if(bean!=null){//删除依赖
			countService.commentDown(bean.getReferid(),bean.getRefer());
		} 
		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	 

	@Override
	public List<Comment> findList(String refer, Long referid, Integer parent) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(parent!=null){
			map.put("parentid", parent);
		}
		if(StringUtils.isNotBlank(refer)){
			map.put("refer", refer);
		}
		if(referid!=null){
			map.put("referid", referid);
		} 
 		List<Comment> datas = dao.findList(map);
		return datas;
	}
	
	@Autowired
	public CountService countService;

	@Override
	public void removeByNewsId(String refer, Long referid) {
		Map<String, Object> map = new HashMap<String, Object>();  
 		map.put("referid", referid);
 		map.put("refer", refer);
 		dao.removeByNewsId(map);
 	}

}
