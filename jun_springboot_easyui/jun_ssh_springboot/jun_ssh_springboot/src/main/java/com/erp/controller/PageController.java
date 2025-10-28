package com.erp.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/jsp")
public class PageController {

	@RequestMapping(value = "/index")
	public ModelAndView index1() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("text", "hello jsp");
		model.put("list", Arrays.asList(1, 2, 3, 4, 5));
		return new ModelAndView("index", model);
	}
	
	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getbyid( HttpServletRequest request,@PathVariable("id") int idnum){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("idnum", idnum);
		return modelMap;
	} 

	//jsp/user/userMain.jsp
	@RequestMapping(value = "/user/{path}")
	public String user(@PathVariable("id") String path) {
		//userMain.jsp
		return path;
	}
	
	@RequestMapping(value = "/login")
	public String login1() {
		
		return "login";
	}

}
