<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import com.doroodo.base.model.DataGrid;

<#include "/java_imports.include">

public interface ${className}Service {
	public DataGrid dataGrid(int page,int rows, String searchName, String searchKey);
	public DataGrid treeGrid(int page,int rows, String searchName, String searchKey,String year);
	public void saveOrUpdate(${className} ${classNameLower});
	public void delete(String ids);
	public List<${className}> listAll();
	public List<${className}> get(${className} ${classNameLower});
	public List<${className}> listByYear(String year);
	public List<${className}> listByDate(String year);
}
