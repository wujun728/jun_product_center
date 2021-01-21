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
@ParentPackage(value = "sys")
@InterceptorRef("mydefault")
public class CxSbTerminalConfigAction extends BaseAction {
	@Autowired
	private CxSbTerminalConfigService cxSbTerminalConfigService;
	private CxSbTerminalConfig cxSbTerminalConfig;
	private String EXCEL_TITLE = "阀值配置";// 请输入导出的excel表表名
	private String tableHtml = "";
	private String tableTitle = "";
	protected String addrs = "";

	public String getTableHtml() {
		return tableHtml;
	}

	public String getAddrs() {
		return addrs;
	}

	public void setAddrs(String addrs) {
		this.addrs = addrs;
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

	public CxSbTerminalConfig getCxSbTerminalConfig() {
		return cxSbTerminalConfig;
	}

	public void setCxSbTerminalConfig(CxSbTerminalConfig cxSbTerminalConfig) {
		this.cxSbTerminalConfig = cxSbTerminalConfig;
	}

	@Action("/sys/cxSbTerminalConfig_Add")
	public void cxSbTerminalConfigAdd() {
		cxSbTerminalConfigService.saveOrUpdate(cxSbTerminalConfig);
		if (cxSbTerminalConfig.getId() != null) {
			Map m = new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbTerminalConfig.getId());
			this.writeJson(m);
		} else {
			writeMsg(SysVal.ADD_ER);
		}
	}

	@Action("/sys/cxSbTerminalConfig_Add_HasFiles")
	public void cxSbTerminalConfigAddHasFiles() {
		cxSbTerminalConfigService.saveOrUpdate(cxSbTerminalConfig);
		Map m = new HashMap();
		if (cxSbTerminalConfig.getId() != null) {
			m.put("info", SysVal.ADD_SUC);
			m.put("flowId", cxSbTerminalConfig.getId());
			m.put("fileid", "cxSbTerminalConfig-" + cxSbTerminalConfig.getId());
			this.writeJson(m);
		} else {
			m.put("info", SysVal.ADD_ER);
			this.writeJson(m);
		}
	}

