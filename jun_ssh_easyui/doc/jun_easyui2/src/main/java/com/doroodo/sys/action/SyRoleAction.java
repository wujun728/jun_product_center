package com.doroodo.sys.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.doroodo.base.action.BaseAction;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.SyModule;
import com.doroodo.sys.model.SyRole;
import com.doroodo.sys.model.SyUser;
import com.doroodo.sys.service.SyModuleService;
import com.doroodo.sys.service.SyRoleService;


@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault") 
public class SyRoleAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private SyRoleService syRoleService;
	@Autowired
	private SyModuleService syModuleService;
	private SyModule syModule;
	private SyRole syRole;
	private String moduleids;
	private String roleid;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getModuleids() {
		return moduleids;
	}

	public void setModuleids(String moduleids) {
		this.moduleids = moduleids;
	}

	@Action("/sys/syRole_Add")
	public void syRoleAdd(){
		syRoleService.saveOrUpdate(syRole);
		writeMsg(SysVal.ADD_SUC);
	}
	
	@Action("/sys/syRole_Delete")
	public void syRoleDelete(){
		if(this.getIds().trim()=="")return;
		syRoleService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	
	public SyModule getSyModule() {
		return syModule;
	}

	public void setSyModule(SyModule syModule) {
		this.syModule = syModule;
	}

	public SyRole getSyRole() {
		return syRole;
	}

	public void setSyRole(SyRole syRole) {
		this.syRole = syRole;
	}

	@Action("/sys/syRole_Update")
	public void syRoleUpdate(){
		if(syRole.getId().toString().equalsIgnoreCase("1")||syRole.getId().toString().equalsIgnoreCase("2")){
			writeMsg(SysVal.EDIT_ER); return;
		}
		if(syRole!=null) syRoleService.saveOrUpdate(syRole);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syRole_Update_Module")
	public void syRoleUpdateModule(){
		if(roleid==null || moduleids==null) return;
		SyRole syRole = new SyRole();
		syRole.setRoleid(roleid);
		List<SyRole> syRoles=syRoleService.get(syRole);
		if(syRoles.size()==1){
			syRole=syRoles.get(0);
			syRole.setOperatemap(moduleids);
			syRoleService.saveOrUpdate(syRole);
			this.writeMsg(SysVal.EDIT_SUC);
		}else{
			this.writeMsg(SysVal.EDIT_ER);
		}
	}
	
	@Action("/sys/syRole_List")
	public void syRoleList(){
		this.writeJson(syRoleService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syRole_Excel")
	public void syRoleExcel(){
		 String title = "Excel表";
		 List<Object> list = syRoleService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		 super.excel(title,list);
	}
	
	@Action("/sys/syRole_Get_ByObj")  
	public void syRoleGetByObj(){
		this.writeJson(syRoleService.get(syRole));
	}
	
	@Action("/sys/SyRole_Get_ById")  
	public void SyRoleGetById(){
		SyRole syRole = new SyRole();
		syRole.setId(Integer.parseInt(this.id));
		syRole=syRoleService.get(syRole).get(0);
		if(syRole==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syRole);
		}
	}
}
