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
public class CxSbSysdomianAction extends BaseAction{
	@Autowired
	private CxSbSysdomianService cxSbSysdomianService;
	private CxSbSysdomian cxSbSysdomian;
	private String EXCEL_TITLE = "";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	private String table_name="";
	private String field_name="";
	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
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
	
	public CxSbSysdomian getCxSbSysdomian(){
		return cxSbSysdomian;
	}
	
	public void setCxSbSysdomian(CxSbSysdomian cxSbSysdomian){
		this.cxSbSysdomian=cxSbSysdomian;
	}
	
	@Action("/sys/cxSbSysdomian_Add")
	public void cxSbSysdomianAdd(){
		cxSbSysdomianService.saveOrUpdate(cxSbSysdomian);
		if(cxSbSysdomian.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbSysdomian.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/cxSbSysdomian_Add_HasFiles")
	public void cxSbSysdomianAddHasFiles(){
		cxSbSysdomianService.saveOrUpdate(cxSbSysdomian);
		Map m=new HashMap();
		if(cxSbSysdomian.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbSysdomian.getId());
			m.put("fileid", "cxSbSysdomian-"+cxSbSysdomian.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/cxSbSysdomian_Delete_HasFiles")
	public void cxSbSysdomianDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		cxSbSysdomianService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "cxSbSysdomian-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/cxSbSysdomian_List")
	public void cxSbSysdomianList(){
		if(cxSbSysdomian!=null){
			this.writeJson(cxSbSysdomianService.dataGrid(this.getPage(), this.getRows(),cxSbSysdomian));
		}else{
			this.writeJson(cxSbSysdomianService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/cxSbSysdomian_List_All")
	public void cxSbSysdomianListAll(){
		this.writeJson(cxSbSysdomianService.listAll());
	}
	
	@Action("/sys/cxSbSysdomian_ComboBox")
	public void cxSbSysdomianComboBox(){
		CxSbSysdomian cxSbSysdomian=new CxSbSysdomian();
		cxSbSysdomian.setTableName(table_name);
		cxSbSysdomian.setFieldName(field_name);
		List<CxSbSysdomian> l = cxSbSysdomianService.get(cxSbSysdomian);
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			CxSbSysdomian obj=l.get(i);
			cb.setId(obj.getFieldValue());
			cb.setText(obj.getFieldDesc());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/cxSbSysdomian_Delete")
	public void cxSbSysdomianDelete(){
		if(this.getIds().trim()=="")return;
		cxSbSysdomianService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/cxSbSysdomian_Update")
	public void cxSbSysdomianUpdate(){
		if(cxSbSysdomian!=null) cxSbSysdomianService.saveOrUpdate(cxSbSysdomian);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/cxSbSysdomian_Excel")
	public void cxSbSysdomianExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = cxSbSysdomianService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = cxSbSysdomianService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/cxSbSysdomian_FormFile")
	public void cxSbSysdomianFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/cxSbSysdomian_Upload")
	public void cxSbSysdomianUpload() throws IOException{
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
				cxSbSysdomian=new CxSbSysdomian();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(cxSbSysdomian,ps[ej],t.getCell(ei,ej));
					 }
					 cxSbSysdomian.setId(null);
					 cxSbSysdomianService.saveOrUpdate(cxSbSysdomian);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(CxSbSysdomian cxSbSysdomian,String p,String v){
		Field field = null;
		try {
			field = cxSbSysdomian.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(cxSbSysdomian,Float.valueOf(v).intValue());
			}else{
				field.set(cxSbSysdomian,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/cxSbSysdomian_Get_ById")  
	public void cxSbSysdomianGetById(){
		CxSbSysdomian cxSbSysdomian=new CxSbSysdomian();
		cxSbSysdomian.setId(Integer.parseInt(this.getId()));
		cxSbSysdomian=cxSbSysdomianService.get(cxSbSysdomian).get(0);
		if(cxSbSysdomian==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbSysdomian);
		}
	}
	
	@Action("/sys/cxSbSysdomian_Get_ByObj")  
	public void cxSbSysdomianByObj(){
		this.writeJson(cxSbSysdomianService.get(cxSbSysdomian));
	}
	
	//检查字段是否唯一
	private String isSingle(CxSbSysdomian cxSbSysdomian,String fieldName,String fieldValue){
		String result=null;
		List<CxSbSysdomian> lsList = cxSbSysdomianService.get(cxSbSysdomian);
		if(cxSbSysdomianService.get(cxSbSysdomian).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

}
