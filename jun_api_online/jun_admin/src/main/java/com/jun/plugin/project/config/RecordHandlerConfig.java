package com.jun.plugin.project.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jun.plugin.common.base.interfaces.IRecordHandler;
import com.jun.plugin.common.constant.Constant;
import com.jun.plugin.common.service.HttpSessionService;
import com.jun.plugin.db.record.Record;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;


/**
 * mybatis plus 默认值配置
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Component
public class RecordHandlerConfig implements IRecordHandler {

    @Lazy
    @Resource
    HttpSessionService httpSessionService;

    @Override
    public String tableName() {
        return "all";
    }

    @Override
    public void insertFill(Record record) {
        if(ObjUtil.isNotNull(record.get("update_time"))){
            record.set("update_time", DateUtil.now());
        }
        if(ObjUtil.isNotNull(record.get("create_time"))){
            record.set("create_time", DateUtil.now());
        }
        if(ObjUtil.isNotNull(record.get("create_id"))){
            record.set("create_id", httpSessionService.getCurrentUserId());
        }
        if(ObjUtil.isNotNull(record.get("update_id"))){
            record.set("update_id", httpSessionService.getCurrentUserId());
        }
    }

    @Override
    public void updateFill(Record record) {
        if(ObjUtil.isNotNull(record.get("update_time"))){
            record.set("update_time", DateUtil.now());
        }
        if(ObjUtil.isNotNull(record.get("create_time"))){
            record.set("create_time", DateUtil.now());
        }
        if(ObjUtil.isNotNull(record.get("create_id"))){
            record.set("create_id", httpSessionService.getCurrentUserId());
        }
        if(ObjUtil.isNotNull(record.get("update_id"))){
            record.set("update_id", httpSessionService.getCurrentUserId());
        }
    }
}