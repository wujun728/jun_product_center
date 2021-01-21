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
public class VcxSbAlarmStateAction extends BaseAction{
	@Autowired
	private VcxSbAlarmStateService vcxSbAlarmStateService;
	private VcxSbAlarmState vcxSbAlarmState;
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
	
	public VcxSbAlarmState getVcxSbAlarmState(){
		return vcxSbAlarmState;
	}
	
	public void setVcxSbAlarmState(VcxSbAlarmState vcxSbAlarmState){
		this.vcxSbAlarmState=vcxSbAlarmState;
	}
	
	@Action("/sys/vcxSbAlarmState_Add")
	public void vcxSbAlarmStateAdd(){
		vcxSbAlarmStateService.saveOrUpdate(vcxSbAlarmState);
		if(vcxSbAlarmState.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbAlarmState.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbAlarmState_Add_HasFiles")
	public void vcxSbAlarmStateAddHasFiles(){
		vcxSbAlarmStateService.saveOrUpdate(vcxSbAlarmState);
		Map m=new HashMap();
		if(vcxSbAlarmState.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbAlarmState.getId());
			m.put("fileid", "vcxSbAlarmState-"+vcxSbAlarmState.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbAlarmState_Delete_HasFiles")
	public void vcxSbAlarmStateDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbAlarmStateService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbAlarmState-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbAlarmState_List")
	public void vcxSbAlarmStateList(){
		if(vcxSbAlarmState!=null){
			this.writeJson(vcxSbAlarmStateService.dataGrid(this.getPage(), this.getRows(),vcxSbAlarmState));
		}else{
			this.writeJson(vcxSbAlarmStateService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbAlarmState_List_All")
	public void vcxSbAlarmStateListAll(){
		this.writeJson(vcxSbAlarmStateService.listAll());
	}
	
	@Action("/sys/vcxSbAlarmState_ComboBox")
	public void vcxSbAlarmStateComboBox(){
		List<VcxSbAlarmState> l = vcxSbAlarmStateService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbAlarmState obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbAlarmState_Delete")
	public void vcxSbAlarmStateDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbAlarmStateService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbAlarmState_Update")
	public void vcxSbAlarmStateUpdate(){
		if(vcxSbAlarmState!=null) vcxSbAlarmStateService.saveOrUpdate(vcxSbAlarmState);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbAlarmState_Excel")
	public void vcxSbAlarmStateExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbAlarmStateService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbAlarmStateService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbAlarmState_FormFile")
	public void vcxSbAlarmStateFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbAlarmState_Upload")
	public void vcxSbAlarmStateUpload() throws IOException{
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
				vcxSbAlarmState=new VcxSbAlarmState();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbAlarmState,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbAlarmState.setId(null);
					 vcxSbAlarmStateService.saveOrUpdate(vcxSbAlarmState);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbAlarmState vcxSbAlarmState,String p,String v){
		Field field = null;
		try {
			field = vcxSbAlarmState.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbAlarmState,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbAlarmState,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbAlarmState_Get_ById")  
	public void vcxSbAlarmStateGetById(){
		VcxSbAlarmState vcxSbAlarmState=new VcxSbAlarmState();
		vcxSbAlarmState.setId(Integer.parseInt(this.getId()));
		try{
		vcxSbAlarmState=vcxSbAlarmStateService.get(vcxSbAlarmState).get(0);
		if(vcxSbAlarmState==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbAlarmState);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/vcxSbAlarmState_Get_ByObj")  
	public void vcxSbAlarmStateByObj(){
		this.writeJson(vcxSbAlarmStateService.get(vcxSbAlarmState));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbAlarmState vcxSbAlarmState,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbAlarmState> lsList = vcxSbAlarmStateService.get(vcxSbAlarmState);
		if(vcxSbAlarmStateService.get(vcxSbAlarmState).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
