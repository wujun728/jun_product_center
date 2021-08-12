package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.ProductCatDao;
import com.shuogesha.cms.entity.ProductCat;
import com.shuogesha.cms.service.ProductCatService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class ProductCatServiceImpl implements ProductCatService{
	
	@Autowired
	private ProductCatDao dao;

	@Override
	public ProductCat findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage(String name,Long pId,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(pId!=null&&pId>0){
			map.put("pid", pId);
		}
		long totalCount = dao.count(map);
		Pagination<ProductCat> page = new Pagination<ProductCat>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<ProductCat> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(ProductCat bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(ProductCat bean) { 
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
	public List<ProductCat> findAll(Long pId) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(pId!=null&&pId>0){
			map.put("pid", pId);
		}
		return dao.findAll(map);
	}
	
	@Override
	public List<ProductCat> findAllProductCats(Long pid) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(pid!=null&&pid>0){
			map.put("pid", pid);
		}
		return dao.findAllProductCats(map);
	}
}
