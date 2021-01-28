<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.impl;

<#include "/java_imports.include">

@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements ${className}Service {
	@Autowired
	private BaseDao<${className}> ${classNameLower}Dao;
	private final int SYSTREEID=-1000000;
	public DataGrid dataGrid(int page, int rows, String searchName,
			String searchKey) {
		DataGrid dg = new DataGrid();
		String hql = "from ${className} t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key";
			params.put("key", "%%" + searchKey.trim() + "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<${className}> l=null;
		if(rows==0 &&page==0){
			l = ${classNameLower}Dao.find(hql, params);
		}else{
			l = ${classNameLower}Dao.find(hql, params,page, rows);
		}
		dg.setTotal(${classNameLower}Dao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("${classNameLower}");
		return dg;
	}
	

	public void saveOrUpdate(${className} ${classNameLower}) {
		boolean isSave=true;
		String routeid="";
		${className} p=new ${className}();
		if(${classNameLower}.getPid()!=null){
			p.setId(${classNameLower}.getPid());
			List<${className}> ${className}s=this.get(p);
			if(${classNameLower}.getId()!=null){
				isSave=false;
			}
			if(${className}s.size()==1){
				p=${className}s.get(0);
				routeid=p.getRouteId();
				String routename=p.getRouteName();
				if(p.getRouteId()==null){routeid=p.getId().toString();}
				if(p.getRouteName()==null){routename=p.getId().toString().toString();}
				if(!isSave){//编辑
					${classNameLower}.setRouteId(routeid+"/"+${classNameLower}.getId());
				}
				${classNameLower}.setRouteName(routename+"/"+${classNameLower}.getId().toString());
			}
		}else{
			${classNameLower}.setPid(SYSTREEID);
		}
		${classNameLower}Dao.saveOrUpdate(${classNameLower});
		if(isSave){//新建
			if(routeid==""){
				${classNameLower}.setRouteName(${classNameLower}.getId().toString());
				${classNameLower}.setRouteId(${classNameLower}.getId().toString());
			}else{
				${classNameLower}.setRouteId(routeid+"/"+${classNameLower}.getId());
			}
			${classNameLower}Dao.saveOrUpdate(${classNameLower});
		}
		setChildren(${classNameLower});
	}
	
	public void delete(String ids) {
		String[] ids_=ids.split(",");
		for(int i=0;i<ids_.length;i++)
		{
			int id=Integer.parseInt(ids_[i]);
			${classNameLower}Dao.delete(${classNameLower}Dao.get(${className}.class, id));
			${className} ${classNameLower} =new ${className}();
			${classNameLower}.setId(id);
			deleteChildren(${classNameLower});
		}
		
	}

	public List<${className}> listAll() {
		String hql = "from ${className} t ";
		List<${className}> l = ${classNameLower}Dao.find(hql);
		return l;
	}

	public List<${className}> get(${className} ${classNameLower}) {
		String hql = "from ${className} t where";
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < ${classNameLower}.getClass().getMethods().length; i++) {
			Method f = ${classNameLower}.getClass().getMethods()[i];
			if (f.getName().startsWith("get")
					&& f.getParameterTypes().length == 0 
					&& (!f.getName().equalsIgnoreCase("getClass"))) {
				String fieldResult;
				String fieldName="";
				try {
					fieldResult = f.invoke(${classNameLower}, null) == null ? "" : f.invoke(${classNameLower}, null).toString();
					for (int j = 0; j < ${classNameLower}.getClass().getDeclaredFields().length; j++) {
						String fieldName_=${classNameLower}.getClass().getDeclaredFields()[j].getName();
						if(fieldName_.equalsIgnoreCase(f.getName().substring(3))){
							fieldName = fieldName_;
							break;
						}
					}
					
					if( !fieldResult.trim().equals("") && !fieldName.trim().isEmpty()){
						if("java.lang.integer".equalsIgnoreCase(f.getReturnType().getName())){
							hql+=" t."+fieldName+" = :"+fieldName+" and";
							params.put(fieldName, Integer.parseInt(fieldResult));
						}else{
							hql+=" t."+fieldName+" = :"+fieldName+" and";
							params.put(fieldName, fieldResult);
						}
					}
					
					System.out.println("返回字段："+fieldName+"  结果:"+fieldResult);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		if(hql.endsWith("and")){
			hql = hql.substring(0, hql.length()-3);
		}
		List<${className}> l = ${classNameLower}Dao.find(hql, params);
		return l;
	}
	
	public List<Tree> tree(String id) {
		String hql = "from ${className} t ";
		Map<String, Object> params = new HashMap<String, Object>();
		${className} ${classNameLower}=null;
		if(id==null || id.trim().isEmpty()){
			hql+="where t.pid = :id";
			params.put("id", SYSTREEID);
		}
		else{
			hql+="where t.pid = :id";
			params.put("id", Integer.parseInt(id.trim()));
		}
		List<${className}> ${className}s=null;
		try{
			${className}s=${classNameLower}Dao.find(hql,params);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(${className}s.size()<1) return null;
		List<Tree> list=new ArrayList<Tree>();
		for(int i=0;i<${className}s.size();i++){
			${classNameLower}=${className}s.get(i);
			Tree tree=new Tree();
			tree.setId(${classNameLower}.getId().toString());
			tree.setPid(${classNameLower}.getPid().toString());
			tree.setText(${classNameLower}.getId().toString());
			if(countById(${classNameLower}.getId().toString())>0){
				tree.setState("closed");
			}else{
				tree.setState("open");
			}
			list.add(tree);
		}
		return list;
	}
	
	private int countById(String id){
		String hql = "select count(*) as count from ${className} t where t.pid='"+id+"'";
		return ${classNameLower}Dao.get_(hql);
	}
	
	public void setChildren(${className} ${classNameLower}){
		if(${classNameLower}==null)return;
		String hql = "from ${className} t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql+="where t.pid = :id";
		params.put("id", ${classNameLower}.getId());
		List<${className}> ${classNameLower}s = ${classNameLower}Dao.find(hql, params);
		for(int i=0;i<${classNameLower}s.size();i++){
			${className} ${classNameLower}_=${classNameLower}s.get(i);
			${classNameLower}_.setRouteId(${classNameLower}.getRouteId()+"/"+${classNameLower}_.getId());
			${classNameLower}_.setRouteName(${classNameLower}.getRouteName()+"/"+${classNameLower}_.getId().toString());
			${classNameLower}Dao.saveOrUpdate(${classNameLower}_);
			setChildren(${classNameLower}_);
		}
	}
	
	public void deleteChildren(${className} ${classNameLower}){
		if(${classNameLower}==null) return;
		if(${classNameLower}.getId().toString().trim().isEmpty()) return;
		String hql = "from ${className} t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql+="where t.pid = :id";
		params.put("id", ${classNameLower}.getId());
		List<${className}> ${classNameLower}s = ${classNameLower}Dao.find(hql, params);
		for(int i=0;i<${classNameLower}s.size();i++){
			${className} ${classNameLower}_=${classNameLower}s.get(i);
			${classNameLower}Dao.delete(${classNameLower}_);
			deleteChildren(${classNameLower}_);
		}
	}

	public DataGrid dataGrid(
			${className} ${classNameLower}, int page,
			int rows, String searchName, String searchKey) {
		DataGrid dg = new DataGrid();
		String hql = "from ${className} t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key and t.routeId like :id";
			params.put("key", "%%" + searchKey.trim() + "%%");
			params.put("id","%%" +  ${classNameLower}.getId().toString().trim() + "%%");
		}else{
			hql+="where t.routeId like :id";
			params.put("id","%%" +  ${classNameLower}.getId().toString().trim() + "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<${className}> l =  null;
		if(rows==0 &&page==0){
			l = ${classNameLower}Dao.find(hql, params);
		}else{
			l = ${classNameLower}Dao.find(hql, params,page, rows);
		}
		dg.setTotal(${classNameLower}Dao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("${classNameLower}");
		return dg;
	}
}