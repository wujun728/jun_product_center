package com.doroodo.sys.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.doroodo.base.action.BaseAction;
import com.doroodo.config.SysVal;
import com.doroodo.base.model.Tree;
import com.doroodo.base.vo.web.LeftMenu;
import com.doroodo.base.vo.web.Web;
import com.doroodo.base.vo.web.LeftMenu.Menu;
import com.doroodo.base.vo.web.LeftMenu.Menu.Menus;
import com.doroodo.sys.model.SyModule;
import com.doroodo.sys.model.SyParameter;
import com.doroodo.sys.model.SyParameterUsed;
import com.doroodo.sys.model.SyProject;
import com.doroodo.sys.model.SyRole;
import com.doroodo.sys.model.SyUser;
import com.doroodo.sys.service.SyModuleService;
import com.doroodo.sys.service.SyParameterService;
import com.doroodo.sys.service.SyProjectService;
import com.doroodo.sys.service.SyRoleService;
	
	
	
	
import com.doroodo.sys.service.SyUserService;
@Controller
@Results({
	//业务页面路由
	@Result(name="sySbProjectImagesInfos", location="/work/SySbProjectImagesInfos.jsp"),
	@Result(name="sySbRightEventInfo", location="/work/SySbRightEventInfo.jsp"),
	@Result(name="vcxSbVoltage", location="/work/VcxSbVoltage.jsp"),
	@Result(name="vcxSbLeakageCurrentMonitor", location="/work/VcxSbLeakageCurrentMonitor.jsp"),
	@Result(name="vcxSbLeakageCurrent", location="/work/VcxSbLeakageCurrent.jsp"),
	@Result(name="vcxSbCurrentMonitor", location="/work/VcxSbCurrentMonitor.jsp"),
	@Result(name="vcxSbCurrent", location="/work/VcxSbCurrent.jsp"),
	@Result(name="vcxSbVoltageMonitor", location="/work/VcxSbVoltageMonitor.jsp"),
	@Result(name="vcxSbTerminal", location="/work/VcxSbTerminal.jsp"),
	@Result(name="vcxSbAlarmStateMonitor", location="/work/VcxSbAlarmStateMonitor.jsp"),
	@Result(name="vcxSbAlarmState", location="/work/VcxSbAlarmState.jsp"),
	@Result(name="sySbRuninfo", location="/work/SySbRuninfo.jsp"),
	@Result(name="sySbProjectPage", location="/work/SySbProjectPage.jsp"),
	@Result(name="sySbProject", location="/work/SySbProject.jsp"),
	@Result(name="sySbPrimitiveClass", location="/work/SySbPrimitiveClass.jsp"),
	@Result(name="sySbPrimitive", location="/work/SySbPrimitive.jsp"),
	@Result(name="sySbJsDictionaries", location="/work/SySbJsDictionaries.jsp"),
	@Result(name="cxSbPoint", location="/work/CxSbPoint.jsp"),
	@Result(name="vcxSbBalanceData", location="/work/VcxSbBalanceData.jsp"),
	@Result(name="cxSbBalanceDevice", location="/work/CxSbBalanceDevice.jsp"),
	@Result(name="cxSbTerminalConfig", location="/work/CxSbTerminalConfig.jsp"),
	@Result(name="vcxSbTakeStateMonitor", location="/work/VcxSbTakeStateMonitor.jsp"),
	@Result(name="vcxSbTerminal", location="/work/VcxSbTerminal.jsp"),
	@Result(name="vcxSbReadStatusMonitor", location="/work/VcxSbReadStatusMonitor.jsp"),
	@Result(name="cxSbTakeState", location="/work/CxSbTakeState.jsp"),
	@Result(name="cxSbReadStatus", location="/work/CxSbReadStatus.jsp"),
	@Result(name="vcxSbTakeState", location="/work/VcxSbTakeState.jsp"),
	@Result(name="cxSbLine", location="/work/CxSbLine.jsp"),
	@Result(name="vcxSbReadStatus", location="/work/VcxSbReadStatus.jsp"),
	@Result(name="cxSbTerminal", location="/work/CxSbTerminal.jsp"),
	@Result(name="cxSbSysdomian", location="/work/CxSbSysdomian.jsp"),
	@Result(name="cxSbCompany", location="/work/CxSbCompany.jsp"),
	@Result(name="cxSbTest", location="/work/CxSbTest.jsp"),
	@Result(name="syTablefieldRemarks", location="/work/SyTablefieldRemarks.jsp"),
	@Result(name="cxSbFault", location="/work/CxSbFault.jsp"),
	@Result(name="cxSbMonitorTerminal", location="/work/CxSbMonitorTerminal.jsp"),
	@Result(name="cxSbStateData", location="/work/CxSbStateData.jsp"),
	//系统路由，请勿删除   start
	@Result(name="syJtopo", location="/plug/jtopo/index.jsp"),
	@Result(name="syProject", location="/sys/SyProject.jsp"),
	@Result(name="syDemoTreeiframe", location="/sys/SyDemoTreeiframe_frame.jsp"),
	@Result(name="syDemoDatetree", location="/sys/SyDemoDatetree.jsp"),
	@Result(name="syDemoGridtochart", location="/sys/SyDemoGridtochart.jsp"),
	@Result(name="syDebug", location="/sys/SyDebug.jsp"),
	@Result(name="syDebugState", location="/sys/SyDebugState.jsp"),
	@Result(name="syChart", location="/sys/SyChart.jsp"),
	@Result(name="syNotice", location="/sys/SyNotice.jsp"),
	@Result(name="syAssembly", location="/sys/SyAssembly.jsp"),
	@Result(name="syWebmodal", location="/sys/SyWebmodal.jsp"),
	@Result(name="syLog", location="/sys/SyLog.jsp"),
	@Result(name="syFile", location="/sys/SyFile.jsp"),
	@Result(name="syFormDesign", location="/sys/SyFormDesign.jsp"),
	@Result(name="syModule", location="/sys/SyModule.jsp"),
	@Result(name="syUser", location="/sys/SyUser_frame.jsp"),
	@Result(name="syOrgan", location="/sys/SyOrgan_frame.jsp"),
	@Result(name="syRole", location="/sys/SyRole.jsp"),
	@Result(name="syDesign", location="/sys/SyDesign_frame.jsp"),
	@Result(name="syParameter", location="/sys/SyParameter.jsp"),
	@Result(name="tsbProjectPage", location="/work/TsbProjectPage.jsp"),
	@Result(name="sySysdomain", location="/sys/SySysdomain.jsp"),
	@Result(name="syPoint", location="/sys/SyPoint.jsp"),
	@Result(name="syScada", location="/component/scada.jsp")
	
	//手机路由
	//系统路由，请勿删除   end
})  
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")  
public class SyParameterAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SyUserService syUserService;
	@Autowired
	private SyParameterService syParameterService;
	@Autowired
	private SyModuleService syModuleService;
	@Autowired
	private SyRoleService syRoleService;
	@Autowired
	private SyProjectService syProjectService;
	private SyParameter syParameter;
	private Map visibleMap=null;
	private String systemId=null;
	
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	@Action("/sys/syParameter_Add")
	public void syParameterAdd(){
		Map<String, String> map = new HashMap<String, String>();
		syParameterService.saveOrUpdate(syParameter);
		writeMsg(SysVal.ADD_SUC);
	}
	
	private Map isVisible(List<SyModule> syModules){
		String userRole=this.getLoginUserRole();  
		Map visibleMap = new HashMap();
		 if(userRole!=null){  
			 SyRole syRole = syRoleService.getBySyRoleName(userRole);
			 if((syRole!=null) && (!syRole.getRolename().equalsIgnoreCase("超级管理员"))){
				 String operatemapStr = syRole.getOperatemap();
				 String[] operatemapStrs = operatemapStr.split(",");
				 for(int i=0;i<operatemapStrs.length;i++){
					 String[] operatemaps = operatemapStrs[i].split(":");
					 if(operatemaps.length>=2){
						 visibleMap.put(operatemaps[0], operatemaps[1]);
					 }
				 }
			 }else{
				for(int i=0;i<syModules.size();i++){
					SyModule syModule=syModules.get(i);
					visibleMap.put(syModule.getMenuid(), "1");
				}
			 }
		 }
		 return visibleMap;
	}
	
	@Action("/sys/syParameter_Get_MainWebInfo")
	public void syParameterGetMainWebInfo(){
		Web web = new Web();
		SyParameter syParameter=(SyParameter) syParameterService.getUsedSyParameter();
		SyUser syUser=syUserService.getByUserId(this.getLoginUserId());
		web.setC_m_footer(syParameter.getFooter());
		web.setC_m_navtitle(syParameter.getNavtitle());
		web.setI_u_userip(getRequest().getRemoteAddr());
		web.setI_u_username(syUser.getUsername());
		web.setI_u_userrole(syUser.getRolename());
		web.setI_u_userid(syUser.getUserid());
		web.setTitle(syParameter.getTitle());
		web.setLeftMenu(leftMenu());
		web.setThemename(syUser.getThemename());
		this.writeJson(web);
	}
	
	@Action("/sys/syParameter_Get_LoginWebInfo")
	public void syParameterGetLoginWebInfo(){
		Web web = new Web();
		SyParameter syParameter=null;
		try{
			syParameter=(SyParameter) syParameterService.getUsedSyParameter();
		}catch(Exception e){
			e.printStackTrace();
		}
		web.setI_u_login_foot(syParameter.getFooter());
		web.setI_u_login_header(syParameter.getTitle());
		this.writeJson(web);
	}
	
	@Action("/sys/syParameter_Get_WebLeftMenuInfo")
	public void syParameterGetWebLeftMenuInfo(){
		if(this.getSystemId()==null){
			this.writeJson(toTree());
		}else{
			this.writeJson(toTree(this.getSystemId()));
		}
	}
	
	private List<Tree> toTree(String systemId){
		SyProject syProject=new SyProject();
		syProject.setId(Integer.parseInt(systemId));
		syProject=syProjectService.get(syProject).get(0);
		List<Tree> ts =new ArrayList();
		if(syProject==null){
			this.writeMsg(SysVal.GET_ER);
			return ts;
		}else{
			String modules=","+syProject.getProjectModule()+",";
			List<Menu> ms=leftMenu().getMenus();
			for(int i=0;i<ms.size();i++){
				if(modules.indexOf(","+ms.get(i).getMenuname()+",")==-1)
					continue;
				Tree t = new Tree();
				t.setId(ms.get(i).getMenuid());
				t.setText(ms.get(i).getMenuname());
				List<Menus> mss = ms.get(i).getMenus();
				List<Tree> cs = new ArrayList();
				for(int j=0;j<mss.size();j++){
					Tree mt=new Tree();
					mt.setId(mss.get(j).getMenuid());
					mt.setText(mss.get(j).getMenuname());
					mt.setState("open");
					cs.add(mt);
				}
				t.setChildren(cs);
				t.setState("open");
				ts.add(t);
			}
			return ts;
		}
	}
	
	private List<Tree> toTree(){
		List<Tree> ts =new ArrayList();
		List<Menu> ms=leftMenu().getMenus();
		for(int i=0;i<ms.size();i++){
			Tree t = new Tree();
			t.setId(ms.get(i).getMenuid());
			t.setText(ms.get(i).getMenuname());
			System.out.println(t.getText());
			List<Menus> mss = ms.get(i).getMenus();
			List<Tree> cs = new ArrayList();
			for(int j=0;j<mss.size();j++){
				Tree mt=new Tree();
				mt.setId(mss.get(j).getMenuid());
				mt.setText(mss.get(j).getMenuname());
				mt.setState("open");
				cs.add(mt);
			}
			t.setChildren(cs);
			t.setState("open");
			ts.add(t);
		}
		return ts;
	}
	
	public LeftMenu leftMenu(){
		String pid="i_m_leftmenu";
		LeftMenu leftMenu = new LeftMenu(pid);
		List<Menu> menusll=new ArrayList();
		menusll.addAll(getModules(leftMenu));
		leftMenu.setMenus(menusll);
		return leftMenu;
	}
	
	private Comparator<Menu> getMenuCompBySort(){
		Comparator<Menu> comparator = new Comparator<Menu>(){
			   public int compare(Menu s1, Menu s2) {
				   return s1.getSort()-s2.getSort();
			   }};
			   return comparator;
	}
	
	private Comparator<Menu.Menus> getMenusCompBySort(){
		Comparator<Menu.Menus> comparator = new Comparator<Menu.Menus>(){
			   public int compare(Menu.Menus s1, Menu.Menus s2) {
				   return s1.getSort()-s2.getSort();
			   }};
			   return comparator;
	}
	
	public List<Menu> getModules(LeftMenu leftMenu){
		List<Menu> menusll=new ArrayList();
		List<SyModule> syModules=syModuleService.list();
		if(visibleMap==null){visibleMap=isVisible(syModules);}
		for(int i=0;i<syModules.size();i++){
			SyModule syModule =syModules.get(i);
			if(syModule.getPid().equalsIgnoreCase("0")){//menu
				//syModules.remove(i); //优化
				Menu menu=leftMenu.new Menu(syModule.getMenuid(),syModule.getIcon(),syModule.getMenuname(),syModule.getSort());
				List<Menus> menusl=new ArrayList();
				for(int j=0;j<syModules.size();j++){
					SyModule syModule_ =syModules.get(j);
					if(!(visibleMap.get(syModule_.getMenuid())==null?"":visibleMap.get(syModule_.getMenuid())).toString().split("-")[0].equalsIgnoreCase("1")){
						continue;
					}
					if(syModule_.getPid().equalsIgnoreCase(syModule.getMenuid())){
						//syModules.remove(j); //优化
						Menus menus=null;
						if(syModule_.getUrl().indexOf("http://")==0){
							menus=menu.new Menus(syModule_.getMenuid(),syModule_.getIcon(),syModule_.getMenuname(),syModule_.getUrl(),syModule_.getSort());
						}else{
							menus=menu.new Menus(syModule_.getMenuid(),syModule_.getIcon(),syModule_.getMenuname(),getRequest().getContextPath()+syModule_.getUrl(),syModule_.getSort());
						}
						menusl.add(menus);
					}
				}
				if(menusl.size()!=0){
					Collections.sort(menusl,getMenusCompBySort());
					menu.setMenus(menusl);
					menusll.add(menu);
				}
			}
		}
		Collections.sort(menusll,getMenuCompBySort());
		return menusll;
	}
	
	@Action("/sys/syParameter_List")
	public void syParameterList(){
		this.writeJson(syParameterService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syParameter_Excel")
	public void syParameterExcel(){
		 String title = "Excel表";
		 List<Object> list = syParameterService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		 super.excel(title,list);
	}
	
	@Action("/sys/syParameter_Set_Defult")
	public void syParameterSetDefult(){
		if(this.getIds().trim()=="")return;
		SyParameterUsed syParameterUsed = new SyParameterUsed();
		syParameterUsed.setId(1);
		String[] ids_=this.getIds().split(",");
		if(ids_.length!=1) {writeMsg(SysVal.SET_ER); return;}
		syParameterUsed.setSyParameterId(this.getIds());
		syParameterService.setDefult(syParameterUsed);
		writeMsg(SysVal.SET_SUC);
	}
	
	@Action("/sys/syParameter_Get_ById")  
	public void syParameterGetById(){
		SyParameter syParameter=new SyParameter();
		syParameter.setId(Integer.parseInt(this.id));
		syParameter=syParameterService.get(syParameter).get(0);
		if(syParameter==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syParameter);
		}
	}
	
	@Action("/sys/syParameter_Delete")
	public void syParameterDelete(){
		if(this.getIds().trim()=="")return;
		SyParameter syParameter=(SyParameter) syParameterService.getUsedSyParameter();
		if(syParameter!=null) {
			String[] ids_=this.getIds().split(",");
			for(int i=0;i<ids_.length;i++)
			{
				if(ids_[i].equalsIgnoreCase(String.valueOf(syParameter.getId()))){
					this.writeMsg(SysVal.DEL_ER_MAIN);
					return ;
				}
			}
		}
		syParameterService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syParameter_Update")
	public void syParameterUpdate(){
		if(syParameter!=null) syParameterService.saveOrUpdate(syParameter);
		writeMsg(SysVal.EDIT_SUC);
	}
	public SyParameter getSyParameter() {
		return syParameter;
	}
	public void setSyParameter(SyParameter syParameter) {
		this.syParameter = syParameter;
	}
	//系统路由，请勿删除   start
	@Action("/sys/syParameter_Go_SyParameter") 
	public String syParameterGoSyParameter(){return "syParameter";}
	@Action("/sys/syParameter_Go_SyModule")  
	public String syParameterGoSyModule(){return "syModule";}
	@Action("/sys/syParameter_Go_SyUser") 
	public String syParameterGoSyUser(){return "syUser";}
	@Action("/sys/syParameter_Go_SyOrgan") 
	public String syParameterGoSyOrgan(){return "syOrgan";}
	@Action("/sys/syParameter_Go_SyRole") 
	public String syParameterGoSyRole(){return "syRole";}
	@Action("/sys/syParameter_Go_SyDesign") 
	public String syParameterGoSyDesign(){return "syDesign";}
	@Action("/sys/syParameter_Go_SyFile")
	public String syParameterGoSyFile(){return "syFile";}
	@Action("/sys/syParameter_Go_syFormDesign")
	public String syParameterGosyFormDesign(){return "syFormDesign";}
	@Action("/sys/syParameter_Go_SyLog")
	public String syParameterGoSyLog(){return "syLog";}
	@Action("/sys/syParameter_Go_SyAssembly")
	public String syParameterGoSyAssembly(){return "syAssembly";}
	@Action("/sys/syParameter_Go_SyNotice")
	public String syParameterGoSyNotice(){return "syNotice";}
	@Action("/sys/syParameter_Go_SyChart")
	public String syParameterGoSyChart(){return "syChart";}
	@Action("/sys/syParameter_Go_SyDebugState")
	public String syParameterGoSyDebugState(){return "syDebugState";}
	@Action("/sys/syParameter_Go_SyDebug")
	public String syParameterGoSyDebug(){return "syDebug";}
	@Action("/sys/syParameter_Go_SyWebmodal")
	public String syParameterGoSyWebmodal(){return "syWebmodal";}
	@Action("/sys/syParameter_Go_SyDemoGridtochart")
	public String syParameterGoSyDemoGridtochart(){return "syDemoGridtochart";}
	@Action("/sys/syParameter_Go_SyDemoDatetree")
	public String syParameterGoSyDemoDatetree(){return "syDemoDatetree";}
	@Action("/sys/syParameter_Go_SyDemoTreeiframe")
	public String syParameterGoSyDemoTreeiframe(){return "syDemoTreeiframe";}
	@Action("/sys/syParameter_Go_SyProject")
	public String syParameterGoSyProject(){return "syProject";}
	@Action("/sys/syParameter_Go_SyJtopo")
	public String syParameterGoSyJtopo(){return "syJtopo";}
	@Action("/sys/syParameter_Go_syScada")
	public String syParameter_Go_syScada(){return "syScada";}
	//手机路由
	//系统路由，请勿删除   end
	public String syParameterGoTsbEq(){return "tsbEq";}
	@Action("/sys/syParameter_Go_CxSbStateData")
	public String syParameterGoCxSbStateData(){return "cxSbStateData";}
	@Action("/sys/syParameter_Go_CxSbMonitorTerminal")
	public String syParameterGoCxSbMonitorTerminal(){return "cxSbMonitorTerminal";}
	@Action("/sys/syParameter_Go_CxSbFault")
	public String syParameterGoCxSbFault(){return "cxSbFault";}
	@Action("/sys/syParameter_Go_SyTablefieldRemarks")
	public String syParameterGoSyTablefieldRemarks(){return "syTablefieldRemarks";}
	@Action("/sys/syParameter_Go_CxSbTest")
	public String syParameterGoCxSbTest(){return "cxSbTest";}
	@Action("/sys/syParameter_Go_CxSbCompany")
	public String syParameterGoCxSbCompany(){return "cxSbCompany";}
	@Action("/sys/syParameter_Go_CxSbSysdomian")
	public String syParameterGoCxSbSysdomian(){return "cxSbSysdomian";}
	@Action("/sys/syParameter_Go_CxSbTerminal")
	public String syParameterGoCxSbTerminal(){return "cxSbTerminal";}
	@Action("/sys/syParameter_Go_VcxSbReadStatus")
	public String syParameterGoVcxSbReadStatus(){return "vcxSbReadStatus";}
	@Action("/sys/syParameter_Go_CxSbLine")
	public String syParameterGoCxSbLine(){return "cxSbLine";}
	@Action("/sys/syParameter_Go_VcxSbTakeState")
	public String syParameterGoVcxSbTakeState(){return "vcxSbTakeState";}
	@Action("/sys/syParameter_Go_CxSbReadStatus")
	public String syParameterGoCxSbReadStatus(){return "cxSbReadStatus";}
	@Action("/sys/syParameter_Go_CxSbTakeState")
	public String syParameterGoCxSbTakeState(){return "cxSbTakeState";}
	@Action("/sys/syParameter_Go_VcxSbReadStatusMonitor")
	public String syParameterGoVcxSbReadStatusMonitor(){return "vcxSbReadStatusMonitor";}
	@Action("/sys/syParameter_Go_VcxSbTerminal")
	public String syParameterGoVcxSbTerminal(){return "vcxSbTerminal";}
	@Action("/sys/syParameter_Go_VcxSbTakeStateMonitor")
	public String syParameterGoVcxSbTakeStateMonitor(){return "vcxSbTakeStateMonitor";}
	@Action("/sys/syParameter_Go_CxSbTerminalConfig")
	public String syParameterGoCxSbTerminalConfig(){return "cxSbTerminalConfig";}
	@Action("/sys/syParameter_Go_CxSbBalanceDevice")
	public String syParameterGoCxSbBalanceDevice(){return "cxSbBalanceDevice";}
	@Action("/sys/syParameter_Go_VcxSbBalanceData")
	public String syParameterGoVcxSbBalanceData(){return "vcxSbBalanceData";}
	@Action("/sys/syParameter_Go_CxSbPoint")
	public String syParameterGoCxSbPoint(){return "cxSbPoint";}
	@Action("/sys/syParameter_Go_SySbJsDictionaries")
	public String syParameterGoSySbJsDictionaries(){return "sySbJsDictionaries";}
	@Action("/sys/syParameter_Go_SySbPrimitive")
	public String syParameterGoSySbPrimitive(){return "sySbPrimitive";}
	@Action("/sys/syParameter_Go_SySbPrimitiveClass")
	public String syParameterGoSySbPrimitiveClass(){return "sySbPrimitiveClass";}
	@Action("/sys/syParameter_Go_SySbProject")
	public String syParameterGoSySbProject(){return "sySbProject";}
	@Action("/sys/syParameter_Go_SySbProjectPage")
	public String syParameterGoSySbProjectPage(){return "sySbProjectPage";}
	@Action("/sys/syParameter_Go_VcxSbAlarmState")
	public String syParameterGoVcxSbAlarmState(){return "vcxSbAlarmState";}
	@Action("/sys/syParameter_Go_VcxSbAlarmStateMonitor")
	public String syParameterGoVcxSbAlarmStateMonitor(){return "vcxSbAlarmStateMonitor";}
	@Action("/sys/syParameter_Go_VcxSbVoltageMonitor")
	public String syParameterGoVcxSbVoltageMonitor(){return "vcxSbVoltageMonitor";}
	@Action("/sys/syParameter_Go_VcxSbCurrent")
	public String syParameterGoVcxSbCurrent(){return "vcxSbCurrent";}
	@Action("/sys/syParameter_Go_VcxSbCurrentMonitor")
	public String syParameterGoVcxSbCurrentMonitor(){return "vcxSbCurrentMonitor";}
	@Action("/sys/syParameter_Go_VcxSbLeakageCurrent")
	public String syParameterGoVcxSbLeakageCurrent(){return "vcxSbLeakageCurrent";}
	@Action("/sys/syParameter_Go_VcxSbLeakageCurrentMonitor")
	public String syParameterGoVcxSbLeakageCurrentMonitor(){return "vcxSbLeakageCurrentMonitor";}
	@Action("/sys/syParameter_Go_VcxSbVoltage")
	public String syParameterGoVcxSbVoltage(){return "vcxSbVoltage";}
	@Action("/sys/syParameter_Go_SySbRuninfo")
	public String syParameterGoSySbRuninfo(){return "sySbRuninfo";}
	@Action("/sys/syParameter_Go_SyPoint")
	public String syParameterGoSyPoint(){return "syPoint";}
	@Action("/sys/syParameter_Go_SySysdomain")
	public String syParameterGoSySysdomain(){return "sySysdomain";}
	@Action("/sys/syParameter_Go_SySbRightEventInfo")
	public String syParameterGoSySbRightEventInfo(){return "sySbRightEventInfo";}
	@Action("/sys/syParameter_Go_SySbProjectImagesInfos")
	public String syParameterGoSySbProjectImagesInfos(){return "sySbProjectImagesInfos";}
}
