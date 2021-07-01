package com.du.lin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.du.lin.annotation.BizLog;
import com.du.lin.bean.Leave;
import com.du.lin.constant.Constant;
import com.du.lin.service.LeaveService;
import com.du.lin.utils.Userinfo;

@Controller
public class LeaveController {
	@Autowired
	private LeaveService service;

	@BizLog("请假")
	@ResponseBody
	@RequestMapping(value="/addleave" , method={RequestMethod.POST})
	public String addLeave(HttpServletRequest request , @RequestParam("type") String type , Date starttime , Date endtime , String reason ){
		Leave leave = new Leave();
		if ("3".endsWith(type)) {
			leave.setType(request.getParameter("othertext"));
		}else{
			leave.setType(type);			
		}
		leave.setEndtime(endtime);
		leave.setStarttime(starttime);
		leave.setReason(reason);
		leave.setUserid(Userinfo.getUserid());
		leave.setUsername(Userinfo.getUsername());
		leave.setIsfinish(Constant.LEAVE_UNKNOWN_CODE);
		leave.setCreatetime(new Date()); 
		String result = service.addLeave(leave);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/getuserleavelist" , method={RequestMethod.POST})
	public String getUserLeaveList(HttpServletRequest request){
		String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数   
	    String rows = request.getParameter("rows"); //
	    return service.getAllUserLeaveJson(Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@ResponseBody
	@RequestMapping(value="/getoperationleavelist" , method={RequestMethod.POST})
	public String getOperationLeaveList(HttpServletRequest request){
		String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数   
		String rows = request.getParameter("rows"); //
		return service.getAllLeaveJson(Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@BizLog("撤回请假")
	@ResponseBody
	@RequestMapping(value="/withdrawleave" , method={RequestMethod.POST})
	public String withdrawLeave(HttpServletRequest request , int  id){
		return service.withdrawLeave(id);
	}
	
	@BizLog("审核请假")
	@ResponseBody
	@RequestMapping(value="/operationleave" , method={RequestMethod.POST})
	public String operationLeave(HttpServletRequest request , @RequestParam("id")int  id , @RequestParam("finish") String finish){
		return service.operationLeave(id, finish);
	}
	
	
	
	
}
