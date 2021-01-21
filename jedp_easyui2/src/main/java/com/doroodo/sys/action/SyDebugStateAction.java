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
public class SyDebugStateAction extends BaseAction{
	@Autowired
	private SyDebugStateService syDebugStateService;
	private SyDebugState syDebugState;
	private String EXCEL_TITLE = "bugg状态";//请输入导出的excel表表名
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
	
	public SyDebugState getSyDebugState(){
		return syDebugState;
	}
	
	public void setSyDebugState(SyDebugState syDebugState){
		this.syDebugState=syDebugState;
	}
	
	@Action("/sys/syDebugState_Add")
	public void syDebugStateAdd(){
		syDebugStateService.saveOrUpdate(syDebugState);
		if(syDebugState.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDebugState.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/syDebugState_Add_HasFiles")
	public void syDebugStateAddHasFiles(){
		syDebugStateService.saveOrUpdate(syDebugState);
		Map m=new HashMap();
		if(syDebugState.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDebugState.getId());
			m.put("fileid", "syDebugState-"+syDebugState.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/syDebugState_Delete_HasFiles")
	public void syDebugStateDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		syDebugStateService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syDebugState-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syDebugState_List")
	public void syDebugStateList(){
		this.writeJson(syDebugStateService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syDebugState_List_All")
	public void syDebugStateListAll(){
		this.writeJson(syDebugStateService.listAll());
	}
	
	@Action("/sys/syDebugState_ComboBox")
	public void syDebugStateComboBox(){
		List<SyDebugState> l = syDebugStateService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SyDebugState obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getStateName().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/syDebugState_Delete")
	public void syDebugStateDelete(){
		if(this.getIds().trim()=="")return;
		syDebugStateService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syDebugState_Update")
	public void syDebugStateUpdate(){
		if(syDebugState!=null) syDebugStateService.saveOrUpdate(syDebugState);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syDebugState_Excel")
	public void syDebugStateExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = syDebugStateService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = syDebugStateService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/syDebugState_FormFile")
	public void syDebugStateFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/syDebugState_Upload")
	public void syDebugStateUpload() throws IOException{
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
				syDebugState=new SyDebugState();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syDebugState,ps[ej],t.getCell(ei,ej));
					 }
					 syDebugState.setId(null);
					 syDebugStateService.saveOrUpdate(syDebugState);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyDebugState syDebugState,String p,String v){
		Field field = null;
		try {
			field = syDebugState.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syDebugState,Float.valueOf(v).intValue());
			}else{
				field.set(syDebugState,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syDebugState_Get_ById")  
	public void syDebugStateGetById(){
		SyDebugState syDebugState=new SyDebugState();
		syDebugState.setId(Integer.parseInt(this.getId()));
		syDebugState=syDebugStateService.get(syDebugState).get(0);
		if(syDebugState==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syDebugState);
		}
	}
	
	@Action("/sys/syDebugState_Get_ByObj")  
	public void syDebugStateByObj(){
		this.writeJson(syDebugStateService.get(syDebugState));
	}
	
	//检查字段是否唯一
	private String isSingle(SyDebugState syDebugState,String fieldName,String fieldValue){
		String result=null;
		List<SyDebugState> lsList = syDebugStateService.get(syDebugState);
		if(syDebugStateService.get(syDebugState).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
