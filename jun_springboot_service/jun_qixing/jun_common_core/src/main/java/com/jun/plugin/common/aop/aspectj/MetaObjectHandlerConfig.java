package com.jun.plugin.common.aop.aspectj;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jun.plugin.common.constant.Constant;


import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * mybatis plus 默认值配置
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Component
@Slf4j
@Primary
public class MetaObjectHandlerConfig implements MetaObjectHandler {

//    @Lazy
//    @Resource
//    HttpSessionService httpSessionService;

    @Override
    public void insertFill(MetaObject metaObject) {
        Date currentDate = new Date();
        String[] setterNames = metaObject.getSetterNames();
        HashSet<String> setterNameSet = new HashSet<>(Arrays.asList(setterNames));
        if (setterNameSet.contains("deleted")) {
            //默认未删除
            setFieldValByName("deleted", Constant.DATA_NOT_DELETED, metaObject);
        }
        if (setterNameSet.contains("createTime")) {
            //创建时间默认当前时间
            setFieldValByName("createTime", currentDate, metaObject);
        }
        if (setterNameSet.contains("createDate")) {
            //创建时间默认当前时间
            setFieldValByName("createDate", currentDate, metaObject);
        }
        if (setterNameSet.contains("createId")) {
            //创建时间默认当前时间
            setFieldValByName("createId", SecurityUtils.getUserId().toString(), metaObject);
        }
        if (setterNameSet.contains("editor")) {
        	setFieldValByName("editor", SecurityUtils.getUsername(), metaObject);
        }
        if (setterNameSet.contains("updateId")) {
            //创建时间默认当前时间
            setFieldValByName("updateId", SecurityUtils.getUserId().toString(), metaObject);
        }
        if (setterNameSet.contains("updateTime")) {
            //创建时间默认当前时间
            setFieldValByName("updateTime", currentDate, metaObject);
        }
        if (setterNameSet.contains("updateDate")) {
            //创建时间默认当前时间
            setFieldValByName("updateDate", currentDate, metaObject);
        }
        if (setterNameSet.contains("creator")) {
        	//创建时间默认当前时间
        	setFieldValByName("creator", SecurityUtils.getUsername(), metaObject);
        }
        log.info("start insert fill ....");
//        this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class);
//        this.strictInsertFill(metaObject, "createBy", String.class, userId);
//        this.strictInsertFill(metaObject, "createDeptId", String.class, userId);

        log.info("自动填充-insert");
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", Date.class, new Timestamp(System.currentTimeMillis()));
        this.strictInsertFill(metaObject, "operTime", Date.class, new Timestamp(System.currentTimeMillis()));
        this.strictInsertFill(metaObject, "loginTime", Date.class, new Timestamp(System.currentTimeMillis()));
        if (StringUtils.isNotNull(SecurityUtils.getAuthentication())) {
            this.strictInsertFill(metaObject, "createBy", String.class, SecurityUtils.getUsername());
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date currentDate = new Date();
        String[] setterNames = metaObject.getSetterNames();
        HashSet<String> setterNameSet = new HashSet<>(Arrays.asList(setterNames));
        if (setterNameSet.contains("updateTime")) {
            //创建时间默认当前时间
            setFieldValByName("updateTime", currentDate, metaObject);
        }
        if (setterNameSet.contains("updateDate")) {
            //创建时间默认当前时间
            setFieldValByName("updateDate", currentDate, metaObject);
        }
        if (setterNameSet.contains("updateId")) {
            Long userId = SecurityUtils.getUserId();
            //创建时间默认当前时间
            setFieldValByName("updateId", userId.toString(), metaObject);
        }
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictUpdateFill(metaObject, "operator", String.class, "张三");
        log.info("自动填充-update");
        // 起始版本 3.3.0(推荐)
//        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Timestamp(System.currentTimeMillis()));
//        if (StringUtils.isNotNull(SecurityUtils.getAuthentication())) {
//            this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtils.getUsername());
//        }
    }

    /**
     * 严格模式填充策略,默认有值不覆盖,如果提供的值为null也不填充
     * modify-author: fsd
     * modify-issue: 重写strictFillStrategy方法，解决null值不自动填充问题
     *
     * @param metaObject metaObject meta object parameter
     * @param fieldName  java bean property name
     * @param fieldVal   java bean property value of Supplier
     * @return this
     * @since 3.3.0
     */
    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }
}