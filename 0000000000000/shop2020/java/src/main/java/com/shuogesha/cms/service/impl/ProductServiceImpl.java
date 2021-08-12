package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.ProductDao;
import com.shuogesha.cms.entity.Content;
import com.shuogesha.cms.entity.Count;
import com.shuogesha.cms.entity.Product;
import com.shuogesha.cms.service.CountService;
import com.shuogesha.cms.service.ProductService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao dao;
	@Autowired
	private CountService countService;

	@Override
	public Product findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Product> page = new Pagination<Product>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Product> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Product bean) {
		 dao.saveEntity(bean);
		 Count count = countService.saveCount(bean.getId(), Product.class.getSimpleName());
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
	public void update(Product bean) { 
		 dao.updateById(bean);
		 Count count = bean.getCount();
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
	public void updateNumById(Long id) {
		dao.updateNumById(id);
	}

	@Override
	public Pagination getAllPage(String name, Long cat_id,Integer filterIndex,Integer priceOrder, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(cat_id!=null&&cat_id>0){
			map.put("cat_id", cat_id);
		}
		if(filterIndex!=null&&filterIndex>0){
			map.put("filterIndex", filterIndex);
		}
		if(priceOrder!=null&&priceOrder>0){
			map.put("priceOrder", priceOrder);
		}
		long totalCount = dao.countAll(map);
		Pagination<Product> page = new Pagination<Product>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Product> datas = dao.queryAllList(map);
		page.setDatas(datas);
		return page; 
	}

}
