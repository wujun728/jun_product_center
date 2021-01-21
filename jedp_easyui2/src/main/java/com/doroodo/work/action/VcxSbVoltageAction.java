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
public class VcxSbVoltageAction extends BaseAction{
	@Autowired
	private VcxSbVoltageService vcxSbVoltageService;
	private VcxSbVoltage vcxSbVoltage;
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
	
	public VcxSbVoltage getVcxSbVoltage(){
		return vcxSbVoltage;
	}
	
	public void setVcxSbVoltage(VcxSbVoltage vcxSbVoltage){
		this.vcxSbVoltage=vcxSbVoltage;
	}
	
	@Action("/sys/vcxSbVoltage_Add")
	public void vcxSbVoltageAdd(){
		vcxSbVoltageService.saveOrUpdate(vcxSbVoltage);
		if(vcxSbVoltage.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbVoltage.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbVoltage_Add_HasFiles")
	public void vcxSbVoltageAddHasFiles(){
		vcxSbVoltageService.saveOrUpdate(vcxSbVoltage);
		Map m=new HashMap();
		if(vcxSbVoltage.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbVoltage.getId());
			m.put("fileid", "vcxSbVoltage-"+vcxSbVoltage.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbVoltage_Delete_HasFiles")
	public void vcxSbVoltageDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbVoltageService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbVoltage-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbVoltage_List")
	public void vcxSbVoltageList(){
		if(vcxSbVoltage!=null){
			this.writeJson(vcxSbVoltageService.dataGrid(this.getPage(), this.getRows(),vcxSbVoltage));
		}else{
			this.writeJson(vcxSbVoltageService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbVoltage_List_All")
	public void vcxSbVoltageListAll(){
		this.writeJson(vcxSbVoltageService.listAll());
	}
	
	@Action("/sys/vcxSbVoltage_ComboBox")
	public void vcxSbVoltageComboBox(){
		List<VcxSbVoltage> l = vcxSbVoltageService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbVoltage obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbVoltage_Delete")
	public void vcxSbVoltageDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbVoltageService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbVoltage_Update")
	public void vcxSbVoltageUpdate(){
		if(vcxSbVoltage!=null) vcxSbVoltageService.saveOrUpdate(vcxSbVoltage);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbVoltage_Excel")
	public void vcxSbVoltageExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbVoltageService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbVoltageService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbVoltage_FormFile")
	public void vcxSbVoltageFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbVoltage_Upload")
	public void vcxSbVoltageUpload() throws IOException{
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
				vcxSbVoltage=new VcxSbVoltage();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbVoltage,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbVoltage.setId(null);
					 vcxSbVoltageService.saveOrUpdate(vcxSbVoltage);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbVoltage vcxSbVoltage,String p,String v){
		Field field = null;
		try {
			field = vcxSbVoltage.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbVoltage,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbVoltage,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbVoltage_Get_ById")  
	public void vcxSbVoltageGetById(){
		VcxSbVoltage vcxSbVoltage=new VcxSbVoltage();
		vcxSbVoltage.setId(Integer.parseInt(this.getId()));
		try{
		vcxSbVoltage=vcxSbVoltageService.get(vcxSbVoltage).get(0);
		if(vcxSbVoltage==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbVoltage);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/vcxSbVoltage_Get_ByObj")  
	public void vcxSbVoltageByObj(){
		this.writeJson(vcxSbVoltageService.get(vcxSbVoltage));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbVoltage vcxSbVoltage,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbVoltage> lsList = vcxSbVoltageService.get(vcxSbVoltage);
		if(vcxSbVoltageService.get(vcxSbVoltage).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
