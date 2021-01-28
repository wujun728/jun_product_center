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

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SySbPrimitiveClassAction extends BaseAction{
	@Autowired
	private SySbPrimitiveClassService sySbPrimitiveClassService;
	private SySbPrimitiveClass sySbPrimitiveClass;
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
	
	public SySbPrimitiveClass getSySbPrimitiveClass(){
		return sySbPrimitiveClass;
	}
	
	public void setSySbPrimitiveClass(SySbPrimitiveClass sySbPrimitiveClass){
		this.sySbPrimitiveClass=sySbPrimitiveClass;
	}
	
	@Action("/sys/sySbPrimitiveClass_Add")
	public void sySbPrimitiveClassAdd(){
		sySbPrimitiveClassService.saveOrUpdate(sySbPrimitiveClass);
		if(sySbPrimitiveClass.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbPrimitiveClass.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/sySbPrimitiveClass_Add_HasFiles")
	public void sySbPrimitiveClassAddHasFiles(){
		sySbPrimitiveClassService.saveOrUpdate(sySbPrimitiveClass);
		Map m=new HashMap();
		if(sySbPrimitiveClass.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbPrimitiveClass.getId());
			m.put("fileid", "sySbPrimitiveClass-"+sySbPrimitiveClass.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/sySbPrimitiveClass_Delete_HasFiles")
	public void sySbPrimitiveClassDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		sySbPrimitiveClassService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "sySbPrimitiveClass-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/sySbPrimitiveClass_List")
	public void sySbPrimitiveClassList(){
		if(sySbPrimitiveClass!=null){
			this.writeJson(sySbPrimitiveClassService.dataGrid(this.getPage(), this.getRows(),sySbPrimitiveClass));
		}else{
			this.writeJson(sySbPrimitiveClassService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/sySbPrimitiveClass_List_All")
	public void sySbPrimitiveClassListAll(){
		this.writeJson(sySbPrimitiveClassService.listAll());
	}
	
	@Action("/sys/sySbPrimitiveClass_ComboBox")
	public void sySbPrimitiveClassComboBox(){
		List<SySbPrimitiveClass> l = sySbPrimitiveClassService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SySbPrimitiveClass obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getClassName());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/sySbPrimitiveClass_Delete")
	public void sySbPrimitiveClassDelete(){
		if(this.getIds().trim()=="")return;
		sySbPrimitiveClassService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/sySbPrimitiveClass_Update")
	public void sySbPrimitiveClassUpdate(){
		if(sySbPrimitiveClass!=null) sySbPrimitiveClassService.saveOrUpdate(sySbPrimitiveClass);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/sySbPrimitiveClass_Excel")
	public void sySbPrimitiveClassExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = sySbPrimitiveClassService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = sySbPrimitiveClassService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/sySbPrimitiveClass_FormFile")
	public void sySbPrimitiveClassFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/sySbPrimitiveClass_Upload")
	public void sySbPrimitiveClassUpload() throws IOException{
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
				sySbPrimitiveClass=new SySbPrimitiveClass();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(sySbPrimitiveClass,ps[ej],t.getCell(ei,ej));
					 }
					 sySbPrimitiveClass.setId(null);
					 sySbPrimitiveClassService.saveOrUpdate(sySbPrimitiveClass);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SySbPrimitiveClass sySbPrimitiveClass,String p,String v){
		Field field = null;
		try {
			field = sySbPrimitiveClass.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(sySbPrimitiveClass,Float.valueOf(v).intValue());
			}else{
				field.set(sySbPrimitiveClass,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/sySbPrimitiveClass_Get_ById")  
	public void sySbPrimitiveClassGetById(){
		SySbPrimitiveClass sySbPrimitiveClass=new SySbPrimitiveClass();
		sySbPrimitiveClass.setId(Integer.parseInt(this.getId()));
		try{
		sySbPrimitiveClass=sySbPrimitiveClassService.get(sySbPrimitiveClass).get(0);
		if(sySbPrimitiveClass==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(sySbPrimitiveClass);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/sySbPrimitiveClass_Get_ByObj")  
	public void sySbPrimitiveClassByObj(){
		this.writeJson(sySbPrimitiveClassService.get(sySbPrimitiveClass));
	}
	
	//检查字段是否唯一
	private String isSingle(SySbPrimitiveClass sySbPrimitiveClass,String fieldName,String fieldValue){
		String result=null;
		List<SySbPrimitiveClass> lsList = sySbPrimitiveClassService.get(sySbPrimitiveClass);
		if(sySbPrimitiveClassService.get(sySbPrimitiveClass).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
