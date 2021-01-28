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
import com.doroodo.sys.model.SyFile;
import com.doroodo.sys.model.SySbProjectPage;
import com.doroodo.sys.service.SySbProjectPageService;
import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;
import com.doroodo.base.model.*;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SySbProjectPageAction extends BaseAction{
	@Autowired
	private SySbProjectPageService sySbProjectPageService;
	private SySbProjectPage sySbProjectPage;
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
	
	public SySbProjectPage getSySbProjectPage(){
		return sySbProjectPage;
	}
	
	public void setSySbProjectPage(SySbProjectPage sySbProjectPage){
		this.sySbProjectPage=sySbProjectPage;
	}
	
	@Action("/sys/sySbProjectPage_Add")
	public void sySbProjectPageAdd(){
		sySbProjectPageService.saveOrUpdate(sySbProjectPage);
		if(sySbProjectPage.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbProjectPage.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/sySbProjectPage_Add_HasFiles")
	public void sySbProjectPageAddHasFiles(){
		sySbProjectPageService.saveOrUpdate(sySbProjectPage);
		Map m=new HashMap();
		if(sySbProjectPage.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbProjectPage.getId());
			m.put("fileid", "sySbProjectPage-"+sySbProjectPage.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/sySbProjectPage_Delete_HasFiles")
	public void sySbProjectPageDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		sySbProjectPageService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "sySbProjectPage-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/sySbProjectPage_List")
	public void sySbProjectPageList(){
		if(sySbProjectPage!=null){
			this.writeJson(sySbProjectPageService.dataGrid(this.getPage(), this.getRows(),sySbProjectPage));
		}else{
			this.writeJson(sySbProjectPageService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/sySbProjectPage_List_All")
	public void sySbProjectPageListAll(){
		this.writeJson(sySbProjectPageService.listAll());
	}
	
	@Action("/sys/sySbProjectPage_ComboBox")
	public void sySbProjectPageComboBox(){
		List<SySbProjectPage> l = sySbProjectPageService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SySbProjectPage obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getPageName());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/sySbProjectPage_Delete")
	public void sySbProjectPageDelete(){
		if(this.getIds().trim()=="")return;
		sySbProjectPageService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/sySbProjectPage_Update")
	public void sySbProjectPageUpdate(){
		if(sySbProjectPage!=null) sySbProjectPageService.saveOrUpdate(sySbProjectPage);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/sySbProjectPage_Excel")
	public void sySbProjectPageExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = sySbProjectPageService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = sySbProjectPageService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/sySbProjectPage_FormFile")
	public void sySbProjectPageFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/sySbProjectPage_Upload")
	public void sySbProjectPageUpload() throws IOException{
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
				sySbProjectPage=new SySbProjectPage();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(sySbProjectPage,ps[ej],t.getCell(ei,ej));
					 }
					 sySbProjectPage.setId(null);
					 sySbProjectPageService.saveOrUpdate(sySbProjectPage);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SySbProjectPage sySbProjectPage,String p,String v){
		Field field = null;
		try {
			field = sySbProjectPage.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(sySbProjectPage,Float.valueOf(v).intValue());
			}else{
				field.set(sySbProjectPage,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/sySbProjectPage_Get_ById")  
	public void sySbProjectPageGetById(){
		SySbProjectPage sySbProjectPage=new SySbProjectPage();
		sySbProjectPage.setId(Integer.parseInt(this.getId()));
		try{
		sySbProjectPage=sySbProjectPageService.get(sySbProjectPage).get(0);
		if(sySbProjectPage==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(sySbProjectPage);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/sySbProjectPage_Get_ByObj")  
	public void sySbProjectPageByObj(){
		this.writeJson(sySbProjectPageService.get(sySbProjectPage));
	}
	
	//检查字段是否唯一
	private String isSingle(SySbProjectPage sySbProjectPage,String fieldName,String fieldValue){
		String result=null;
		List<SySbProjectPage> lsList = sySbProjectPageService.get(sySbProjectPage);
		if(sySbProjectPageService.get(sySbProjectPage).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
