package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class SysTreeDict {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Excel(name = "主键")
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 重写：分类名称
     */
    @Excel(name = "分类名称")
    private String name;

    /**
     * 编码
     */
    @Excel(name = "编码")
    private String code;

    /**
     * 类别(0：树型结构；1：平铺结构)
     */
    @Excel(name = "类别")
    private String struType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 租户ID
     */
    @Excel(name = "租户ID")
    private String tenantId;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 是否系统参数
     */
    @Excel(name = "图标地址")
    private String isSysParam;

}
