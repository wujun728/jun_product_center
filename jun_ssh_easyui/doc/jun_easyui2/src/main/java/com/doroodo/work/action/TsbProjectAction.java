package com.doroodo.work.action;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
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

import com.doroodo.work.model.*;
import com.doroodo.work.service.*;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class TsbProjectAction extends BaseAction{
	@Autowired
	private TsbProjectService tsbProjectService;
	private TsbProject tsbProject;
	private String EXCEL_TITLE = "";//请输入导出的excel表表名
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
	
	public TsbProject getTsbProject(){
		return tsbProject;
	}
	
	public void setTsbProject(TsbProject tsbProject){
		this.tsbProject=tsbProject;
	}
	
	@Action("/sys/tsbProject_Add")
	public void tsbProjectAdd(){
		tsbProjectService.saveOrUpdate(tsbProject);
		if(tsbProject.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", tsbProject.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/tsbProject_Add_HasFiles")
	public void tsbProjectAddHasFiles(){
		tsbProjectService.saveOrUpdate(tsbProject);
		Map m=new HashMap();
		if(tsbProject.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", tsbProject.getId());
			m.put("fileid", "tsbProject-"+tsbProject.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/tsbProject_Delete_HasFiles")
	public void tsbProjectDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		tsbProjectService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "tsbProject-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/tsbProject_List")
	public void tsbProjectList(){
		if(tsbProject!=null){
			this.writeJson(tsbProjectService.dataGrid(this.getPage(), this.getRows(),tsbProject));
		}else{
			this.writeJson(tsbProjectService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/tsbProject_List_All")
	public void tsbProjectListAll(){
		this.writeJson(tsbProjectService.listAll());
	}
	
	@Action("/sys/tsbProject_ComboBox")
	public void tsbProjectComboBox(){
		List<TsbProject> l = tsbProjectService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			TsbProject obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/tsbProject_Delete")
	public void tsbProjectDelete(){
		if(this.getIds().trim()=="")return;
		tsbProjectService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/tsbProject_Update")
	public void tsbProjectUpdate(){
		if(tsbProject!=null) tsbProjectService.saveOrUpdate(tsbProject);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/tsbProject_Excel")
	public void tsbProjectExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = tsbProjectService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = tsbProjectService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/tsbProject_FormFile")
	public void tsbProjectFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/tsbProject_Upload")
	public void tsbProjectUpload() throws IOException{
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
				tsbProject=new TsbProject();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(tsbProject,ps[ej],t.getCell(ei,ej));
					 }
					 tsbProject.setId(null);
					 tsbProjectService.saveOrUpdate(tsbProject);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(TsbProject tsbProject,String p,String v){
		Field field = null;
		try {
			field = tsbProject.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(tsbProject,Float.valueOf(v).intValue());
			}else{
				field.set(tsbProject,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/tsbProject_Get_ById")  
	public void tsbProjectGetById(){
		TsbProject tsbProject=new TsbProject();
		tsbProject.setId(Integer.parseInt(this.getId()));
		tsbProject=tsbProjectService.get(tsbProject).get(0);
		if(tsbProject==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(tsbProject);
		}
	}
	
	@Action("/sys/tsbProject_Get_ByObj")  
	public void tsbProjectByObj(){
		this.writeJson(tsbProjectService.get(tsbProject));
	}
	
	//检查字段是否唯一
	private String isSingle(TsbProject tsbProject,String fieldName,String fieldValue){
		String result=null;
		List<TsbProject> lsList = tsbProjectService.get(tsbProject);
		if(tsbProjectService.get(tsbProject).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
