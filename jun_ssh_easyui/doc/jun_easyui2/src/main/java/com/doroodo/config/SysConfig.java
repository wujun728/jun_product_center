package com.doroodo.config;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doroodo.code.GeneratorProperties;
import com.doroodo.code.provider.db.table.model.Table;
import com.doroodo.sys.service.SyDbService;
import com.doroodo.util.SmarteUntil;

public class SysConfig {
	private  SyDbService syDbService = null;
	private  Map<String, String> DbMap = null;
	private  String sysPath="";

	public SysConfig() {
		setWebPath();
		setMessage();
		CreateDBMap();// 初始化dbmap到dbmap.js
	}
	
	public  void setWebPath(){
		sysPath=this.getClass().getClassLoader().getResource("").getPath().split("WEB-INF")[0]; 
		SysVal.PROJECT_PATH=GeneratorProperties.getProperty("codeRoot");
	}
	
	public void setMessage(){
		SysVal.GET_ER=GeneratorProperties.getProperty("GET_ER");
		SysVal.ADD_SUC=GeneratorProperties.getProperty("ADD_SUC");
		SysVal.EDIT_SUC=GeneratorProperties.getProperty("EDIT_SUC");
		SysVal.ADD_ER=GeneratorProperties.getProperty("ADD_ER");
		SysVal.EDIT_ER=GeneratorProperties.getProperty("EDIT_ER");
		SysVal.SET_SUC=GeneratorProperties.getProperty("SET_SUC");
		SysVal.SET_ER=GeneratorProperties.getProperty("SET_ER");
		SysVal.DEL_SUC=GeneratorProperties.getProperty("DEL_SUC");
		
		SysVal.DEL_ER_MAIN=GeneratorProperties.getProperty("DEL_ER_MAIN");
		SysVal.LOGIN_YES=GeneratorProperties.getProperty("LOGIN_YES");
		SysVal.LOGIN_NO=GeneratorProperties.getProperty("LOGIN_NO");
		SysVal.LOGIN_ER=GeneratorProperties.getProperty("LOGIN_ER");
		SysVal.OPT_ERROR=GeneratorProperties.getProperty("OPT_ERROR");
		SysVal.OPT_DLG_ERROR=GeneratorProperties.getProperty("OPT_DLG_ERROR");
		SysVal.FIle_UPLOAD_SUC=GeneratorProperties.getProperty("FIle_UPLOAD_SUC");
		SysVal.FIle_UPLOAD_ER=GeneratorProperties.getProperty("FIle_UPLOAD_ER");
		
		SysVal.READHASE=GeneratorProperties.getProperty("READHASE");
		SysVal.STARTWORKFOW_SUC=GeneratorProperties.getProperty("STARTWORKFOW_SUC");
		SysVal.STARTWORKFOW_ER=GeneratorProperties.getProperty("STARTWORKFOW_ER");
		SysVal.DOWORKFOW_SUC=GeneratorProperties.getProperty("DOWORKFOW_SUC");
		SysVal.DOWORKFOW_ER=GeneratorProperties.getProperty("DOWORKFOW_ER");
		SysVal.NOFILE=GeneratorProperties.getProperty("NOFILE");
		SysVal.UPFILE_SUC=GeneratorProperties.getProperty("UPFILE_SUC");
		SysVal.UPFILE_ER=GeneratorProperties.getProperty("UPFILE_ER");
		
		SysVal.UPDATA_SUC=GeneratorProperties.getProperty("UPDATA_SUC");
		SysVal.UPDATA_ER=GeneratorProperties.getProperty("UPDATA_ER");
		SysVal.ARG_ER=GeneratorProperties.getProperty("ARG_ER");
		
		
		SysVal.DAS_SERVER_IP=GeneratorProperties.getProperty("das.server.ip");
		SysVal.DAS_SERVER_PORT=Integer.parseInt(GeneratorProperties.getProperty("das.server.port"));
		
		SysVal.DAS_CMD_OPEN=GeneratorProperties.getProperty("das.cmd.open");
		SysVal.DAS_CMD_CLOSE=GeneratorProperties.getProperty("das.cmd.close");
		SysVal.DAS_CMD_DAS=GeneratorProperties.getProperty("das.cmd.das");
		SysVal.DAS_CMD_CONFIG=GeneratorProperties.getProperty("das.cmd.config");
		SysVal.DAS_CMD_BALANCE=GeneratorProperties.getProperty("das.cmd.balance");
		
		SysVal.DAS_SERVER_CONNECTION_TIMEOUT=Integer.parseInt(GeneratorProperties.getProperty("das.server.connectiontimeout"));
		SysVal.DAS_SERVER_READ_TIMEOUT=Integer.parseInt(GeneratorProperties.getProperty("das.server.readtimeout"));

		SysVal.DAS_CMD_RESULT=new HashMap<String,String>();
		SysVal.DAS_CMD_RESULT.put("0", "操作成功");
		SysVal.DAS_CMD_RESULT.put("1", "找不到该设备");
		SysVal.DAS_CMD_RESULT.put("2", "参数错误");
		SysVal.DAS_CMD_RESULT.put("3", "不能连接到服务");
		SysVal.DAS_CMD_RESULT.put("4", "连接超时");
		SysVal.DAS_CMD_RESULT.put("5", "执行命令超时");
		SysVal.DAS_CMD_RESULT.put("6", "操作失败");
		SysVal.DAS_CMD_RESULT.put("7", "操作异常");
	}

