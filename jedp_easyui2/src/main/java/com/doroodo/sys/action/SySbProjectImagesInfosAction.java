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
import com.doroodo.code.GeneratorProperties;
import com.doroodo.config.SysVal;
import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;
import com.doroodo.base.model.*;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SySbProjectImagesInfosAction extends BaseAction{
	@Autowired
	private SySbProjectImagesInfosService sySbProjectImagesInfosService;
	private SySbProjectImagesInfos sySbProjectImagesInfos;
	private String EXCEL_TITLE = "";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	private List<String> fileClass;
	
	public List<String> getFileClass() {
		return fileClass;
	}

	public void setFileClass(List<String> fileClass) {
		this.fileClass = fileClass;
	}

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
	
	public SySbProjectImagesInfos getSySbProjectImagesInfos(){
		return sySbProjectImagesInfos;
	}
	
	public void setSySbProjectImagesInfos(SySbProjectImagesInfos sySbProjectImagesInfos){
		this.sySbProjectImagesInfos=sySbProjectImagesInfos;
	}
	
	@Action("/sys/sySbProjectImagesInfos_Add")
	public void sySbProjectImagesInfosAdd(){
		sySbProjectImagesInfosService.saveOrUpdate(sySbProjectImagesInfos);
		if(sySbProjectImagesInfos.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbProjectImagesInfos.getId());
			m.put("fileid", "sySbProjectImagesInfos-"+sySbProjectImagesInfos.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/sySbProjectImagesInfos_Add_HasFiles")
	public void sySbProjectImagesInfosAddHasFiles(){
		sySbProjectImagesInfosService.saveOrUpdate(sySbProjectImagesInfos);
		Map m=new HashMap();
		if(sySbProjectImagesInfos.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", sySbProjectImagesInfos.getId());
			m.put("fileid", "sySbProjectImagesInfos-"+sySbProjectImagesInfos.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/sySbProjectImagesInfos_Delete_HasFiles")
	public void sySbProjectImagesInfosDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		sySbProjectImagesInfosService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "sySbProjectImagesInfos-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/sySbProjectImagesInfos_List")
	public void sySbProjectImagesInfosList(){
		if(sySbProjectImagesInfos!=null){
			this.writeJson(sySbProjectImagesInfosService.dataGrid(this.getPage(), this.getRows(),sySbProjectImagesInfos));
		}else{
			this.writeJson(sySbProjectImagesInfosService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/sySbProjectImagesInfos_List_All")
	public void sySbProjectImagesInfosListAll(){
		this.writeJson(sySbProjectImagesInfosService.listAll());
	}
	
	@Action("/sys/sySbProjectImagesInfos_ComboBox")
	public void sySbProjectImagesInfosComboBox(){
		List<SySbProjectImagesInfos> l = sySbProjectImagesInfosService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SySbProjectImagesInfos obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/sySbProjectImagesInfos_Delete")
	public void sySbProjectImagesInfosDelete(){
		if(this.getIds().trim()=="")return;
		sySbProjectImagesInfosService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/sySbProjectImagesInfos_Update")
	public void sySbProjectImagesInfosUpdate(){
		if(sySbProjectImagesInfos!=null) sySbProjectImagesInfosService.saveOrUpdate(sySbProjectImagesInfos);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/sySbProjectImagesInfos_Excel")
	public void sySbProjectImagesInfosExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = sySbProjectImagesInfosService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = sySbProjectImagesInfosService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/sySbProjectImagesInfos_FormFile")
	public void sySbProjectImagesInfosFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/sySbProjectImagesInfos_Upload")
	public void sySbProjectImagesInfosUpload() throws IOException{
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
				sySbProjectImagesInfos=new SySbProjectImagesInfos();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(sySbProjectImagesInfos,ps[ej],t.getCell(ei,ej));
					 }
					 sySbProjectImagesInfos.setId(null);
					 sySbProjectImagesInfosService.saveOrUpdate(sySbProjectImagesInfos);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SySbProjectImagesInfos sySbProjectImagesInfos,String p,String v){
		Field field = null;
		try {
			field = sySbProjectImagesInfos.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(sySbProjectImagesInfos,Float.valueOf(v).intValue());
			}else{
				field.set(sySbProjectImagesInfos,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/sySbProjectImagesInfos_Get_ById")  
	public void sySbProjectImagesInfosGetById(){
		SySbProjectImagesInfos sySbProjectImagesInfos=new SySbProjectImagesInfos();
		sySbProjectImagesInfos.setId(Integer.parseInt(this.getId()));
		try{
		sySbProjectImagesInfos=sySbProjectImagesInfosService.get(sySbProjectImagesInfos).get(0);
		if(sySbProjectImagesInfos==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(sySbProjectImagesInfos);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/sySbProjectImagesInfos_Get_ByObj")  
	public void sySbProjectImagesInfosByObj(){
		this.writeJson(sySbProjectImagesInfosService.get(sySbProjectImagesInfos));
	}
	@Action("/sys/sySbProjectImagesInfos_upload_file")
	public void uploadFile(){
		List<File> fileGroup=this.getFileGroup();
		List<String> fileGroupFileName=this.getFileGroupFileName();
		if(fileGroup==null||fileGroupFileName==null){this.writeMsg(SysVal.NOFILE); return;}
		float maxUploadFileSize=Float.parseFloat(GeneratorProperties.getProperty("maxUploadFileSize"))*1024*1024;
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//设置日期格式
		List<SyFile> syFiles=new ArrayList<SyFile>();
		String msg=SysVal.UPFILE_SUC;
		try {
			for (int i = 0; i < fileGroup.size(); i++) {
				File f=fileGroup.get(i);
				if(f.length()>maxUploadFileSize){
					Map<String, String> map = new HashMap<String, String>();
					String error="文件【"+fileGroupFileName.get(i)+"】超过了系统最大上传限制"+GeneratorProperties.getProperty("maxUploadFileSize")+"MB";
					map.put("error", error);
					this.writeJson(map);
					return ;
				}
			}
			Map< String, String> map=null;
			if(fileClass.size()>0){
				map=this.uploadFiles(fileGroup,fileGroupFileName,"/"+fileClass.get(0)+"/");
			}else{
				map=this.uploadFiles(fileGroup,fileGroupFileName,"");
			}
			for(int i=0;i<fileGroupFileName.size();i++){
				SyFile syFile_=new SyFile();
				String fileName=fileGroupFileName.get(i);
				String sysName=map.get(fileName);
				String createTime=df.format(new Date());
				syFile_.setCreatetime(createTime);
				syFile_.setFilename(fileName);
				syFile_.setSysname(sysName);
				syFile_.setUserid(this.getLoginUserId());
				if(this.getFileid()!=null){
					syFile_.setFileid(this.getFileid());
				}
				syFileService.saveOrUpdate(syFile_);
				List<SyFile> syFiles_=syFileService.get(syFile_);
				if(syFiles_.size()==1){
					syFiles.add(syFiles_.get(0));
				}
			}
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("syFiles", syFiles);
			this.writeJson(m);
			
		} catch (Exception e) {
			msg=SysVal.UPFILE_ER;
		}finally{
			this.writeJson(msg);
		}
	}
	
	//检查字段是否唯一
	private String isSingle(SySbProjectImagesInfos sySbProjectImagesInfos,String fieldName,String fieldValue){
		String result=null;
		List<SySbProjectImagesInfos> lsList = sySbProjectImagesInfosService.get(sySbProjectImagesInfos);
		if(sySbProjectImagesInfosService.get(sySbProjectImagesInfos).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
