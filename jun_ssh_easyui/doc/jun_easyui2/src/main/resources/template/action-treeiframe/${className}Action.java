<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign excelName = table.excelName>   
package ${basepackage}.action;

<#include "/java_imports.include">

@Controller
@ParentPackage(value="sys")   
@InterceptorRef("mydefault")
public class ${className}Action extends BaseAction{
	@Autowired
	private ${className}Service ${classNameLower}Service;
	private ${className} ${classNameLower};
	private String EXCEL_TITLE = "${excelName}";//请输入导出的excel表表名
	private String tableHtml="";
	private String tableTitle="";
	private String id="";
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public ${className} get${className}(){
		return ${classNameLower};
	}
	
	public void set${className}(${className} ${classNameLower}){
		this.${classNameLower}=${classNameLower};
	}
	
	@Action("/sys/${classNameLower}_Add")
	public void ${classNameLower}Add(){
		${classNameLower}Service.saveOrUpdate(${classNameLower});
		if(${classNameLower}.getId()!=null){
			Map m=new HashMap();
			m.put("info", ADD_SUC);
			m.put("flowId", ${classNameLower}.getId());
			this.writeJson(m);
		}else{
			writeMsg(this.ADD_ER);
		}
	}
	
	@Action("/sys/${classNameLower}_Add_HasFiles")
	public void ${classNameLower}AddHasFiles(){
		${classNameLower}Service.saveOrUpdate(${classNameLower});
		Map m=new HashMap();
		if(${classNameLower}.getId()!=null){
			m.put("info", ADD_SUC);
			m.put("flowId", ${classNameLower}.getId());
			m.put("fileid", "${classNameLower}-"+${classNameLower}.getId());
			this.writeJson(m);
		}else{
			m.put("info", ADD_ER);
			this.writeJson(m);
		}
	}
	
	@Action("/sys/${classNameLower}_Delete_HasFiles")
	public void ${classNameLower}DeleteHasFiles(){
		if(this.getIds().trim()=="")return;
		${classNameLower}Service.delete(this.getIds());
		Map m=new HashMap();
		m.put("info", DEL_SUC);
		String[] ids=this.getIds().split(",");
		String fileids="";
		for(int i=0;i<ids.length;i++){
			String fileid=ids[i];
			if(!fileid.isEmpty()){
				fileids+= "${classNameLower}-"+fileid+",";
			}
		}
		m.put("fileids", fileids);
		this.writeJson(m);
	}
	
	@Action("/sys/${classNameLower}_List")
	public void ${classNameLower}List(){
		this.writeJson(${classNameLower}Service.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/${classNameLower}_List_ByPid")
	public void ${classNameLower}ListByPid(){
		this.writeJson(${classNameLower}Service.dataGrid(${classNameLower},this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()));
	}
	
	@Action("/sys/${classNameLower}_Get_Tree")
	public void ${classNameLower}GetTree(){
		this.writeJson(${classNameLower}Service.tree(id));
	}
	
	@Action("/sys/${classNameLower}_List_All")
	public void ${classNameLower}ListAll(){
		this.writeJson(${classNameLower}Service.listAll());
	}
	
	@Action("/sys/${classNameLower}_ComboBox")
	public void ${classNameLower}ComboBox(){
		List<${className}> l = ${classNameLower}Service.listAll();
		List<comboBox> l_=new ArrayList<comboBox>();
		for(int i=0;i<l.size();i++){
			comboBox cb = new comboBox();
			${className} obj=l.get(i);
			cb.setId(obj.getId().toString());
			cb.setText(obj.getId().toString());//请按需修改!
			l_.add(cb);
		}
		this.writeJson(l_);
	}
	
	@Action("/sys/${classNameLower}_Delete")
	public void ${classNameLower}Delete(){
		if(this.getIds().trim()=="")return;
		${classNameLower}Service.delete(this.getIds());
		writeMsg(this.DEL_SUC);
	}
	
	@Action("/sys/${classNameLower}_Update")
	public void ${classNameLower}Update(){
		if(${classNameLower}!=null) ${classNameLower}Service.saveOrUpdate(${classNameLower});
		writeMsg(this.EDIT_SUC);
	}
	
	@Action("/sys/${classNameLower}_Excel")
	public void ${classNameLower}Excel(){
		List<Object> list = new ArrayList();
		if(this.getExcelExportAll().equalsIgnoreCase("false")){
			list = ${classNameLower}Service.dataGrid(this.getPage(), this.getRows(), this.getSearchName(), this.getSearchKey()).getRows();//获取数据
		}else if(this.getExcelExportAll().equalsIgnoreCase("true")){
			List l = ${classNameLower}Service.listAll();//获取数据
			list=l;
		}
		super.excel(EXCEL_TITLE,list);
	}
	
	@Action("/sys/${classNameLower}_FormFile")
	public void ${classNameLower}FormFile(){
		getSession().put("tableHtml", tableHtml); 
		getSession().put("tableTitle", tableTitle); 
	}
	
	@Action("/sys/${classNameLower}_Upload")
	public void ${classNameLower}Upload() throws IOException{
		String msg=UPDATA_SUC;
		List<File> fileGroup=this.getFileGroup();
		List<String> fileGroupFileName=this.getFileGroupFileName();
		if(fileGroup==null||fileGroupFileName==null){this.writeMsg(NOFILE); return;}
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
				${classNameLower}=new ${className}();
				 for(int ei=0;ei<t.getRowSize();ei++){//读出行
					 for(int ej=0;ej<t.getColSize();ej++){//读出列
						 setPValue(${classNameLower},ps[ej],t.getCell(ei,ej));
					 }
					 ${classNameLower}.setId(null);
					 ${classNameLower}Service.saveOrUpdate(${classNameLower});
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=UPDATA_ER;
		}finally{
			writeMsg(msg);
		}
	}
	
	private void setPValue(${className} ${classNameLower},String p,String v){
		Field field = null;
		try {
			field = ${classNameLower}.getClass().getDeclaredField(p);
			field.setAccessible(true);  
			if("java.lang.integer".equalsIgnoreCase(field.getType().getName())){
				field.set(${classNameLower},Float.valueOf(v).intValue());
			}else{
				field.set(${classNameLower},v);  
			}
		} catch (Exception e) {
			System.out.println("++++++++++++++++++++您需要转下类型，doroodo平台缺少您说的这个类型，烦请加下，或者联系下维护人员!++++++++++++++++++++");
			e.printStackTrace();
		}
	}
	
	@Action("/sys/${classNameLower}_Get_ById")  
	public void ${classNameLower}GetById(){
		${className} ${classNameLower}=new ${className}();
		${classNameLower}.setId(Integer.parseInt(this.getId()));
		${classNameLower}=${classNameLower}Service.get(${classNameLower}).get(0);
		if(${classNameLower}==null){
			this.writeMsg(GET_ER);
			return;
		}else{
			this.writeJson(${classNameLower});
		}
	}
	
	@Action("/sys/${classNameLower}_Get_ByObj")  
	public void ${classNameLower}ByObj(){
		this.writeJson(${classNameLower}Service.get(${classNameLower}));
	}
	
	//检查字段是否唯一
	private String isSingle(${className} ${classNameLower},String fieldName,String fieldValue){
		String result=null;
		List<${className}> lsList = ${classNameLower}Service.get(${classNameLower});
		if(${classNameLower}Service.get(${classNameLower}).size()>0) {
			result=fieldName+"["+fieldValue+"]"+READHASE;
			return result;
		}
		return result;
	}

}
