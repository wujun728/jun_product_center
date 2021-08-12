package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.NoticeDao;
import com.shuogesha.cms.entity.Notice;
import com.shuogesha.cms.service.NoticeService;
import com.shuogesha.platform.web.mongo.Pagination;

//import io.seata.spring.annotation.GlobalTransactional;


@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDao dao;

	@Override
	public Notice findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Notice> page = new Pagination<Notice>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Notice> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
//	@Transactional
//    @GlobalTransactional(timeoutMills = 300000, name = "base2020-seata-example")
	public void save(Notice bean) {
		 dao.saveEntity(bean);
 	}

	@Override
	public void update(Notice bean) { 
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

}
