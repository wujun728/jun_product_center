package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.cms.dao.MessageDao;
import com.shuogesha.cms.entity.Message;
import com.shuogesha.cms.service.MessageService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageDao dao;

	@Override
	public Message findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,Long userId,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(userId!=null&&userId>0){
			map.put("userId",userId);
		}
		long totalCount = dao.count(map);
		Pagination<Message> page = new Pagination<Message>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Message> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Message bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Message bean) { 
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
	public void saveSend(Message bean, Boolean send) {
		 dao.saveEntity(bean);
		 if(send!=null&&send){
			 JSONObject data = JSONObject.parseObject(bean.getData());
			 Map<String, String> extras = new HashMap<String, String>();
			 extras.put("model", bean.getName());
			 extras.put("id", bean.getId().toString());
			 if(bean.getUserId()!=null&&bean.getUserId()>0){
//				 JpushUtils.pushNotice(1,data.getString("name"), data.getString("title"),bean.getUser_id().toString(), extras);
			 } else{
//				 JpushUtils.pushNotice(1,data.getString("name"), data.getString("title"), extras);
			 }
		 }
		
	}
 
}
