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

import com.doroodo.base.action.BaseAction;
import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.util.SmarteUntil;
import com.doroodo.config.SysVal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SyNoticeAction extends BaseAction{
	@Autowired
	private SyNoticeService syNoticeService;
	@Autowired
	private SyUserService syUserService;
	private SyNotice syNotice;
	private String id="";
	
	public SyNotice getSyNotice(){
		return syNotice;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSyNotice(SyNotice syNotice){
		this.syNotice=syNotice;
	}
	
	@Action("/sys/syNotice_Add")
	public void syNoticeAdd(){
		syNotice.setCreatetime(SmarteUntil.getCurrentTime());
		syNoticeService.saveOrUpdate(syNotice);
		Map m=new HashMap();
		m.put("info", SysVal.ADD_SUC);
		m.put("fileid", "syNotice-"+syNotice.getId());
		this.writeJson(m);
	}
	
	@Action("/sys/syNotice_List")
	public void syNoticeList(){
		if(syNotice!=null){
			this.writeJson(syNoticeService.dataGrid(this.getPage(), this.getRows(),syNotice));
		}else{
			this.writeJson(syNoticeService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/syNotice_List_All")
	public void syNoticeListAll(){
		this.writeJson(syNoticeService.listAll());
	}
	
	@Action("/sys/syNotice_Delete")
	public void syNoticeDelete(){
		if(this.getIds().trim()=="")return;
		syNoticeService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syNotice-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syNotice_Update")
	public void syNoticeUpdate(){
		if(syNotice!=null) syNoticeService.saveOrUpdate(syNotice);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syNotice_Update_Readusernames")
	public void syNoticeUpdateReadusernames(){
		if(this.getId().trim().isEmpty()) return;
		SyNotice syNotice =new SyNotice();
		syNotice.setId(Integer.parseInt(this.getId()));
		List<SyNotice> syNotices=syNoticeService.get(syNotice);
		if(syNotices.size()==1){
			syNotice=syNotices.get(0);
			String userid=","+this.getLoginUserId();
			String run=syNotice.getReadusernames();
			if(run==null)run="";
			if(run.indexOf(userid)==-1){
				syNotice.setReadusernames(syNotice.getReadusernames()+","+this.getLoginUserId());
				syNoticeService.saveOrUpdate(syNotice);
				writeMsg(SysVal.EDIT_SUC);
			}else{
				writeMsg("您已阅过!");
			}
		}else{
			writeMsg(SysVal.EDIT_ER);
		}
	}
	
	@Action("/sys/syNotice_Get_Readusernames")
	public void syNoticeGetReadusernames(){
		if(this.getId().trim().isEmpty()) return;
		SyNotice syNotice =new SyNotice();
		syNotice.setId(Integer.parseInt(this.getId()));
		List<SyNotice> syNotices=syNoticeService.get(syNotice);
		String readusernames_="";
		if(syNotices.size()==1){
			syNotice=syNotices.get(0);
			String[] readusernames=syNotice.getReadusernames().split(",");
			for(int i=0;i<readusernames.length;i++){
				String readusername=readusernames[i];
				if(!readusername.isEmpty()){
					SyUser syUser=new SyUser();
					syUser.setUserid(readusername);
					List<SyUser> syUsers=syUserService.get(syUser);
					if(syUsers.size()==1){
						readusernames_+=syUsers.get(0).getUsername()+",";
					}
				}
			}
		}
		Map m=new HashMap();
		m.put("readusernames", readusernames_);
		this.writeJson(m);
	}
	
	@Action("/sys/syNotice_Excel")
	public void syNoticeExcel(){
		 String title = "Excel表";
		 List<Object> list = syNoticeService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		 super.excel(title,list);
	}
	
	@Action("/sys/syNotice_Get_ByObj")  
	public void syNoticeGetByObj(){
		this.writeJson(syNoticeService.get(syNotice));
	}
	
	@Action("/sys/syNotice_Upload")
	public void syNoticeUpload() throws IOException{
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
				syNotice=new SyNotice();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syNotice,ps[ej],t.getCell(ei,ej));
					 }
					 syNotice.setId(null);
					 syNoticeService.saveOrUpdate(syNotice);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyNotice syNotice,String p,String v){
		Field field = null;
		try {
			field = syNotice.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syNotice,Float.valueOf(v).intValue());
			}else{
				field.set(syNotice,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，smarte平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syNotice_Get_ById")  
	public void syNoticeGetById(){
		SyNotice syNotice=new SyNotice();
		syNotice.setId(Integer.parseInt(this.id));
		syNotice=syNoticeService.get(syNotice).get(0);
		if(syNotice==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syNotice);
		}
	}
	
	//检查字段是否唯一
	private String isSingle(SyNotice syNotice,String fieldName,String fieldValue){
		String result=null;
		List<SyNotice> lsList = syNoticeService.get(syNotice);
		if(syNoticeService.get(syNotice).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}
}
