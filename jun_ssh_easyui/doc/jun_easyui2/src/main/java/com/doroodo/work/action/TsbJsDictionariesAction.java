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
public class TsbJsDictionariesAction extends BaseAction{
	@Autowired
	private TsbJsDictionariesService tsbJsDictionariesService;
	private TsbJsDictionaries tsbJsDictionaries;
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
	
	public TsbJsDictionaries getTsbJsDictionaries(){
		return tsbJsDictionaries;
	}
	
	public void setTsbJsDictionaries(TsbJsDictionaries tsbJsDictionaries){
		this.tsbJsDictionaries=tsbJsDictionaries;
	}
	
	@Action("/sys/tsbJsDictionaries_Add")
	public void tsbJsDictionariesAdd(){
		tsbJsDictionariesService.saveOrUpdate(tsbJsDictionaries);
		if(tsbJsDictionaries.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", tsbJsDictionaries.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/tsbJsDictionaries_Add_HasFiles")
	public void tsbJsDictionariesAddHasFiles(){
		tsbJsDictionariesService.saveOrUpdate(tsbJsDictionaries);
		Map m=new HashMap();
		if(tsbJsDictionaries.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", tsbJsDictionaries.getId());
			m.put("fileid", "tsbJsDictionaries-"+tsbJsDictionaries.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/tsbJsDictionaries_Delete_HasFiles")
	public void tsbJsDictionariesDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		tsbJsDictionariesService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "tsbJsDictionaries-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/tsbJsDictionaries_List")
	public void tsbJsDictionariesList(){
		if(tsbJsDictionaries!=null){
			this.writeJson(tsbJsDictionariesService.dataGrid(this.getPage(), this.getRows(),tsbJsDictionaries));
		}else{
			this.writeJson(tsbJsDictionariesService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/tsbJsDictionaries_List_All")
	public void tsbJsDictionariesListAll(){
		this.writeJson(tsbJsDictionariesService.listAll());
	}
	
	@Action("/sys/tsbJsDictionaries_ComboBox")
	public void tsbJsDictionariesComboBox(){
		List<TsbJsDictionaries> l = tsbJsDictionariesService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			TsbJsDictionaries obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getEventName().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/tsbJsDictionaries_Delete")
	public void tsbJsDictionariesDelete(){
		if(this.getIds().trim()=="")return;
		tsbJsDictionariesService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/tsbJsDictionaries_Update")
	public void tsbJsDictionariesUpdate(){
		if(tsbJsDictionaries!=null) tsbJsDictionariesService.saveOrUpdate(tsbJsDictionaries);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/tsbJsDictionaries_Excel")
	public void tsbJsDictionariesExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = tsbJsDictionariesService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = tsbJsDictionariesService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/tsbJsDictionaries_FormFile")
	public void tsbJsDictionariesFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/tsbJsDictionaries_Upload")
	public void tsbJsDictionariesUpload() throws IOException{
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
				tsbJsDictionaries=new TsbJsDictionaries();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(tsbJsDictionaries,ps[ej],t.getCell(ei,ej));
					 }
					 tsbJsDictionaries.setId(null);
					 tsbJsDictionariesService.saveOrUpdate(tsbJsDictionaries);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(TsbJsDictionaries tsbJsDictionaries,String p,String v){
		Field field = null;
		try {
			field = tsbJsDictionaries.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(tsbJsDictionaries,Float.valueOf(v).intValue());
			}else{
				field.set(tsbJsDictionaries,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/tsbJsDictionaries_Get_ById")  
	public void tsbJsDictionariesGetById(){
		TsbJsDictionaries tsbJsDictionaries=new TsbJsDictionaries();
		tsbJsDictionaries.setId(Integer.parseInt(this.getId()));
		tsbJsDictionaries=tsbJsDictionariesService.get(tsbJsDictionaries).get(0);
		if(tsbJsDictionaries==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(tsbJsDictionaries);
		}
	}
	
	@Action("/sys/tsbJsDictionaries_Get_ByObj")  
	public void tsbJsDictionariesByObj(){
		this.writeJson(tsbJsDictionariesService.get(tsbJsDictionaries));
	}
	
	//检查字段是否唯一
	private String isSingle(TsbJsDictionaries tsbJsDictionaries,String fieldName,String fieldValue){
		String result=null;
		List<TsbJsDictionaries> lsList = tsbJsDictionariesService.get(tsbJsDictionaries);
		if(tsbJsDictionariesService.get(tsbJsDictionaries).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