	@Action("/sys/cxSbTerminalConfig_Delete_HasFiles")
	public void cxSbTerminalConfigDeleteHasFiles() {
		if (this.getIds().trim() == "")
			return;
		cxSbTerminalConfigService.delete(this.getIds());
		Map m = new HashMap();
		m.put("info", SysVal.DEL_SUC);
		String[] ids = this.getIds().split(",");
		String fileids = "";
		for (int i = 0; i < ids.length; i++) {
			String fileid = ids[i];
			if (!fileid.isEmpty()) {
				fileids += "cxSbTerminalConfig-" + fileid + ",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}

	@Action("/sys/cxSbTerminalConfig_List")
	public void cxSbTerminalConfigList() {
		if (cxSbTerminalConfig != null) {
			this.writeJson(cxSbTerminalConfigService.dataGrid(this.getPage(),
					this.getRows(), cxSbTerminalConfig));
		} else {
			this.writeJson(cxSbTerminalConfigService.dataGrid(this.getPage(),
					this.getRows(), this.getSearchName(), this.getSearchKey()));
		}
	}

	@Action("/sys/cxSbTerminalConfig_List_All")
	public void cxSbTerminalConfigListAll() {
		this.writeJson(cxSbTerminalConfigService.listAll());
	}

	@Action("/sys/cxSbTerminalConfig_ComboBox")
	public void cxSbTerminalConfigComboBox() {
		List<CxSbTerminalConfig> l = cxSbTerminalConfigService.listAll();
		List<comboBox> l_ = new ArrayList<comboBox>();
		for (int i = 0; i < l.size(); i++) {
			comboBox cb = new comboBox();
			CxSbTerminalConfig obj = l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());// 请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}

	@Action("/sys/cxSbTerminalConfig_Delete")
	public void cxSbTerminalConfigDelete() {
		if (this.getIds().trim() == "")
			return;
		cxSbTerminalConfigService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}

	@Action("/sys/cxSbTerminalConfig_BatchConfig")
	public void cxSbTerminalConfigBatchUpdate() {
		if (this.getIds().trim() == "")
			return;
		String leakage = cxSbTerminalConfig.getLeakageCurrent();
		String over = cxSbTerminalConfig.getOverCurrent();
		String time = cxSbTerminalConfig.getHandleTime();
		HashMap<String, String> _res = cxSbTerminalConfigService.batchConfig(
				this.getAddrs(), leakage, over, time);
		if (_res.get("code").equals("0")) {
			// cxSbTerminalConfigService.batchUpdate(this.getIds(), leakage,
			// over, time);

			for (String _id : this.getIds().split(",")) {
				CxSbTerminalConfig cxSbTerminalConfig = new CxSbTerminalConfig();
				cxSbTerminalConfig.setId(Integer.parseInt(_id));
				cxSbTerminalConfig = cxSbTerminalConfigService.get(
						cxSbTerminalConfig).get(0);
				cxSbTerminalConfig.setLeakageCurrent(leakage);
				cxSbTerminalConfig.setOverCurrent(over);
				cxSbTerminalConfig.setHandleTime(time);
				cxSbTerminalConfig.setConfigDate(new Date());
				cxSbTerminalConfigService.saveOrUpdate(cxSbTerminalConfig);
			}

			writeJson(_res);
		} else {
			writeJson(_res);
		}
	}

	@Action("/sys/cxSbTerminalConfig_Update")
	public void cxSbTerminalConfigUpdate() {
		if (cxSbTerminalConfig != null) {
			String leakage = cxSbTerminalConfig.getLeakageCurrent();
			String over = cxSbTerminalConfig.getOverCurrent();
			String time = cxSbTerminalConfig.getHandleTime();
			HashMap<String, String> _res = cxSbTerminalConfigService.config(
					cxSbTerminalConfig.getId() + "", leakage, over, time);
			if (_res.get("code").equals("0")) {
				cxSbTerminalConfig.setAddress(cxSbTerminalConfig.getId() + "");
				cxSbTerminalConfig.setPostion(cxSbTerminalConfig.getId() + "");
				cxSbTerminalConfig.setLine(cxSbTerminalConfig.getId() + "");
				cxSbTerminalConfig.setFactory(cxSbTerminalConfig.getId() + "");
				cxSbTerminalConfigService.saveOrUpdate(cxSbTerminalConfig);
				writeJson(_res);
			} else {
				writeJson(_res);
			}
		}

	}

	@Action("/sys/cxSbTerminalConfig_Excel")
	public void cxSbTerminalConfigExcel() {
		List<Object> list = new ArrayList();
		if (this.getExcelExportAll().equalsIgnoreCase("false")) {
			list = cxSbTerminalConfigService.dataGrid(this.getPage(),
					this.getRows(), this.getSearchName(), this.getSearchKey())
					.getRows();// 获取数据
		} else if (this.getExcelExportAll().equalsIgnoreCase("true")) {
			List l = cxSbTerminalConfigService.listAll();// 获取数据
			list = l;
		}
		super.excel(EXCEL_TITLE, list);
	}

	@Action("/sys/cxSbTerminalConfig_FormFile")
	public void cxSbTerminalConfigFormFile() {
		getSession().put("tableHtml", tableHtml);
		getSession().put("tableTitle", tableTitle);
	}

	@Action("/sys/cxSbTerminalConfig_Upload")
	public void cxSbTerminalConfigUpload() throws IOException {
		String msg = SysVal.UPDATA_SUC;
		List<File> fileGroup = this.getFileGroup();
		List<String> fileGroupFileName = this.getFileGroupFileName();
		if (fileGroup == null || fileGroupFileName == null) {
			this.writeMsg(SysVal.NOFILE);
			return;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// 设置日期格式
		try {
			Map<String, String> map = this.uploadFiles(fileGroup,
					fileGroupFileName, "");
			for (int i = 0; i < fileGroupFileName.size(); i++) {
				SyFile syFile_ = new SyFile();
				String fileName = fileGroupFileName.get(i);
				String sysName = map.get(fileName);
				String createTime = df.format(new Date());
				syFile_.setCreatetime(createTime);
				syFile_.setFilename(fileName);
				syFile_.setSysname(sysName);
				syFile_.setUserid(this.getLoginUserId());
				syFileService.saveOrUpdate(syFile_);
				Excel e = new Excel(fileName, new FileInputStream(
						fileGroup.get(i)));
				Table t = e.getSheet(0).getAsTable();
				String[] ps = t.getHeader();
				cxSbTerminalConfig = new CxSbTerminalConfig();
				for (int ei = 0; ei < t.getRowSize(); ei++) {// 读出行
					for (int ej = 0; ej < t.getColSize(); ej++) {// 读出列
						setPValue(cxSbTerminalConfig, ps[ej], t.getCell(ei, ej));
					}
					cxSbTerminalConfig.setId(null);
					cxSbTerminalConfigService.saveOrUpdate(cxSbTerminalConfig);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = SysVal.UPDATA_ER;
		} finally {
			writeMsg(msg);
		}
	}

	private void setPValue(CxSbTerminalConfig cxSbTerminalConfig, String p,
			String v) {
		Field field = null;
		try {
			field = cxSbTerminalConfig.getClass().getDeclaredField(p);
			field.setAccessible(true);
			if ("java.lang.integer".equalsIgnoreCase(field.getType().getName())) {
				field.set(cxSbTerminalConfig, Float.valueOf(v).intValue());
			} else {
				field.set(cxSbTerminalConfig, v);
			}
		} catch (Exception e) {
			System.out
					.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}

	@Action("/sys/cxSbTerminalConfig_Get_ById")
	public void cxSbTerminalConfigGetById() {
		CxSbTerminalConfig cxSbTerminalConfig = new CxSbTerminalConfig();
		cxSbTerminalConfig.setId(Integer.parseInt(this.getId()));
		cxSbTerminalConfig = cxSbTerminalConfigService.get(cxSbTerminalConfig)
				.get(0);
		if (cxSbTerminalConfig == null) {
			this.writeMsg(SysVal.GET_ER);
			return;
		} else {
			this.writeJson(cxSbTerminalConfig);
		}
	}

	@Action("/sys/cxSbTerminalConfig_Get_ByObj")
	public void cxSbTerminalConfigByObj() {
		this.writeJson(cxSbTerminalConfigService.get(cxSbTerminalConfig));
	}

	// 检查字段是否唯一
	private String isSingle(CxSbTerminalConfig cxSbTerminalConfig,
			String fieldName, String fieldValue) {
		String result = null;
		List<CxSbTerminalConfig> lsList = cxSbTerminalConfigService
				.get(cxSbTerminalConfig);
		if (cxSbTerminalConfigService.get(cxSbTerminalConfig).size() > 0) {
			result = fieldName + "[" + fieldValue + "]" + SysVal.READHASE;
			return result;
		}
		return result;
	}

}
