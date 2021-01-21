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

import com.doroodo.base.action.BaseAction;
import com.doroodo.config.SysVal;
import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;

import com.doroodo.base.model.*;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SySbRightEventInfoAction extends BaseAction{
	@Autowired
	private SySbRightEventInfoService sySbRightEventInfoService;
	private SySbRightEventInfo sySbRightEventInfo;
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
	
	public SySbRightEventInfo getSySbRightEventInfo(){
		return sySbRightEventInfo;
	}
	
	public void setSySbRightEventInfo(SySbRightEventInfo sySbRightEventInfo){
		this.sySbRightEventInfo=sySbRightEventInfo;
	}
	
	@Action("/sys/sySbRightEventInfo_Add")
	public void sySbRightEventInfoAdd(){
		sySbRightEventInfoService.saveOrUpdate(sySbRightEventInfo);
		if(sySbRightEventInfo.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbRightEventInfo.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/sySbRightEventInfo_Add_HasFiles")
	public void sySbRightEventInfoAddHasFiles(){
		sySbRightEventInfoService.saveOrUpdate(sySbRightEventInfo);
		Map m=new HashMap();
		if(sySbRightEventInfo.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbRightEventInfo.getId());
			m.put("fileid", "sySbRightEventInfo-"+sySbRightEventInfo.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/sySbRightEventInfo_Delete_HasFiles")
	public void sySbRightEventInfoDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		sySbRightEventInfoService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "sySbRightEventInfo-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/sySbRightEventInfo_List")
	public void sySbRightEventInfoList(){
		if(sySbRightEventInfo!=null){
			this.writeJson(sySbRightEventInfoService.dataGrid(this.getPage(), this.getRows(),sySbRightEventInfo));
		}else{
			this.writeJson(sySbRightEventInfoService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/sySbRightEventInfo_List_All")
	public void sySbRightEventInfoListAll(){
		this.writeJson(sySbRightEventInfoService.listAll());
	}
	
	@Action("/sys/sySbRightEventInfo_ComboBox")
	public void sySbRightEventInfoComboBox(){
		List<SySbRightEventInfo> l = sySbRightEventInfoService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SySbRightEventInfo obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/sySbRightEventInfo_Delete")
	public void sySbRightEventInfoDelete(){
		if(this.getIds().trim()=="")return;
		sySbRightEventInfoService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/sySbRightEventInfo_Update")
	public void sySbRightEventInfoUpdate(){
		if(sySbRightEventInfo!=null) sySbRightEventInfoService.saveOrUpdate(sySbRightEventInfo);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/sySbRightEventInfo_Excel")
	public void sySbRightEventInfoExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = sySbRightEventInfoService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = sySbRightEventInfoService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/sySbRightEventInfo_FormFile")
	public void sySbRightEventInfoFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/sySbRightEventInfo_Upload")
	public void sySbRightEventInfoUpload() throws IOException{
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
				sySbRightEventInfo=new SySbRightEventInfo();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(sySbRightEventInfo,ps[ej],t.getCell(ei,ej));
					 }
					 sySbRightEventInfo.setId(null);
					 sySbRightEventInfoService.saveOrUpdate(sySbRightEventInfo);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SySbRightEventInfo sySbRightEventInfo,String p,String v){
		Field field = null;
		try {
			field = sySbRightEventInfo.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(sySbRightEventInfo,Float.valueOf(v).intValue());
			}else{
				field.set(sySbRightEventInfo,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/sySbRightEventInfo_Get_ById")  
	public void sySbRightEventInfoGetById(){
		SySbRightEventInfo sySbRightEventInfo=new SySbRightEventInfo();
		sySbRightEventInfo.setId(Integer.parseInt(this.getId()));
		try{
		sySbRightEventInfo=sySbRightEventInfoService.get(sySbRightEventInfo).get(0);
		if(sySbRightEventInfo==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(sySbRightEventInfo);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/sySbRightEventInfo_Get_ByObj")  
	public void sySbRightEventInfoByObj(){
		this.writeJson(sySbRightEventInfoService.get(sySbRightEventInfo));
	}
	
	//检查字段是否唯一
	private String isSingle(SySbRightEventInfo sySbRightEventInfo,String fieldName,String fieldValue){
		String result=null;
		List<SySbRightEventInfo> lsList = sySbRightEventInfoService.get(sySbRightEventInfo);
		if(sySbRightEventInfoService.get(sySbRightEventInfo).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
