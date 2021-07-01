package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_project")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project  extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String cover;
    private String name;
    private String code;
    private String description;
    private String access_control_type;
    private String white_list;
    @TableField(exist = false)
    private Long order;
    private Integer deleted;
    private String template_code;
    private Double schedule;
    private String create_time;
    private String organization_code;
    private String deleted_time;
    @TableField("private")
    private Integer privated;
    private String prefix;
    private Integer open_prefix;
    private Integer archive;
    private String archive_time;
    private Integer open_begin_time;
    private Integer open_task_private;
    private String task_board_theme;
    private String begin_time;
    private String end_time;
    private Integer auto_update_schedule;

    @TableField(exist = false)
    private Integer collected;
    @TableField(exist = false)
    private String owner_name;
    @TableField(exist = false)
    private String owner_avatar;

}
