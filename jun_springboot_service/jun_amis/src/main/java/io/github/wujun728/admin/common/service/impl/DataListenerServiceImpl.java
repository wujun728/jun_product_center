package io.github.wujun728.admin.common.service.impl;

import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.common.constants.EventType;
import io.github.wujun728.admin.common.data.DataListener;
import io.github.wujun728.admin.common.service.DataListenerService;
import io.github.wujun728.admin.common.service.DataListenerTask;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.rbac.service.ApiService;
import io.github.wujun728.admin.util.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("dataListenerService")
public class DataListenerServiceImpl implements DataListenerService {
    @Resource
    private JdbcService jdbcService;
    @Resource
    private ApiService apiService;
    @Override
    public void newObj(String tableName, Map<String, Object> obj) {
        List<DataListener> list = jdbcService.find("select * from data_listener " +
                "where table_name =? " +
                "and FIND_IN_SET(?,event_type) > 0 " +
                "order by seq asc ",
                DataListener.class,
                tableName,
                EventType.NEW);
        Map<String,Object> context = new HashMap<>();
        context.put("obj",obj);
        context.put("tableName",tableName);
        context.put("eventType",EventType.NEW);
        SessionContext.putUserSessionParams(context);
        for(DataListener dataListener:list){
            this.call(dataListener,context);
        }
    }

    @Override
    public void deleteObj(String tableName, Map<String, Object> obj) {
        List<DataListener> list = jdbcService.find("select * from data_listener " +
                        "where table_name =? " +
                        "and FIND_IN_SET(?,event_type) > 0 " +
                        "order by seq asc ",
                DataListener.class,
                tableName,
                EventType.DELETE);
        Map<String,Object> context = new HashMap<>();
        context.put("obj",obj);
        context.put("tableName",tableName);
        context.put("eventType",EventType.DELETE);
        SessionContext.putUserSessionParams(context);
        for(DataListener dataListener:list){
            this.call(dataListener,context);
        }
    }

    @Override
    public void updateObj(String tableName, Map<String, Object> beforeObj, Map<String, Object> afterObj) {
        List<DataListener> list = jdbcService.find("select * from data_listener " +
                        "where table_name =? " +
                        "and FIND_IN_SET(?,event_type) > 0 " +
                        "order by seq asc ",
                DataListener.class,
                tableName,
                EventType.UPDATE);
        Map<String,Object> context = new HashMap<>();
        context.put("beforeObj",beforeObj);
        context.put("afterObj",afterObj);
        context.put("tableName",tableName);
        context.put("eventType",EventType.UPDATE);
        SessionContext.putUserSessionParams(context);
        for(DataListener dataListener:list){
            this.call(dataListener,context);
        }
    }

    @Override
    public void updateObjColumn(String tableName, String columnName, Map<String, Object> beforeObj, Map<String, Object> afterObj, Object beforeValue, Object afterValue) {
        List<DataListener> list = jdbcService.find("select * from data_listener " +
                        "where table_name =? " +
                        "and FIND_IN_SET(?,event_type) > 0 " +
                        "and column_name =? " +
                        "order by seq asc ",
                DataListener.class,
                tableName,
                EventType.UPDATE_COLUMN,
                columnName);
        Map<String,Object> context = new HashMap<>();
        context.put("beforeObj",beforeObj);
        context.put("afterObj",afterObj);
        context.put("tableName",tableName);
        context.put("beforeValue",beforeValue);
        context.put("afterValue",afterValue);
        context.put("eventType",EventType.UPDATE_COLUMN);
        SessionContext.putUserSessionParams(context);
        for(DataListener dataListener:list){
            this.call(dataListener,context);
        }
    }

    private void call(DataListener dataListener, Map<String, Object> context){
        if(StringUtils.isNotBlank(dataListener.getJavaBean())){
            DataListenerTask task = (DataListenerTask) SpringUtil.getBean(dataListener.getJavaBean());
            task.call(dataListener,context);
        }else if(StringUtils.isNotBlank(dataListener.getAfterApi())){
            apiService.call(dataListener.getAfterApi(),context);
        }
    }
}
