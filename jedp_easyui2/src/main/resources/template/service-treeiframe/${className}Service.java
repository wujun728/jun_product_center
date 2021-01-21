<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

<#include "/java_imports.include">

public interface ${className}Service {
	public DataGrid dataGrid(int page,int rows, String searchName, String searchKey);
	public DataGrid dataGrid(${className} ${classNameLower}, int page, int rows, String searchName,
			String searchKey);
	public void saveOrUpdate(${className} ${classNameLower});
	public void delete(String ids);
	public List<${className}> listAll();
	public List<${className}> get(${className} ${classNameLower});
	public List<Tree> tree(String id);
}
