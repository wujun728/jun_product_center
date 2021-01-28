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

import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;

import com.doroodo.base.action.BaseAction;
import com.doroodo.config.SysVal;
import com.doroodo.base.model.*;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SyDebugAction extends BaseAction{
	@Autowired
	private SyDebugService syDebugService;
	private SyDebug syDebug;
	private String EXCEL_TITLE = "bug管理";//请输入导出的excel表表名
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
	
	public SyDebug getSyDebug(){
		return syDebug;
	}
	
	public void setSyDebug(SyDebug syDebug){
		this.syDebug=syDebug;
	}
	
	@Action("/sys/syDebug_Add")
	public void syDebugAdd(){
		syDebugService.saveOrUpdate(syDebug);
		if(syDebug.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDebug.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/syDebug_Add_HasFiles")
	public void syDebugAddHasFiles(){
		syDebugService.saveOrUpdate(syDebug);
		Map m=new HashMap();
		if(syDebug.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDebug.getId());
			m.put("fileid", "syDebug-"+syDebug.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/syDebug_Delete_HasFiles")
	public void syDebugDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		syDebugService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syDebug-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syDebug_List")
	public void syDebugList(){
		this.writeJson(syDebugService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syDebug_List_All")
	public void syDebugListAll(){
		this.writeJson(syDebugService.listAll());
	}
	
	@Action("/sys/syDebug_ComboBox")
	public void syDebugComboBox(){
		List<SyDebug> l = syDebugService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SyDebug obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/syDebug_Delete")
	public void syDebugDelete(){
		if(this.getIds().trim()=="")return;
		syDebugService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syDebug_Update")
	public void syDebugUpdate(){
		if(syDebug!=null) syDebugService.saveOrUpdate(syDebug);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syDebug_Excel")
	public void syDebugExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = syDebugService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = syDebugService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/syDebug_FormFile")
	public void syDebugFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/syDebug_Upload")
	public void syDebugUpload() throws IOException{
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
				syDebug=new SyDebug();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syDebug,ps[ej],t.getCell(ei,ej));
					 }
					 syDebug.setId(null);
					 syDebugService.saveOrUpdate(syDebug);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyDebug syDebug,String p,String v){
		Field field = null;
		try {
			field = syDebug.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syDebug,Float.valueOf(v).intValue());
			}else{
				field.set(syDebug,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syDebug_Get_ById")  
	public void syDebugGetById(){
		SyDebug syDebug=new SyDebug();
		syDebug.setId(Integer.parseInt(this.getId()));
		syDebug=syDebugService.get(syDebug).get(0);
		if(syDebug==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syDebug);
		}
	}
	
	@Action("/sys/syDebug_Get_ByObj")  
	public void syDebugByObj(){
		this.writeJson(syDebugService.get(syDebug));
	}
	
	//检查字段是否唯一
	private String isSingle(SyDebug syDebug,String fieldName,String fieldValue){
		String result=null;
		List<SyDebug> lsList = syDebugService.get(syDebug);
		if(syDebugService.get(syDebug).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
