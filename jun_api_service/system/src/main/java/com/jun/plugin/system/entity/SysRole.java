package com.jun.plugin.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseEntity;
import com.jun.plugin.system.vo.resp.DeptRespNodeVO;
import com.jun.plugin.system.vo.resp.PermissionRespNode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysRole extends BaseEntity implements Serializable {
    @TableId
    private String id;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String description;

    private Integer status;

    private Integer dataScope;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @TableField(exist = false)
    private List<PermissionRespNode> permissionRespNodes;
    @TableField(exist = false)
    private List<DeptRespNodeVO> deptRespNodes;

    @TableField(exist = false)
    private String startTime;

    @TableField(exist = false)
    private String endTime;

    @TableField(exist = false)
    private List<String> permissions;

    @TableField(exist = false)
    private List<String> depts;

}