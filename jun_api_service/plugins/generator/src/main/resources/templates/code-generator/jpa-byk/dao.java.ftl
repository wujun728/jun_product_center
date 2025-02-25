<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.service;</#if>
<#if isAutoImport?exists && isAutoImport==true>
import org.springframework.stereotype.Service;

import ${packageName}.entity.${classInfo.className}Entity;
 import com.jun.plugin.common.base.IDAO;
</#if>
/**
 * @description ${classInfo.classComment}数据层
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
public interface ${classInfo.className}Dao extends  IDAO<Long,${classInfo.className}Entity>   {


}
