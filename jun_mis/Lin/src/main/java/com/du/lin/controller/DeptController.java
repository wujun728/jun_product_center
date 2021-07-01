package com.du.lin.controller;


import javax.servlet.http.HttpServletRequest;

import com.du.lin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.du.lin.annotation.BizLog;


@Controller
public class DeptController {

	@Autowired
	private DeptService service;

	/**
	 * 添加用户或修改用户信息时，需要传到前台当前所有的部门信息，thymeleaf解析显示
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deptlistforadd" , method={RequestMethod.POST})
	public String deptListForAdd(){
		return service.deptListForUserAdd();
	}
	
	@ResponseBody
	@RequestMapping(value="/deptlist" , method={RequestMethod.POST})
	public String deptList(HttpServletRequest request){
		String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
		String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
		return service.getAllDeptJson(Integer.parseInt(page) , Integer.parseInt(rows));
	}
	@BizLog("添加部门")
	@ResponseBody
	@RequestMapping(value="/adddept" , method={RequestMethod.POST})
	public String addDept(HttpServletRequest request){
		String deptName = request.getParameter("name").trim();
		if ("".endsWith(deptName)) {
			return "";
		}
		return service.addDept(deptName);
	}
	
	@BizLog("修改部门信息")
	@ResponseBody
	@RequestMapping(value="/setdept" , method={RequestMethod.POST})
	public String setDept(HttpServletRequest request){
		String deptid = request.getParameter("deptid");
		String deptname = request.getParameter("deptname");
		return service.modifyDept(deptid , deptname);
	}
	@BizLog("删除部门")
	@ResponseBody
	@RequestMapping(value="/deletedept" , method={RequestMethod.POST})
	public String deleteDept(HttpServletRequest request){
		String deptid = request.getParameter("id");
		return service.deleteDept(deptid);
	}
	

	
	
}
