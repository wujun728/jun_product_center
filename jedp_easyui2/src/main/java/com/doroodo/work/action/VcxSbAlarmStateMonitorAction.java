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
public class VcxSbAlarmStateMonitorAction extends BaseAction{
	@Autowired
	private VcxSbAlarmStateMonitorService vcxSbAlarmStateMonitorService;
	private VcxSbAlarmStateMonitor vcxSbAlarmStateMonitor;
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
	
	public VcxSbAlarmStateMonitor getVcxSbAlarmStateMonitor(){
		return vcxSbAlarmStateMonitor;
	}
	
	public void setVcxSbAlarmStateMonitor(VcxSbAlarmStateMonitor vcxSbAlarmStateMonitor){
		this.vcxSbAlarmStateMonitor=vcxSbAlarmStateMonitor;
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_Add")
	public void vcxSbAlarmStateMonitorAdd(){
		vcxSbAlarmStateMonitorService.saveOrUpdate(vcxSbAlarmStateMonitor);
		if(vcxSbAlarmStateMonitor.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbAlarmStateMonitor.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_Add_HasFiles")
	public void vcxSbAlarmStateMonitorAddHasFiles(){
		vcxSbAlarmStateMonitorService.saveOrUpdate(vcxSbAlarmStateMonitor);
		Map m=new HashMap();
		if(vcxSbAlarmStateMonitor.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbAlarmStateMonitor.getId());
			m.put("fileid", "vcxSbAlarmStateMonitor-"+vcxSbAlarmStateMonitor.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_Delete_HasFiles")
	public void vcxSbAlarmStateMonitorDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbAlarmStateMonitorService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbAlarmStateMonitor-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_List")
	public void vcxSbAlarmStateMonitorList(){
		if(vcxSbAlarmStateMonitor!=null){
			this.writeJson(vcxSbAlarmStateMonitorService.dataGrid(this.getPage(), this.getRows(),vcxSbAlarmStateMonitor));
		}else{
			this.writeJson(vcxSbAlarmStateMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_List_All")
	public void vcxSbAlarmStateMonitorListAll(){
		this.writeJson(vcxSbAlarmStateMonitorService.listAll());
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_ComboBox")
	public void vcxSbAlarmStateMonitorComboBox(){
		List<VcxSbAlarmStateMonitor> l = vcxSbAlarmStateMonitorService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbAlarmStateMonitor obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_Delete")
	public void vcxSbAlarmStateMonitorDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbAlarmStateMonitorService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_Update")
	public void vcxSbAlarmStateMonitorUpdate(){
		if(vcxSbAlarmStateMonitor!=null) vcxSbAlarmStateMonitorService.saveOrUpdate(vcxSbAlarmStateMonitor);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_Excel")
	public void vcxSbAlarmStateMonitorExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbAlarmStateMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbAlarmStateMonitorService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_FormFile")
	public void vcxSbAlarmStateMonitorFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_Upload")
	public void vcxSbAlarmStateMonitorUpload() throws IOException{
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
				vcxSbAlarmStateMonitor=new VcxSbAlarmStateMonitor();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbAlarmStateMonitor,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbAlarmStateMonitor.setId(null);
					 vcxSbAlarmStateMonitorService.saveOrUpdate(vcxSbAlarmStateMonitor);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbAlarmStateMonitor vcxSbAlarmStateMonitor,String p,String v){
		Field field = null;
		try {
			field = vcxSbAlarmStateMonitor.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbAlarmStateMonitor,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbAlarmStateMonitor,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_Get_ById")  
	public void vcxSbAlarmStateMonitorGetById(){
		VcxSbAlarmStateMonitor vcxSbAlarmStateMonitor=new VcxSbAlarmStateMonitor();
		vcxSbAlarmStateMonitor.setId(Integer.parseInt(this.getId()));
		try{
		vcxSbAlarmStateMonitor=vcxSbAlarmStateMonitorService.get(vcxSbAlarmStateMonitor).get(0);
		if(vcxSbAlarmStateMonitor==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbAlarmStateMonitor);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/vcxSbAlarmStateMonitor_Get_ByObj")  
	public void vcxSbAlarmStateMonitorByObj(){
		this.writeJson(vcxSbAlarmStateMonitorService.get(vcxSbAlarmStateMonitor));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbAlarmStateMonitor vcxSbAlarmStateMonitor,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbAlarmStateMonitor> lsList = vcxSbAlarmStateMonitorService.get(vcxSbAlarmStateMonitor);
		if(vcxSbAlarmStateMonitorService.get(vcxSbAlarmStateMonitor).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
