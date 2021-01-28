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
public class VcxSbCurrentMonitorAction extends BaseAction{
	@Autowired
	private VcxSbCurrentMonitorService vcxSbCurrentMonitorService;
	private VcxSbCurrentMonitor vcxSbCurrentMonitor;
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
	
	public VcxSbCurrentMonitor getVcxSbCurrentMonitor(){
		return vcxSbCurrentMonitor;
	}
	
	public void setVcxSbCurrentMonitor(VcxSbCurrentMonitor vcxSbCurrentMonitor){
		this.vcxSbCurrentMonitor=vcxSbCurrentMonitor;
	}
	
	@Action("/sys/vcxSbCurrentMonitor_Add")
	public void vcxSbCurrentMonitorAdd(){
		vcxSbCurrentMonitorService.saveOrUpdate(vcxSbCurrentMonitor);
		if(vcxSbCurrentMonitor.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbCurrentMonitor.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbCurrentMonitor_Add_HasFiles")
	public void vcxSbCurrentMonitorAddHasFiles(){
		vcxSbCurrentMonitorService.saveOrUpdate(vcxSbCurrentMonitor);
		Map m=new HashMap();
		if(vcxSbCurrentMonitor.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbCurrentMonitor.getId());
			m.put("fileid", "vcxSbCurrentMonitor-"+vcxSbCurrentMonitor.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbCurrentMonitor_Delete_HasFiles")
	public void vcxSbCurrentMonitorDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbCurrentMonitorService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbCurrentMonitor-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbCurrentMonitor_List")
	public void vcxSbCurrentMonitorList(){
		if(vcxSbCurrentMonitor!=null){
			this.writeJson(vcxSbCurrentMonitorService.dataGrid(this.getPage(), this.getRows(),vcxSbCurrentMonitor));
		}else{
			this.writeJson(vcxSbCurrentMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbCurrentMonitor_List_All")
	public void vcxSbCurrentMonitorListAll(){
		this.writeJson(vcxSbCurrentMonitorService.listAll());
	}
	
	@Action("/sys/vcxSbCurrentMonitor_ComboBox")
	public void vcxSbCurrentMonitorComboBox(){
		List<VcxSbCurrentMonitor> l = vcxSbCurrentMonitorService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbCurrentMonitor obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbCurrentMonitor_Delete")
	public void vcxSbCurrentMonitorDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbCurrentMonitorService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbCurrentMonitor_Update")
	public void vcxSbCurrentMonitorUpdate(){
		if(vcxSbCurrentMonitor!=null) vcxSbCurrentMonitorService.saveOrUpdate(vcxSbCurrentMonitor);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbCurrentMonitor_Excel")
	public void vcxSbCurrentMonitorExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbCurrentMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbCurrentMonitorService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbCurrentMonitor_FormFile")
	public void vcxSbCurrentMonitorFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbCurrentMonitor_Upload")
	public void vcxSbCurrentMonitorUpload() throws IOException{
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
				vcxSbCurrentMonitor=new VcxSbCurrentMonitor();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbCurrentMonitor,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbCurrentMonitor.setId(null);
					 vcxSbCurrentMonitorService.saveOrUpdate(vcxSbCurrentMonitor);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbCurrentMonitor vcxSbCurrentMonitor,String p,String v){
		Field field = null;
		try {
			field = vcxSbCurrentMonitor.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbCurrentMonitor,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbCurrentMonitor,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbCurrentMonitor_Get_ById")  
	public void vcxSbCurrentMonitorGetById(){
		VcxSbCurrentMonitor vcxSbCurrentMonitor=new VcxSbCurrentMonitor();
		vcxSbCurrentMonitor.setId(Integer.parseInt(this.getId()));
		try{
		vcxSbCurrentMonitor=vcxSbCurrentMonitorService.get(vcxSbCurrentMonitor).get(0);
		if(vcxSbCurrentMonitor==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbCurrentMonitor);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	@Action("/sys/vcxSbCurrentMonitor_Cmd_Collection")
	public void vcxSbCurrentMonitor_Cmd_Collection(){
		this.writeJson(vcxSbCurrentMonitorService.cmdCollection(this.getId()));
	}
	
	
	@Action("/sys/vcxSbCurrentMonitor_Get_ConnectionData")
	public void vcxSbCurrentMonitor_Get_ConnectionData(){
		try{
		this.writeJson(vcxSbCurrentMonitorService.getConnectionData(this.getId()).get(0));
		}catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/vcxSbCurrentMonitor_Get_ByObj")  
	public void vcxSbCurrentMonitorByObj(){
		this.writeJson(vcxSbCurrentMonitorService.get(vcxSbCurrentMonitor));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbCurrentMonitor vcxSbCurrentMonitor,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbCurrentMonitor> lsList = vcxSbCurrentMonitorService.get(vcxSbCurrentMonitor);
		if(vcxSbCurrentMonitorService.get(vcxSbCurrentMonitor).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
