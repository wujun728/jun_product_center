package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.lu.manage.core.common.exception.SysLogExcepetion;
import java.util.Map;

/**
 * @Project ${cfg.project}
 * @Author: ${author}
 * @Date: ${cfg.createTime}
 * @Description: ${table.comment!} 服务类
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 分页查询
     */
    Object listPage(Map<String, Object> map) throws SysLogExcepetion;

    /**
     * 列表查询
     */
    Object list(Map<String, Object> map) throws SysLogExcepetion;

    /**
     * 添加
     */
    Object addObj(${entity} ${entity?uncap_first}) throws SysLogExcepetion;

    /**
     * 修改
     */
    Object updateObj(${entity} ${entity?uncap_first}) throws SysLogExcepetion;

    /**
     * 删除
     */
    Object deleteObj(String data) throws SysLogExcepetion;

}
</#if>