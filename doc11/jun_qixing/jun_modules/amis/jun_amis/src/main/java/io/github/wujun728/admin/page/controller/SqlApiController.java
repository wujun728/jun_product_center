package io.github.wujun728.admin.page.controller;

import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.db.service.SqlInfoService;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.SqlApiContent;
import io.github.wujun728.admin.page.data.SqlApiContentArg;
import io.github.wujun728.admin.page.data.SqlInfo;
import io.github.wujun728.admin.page.data.SqlParam;
import io.github.wujun728.admin.util.TemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/sqlApi")
@Slf4j
public class SqlApiController {
    @Resource
    private JdbcService jdbcService;
    @Resource
    private SqlInfoService sqlInfoService;

    @RequestMapping("execute/{code}")
    public Result execute(@PathVariable String code,@RequestParam Map<String,Object> params){
        log.info("code:{},params:{}",code,params);
        Map<String, Object> sqlApi = jdbcService.findOne("select * from sql_api where code = ? ", code);
        if(sqlApi == null){
            return Result.error("接口不存在"+code);
        }

        String content = (String) sqlApi.get("content");
        SqlApiContent sqlApiContent = JSONUtil.toBean(content, SqlApiContent.class);

        List<SqlParam> apiParams = new ArrayList<>();
        getParams(sqlApiContent,apiParams,new HashSet<>());
        for(SqlParam sqlParam:apiParams){
            if(Whether.YES.equals(sqlParam.getMust())){
                return Result.error(sqlParam.getLabel()+"不能为空");
            }
        }
        Object page = params.get("page");
        Object data = execute(sqlApiContent, params,page != null);
        return Result.success(data);
    }

    private void getParams(SqlApiContent content, List<SqlParam> params, Set<String> names){
        String sqlCode = content.getSql();
        SqlInfo sqlInfo = sqlInfoService.getSqlInfo(sqlCode);
        List<SqlApiContentArg> args = content.getArgs();
        Set<String> argNames = new HashSet<>();
        for (SqlApiContentArg arg : args) {
            argNames.add(arg.getName());
        }
        List<SqlParam> sqlParams = sqlInfo.getSqlParams();
        for(SqlParam sqlParam:sqlParams){
            if(!argNames.contains(sqlParam.getName()) && !names.contains(sqlParam.getName())){
                params.add(sqlParam);
                names.add(sqlParam.getName());
            }
        }
        for (SqlApiContent prop : content.getProps()) {
            getParams(prop,params,names);
        }
    }

    private Object execute(SqlApiContent content,Map<String,Object> params,boolean openPage){

        String sqlCode = content.getSql();

        SqlInfo sqlInfo = sqlInfoService.getSqlInfo(sqlCode);
        String sql = sqlInfoService.getSql(sqlInfo);
        sql = TemplateUtil.getValue(sql,params);

        for (SqlApiContentArg arg : content.getArgs()) {
            String value = TemplateUtil.getValue(arg.getValue(), params);
            params.put(arg.getName(),value);
        }
        Integer count = null;
        if(openPage && "array".equals(content.getType())){
            Object page =  params.get("page");
            Object perPage =  params.get("perPage");
            if(page != null){
                perPage = perPage == null ? "10" : perPage;
                count = jdbcService.findOneForObject("select count(*) from (" + sql + ") t", params, Integer.class);
                int start = (Integer.parseInt(page.toString()) - 1) * Integer.parseInt(perPage.toString());
                if(start >= count || start < 0){
                    start = 0;
                }
                sql += " limit " + start+","+perPage;
            }
        }

        List<Map<String, Object>> list = jdbcService.find(sql, params);
        for(Map<String,Object> item:list){
            Map<String,Object> newParams = new HashMap<>();
            newParams.putAll(params);
            newParams.put(content.getName(),item);
            for (SqlApiContent prop : content.getProps()) {
                Object propValue = execute(prop, newParams,false);
                item.put(prop.getName(),propValue);
                newParams.put(prop.getName(),propValue);
            }
        }
        if("obj".equals(content.getType())){
            return list.isEmpty() ? null : list.get(0);
        }else{
            if(count != null){
                Map<String,Object> data = new HashMap<>();
                data.put("items",list);
                data.put("total",count);
                return data;
            }
        }
        return list;
    }
}
