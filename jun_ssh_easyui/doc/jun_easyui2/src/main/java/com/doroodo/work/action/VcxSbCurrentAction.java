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
public class VcxSbCurrentAction extends BaseAction{
	@Autowired
	private VcxSbCurrentService vcxSbCurrentService;
	private VcxSbCurrent vcxSbCurrent;
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
	
	public VcxSbCurrent getVcxSbCurrent(){
		return vcxSbCurrent;
	}
	
	public void setVcxSbCurrent(VcxSbCurrent vcxSbCurrent){
		this.vcxSbCurrent=vcxSbCurrent;
	}
	
	@Action("/sys/vcxSbCurrent_Add")
	public void vcxSbCurrentAdd(){
		vcxSbCurrentService.saveOrUpdate(vcxSbCurrent);
		if(vcxSbCurrent.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbCurrent.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbCurrent_Add_HasFiles")
	public void vcxSbCurrentAddHasFiles(){
		vcxSbCurrentService.saveOrUpdate(vcxSbCurrent);
		Map m=new HashMap();
		if(vcxSbCurrent.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbCurrent.getId());
			m.put("fileid", "vcxSbCurrent-"+vcxSbCurrent.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbCurrent_Delete_HasFiles")
	public void vcxSbCurrentDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbCurrentService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbCurrent-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbCurrent_List")
	public void vcxSbCurrentList(){
		if(vcxSbCurrent!=null){
			this.writeJson(vcxSbCurrentService.dataGrid(this.getPage(), this.getRows(),vcxSbCurrent));
		}else{
			this.writeJson(vcxSbCurrentService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbCurrent_List_All")
	public void vcxSbCurrentListAll(){
		this.writeJson(vcxSbCurrentService.listAll());
	}
	
	@Action("/sys/vcxSbCurrent_ComboBox")
	public void vcxSbCurrentComboBox(){
		List<VcxSbCurrent> l = vcxSbCurrentService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbCurrent obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbCurrent_Delete")
	public void vcxSbCurrentDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbCurrentService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbCurrent_Update")
	public void vcxSbCurrentUpdate(){
		if(vcxSbCurrent!=null) vcxSbCurrentService.saveOrUpdate(vcxSbCurrent);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbCurrent_Excel")
	public void vcxSbCurrentExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbCurrentService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbCurrentService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbCurrent_FormFile")
	public void vcxSbCurrentFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbCurrent_Upload")
	public void vcxSbCurrentUpload() throws IOException{
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
				vcxSbCurrent=new VcxSbCurrent();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbCurrent,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbCurrent.setId(null);
					 vcxSbCurrentService.saveOrUpdate(vcxSbCurrent);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbCurrent vcxSbCurrent,String p,String v){
		Field field = null;
		try {
			field = vcxSbCurrent.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbCurrent,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbCurrent,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbCurrent_Get_ById")  
	public void vcxSbCurrentGetById(){
		VcxSbCurrent vcxSbCurrent=new VcxSbCurrent();
		vcxSbCurrent.setId(Integer.parseInt(this.getId()));
		try{
		vcxSbCurrent=vcxSbCurrentService.get(vcxSbCurrent).get(0);
		if(vcxSbCurrent==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbCurrent);
		}}
		catch(Exception e){
			this.writeMsg(SysVal.GET_ER);
		}
	}
	
	@Action("/sys/vcxSbCurrent_Get_ByObj")  
	public void vcxSbCurrentByObj(){
		this.writeJson(vcxSbCurrentService.get(vcxSbCurrent));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbCurrent vcxSbCurrent,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbCurrent> lsList = vcxSbCurrentService.get(vcxSbCurrent);
		if(vcxSbCurrentService.get(vcxSbCurrent).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
