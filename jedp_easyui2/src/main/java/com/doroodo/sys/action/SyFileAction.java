package com.doroodo.sys.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.doroodo.base.action.BaseAction;
import com.doroodo.config.SysVal;
import com.doroodo.base.model.DataGrid;
import com.doroodo.code.GeneratorProperties;
import com.doroodo.sys.model.SyFile;
import com.doroodo.sys.model.SyOrgan;
import com.doroodo.sys.service.SyFileService;
import com.doroodo.util.SmarteUntil;

@Controller
@ParentPackage(value = "sys")
@InterceptorRef("mydefault")
public class SyFileAction extends BaseAction {
	@Autowired
	private SyFileService syFileService;
	private SyFile syFile;
	private List<File> fileGroup;
	private String fileid;
	private String fileids="";
	private String sourceUrl="";
	private String pUrl = "";
	
	public String getpUrl() {
		return pUrl;
	}

	public void setpUrl(String pUrl) {
		this.pUrl = pUrl;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getFileids() {
		return fileids;
	}

	public void setFileids(String fileids) {
		this.fileids = fileids;
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

	@Action("/sys/syFile_Upload")
	public void syFileUpload(){
		if(fileGroup==null||fileGroupFileName==null){this.writeMsg(SysVal.NOFILE); return;}
		float maxUploadFileSize=Float.parseFloat(GeneratorProperties.getProperty("maxUploadFileSize"))*1024*1024;
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//设置日期格式
		List<SyFile> syFiles=new ArrayList<SyFile>();
		String msg=SysVal.UPFILE_SUC;
		try {
			for (int i = 0; i < fileGroup.size(); i++) {
				File f=fileGroup.get(i);
				if(f.length()>maxUploadFileSize){
					Map<String, String> map = new HashMap<String, String>();
					String error="文件【"+fileGroupFileName.get(i)+"】超过了系统最大上传限制"+GeneratorProperties.getProperty("maxUploadFileSize")+"MB";
					map.put("error", error);
					this.writeJson(map);
					return ;
				}
			}
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
				if(this.getFileid()!=null){
					syFile_.setFileid(this.getFileid());
				}
				syFileService.saveOrUpdate(syFile_);
				List<SyFile> syFiles_=syFileService.get(syFile_);
				if(syFiles_.size()==1){
					syFiles.add(syFiles_.get(0));
				}
			}
			Map m=new HashMap();
			m.put("info", SysVal.ADD_SUC);
			m.put("syFiles", syFiles);
			this.writeJson(m);
			//转pdf
//			String path = ServletActionContext.getRequest().getRealPath("/fileupload/");
//			for(int i=0;i<syFiles.size();i++){
//				SyFile syFile=syFiles.get(i);
//				String [] array = syFile.getSysname().split("[.]");
//				String destFlie=path+"/swf/"+syFile.getSysname().substring(0,syFile.getSysname().lastIndexOf("."))+".swf";
//				if(array[1].equals("doc")||array[1].equals("wps")){
//					String dUrl = path+"/"+syFile.getSysname().substring(0,syFile.getSysname().lastIndexOf("."))+".pdf";
//					int x = SmarteUntil.office2PDF(path+"/"+syFile.getSysname(), dUrl);
//					if(x==0){
//						SmarteUntil.pdf2SWF(dUrl, destFlie);
//					}
//				}
//				SmarteUntil.pdf2SWF(path+"/"+syFile.getSysname(), destFlie);
//			}
		} catch (Exception e) {
			msg=SysVal.UPFILE_ER;
		}finally{
			this.writeJson(msg);
		}
	}

	@Action("/sys/syFile_Add")
	public void syFileAdd() {
		syFileService.saveOrUpdate(syFile);
		writeMsg(SysVal.ADD_SUC);
	}
	
	@Action("/sys/syFile_List")
	public void syFileList() {
		if(this.getFileid()!=null){
			this.writeJson(syFileService.dataGrid(this.getPage(), this.getRows(),
					this.getSearchName(), this.getSearchKey(),"fileid='"+this.getFileid()+"'"));
		}
		if(this.getIds()!=""){
			DataGrid dg = new DataGrid();
			List<SyFile> l =  new ArrayList();
			String[] ids=this.getIds().split(",");
			for(int i=0;i<ids.length;i++){
				String id=ids[i];
				if(!id.trim().equalsIgnoreCase("")){
					SyFile syFile_=new SyFile();
					syFile_.setId(Integer.parseInt(id));
					List<SyFile> l_= syFileService.get(syFile_);
					if(l_.size()>0){
						l.add(l_.get(0));
					}
				}
			}
			dg.setTotal((long)l.size());
			dg.setRows(l);
			dg.setModelName("syFile");
			this.writeJson(dg);
		}else{
			this.writeJson(syFileService.dataGrid(this.getPage(), this.getRows(),
					this.getSearchName(), this.getSearchKey()));
		}
	}
	
	
	@Action("/sys/syFile_List_Null")
	public void syFileListNull() {
		DataGrid dg = new DataGrid();
		List l= new ArrayList();
		dg.setTotal((long)l.size());
		dg.setRows(l);
		dg.setModelName("syFile");
		this.writeJson(dg);
	}

	@Action("/sys/syFile_ListAll")
	public void syFileListAll() {
		this.writeJson(syFileService.listAll());
	}

	@Action("/sys/syFile_Delete")
	public void syFileDelete() {
		if (this.getIds().trim() == "")
			return;
		syFileService.delete(this.getIds());
		writeMsg(SysVal.DEL_SUC);
	}
	
	@Action("/sys/syFile_Delete_ByFileIds")
	public void syFileDeleteByFileIds() {
		String strFileids=this.getFileids().trim();
		if (strFileids.isEmpty())return;
		syFileService.deleteByFileIds(strFileids);
		writeMsg(SysVal.DEL_SUC);
	}

	@Action("/sys/syFile_Update")
	public void syFileUpdate() {
		if (this.ids.isEmpty() || this.fileid.isEmpty()) return;
		String[] ids_=ids.split(",");
		for(int i=0;i<ids_.length;i++)
		{
			syFile=new SyFile();
			syFile.setId(Integer.parseInt(ids_[i]));
			syFile=syFileService.get(syFile).get(0);
			syFile.setFileid(fileid);
			syFileService.saveOrUpdate(syFile);
		}
		writeMsg(SysVal.ADD_SUC);
	}

	@Action("/sys/syFile_Excel")
	public void syFileExcel() {
		String title = "Excel表";
		List<Object> list = syFileService.dataGrid(this.getPage(),
				this.getRows(), this.getSearchName(), this.getSearchKey())
				.getRows();// 获取数据
		super.excel(title, list);
	}

	@Action("/sys/syFile_Get_ByObj")  
	public void syFileGetByObj(){
		this.writeJson(syFileService.get(syFile));
	}
	
	@Action("/sys/syFile_Get_ById")  
	public void syFileGetById(){
		SyFile syFile_=new SyFile();
		syFile_.setId(Integer.parseInt(this.id));
		syFile_=syFileService.get(syFile_).get(0);
		if(syFile_==null){
			this.writeMsg(SysVal.GET_ER);
			return;
		}else{
			this.writeJson(syFile_);
		}
	}
	
	// 检查字段是否唯一
	private String isSingle(SyFile syFile, String fieldName, String fieldValue) {
		String result = null;
		List<SyFile> lsList = syFileService.get(syFile);
		if (syFileService.get(syFile).size() > 0) {
			result = fieldName + "[" + fieldValue + "]" + SysVal.READHASE;
			return result;
		}
		return result;
	}

}
