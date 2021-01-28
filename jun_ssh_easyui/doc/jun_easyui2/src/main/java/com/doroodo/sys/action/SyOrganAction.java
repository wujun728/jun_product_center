package com.doroodo.sys.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.doroodo.base.action.BaseAction;
import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;
import com.doroodo.sys.model.SyFile;
import com.doroodo.sys.model.SyOrgan;
import com.doroodo.sys.model.SyRole;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.SyUser;
import com.doroodo.sys.service.SyOrganService;
import com.doroodo.sys.service.SyUserService;


@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")  
public class SyOrganAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private SyOrganService syOrganService;
	@Autowired
	private SyUserService syUserService;
	private SyOrgan syOrgan;
	private SyUser syUser;
	private String id;

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
	
	@Action("/sys/syOrgan_Add")
	public void syOrganAdd(){
		syOrgan.setUporganid(syOrgan.getRoutename());//父id
		syOrganService.saveOrUpdate(syOrgan);
		writeMsg(SysVal.ADD_SUC);
	}
	
	@Action("/sys/syOrgan_Delete")
	public void syOrganDelete(){
		if(this.getIds().trim()=="")return;
		String[] ids_=this.getIds().split(",");
		for(int i=0;i<ids_.length;i++)
		{
			if(ids_[i].equalsIgnoreCase("1")){
				writeMsg(SysVal.EDIT_ER); return;
			}
		}
		syOrganService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syOrgan_Update")
	public void syOrganUpdate(){
		if(syOrgan.getId().toString().equalsIgnoreCase("1")){
			writeMsg(SysVal.EDIT_ER); return;
		}
		SyOrgan syOrgan_=new SyOrgan();
		syOrgan_.setOrganid(syOrgan.getOrganid());
		syOrgan_=syOrganService.get(syOrgan_).get(0);
		String[] strUpOrganames=syOrgan_.getRoutename().split("/");
		if(syOrgan.getRoutename().equalsIgnoreCase(strUpOrganames[strUpOrganames.length-2])){
			syOrgan.setUporganid(syOrgan_.getUporganid());
		}else if(syOrgan.getRoutename().equalsIgnoreCase(syOrgan_.getOrganid())){
			 writeMsg("从属组织不能是自己,请重新选择!"); return;
		}else{
			SyOrgan syOrgan_p=new SyOrgan();
			syOrgan_p.setOrganid(syOrgan.getRoutename());
			List<SyOrgan> syOrgans=syOrganService.get(syOrgan_p);
			if(syOrgans.size()>0){
				syOrgan_p=syOrgans.get(0);
				String strRouteids=syOrgan_p.getRouteid();
				if(strRouteids.indexOf(syOrgan.getOrganid())!=-1){
					writeMsg("从属组织不能是下级组织,请重新选择!"); return;
				}
				syOrgan.setUporganid(syOrgan.getRoutename());
			}else{
				writeMsg("从属组织不存在,请重新选择!"); return;
			}
		}
		if(syOrgan!=null) syOrganService.saveOrUpdate(syOrgan);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syOrgan_Get_Tree")
	public void syOrganGetTree(){
		this.writeJson(syOrganService.tree(id));
	}
	
	@Action("/sys/syOrgan_List")
	public void syOrganList(){
		this.writeJson(syOrganService.dataGrid(syOrgan,this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syOrgan_Excel")
	public void syOrganExcel(){
		 String title = "Excel表";
		 List<Object> list = syOrganService.dataGrid(syOrgan,this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		 super.excel(title,list);
	}
	
	@Action("/sys/syOrgan_Get_ByObj")  
	public void syOrganGetByObj(){
		this.writeJson(syOrganService.get(syOrgan));
	}
	
	private void generateOrganTree(Table t,SyOrgan syOrgan,List<SyOrgan> syOrgans){
		if(syOrgan==null)return;
		syOrgan.setId(null);
		String upId=syOrgan.getUporganid();
		SyOrgan syOrgan_=new SyOrgan();
		syOrgan_.setOrganid(upId);
		syOrgans.add(syOrgan);
		if(syOrganService.get(syOrgan_).size()>0){
			for(int i=syOrgans.size();i>0;i--){
				syOrganService.saveOrUpdate(syOrgans.get(i-1));
			}
		}
		else{
			generateOrganTree(t,getSyOrganByUpId(t,upId),syOrgans);//通过pid在excel中找到行
		}
	}
	
	private SyOrgan getSyOrganByUpId(Table t,String upId){
		SyOrgan syOrgan=new SyOrgan();
		String[] ps=t.getHeader();
		for(int ei=0;ei<t.getRowSize();ei++){//读出行
			 for(int ej=0;ej<t.getColSize();ej++){//读出列
				 setPValue(syOrgan,ps[ej],t.getCell(ei,ej));
			 }
			 if(syOrgan.getOrganid().equalsIgnoreCase(upId)){
				 return syOrgan;
			 }
		}
		return syOrgan;
	}
	
	@Action("/sys/syOrgan_Upload")
	public void syOrganUpload() throws IOException{
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
				syOrgan=new SyOrgan();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syOrgan,ps[ej],t.getCell(ei,ej));
					 }
					 System.out.print("共"+t.getRowSize()+"行,现在在"+ei+"行");
					 if(syOrganService.get(syOrgan).size()>0) {continue;}//如何已经有就跳过
					 List<SyOrgan> syOrgans=new ArrayList();
					 generateOrganTree(t,syOrgan,syOrgans);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyOrgan syOrgan,String p,String v){
		Field field = null;
		try {
			field = syOrgan.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syOrgan,Integer.parseInt(v));  
			}else{
				field.set(syOrgan,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，smarte平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syOrgan_Get_ById")  
	public void syModuleGetById(){
		SyOrgan syOrgan=new SyOrgan();
		syOrgan.setId(Integer.parseInt(this.id));
		syOrgan=syOrganService.get(syOrgan).get(0);
		if(syOrgan==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syOrgan);
		}
	}
}
