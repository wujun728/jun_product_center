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
public class TsbPrimitiveClassAction extends BaseAction{
	@Autowired
	private TsbPrimitiveClassService tsbPrimitiveClassService;
	private TsbPrimitiveClass tsbPrimitiveClass;
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
	
	public TsbPrimitiveClass getTsbPrimitiveClass(){
		return tsbPrimitiveClass;
	}
	
	public void setTsbPrimitiveClass(TsbPrimitiveClass tsbPrimitiveClass){
		this.tsbPrimitiveClass=tsbPrimitiveClass;
	}
	
	@Action("/sys/tsbPrimitiveClass_Add")
	public void tsbPrimitiveClassAdd(){
		tsbPrimitiveClassService.saveOrUpdate(tsbPrimitiveClass);
		if(tsbPrimitiveClass.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", tsbPrimitiveClass.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/tsbPrimitiveClass_Add_HasFiles")
	public void tsbPrimitiveClassAddHasFiles(){
		tsbPrimitiveClassService.saveOrUpdate(tsbPrimitiveClass);
		Map m=new HashMap();
		if(tsbPrimitiveClass.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", tsbPrimitiveClass.getId());
			m.put("fileid", "tsbPrimitiveClass-"+tsbPrimitiveClass.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/tsbPrimitiveClass_Delete_HasFiles")
	public void tsbPrimitiveClassDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		tsbPrimitiveClassService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "tsbPrimitiveClass-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/tsbPrimitiveClass_List")
	public void tsbPrimitiveClassList(){
		if(tsbPrimitiveClass!=null){
			this.writeJson(tsbPrimitiveClassService.dataGrid(this.getPage(), this.getRows(),tsbPrimitiveClass));
		}else{
			this.writeJson(tsbPrimitiveClassService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/tsbPrimitiveClass_List_All")
	public void tsbPrimitiveClassListAll(){
		this.writeJson(tsbPrimitiveClassService.listAll());
	}
	
	@Action("/sys/tsbPrimitiveClass_ComboBox")
	public void tsbPrimitiveClassComboBox(){
		List<TsbPrimitiveClass> l = tsbPrimitiveClassService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			TsbPrimitiveClass obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getClassName().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/tsbPrimitiveClass_Delete")
	public void tsbPrimitiveClassDelete(){
		if(this.getIds().trim()=="")return;
		tsbPrimitiveClassService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/tsbPrimitiveClass_Update")
	public void tsbPrimitiveClassUpdate(){
		if(tsbPrimitiveClass!=null) tsbPrimitiveClassService.saveOrUpdate(tsbPrimitiveClass);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/tsbPrimitiveClass_Excel")
	public void tsbPrimitiveClassExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = tsbPrimitiveClassService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = tsbPrimitiveClassService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/tsbPrimitiveClass_FormFile")
	public void tsbPrimitiveClassFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/tsbPrimitiveClass_Upload")
	public void tsbPrimitiveClassUpload() throws IOException{
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
				tsbPrimitiveClass=new TsbPrimitiveClass();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(tsbPrimitiveClass,ps[ej],t.getCell(ei,ej));
					 }
					 tsbPrimitiveClass.setId(null);
					 tsbPrimitiveClassService.saveOrUpdate(tsbPrimitiveClass);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(TsbPrimitiveClass tsbPrimitiveClass,String p,String v){
		Field field = null;
		try {
			field = tsbPrimitiveClass.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(tsbPrimitiveClass,Float.valueOf(v).intValue());
			}else{
				field.set(tsbPrimitiveClass,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/tsbPrimitiveClass_Get_ById")  
	public void tsbPrimitiveClassGetById(){
		TsbPrimitiveClass tsbPrimitiveClass=new TsbPrimitiveClass();
		tsbPrimitiveClass.setId(Integer.parseInt(this.getId()));
		tsbPrimitiveClass=tsbPrimitiveClassService.get(tsbPrimitiveClass).get(0);
		if(tsbPrimitiveClass==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(tsbPrimitiveClass);
		}
	}
	
	@Action("/sys/tsbPrimitiveClass_Get_ByObj")  
	public void tsbPrimitiveClassByObj(){
		this.writeJson(tsbPrimitiveClassService.get(tsbPrimitiveClass));
	}
	
	//检查字段是否唯一
	private String isSingle(TsbPrimitiveClass tsbPrimitiveClass,String fieldName,String fieldValue){
		String result=null;
		List<TsbPrimitiveClass> lsList = tsbPrimitiveClassService.get(tsbPrimitiveClass);
		if(tsbPrimitiveClassService.get(tsbPrimitiveClass).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
