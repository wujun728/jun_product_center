package com.doroodo.sys.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.doroodo.base.action.BaseAction;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.SyLogService;
import com.doroodo.sys.service.impl.SyLogServiceImpl;
import com.doroodo.config.SysVal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SyLogAction extends BaseAction{
	@Autowired
	private SyLogService syLogService;
	private SyLog syLog;
	private String id;

	public SyLog getSyLog(){
		return syLog;
	}
	
	public void setSyLog(SyLog syLog){
		this.syLog=syLog;
	}
	
	@Action("/sys/syLog_Add")
	public void syLogAdd(){
		syLogService.saveOrUpdate(syLog);
		writeMsg(SysVal.ADD_SUC);
	}
	
	@Action("/sys/syLog_List")
	public void syLogList(){
		this.writeJson(syLogService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syLog_List_All")
	public void syLogListAll(){
		this.writeJson(syLogService.listAll());
	}
	
	@Action("/sys/syLog_Delete")
	public void syLogDelete(){
		if(this.getIds().trim()=="")return;
		syLogService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syLog_Update")
	public void syLogUpdate(){
		if(syLog!=null) syLogService.saveOrUpdate(syLog);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syLog_Excel")
	public void syLogExcel(){
		 String title = "Excel表";
		 List<Object> list = syLogService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		 super.excel(title,list);
		 writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syLog_Get_ByObj")  
	public void syLogGetByObj(){
		this.writeJson(syLogService.get(syLog));
	}
	
	@Action("/sys/syLog_GetById")  
	public void syLogGetById(){
		SyLog syLog=new SyLog();
		syLog.setId(Integer.parseInt(this.id));
		syLog=syLogService.get(syLog).get(0);
		if(syLog==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syLog);
		}
	}
	
	//检查字段是否唯一
	private String isSingle(SyLog syLog,String fieldName,String fieldValue){
		String result=null;
		List<SyLog> lsList = syLogService.get(syLog);
		if(syLogService.get(syLog).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
