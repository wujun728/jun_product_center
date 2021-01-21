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
public class CxSbLineAction extends BaseAction{
	@Autowired
	private CxSbLineService cxSbLineService;
	private CxSbLine cxSbLine;
	private String EXCEL_TITLE = "线路信息";//请输入导出的excel表表名
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
	
	public CxSbLine getCxSbLine(){
		return cxSbLine;
	}
	
	public void setCxSbLine(CxSbLine cxSbLine){
		this.cxSbLine=cxSbLine;
	}
	
	@Action("/sys/cxSbLine_Add")
	public void cxSbLineAdd(){
		cxSbLineService.saveOrUpdate(cxSbLine);
		if(cxSbLine.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbLine.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/cxSbLine_Add_HasFiles")
	public void cxSbLineAddHasFiles(){
		cxSbLineService.saveOrUpdate(cxSbLine);
		Map m=new HashMap();
		if(cxSbLine.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbLine.getId());
			m.put("fileid", "cxSbLine-"+cxSbLine.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/cxSbLine_Delete_HasFiles")
	public void cxSbLineDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		cxSbLineService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "cxSbLine-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/cxSbLine_List")
	public void cxSbLineList(){
		if(cxSbLine!=null){
			this.writeJson(cxSbLineService.dataGrid(this.getPage(), this.getRows(),cxSbLine));
		}else{
			this.writeJson(cxSbLineService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/cxSbLine_List_All")
	public void cxSbLineListAll(){
		this.writeJson(cxSbLineService.listAll());
	}
	
	@Action("/sys/cxSbLine_ComboBox")
	public void cxSbLineComboBox(){
		List<CxSbLine> l = cxSbLineService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			CxSbLine obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getLineName());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/cxSbLine_Delete")
	public void cxSbLineDelete(){
		if(this.getIds().trim()=="")return;
		cxSbLineService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/cxSbLine_Update")
	public void cxSbLineUpdate(){
		if(cxSbLine!=null) cxSbLineService.saveOrUpdate(cxSbLine);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/cxSbLine_Excel")
	public void cxSbLineExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = cxSbLineService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = cxSbLineService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/cxSbLine_FormFile")
	public void cxSbLineFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/cxSbLine_Upload")
	public void cxSbLineUpload() throws IOException{
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
				cxSbLine=new CxSbLine();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(cxSbLine,ps[ej],t.getCell(ei,ej));
					 }
					 cxSbLine.setId(null);
					 cxSbLineService.saveOrUpdate(cxSbLine);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(CxSbLine cxSbLine,String p,String v){
		Field field = null;
		try {
			field = cxSbLine.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(cxSbLine,Float.valueOf(v).intValue());
			}else{
				field.set(cxSbLine,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/cxSbLine_Get_ById")  
	public void cxSbLineGetById(){
		CxSbLine cxSbLine=new CxSbLine();
		cxSbLine.setId(Integer.parseInt(this.getId()));
		cxSbLine=cxSbLineService.get(cxSbLine).get(0);
		if(cxSbLine==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbLine);
		}
	}
	
	@Action("/sys/cxSbLine_Get_ByObj")  
	public void cxSbLineByObj(){
		this.writeJson(cxSbLineService.get(cxSbLine));
	}
	
	//检查字段是否唯一
	private String isSingle(CxSbLine cxSbLine,String fieldName,String fieldValue){
		String result=null;
		List<CxSbLine> lsList = cxSbLineService.get(cxSbLine);
		if(cxSbLineService.get(cxSbLine).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
