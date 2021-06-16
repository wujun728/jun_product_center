package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};

import com.lu.manage.core.common.exception.SysLogExcepetion;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.lu.manage.core.common.constant.SystemConstant;
import com.alibaba.fastjson.JSONArray;
import com.lu.manage.core.tips.SuccessTip;
import com.lu.manage.core.utils.ToolUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Project ${cfg.project}
 * @Author: ${author}
 * @Date: ${cfg.createTime}
 * @Description: ${table.comment!} 服务实现类
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public Object listPage(Map<String, Object> map) throws SysLogExcepetion {
        SuccessTip successTip = new SuccessTip();
        try {
            Page page = new Page(Integer.valueOf((String)map.get(SystemConstant.PAGE)), Integer.valueOf((String)map.get(SystemConstant.LIMIT)));
            QueryWrapper queryWrapper = new QueryWrapper();
            map.forEach((k, v) -> {
                if(!k.equals(SystemConstant.PAGE) && !k.equals(SystemConstant.LIMIT)){
                    if(k.equals(SystemConstant.SEARCH_WORDS)){
                        queryWrapper.like("name", v);
                    }else {
                        queryWrapper.eq(k, v);
                    }
                }
            });
            IPage<${entity}> ${entity?uncap_first}List = this.baseMapper.selectPage(page, queryWrapper);
            successTip.setCount(${entity?uncap_first}List.getTotal());
            successTip.setData(${entity?uncap_first}List.getRecords());
        } catch (Exception e) {
            throw new SysLogExcepetion(Thread.currentThread(), e);
        }
        return successTip;
    }

    @Override
    public Object list(Map<String, Object> map) throws SysLogExcepetion {
        List<${entity}> ${entity?uncap_first}List = new ArrayList<>();
        try {
            QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
            if(ToolUtil.isNotEmpty(map)){
                map.forEach((k, v) -> {
                    queryWrapper.eq(k, v);
                });
            }
            ${entity?uncap_first}List = this.baseMapper.selectList(queryWrapper);
        } catch (Exception e) {
            throw new SysLogExcepetion(Thread.currentThread(), e);
        }
        return ${entity?uncap_first}List;
    }

    @Override
    public Object addObj(${entity} ${entity?uncap_first}) throws SysLogExcepetion {
        try {
            return this.baseMapper.insert(${entity?uncap_first});
        } catch (Exception e) {
            throw new SysLogExcepetion(Thread.currentThread(), e);
        }
    }

    @Override
    public Object updateObj(${entity} ${entity?uncap_first}) throws SysLogExcepetion {
        try {
            return this.baseMapper.updateById(${entity?uncap_first});
        } catch (Exception e) {
            throw new SysLogExcepetion(Thread.currentThread(), e);
        }
    }

    @Override
    public Object deleteObj(String data) throws SysLogExcepetion {
        try {
            List<${entity}> ${entity?uncap_first}List = JSONArray.parseArray(data, ${entity}.class);
            List<String> ids = ${entity?uncap_first}List.stream().map(${entity}::getId).collect(Collectors.toList());
            return this.baseMapper.deleteBatchIds(ids);
        } catch (Exception e) {
            throw new SysLogExcepetion(Thread.currentThread(), e);
        }
    }

}
</#if>