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
import com.doroodo.config.SysVal;

import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;

import com.doroodo.base.action.BaseAction;
import com.doroodo.base.model.*;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SyWebmodalAction extends BaseAction{
	@Autowired
	private SyWebmodalService syWebmodalService;
	private SyWebmodal syWebmodal;
	private String EXCEL_TITLE = "页面模板";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	private String name = "";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
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
	
	public SyWebmodal getSyWebmodal(){
		return syWebmodal;
	}
	
	public void setSyWebmodal(SyWebmodal syWebmodal){
		this.syWebmodal=syWebmodal;
	}
	
	@Action("/sys/syWebmodal_Add")
	public void syWebmodalAdd(){
		syWebmodalService.saveOrUpdate(syWebmodal);
		if(syWebmodal.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syWebmodal.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/syWebmodal_Add_HasFiles")
	public void syWebmodalAddHasFiles(){
		syWebmodalService.saveOrUpdate(syWebmodal);
		Map m=new HashMap();
		if(syWebmodal.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syWebmodal.getId());
			m.put("fileid", "syWebmodal-"+syWebmodal.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/syWebmodal_Delete_HasFiles")
	public void syWebmodalDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		syWebmodalService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syWebmodal-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syWebmodal_List")
	public void syWebmodalList(){
		this.writeJson(syWebmodalService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syWebmodal_List_All")
	public void syWebmodalListAll(){
		this.writeJson(syWebmodalService.listAll());
	}
	
	@Action("/sys/syWebmodal_ComboBox")
	public void syWebmodalComboBox(){
		List<SyWebmodal> l = syWebmodalService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SyWebmodal obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/syWebmodal_Delete")
	public void syWebmodalDelete(){
		if(this.getIds().trim()=="")return;
		syWebmodalService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syWebmodal_Update")
	public void syWebmodalUpdate(){
		if(syWebmodal!=null) syWebmodalService.saveOrUpdate(syWebmodal);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syWebmodal_Excel")
	public void syWebmodalExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = syWebmodalService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = syWebmodalService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/syWebmodal_FormFile")
	public void syWebmodalFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/syWebmodal_Upload")
	public void syWebmodalUpload() throws IOException{
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
				syWebmodal=new SyWebmodal();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syWebmodal,ps[ej],t.getCell(ei,ej));
					 }
					 syWebmodal.setId(null);
					 syWebmodalService.saveOrUpdate(syWebmodal);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyWebmodal syWebmodal,String p,String v){
		Field field = null;
		try {
			field = syWebmodal.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syWebmodal,Float.valueOf(v).intValue());
			}else{
				field.set(syWebmodal,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syWebmodal_Get_ById")  
	public void syWebmodalGetById(){
		SyWebmodal syWebmodal=new SyWebmodal();
		syWebmodal.setId(Integer.parseInt(this.getId()));
		syWebmodal=syWebmodalService.get(syWebmodal).get(0);
		if(syWebmodal==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syWebmodal);
		}
	}
	
	@Action("/sys/syWebmodal_Get_ByName")  
	public void syWebmodalGetByName(){
		SyWebmodal syWebmodal=new SyWebmodal();
		syWebmodal.setTitle(this.getName());
		syWebmodal=(SyWebmodal) syWebmodalService.get(syWebmodal).get(0);
		if(syWebmodal==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syWebmodal);
		}
	}
	
	@Action("/sys/syWebmodal_Get_ByObj")  
	public void syWebmodalByObj(){
		this.writeJson(syWebmodalService.get(syWebmodal));
	}
	
	//检查字段是否唯一
	private String isSingle(SyWebmodal syWebmodal,String fieldName,String fieldValue){
		String result=null;
		List<SyWebmodal> lsList = syWebmodalService.get(syWebmodal);
		if(syWebmodalService.get(syWebmodal).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
