package com.ruoyi.web.core.config;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/*
 *元数据处理器,,处理自动填充问题
 *@author Ye
 *@create 2020/5/24 10:56
 */
@Slf4j//日志
@Component//组件
public class MyMetaObjectHandler implements MetaObjectHandler {

//插入时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入时间");
        //setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        this.setFieldValByName("createTime", DateUtil.date(), metaObject);
        this.setFieldValByName("updateTime", DateUtil.date(), metaObject);
    }
    //更新时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新时间");
        this.setFieldValByName("updateTime", DateUtil.date(), metaObject);
    }
}
