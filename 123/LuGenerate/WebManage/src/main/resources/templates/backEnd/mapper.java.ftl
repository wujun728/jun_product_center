package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
 * @Project ${cfg.project}
 * @Author: ${author}
 * @Date: ${cfg.createTime}
 * @Description: ${table.comment!} Dao
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>