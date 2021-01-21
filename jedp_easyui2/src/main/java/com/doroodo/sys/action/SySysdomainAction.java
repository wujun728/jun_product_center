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

import com.doroodo.work.model.*;
import com.doroodo.work.service.*;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SySysdomainAction extends BaseAction{
	@Autowired
	private SySysdomainService sySysdomainService;
	private SySysdomain sySysdomain;
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
	
	public SySysdomain getSySysdomain(){
		return sySysdomain;
	}
	
	public void setSySysdomain(SySysdomain sySysdomain){
		this.sySysdomain=sySysdomain;
	}
	
	@Action("/sys/sySysdomain_Add")
	public void sySysdomainAdd(){
		sySysdomainService.saveOrUpdate(sySysdomain);
		if(sySysdomain.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySysdomain.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/sySysdomain_Add_HasFiles")
	public void sySysdomainAddHasFiles(){
		sySysdomainService.saveOrUpdate(sySysdomain);
		Map m=new HashMap();
		if(sySysdomain.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySysdomain.getId());
			m.put("fileid", "sySysdomain-"+sySysdomain.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/sySysdomain_Delete_HasFiles")
	public void sySysdomainDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		sySysdomainService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "sySysdomain-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/sySysdomain_List")
	public void sySysdomainList(){
		if(sySysdomain!=null){
			this.writeJson(sySysdomainService.dataGrid(this.getPage(), this.getRows(),sySysdomain));
		}else{
			this.writeJson(sySysdomainService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/sySysdomain_List_All")
	public void sySysdomainListAll(){
		this.writeJson(sySysdomainService.listAll());
	}
	
	@Action("/sys/sySysdomain_ComboBox")
	public void sySysdomainComboBox(){
		SySysdomain sySysdomain=new SySysdomain();
		sySysdomain.setTableName(table_name);
		sySysdomain.setFieldName(field_name);
		List<SySysdomain> l = sySysdomainService.get(sySysdomain);
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SySysdomain obj=l.get(i);
			cb.setId(obj.getFieldValue());
			cb.setText(obj.getFieldDesc());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/sySysdomain_Delete")
	public void sySysdomainDelete(){
		if(this.getIds().trim()=="")return;
		sySysdomainService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/sySysdomain_Update")
	public void sySysdomainUpdate(){
		if(sySysdomain!=null) sySysdomainService.saveOrUpdate(sySysdomain);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/sySysdomain_Excel")
	public void sySysdomainExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = sySysdomainService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = sySysdomainService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/sySysdomain_FormFile")
	public void sySysdomainFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/sySysdomain_Upload")
	public void sySysdomainUpload() throws IOException{
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
				sySysdomain=new SySysdomain();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(sySysdomain,ps[ej],t.getCell(ei,ej));
					 }
					 sySysdomain.setId(null);
					 sySysdomainService.saveOrUpdate(sySysdomain);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SySysdomain sySysdomain,String p,String v){
		Field field = null;
		try {
			field = sySysdomain.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(sySysdomain,Float.valueOf(v).intValue());
			}else{
				field.set(sySysdomain,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/sySysdomain_Get_ById")  
	public void sySysdomainGetById(){
		SySysdomain sySysdomain=new SySysdomain();
		sySysdomain.setId(Integer.parseInt(this.getId()));
		sySysdomain=sySysdomainService.get(sySysdomain).get(0);
		if(sySysdomain==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(sySysdomain);
		}
	}
	
	@Action("/sys/sySysdomain_Get_ByObj")  
	public void sySysdomainByObj(){
		this.writeJson(sySysdomainService.get(sySysdomain));
	}
	
	//检查字段是否唯一
	private String isSingle(SySysdomain sySysdomain,String fieldName,String fieldValue){
		String result=null;
		List<SySysdomain> lsList = sySysdomainService.get(sySysdomain);
		if(sySysdomainService.get(sySysdomain).size()>0) {
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
