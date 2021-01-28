package com.doroodo.base.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.doroodo.base.action.BaseAction;
import com.doroodo.code.util.*;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.SyModule;
import com.doroodo.sys.model.SyRole;
import com.doroodo.sys.service.SyModuleService;
import com.doroodo.sys.service.SyRoleService;
import com.doroodo.sys.service.SyUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;
	@Autowired
	private SyUserService syUserService;
	@Autowired
	private SyModuleService syModuleService;
	@Autowired
	private SyRoleService syRoleService;
	private  final String SYSPATH="syParameter_Go_";
	private  Logger logger = Logger.getLogger(AuthorityInterceptor.class);  
	//增加
	private  String strAddKeys="add,new,do";
	//删除
	private  String strDelKeys="delete,del";
	//修改
	private  String strEditKeys="update,edit,upload";
	//查看
	private  String strListKeys="list,select,get,go,excel";
	//系统操作
	private  String strPapKeys="go,login,loginout,e,islogin,loginwebinfo,mainwebinfo,theme";
	//特赦的操作
	private  String strSPKeys="syProject_List_All,syUser_E_SRole,syNotice_List,syLog_List";//填入完整请求
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception{
		 ActionContext ctx=invocation.getInvocationContext();  
		 Map<String, Object> session=ctx.getSession();  
		try{
			String user=(String)session.get("userid");  
			
			//特赦操作
			if(getTypeByVal(optInWhere(ctx.getName())).equalsIgnoreCase("特赦操作")){
				logger.warn("用户名id:"+user+"[角色:"+(String)session.get("userrole")+"] 操作:"+ctx.getName()+" 操作类型:特赦操作");
				return invocation.invoke();  
			}
			//系统操作
			if(getTypeByVal(optInWhere(ctx.getName())).equalsIgnoreCase("系统操作")){
				logger.warn("用户名id:"+user+"[角色:"+(String)session.get("userrole")+"] 操作:"+ctx.getName()+" 操作类型:系统操作");
				return invocation.invoke();  
			}
			if(user!=null){  
				String actionPath=invocation.getProxy().getNamespace()+"/"+SYSPATH+actionNameToClassName(ctx.getName());
				RoleControlResult roleControlResult =roleControl(user,(String)session.get("userrole"),ctx.getName(),actionPath);
				if(roleControlResult.isAccess())
				{
					return invocation.invoke();  
				}else{
					if(!roleControlResult.getOptType().endsWith("查询")){
						Map<String, String> map = new HashMap<String, String>();
						map.put("info",SysVal.OPT_DLG_ERROR);
						((BaseAction)invocation.getAction()).writeJson(map);
						return null;
					}
				}
			}
			((BaseAction)invocation.getAction()).isErr();
		}catch(RuntimeException e){
			Map<String, String> map = new HashMap<String, String>();
			String error="请求地址:"+ctx.getName()+"</br>";
			error+="错误信息:</br><span  style=\"color:red;background-color: #F2FAFF\">"+getStackMsg(e)+"</span>";
			logger.error(error);
			map.put("error", error);
			((BaseAction)invocation.getAction()).writeJson(map);
		}
		return null;
	}
	
	private  String getStackMsg(Exception e) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stackArray = e.getStackTrace();
		for (int i = 0; i < stackArray.length; i++) {
			StackTraceElement element = stackArray[i];
			sb.append(element.toString() + "\n");
		}
		return sb.toString();
	}
	
	class RoleControlResult{
		private boolean isAccess=false;
		private String optType="5";
		private String strLog="";
		public boolean isAccess() {
			return isAccess;
		}
		public void setAccess(boolean isAccess) {
			this.isAccess = isAccess;
		}
		public String getOptType() {
			return optType;
		}
		public void setOptType(String optType) {
			this.optType = optType;
		}
		public String getStrLog() {
			return strLog;
		}
		public void setStrLog(String strLog) {
			this.strLog = strLog;
		}
	};
	
	
	private  RoleControlResult roleControl(String userId,String roleName,String actionName,String actionPath){
		RoleControlResult roleControlResult =new RoleControlResult();
		if("超级管理员".equalsIgnoreCase(roleName)) {
			roleControlResult.setAccess(true);
			roleControlResult.setStrLog("用户名id:"+userId+"[角色:"+roleName+"] 操作:"+actionName+" 操作类型:"+getTypeByVal(optInWhere(actionName))+"  权限控制信息：成功");
			return roleControlResult;
		};
		SyRole syRole=syRoleService.getBySyRoleName(roleName);
		if(syRole!=null){
			List<SyModule> syModules=getSyModuleByUrl(actionPath);
			if(syModules!=null){
				String optmap=getOptMapByModuleName(syRole,syModules);
				if(optmap!=""){
					String[] optMaps=optmap.split(":");
					if(optMaps.length==2){
						String val=optInWhere(actionName);
						if(-1!=optMaps[1].indexOf(val) || val.endsWith("6")){
							roleControlResult.setAccess(true);
							roleControlResult.setOptType(getTypeByVal(val));
							roleControlResult.setStrLog("用户名id:"+userId+"[角色:"+roleName+"] 操作:"+actionName+" 操作类型:"+roleControlResult.getOptType()+"  权限控制信息："+roleControlResult.getOptType()+",成功");
							return roleControlResult;
						}
					}
				}
			}
			roleControlResult.setAccess(false);
			roleControlResult.setOptType(getTypeByVal(optInWhere(actionName)));
			roleControlResult.setStrLog("用户id:"+userId+"[角色:"+roleName+"] 操作:"+actionName+" 操作类型:"+roleControlResult.getOptType()+" 权限控制信息：模版无匹配,失败");
			return roleControlResult;
		}else{
			roleControlResult.setAccess(false);
			roleControlResult.setOptType(getTypeByVal(optInWhere(actionName)));
			roleControlResult.setStrLog("用户名id:"+userId+"[角色:"+roleName+"] 操作:"+actionName+" 操作类型:"+roleControlResult.getOptType()+" 权限控制信息：角色不存在,失败");
			return roleControlResult;
		}
	}
	
	private  String getTypeByVal(String val){
		String result="";
		switch(Integer.parseInt(val)){
		case 2:
			result="新增";
			break;
		case 3:
			result="删除";
			break;
		case 4:
			result="修改";
			break;
		case 5:
			result="查询";
			break;
		case 6:
			result="系统操作";
			break;
		case 7:
			result="特赦操作";
			break;
		}
		return result;
	}

	private  String actionNameToClassName(String actionName){
		String className="";
		if(actionName.indexOf(SYSPATH)==0){
			className=actionName.substring(SYSPATH.length());
		}else{
			String [] strs=actionName.split("_");
			className=StringHelper.capitalize(strs[0]);
		}
		return className;
	}
	
	private  List<SyModule> getSyModuleByUrl(String url){
		SyModule syModule=new SyModule();
		syModule.setUrl(url);
		List<SyModule> syModules=syModuleService.get(syModule);
		return syModules;
	}
	
	//只取最长的
	private  String getOptMapByModuleName(SyRole syRole,List<SyModule> syModules){
		String optMap="";
		String roleOptMap=syRole.getOperatemap();
		for(int i=0;i<syModules.size();i++){
			SyModule syModule=syModules.get(i);
			Pattern pattern = Pattern.compile(syModule.getMenuid()+"\\:([^,]+),");
			Matcher matcher = pattern.matcher(roleOptMap);
			while(matcher.find()) {
				 String str=matcher.group();
				 if(str.length()>optMap.length()){
					 optMap=str;
				 }
			}
		}
		return optMap;
	}
	
	private  String optInWhere(String actionName){
		String value="5";
		String [] strs=actionName.split("_");
		if(strs.length>=2){
			String str1=strs[1].toLowerCase();
			if(isInStrs(actionName,strSPKeys)){
				return value="7";
			}else if(isInStrs(strs[strs.length-1].toLowerCase(),strPapKeys)){
				return value="6";
			}else if(isInStrs(str1,strAddKeys)){
				return value="2";
			}else if(isInStrs(str1,strDelKeys)){
				return value="3";
			}else if(isInStrs(str1,strEditKeys)){
				return value="4";
			}else if(isInStrs(str1,strListKeys)){
				return value="5";
			} 
		}
		return value;
	}
	
	private  boolean isInStrs(String doStr,String opts){
		if(opts.indexOf(doStr)!=-1){
			return true;
		}
		return false;
	}
}
      