package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.ContentDao;
import com.shuogesha.cms.entity.Content;
import com.shuogesha.cms.entity.Count;
import com.shuogesha.cms.service.ContentService;
import com.shuogesha.cms.service.CountService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private ContentDao dao;
	
	@Autowired
	private CountService countService;

	@Override
	public Content findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,Integer channelId,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(channelId!=null&&channelId>0){
			map.put("channelId", channelId);
		}
		long totalCount = dao.count(map);
		Pagination<Content> page = new Pagination<Content>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Content> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Content bean) {
		 dao.saveEntity(bean);
		 Count count = countService.saveCount(bean.getId(), Content.class.getSimpleName());
		 if (bean.getCount() != null && count != null) {// 如果前端传了内容那么直接保存
			if (bean.getCount().getViews() != null && bean.getCount().getViews() > 0) {
				count.setViews(bean.getCount().getViews());
			}
			if (bean.getCount().getCollect() != null && bean.getCount().getCollect() > 0) {
				count.setCollect(bean.getCount().getCollect());
			}
			if (bean.getCount().getComments() != null && bean.getCount().getComments() > 0) {
				count.setComments(bean.getCount().getComments());
			}
			if (bean.getCount().getPraise() != null && bean.getCount().getPraise() > 0) {
				count.setPraise(bean.getCount().getPraise());
			}
			countService.update(count);
		}
	}

	@Override
	public void update(Content bean) { 
		 dao.updateById(bean);
//		 Count count = bean.getCount();
//	     if (bean.getCount() != null && count != null) {// 如果前端传了内容那么直接保存
//			if (bean.getCount().getViews() != null && bean.getCount().getViews() > 0) {
//				count.setViews(bean.getCount().getViews());
//			}
//			if (bean.getCount().getCollect() != null && bean.getCount().getCollect() > 0) {
//				count.setCollect(bean.getCount().getCollect());
//			}
//			if (bean.getCount().getComments() != null && bean.getCount().getComments() > 0) {
//				count.setComments(bean.getCount().getComments());
//			}
//			if (bean.getCount().getPraise() != null && bean.getCount().getPraise() > 0) {
//				count.setPraise(bean.getCount().getPraise());
//			}
//			countService.update(count);
//		}
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

}