	public  SyDbService getInstance() {
		try{
		if (syDbService == null) {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"classpath:applicationContext.xml");
			syDbService = (SyDbService) ctx.getBean("dbDao");
			List<Table> ms = syDbService.list();
			DbMap = new HashMap<String, String>();
			for (int i = 0; i < ms.size(); i++) {
				Table table = ms.get(i);
				DbMap.put(table.getSqlName(), table.getClassNameFirstLower());
			}
		}}
		catch(Exception e){
			e.printStackTrace();
		}
		return syDbService;
	}

	private  String DBMapS = "//DBMAPS";
	private  String DBMapE = "//DBMAPE";

	public  void CreateDBMap() {
		String path = SysVal.PROJECT_PATH;
		String path_js = path + "/src/main/webapp/js/dbmap.js";
		if (new File(path_js).exists()) {
			int index = SmarteUntil.findLineFormFile(path_js, DBMapS);
			int end = SmarteUntil.findLineFormFile(path_js, DBMapE);
			int delI = end - index - 1;
			for (int i = 0; i < delI; i++) {
				SmarteUntil.removeLineFromFile(path_js, index + 1);
			}
			syDbService = getInstance();
			String dbmapStr = "var doroodo_db={";
			if (DbMap != null) {
				Iterator<String> iter = DbMap.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					String value = (String) DbMap.get(key);
					dbmapStr += key + ":'" + value + "',";
				}
				dbmapStr = dbmapStr.substring(0, dbmapStr.length() - 1);
				dbmapStr += "}";
				SmarteUntil.insertStringInFile(path_js, index + 1, dbmapStr);
				SmarteUntil.log("写入开发模式 dbmap信息");
			}
		}else if(new File(sysPath+"js/dbmap.js").exists()){
			path_js=sysPath+"js/dbmap.js";
			int index = SmarteUntil.findLineFormFile(path_js, DBMapS);
			int end = SmarteUntil.findLineFormFile(path_js, DBMapE);
			int delI = end - index - 1;
			for (int i = 0; i < delI; i++) {
				SmarteUntil.removeLineFromFile(path_js, index + 1);
			}
			syDbService = getInstance();
			String dbmapStr = "var doroodo_db={";
			if (DbMap != null) {
				Iterator<String> iter = DbMap.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					String value = (String) DbMap.get(key);
					dbmapStr += key + ":'" + value + "',";
				}
				dbmapStr = dbmapStr.substring(0, dbmapStr.length() - 1);
				dbmapStr += "}";
				SmarteUntil.insertStringInFile(path_js, index + 1, dbmapStr);
				SmarteUntil.log("写入运行模式 dbmap信息");
			}
		}
	}
}
