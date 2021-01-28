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
public class SyDemoDatetreeAction extends BaseAction{
	@Autowired
	private SyDemoDatetreeService syDemoDatetreeService;
	private SyDemoDatetree syDemoDatetree;
	private String EXCEL_TITLE = "日期树表格";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	private String year="";
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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
	
	public SyDemoDatetree getSyDemoDatetree(){
		return syDemoDatetree;
	}
	
	public void setSyDemoDatetree(SyDemoDatetree syDemoDatetree){
		this.syDemoDatetree=syDemoDatetree;
	}
	
	@Action("/sys/syDemoDatetree_Add")
	public void syDemoDatetreeAdd(){
		syDemoDatetreeService.saveOrUpdate(syDemoDatetree);
		if(syDemoDatetree.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDemoDatetree.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/syDemoDatetree_Add_HasFiles")
	public void syDemoDatetreeAddHasFiles(){
		syDemoDatetreeService.saveOrUpdate(syDemoDatetree);
		Map m=new HashMap();
		if(syDemoDatetree.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", syDemoDatetree.getId());
			m.put("fileid", "syDemoDatetree-"+syDemoDatetree.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/syDemoDatetree_Delete_HasFiles")
	public void syDemoDatetreeDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		syDemoDatetreeService.delete(this.getIds());
		Map<String, String> m=new HashMap<String, String>();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syDemoDatetree-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syDemoDatetree_List")
	public void syDemoDatetreeList(){
		this.writeJson(syDemoDatetreeService.treeGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey(),this.getYear()));
	}
	
	@Action("/sys/syDemoDatetree_List_tree")
	public void syDemoDatetreeList_tree(){
		this.writeJson(syDemoDatetreeService.listByDate(this.getYear()));
	}
	
	@Action("/sys/syDemoDatetree_List_All")
	public void syDemoDatetreeListAll(){
		this.writeJson(syDemoDatetreeService.listAll());
	}
	
	@Action("/sys/syDemoDatetree_ComboBox")
	public void syDemoDatetreeComboBox(){
		List<SyDemoDatetree> l = syDemoDatetreeService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SyDemoDatetree obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/syDemoDatetree_Delete")
	public void syDemoDatetreeDelete(){
		if(this.getIds().trim()=="")return;
		syDemoDatetreeService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syDemoDatetree_Update")
	public void syDemoDatetreeUpdate(){
		if(syDemoDatetree!=null) syDemoDatetreeService.saveOrUpdate(syDemoDatetree);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syDemoDatetree_Excel")
	public void syDemoDatetreeExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = syDemoDatetreeService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = syDemoDatetreeService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/syDemoDatetree_FormFile")
	public void syDemoDatetreeFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/syDemoDatetree_Upload")
	public void syDemoDatetreeUpload() throws IOException{
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
				syDemoDatetree=new SyDemoDatetree();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syDemoDatetree,ps[ej],t.getCell(ei,ej));
					 }
					 syDemoDatetree.setId(null);
					 syDemoDatetreeService.saveOrUpdate(syDemoDatetree);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyDemoDatetree syDemoDatetree,String p,String v){
		Field field = null;
		try {
			field = syDemoDatetree.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syDemoDatetree,Float.valueOf(v).intValue());
			}else{
				field.set(syDemoDatetree,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syDemoDatetree_Get_ById")  
	public void syDemoDatetreeGetById(){
		SyDemoDatetree syDemoDatetree=new SyDemoDatetree();
		syDemoDatetree.setId(Integer.parseInt(this.getId()));
		syDemoDatetree=syDemoDatetreeService.get(syDemoDatetree).get(0);
		if(syDemoDatetree==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syDemoDatetree);
		}
	}
	
	@Action("/sys/syDemoDatetree_Get_ByObj")  
	public void syDemoDatetreeByObj(){
		this.writeJson(syDemoDatetreeService.get(syDemoDatetree));
	}
	
	//检查字段是否唯一
	private String isSingle(SyDemoDatetree syDemoDatetree,String fieldName,String fieldValue){
		String result=null;
		List<SyDemoDatetree> lsList = syDemoDatetreeService.get(syDemoDatetree);
		if(syDemoDatetreeService.get(syDemoDatetree).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
