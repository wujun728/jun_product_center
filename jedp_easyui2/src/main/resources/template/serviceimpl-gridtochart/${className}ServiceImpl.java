<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.impl;

import com.doroodo.base.service.impl.BaseServiceImpl;

<#include "/java_imports.include">

@Service("${classNameLower}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${className}> implements ${className}Service {
}