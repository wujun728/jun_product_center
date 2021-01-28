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
public class CxSbBalanceDeviceAction extends BaseAction{
	@Autowired
	private CxSbBalanceDeviceService cxSbBalanceDeviceService;
	private CxSbBalanceDevice cxSbBalanceDevice;
	private String EXCEL_TITLE = "三相不平衡装置";//请输入导出的excel表表名
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
	
	public CxSbBalanceDevice getCxSbBalanceDevice(){
		return cxSbBalanceDevice;
	}
	
	public void setCxSbBalanceDevice(CxSbBalanceDevice cxSbBalanceDevice){
		this.cxSbBalanceDevice=cxSbBalanceDevice;
	}
	
	@Action("/sys/cxSbBalanceDevice_Add")
	public void cxSbBalanceDeviceAdd(){
		cxSbBalanceDeviceService.saveOrUpdate(cxSbBalanceDevice);
		if(cxSbBalanceDevice.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbBalanceDevice.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/cxSbBalanceDevice_Add_HasFiles")
	public void cxSbBalanceDeviceAddHasFiles(){
		cxSbBalanceDeviceService.saveOrUpdate(cxSbBalanceDevice);
		Map m=new HashMap();
		if(cxSbBalanceDevice.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbBalanceDevice.getId());
			m.put("fileid", "cxSbBalanceDevice-"+cxSbBalanceDevice.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/cxSbBalanceDevice_Delete_HasFiles")
	public void cxSbBalanceDeviceDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		cxSbBalanceDeviceService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "cxSbBalanceDevice-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/cxSbBalanceDevice_List")
	public void cxSbBalanceDeviceList(){
		if(cxSbBalanceDevice!=null){
			this.writeJson(cxSbBalanceDeviceService.dataGrid(this.getPage(), this.getRows(),cxSbBalanceDevice));
		}else{
			this.writeJson(cxSbBalanceDeviceService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/cxSbBalanceDevice_List_All")
	public void cxSbBalanceDeviceListAll(){
		this.writeJson(cxSbBalanceDeviceService.listAll());
	}
	
	@Action("/sys/cxSbBalanceDevice_List_By_line")
	public void cxSbBalanceDeviceListByLine(){
		CxSbBalanceDevice cxSbBalanceDevice=new CxSbBalanceDevice();
		cxSbBalanceDevice.setLocationLine(this.getId());
		List<CxSbBalanceDevice>l=cxSbBalanceDeviceService.get(cxSbBalanceDevice);
		if(l.size()<1){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(l);
		}
	}
	
	@Action("/sys/cxSbBalanceDevice_ComboBox")
	public void cxSbBalanceDeviceComboBox(){
		List<CxSbBalanceDevice> l = cxSbBalanceDeviceService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			CxSbBalanceDevice obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/cxSbBalanceDevice_Delete")
	public void cxSbBalanceDeviceDelete(){
		if(this.getIds().trim()=="")return;
		cxSbBalanceDeviceService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/cxSbBalanceDevice_Update")
	public void cxSbBalanceDeviceUpdate(){
		if(cxSbBalanceDevice!=null) cxSbBalanceDeviceService.saveOrUpdate(cxSbBalanceDevice);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/cxSbBalanceDevice_Excel")
	public void cxSbBalanceDeviceExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = cxSbBalanceDeviceService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = cxSbBalanceDeviceService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/cxSbBalanceDevice_FormFile")
	public void cxSbBalanceDeviceFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/cxSbBalanceDevice_Upload")
	public void cxSbBalanceDeviceUpload() throws IOException{
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
				cxSbBalanceDevice=new CxSbBalanceDevice();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(cxSbBalanceDevice,ps[ej],t.getCell(ei,ej));
					 }
					 cxSbBalanceDevice.setId(null);
					 cxSbBalanceDeviceService.saveOrUpdate(cxSbBalanceDevice);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(CxSbBalanceDevice cxSbBalanceDevice,String p,String v){
		Field field = null;
		try {
			field = cxSbBalanceDevice.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(cxSbBalanceDevice,Float.valueOf(v).intValue());
			}else{
				field.set(cxSbBalanceDevice,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/cxSbBalanceDevice_Get_ById")  
	public void cxSbBalanceDeviceGetById(){
		CxSbBalanceDevice cxSbBalanceDevice=new CxSbBalanceDevice();
		cxSbBalanceDevice.setId(Integer.parseInt(this.getId()));
		cxSbBalanceDevice=cxSbBalanceDeviceService.get(cxSbBalanceDevice).get(0);
		if(cxSbBalanceDevice==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbBalanceDevice);
		}
	}
	
	@Action("/sys/cxSbBalanceDevice_Get_ByObj")  
	public void cxSbBalanceDeviceByObj(){
		this.writeJson(cxSbBalanceDeviceService.get(cxSbBalanceDevice));
	}
	
	//检查字段是否唯一
	private String isSingle(CxSbBalanceDevice cxSbBalanceDevice,String fieldName,String fieldValue){
		String result=null;
		List<CxSbBalanceDevice> lsList = cxSbBalanceDeviceService.get(cxSbBalanceDevice);
		if(cxSbBalanceDeviceService.get(cxSbBalanceDevice).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
