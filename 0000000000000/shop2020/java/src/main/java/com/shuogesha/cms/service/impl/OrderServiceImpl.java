package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.OrderDao;
import com.shuogesha.cms.entity.Order;
import com.shuogesha.cms.service.OrderService;
import com.shuogesha.mq.order.OrderDelayedSender;
import com.shuogesha.platform.service.PaybillService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao dao;
	@Autowired
	private PaybillService paybillService;
	@Autowired
	private OrderDelayedSender orderDelayedSender;

	@Override
	public Order findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<Order> page = new Pagination<Order>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Order> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Order bean) {
		 dao.saveEntity(bean);
		 if(Order.ORDER_JIAOYI.equals(bean.getStatus())&&Order.PAY_WEIZHIFU.equals(bean.getPay())){
			//加入延迟取消队列
			orderDelayedSender.cancelOrder(bean.getId());
     	 }  
	}

	@Override
	public void update(Order bean) { 
		 dao.updateById(bean);
		 if(Order.ORDER_JIAOYI.equals(bean.getStatus())&&Order.PAY_WEIZHIFU.equals(bean.getPay())){
			//加入延迟取消队列
			orderDelayedSender.cancelOrder(bean.getId());
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
	public Order cancel(Order bean) {
		if(Order.PAY_YIZHIFU.equals(bean.getPay())){
			//取消已经支付的并退款
			paybillService.re(bean.getOrderNo());
		}
		bean.setStatus(Order.ORDER_CANCEL);
		update(bean);
		return bean;
	}

	@Override
	public Pagination findFrontPage(String name, Long userId, Integer state,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(userId!=null&&userId>0){
			map.put("userId",userId);
		}
		if(state!=null){
			map.put("state",state);
		}
		long totalCount = dao.countFront(map);
		Pagination<Order> page = new Pagination<Order>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Order> datas = dao.queryFrontList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public Order findByOrderNo(String orderNo) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(orderNo)){
			map.put("orderNo", orderNo);
		}
		return dao.findByOrderNo(map);
	}

	@Override
	public void cancelAllScan(Integer pay_timeout) {
 		dao.cancelAllScan(pay_timeout);
	}

}
