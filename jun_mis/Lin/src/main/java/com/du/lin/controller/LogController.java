package com.du.lin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.du.lin.annotation.BizLog;

import com.du.lin.constant.Constant;
import com.du.lin.service.LoginLogService;
import com.du.lin.service.OperationLogService;

import com.du.lin.utils.ExcelUtil;

@Controller
public class LogController extends BaseController{

	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private ExcelUtil excelUtil;
	
	@ResponseBody
	@RequestMapping(value="/loginloglist" , method={RequestMethod.POST})
	public String loginLogList(HttpServletRequest request){
		 String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数   
	      String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数   

		return loginLogService.getShowLogJson(Integer.parseInt(page), Integer.parseInt(rows));
	
	}
	
	@ResponseBody
	@RequestMapping(value="/operationloglist" , method={RequestMethod.POST})
	public String operationLogList(HttpServletRequest request){
		  String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数   
	      String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数   
		return operationLogService.getShowLogJson(Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@BizLog("下载日志")
	@RequestMapping(value="/downloginlogexcel" , method={RequestMethod.GET} )
	public String downLoginLogExcel(HttpServletRequest request , HttpServletResponse response, @RequestParam("type") int type){
		
		if (SecurityUtils.getSubject().hasRole("ROLE_ADMIN")) {
			downloadFile(response, excelUtil.getExcel(type));
			return null;
		}
		request.setAttribute("msg", "没有权限");
		request.setAttribute("status", Constant.ERROR_CODE_NO_PERMISION);
		return "error";
		
	}

	@BizLog("清除日志")
	@ResponseBody
	@RequestMapping(value="/clearloginlog" , method={RequestMethod.POST})
	public String clearLoginLog(HttpServletRequest request , HttpServletResponse response, @RequestParam("type") int type){
		if (!SecurityUtils.getSubject().hasRole("ROLE_ADMIN")) {
			return Constant.ERROR_CODE_NO_PERMISION;
		}
		String result = "999";
		if (type == 1) {
			result = loginLogService.deleteALLLoginLog();
		}else if(type == 2){
			result = operationLogService.deleteALLLog();
		}
		return result;
	}
	
}
