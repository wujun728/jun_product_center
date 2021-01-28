<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import com.doroodo.base.service.BaseService;
<#include "/java_imports.include">

public interface ${className}Service extends BaseService<${className}> {
}
