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
public class TsbPrimitiveAction extends BaseAction{
	@Autowired
	private TsbPrimitiveService tsbPrimitiveService;
	private TsbPrimitive tsbPrimitive;
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
	
	public TsbPrimitive getTsbPrimitive(){
		return tsbPrimitive;
	}
	
	public void setTsbPrimitive(TsbPrimitive tsbPrimitive){
		this.tsbPrimitive=tsbPrimitive;
	}
	
	@Action("/sys/tsbPrimitive_Add")
	public void tsbPrimitiveAdd(){
		tsbPrimitiveService.saveOrUpdate(tsbPrimitive);
		if(tsbPrimitive.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", tsbPrimitive.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/tsbPrimitive_Add_HasFiles")
	public void tsbPrimitiveAddHasFiles(){
		tsbPrimitiveService.saveOrUpdate(tsbPrimitive);
		Map m=new HashMap();
		if(tsbPrimitive.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", tsbPrimitive.getId());
			m.put("fileid", "tsbPrimitive-"+tsbPrimitive.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/tsbPrimitive_Delete_HasFiles")
	public void tsbPrimitiveDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		tsbPrimitiveService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "tsbPrimitive-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/tsbPrimitive_List")
	public void tsbPrimitiveList(){
		if(tsbPrimitive!=null){
			this.writeJson(tsbPrimitiveService.dataGrid(this.getPage(), this.getRows(),tsbPrimitive));
		}else{
			this.writeJson(tsbPrimitiveService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/tsbPrimitive_List_All")
	public void tsbPrimitiveListAll(){
		this.writeJson(tsbPrimitiveService.listAll());
	}
	
	@Action("/sys/tsbPrimitive_ComboBox")
	public void tsbPrimitiveComboBox(){
		List<TsbPrimitive> l = tsbPrimitiveService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			TsbPrimitive obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/tsbPrimitive_Delete")
	public void tsbPrimitiveDelete(){
		if(this.getIds().trim()=="")return;
		tsbPrimitiveService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/tsbPrimitive_Update")
	public void tsbPrimitiveUpdate(){
		if(tsbPrimitive!=null) tsbPrimitiveService.saveOrUpdate(tsbPrimitive);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/tsbPrimitive_Excel")
	public void tsbPrimitiveExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = tsbPrimitiveService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = tsbPrimitiveService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/tsbPrimitive_FormFile")
	public void tsbPrimitiveFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/tsbPrimitive_Upload")
	public void tsbPrimitiveUpload() throws IOException{
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
				tsbPrimitive=new TsbPrimitive();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(tsbPrimitive,ps[ej],t.getCell(ei,ej));
					 }
					 tsbPrimitive.setId(null);
					 tsbPrimitiveService.saveOrUpdate(tsbPrimitive);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(TsbPrimitive tsbPrimitive,String p,String v){
		Field field = null;
		try {
			field = tsbPrimitive.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(tsbPrimitive,Float.valueOf(v).intValue());
			}else{
				field.set(tsbPrimitive,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/tsbPrimitive_Get_ById")  
	public void tsbPrimitiveGetById(){
		TsbPrimitive tsbPrimitive=new TsbPrimitive();
		tsbPrimitive.setId(Integer.parseInt(this.getId()));
		tsbPrimitive=tsbPrimitiveService.get(tsbPrimitive).get(0);
		if(tsbPrimitive==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(tsbPrimitive);
		}
	}
	
	@Action("/sys/tsbPrimitive_Get_ByObj")  
	public void tsbPrimitiveByObj(){
		this.writeJson(tsbPrimitiveService.get(tsbPrimitive));
	}
	
	//检查字段是否唯一
	private String isSingle(TsbPrimitive tsbPrimitive,String fieldName,String fieldValue){
		String result=null;
		List<TsbPrimitive> lsList = tsbPrimitiveService.get(tsbPrimitive);
		if(tsbPrimitiveService.get(tsbPrimitive).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
