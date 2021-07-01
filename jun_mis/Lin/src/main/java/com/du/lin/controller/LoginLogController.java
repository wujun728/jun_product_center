//package com.du.lin.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.SecurityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.du.lin.annotation.BizLog;
//import com.du.lin.constant.Constant;
//import com.du.lin.service.LoginLogService;
//import com.du.lin.utils.ExcelUtil;
//
//@Controller
//public class LoginLogController extends BaseController{
//	@Autowired
//	private LoginLogService loginLogService;
//	@Autowired
//	private ExcelUtil excelUtil;
//	
//	@BizLog("liebiao")
//	@ResponseBody
//	@RequestMapping(value="/loginloglist" , method={RequestMethod.POST})
//	public String loginLogList(HttpServletRequest request){
//		 String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数   
//	      String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数   
//
//		return loginLogService.getShowLogJson(Integer.parseInt(page), Integer.parseInt(rows));
//	
//	}
//	
////	@BizLog("下载登陆日志")
////	@RequestMapping(value="/downloginlogexcel" , method={RequestMethod.GET} )
////	private String downLoginLogExcel(HttpServletRequest request , HttpServletResponse response){
////		
////		if (SecurityUtils.getSubject().hasRole("ROLE_ADMIN")) {
////			downloadFile(response, excelUtil.getExcel(1));
////			return null;
////		}
////		request.setAttribute("msg", "没有权限");
////		request.setAttribute("status", Constant.ERROR_CODE_NO_PERMISION);
////		return "error";
////		
////	}
//	
//}
