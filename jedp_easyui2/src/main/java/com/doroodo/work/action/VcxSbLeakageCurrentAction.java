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
public class VcxSbLeakageCurrentAction extends BaseAction{
	@Autowired
	private VcxSbLeakageCurrentService vcxSbLeakageCurrentService;
	private VcxSbLeakageCurrent vcxSbLeakageCurrent;
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
	
	public VcxSbLeakageCurrent getVcxSbLeakageCurrent(){
		return vcxSbLeakageCurrent;
	}
	
	public void setVcxSbLeakageCurrent(VcxSbLeakageCurrent vcxSbLeakageCurrent){
		this.vcxSbLeakageCurrent=vcxSbLeakageCurrent;
	}
	
	@Action("/sys/vcxSbLeakageCurrent_Add")
	public void vcxSbLeakageCurrentAdd(){
		vcxSbLeakageCurrentService.saveOrUpdate(vcxSbLeakageCurrent);
		if(vcxSbLeakageCurrent.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbLeakageCurrent.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbLeakageCurrent_Add_HasFiles")
	public void vcxSbLeakageCurrentAddHasFiles(){
		vcxSbLeakageCurrentService.saveOrUpdate(vcxSbLeakageCurrent);
		Map m=new HashMap();
		if(vcxSbLeakageCurrent.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbLeakageCurrent.getId());
			m.put("fileid", "vcxSbLeakageCurrent-"+vcxSbLeakageCurrent.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbLeakageCurrent_Delete_HasFiles")
	public void vcxSbLeakageCurrentDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbLeakageCurrentService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbLeakageCurrent-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbLeakageCurrent_List")
	public void vcxSbLeakageCurrentList(){
		if(vcxSbLeakageCurrent!=null){
			this.writeJson(vcxSbLeakageCurrentService.dataGrid(this.getPage(), this.getRows(),vcxSbLeakageCurrent));
		}else{
			this.writeJson(vcxSbLeakageCurrentService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbLeakageCurrent_List_All")
	public void vcxSbLeakageCurrentListAll(){
		this.writeJson(vcxSbLeakageCurrentService.listAll());
	}
	
	@Action("/sys/vcxSbLeakageCurrent_ComboBox")
	public void vcxSbLeakageCurrentComboBox(){
		List<VcxSbLeakageCurrent> l = vcxSbLeakageCurrentService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbLeakageCurrent obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbLeakageCurrent_Delete")
	public void vcxSbLeakageCurrentDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbLeakageCurrentService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbLeakageCurrent_Update")
	public void vcxSbLeakageCurrentUpdate(){
		if(vcxSbLeakageCurrent!=null) vcxSbLeakageCurrentService.saveOrUpdate(vcxSbLeakageCurrent);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbLeakageCurrent_Excel")
	public void vcxSbLeakageCurrentExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbLeakageCurrentService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbLeakageCurrentService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbLeakageCurrent_FormFile")
	public void vcxSbLeakageCurrentFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbLeakageCurrent_Upload")
	public void vcxSbLeakageCurrentUpload() throws IOException{
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
				vcxSbLeakageCurrent=new VcxSbLeakageCurrent();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbLeakageCurrent,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbLeakageCurrent.setId(null);
					 vcxSbLeakageCurrentService.saveOrUpdate(vcxSbLeakageCurrent);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbLeakageCurrent vcxSbLeakageCurrent,String p,String v){
		Field field = null;
		try {
			field = vcxSbLeakageCurrent.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbLeakageCurrent,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbLeakageCurrent,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbLeakageCurrent_Get_ById")  
	public void vcxSbLeakageCurrentGetById(){
		VcxSbLeakageCurrent vcxSbLeakageCurrent=new VcxSbLeakageCurrent();
		vcxSbLeakageCurrent.setId(Integer.parseInt(this.getId()));
		try{
		vcxSbLeakageCurrent=vcxSbLeakageCurrentService.get(vcxSbLeakageCurrent).get(0);
		if(vcxSbLeakageCurrent==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbLeakageCurrent);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/vcxSbLeakageCurrent_Get_ByObj")  
	public void vcxSbLeakageCurrentByObj(){
		this.writeJson(vcxSbLeakageCurrentService.get(vcxSbLeakageCurrent));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbLeakageCurrent vcxSbLeakageCurrent,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbLeakageCurrent> lsList = vcxSbLeakageCurrentService.get(vcxSbLeakageCurrent);
		if(vcxSbLeakageCurrentService.get(vcxSbLeakageCurrent).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
