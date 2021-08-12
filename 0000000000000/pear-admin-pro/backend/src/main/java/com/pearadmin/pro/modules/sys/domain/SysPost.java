package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;

/**
 * 岗位模型
 *
 * Author: 就眠仪式
 * CreateTime: 2021/03/27
 * */

@Data
@Alias("SysPost")
@TableName("sys_post")
@EqualsAndHashCode(callSuper = true)
public class SysPost extends BaseDomain {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 名称
     * */
    @TableField("name")
    private String name;

    /**
     * 编码
     * */
    @TableField("code")
    private String code;

    /**
     * 排序
     * */
    @TableField("sort")
    private Integer sort;

    /**
     * 启用
     * */
    @TableField("enable")
    private Boolean enable;

    /**
     * 所属租户
     * */
    @TableField("tenant_id")
    private String tenantId;
}
