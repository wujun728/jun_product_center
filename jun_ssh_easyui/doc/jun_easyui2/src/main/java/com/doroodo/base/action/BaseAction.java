package com.doroodo.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.doroodo.base.util.excelTools.ExcelUtils;
import com.doroodo.base.util.excelTools.JsGridReportBase;
import com.doroodo.base.util.excelTools.TableData;
import com.doroodo.sys.model.SyFile;
import com.doroodo.config.*;
import com.doroodo.sys.service.SyFileService;
import com.doroodo.util.SmarteUntil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	public SyFileService syFileService;
	private SyFile syFile;
	private List<File> fileGroup;
	private String fileid;
	private String excelExportAll = "false";// 页面导出，true导出全部数据，false导出当前页数据

	public String getExcelExportAll() {
		return excelExportAll;
	}

	public void setExcelExportAll(String excelExportAll) {
		this.excelExportAll = excelExportAll;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	private List<String> fileGroupFileName;

	public List<String> getFileGroupFileName() {
		return fileGroupFileName;
	}

	public void setFileGroupFileName(List<String> fileGroupFileName) {
		this.fileGroupFileName = fileGroupFileName;
	}

	public List<String> getFileGroupContentType() {
		return fileGroupContentType;
	}

	public void setFileGroupContentType(List<String> fileGroupContentType) {
		this.fileGroupContentType = fileGroupContentType;
	}

	private List<String> fileGroupContentType;

	public SyFile getSyFile() {
		return syFile;
	}

	public List<File> getFileGroup() {
		return fileGroup;
	}

	public void setFileGroup(List<File> fileGroup) {
		this.fileGroup = fileGroup;
	}

	public void setSyFile(SyFile syFile) {
		this.syFile = syFile;
	}

	private int page = 0;
	private int rows = 0;
	private String searchKey = "";
	private String searchName = "";
	protected String id = "";
	protected String ids = "";
	private String hearders = "";
	private String fields = "";
	private File uploadFile; // 上传的文件
	private String uploadFileFileName; // 文件名称
	private String uploadFileContentType; // 文件类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getHearders() {
		return hearders;
	}

	public void setHearders(String hearders) {
		this.hearders = hearders;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void writeText(String str) {
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			getResponse().getWriter().write(str);
			getResponse().getWriter().flush();
			getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object,"yyyy-MM-dd HH:mm:ss");
			getResponse().setContentType("text/html;charset=utf-8");
			getResponse().setHeader("Access-Control-Allow-Origin", "*");
			getResponse().getWriter().write(json);
			getResponse().getWriter().flush();
			getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeMsg(String msg) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("info", msg);
		this.writeJson(map);
	}

	public String getLoginUserName() {
		return (String) this.getSession().get("username");
	}

	public String getLoginUserId() {
		return (String) this.getSession().get("userid");
	}

	public String getLoginUserRole() {
		return (String) this.getSession().get("userrole");
	}

	public void isLogin() {
		String user = getLoginUserName();
		Map<String, Comparable> map = new HashMap();
		if (user != null) {
			map.put("info", SysVal.LOGIN_YES);
			map.put("islogin", 1);
		} else {
			map.put("info", SysVal.LOGIN_NO);
			map.put("islogin", 0);
		}
		this.writeJson(map);
	}

	public void isErr() {
		this.writeText(SysVal.OPT_ERROR);
	}

	public  void excel(String title, List<Object> list) {
		if (list == null)return;
		this.getResponse().setContentType("application/msexcel;charset=GBK");
		String[] hearders = null;
		String hearderString = "";
		try {
			hearderString = URLDecoder.decode(this.getHearders(), "utf-8");
			hearders = hearderString.split(",");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}// 表头数组
		String[] fields = this.getFields().split(",");
		TableData td = ExcelUtils.createTableData(list,
				ExcelUtils.createTableHeader(hearders), fields);
		try {
			JsGridReportBase report = new JsGridReportBase(this.getRequest(),
					this.getResponse());
			String user = (String) this.getSession().get("username");
			report.exportToExcel(title, user, td);
		} catch (Exception e) {
			e.toString();
		}
	}

	public void fileUpload(String uploadDir) throws IOException {
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/" + uploadDir);
		if (uploadFile != null) {
			File savefile = new File(new File(realpath), uploadFileFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(uploadFile, savefile);
			this.writeMsg(SysVal.FIle_UPLOAD_SUC);
		} else {
			this.writeMsg(SysVal.FIle_UPLOAD_ER);
		}
	}

	public Map<String, String> uploadFiles(List<File> fileGroup,
			List<String> fileGroupFileName, String dirs) throws Exception {
		// 获取文件存储路径
		String path = ServletActionContext.getRequest().getRealPath("/fileupload/");
		Map map = new HashMap<String, String>();
		if (!dirs.isEmpty())
			(new File(path + dirs)).mkdirs();
		for (int i = 0; i < fileGroup.size(); i++) {
			String sysFileNameString = dirs
					+ (new SimpleDateFormat("yyyyMMddHHmmssSSS")
							.format(new Date()));
			sysFileNameString += fileGroupFileName.get(i).substring(
					fileGroupFileName.get(i).lastIndexOf("."));
			OutputStream os = new FileOutputStream(new File(path,
					sysFileNameString));
			map.put(fileGroupFileName.get(i), sysFileNameString);
			InputStream is = new FileInputStream(fileGroup.get(i));
			byte[] buf = new byte[1024];
			int length = 0;
			while (-1 != (length = is.read(buf))) {
				os.write(buf, 0, length);
			}
			is.close();
			os.close();
		}
		return map;
	}
}
