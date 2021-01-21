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

import com.doroodo.base.action.BaseAction;
import com.doroodo.base.model.comboBox;
import com.doroodo.base.util.excelTools.Excel;
import com.doroodo.base.util.excelTools.Table;
import com.doroodo.code.GeneratorProperties;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.util.SmarteUntil;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SyAssemblyAction extends BaseAction{
	@Autowired
	private SyAssemblyService syAssemblyService;
	private SyAssembly syAssembly;
	private String EXCEL_TITLE = "组件列表";//请输入导出的excel表表名
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
	
	public SyAssembly getSyAssembly(){
		return syAssembly;
	}
	
	public void setSyAssembly(SyAssembly syAssembly){
		this.syAssembly=syAssembly;
	}
	
	@Action("/sys/syAssembly_Add")
	public void syAssemblyAdd(){
		syAssemblyService.saveOrUpdate(syAssembly);
		if(syAssembly.getId()!=null){
			writeMsg(SysVal.ADD_SUC);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	private static final String ASSEMBLY_TYPE="//eType new";
	private static final String ASSEMBLY_EGET="//eGet new";
	private static final String ASSEMBLY_ESET="//eSet new";
	private static final String ASSEMBLY_CSS="//css new";
	private static final String ASSEMBLY_HTML="//html new";
	private static final String ASSEMBLY_PROP="//prop new";
	@Action("/sys/syAssembly_Add_HasFiles")
	public void syAssemblyAddHasFiles(){
		syAssemblyService.saveOrUpdate(syAssembly);
		String path = SysVal.PROJECT_PATH;
		String path_js = path+"/WebRoot/js/core.js";
		if(!syAssembly.getSysname().isEmpty()){//插入组件类型
			int i=SmarteUntil.findLineFormFile(path_js,ASSEMBLY_TYPE);
			String str=SmarteUntil.listFileByRow(path_js,i+1);
			SmarteUntil.removeLineFromFile(path_js,i+1);
			int maxId=Integer.parseInt(str.substring(str.lastIndexOf(":")+1))+1;
			SmarteUntil.insertStringInFile(path_js,i+1,str+","+syAssembly.getSysname()+":"+maxId);
		}
		if(!syAssembly.getGetscript().isEmpty()){//插入get脚本
			int i=SmarteUntil.findLineFormFile(path_js,ASSEMBLY_EGET);
			String str="case eType."+syAssembly.getSysname()+":return "+syAssembly.getGetscript();
			SmarteUntil.insertStringInFile(path_js,i,str);
		}
		if(!syAssembly.getSetscript().isEmpty()){//插入set脚本
			int i=SmarteUntil.findLineFormFile(path_js,ASSEMBLY_ESET);
			String str="case eType."+syAssembly.getSysname()+":"+syAssembly.getSetscript()+" break;";
			SmarteUntil.insertStringInFile(path_js,i,str);
		}
		if(!syAssembly.getClassname().isEmpty()){//插入css脚本
			int i=SmarteUntil.findLineFormFile(path_js,ASSEMBLY_CSS);
			String str="case eType."+syAssembly.getSysname()+":return "+syAssembly.getClassname();
			SmarteUntil.insertStringInFile(path_js,i,str);
		}
		if(!syAssembly.getHtml().isEmpty()){//插入html
			String path_html = path+"/WebRoot/sys/SyDesign_frame.jsp";
			int i=SmarteUntil.findLineFormFile(path_html,ASSEMBLY_HTML);
			String str="case eType."+syAssembly.getSysname()+":html += "+syAssembly.getClassname()+"break;";
			SmarteUntil.insertStringInFile(path_html,i,str);
		}
		if(!syAssembly.getPropety().isEmpty()){//插入Propety
			String path_prop = path+"/WebRoot/sys/SyDesign.jsp";
			int i=SmarteUntil.findLineFormFile(path_prop,ASSEMBLY_PROP);
			String str="{type:\"eType."+syAssembly.getSysname()+"\",attr:["+syAssembly.getPropety()+"]},";
			SmarteUntil.insertStringInFile(path_prop,i,str);
		}
		Map m=new HashMap();
		if(syAssembly.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("fileid", "syAssembly-"+syAssembly.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/syAssembly_Delete_HasFiles")
	public void syAssemblyDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		syAssemblyService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syAssembly-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syAssembly_List")
	public void syAssemblyList(){
		this.writeJson(syAssemblyService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syAssembly_List_All")
	public void syAssemblyListAll(){
		this.writeJson(syAssemblyService.listAll());
	}
	
	@Action("/sys/syAssembly_ComboBox")
	public void syAssemblyComboBox(){
		List<SyAssembly> l = syAssemblyService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SyAssembly obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/syAssembly_Delete")
	public void syAssemblyDelete(){
		if(this.getIds().trim()=="")return;
		syAssemblyService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syAssembly_Update")
	public void syAssemblyUpdate(){
		if(syAssembly!=null) syAssemblyService.saveOrUpdate(syAssembly);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syAssembly_Excel")
	public void syAssemblyExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = syAssemblyService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = syAssemblyService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/syAssembly_Get_ByObj")  
	public void syAssemblyGetByObj(){
		this.writeJson(syAssemblyService.get(syAssembly));
	}
	
	@Action("/sys/syAssembly_FormFile")
	public void syAssemblyFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/syAssembly_Upload")
	public void syAssemblyUpload() throws IOException{
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
				syAssembly=new SyAssembly();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syAssembly,ps[ej],t.getCell(ei,ej));
					 }
					 syAssembly.setId(null);
					 syAssemblyService.saveOrUpdate(syAssembly);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyAssembly syAssembly,String p,String v){
		Field field = null;
		try {
			field = syAssembly.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syAssembly,Float.valueOf(v).intValue());
			}else{
				field.set(syAssembly,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，smarte平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syAssembly_Get_ById")  
	public void syAssemblyGetById(){
		SyAssembly syAssembly=new SyAssembly();
		syAssembly.setId(Integer.parseInt(this.getId()));
		syAssembly=syAssemblyService.get(syAssembly).get(0);
		if(syAssembly==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syAssembly);
		}
	}
	
	//检查字段是否唯一
	private String isSingle(SyAssembly syAssembly,String fieldName,String fieldValue){
		String result=null;
		List<SyAssembly> lsList = syAssemblyService.get(syAssembly);
		if(syAssemblyService.get(syAssembly).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
