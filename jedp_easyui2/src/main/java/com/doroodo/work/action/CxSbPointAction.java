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
public class CxSbPointAction extends BaseAction{
	@Autowired
	private CxSbPointService cxSbPointService;
	private CxSbPoint cxSbPoint;
	private String EXCEL_TITLE = "点表";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	private String tab="";
	private String field="";
	private String fun="";
	private String where="";
	private String varName;
	
	
	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}


	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFun() {
		return fun;
	}

	public void setFun(String fun) {
		this.fun = fun;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
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
	
	public CxSbPoint getCxSbPoint(){
		return cxSbPoint;
	}
	
	public void setCxSbPoint(CxSbPoint cxSbPoint){
		this.cxSbPoint=cxSbPoint;
	}
	
	@Action("/sys/cxSbPoint_Add")
	public void cxSbPointAdd(){
		cxSbPointService.saveOrUpdate(cxSbPoint);
		if(cxSbPoint.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbPoint.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/cxSbPoint_Add_HasFiles")
	public void cxSbPointAddHasFiles(){
		cxSbPointService.saveOrUpdate(cxSbPoint);
		Map m=new HashMap();
		if(cxSbPoint.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbPoint.getId());
			m.put("fileid", "cxSbPoint-"+cxSbPoint.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/cxSbPoint_Delete_HasFiles")
	public void cxSbPointDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		cxSbPointService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "cxSbPoint-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/cxSbPoint_List")
	public void cxSbPointList(){
		if(cxSbPoint!=null){
			this.writeJson(cxSbPointService.dataGrid(this.getPage(), this.getRows(),cxSbPoint));
		}else{
			this.writeJson(cxSbPointService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/cxSbPoint_List_All")
	public void cxSbPointListAll(){
		this.writeJson(cxSbPointService.listAll());
	}
	
	@Action("/sys/cxSbPoint_ComboBox")
	public void cxSbPointComboBox(){
		List<CxSbPoint> l = cxSbPointService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			CxSbPoint obj=l.get(i);
			cb.setId(obj.getCode());
			cb.setText(obj.getCodeDesc());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/cxSbPoint_ComboBoxCode")
	public void cxSbPointComboBoxCode(){
		List<CxSbPoint> l = cxSbPointService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			CxSbPoint obj=l.get(i);
			cb.setId(obj.getCode());
			cb.setText(obj.getCodeDesc());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/cxSbPoint_Delete")
	public void cxSbPointDelete(){
		if(this.getIds().trim()=="")return;
		cxSbPointService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/cxSbPoint_Update")
	public void cxSbPointUpdate(){
		if(cxSbPoint!=null) cxSbPointService.saveOrUpdate(cxSbPoint);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/cxSbPoint_Excel")
	public void cxSbPointExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = cxSbPointService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = cxSbPointService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/cxSbPoint_FormFile")
	public void cxSbPointFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/cxSbPoint_Upload")
	public void cxSbPointUpload() throws IOException{
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
				cxSbPoint=new CxSbPoint();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(cxSbPoint,ps[ej],t.getCell(ei,ej));
					 }
					 cxSbPoint.setId(null);
					 cxSbPointService.saveOrUpdate(cxSbPoint);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(CxSbPoint cxSbPoint,String p,String v){
		Field field = null;
		try {
			field = cxSbPoint.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(cxSbPoint,Float.valueOf(v).intValue());
			}else{
				field.set(cxSbPoint,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/cxSbPoint_Get_ById")  
	public void cxSbPointGetById(){
		CxSbPoint cxSbPoint=new CxSbPoint();
		cxSbPoint.setId(Integer.parseInt(this.getId()));
		cxSbPoint=cxSbPointService.get(cxSbPoint).get(0);
		if(cxSbPoint==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbPoint);
		}
	}
	
	@Action("/sys/cxSbPoint_Get_By_Tab_Where")  
	public void cxSbPointGetPv(){
		this.writeJson(cxSbPointService.getPvByTabWhere(tab, field, fun, where));
	}

	@Action("/sys/cxSbPoint_GetJs_By_Vars_Arg")  
	public void cxSbPointGetJsByVarsArg(){
		String[] tabs=tab.split("\\^");
		String[] fields=field.split("\\^");
		String[] funs=fun.split("\\^");
		String[] wheres=where.split("\\^");
		String[] varNames=varName.split("\\^");
		HashMap<String, String> _res=new HashMap<String, String>();
		String js="";
		String vars="";
		String val="";
		for(int i=0;i<tabs.length;i++){
			try{
			val=((HashMap<?, ?>)cxSbPointService.getPvByTabWhere(tabs[i], fields[i], funs[i], wheres[i]).get(0)).get("val").toString();
			try{
			Float f = Float.parseFloat( val ); }
			catch(Exception e){
				val="'"+val+"'";
			}
			}
			catch(Exception e){
				val=null;
			}
			js+="var "+varNames[i]+"="+val+";";
			vars+=varNames[i]+":"+val+",";
		}
		vars=vars.substring(0,vars.length() - 1);
		_res.put("js", js);
		_res.put("vars", "{"+vars+"}");
		this.writeJson(_res);
	}
	
	//点号
	@Action("/sys/cxSbPoint_Get_By_Code")
	public void cxSbPointGetJsByid(){
		CxSbPoint cxSbPoint=new CxSbPoint();
		cxSbPoint.setCode(this.getId());
		try{
		cxSbPoint=cxSbPointService.get(cxSbPoint).get(0);
		if(cxSbPoint==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbPoint);
		}}
		catch(Exception ex){
			this.writeMsg(SysVal.GET_ER);
			return;
		}
	}
	
/*	@Action("/sys/cxSbPoint_GetPoint_By_id")
	public void cxSbPointGetPointByid(){
		
	}
	
	@Action("/sys/cxSbPoint_GetPoints_By_id")
	public void cxSbPointGetPointsByid(){
		
	}*/
	
	@Action("/sys/cxSbPoint_Get_ByObj")  
	public void cxSbPointByObj(){
		this.writeJson(cxSbPointService.get(cxSbPoint));
	}
	
	//检查字段是否唯一
	private String isSingle(CxSbPoint cxSbPoint,String fieldName,String fieldValue){
		String result=null;
		List<CxSbPoint> lsList = cxSbPointService.get(cxSbPoint);
		if(cxSbPointService.get(cxSbPoint).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
