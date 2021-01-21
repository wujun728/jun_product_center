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
import com.doroodo.sys.service.SySbJsDictionariesService;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SySbJsDictionariesAction extends BaseAction{
	@Autowired
	private SySbJsDictionariesService sySbJsDictionariesService;
	private SySbJsDictionaries sySbJsDictionaries;
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
	
	public SySbJsDictionaries getSySbJsDictionaries(){
		return sySbJsDictionaries;
	}
	
	public void setSySbJsDictionaries(SySbJsDictionaries sySbJsDictionaries){
		this.sySbJsDictionaries=sySbJsDictionaries;
	}
	
	@Action("/sys/sySbJsDictionaries_Add")
	public void sySbJsDictionariesAdd(){
		sySbJsDictionariesService.saveOrUpdate(sySbJsDictionaries);
		if(sySbJsDictionaries.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbJsDictionaries.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/sySbJsDictionaries_Add_HasFiles")
	public void sySbJsDictionariesAddHasFiles(){
		sySbJsDictionariesService.saveOrUpdate(sySbJsDictionaries);
		Map m=new HashMap();
		if(sySbJsDictionaries.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbJsDictionaries.getId());
			m.put("fileid", "sySbJsDictionaries-"+sySbJsDictionaries.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/sySbJsDictionaries_Delete_HasFiles")
	public void sySbJsDictionariesDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		sySbJsDictionariesService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "sySbJsDictionaries-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/sySbJsDictionaries_List")
	public void sySbJsDictionariesList(){
		if(sySbJsDictionaries!=null){
			this.writeJson(sySbJsDictionariesService.dataGrid(this.getPage(), this.getRows(),sySbJsDictionaries));
		}else{
			this.writeJson(sySbJsDictionariesService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/sySbJsDictionaries_List_All")
	public void sySbJsDictionariesListAll(){
		this.writeJson(sySbJsDictionariesService.listAll());
	}
	
	@Action("/sys/sySbJsDictionaries_ComboBox")
	public void sySbJsDictionariesComboBox(){
		List<SySbJsDictionaries> l = sySbJsDictionariesService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SySbJsDictionaries obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getEventName());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/sySbJsDictionaries_Delete")
	public void sySbJsDictionariesDelete(){
		if(this.getIds().trim()=="")return;
		sySbJsDictionariesService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/sySbJsDictionaries_Update")
	public void sySbJsDictionariesUpdate(){
		if(sySbJsDictionaries!=null) sySbJsDictionariesService.saveOrUpdate(sySbJsDictionaries);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/sySbJsDictionaries_Excel")
	public void sySbJsDictionariesExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = sySbJsDictionariesService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = sySbJsDictionariesService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/sySbJsDictionaries_FormFile")
	public void sySbJsDictionariesFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/sySbJsDictionaries_Upload")
	public void sySbJsDictionariesUpload() throws IOException{
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
				sySbJsDictionaries=new SySbJsDictionaries();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(sySbJsDictionaries,ps[ej],t.getCell(ei,ej));
					 }
					 sySbJsDictionaries.setId(null);
					 sySbJsDictionariesService.saveOrUpdate(sySbJsDictionaries);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SySbJsDictionaries sySbJsDictionaries,String p,String v){
		Field field = null;
		try {
			field = sySbJsDictionaries.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(sySbJsDictionaries,Float.valueOf(v).intValue());
			}else{
				field.set(sySbJsDictionaries,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/sySbJsDictionaries_Get_ById")  
	public void sySbJsDictionariesGetById(){
		SySbJsDictionaries sySbJsDictionaries=new SySbJsDictionaries();
		sySbJsDictionaries.setId(Integer.parseInt(this.getId()));
		try{
		sySbJsDictionaries=sySbJsDictionariesService.get(sySbJsDictionaries).get(0);
		if(sySbJsDictionaries==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(sySbJsDictionaries);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/sySbJsDictionaries_Get_ByObj")  
	public void sySbJsDictionariesByObj(){
		this.writeJson(sySbJsDictionariesService.get(sySbJsDictionaries));
	}
	
	//检查字段是否唯一
	private String isSingle(SySbJsDictionaries sySbJsDictionaries,String fieldName,String fieldValue){
		String result=null;
		List<SySbJsDictionaries> lsList = sySbJsDictionariesService.get(sySbJsDictionaries);
		if(sySbJsDictionariesService.get(sySbJsDictionaries).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
