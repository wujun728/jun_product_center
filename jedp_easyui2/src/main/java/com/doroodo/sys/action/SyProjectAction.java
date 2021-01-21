package com.doroodo.sys.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.doroodo.base.action.BaseAction;
import com.doroodo.config.SysVal;
import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;

import com.doroodo.base.model.*;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;

//import com.doroodo.work.model.*;
//import com.doroodo.work.service.*;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SyProjectAction extends BaseAction{
	@Autowired
	private SyProjectService syProjectService;
	private SyProject syProject;
	private String EXCEL_TITLE = "项目列表";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	public String getTableHtml() {
		return tableHtml;
	}

	public String getTableTitle() {
		return tableTitle;
	}

	public void setTableTitle(String tableTitle) {
		this.tableTitle = tableTitle;
	}

	public void setTableHtml(String tableHtml) {
		this.tableHtml = tableHtml;
	}
	
	public SyProject getSyProject(){
		return syProject;
	}
	
	public void setSyProject(SyProject syProject){
		this.syProject=syProject;
	}
	
	@Action("/sys/syProject_Add")
	public void syProjectAdd(){
		syProjectService.saveOrUpdate(syProject);
		if(syProject.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syProject.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/syProject_Add_HasFiles")
	public void syProjectAddHasFiles(){
		syProjectService.saveOrUpdate(syProject);
		Map m=new HashMap();
		if(syProject.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syProject.getId());
			m.put("fileid", "syProject-"+syProject.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/syProject_Delete_HasFiles")
	public void syProjectDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		syProjectService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syProject-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syProject_List")
	public void syProjectList(){
		this.writeJson(syProjectService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syProject_List_All")
	public void syProjectListAll(){
		this.writeJson(syProjectService.listAll());
	}
	
	@Action("/sys/syProject_ComboBox")
	public void syProjectComboBox(){
		List<SyProject> l = syProjectService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SyProject obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/syProject_Delete")
	public void syProjectDelete(){
		if(this.getIds().trim()=="")return;
		syProjectService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syProject_Update")
	public void syProjectUpdate(){
		if(syProject!=null) syProjectService.saveOrUpdate(syProject);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syProject_Excel")
	public void syProjectExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = syProjectService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = syProjectService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/syProject_FormFile")
	public void syProjectFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/syProject_Upload")
	public void syProjectUpload() throws IOException{
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
				syProject=new SyProject();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syProject,ps[ej],t.getCell(ei,ej));
					 }
					 syProject.setId(null);
					 syProjectService.saveOrUpdate(syProject);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyProject syProject,String p,String v){
		Field field = null;
		try {
			field = syProject.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syProject,Float.valueOf(v).intValue());
			}else{
				field.set(syProject,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syProject_Get_ById")  
	public void syProjectGetById(){
		SyProject syProject=new SyProject();
		syProject.setId(Integer.parseInt(this.getId()));
		syProject=syProjectService.get(syProject).get(0);
		if(syProject==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syProject);
		}
	}
	
	@Action("/sys/syProject_Get_ByObj")  
	public void syProjectByObj(){
		this.writeJson(syProjectService.get(syProject));
	}
	
	//检查字段是否唯一
	private String isSingle(SyProject syProject,String fieldName,String fieldValue){
		String result=null;
		List<SyProject> lsList = syProjectService.get(syProject);
		if(syProjectService.get(syProject).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}
	
	@Action("/sys/syProject_Set_Defult")
	public void syProjectSetDefult(){
		if(this.getIds().trim()=="")return;
		SyProject syProject=new SyProject();
		syProject.setProjectUsed(1);
		List<SyProject> lsList = syProjectService.get(syProject);
		for(int i=0;i<lsList.size();i++){
			SyProject syProject_=lsList.get(i);
			syProject_.setProjectUsed(0);
			syProjectService.saveOrUpdate(syProject_);
		}
		String[] ids_=this.getIds().split(",");
		if(ids_.length!=1) {writeMsg(SysVal.SET_ER); return;}
		SyProject syProject_userd=new SyProject();
		syProject_userd.setId(Integer.parseInt(ids_[0]));
		List<SyProject> lsList_ = syProjectService.get(syProject_userd);
		for(int i=0;i<lsList_.size();i++){
			SyProject syProject_=lsList_.get(i);
			syProject_.setProjectUsed(1);
			syProjectService.saveOrUpdate(syProject_);
		}
		writeMsg(SysVal.SET_SUC);
	}

}
