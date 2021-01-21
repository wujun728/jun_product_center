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
public class SyDemoTreeiframeAction extends BaseAction{
	@Autowired
	private SyDemoTreeiframeService syDemoTreeiframeService;
	private SyDemoTreeiframe syDemoTreeiframe;
	private String EXCEL_TITLE = "左树右表格";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	private String id="";
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public SyDemoTreeiframe getSyDemoTreeiframe(){
		return syDemoTreeiframe;
	}
	
	public void setSyDemoTreeiframe(SyDemoTreeiframe syDemoTreeiframe){
		this.syDemoTreeiframe=syDemoTreeiframe;
	}
	
	@Action("/sys/syDemoTreeiframe_Add")
	public void syDemoTreeiframeAdd(){
		syDemoTreeiframeService.saveOrUpdate(syDemoTreeiframe);
		if(syDemoTreeiframe.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDemoTreeiframe.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/syDemoTreeiframe_Add_HasFiles")
	public void syDemoTreeiframeAddHasFiles(){
		syDemoTreeiframeService.saveOrUpdate(syDemoTreeiframe);
		Map m=new HashMap();
		if(syDemoTreeiframe.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDemoTreeiframe.getId());
			m.put("fileid", "syDemoTreeiframe-"+syDemoTreeiframe.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/syDemoTreeiframe_Delete_HasFiles")
	public void syDemoTreeiframeDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		syDemoTreeiframeService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syDemoTreeiframe-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syDemoTreeiframe_List")
	public void syDemoTreeiframeList(){
		this.writeJson(syDemoTreeiframeService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syDemoTreeiframe_List_ByPid")
	public void syDemoTreeiframeListByPid(){
		this.writeJson(syDemoTreeiframeService.dataGrid(syDemoTreeiframe,this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syDemoTreeiframe_Get_Tree")
	public void syDemoTreeiframeGetTree(){
		this.writeJson(syDemoTreeiframeService.tree(id));
	}
	
	@Action("/sys/syDemoTreeiframe_List_All")
	public void syDemoTreeiframeListAll(){
		this.writeJson(syDemoTreeiframeService.listAll());
	}
	
	@Action("/sys/syDemoTreeiframe_ComboBox")
	public void syDemoTreeiframeComboBox(){
		List<SyDemoTreeiframe> l = syDemoTreeiframeService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SyDemoTreeiframe obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/syDemoTreeiframe_Delete")
	public void syDemoTreeiframeDelete(){
		if(this.getIds().trim()=="")return;
		syDemoTreeiframeService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syDemoTreeiframe_Update")
	public void syDemoTreeiframeUpdate(){
		if(syDemoTreeiframe!=null) syDemoTreeiframeService.saveOrUpdate(syDemoTreeiframe);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syDemoTreeiframe_Excel")
	public void syDemoTreeiframeExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = syDemoTreeiframeService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = syDemoTreeiframeService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/syDemoTreeiframe_FormFile")
	public void syDemoTreeiframeFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/syDemoTreeiframe_Upload")
	public void syDemoTreeiframeUpload() throws IOException{
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
				syDemoTreeiframe=new SyDemoTreeiframe();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syDemoTreeiframe,ps[ej],t.getCell(ei,ej));
					 }
					 syDemoTreeiframe.setId(null);
					 syDemoTreeiframeService.saveOrUpdate(syDemoTreeiframe);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyDemoTreeiframe syDemoTreeiframe,String p,String v){
		Field field = null;
		try {
			field = syDemoTreeiframe.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syDemoTreeiframe,Float.valueOf(v).intValue());
			}else{
				field.set(syDemoTreeiframe,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syDemoTreeiframe_Get_ById")  
	public void syDemoTreeiframeGetById(){
		SyDemoTreeiframe syDemoTreeiframe=new SyDemoTreeiframe();
		syDemoTreeiframe.setId(Integer.parseInt(this.getId()));
		syDemoTreeiframe=syDemoTreeiframeService.get(syDemoTreeiframe).get(0);
		if(syDemoTreeiframe==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syDemoTreeiframe);
		}
	}
	
	@Action("/sys/syDemoTreeiframe_Get_ByObj")  
	public void syDemoTreeiframeByObj(){
		this.writeJson(syDemoTreeiframeService.get(syDemoTreeiframe));
	}
	
	//检查字段是否唯一
	private String isSingle(SyDemoTreeiframe syDemoTreeiframe,String fieldName,String fieldValue){
		String result=null;
		List<SyDemoTreeiframe> lsList = syDemoTreeiframeService.get(syDemoTreeiframe);
		if(syDemoTreeiframeService.get(syDemoTreeiframe).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
