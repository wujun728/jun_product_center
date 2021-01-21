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
public class VcxSbReadStatusMonitorAction extends BaseAction{
	@Autowired
	private VcxSbReadStatusMonitorService vcxSbReadStatusMonitorService;
	private VcxSbReadStatusMonitor vcxSbReadStatusMonitor;
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
	
	public VcxSbReadStatusMonitor getVcxSbReadStatusMonitor(){
		return vcxSbReadStatusMonitor;
	}
	
	public void setVcxSbReadStatusMonitor(VcxSbReadStatusMonitor vcxSbReadStatusMonitor){
		this.vcxSbReadStatusMonitor=vcxSbReadStatusMonitor;
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_Add")
	public void vcxSbReadStatusMonitorAdd(){
		vcxSbReadStatusMonitorService.saveOrUpdate(vcxSbReadStatusMonitor);
		if(vcxSbReadStatusMonitor.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbReadStatusMonitor.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_Add_HasFiles")
	public void vcxSbReadStatusMonitorAddHasFiles(){
		vcxSbReadStatusMonitorService.saveOrUpdate(vcxSbReadStatusMonitor);
		Map m=new HashMap();
		if(vcxSbReadStatusMonitor.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbReadStatusMonitor.getId());
			m.put("fileid", "vcxSbReadStatusMonitor-"+vcxSbReadStatusMonitor.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_Delete_HasFiles")
	public void vcxSbReadStatusMonitorDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbReadStatusMonitorService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbReadStatusMonitor-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_List")
	public void vcxSbReadStatusMonitorList(){
		if(vcxSbReadStatusMonitor!=null){
			this.writeJson(vcxSbReadStatusMonitorService.dataGrid(this.getPage(), this.getRows(),vcxSbReadStatusMonitor));
		}else{
			this.writeJson(vcxSbReadStatusMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_List_All")
	public void vcxSbReadStatusMonitorListAll(){
		this.writeJson(vcxSbReadStatusMonitorService.listAll());
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_ComboBox")
	public void vcxSbReadStatusMonitorComboBox(){
		List<VcxSbReadStatusMonitor> l = vcxSbReadStatusMonitorService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbReadStatusMonitor obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_Delete")
	public void vcxSbReadStatusMonitorDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbReadStatusMonitorService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_Update")
	public void vcxSbReadStatusMonitorUpdate(){
		if(vcxSbReadStatusMonitor!=null) vcxSbReadStatusMonitorService.saveOrUpdate(vcxSbReadStatusMonitor);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_Excel")
	public void vcxSbReadStatusMonitorExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbReadStatusMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbReadStatusMonitorService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_FormFile")
	public void vcxSbReadStatusMonitorFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_Upload")
	public void vcxSbReadStatusMonitorUpload() throws IOException{
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
				vcxSbReadStatusMonitor=new VcxSbReadStatusMonitor();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbReadStatusMonitor,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbReadStatusMonitor.setId(null);
					 vcxSbReadStatusMonitorService.saveOrUpdate(vcxSbReadStatusMonitor);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbReadStatusMonitor vcxSbReadStatusMonitor,String p,String v){
		Field field = null;
		try {
			field = vcxSbReadStatusMonitor.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbReadStatusMonitor,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbReadStatusMonitor,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbReadStatusMonitor_Get_ById")  
	public void vcxSbReadStatusMonitorGetById(){
		VcxSbReadStatusMonitor vcxSbReadStatusMonitor=new VcxSbReadStatusMonitor();
		vcxSbReadStatusMonitor.setId(Integer.parseInt(this.getId()));
		vcxSbReadStatusMonitor=vcxSbReadStatusMonitorService.get(vcxSbReadStatusMonitor).get(0);
		if(vcxSbReadStatusMonitor==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbReadStatusMonitor);
		}
	}
	
	
	@Action("/sys/vcxSbReadStatusMonitor_Get_ByTid")  
	public void vcxSbReadStatusMonitorGetByTid(){
		VcxSbReadStatusMonitor vcxSbReadStatusMonitor=new VcxSbReadStatusMonitor();
		
		vcxSbReadStatusMonitor.setTerminalId(Integer.parseInt(this.getId()));
		vcxSbReadStatusMonitor=vcxSbReadStatusMonitorService.get(vcxSbReadStatusMonitor).get(0);
		if(vcxSbReadStatusMonitor==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbReadStatusMonitor);
		}
	}
	
	
	@Action("/sys/vcxSbReadStatusMonitor_Cmd_Collection")
	public void vcxSbReadStatusMonitor_Cmd_Collection(){
		this.writeJson(vcxSbReadStatusMonitorService.cmdCollection(this.getId()));
	}
	
	
	@Action("/sys/vcxSbReadStatusMonitor_Get_ByObj")  
	public void vcxSbReadStatusMonitorByObj(){
		this.writeJson(vcxSbReadStatusMonitorService.get(vcxSbReadStatusMonitor));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbReadStatusMonitor vcxSbReadStatusMonitor,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbReadStatusMonitor> lsList = vcxSbReadStatusMonitorService.get(vcxSbReadStatusMonitor);
		if(vcxSbReadStatusMonitorService.get(vcxSbReadStatusMonitor).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
