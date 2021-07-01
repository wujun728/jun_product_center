package com.du.lin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.du.lin.service.MenuService;
import com.du.lin.utils.TreeDataUtil;
@Controller
public class TreeController {
	
	@Autowired
	private TreeDataUtil treeDataUtil;
	@Autowired
	private MenuService service;
	
	@ResponseBody
	@RequestMapping(value="/noticetreedata" , method={RequestMethod.GET})
	public String treeDataForNotice(){
		
		return treeDataUtil.getTreeDataForNotice();
	}
	
	@ResponseBody
	@RequestMapping(value="/menutreedata" , method={RequestMethod.GET})
	public String menuTreeData(){
		
		return service.menuTreeData();
	}
	
}
