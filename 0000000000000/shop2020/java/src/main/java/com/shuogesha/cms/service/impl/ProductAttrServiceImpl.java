package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.ProductAttrDao;
import com.shuogesha.cms.entity.ProductAttr;
import com.shuogesha.cms.service.ProductAttrService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class ProductAttrServiceImpl implements ProductAttrService{
	
	@Autowired
	private ProductAttrDao dao;

	@Override
	public ProductAttr findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<ProductAttr> page = new Pagination<ProductAttr>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<ProductAttr> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(ProductAttr bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(ProductAttr bean) { 
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
	public void saveAll(List<ProductAttr> list, Long productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", productId);
		map.put("productAttrList", list);
		dao.removeAll(map);
		if(list!=null&&list.size()>0) {
			dao.addAll(map);
		} 
	}

}
