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
public class CxSbTestAction extends BaseAction{
	@Autowired
	private CxSbTestService cxSbTestService;
	private CxSbTest cxSbTest;
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
	
	public CxSbTest getCxSbTest(){
		return cxSbTest;
	}
	
	public void setCxSbTest(CxSbTest cxSbTest){
		this.cxSbTest=cxSbTest;
	}
	
	@Action("/sys/cxSbTest_Add")
	public void cxSbTestAdd(){
		cxSbTestService.saveOrUpdate(cxSbTest);
		if(cxSbTest.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbTest.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/cxSbTest_Add_HasFiles")
	public void cxSbTestAddHasFiles(){
		cxSbTestService.saveOrUpdate(cxSbTest);
		Map m=new HashMap();
		if(cxSbTest.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbTest.getId());
			m.put("fileid", "cxSbTest-"+cxSbTest.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/cxSbTest_Delete_HasFiles")
	public void cxSbTestDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		cxSbTestService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "cxSbTest-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/cxSbTest_List")
	public void cxSbTestList(){
		if(cxSbTest!=null){
			this.writeJson(cxSbTestService.dataGrid(this.getPage(), this.getRows(),cxSbTest));
		}else{
			this.writeJson(cxSbTestService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/cxSbTest_List_All")
	public void cxSbTestListAll(){
		this.writeJson(cxSbTestService.listAll());
	}
	
	@Action("/sys/cxSbTest_ComboBox")
	public void cxSbTestComboBox(){
		List<CxSbTest> l = cxSbTestService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			CxSbTest obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/cxSbTest_Delete")
	public void cxSbTestDelete(){
		if(this.getIds().trim()=="")return;
		cxSbTestService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/cxSbTest_Update")
	public void cxSbTestUpdate(){
		if(cxSbTest!=null) cxSbTestService.saveOrUpdate(cxSbTest);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/cxSbTest_Excel")
	public void cxSbTestExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = cxSbTestService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = cxSbTestService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/cxSbTest_FormFile")
	public void cxSbTestFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/cxSbTest_Upload")
	public void cxSbTestUpload() throws IOException{
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
				cxSbTest=new CxSbTest();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(cxSbTest,ps[ej],t.getCell(ei,ej));
					 }
					 cxSbTest.setId(null);
					 cxSbTestService.saveOrUpdate(cxSbTest);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(CxSbTest cxSbTest,String p,String v){
		Field field = null;
		try {
			field = cxSbTest.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(cxSbTest,Float.valueOf(v).intValue());
			}else{
				field.set(cxSbTest,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/cxSbTest_Get_ById")  
	public void cxSbTestGetById(){
		CxSbTest cxSbTest=new CxSbTest();
		cxSbTest.setId(Integer.parseInt(this.getId()));
		cxSbTest=cxSbTestService.get(cxSbTest).get(0);
		if(cxSbTest==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbTest);
		}
	}
	
	@Action("/sys/cxSbTest_Get_ByObj")  
	public void cxSbTestByObj(){
		this.writeJson(cxSbTestService.get(cxSbTest));
	}
	
	//检查字段是否唯一
	private String isSingle(CxSbTest cxSbTest,String fieldName,String fieldValue){
		String result=null;
		List<CxSbTest> lsList = cxSbTestService.get(cxSbTest);
		if(cxSbTestService.get(cxSbTest).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
