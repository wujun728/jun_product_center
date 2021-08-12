package com.shuogesha.app.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.common.util.UtilDate;
import com.shuogesha.platform.entity.Paybill;
import com.shuogesha.platform.entity.Paylog;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.PaybillService;
import com.shuogesha.platform.service.PaylogService;

/**
 * 测试阶段
 * @author zhaohaiyuan
 *
 */
@Controller
@RequestMapping("/app/")
public class ApiPayAct { 
	 
	@RequestMapping(value = "pay_wx")
  	public @ResponseBody Object pay_wx(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		UnifiedUser unifiedUser=ApiUtils.getUnifiedUser(request);
		String orderNo=UtilDate.getOrderNum();
		String subject = "用户订单_"+UtilDate.getOrderNum();
		String body = "用户订单";  
		String type=Paybill.ORDER; 
		Paybill payBill =  new Paybill(type,orderNo,subject,body,"wx",BigDecimal.valueOf(0.01) ,40L,"15881169173");
		paybillService.save(payBill);
		Paylog payLog = paylogService.create(payBill.getId(),type,"0");  
		return new JsonResult(ResultCode.SUCCESS, payBill);
	}
	
	@RequestMapping(value = "pay_ali")
  	public @ResponseBody Object pay_ali(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		UnifiedUser unifiedUser=ApiUtils.getUnifiedUser(request);
		String orderNo=UtilDate.getOrderNum();
		String subject = "用户订单_"+UtilDate.getOrderNum();
		String body = "用户订单";  
		String type=Paybill.ORDER; 
		Paybill payBill =  new Paybill(type,orderNo,subject,body,"wx", BigDecimal.valueOf(0.01),40L,"15881169173");
		paybillService.save(payBill);
		Paylog payLog = paylogService.create(payBill.getId(),type,"0");  
		return new JsonResult(ResultCode.SUCCESS, payBill);
	} 


    @Autowired
	private PaybillService paybillService;
	@Autowired
	private PaylogService paylogService; 
}
