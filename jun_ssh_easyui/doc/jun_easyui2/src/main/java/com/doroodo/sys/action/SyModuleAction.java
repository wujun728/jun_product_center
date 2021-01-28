package com.doroodo.sys.action;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.doroodo.base.action.BaseAction;
import com.doroodo.config.SysVal;
import com.doroodo.base.model.comboBox;
import com.doroodo.sys.model.SyModule;
import com.doroodo.sys.model.SyUser;
import com.doroodo.sys.service.SyModuleService;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")  
public class SyModuleAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private SyModuleService syModuleService;
	private SyModule syModule;
	public SyModule getSyModule() {
		return syModule;
	}

	public void setSyModule(SyModule syModule) {
		this.syModule = syModule;
	}

	@Action("/sys/syModule_Add")
	public void syModuleAdd(){
		syModuleService.saveOrUpdate(syModule);
		writeMsg(SysVal.ADD_SUC);
	}

	@Action("/sys/syModule_List")
	public void syModuleList(){
		this.writeJson(syModuleService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syModule_Delete")
	public void syModuleDelete(){
		if(this.getIds().trim()=="")return;
		syModuleService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syModule_Update")
	public void syModuleUpdate(){
		if(syModule!=null) syModuleService.saveOrUpdate(syModule);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syModule_Excel")
	public void syModuleExcel(){
		 String title = "Excel表";
		 List<Object> list = syModuleService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		 super.excel(title,list);
	}
	
	@Action("/sys/syModule_Get_ByObj")  
	public void syModuleGetByObj(){
		this.writeJson(syModuleService.get(syModule));
	}
	
	@Action("/sys/syModule_Get_ComboBox")
	public void syModuleGetComboBox(){
		SyModule syModule=new SyModule();
		syModule.setPid("0");
		List<SyModule> l = syModuleService.get(syModule);
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			cb.setId(l.get(i).getMenuname());
			cb.setText(l.get(i).getMenuname());
			l_.add(cb);
		}
		comboBox cb = new comboBox();
		cb.setId("0");
		cb.setText("0");
		l_.add(cb);
		this.writeJson(l_);
	}
	
	@Action("/sys/syModule_Get_ById")  
	public void syModuleGetById(){
		SyModule syModule=new SyModule();
		syModule.setId(Integer.parseInt(this.id));
		syModule=syModuleService.get(syModule).get(0);
		if(syModule==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syModule);
		}
	}
}
