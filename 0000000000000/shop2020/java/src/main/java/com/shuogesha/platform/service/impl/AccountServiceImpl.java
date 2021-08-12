package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.AccountDao;
import com.shuogesha.platform.entity.Account;
import com.shuogesha.platform.service.AccountService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDao dao;

	@Override
	public Account findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		long totalCount = dao.count(map);
		Pagination<Account> page = new Pagination<Account>(pageNo, pageSize, totalCount);
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Account> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Account bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Account bean) { 
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
	public Account init(Long id) {
		Account bean = findById(id);
		if(bean!=null) {
			return bean;
		}
		bean=new Account();
		bean.setId(id);
		dao.saveEntity(bean);
		return bean;
	}

}
