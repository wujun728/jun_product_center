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
public class VcxSbBalanceDataAction extends BaseAction{
	@Autowired
	private VcxSbBalanceDataService vcxSbBalanceDataService;
	private VcxSbBalanceData vcxSbBalanceData;
	private String EXCEL_TITLE = "三相平衡管理";//请输入导出的excel表表名
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
	
	public VcxSbBalanceData getVcxSbBalanceData(){
		return vcxSbBalanceData;
	}
	
	public void setVcxSbBalanceData(VcxSbBalanceData vcxSbBalanceData){
		this.vcxSbBalanceData=vcxSbBalanceData;
	}
	
	@Action("/sys/vcxSbBalanceData_Add")
	public void vcxSbBalanceDataAdd(){
		vcxSbBalanceDataService.saveOrUpdate(vcxSbBalanceData);
		if(vcxSbBalanceData.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbBalanceData.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/vcxSbBalanceData_Add_HasFiles")
	public void vcxSbBalanceDataAddHasFiles(){
		vcxSbBalanceDataService.saveOrUpdate(vcxSbBalanceData);
		Map m=new HashMap();
		if(vcxSbBalanceData.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", vcxSbBalanceData.getId());
			m.put("fileid", "vcxSbBalanceData-"+vcxSbBalanceData.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/vcxSbBalanceData_Delete_HasFiles")
	public void vcxSbBalanceDataDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		vcxSbBalanceDataService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "vcxSbBalanceData-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/vcxSbBalanceData_List")
	public void vcxSbBalanceDataList(){
		if(vcxSbBalanceData!=null){
			this.writeJson(vcxSbBalanceDataService.dataGrid(this.getPage(), this.getRows(),vcxSbBalanceData));
		}else{
			this.writeJson(vcxSbBalanceDataService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/vcxSbBalanceData_List_All")
	public void vcxSbBalanceDataListAll(){
		this.writeJson(vcxSbBalanceDataService.listAll());
	}
	
	@Action("/sys/vcxSbBalanceData_ComboBox")
	public void vcxSbBalanceDataComboBox(){
		List<VcxSbBalanceData> l = vcxSbBalanceDataService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			VcxSbBalanceData obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/vcxSbBalanceData_Delete")
	public void vcxSbBalanceDataDelete(){
		if(this.getIds().trim()=="")return;
		vcxSbBalanceDataService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/vcxSbBalanceData_Update")
	public void vcxSbBalanceDataUpdate(){
		if(vcxSbBalanceData!=null) vcxSbBalanceDataService.saveOrUpdate(vcxSbBalanceData);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/vcxSbBalanceData_Excel")
	public void vcxSbBalanceDataExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = vcxSbBalanceDataService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = vcxSbBalanceDataService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/vcxSbBalanceData_FormFile")
	public void vcxSbBalanceDataFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/vcxSbBalanceData_Upload")
	public void vcxSbBalanceDataUpload() throws IOException{
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
				vcxSbBalanceData=new VcxSbBalanceData();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(vcxSbBalanceData,ps[ej],t.getCell(ei,ej));
					 }
					 vcxSbBalanceData.setId(null);
					 vcxSbBalanceDataService.saveOrUpdate(vcxSbBalanceData);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(VcxSbBalanceData vcxSbBalanceData,String p,String v){
		Field field = null;
		try {
			field = vcxSbBalanceData.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(vcxSbBalanceData,Float.valueOf(v).intValue());
			}else{
				field.set(vcxSbBalanceData,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/vcxSbBalanceData_Get_ById")  
	public void vcxSbBalanceDataGetById(){
		VcxSbBalanceData vcxSbBalanceData=new VcxSbBalanceData();
		vcxSbBalanceData.setId(Integer.parseInt(this.getId()));
		vcxSbBalanceData=vcxSbBalanceDataService.get(vcxSbBalanceData).get(0);
		if(vcxSbBalanceData==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(vcxSbBalanceData);
		}
	}
	
	@Action("/sys/vcxSbBalanceData_Get_ByObj")  
	public void vcxSbBalanceDataByObj(){
		this.writeJson(vcxSbBalanceDataService.get(vcxSbBalanceData));
	}
	
	@Action("/sys/vcxSbBalanceData_Cmd_AutoBalance")  
	public void vcxSbBalanceDataCmdAutoBalance(){
		VcxSbBalanceData vcxSbBalanceData=new VcxSbBalanceData();
		vcxSbBalanceData.setId(Integer.parseInt(this.getId()));
		vcxSbBalanceData=vcxSbBalanceDataService.get(vcxSbBalanceData).get(0);
		if(vcxSbBalanceData==null){
			HashMap<String,String> _res=new HashMap<String,String>();
			_res.put("code", "1");
			_res.put("msg", "未找到设备");
			this.writeJson(_res);
			return;
		}
		this.writeJson(vcxSbBalanceDataService.autoBalance(vcxSbBalanceData.getAddress(),vcxSbBalanceData.getStandard().toString()));
	}
	
	//检查字段是否唯一
	private String isSingle(VcxSbBalanceData vcxSbBalanceData,String fieldName,String fieldValue){
		String result=null;
		List<VcxSbBalanceData> lsList = vcxSbBalanceDataService.get(vcxSbBalanceData);
		if(vcxSbBalanceDataService.get(vcxSbBalanceData).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
