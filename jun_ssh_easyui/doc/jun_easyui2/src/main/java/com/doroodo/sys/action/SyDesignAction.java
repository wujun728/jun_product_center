/*

 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.doroodo.sys.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.doroodo.base.action.BaseAction;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SyDesignAction extends BaseAction{
	@Autowired
	private SyDesignService syDesignService;
	private SyDesign syDesign;
	
	public SyDesign getSyDesign(){
		return syDesign;
	}
	
	public void setSyDesign(SyDesign syDesign){
		this.syDesign=syDesign;
	}
	
	@Action("/sys/syDesign_Add")
	public void syDesignAdd(){
		syDesignService.saveOrUpdate(syDesign);
		writeMsg(SysVal.ADD_SUC);
	}
	
	@Action("/sys/syDesign_List")
	public void syDesignList(){
		this.writeJson(syDesignService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syDesign_Delete")
	public void syDesignDelete(){
		if(this.getIds().trim()=="")return;
		syDesignService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syDesign_Update")
	public void syDesignUpdate(){
		if(syDesign!=null) syDesignService.saveOrUpdate(syDesign);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syDesign_Excel")
	public void syDesignExcel(){
		 String title = "Excel表";
		 List<Object> list = syDesignService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		 super.excel(title,list);
	}
	
	@Action("/sys/syDesign_Get_ById")  
	public void syDesignGetById(){
		SyDesign syDesign=new SyDesign();
		syDesign.setId(Integer.parseInt(this.id));
		syDesign=syDesignService.get(syDesign).get(0);
		if(syDesign==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syDesign);
		}
	}
	

}
