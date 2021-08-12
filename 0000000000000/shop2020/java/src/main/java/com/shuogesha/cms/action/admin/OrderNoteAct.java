package com.shuogesha.cms.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.cms.entity.Order;
import com.shuogesha.cms.entity.OrderNote;
import com.shuogesha.cms.service.OrderNoteService;
import com.shuogesha.cms.service.OrderService;
import com.shuogesha.platform.web.CmsUtils;
import com.shuogesha.platform.web.mongo.Pagination;

@RestController 
@RequestMapping("/api/orderNote/")
public class OrderNoteAct {
	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = orderNoteService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		OrderNote bean = orderNoteService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object oSave(@RequestBody OrderNote bean, HttpServletRequest request) {
 		if(bean.getOrderId()!=null) {
 			Order order = orderService.findById(bean.getOrderId());
 			if(order!=null) {
 	 			OrderNote orderNote= new OrderNote(order.getStatus(), order.getPay(), order.getShippingStatus(), bean.getRemark(), order.getId(), CmsUtils.getUser(request));
 	 			//记录日志
 	 			orderNoteService.save(orderNote);
 	 			return new JsonResult(ResultCode.SUCCESS, true);
 			} 
 		} 
		return new JsonResult(ResultCode.FAIL, false);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody OrderNote bean) {
		orderNoteService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		orderNoteService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	} 
	 
	@Autowired
	public OrderNoteService orderNoteService; 
	@Autowired
	public OrderService orderService; 
}
