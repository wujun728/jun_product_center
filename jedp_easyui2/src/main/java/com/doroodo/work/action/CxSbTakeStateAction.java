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
public class CxSbTakeStateAction extends BaseAction{
	@Autowired
	private CxSbTakeStateService cxSbTakeStateService;
	private CxSbTakeState cxSbTakeState;
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
	
	public CxSbTakeState getCxSbTakeState(){
		return cxSbTakeState;
	}
	
	public void setCxSbTakeState(CxSbTakeState cxSbTakeState){
		this.cxSbTakeState=cxSbTakeState;
	}
	
	@Action("/sys/cxSbTakeState_Add")
	public void cxSbTakeStateAdd(){
		cxSbTakeStateService.saveOrUpdate(cxSbTakeState);
		if(cxSbTakeState.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbTakeState.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/cxSbTakeState_Add_HasFiles")
	public void cxSbTakeStateAddHasFiles(){
		cxSbTakeStateService.saveOrUpdate(cxSbTakeState);
		Map m=new HashMap();
		if(cxSbTakeState.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbTakeState.getId());
			m.put("fileid", "cxSbTakeState-"+cxSbTakeState.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/cxSbTakeState_Delete_HasFiles")
	public void cxSbTakeStateDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		cxSbTakeStateService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "cxSbTakeState-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/cxSbTakeState_List")
	public void cxSbTakeStateList(){
		if(cxSbTakeState!=null){
			this.writeJson(cxSbTakeStateService.dataGrid(this.getPage(), this.getRows(),cxSbTakeState));
		}else{
			this.writeJson(cxSbTakeStateService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/cxSbTakeState_List_All")
	public void cxSbTakeStateListAll(){
		this.writeJson(cxSbTakeStateService.listAll());
	}
	
	@Action("/sys/cxSbTakeState_ComboBox")
	public void cxSbTakeStateComboBox(){
		List<CxSbTakeState> l = cxSbTakeStateService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			CxSbTakeState obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/cxSbTakeState_Delete")
	public void cxSbTakeStateDelete(){
		if(this.getIds().trim()=="")return;
		cxSbTakeStateService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/cxSbTakeState_Update")
	public void cxSbTakeStateUpdate(){
		if(cxSbTakeState!=null) cxSbTakeStateService.saveOrUpdate(cxSbTakeState);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/cxSbTakeState_Excel")
	public void cxSbTakeStateExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = cxSbTakeStateService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = cxSbTakeStateService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/cxSbTakeState_FormFile")
	public void cxSbTakeStateFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/cxSbTakeState_Upload")
	public void cxSbTakeStateUpload() throws IOException{
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
				cxSbTakeState=new CxSbTakeState();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(cxSbTakeState,ps[ej],t.getCell(ei,ej));
					 }
					 cxSbTakeState.setId(null);
					 cxSbTakeStateService.saveOrUpdate(cxSbTakeState);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(CxSbTakeState cxSbTakeState,String p,String v){
		Field field = null;
		try {
			field = cxSbTakeState.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(cxSbTakeState,Float.valueOf(v).intValue());
			}else{
				field.set(cxSbTakeState,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/cxSbTakeState_Get_ById")  
	public void cxSbTakeStateGetById(){
		CxSbTakeState cxSbTakeState=new CxSbTakeState();
		cxSbTakeState.setId(Integer.parseInt(this.getId()));
		cxSbTakeState=cxSbTakeStateService.get(cxSbTakeState).get(0);
		if(cxSbTakeState==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbTakeState);
		}
	}
	
	@Action("/sys/cxSbTakeState_Get_ByObj")  
	public void cxSbTakeStateByObj(){
		this.writeJson(cxSbTakeStateService.get(cxSbTakeState));
	}
	
	//检查字段是否唯一
	private String isSingle(CxSbTakeState cxSbTakeState,String fieldName,String fieldValue){
		String result=null;
		List<CxSbTakeState> lsList = cxSbTakeStateService.get(cxSbTakeState);
		if(cxSbTakeStateService.get(cxSbTakeState).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
