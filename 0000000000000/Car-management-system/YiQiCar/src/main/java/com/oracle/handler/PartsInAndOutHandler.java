package com.oracle.handler;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.oracle.service.PartsInAndOutService;
import com.oracle.vo.PartsRepBill;

@Controller
@RequestMapping("/pages/partssys/partsrep/")
public class PartsInAndOutHandler {

	@Autowired
	PartsInAndOutService service;
	
	@RequestMapping("/partsreplist/{start}")
	public String partsList(Map<String, Object> map, @PathVariable("start") Integer start) {

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		PageHelper.startPage(start, 5);

		list = service.getAll();
		
		PageInfo<Map<String,Object>> info = new PageInfo<Map<String,Object>>(list);

		map.put("pageInfo", info);

		return "/pages/partssys/partsrep/partsreplist";

	}
	
	@RequestMapping(value = "/{path}")
	public String frame(@PathVariable("path") String path) {
		
		return "pages/partssys/partsrep/" + path;
	}
	
	@RequestMapping("/jump")
	public String jump(@RequestParam("num") int num) {
		
		return "redirect:/pages/partssys/parts/partsreplist/"+num;
	}
	
	@RequestMapping(value="/getJsons",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String,Object>> getJsons(String inout) {
		
		return service.getJson(inout);
	}
	
	@RequestMapping(value="/getJsons1",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String,Object>> getJsons1() {
		
		return service.getJson1();
	}
	
	@RequestMapping(value="/getJsons2",produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String,Object>> getJsons2() {
		
		return service.getJson2();
	}
	
	@RequestMapping("/addBill")
	public String addBill(String descript,String inout,Integer partsid,Integer billcount,Integer billuser,String billtype) {
			
		PartsRepBill bill = new PartsRepBill();
		
		if(inout.equals("out")) {
			inout = "O";
		} else {
			inout = "I";
		}
		
		bill.setBillcount(billcount);
		bill.setBillflag(inout);
		
		bill.setBilltype(billtype);
		bill.setBilluser(billuser);
		bill.setPartsid(partsid);
		bill.setDescript(descript);
		
		service.insertBill(bill);
		
		List<Integer> partsids = service.getPartsid();
		
		if(partsids.contains(partsid)) {
			if(inout.equals("I")) {
				service.updateCount1(billcount, partsid);
			}else if(inout.equals("O")) {
				service.updateCount2(billcount, partsid);
			}
		}else {
			service.insertBill1(billcount,partsid);
		}
		
		return "redirect:/pages/partssys/partsrep/partsreplist/1";
	}
	
	@RequestMapping("/getParts")
	public String getParts(HttpSession session,Map<String,Object> map,Integer partsid,String partsname) {
		
		map.put("partsid",partsid);
		map.put("partsname",partsname);
		
		List<Map<String,Object>> list = service.getParts(map);
		
		session.setAttribute("ilist", list);
		
		
		return "/pages/partssys/partsrep/partsreplist2";
	}
	
	
	@RequestMapping("/getSelected")
	public String getSelected(Integer [] c,HttpSession session) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		
		for(int i = 0 ; i < c.length ; i++) {
			Map<String,Object> map = service.getSelected(c[i]);
			
			list.add(map);       
		}
		
		session.setAttribute("iilist", list);
		
		return "redirect:/pages/ordersys/order/orderadd";
	}
	
	
	
	
	
	
}
