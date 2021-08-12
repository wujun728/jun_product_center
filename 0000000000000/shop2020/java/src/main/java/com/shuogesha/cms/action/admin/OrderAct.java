package com.shuogesha.cms.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuogesha.cms.entity.Address;
import com.shuogesha.cms.entity.Order;
import com.shuogesha.cms.entity.OrderNote;
import com.shuogesha.cms.entity.Shipping;
import com.shuogesha.cms.service.OrderNoteService;
import com.shuogesha.cms.service.OrderService;
import com.shuogesha.cms.service.ShippingService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.version.SysLog;
import com.shuogesha.platform.web.CmsUtils;
import com.shuogesha.platform.web.mongo.Pagination;

@RestController 
@RequestMapping("/api/order/")
public class OrderAct {
	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = orderService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		Order bean = orderService.findById(id);
		if(bean!=null&&StringUtils.isNotBlank(bean.getContent())) {
			bean.setProductJSON(JSON.parseObject(bean.getContent()));
		}
		List<OrderNote> orderNotes=orderNoteService.findList(id);
		bean.setOrderNotes(orderNotes);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object oSave(@RequestBody Order bean) {
 		orderService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody Order bean) {
		orderService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	@SysLog(description = "删除订单")
	public @ResponseBody Object o_delete(Long[] ids) {
		orderService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	} 
	 
	/**
	 * 取消订单
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@SysLog(description = "取消订单")
	public @ResponseBody Object cancel(@RequestBody Order bean, HttpServletRequest request) {
		Order order = orderService.findById(bean.getId());
		if(Order.ORDER_CANCEL.equals(order.getStatus())){ 
			return new JsonResult(ResultCode.FAIL,"取消失败！",null);
		} 
		bean=orderService.cancel(order);
		//记录日志
		OrderNote orderNote= new OrderNote(order.getStatus(), order.getPay(), order.getShippingStatus(), "取消订单", order.getId(), CmsUtils.getUser(request));
		orderNoteService.save(orderNote);
		return new JsonResult(ResultCode.SUCCESS,bean);
	}
	/**
	 * 修改支付状态
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@SysLog(description = "修改订单支付状态")
	public @ResponseBody Object pay(@RequestBody Order bean, HttpServletRequest request) {
		Order order = orderService.findById(bean.getId());
		//如果是支付
		if(Order.PAY_YIZHIFU.equals(bean.getPay())) {
			order.setPayTime(new Date());
		}
		order.setPay(bean.getPay());
		orderService.update(order);
		//记录日志
		OrderNote orderNote= new OrderNote(order.getStatus(), order.getPay(), order.getShippingStatus(), "修改支付状态", order.getId(), CmsUtils.getUser(request));
		orderNoteService.save(orderNote);
		return new JsonResult(ResultCode.SUCCESS,"操作成功",order);
	}
	/**
	 * 修改状态
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/shipping", method = RequestMethod.POST)
	@SysLog(description = "修改订单物流状态")
	public @ResponseBody Object shipping(@RequestBody Order bean, HttpServletRequest request) {
 		Order order = orderService.findById(bean.getId());
 		if(order!=null&&Order.PAY_YIZHIFU.equals(order.getPay())) {
 			order.setShippingStatus(bean.getShippingStatus());
 			if("3".equals(order.getShippingStatus())) {
 				order.setStatus(Order.ORDER_OK);
 				//更新收货时间
 				order.setShippingTime(new Date());
 			} 
 			orderService.update(order);
 		}
		if(order!=null&&"0".equals(order.getShippingStatus())) {
			shippingService.removeByOrderId(bean.getId()); 
		} 
		//记录日志
		OrderNote orderNote= new OrderNote(order.getStatus(), order.getPay(), order.getShippingStatus(), "修改发货状态", order.getId(), CmsUtils.getUser(request));
		orderNoteService.save(orderNote);
		return new JsonResult(ResultCode.SUCCESS,"操作成功",order); 
	}
	/**
	 * 发货
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/saveShipping", method = RequestMethod.POST)
	@SysLog(description = "订单发货")
	public @ResponseBody Object saveShipping(@RequestBody Shipping bean, HttpServletRequest request) {
		shippingService.save(bean);
		Order order = orderService.findById(bean.getOrderId());
		if(!"2".equals(order.getShippingStatus())) {//标记发货
			order.setShippingStatus("2");
			orderService.update(order);
		}
		//记录日志
		OrderNote orderNote= new OrderNote(order.getStatus(), order.getPay(), order.getShippingStatus(), "完成发货", order.getId(), CmsUtils.getUser(request));
		orderNoteService.save(orderNote);
		return new JsonResult(ResultCode.SUCCESS,"操作成功",bean);
	}
	
	
	@RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
	@SysLog(description = "修改订单收货地址")
	public @ResponseBody Object updateAddress(@RequestBody String str, HttpServletRequest request) {
		if(StringUtils.isBlank(str)) {
			return new JsonResult(ResultCode.FAIL, "参数错误",null);
		} 
 		Order bean= JSON.parseObject(str, Order.class); //转order 
 		JSONObject json = JSONObject.parseObject(str); //必须带产品和收货地址
 		if(StringUtils.isBlank(json.getString("addressData"))) {
 			return new JsonResult(ResultCode.FAIL, "参数错误",null);
 		} 
 		if(bean.getId()<=0) {
 			return new JsonResult(ResultCode.FAIL, "参数错误",null);
 		}
 		Order order = orderService.findById(bean.getId());
		if(order!=null) {
			Address address = JSON.parseObject(json.getString("addressData"), Address.class); //转order 
			JSONObject content=JSONObject.parseObject(order.getContent());
			content.put("addressData", json.get("addressData"));
	  		if(address!=null) {
	  			order.setPhone(address.getPhone());
	  			order.setAddress(address.getAddress());
	  		}
	  		order.setContent(content.toJSONString());
	  		orderService.update(order);//修改信息
		}
		//记录日志
		OrderNote orderNote= new OrderNote(order.getStatus(), order.getPay(), order.getShippingStatus(), "修改收货地址", order.getId(), CmsUtils.getUser(request));
		orderNoteService.save(orderNote);
		return new JsonResult(ResultCode.SUCCESS, order);
	}
	
	

	
	@Autowired
	public OrderService orderService; 
	@Autowired
	public ShippingService shippingService;
	@Autowired
	public OrderNoteService orderNoteService; 
}
