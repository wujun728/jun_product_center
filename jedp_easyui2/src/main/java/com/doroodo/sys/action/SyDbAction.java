package com.doroodo.sys.action;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.doroodo.base.action.BaseAction;
import com.doroodo.base.model.Tree;
import com.doroodo.code.GeneratorFacade;
import com.doroodo.code.provider.db.table.model.Column;
import com.doroodo.code.provider.db.table.model.Table;
import com.doroodo.config.SysVal;
import com.doroodo.sys.service.*;
import com.doroodo.util.SmarteUntil;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SyDbAction extends BaseAction{
	@Autowired
	private SyDbService syDbService;
	private String tableId="";
	private String fieldId="";
	private String remarks="";
	
	private String html="";
	private String ero="";
	private String modal="";
	private String excelName="";
	private String codeModalName="";
	
	private String tableName="";
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getHtml() {
		return html;
	}

	public String getCodeModalName() {
		return codeModalName;
	}

	public void setCodeModalName(String codeModalName) {
		this.codeModalName = codeModalName;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getEro() {
		return ero;
	}

	public void setEro(String ero) {
		this.ero = ero;
	}

	public String getModal() {
		return modal;
	}

	public void setModal(String modal) {
		this.modal = modal;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTableId() {
		return tableId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	@Action("/sys/syDb_Get_Tree")
	public void syDbGetTree(){
		List<Tree> ts =new ArrayList<Tree>();
		List<Table> ms=syDbService.list();
		for(int i=0;i<ms.size();i++){
			Table table = ms.get(i);
			String tableName=table.getSqlName();
			//生成业务表
		if((tableName.indexOf("_SB_")!=-1)||(tableName.indexOf("_sb_")!=-1)){
				Tree tree = new Tree();
				tree.setId(table.getClassName());
				tree.setText(tableName);
				tree.setState("open");
				tree.setChildren(null);
				ts.add(tree);
		}/**/
			//生成系统表
		/*if((tableName.indexOf("SY_")==0)||(tableName.indexOf("sy_")==0)){
			Tree tree = new Tree();
			tree.setId(table.getClassName());
			tree.setText(tableName);
			tree.setState("open");
			tree.setChildren(null);
			ts.add(tree);
		}*/
		}
		this.writeJson(ts);
	}
	
	
	class columnVo{
		private String id="";
		private String columnName="";
		private String remarks="";
		public String getColumnName() {
			return columnName;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		
	}
	
	@Action("/sys/syDb_Get_Table")
	public void syDbGetTable(){
		if(tableId=="") return ;
		LinkedHashSet<Column> ls=syDbService.getTableByTableName(tableId).getColumns();
		Iterator  it = ls.iterator();
		List l = new ArrayList();
		while(it.hasNext()){
			Column it_=(Column)it.next();
			columnVo c = new columnVo();
			c.setId(it_.getConstantName());
			c.setColumnName(it_.getConstantName());
			//c.setRemarks(it_.getRemarks());
			c.setRemarks(it_.getFieldRemarks());
			l.add(c);
		}
		this.writeJson(l);
	}
	
	@Action("/sys/syDb_Set_TableFormHtml")
	public void syDbSetTableFormHtml(){
		if(this.getHtml().trim().isEmpty()||this.getModal().trim().isEmpty()||this.getEro().trim().isEmpty())
		{
			this.writeMsg(SysVal.EDIT_ER);
			return;
		}
		setFormUrl(this.getEro(),this.getHtml(),this.getModal());
		this.writeMsg(SysVal.EDIT_SUC);
	}
	
	private String jsName="";
	private String jsValue="";
	
	public String getJsName() {
		return jsName;
	}

	public void setJsName(String jsName) {
		this.jsName = jsName;
	}

	public String getJsValue() {
		return jsValue;
	}

	public void setJsValue(String jsValue) {
		this.jsValue = jsValue;
	}

	@Action("/sys/syDb_Set_Js")
	public void syDbSetJs(){
		if(jsName.trim().isEmpty()) {this.writeMsg(SysVal.EDIT_ER);return;}
		String path = SysVal.PROJECT_PATH;
		String path_js = path+"/src/main/webapp/js/page/"+jsName;
		File filename = new File(path_js);
		if(filename.exists()){
			filename.delete();
		}
		try {
			filename.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//String jsTitle="// create by:"+this.getLoginUserName()+ "\r\n" ;
		//jsTitle+="// create time:"+this.getCurrentTime()+ "\r\n" ;
		//jsValue=jsTitle+jsValue;
		 RandomAccessFile mm = null;
	        try {
	            mm = new RandomAccessFile(filename, "rw");
	            mm.write(jsValue.getBytes("utf-8"));
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        } finally {
	            if (mm != null) {
	                try {
	                    mm.close();
	                } catch (IOException e2) {
	                    e2.printStackTrace();
	                }
	            }
	        }
	        this.writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syDb_Get_TableUrl")
	public void syDbGetTableUrl(){
		if(tableId=="") return ;
		Map map=new HashMap();
		List<Tree> ts =new ArrayList();
		Table table=syDbService.getTableByTableName(tableId);
		LinkedHashSet<Column> ls=table.getColumns();
		Iterator  it = ls.iterator();
		while(it.hasNext()){
			Column it_=(Column)it.next();
			Tree tree = new Tree();
			tree.setId(it_.getRemarks());
			tree.setText(it_.getSqlName());
			tree.setState("open");
			tree.setChildren(null);
			ts.add(tree);
		}
		map.put("url", "/sys/syParameter_Go_"+table.getClassName());
		map.put("treedata", ts);
		map.put("modal", table.getClassNameFirstLower());
		map.put("js", table.getClassName());
		this.writeJson(map);
	}
	
	@Action("/sys/syDb_Get_TableTree")
	public void syDbGetTableTree(){
		if(tableId=="") return ;
		List<Tree> ts =new ArrayList();
		LinkedHashSet<Column> ls=syDbService.getTableByTableName(tableId).getColumns();
		Iterator  it = ls.iterator();
		while(it.hasNext()){
			Column it_=(Column)it.next();
			Tree tree = new Tree();
			tree.setId(it_.getRemarks());
			tree.setText(it_.getSqlName());
			tree.setState("open");
			tree.setChildren(null);
			ts.add(tree);
		}
		this.writeJson(ts);
	}
	
	@Action("/sys/syDb_Get_TableField")
	public void syDbGetTableField(){
		if( (tableId=="") || (fieldId=="")) return ;
		LinkedHashSet<Column> ls=syDbService.getTableByTableName(tableId).getColumns();
		Iterator  it = ls.iterator();
		while(it.hasNext()){
			Column it_=(Column)it.next();
			if(it_.getSqlName().equals(fieldId)){
				this.writeJson(it_);
				return;
			}
		}
	}
	
	@Action("/sys/syDb_Set_TableFieldRemarks")
	public void syDbSetTableFieldRemarks(){
		if( (tableId=="") || (fieldId=="")) {this.writeMsg(SysVal.EDIT_ER); return ;}
		syDbService.setTableColumnRemarks(tableId, fieldId, remarks);
			this.writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syDb_Del_TableCode")
	public void syDbDelTableCode() throws Exception{
		if( (tableId=="")) {this.writeMsg(SysVal.EDIT_ER); return ;}
		String tablename=tableId;
		Table table=syDbService.getTableByTableName(tableId);
		GeneratorFacade g = new GeneratorFacade();
		String path = SysVal.PROJECT_PATH;
		String bakPath=path+"/bak/"+SmarteUntil.getCurrentTimeForFileName()+"-"+this.getLoginUserId()+"/";
		//清除路由
		String path_parmeter =  path+"/src/main/java/com/doroodo/sys/action/SyParameterAction.java";
		String gResultString =gResult(tablename);
		String gGoParameterString = gGoParameter(table);
		String[] sgs = gGoParameterString.split("\r\n");
		SmarteUntil.removeLineFromFile(path_parmeter,gResultString.replaceFirst("\t", ""));
		SmarteUntil.removeLineFromFile(path_parmeter,sgs[0].replaceFirst("\t", ""));
		SmarteUntil.removeLineFromFile(path_parmeter,sgs[1].replaceFirst("\t", ""));
		//清理model
		String path_model =  path+"/src/main/java/com/doroodo/work/model/"+table.getClassName()+".java";
		SmarteUntil.moveFile(new File(path_model), bakPath+"model/");
		//清理service接口
		String path_service =  path+"/src/main/java/com/doroodo/work/service/"+table.getClassName()+"Service.java";
		SmarteUntil.moveFile(new File(path_service), bakPath+"service/");
		//清理service实现
		String path_service_impl =  path+"/src/main/java/com/doroodo/work/service/impl/"+table.getClassName()+"ServiceImpl.java";
		SmarteUntil.moveFile(new File(path_service_impl), bakPath+"serviceImpl/");
		//清理action
		String path_action = path+"/src/main/java/com/doroodo/work/action/"+table.getClassName()+"Action.java";
		SmarteUntil.moveFile(new File(path_action), bakPath+"action/");
		//清理jsp
		String path_web = path+"/src/main/webapp/work/"+table.getClassName()+".jsp";
		SmarteUntil.moveFile(new File(path_web), bakPath+"jsp/");
		//清理js
		String path_js = path+"/src/main/webapp/js/page/"+table.getClassName()+".js";
		SmarteUntil.moveFile(new File(path_js), bakPath+"js/");
		SmarteUntil.log("清理完成!");
		this.writeMsg(SysVal.EDIT_SUC);
	}
	
	private static String addParmeter="//业务页面路由";
	@Action("/sys/syDb_Get_TableCode")
	public void syDbGetTableCode() throws Exception{
		String result=SysVal.EDIT_ER;
		if( (tableId=="")) {this.writeMsg(result); return ;}
		GeneratorFacade g = new GeneratorFacade();
		String path =SysVal.PROJECT_PATH;
		String[] strs=tableId.split("!");
		String tablename=strs[0];
		String[] strs_=strs[1].split(",");
		String codeModalName=this.getCodeModalName();
		if(!codeModalName.trim().isEmpty()){
			codeModalName="-"+codeModalName;
		}
		try{
		//产生model
		if(strs_[0].equalsIgnoreCase("1")){
			String path_model =  path+"/src/main/java/com/doroodo/work/model/";
			g.g.setOutRootDir(path_model);
			g.generateByTable(tablename,"template/model"); 
			String path_javascript =  path+"/src/main/webapp/js/page/";
			g.g.setOutRootDir(path_javascript);
			g.generateByTable(tablename,"template/javascript"); 
		}
		//产生service接口
		if(strs_[1].equalsIgnoreCase("1")){
			String path_service =  path+"/src/main/java/com/doroodo/work/service/";
			g.g.setOutRootDir(path_service);
			g.generateByTable(tablename,"template/service"+codeModalName); 
			//产生service实现
			String path_service_impl =  path+"/src/main/java/com/doroodo/work/service/impl/";
			g.g.setOutRootDir(path_service_impl);
			g.generateByTable(tablename,"template/serviceimpl"+codeModalName); 
		}
		//产生action
		if(strs_[2].equalsIgnoreCase("1")){
			String path_action = path+"/src/main/java/com/doroodo/work/action/";
			g.g.setOutRootDir(path_action);
			g.generateByTable(tablename,"template/action"+codeModalName,excelName); 
		}
		//产生jsp
		if(strs_[3].equalsIgnoreCase("1")){
			String path_web = path+"/src/main/webapp/work/";
			g.g.setOutRootDir(path_web);
			g.generateByTable(tablename,"template/web"+codeModalName); 
		}else if(strs_[3].equalsIgnoreCase("2")){
			String path_web = path+"/src/main/webapp/work/";
			g.g.setOutRootDir(path_web);
			g.generateByTable(tablename,"template/webHasFile"+codeModalName); 
		}
		//生成路由
		if(strs_[4].equalsIgnoreCase("1")){
			String path_parmeter =  path+"/src/main/java/com/doroodo/sys/action/SyParameterAction.java";
			String gResultString =gResult(tablename);
			Table table = syDbService.getTableByTableName(tablename);
			String gGoParameterString = gGoParameter(table);
			String[] sgs = gGoParameterString.split("\r\n");
			SmarteUntil.removeLineFromFile(path_parmeter,gResultString.replaceFirst("\t", ""));
			SmarteUntil.removeLineFromFile(path_parmeter,sgs[0].replaceFirst("\t", ""));
			SmarteUntil.removeLineFromFile(path_parmeter,sgs[1].replaceFirst("\t", ""));
			int index=SmarteUntil.findLineFormFile(path_parmeter,addParmeter);
			SmarteUntil.insertStringInFile(path_parmeter,SmarteUntil.insertStringInFile(path_parmeter,index+1,gResultString),gGoParameterString);//插入result
		}
		SmarteUntil.log("生成完成!");
			result=SysVal.EDIT_SUC;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.writeMsg(result);
		}
	}
	
	private String gGoParameter(Table table){
		String className = table.getClassName();
		char[] chars=new char[1];
		chars[0]=className.charAt(0);
		String temp=new String(chars);
		String classNameLower = className.replaceFirst(temp,temp.toLowerCase());
		String goResultString="\t@Action(\"/sys/syParameter_Go_"+className+"\")\r\n";
		goResultString+="\tpublic String syParameterGo"+className+"(){return \""+classNameLower+"\";}";
		return goResultString;
	}
	
	private String gResult(String tableId){
		Table table = syDbService.getTableByTableName(tableId);
		String className = table.getClassName();
		char[] chars=new char[1];
		chars[0]=className.charAt(0);
		String temp=new String(chars);
		String classNameLower = className.replaceFirst(temp,temp.toLowerCase());
		String goResultString="\t@Result(name=\""+classNameLower+"\", location=\"/work/"+className+".jsp\"),";
		return goResultString;
	}
	
	private static String addFormStart="<!-- 请填入新建表单html  start -->";
	private static String addFormEnd="<!-- 请填入新建表单html  end -->";
	private static String editFormStart="<!-- 请填入编辑表单html  start -->";
	private static String editFormEnd="<!-- 请填入编辑表单html  end -->";
	private void setFormUrl(String ero,String html,String modal){
		char[] chars=new char[1];
		chars[0]=modal.charAt(0);
		String temp=new String(chars);
		String classNameUpperCase= modal.replaceFirst(temp,temp.toUpperCase());

		GeneratorFacade g = new GeneratorFacade();
		String path = SysVal.PROJECT_PATH;
		String path_web = path+"/src/main/webapp/work/"+classNameUpperCase+".jsp";
		
		if(ero.equalsIgnoreCase("0")){
			try {
				int index=SmarteUntil.findLineFormFile(path_web,addFormStart);
				int end=SmarteUntil.findLineFormFile(path_web,addFormEnd);
				int delI=end-index-1;
				for(int i=0;i<delI;i++){
					SmarteUntil.removeLineFromFile(path_web,index+1);
				}
				SmarteUntil.insertStringInFile(path_web,index+1,html);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(ero.equalsIgnoreCase("1")){
			try {
				int index=SmarteUntil.findLineFormFile(path_web,editFormStart);
				int end=SmarteUntil.findLineFormFile(path_web,editFormEnd);
				int delI=end-index-1;
				for(int i=0;i<delI;i++){
					SmarteUntil.removeLineFromFile(path_web,index+1);
				}
				SmarteUntil.insertStringInFile(path_web,index+1,html);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
