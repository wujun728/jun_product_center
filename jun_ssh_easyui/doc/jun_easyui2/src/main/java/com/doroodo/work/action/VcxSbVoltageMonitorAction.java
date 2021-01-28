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
public class VcxSbVoltageMonitorAction extends BaseAction{
	@Autowired
	private VcxSbVoltageMonitorService vcxSbVoltageMonitorService;
	private VcxSbVoltageMonitor vcxSbVoltageMonitor;
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
	
	public VcxSbVoltageMonitor getVcxSbVoltageMonitor(){
		return vcxSbVoltageMonitor;
	}
	
	public void setVcxSbVoltageMonitor(VcxSbVoltageMonitor vcxSbVoltageMonitor){
		this.vcxSbVoltageMonitor=vcxSbVoltageMonitor;
	}
	
	@Action("/sys/vcxSbVoltageMonitor_Add")
	public void vcxSbVoltageMonitorAdd(){
		vcxSbVoltageMonitorService.saveOrUpdate(vcxSbVoltageMonitor);
		if(vcxSbVoltageMonitor.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbVoltageMonitor.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbVoltageMonitor_Add_HasFiles")
	public void vcxSbVoltageMonitorAddHasFiles(){
		vcxSbVoltageMonitorService.saveOrUpdate(vcxSbVoltageMonitor);
		Map m=new HashMap();
		if(vcxSbVoltageMonitor.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbVoltageMonitor.getId());
			m.put("fileid", "vcxSbVoltageMonitor-"+vcxSbVoltageMonitor.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbVoltageMonitor_Delete_HasFiles")
	public void vcxSbVoltageMonitorDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbVoltageMonitorService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbVoltageMonitor-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbVoltageMonitor_List")
	public void vcxSbVoltageMonitorList(){
		if(vcxSbVoltageMonitor!=null){
			this.writeJson(vcxSbVoltageMonitorService.dataGrid(this.getPage(), this.getRows(),vcxSbVoltageMonitor));
		}else{
			this.writeJson(vcxSbVoltageMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbVoltageMonitor_List_All")
	public void vcxSbVoltageMonitorListAll(){
		this.writeJson(vcxSbVoltageMonitorService.listAll());
	}
	
	@Action("/sys/vcxSbVoltageMonitor_ComboBox")
	public void vcxSbVoltageMonitorComboBox(){
		List<VcxSbVoltageMonitor> l = vcxSbVoltageMonitorService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbVoltageMonitor obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbVoltageMonitor_Delete")
	public void vcxSbVoltageMonitorDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbVoltageMonitorService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbVoltageMonitor_Update")
	public void vcxSbVoltageMonitorUpdate(){
		if(vcxSbVoltageMonitor!=null) vcxSbVoltageMonitorService.saveOrUpdate(vcxSbVoltageMonitor);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbVoltageMonitor_Excel")
	public void vcxSbVoltageMonitorExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbVoltageMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbVoltageMonitorService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbVoltageMonitor_FormFile")
	public void vcxSbVoltageMonitorFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbVoltageMonitor_Upload")
	public void vcxSbVoltageMonitorUpload() throws IOException{
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
				vcxSbVoltageMonitor=new VcxSbVoltageMonitor();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbVoltageMonitor,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbVoltageMonitor.setId(null);
					 vcxSbVoltageMonitorService.saveOrUpdate(vcxSbVoltageMonitor);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbVoltageMonitor vcxSbVoltageMonitor,String p,String v){
		Field field = null;
		try {
			field = vcxSbVoltageMonitor.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbVoltageMonitor,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbVoltageMonitor,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbVoltageMonitor_Get_ById")  
	public void vcxSbVoltageMonitorGetById(){
		VcxSbVoltageMonitor vcxSbVoltageMonitor=new VcxSbVoltageMonitor();
		vcxSbVoltageMonitor.setId(Integer.parseInt(this.getId()));
		try{
		vcxSbVoltageMonitor=vcxSbVoltageMonitorService.get(vcxSbVoltageMonitor).get(0);
		if(vcxSbVoltageMonitor==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbVoltageMonitor);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/vcxSbVoltageMonitor_Get_ByObj")  
	public void vcxSbVoltageMonitorByObj(){
		this.writeJson(vcxSbVoltageMonitorService.get(vcxSbVoltageMonitor));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbVoltageMonitor vcxSbVoltageMonitor,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbVoltageMonitor> lsList = vcxSbVoltageMonitorService.get(vcxSbVoltageMonitor);
		if(vcxSbVoltageMonitorService.get(vcxSbVoltageMonitor).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
