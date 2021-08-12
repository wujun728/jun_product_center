package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.PaybillDao;
import com.shuogesha.platform.entity.Paybill;
import com.shuogesha.platform.entity.Paylog;
import com.shuogesha.platform.service.AccountService;
import com.shuogesha.platform.service.PaybillService;
import com.shuogesha.platform.service.PaylogService;
import com.shuogesha.platform.service.SiteService;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;


@Service
public class PaybillServiceImpl implements PaybillService{
	
	@Autowired
	private PaybillDao dao; 
	@Autowired
	private PaylogService paylogService;
	@Autowired
	private AccountService accountService;  
	@Autowired
	private SiteService siteService; 
	@Autowired
	private UnifiedUserService unifiedUserService; 
	 
	

	@Override
	public Paybill findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Paybill> page = new Pagination<Paybill>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Paybill> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Paybill bean) {
		Paybill entity= dao.findById(bean.getId());
		if(entity==null){
			 dao.saveEntity(bean);
		}else{
			dao.updateById(bean);
		} 
	}

	@Override
	public void update(Paybill bean) { 
		 dao.updateById(bean);
	}

	@Override
	public void removeById(String id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}
	/**
	 * 付款，只允许调用一次
	 */
	@Override
	public Paylog pay(Paylog payLog, Integer status, boolean pay) {
		Paybill entity = findById(payLog.getId());
		if(entity!=null&&entity.getState()==0){
			pay_success(entity, status, pay);
		}
		payLog.setStatus("1");//设置支付成功
		payLog.setDateline(RequestUtils.getNow());
		paylogService.update(payLog); 
		return payLog;
	}
	
	/*
	 * 交易完成
	 */
	private void pay_success(Paybill entity, Integer state,boolean pay) { 
		 
		entity.setState(state);
		entity.setPayTime(RequestUtils.getNow()); 
 		if (Paybill.ORDER.equals(entity.getName())){//默认订单
			//修改支付状态
			entity.setStatus(1); 
			Paybill bean = findById(entity.getId());
			//去修改订单的状态
 		} 
		update(entity);  
	}

	@Override
	public Pagination getPageByAcount(String type, Integer unifiedUserId, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(type)){
			map.put("name",type);
		}
		map.put("payeer_id",unifiedUserId);
		long totalCount = dao.count(map);
		Pagination<Paybill> page = new Pagination<Paybill>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Paybill> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void re(String orderNo) {
		List<Paybill> list = dao.findByOrderNo(orderNo);
		for (Paybill payBill : list) {
//			if (payBill!=null&&"1".equals(payBill.getState())&&"1".equals(payBill.getStatus())) {
//				accountService.upAccount(payBill.getPayeer_id(),payBill.getTran_amount(),false);
//				payBill.setStatus(-1); 
//				update(payBill);
//			}
		}
	}
}
