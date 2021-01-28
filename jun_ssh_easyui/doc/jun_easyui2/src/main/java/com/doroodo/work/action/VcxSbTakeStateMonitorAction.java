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
import com.doroodo.base.msg.SendMsg;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.util.Pusher;
import com.doroodo.work.model.*;
import com.doroodo.work.service.*;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class VcxSbTakeStateMonitorAction extends BaseAction{
	@Autowired
	private VcxSbTakeStateMonitorService vcxSbTakeStateMonitorService;
	private VcxSbTakeStateMonitor vcxSbTakeStateMonitor;
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
	
	public VcxSbTakeStateMonitor getVcxSbTakeStateMonitor(){
		return vcxSbTakeStateMonitor;
	}
	
	public void setVcxSbTakeStateMonitor(VcxSbTakeStateMonitor vcxSbTakeStateMonitor){
		this.vcxSbTakeStateMonitor=vcxSbTakeStateMonitor;
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_Add")
	public void vcxSbTakeStateMonitorAdd(){
		vcxSbTakeStateMonitorService.saveOrUpdate(vcxSbTakeStateMonitor);
		if(vcxSbTakeStateMonitor.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbTakeStateMonitor.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_Add_HasFiles")
	public void vcxSbTakeStateMonitorAddHasFiles(){
		vcxSbTakeStateMonitorService.saveOrUpdate(vcxSbTakeStateMonitor);
		Map m=new HashMap();
		if(vcxSbTakeStateMonitor.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbTakeStateMonitor.getId());
			m.put("fileid", "vcxSbTakeStateMonitor-"+vcxSbTakeStateMonitor.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_Delete_HasFiles")
	public void vcxSbTakeStateMonitorDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbTakeStateMonitorService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbTakeStateMonitor-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_List")
	public void vcxSbTakeStateMonitorList(){
		if(vcxSbTakeStateMonitor!=null){
			this.writeJson(vcxSbTakeStateMonitorService.dataGrid(this.getPage(), this.getRows(),vcxSbTakeStateMonitor));
		}else{
			this.writeJson(vcxSbTakeStateMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_List_All")
	public void vcxSbTakeStateMonitorListAll(){
		this.writeJson(vcxSbTakeStateMonitorService.listAll());
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_ComboBox")
	public void vcxSbTakeStateMonitorComboBox(){
		List<VcxSbTakeStateMonitor> l = vcxSbTakeStateMonitorService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbTakeStateMonitor obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_Delete")
	public void vcxSbTakeStateMonitorDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbTakeStateMonitorService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_Update")
	public void vcxSbTakeStateMonitorUpdate(){
		if(vcxSbTakeStateMonitor!=null) vcxSbTakeStateMonitorService.saveOrUpdate(vcxSbTakeStateMonitor);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_Excel")
	public void vcxSbTakeStateMonitorExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbTakeStateMonitorService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbTakeStateMonitorService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_FormFile")
	public void vcxSbTakeStateMonitorFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_Upload")
	public void vcxSbTakeStateMonitorUpload() throws IOException{
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
				vcxSbTakeStateMonitor=new VcxSbTakeStateMonitor();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbTakeStateMonitor,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbTakeStateMonitor.setId(null);
					 vcxSbTakeStateMonitorService.saveOrUpdate(vcxSbTakeStateMonitor);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbTakeStateMonitor vcxSbTakeStateMonitor,String p,String v){
		Field field = null;
		try {
			field = vcxSbTakeStateMonitor.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbTakeStateMonitor,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbTakeStateMonitor,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_Get_ById")  
	public void vcxSbTakeStateMonitorGetById(){
		VcxSbTakeStateMonitor vcxSbTakeStateMonitor=new VcxSbTakeStateMonitor();
		vcxSbTakeStateMonitor.setId(Integer.parseInt(this.getId()));
		vcxSbTakeStateMonitor=vcxSbTakeStateMonitorService.get(vcxSbTakeStateMonitor).get(0);
		if(vcxSbTakeStateMonitor==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbTakeStateMonitor);
		}
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_Get_ByObj")  
	public void vcxSbTakeStateMonitorByObj(){
		this.writeJson(vcxSbTakeStateMonitorService.get(vcxSbTakeStateMonitor));
	}
	@Action("/sys/vcxSbTakeStateMonitor_Count_By_State")  
	public void vcxSbTakeStateMonitorCountByState(){
		this.writeJson(vcxSbTakeStateMonitorService.countGroupByState());
	}
	
	@Action("/sys/vcxSbTakeStateMonitor_List_By_Line")  
	public void vcxSbTakeStateMonitorListByLine(){
		VcxSbTakeStateMonitor vcxSbTakeStateMonitor=new VcxSbTakeStateMonitor();
		vcxSbTakeStateMonitor.setLineId(Integer.parseInt(this.getId()));
		this.writeJson(vcxSbTakeStateMonitorService.get(vcxSbTakeStateMonitor));
	}
	//检查字段是否唯一
	private String isSingle(VcxSbTakeStateMonitor vcxSbTakeStateMonitor,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbTakeStateMonitor> lsList = vcxSbTakeStateMonitorService.get(vcxSbTakeStateMonitor);
		if(vcxSbTakeStateMonitorService.get(vcxSbTakeStateMonitor).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
