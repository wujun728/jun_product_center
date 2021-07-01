package com.du.lin.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.du.lin.annotation.BizLog;
import com.du.lin.service.RoleService;

@Controller
public class RoleController {
	@Autowired
	private RoleService service;
	
	@ResponseBody
	@RequestMapping(value="/rolelistforuseradd" , method={RequestMethod.POST})
	public String roleListForUserAdd(){
		return service.roleListForUserAdd();
	}
	
	@ResponseBody
	@RequestMapping(value="showrolelist" , method={RequestMethod.POST})
	public String getAllShowRole(HttpServletRequest request){
		String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数   
	    String rows = request.getParameter("rows"); //
		return service.getAllShowRoleJson(Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@BizLog("添加角色")
	@ResponseBody
	@RequestMapping(value="addrole" , method={RequestMethod.POST})
	public String addRole(HttpServletRequest request , @RequestParam("tips") String tips ,@RequestParam("roles") String roles){
		String result = service.addRole(tips, roles);
		
		return result;
	}
	@BizLog("修改角色信息")
	@ResponseBody
	@RequestMapping(value="setrole" , method={RequestMethod.POST})
	public String setRole(HttpServletRequest request ,@RequestParam("changeid") int id,@RequestParam("tips") String tips ,@RequestParam("roles") String roles){
		String result = service.setRole(id, tips, roles);
		
		return result;
	}
	
	@BizLog("删除角色")
	@ResponseBody
	@RequestMapping(value="deleterole" , method={RequestMethod.POST})
	public String deleteRole(HttpServletRequest request ,@RequestParam("id") int id){
		String result = service.deleteRole(id);
		 
		return result;
	}
	
	@BizLog("配置菜单")
	@ResponseBody
	@RequestMapping(value="addrelation" , method={RequestMethod.POST})
	public String addRelation(String roleid,String menus){
		return service.addRelation(Integer.parseInt(roleid), menus);
	}
	
}
