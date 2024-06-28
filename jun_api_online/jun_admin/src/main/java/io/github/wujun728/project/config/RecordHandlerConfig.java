package io.github.wujun728.project.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import com.jfinal.plugin.activerecord.Record;
import io.github.wujun728.common.base.interfaces.IRecordHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * mybatis plus 默认值配置
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Component
public class RecordHandlerConfig implements IRecordHandler {

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
            record.set("create_id", StpUtil.getLoginIdAsString());
        }
        if(ObjUtil.isNotNull(record.get("update_id"))){
            record.set("update_id", StpUtil.getLoginIdAsString());
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
            record.set("create_id", StpUtil.getLoginIdAsString());
        }
        if(ObjUtil.isNotNull(record.get("update_id"))){
            record.set("update_id", StpUtil.getLoginIdAsString());
        }
    }
}