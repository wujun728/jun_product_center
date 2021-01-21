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
public class VcxSbTerminalAction extends BaseAction{
	@Autowired
	private VcxSbTerminalService vcxSbTerminalService;
	private VcxSbTerminal vcxSbTerminal;
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
	
	public VcxSbTerminal getVcxSbTerminal(){
		return vcxSbTerminal;
	}
	
	public void setVcxSbTerminal(VcxSbTerminal vcxSbTerminal){
		this.vcxSbTerminal=vcxSbTerminal;
	}
	
	@Action("/sys/vcxSbTerminal_Add")
	public void vcxSbTerminalAdd(){
		vcxSbTerminalService.saveOrUpdate(vcxSbTerminal);
		if(vcxSbTerminal.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbTerminal.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbTerminal_Add_HasFiles")
	public void vcxSbTerminalAddHasFiles(){
		vcxSbTerminalService.saveOrUpdate(vcxSbTerminal);
		Map m=new HashMap();
		if(vcxSbTerminal.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbTerminal.getId());
			m.put("fileid", "vcxSbTerminal-"+vcxSbTerminal.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbTerminal_Delete_HasFiles")
	public void vcxSbTerminalDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbTerminalService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbTerminal-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbTerminal_List")
	public void vcxSbTerminalList(){
		if(vcxSbTerminal!=null){
			this.writeJson(vcxSbTerminalService.dataGrid(this.getPage(), this.getRows(),vcxSbTerminal));
		}else{
			this.writeJson(vcxSbTerminalService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbTerminal_List_All")
	public void vcxSbTerminalListAll(){
		this.writeJson(vcxSbTerminalService.listAll());
	}
	
	@Action("/sys/vcxSbTerminal_ComboBox")
	public void vcxSbTerminalComboBox(){
		List<VcxSbTerminal> l = vcxSbTerminalService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbTerminal obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbTerminal_Delete")
	public void vcxSbTerminalDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbTerminalService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbTerminal_Update")
	public void vcxSbTerminalUpdate(){
		if(vcxSbTerminal!=null) vcxSbTerminalService.saveOrUpdate(vcxSbTerminal);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbTerminal_Excel")
	public void vcxSbTerminalExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbTerminalService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbTerminalService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbTerminal_FormFile")
	public void vcxSbTerminalFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbTerminal_Upload")
	public void vcxSbTerminalUpload() throws IOException{
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
				vcxSbTerminal=new VcxSbTerminal();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbTerminal,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbTerminal.setId(null);
					 vcxSbTerminalService.saveOrUpdate(vcxSbTerminal);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbTerminal vcxSbTerminal,String p,String v){
		Field field = null;
		try {
			field = vcxSbTerminal.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbTerminal,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbTerminal,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbTerminal_Get_ById")  
	public void vcxSbTerminalGetById(){
		VcxSbTerminal vcxSbTerminal=new VcxSbTerminal();
		vcxSbTerminal.setId(Integer.parseInt(this.getId()));
		try{
		vcxSbTerminal=vcxSbTerminalService.get(vcxSbTerminal).get(0);
		if(vcxSbTerminal==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbTerminal);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/vcxSbTerminal_Get_ByObj")  
	public void vcxSbTerminalByObj(){
		this.writeJson(vcxSbTerminalService.get(vcxSbTerminal));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbTerminal vcxSbTerminal,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbTerminal> lsList = vcxSbTerminalService.get(vcxSbTerminal);
		if(vcxSbTerminalService.get(vcxSbTerminal).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
