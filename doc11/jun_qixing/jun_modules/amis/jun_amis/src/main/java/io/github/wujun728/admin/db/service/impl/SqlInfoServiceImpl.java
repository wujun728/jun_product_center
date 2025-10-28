package io.github.wujun728.admin.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.service.impl.AbstractCacheService;
import io.github.wujun728.admin.db.data.ColumnMeta;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.db.service.SqlInfoService;
import io.github.wujun728.admin.page.constants.ParamType;
import io.github.wujun728.admin.page.constants.SqlType;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.data.SqlInfo;
import io.github.wujun728.admin.page.data.SqlParam;
import io.github.wujun728.admin.page.data.SqlResult;
import io.github.wujun728.admin.page.service.FormEvent;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.TemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("sqlInfoService")
@Slf4j
public class SqlInfoServiceImpl extends AbstractCacheService<SqlInfo> implements SqlInfoService, FormEvent {
    @Resource
    private JdbcService jdbcService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void resultFields(SqlInfo sqlInfo) {

        Map<String, SqlParam> paramMap = new HashMap<>();
        sqlInfo.getSqlParams().clear();
        String sql = getSql(sqlInfo,paramMap,new HashSet<>());
        Collection<String> argNames = new HashSet<>(paramMap.keySet());

        log.info("最终sql:{}",sql);

        Map<String,Object> params = new HashMap<>();
        List<Object> values = new ArrayList<>();
        params.put("values",values);

        sql = TemplateUtil.getValue(sql,params);

        log.info("sql:{}",sql);
        log.info("values:{}",values);

        ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sqlInfo.getSqlInfo());
        List<String> names = ReflectUtil.invoke(parsedSql,"getParameterNames");
        log.info("names:{}",names);

        //加载参数
        argNames.addAll(names);
        for(SqlParam param:sqlInfo.getSqlParams()){
            paramMap.put(param.getName(),param);
        }
        argNames = new ArrayList<>(argNames);
        Collections.sort((ArrayList<String>)argNames);

        log.info("参数列表:{}",argNames);
        for(String paramName:argNames){
            if(paramMap.containsKey(paramName)){
                sqlInfo.getSqlParams().add(paramMap.get(paramName));
            }else{
                SqlParam param = new SqlParam();
                param.setName(paramName);
                param.setMust(Whether.YES);
                sqlInfo.getSqlParams().add(param);
            }
        }

        //加载结果集
        if(SqlType.QUERY.equals(sqlInfo.getSqlType())){
            Map<String, SqlResult> fieldMap = new HashMap<>();
            for(SqlResult field:sqlInfo.getSqlResults()){
                fieldMap.put(field.getField(),field);
            }
            sqlInfo.getSqlResults().clear();

            List<ColumnMeta> columnMetas = jdbcService.namedColumnMeta(sql);
            for(ColumnMeta columnMeta:columnMetas){
                if(fieldMap.containsKey(columnMeta.getColumnLabel())){
                    sqlInfo.getSqlResults().add(fieldMap.get(columnMeta.getColumnLabel()));
                    continue;
                }
                SqlResult field = new SqlResult();
                field.setField(columnMeta.getColumnLabel());
                field.setLabel(columnMeta.getColumnComment());

                if(columnMeta.getColumnClassName().equalsIgnoreCase(String.class.getCanonicalName())){
                    //字符串类型
                    field.setType(ParamType.STRING);
                }else if(columnMeta.getColumnClassName().toLowerCase().contains("date")){
                    field.setType(ParamType.DATE);
                    field.setFormat("yyyy-MM-dd");
                }else if(columnMeta.getColumnClassName().equalsIgnoreCase(Integer.class.getCanonicalName())){
                    field.setType(ParamType.INT);
                }else if(columnMeta.getColumnClassName().equalsIgnoreCase(Long.class.getCanonicalName())){
                    field.setType(ParamType.LONG);
                }else if(columnMeta.getColumnClassName().equalsIgnoreCase(Float.class.getCanonicalName())
                        || columnMeta.getColumnClassName().equalsIgnoreCase(Double.class.getCanonicalName())){
                    field.setType(ParamType.DOUBLE);
                }
                sqlInfo.getSqlResults().add(field);
            }
        }
    }

    @Override
    public String getSql(String code) {
        return getSql(getSqlInfo(code));
    }

    @Override
    public String getSql(SqlInfo sqlInfo) {
        return getSql(sqlInfo,new HashMap<>(),new HashSet<>());
    }

    @Override
    public String getSql(SqlInfo sqlInfo,Map<String,SqlParam> paramMap,Set<String> sqlCodes) {
        if(sqlCodes.contains(sqlInfo.getCode())){
            return null;
        }
        sqlCodes.add(sqlInfo.getCode());
        String sql = sqlInfo.getSqlInfo();
        for(SqlParam sqlParam:sqlInfo.getSqlParams()){
            if(!paramMap.containsKey(sqlParam.getName())){
                SqlParam cloneParam = new SqlParam();
                BeanUtil.copyProperties(sqlParam,cloneParam,false);
                cloneParam.setId(null);
                cloneParam.setSqlInfoId(null);
                paramMap.put(sqlParam.getName(),cloneParam);
            }
        }
        if(StringUtils.isNotBlank(sqlInfo.getRefSqlCodes())){
            String[] refCodes = StringUtil.splitStr(sqlInfo.getRefSqlCodes(), ",");
            for(String refCode:refCodes){
                if(sqlCodes.contains(refCode)){
                   continue;
                }
                SqlInfo refSqlInfo = getSqlInfo(refCode);
                if(refSqlInfo != null){
                    String refSql = getSql(refSqlInfo, paramMap,sqlCodes);
                    if(StringUtils.isNotBlank(refSql)){
                        String key1 = StrUtil.format("${}",refCode);
                        String key2 = StrUtil.format("${{}}",refCode);
                        while(sql.contains(key1) || sql.contains(key2)){
                            sql = sql.replace(key1,refSql);
                            sql = sql.replace(key2,refSql);
                        }
                    }
                }else{
                    log.error("{}关联sql不存在:{}",sqlInfo.getCode(),refCode);
                }
            }
        }
        return sql;
    }

    @Override
    public SqlInfo getSqlInfo(String code) {
        return get(code);
    }

    @Override
    protected SqlInfo load(String key) {
        SqlInfo sqlInfo = jdbcService.findOne(SqlInfo.class, SqlInfo.Fields.code, key);
        if(sqlInfo == null){
            return null;
        }
        List<SqlParam> sqlParams = jdbcService.find(SqlParam.class, SqlParam.Fields.sqlInfoId, sqlInfo.getId());
        sqlInfo.setSqlParams(sqlParams);
        List<SqlResult> sqlResults = jdbcService.find(SqlResult.class, SqlResult.Fields.sqlInfoId, sqlInfo.getId());
        sqlInfo.setSqlResults(sqlResults);
        return sqlInfo;
    }

    @Override
    public Result beforeSave(Map<String, Object> obj, String tableName, Form form) {
        Long id = (Long) obj.get("id");
        String code = (String) obj.get("code");
        if(id != null){
            SqlInfo oldSqlInfo = jdbcService.getById(SqlInfo.class, id);
            if(oldSqlInfo != null && !oldSqlInfo.getCode().equals(code)){
                super.invalid(oldSqlInfo.getCode());
            }
        }
        return null;
    }
}
