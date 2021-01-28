package com.doroodo.sys.action;


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
public class SyDemoGridtochartAction extends BaseAction{
	@Autowired
	private SyDemoGridtochartService syDemoGridtochartService;
	private SyDemoGridtochart syDemoGridtochart;
	private String EXCEL_TITLE = "表图联动";//请输入导出的excel表表名
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
	
	public SyDemoGridtochart getSyDemoGridtochart(){
		return syDemoGridtochart;
	}
	
	public void setSyDemoGridtochart(SyDemoGridtochart syDemoGridtochart){
		this.syDemoGridtochart=syDemoGridtochart;
	}
	
	@Action("/sys/syDemoGridtochart_Add")
	public void syDemoGridtochartAdd(){
		syDemoGridtochartService.saveOrUpdate(syDemoGridtochart);
		if(syDemoGridtochart.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDemoGridtochart.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/syDemoGridtochart_Add_HasFiles")
	public void syDemoGridtochartAddHasFiles(){
		syDemoGridtochartService.saveOrUpdate(syDemoGridtochart);
		Map m=new HashMap();
		if(syDemoGridtochart.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDemoGridtochart.getId());
			m.put("fileid", "syDemoGridtochart-"+syDemoGridtochart.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/syDemoGridtochart_Delete_HasFiles")
	public void syDemoGridtochartDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		syDemoGridtochartService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syDemoGridtochart-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syDemoGridtochart_List")
	public void syDemoGridtochartList(){
		this.writeJson(syDemoGridtochartService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syDemoGridtochart_List_All")
	public void syDemoGridtochartListAll(){
		this.writeJson(syDemoGridtochartService.listAll());
	}
	
	@Action("/sys/syDemoGridtochart_ComboBox")
	public void syDemoGridtochartComboBox(){
		List<SyDemoGridtochart> l = syDemoGridtochartService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SyDemoGridtochart obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/syDemoGridtochart_Delete")
	public void syDemoGridtochartDelete(){
		if(this.getIds().trim()=="")return;
		syDemoGridtochartService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syDemoGridtochart_Update")
	public void syDemoGridtochartUpdate(){
		if(syDemoGridtochart!=null) syDemoGridtochartService.saveOrUpdate(syDemoGridtochart);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syDemoGridtochart_Excel")
	public void syDemoGridtochartExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = syDemoGridtochartService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = syDemoGridtochartService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/syDemoGridtochart_FormFile")
	public void syDemoGridtochartFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/syDemoGridtochart_Upload")
	public void syDemoGridtochartUpload() throws IOException{
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
				syDemoGridtochart=new SyDemoGridtochart();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syDemoGridtochart,ps[ej],t.getCell(ei,ej));
					 }
					 syDemoGridtochart.setId(null);
					 syDemoGridtochartService.saveOrUpdate(syDemoGridtochart);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyDemoGridtochart syDemoGridtochart,String p,String v){
		Field field = null;
		try {
			field = syDemoGridtochart.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syDemoGridtochart,Float.valueOf(v).intValue());
			}else{
				field.set(syDemoGridtochart,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syDemoGridtochart_Get_ById")  
	public void syDemoGridtochartGetById(){
		SyDemoGridtochart syDemoGridtochart=new SyDemoGridtochart();
		syDemoGridtochart.setId(Integer.parseInt(this.getId()));
		syDemoGridtochart=syDemoGridtochartService.get(syDemoGridtochart).get(0);
		if(syDemoGridtochart==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syDemoGridtochart);
		}
	}
	
	@Action("/sys/syDemoGridtochart_Get_ByObj")  
	public void syDemoGridtochartByObj(){
		this.writeJson(syDemoGridtochartService.get(syDemoGridtochart));
	}
	
	//检查字段是否唯一
	private String isSingle(SyDemoGridtochart syDemoGridtochart,String fieldName,String fieldValue){
		String result=null;
		List<SyDemoGridtochart> lsList = syDemoGridtochartService.get(syDemoGridtochart);
		if(syDemoGridtochartService.get(syDemoGridtochart).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
