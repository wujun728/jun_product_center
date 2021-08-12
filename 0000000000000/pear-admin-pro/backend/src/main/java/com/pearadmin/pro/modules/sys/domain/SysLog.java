package com.pearadmin.pro.modules.sys.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.aop.enums.Action;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 日志模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */

@Data
@Alias("SysLog")
@TableName("sys_log")
@EqualsAndHashCode(callSuper = true)
public class SysLog extends BaseDomain {

    /**
     * 编号
     * */
    @TableId("id")
    @ExcelProperty("编号")
    private String id;

    /**
     * 标题
     * */
    @TableField("title")
    @ExcelProperty("标题")
    private String title;

    /**
     * 描述
     * */
    @TableField("`describe`")
    @ExcelProperty("描述")
    private String describe;

    /**
     * 操作人
     * */
    @TableField("operator")
    @ExcelProperty("操作人")
    private String operator;

    /**
     * 操作地址
     * */
    @TableField("address")
    @ExcelProperty("地址")
    private String address;

    /**
     * 操作
     * */
    @TableField("action")
    @ExcelIgnore
    private Action action;

    /**
     * 浏览器
     * */
    @TableField("browser")
    @ExcelProperty("浏览器")
    private String browser;

    /**
     * 方法
     * */
    @TableField("method")
    @ExcelProperty("方法")
    private String method;

    /**
     * 接口
     * */
    @TableField("url")
    @ExcelProperty("接口")
    private String url;

    /**
     * 请求方式
     * */
    @TableField("type")
    @ExcelProperty("请求方式")
    private String type;

    /**
     * 参数
     * */
    @TableField("params")
    @ExcelProperty("参数")
    private String params;

    /**
     * 返回值
     * */
    @TableField("result")
    @ExcelProperty("返回值")
    private String result;

    /**
     * 状态
     * */
    @TableField("state")
    @ExcelProperty("状态")
    private Boolean state;

    /**
     * 异常信息
     * */
    @TableField("error")
    @ExcelProperty("异常信息")
    private String error;

    /**
     * 系统
     * */
    @TableField("`system`")
    @ExcelProperty("操作系统")
    private String system;

}
