package com.doroodo.sys.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
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
import com.doroodo.base.model.*;
import com.doroodo.code.GeneratorProperties;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.util.SmarteUntil;

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class SyChartAction extends BaseAction{
	@Autowired
	private SyChartService syChartService;
	private SyChart syChart;
	private String EXCEL_TITLE = "图表列表";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	private String chartHtml="";
	private String chartTitle="";
	public String getChartHtml() {
		return chartHtml;
	}

	public void setChartHtml(String chartHtml) {
		this.chartHtml = chartHtml;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
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
	
	public SyChart getSyChart(){
		return syChart;
	}
	
	public void setSyChart(SyChart syChart){
		this.syChart=syChart;
	}
	
	@Action("/sys/syChart_Add")
	public void syChartAdd(){
		syChart.setCreatetime(SmarteUntil.getCurrentTime());
		syChart.setUrl("/chart/"+syChart.getSysname()+".html");
		syChartService.saveOrUpdate(syChart);
		if(syChart.getId()!=null){
			writeMsg(SysVal.ADD_SUC);
		}else{
			writeMsg(SysVal.ADD_ER);
		}
	}
	
	@Action("/sys/syChart_Add_HasFiles")
	public void syChartAddHasFiles(){
		syChartService.saveOrUpdate(syChart);
		Map m=new HashMap();
		if(syChart.getId()!=null){
			m.put("info", SysVal.ADD_SUC);
			m.put("fileid", "syChart-"+syChart.getId());
			this.writeJson(m);
		}else{
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/syChart_Set_Chart")
	public void syChartSetChart(){
		if(chartTitle.trim().isEmpty()) {this.writeMsg(SysVal.EDIT_ER);return;}
		String path =SysVal.PROJECT_PATH;
		String path_chart = path+"/src/main/webapp/chart/"+chartTitle+".html";
		File filename = new File(path_chart);
		if(filename.exists()){
			filename.delete();  
		}
		try {
			filename.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 RandomAccessFile mm = null;
	        try {
	            mm = new RandomAccessFile(filename, "rw");
	            mm.write(chartHtml.getBytes("utf-8"));
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
	
	@Action("/sys/syChart_Delete_HasFiles")
	public void syChartDeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		syChartService.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "syChart-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/syChart_List")
	public void syChartList(){
		this.writeJson(syChartService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/syChart_List_All")
	public void syChartListAll(){
		this.writeJson(syChartService.listAll());
	}
	
	@Action("/sys/syChart_ComboBox")
	public void syChartComboBox(){
		List<SyChart> l = syChartService.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			SyChart obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/syChart_Delete")
	public void syChartDelete(){
		if(this.getIds().trim()=="")return;
		syChartService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syChart_Update")
	public void syChartUpdate(){
		syChart.setCreatetime(SmarteUntil.getCurrentTime());
		SyChart sychart = new SyChart();
		sychart.setCreateuser(this.getLoginUserId());
		List<SyChart> sycharts =syChartService.get(sychart);
		syChart.setUrl("/chart/"+syChart.getSysname()+".html");
		if(syChart!=null) syChartService.saveOrUpdate(syChart);
		writeMsg(SysVal.EDIT_SUC);
	}
	
	@Action("/sys/syChart_Excel")
	public void syChartExcel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = syChartService.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = syChartService.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/syChart_Get_ByObj")  
	public void syChartGetByObj(){
		this.writeJson(syChartService.get(syChart));
	}
	
	@Action("/sys/syChart_FormFile")
	public void syChartFormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/syChart_Upload")
	public void syChartUpload() throws IOException{
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
				syChart=new SyChart();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(syChart,ps[ej],t.getCell(ei,ej));
					 }
					 syChart.setId(null);
					 syChartService.saveOrUpdate(syChart);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=SysVal.UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(SyChart syChart,String p,String v){
		Field field = null;
		try {
			field = syChart.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(syChart,Float.valueOf(v).intValue());
			}else{
				field.set(syChart,v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/syChart_Get_ById")  
	public void syChartGetById(){
		SyChart syChart=new SyChart();
		syChart.setId(Integer.parseInt(this.getId()));
		syChart=syChartService.get(syChart).get(0);
		if(syChart==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syChart);
		}
	}
	
	@Action("/sys/syChart_Get_TestData")  
	public void syChartGetTestData(){
		this.writeText(String.valueOf(Math.random()*100));
	}
	
	//检查字段是否唯一
	private String isSingle(SyChart syChart,String fieldName,String fieldValue){
		String result=null;
		List<SyChart> lsList = syChartService.get(syChart);
		if(syChartService.get(syChart).size()>0) {
			result=fieldName+"["+fieldValue+"]"+SysVal.READHASE;
			return result;
		}
		return result;
	}

}
