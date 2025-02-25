package com.jun.plugin.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限菜单
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class SysPermission implements Serializable {


    @TableId
    private String id;

    @NotBlank(message = "菜单权限名称不能为空")
    private String name;

    private String perms;

    @TableField("url")
    private String url;
    
    @TableField("url")
    private String href;

    private String icon;

    private String target;

    @NotNull(message = "所属菜单不能为空")
    private String pid;

    private Integer orderNum;

    @NotNull(message = "菜单权限类型不能为空")
    private Integer type;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @TableField(exist = false)
    private String pidName;

}