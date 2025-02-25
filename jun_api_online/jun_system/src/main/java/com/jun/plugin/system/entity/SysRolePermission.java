package com.jun.plugin.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class SysRolePermission implements Serializable {
    @TableId
    private String id;

    private String roleId;

    private String permissionId;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

}