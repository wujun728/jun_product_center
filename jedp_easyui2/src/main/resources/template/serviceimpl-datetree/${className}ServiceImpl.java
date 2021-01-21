<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.impl;

import org.hibernate.Query;

<#include "/java_imports.include">

@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements ${className}Service {
	@Autowired
	private BaseDao<${className}> ${classNameLower}Dao;
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
	
	public DataGrid treeGrid(int page, int rows, String searchName,
			String searchKey,String year) {
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
		dg.setRows(listByYear(year));
		dg.setModelName("${classNameLower}");
		return dg;
	}
	
	public void saveOrUpdate(${className} ${classNameLower}) {
		${classNameLower}Dao.saveOrUpdate(${classNameLower});
	}
	
	public void delete(String ids) {
		String[] ids_=ids.split(",");
		for(int i=0;i<ids_.length;i++)
		{
			${classNameLower}Dao.delete(${classNameLower}Dao.get(${className}.class, Integer.parseInt(ids_[i])));
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
	
	public List<${className}> listByYear(String year) {
		List<${className}> l=new ArrayList();
		${className} sp=new ${className}();
		sp.setDoroodoDate(year+"年");		//DoroodoDate字段为日期字段，数据库默认日期字段名为DoroodoDate，也可根据实际情况进行更改sp.setDoroodoDate(year+"年");
		sp.setId(-Integer.parseInt(year));
		sp.setState("closed");
		l.add(sp);
		return l;
	}
	
	
	private List<${className}> queryTree(String begintime,String endtime){
		String hql = "from ${className} t where t.doroodoDate>='"+begintime+"'and t.doroodoDate<='"+endtime+"'";//DoroodoDate字段为日期字段，数据库默认日期字段名为DoroodoDate，也可根据实际情况进行更改
		List<${className}> l = ${classNameLower}Dao.find(hql);
		System.out.println(l.size()+"-----数据库查询出的list的长度");
		return l;
	}
	
	public int queryCount(String begintime,String endtime){
		//用到日期树时，请将下方的sql语句中的表对象名该为你所操作的表的对象，所以来的时间字段也需要做对应的更改
		String sql = "select count(*) from ${className} t where t.doroodoDate>='"+begintime+"'and t.doroodoDate<='"+endtime+"'";
		List<${className}> l = ${classNameLower}Dao.find(sql);      
	    return l.size();  
	}
	
	private List<${className}> getTreeList(String [] arr){
		List<${className}> l_=new ArrayList();
		String y = "";
		String m = "";
		String w = "";
		String datestr = "";
		String db = "";
		String de = "";
		List<${className}> l =new ArrayList();
		if(arr.length==3){
			y = arr[0];
			m = arr[1];
			w = arr[2];
			try {
				datestr = SmarteUntil.printfWeeks(w, m, y);
				String [] arr2 = datestr.split("!");
				l = queryTree(arr2[0], arr2[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			y = arr[0];
		}
		return l;
	}
	
	
	
	public List<${className}> listByDate(String year) {
		String[] strs=year.split(":");
		year=strs[0];
		List<${className}> l=new ArrayList();
		switch(strs.length){
			case 1:
				for(int i=1;i<=12;i++){
					${className} sp_=new ${className}();
					sp_.setDoroodoDate(year+"年"+i+"月");								//DoroodoDate字段为日期字段，数据库默认日期字段名为DoroodoDate，也可根据实际情况进行更改sp_.setDatetimes(year+"年"+i+"月");
					sp_.setId(-Integer.parseInt(year+i));
					sp_.setState("closed");
					l.add(sp_);
				}
				return l;			
			case 2:
				int k = 0;
				String datestr = "";
				String [] arr  = {};
				for(int i=1;i<=4;i++){
					${className} sp_=new ${className}();
					sp_.setDoroodoDate(year+"年"+strs[1]+"月第"+i+"周");									//DoroodoDate字段为日期字段，数据库默认日期字段名为DoroodoDate，也可根据实际情况进行更改sp_.setDatetimes(year+"年"+strs[1]+"月第"+i+"周");
					sp_.setId(-Integer.parseInt(year+strs[1]+i+k++));
					try {
						datestr =  SmarteUntil.printfWeeks(String.valueOf(i), strs[1], strs[0]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					arr = datestr.split("!");
					if(queryCount(arr[0],arr[1])>0){
						sp_.setState("closed");
					}else{
						sp_.setState("open");
					}
					l.add(sp_);
				}
				return l;
			case 3:
					return getTreeList(strs);
		}
		return l;
	}
	
}