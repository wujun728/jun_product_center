package com.lu.single.config.datasource;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @program LuBoot
 * @description: MybatisPlus自定义配置
 * @author: zhanglu
 * @create: 2019-11-05 17:15:00
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
