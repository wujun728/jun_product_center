package com.doroodo.sys.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sun.util.logging.resources.logging;

import com.doroodo.base.action.BaseAction;
import com.doroodo.config.SysVal;
import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;
import com.doroodo.sys.model.SyFile;
import com.doroodo.sys.model.SyOrgan;
import com.doroodo.sys.model.SyUser;
import com.doroodo.sys.service.SyOrganService;
import com.doroodo.sys.service.SyUserService;
import com.doroodo.util.SmarteUntil;


@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")  
public class SyUserAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private SyOrganService syOrganService;
	@Autowired
	private SyUserService syUserService;
	private SyOrgan syOrgan;
	private SyUser syUser;
	private String id;
	private String rolename="";
	private String themename="";
	private String username="";
	private String userid="";
	private String password="";
	private String oldPassword="";
	private static final String DEFPSD="123456";
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getUsername() {
		return username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getThemename() {
		return themename;
	}

	public void setThemename(String themename) {
		this.themename = themename;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SyOrgan getSyOrgan() {
		return syOrgan;
	}

	public void setSyOrgan(SyOrgan syOrgan) {
		this.syOrgan = syOrgan;
	}

	public SyUser getSyUser() {
		return syUser;
	}

	public void setSyUser(SyUser syUser) {
		this.syUser = syUser;
	}
	
	//检查字段是否唯一
	private String isSingle(SyUser syUser,String fieldName,String fieldValue){
		String result=null;
		List<SyUser> lsList = syUserService.get(syUser);
		if(syUserService.get(syUser).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}
	
	@Action("/sys/syUser_Update_Role")
	public void syUserUpdateRole(){
		if( (this.getRolename().trim()=="")||(this.getIds().trim()=="") ){
			this.writeMsg(SysVal.EDIT_ER);
			return;
		}
		syUserService.updateRole(this.getIds(), this.getRolename());
		this.writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syUser_Add")
	public void syUserAdd(){
		SyUser su = new SyUser();
		su.setUserid(syUser.getUserid());
		String resultString=isSingle(su,"登录名",syUser.getUserid());
		if(resultString!=null){this.writeMsg(resultString);return;}
		syUser.setOrganid(syUser.getRouteid());
		if(syUser.getPassword()==null||syUser.getPassword().trim().equalsIgnoreCase("")){
			syUser.setPassword(SmarteUntil.md5(DEFPSD));
		}else{
			syUser.setPassword(SmarteUntil.md5(syUser.getPassword().trim()));
		}
		syUser.setRolename("普通用户");
		syUser.setThemename("default");
		syUserService.saveOrUpdate(syUser);
		writeMsg(SysVal.ADD_SUC);
	}
	
	@Action("/sys/syUser_List")
	public void syUserList(){
		this.writeJson(syUserService.dataGrid(syOrgan,this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syUser_Delete")
	public void syUserDelete(){
		if(this.getIds().trim()=="")return;
		syUserService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syUser_Update_Organ")
	public void syUserUpdateOrgan(){
		if(syOrgan!=null) syOrganService.saveOrUpdate(syOrgan);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	
	@Action("/sys/syUser_Update")
	public void syUserUpdate(){
		if(syUser!=null) {
			SyUser syUser_=new SyUser();
			syUser_.setId(syUser.getId());
			syUser_=syUserService.get(syUser_).get(0);
				if(syUser_.getUserid().endsWith(syUser.getUserid())){
					syUser.setOrganid(syUser.getRouteid());
					syUser.setThemename(syUser_.getThemename());
					syUser.setRolename(syUser_.getRolename());
					syUser.setPassword(syUser_.getPassword());
					syUserService.saveOrUpdate(syUser);
					writeMsg(SysVal.EDIT_SUC);
				}else{
					writeMsg("您不能修改用户登录名!");
				}
			}
	}
	
	@Action("/sys/syUser_Update_")
	public void syUserUpdate_(){
		if(syUser!=null && !this.oldPassword.trim().isEmpty()){
			String newSyUserPassword=syUser.getPassword().trim();
			String newSyUserUserName=syUser.getUsername().trim();
			syUser.setPassword(SmarteUntil.md5(oldPassword));
			syUser=syUserService.login(syUser);
			if(syUser!=null){
				if(!newSyUserPassword.isEmpty()){
					syUser.setPassword(SmarteUntil.md5(newSyUserPassword));
				}
				syUser.setUsername(newSyUserUserName);
				syUserService.saveOrUpdate(syUser);
			}else{
				this.writeMsg(SysVal.EDIT_ER);
				return;
			}
			
			}
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syUser_E_SRole")
	public void syUserESRole(){
		if(this.getRolename().trim().equalsIgnoreCase(""))  {this.writeMsg(SysVal.EDIT_ER);return;}
		String user=this.getLoginUserId();    
		if(user!=null){  
			SyUser syUser = new SyUser();
			syUser.setUserid(user);
			List<SyUser> lsList = syUserService.get(syUser);
			if(lsList.size()>0) {
				syUser=lsList.get(0);
				if(syUser.getRolename().indexOf(this.getRolename())!=-1){
					getSession().put("userrole", this.getRolename()); 
					this.writeMsg(SysVal.EDIT_SUC);
				}else{
					this.writeMsg(SysVal.EDIT_ER);
				}
			}else{
				this.writeMsg(SysVal.EDIT_ER);
			}
		} else{
			writeMsg(SysVal.LOGIN_NO);
		}
	}
	
	@Action("/sys/syUser_Login")  
	public void syUserLogin(){
		SyUser syUser = new SyUser();
		syUser.setUserid(this.getUserid());
		syUser.setPassword(SmarteUntil.md5(this.getPassword()));
		syUser=syUserService.login(syUser);
		Map map = new HashMap();
		if(syUser!=null){
			getSession().put("username", syUser.getUsername()); 
			getSession().put("userid", syUser.getUserid()); 
			getSession().put("userrole", syUser.getRolename().split(",")[0]); 
			getSession().put("topthemeName", syUser.getThemename()); 
			map.put("info", SysVal.LOGIN_YES);
			map.put("islogin", 1);
		}else{
			map.put("info", SysVal.LOGIN_ER);
			map.put("islogin", 0);
		}
		this.writeJson(map);
	}
	
	@Action("/sys/syUser_LoginOut")
	public void syUserLoginOut(){ 
		getSession().put("username",null);
		getSession().put("userid",null);
	}
	
	@Action("/sys/syUser_Update_Theme")
	public void syUserUpdateTheme(){
		if(themename.equalsIgnoreCase("")) {
			this.writeMsg(SysVal.EDIT_ER);
			return;
		}
		String userid = this.getLoginUserId();
		SyUser syUser=new SyUser();
		syUser.setUserid(userid);
		List<SyUser> syUsers=syUserService.get(syUser);
		if(syUsers.size()!=1){
			this.writeMsg(SysVal.OPT_ERROR);
			return;
		}
		syUser=syUserService.get(syUser).get(0);
		if(syUser==null){
			this.writeMsg(SysVal.OPT_ERROR);
			return;
		}
		syUser.setThemename(themename);
		syUserService.saveOrUpdate(syUser);
		getSession().put("topthemeName", syUser.getThemename()); 
		this.writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syUser_Excel")
	public void syUserExcel(){
		 String title = "Excel表";
		 List<Object> list = syUserService.dataGrid(syOrgan,this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		 super.excel(title,list);
	}
	
	@Action("/sys/syUser_Get_IsLogin")  
	public void syUserGetIsLogin(){
		super.isLogin();
	}
	
	@Action("/sys/syUser_Get_ById")  
	public void syUserGetById(){
		SyUser syUser=new SyUser();
		syUser.setId(Integer.parseInt(this.id));
		syUser=syUserService.get(syUser).get(0);
		if(syUser==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syUser);
		}
	}
	
	@Action("/sys/syUser_Get_ByUserId")  
	public void syUserGetByUserId(){
		syUser=syUserService.getByUserId(userid);
		if(syUser==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syUser);
		}
	}
	
	@Action("/sys/syUser_Get_ByObj")  
	public void syUserGetByObj(){
		this.writeJson(syUserService.get(syUser));
	}
	
	@Action("/sys/syUser_Upload")
	public void syUserUpload() throws IOException{
		String msg=SysVal.UPDATA_SUC;
		List<File> fileGroup=this.getFileGroup();
		List<String> fileGroupFileName=this.getFileGroupFileName();
		if(fileGroup==null||fileGroupFileName==null){this.writeMsg(SysVal.NOFILE); return;}
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//设置日期格式
		try {
			Map< String, String> map=this.uploadFiles(fileGroup,fileGroupFileName,"");
			for(int i=0;i<fileGroupFileName.size();i++){
				SyFile syFile_=new SyFile();
				String fileName=fileGroupFileName.get(i);
				String sysName=map.get(fileName);
				String createTime=df.format(new Date());
				syFile_.setCreatetime(createTime);
				syFile_.setFilename(fileName);
				syFile_.setSysname(sysName);
				syFile_.setUserid(this.getLoginUserId());
				syFileService.saveOrUpdate(syFile_);
				Excel e = new Excel(fileName,new FileInputStream(fileGroup.get(i)));
				Table t=e.getSheet(0).getAsTable();
				String[] ps=t.getHeader();
				syUser=new SyUser();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syUser,ps[ej],t.getCell(ei,ej));
					 }
					 System.out.print("共"+t.getRowSize()+"行,现在在"+ei+"行");
					 syUser.setId(null);
					 if(syUser.getPassword()==null||syUser.getPassword().trim().equalsIgnoreCase("")){
							syUser.setPassword(SmarteUntil.md5(DEFPSD));
						}
					 if(syUser.getRolename().trim().endsWith("")){
						 syUser.setRolename("普通用户");
					 }
					 if(syUser.getThemename().trim().endsWith("")){
						syUser.setThemename("default");
					 }
					 syUserService.saveOrUpdate(syUser);
					 System.out.print("执行成功!");
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyUser syUser,String p,String v){
		Field field = null;
		try {
			field = syUser.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syUser,Integer.parseInt(v));  
			}else{
				field.set(syUser,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，smarte平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
}
