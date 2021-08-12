package com.oracle.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.service.OrderDetailService;
import com.oracle.service.OrderService;
import com.oracle.vo.Order;
import com.oracle.vo.OrderDetail;

@Controller
@RequestMapping("pages/ordersys/order/")
public class OrderHandler {

	@Autowired
	OrderService service;
	
	@Autowired
	OrderDetailService service1;
	
	@RequestMapping("/orderlist/{start}")
	public String orderlist(Map<String, Object> map, @PathVariable("start") Integer start) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		PageHelper.startPage(start, 5);

		list = service.getAll();

		PageInfo<Map<String, Object>> info = new PageInfo<Map<String, Object>>(list);

		map.put("pageInfo", info);

		return "/pages/ordersys/order/orderlist";
	}
	
	
	@RequestMapping("/jump")
	public String jump(@RequestParam("num") int num) {

		return "redirect:/pages/ordersys/order/orderlist/" + num;
	}
	
	@RequestMapping("/jump1")
	public String jump1(@RequestParam("num") int num) {

		return "redirect:/pages/ordersys/order/orderchecklist/" + num;
	}
	
	@RequestMapping("/jump2")
	public String jump2() {

		return "redirect:/pages/partssys/partsrep/partsreplist/1";
	}
	
	@RequestMapping("/getOrders")
	public String getOrder(HttpSession session,Map<String,Object> map,String ordercode,String orderdate,Integer orderflag) {
		
		map.put("ordercode",ordercode);
		map.put("orderdate",orderdate);
		map.put("orderflag",orderflag);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		list = service.getOrders(map);
	
		session.setAttribute("info", list);
		
		return "/pages/ordersys/order/orderlist2";
		
	}
	
	@RequestMapping("/search")
	public String getById(Integer orderid,HttpSession session) {
		
		Map<String,Object> imap = service.getOrderById(orderid);
		
		List<Map<String,Object>> mlist = service.getBillDetail(orderid);
		
		session.setAttribute("mlist", mlist);
		
		session.setAttribute("imap", imap);
		
		return "/pages/ordersys/order/orderedit";
	}
	
	
	@RequestMapping("/search1")
	public String getById1(Integer orderid,HttpSession session) {
		
		Map<String,Object> imap = service.getOrderById(orderid);
		
		List<Map<String,Object>> mlist = service.getBillDetail(orderid);
			
		session.setAttribute("mlist", mlist);
		
		session.setAttribute("imap", imap);
		
		return "pages/ordersys/order/order-view";
	}

	
	@RequestMapping(value = "/{path}")
	public String frame(@PathVariable("path") String path) {
		
		return "pages/ordersys/order/" + path;
	}
	
	@RequestMapping("/check")
	@ResponseBody
	public String[] check(String code) {
		
		List<String> list = service.getOrderCode();
		
		String [] str = new String[1];
		
		if(list.contains(code)) {
			str[0] = "0";
		}else {
			str[0] = "1";
		}
		
		return str;
	}
	
	@RequestMapping("/delete")
	public String delete(Integer partsid,HttpSession session) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = (List<Map<String, Object>>) session.getAttribute("iilist");
		
		try {
			for(Map<String,Object> map : list) {
				int i = 0;
				if(map.containsValue(partsid)) {
					list.remove(i);
				}
				i++;
			}
			
			session.setAttribute("iilist", list);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return "redirect:/pages/ordersys/order/orderadd";
	}
	
	@RequestMapping("/save")
	public String save(Order order,Integer [] orderpartscount,HttpSession session) {
		

		
		System.out.println(Arrays.toString(orderpartscount));
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = (List<Map<String, Object>>) session.getAttribute("iilist");
		
		service.insert(order);
		
		String partsid = "partsid";
		
		Integer orderid = service1.getOrderId(order.getOrdercode());
	
		int i = 0;
		
		for(Map<String,Object> map : list) {
			
			OrderDetail detail = new OrderDetail();
			detail.setPartsid((Integer)map.get(partsid));
			detail.setOrderpartscount(orderpartscount[i]);
			detail.setOrderid(orderid);
			
			service1.insert(detail);
			service1.updateNum((Integer)map.get(partsid), orderpartscount[i]);
			i++;
		}
		
		return "redirect:/pages/ordersys/order/orderadd";
	}
	
	@RequestMapping("/orderchecklist/{start}")
	public String getAllBills(Map<String, Object> map, @PathVariable("start") Integer start) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		PageHelper.startPage(start, 5);

		list = service.getAllBills();

		PageInfo<Map<String, Object>> info = new PageInfo<Map<String, Object>>(list);

		map.put("info", info);

		return "/pages/ordersys/order/orderchecklist";
	}
	
	@RequestMapping("/updateFlag")
	public String updateFlag(Integer flag,Integer orderid) {
		
		System.out.println("-----orderid"+orderid);
			
		service.updateFlag(orderid);
		
		return "redirect:/pages/ordersys/order/orderchecklist/1";
	}
	
	@RequestMapping("/updateFlag1")
	public String updateFlag1(Integer flag,Integer orderid) {
		
		System.out.println("-----orderid"+orderid);
			
		service.updateFlag1(orderid);
		
		return "redirect:/pages/ordersys/order/orderchecklist/1";
	}
	
	
	@RequestMapping("/getChecks")
	public String getCheck(HttpSession session,String ordercode,String orderdate,String orderflag) {
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("ordercode",ordercode);
		map.put("orderdate",orderdate);
		map.put("orderflag",orderflag);

		list = service1.getChecks(map);
		
		session.setAttribute("iiilist", list);

		return "/pages/ordersys/order/orderchecklist2";
	}
	
	
	@RequestMapping("/delete1")
	public String delete1(Integer partsid,HttpSession session) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = (List<Map<String, Object>>) session.getAttribute("mlist");
		try{
			for(Map<String,Object> map : list) {
				int i = 0;
				if(map.containsValue(partsid)) {
					list.remove(i);
				}
				i++;
			}
			
			session.setAttribute("mlist", list);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "pages/ordersys/order/orderedit";
	}
	
}
