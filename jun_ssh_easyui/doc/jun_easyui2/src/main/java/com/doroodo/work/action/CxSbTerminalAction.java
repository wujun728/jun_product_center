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
public class CxSbTerminalAction extends BaseAction{
	@Autowired
	private CxSbTerminalService cxSbTerminalService;
	private CxSbTerminal cxSbTerminal;
	private String EXCEL_TITLE = "";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	private String cmd="";
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
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
	
	public CxSbTerminal getCxSbTerminal(){
		return cxSbTerminal;
	}
	
	public void setCxSbTerminal(CxSbTerminal cxSbTerminal){
		this.cxSbTerminal=cxSbTerminal;
	}
	
	@Action("/sys/cxSbTerminal_Add")
	public void cxSbTerminalAdd(){
		cxSbTerminalService.saveOrUpdate(cxSbTerminal);
		if(cxSbTerminal.getId()!=null){
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbTerminal.getId());
			this.writeJson(m);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/cxSbTerminal_Add_HasFiles")
	public void cxSbTerminalAddHasFiles(){
		cxSbTerminalService.saveOrUpdate(cxSbTerminal);
		Map m=new HashMap();
		if(cxSbTerminal.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbTerminal.getId());
			m.put("fileid", "cxSbTerminal-"+cxSbTerminal.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/cxSbTerminal_Delete_HasFiles")
	public void cxSbTerminalDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		cxSbTerminalService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "cxSbTerminal-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/cxSbTerminal_List")
	public void cxSbTerminalList(){
		if(cxSbTerminal!=null){
			this.writeJson(cxSbTerminalService.dataGrid(this.getPage(), this.getRows(),cxSbTerminal));
		}else{
			this.writeJson(cxSbTerminalService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}
	
	@Action("/sys/cxSbTerminal_List_All")
	public void cxSbTerminalListAll(){
		this.writeJson(cxSbTerminalService.listAll());
	}
	
	@Action("/sys/cxSbTerminal_ComboBox")
	public void cxSbTerminalComboBox(){
		List<CxSbTerminal> l = cxSbTerminalService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			CxSbTerminal obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/cxSbTerminal_Delete")
	public void cxSbTerminalDelete(){
		if(this.getIds().trim()=="")return;
		cxSbTerminalService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/cxSbTerminal_Update")
	public void cxSbTerminalUpdate(){
		if(cxSbTerminal!=null) cxSbTerminalService.saveOrUpdate(cxSbTerminal);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/cxSbTerminal_Excel")
	public void cxSbTerminalExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = cxSbTerminalService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = cxSbTerminalService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/cxSbTerminal_FormFile")
	public void cxSbTerminalFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/cxSbTerminal_Upload")
	public void cxSbTerminalUpload() throws IOException{
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
				cxSbTerminal=new CxSbTerminal();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(cxSbTerminal,ps[ej],t.getCell(ei,ej));
					 }
					 cxSbTerminal.setId(null);
					 cxSbTerminalService.saveOrUpdate(cxSbTerminal);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(CxSbTerminal cxSbTerminal,String p,String v){
		Field field = null;
		try {
			field = cxSbTerminal.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(cxSbTerminal,Float.valueOf(v).intValue());
			}else{
				field.set(cxSbTerminal,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/cxSbTerminal_Get_ById")  
	public void cxSbTerminalGetById(){
		CxSbTerminal cxSbTerminal=new CxSbTerminal();
		cxSbTerminal.setId(Integer.parseInt(this.getId()));
		cxSbTerminal=cxSbTerminalService.get(cxSbTerminal).get(0);
		if(cxSbTerminal==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbTerminal);
		}
	}
	
	@Action("/sys/cxSbTerminal_Get_ByObj")  
	public void cxSbTerminalByObj(){
		this.writeJson(cxSbTerminalService.get(cxSbTerminal));
	}
	
	
	
	

	@Action("/sys/cxSbTerminalInfo_Get_ByPowerline")
	public void cxSbTerminalInfoByPowerline(){
		/*		List cxSbTerminalList=cxSbTerminalService.getTerminalInfoByPl(cxSbTerminal.getCircuit());
		if(cxSbTerminalList==null){
			this.writeMsg(GET_ER);
			return;
		}else{
			this.writeJson(cxSbTerminalList);
		}*/
		if( cxSbTerminal.getCircuit().isEmpty()) {this.writeMsg(SysVal.ARG_ER);return ;}
		CxSbTerminal _cxSbTerminal=new CxSbTerminal();
		_cxSbTerminal.setCircuit(cxSbTerminal.getCircuit());
		List<CxSbTerminal> ls=cxSbTerminalService.get(_cxSbTerminal);
		this.writeJson(ls);
	}
	
	@Action("/sys/cxSbTerminal_Control_ByCmd")
	public void cxSbTerminalControlByCmd(){
		
		this.writeJson(cxSbTerminalService.cmdControlTerminal(this.getId(), this.getCmd()));
		
	}
	
	@Action("/sys/cxSbTerminal_Get_State_ById")
	public void cxSbTerminalStateGetById(){
		CxSbTerminal cxSbTerminal=new CxSbTerminal();
		cxSbTerminal.setId(Integer.parseInt(this.getId()));
		cxSbTerminal=cxSbTerminalService.get(cxSbTerminal).get(0);
		if(cxSbTerminal==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(cxSbTerminal.getState());
		}
	}
	
	//检查字段是否唯一
	private String isSingle(CxSbTerminal cxSbTerminal,String fieldName,String fieldValue){
		String result=null;
		List<CxSbTerminal> lsList = cxSbTerminalService.get(cxSbTerminal);
		if(cxSbTerminalService.get(cxSbTerminal).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
