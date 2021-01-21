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
public class CxSbReadStatusAction extends BaseAction{
	@Autowired
	private CxSbReadStatusService cxSbReadStatusService;
	private CxSbReadStatus cxSbReadStatus;
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
	
	public CxSbReadStatus getCxSbReadStatus(){
		return cxSbReadStatus;
	}
	
	public void setCxSbReadStatus(CxSbReadStatus cxSbReadStatus){
		this.cxSbReadStatus=cxSbReadStatus;
	}
	
	@Action("/sys/cxSbReadStatus_Add")
	public void cxSbReadStatusAdd(){
		cxSbReadStatusService.saveOrUpdate(cxSbReadStatus);
		if(cxSbReadStatus.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbReadStatus.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/cxSbReadStatus_Add_HasFiles")
	public void cxSbReadStatusAddHasFiles(){
		cxSbReadStatusService.saveOrUpdate(cxSbReadStatus);
		Map m=new HashMap();
		if(cxSbReadStatus.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbReadStatus.getId());
			m.put("fileid", "cxSbReadStatus-"+cxSbReadStatus.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/cxSbReadStatus_Delete_HasFiles")
	public void cxSbReadStatusDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		cxSbReadStatusService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "cxSbReadStatus-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/cxSbReadStatus_List")
	public void cxSbReadStatusList(){
		if(cxSbReadStatus!=null){
			this.writeJson(cxSbReadStatusService.dataGrid(this.getPage(), this.getRows(),cxSbReadStatus));
		}else{
			this.writeJson(cxSbReadStatusService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/cxSbReadStatus_List_All")
	public void cxSbReadStatusListAll(){
		this.writeJson(cxSbReadStatusService.listAll());
	}
	
	@Action("/sys/cxSbReadStatus_ComboBox")
	public void cxSbReadStatusComboBox(){
		List<CxSbReadStatus> l = cxSbReadStatusService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			CxSbReadStatus obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/cxSbReadStatus_Delete")
	public void cxSbReadStatusDelete(){
		if(this.getIds().trim()=="")return;
		cxSbReadStatusService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/cxSbReadStatus_Update")
	public void cxSbReadStatusUpdate(){
		if(cxSbReadStatus!=null) cxSbReadStatusService.saveOrUpdate(cxSbReadStatus);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/cxSbReadStatus_Excel")
	public void cxSbReadStatusExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = cxSbReadStatusService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = cxSbReadStatusService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/cxSbReadStatus_FormFile")
	public void cxSbReadStatusFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/cxSbReadStatus_Upload")
	public void cxSbReadStatusUpload() throws IOException{
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
				cxSbReadStatus=new CxSbReadStatus();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(cxSbReadStatus,ps[ej],t.getCell(ei,ej));
					 }
					 cxSbReadStatus.setId(null);
					 cxSbReadStatusService.saveOrUpdate(cxSbReadStatus);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(CxSbReadStatus cxSbReadStatus,String p,String v){
		Field field = null;
		try {
			field = cxSbReadStatus.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(cxSbReadStatus,Float.valueOf(v).intValue());
			}else{
				field.set(cxSbReadStatus,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/cxSbReadStatus_Get_ById")  
	public void cxSbReadStatusGetById(){
		CxSbReadStatus cxSbReadStatus=new CxSbReadStatus();
		cxSbReadStatus.setId(Integer.parseInt(this.getId()));
		cxSbReadStatus=cxSbReadStatusService.get(cxSbReadStatus).get(0);
		if(cxSbReadStatus==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbReadStatus);
		}
	}
	
	@Action("/sys/cxSbReadStatus_Get_ByTid")  
	public void cxSbReadStatusGetByTid(){
		CxSbReadStatus cxSbReadStatus=new CxSbReadStatus();
		cxSbReadStatus.setTerminalId(this.getId());		
		this.writeJson(cxSbReadStatusService.get(cxSbReadStatus));
	}
	
	@Action("/sys/cxSbReadStatus_Get_ByObj")  
	public void cxSbReadStatusByObj(){
		this.writeJson(cxSbReadStatusService.get(cxSbReadStatus));
	}
	
	//检查字段是否唯一
	private String isSingle(CxSbReadStatus cxSbReadStatus,String fieldName,String fieldValue){
		String result=null;
		List<CxSbReadStatus> lsList = cxSbReadStatusService.get(cxSbReadStatus);
		if(cxSbReadStatusService.get(cxSbReadStatus).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
