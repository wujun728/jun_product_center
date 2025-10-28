package io.github.wujun728.admin.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.BaseData;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.common.config.UserSession;
import io.github.wujun728.admin.common.data.GlobalLog;
import io.github.wujun728.admin.common.data.LogTable;
import io.github.wujun728.admin.common.service.DataListenerService;
import io.github.wujun728.admin.common.service.LogService;
import io.github.wujun728.admin.db.data.ColumnInfo;
import io.github.wujun728.admin.db.data.TableInfo;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.db.service.TableService;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.rbac.data.User;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    @Lazy
    private TableService tableService;
    @Resource
    @Lazy
    private JdbcService jdbcService;

    @Resource
    @Lazy
    private DbCacheServiceImpl dbCacheService;

    @Resource
    @Lazy
    private DataListenerService dataListenerService;
    @Override
    public void log(Map<String, Object> beforeObj, Map<String, Object> afterObj,String tableName) {
        if(beforeObj == null && afterObj == null){
            return;
        }
        if(tableName.equalsIgnoreCase("global_log")){
            return;
        }

        LogTable logTable = jdbcService.findOne("select * from log_table where lower(table_name) = ? ", LogTable.class, tableName);
        if(logTable == null){
            return;
        }

        GlobalLog globalLog = new GlobalLog();
        globalLog.setCreateTime(new Date());
        globalLog.setTableName(tableName.toLowerCase());
        UserSession session = SessionContext.getSession();
        if(session != null){
            Long userId = session.getUserId();
            globalLog.setUserId(userId);
            //TODO wujun
//            User user = jdbcService.getById(User.class, userId);
//            globalLog.setUserName(user.getName());
            globalLog.setUserName(String.valueOf(userId));
        }
        String operation = null;
        String now = DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        if(beforeObj == null && afterObj != null){
            operation = "创建";
            globalLog.setAfterValue(JSONUtil.toJsonPrettyStr(afterObj));
            globalLog.setRemark(StrUtil.format("{}在{}创建了记录",globalLog.getUserName(),now));
            globalLog.setOptionType(operation);
            globalLog.setRefId(Long.valueOf(afterObj.get("id").toString()));
            if(Whether.YES.equals(logTable.getSaveLog())){
                jdbcService.saveOrUpdate(globalLog);
            }
            dataListenerService.newObj(tableName,afterObj);
        }else if(beforeObj != null && afterObj == null){
            operation = "删除";
            globalLog.setBeforeValue(JSONUtil.toJsonPrettyStr(beforeObj));
            globalLog.setRemark(StrUtil.format("{}在{}删除了记录",globalLog.getUserName(),now));
            globalLog.setOptionType(operation);
            globalLog.setRefId(Long.valueOf(beforeObj.get("id").toString()));
            if(Whether.YES.equals(logTable.getSaveLog())){
                jdbcService.saveOrUpdate(globalLog);
            }
            dataListenerService.deleteObj(tableName,beforeObj);
            invalidCache(logTable,beforeObj);
        }else{
            operation = "修改";
            globalLog.setOptionType(operation);
            globalLog.setRefId(Long.valueOf(beforeObj.get("id").toString()));
            Result<TableInfo> tableInfo = tableService.get(tableName);
            List<ColumnInfo> columnInfos = tableInfo.getData().getColumnInfos();
            boolean isUpdate = false;
            for(ColumnInfo columnInfo:columnInfos){
                String field = StringUtil.toFieldColumn(columnInfo.getColumnName());
                Object beforeValue = beforeObj.get(field);
                Object afterValue = afterObj.get(field);
                if(beforeValue == null && afterValue == null){
                    continue;
                }
                if(beforeValue != null && afterValue != null && beforeValue.equals(afterValue)){
                    continue;
                }
                GlobalLog pLog = BeanUtil.copyProperties(globalLog, GlobalLog.class);
                pLog.setField(columnInfo.getColumnName());
                pLog.setBeforeValue(getValueStr(beforeValue));
                pLog.setAfterValue(getValueStr(afterValue));
                if(pLog.getBeforeValue().equals(pLog.getAfterValue())){
                    continue;
                }
                pLog.setRemark(StrUtil.format("{}在{}将字段{}的值从{}改为{}",pLog.getUserName(),now,columnInfo.getColumnComment(),beforeValue,afterValue));
                if(Whether.YES.equals(logTable.getSaveLog())){
                    jdbcService.saveOrUpdate(pLog);
                }
                dataListenerService.updateObjColumn(tableName,columnInfo.getColumnName(),beforeObj,afterObj,beforeValue,afterValue);
                isUpdate = true;
            }
            if(isUpdate){
                dataListenerService.updateObj(tableName,beforeObj,afterObj);
                invalidCache(logTable,beforeObj);
                invalidCache(logTable,afterObj);
            }
        }
    }

    private void invalidCache(LogTable logTable,Map<String,Object> obj){
        if(!Whether.YES.equals(logTable.getOpenCache()) || StringUtils.isBlank(logTable.getCacheKeyFields())){
            return;
        }

        String[] fields = logTable.getCacheKeyFields().split(",");
        String[] values = new String[fields.length];
        for(int i=0;i<fields.length;i++){
            values[i] = getValueStr(obj.get(StringUtil.toFieldColumn(fields[i])));
        }
        String dataKey = dbCacheService.getDataKey(logTable.getTableName(), StringUtil.concatStr(values, ","));
        dbCacheService.invalid(dataKey);
        log.info("清理缓存:{}",dataKey);
    }

    private String getValueStr(Object value){
        if(value == null){
            return "";
        }
        String format = "yyyy-MM-dd HH:mm:ss";
        value = Util.dateFormat(value,format);
        return (String) value;
    }

    @Override
    public void log(BaseData beforeObj, BaseData afterObj) {
        if(beforeObj == null && afterObj == null){
            return;
        }
        String tableName = StringUtil.toTableName((beforeObj == null ? afterObj : beforeObj).getClass().getSimpleName());
        this.log(BeanUtil.beanToMap(beforeObj),BeanUtil.beanToMap(afterObj),tableName);
    }
}
