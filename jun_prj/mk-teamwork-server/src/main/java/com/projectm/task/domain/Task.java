package com.projectm.task.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import com.projectm.member.domain.Member;
import com.projectm.project.domain.Project;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@TableName("team_task")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("code")
    private String code;
    @TableField("project_code")
    private String project_code;
    @TableField("name")
    private String name;
    @TableField("pri")
    private Integer pri;
    @TableField(exist = false)
    private String priText;
    @TableField("execute_status")
    private String execute_status;
    @TableField("description")
    private String description;
    @TableField("create_by")
    private String create_by;
    @TableField("create_time")
    private String create_time;
    @TableField("assign_to")
    private String assign_to;
    @TableField("deleted")
    private Integer deleted;
    @TableField("stage_code")
    private String stage_code;
    @TableField("task_tag")
    private String task_tag;
    @TableField("done")
    private Integer done;
    @TableField("begin_time")
    private String begin_time;
    @TableField("end_time")
    private String end_time;
    @TableField("remind_time")
    private String remind_time;
    @TableField("pcode")
    private String pcode;
    @TableField(exist = false)
    private String pName;
    @TableField("sort")
    private Integer sort;
    @TableField("liked")
    private Integer liked;
    @TableField(exist = false)
    private Integer like;
    public Integer getLike(){
        return liked;
    }
    @TableField("star")
    private Integer star;
    @TableField("deleted_time")
    private String deleted_time;
    @TableField("private")
    private Integer privated;
    @TableField("id_num")
    private Integer id_num;
    @TableField("path")
    private String path;
    @TableField("schedule")
    private Integer schedule;
    @TableField("version_code")
    private String version_code;
    @TableField("features_code")
    private String features_code;
    @TableField("work_time")
    private Integer work_time;
    @TableField("status")
    private Integer status;

    @TableField(exist = false)
    private List<Task> childList;

    public Integer getPrivate(){
        return privated;
    }

    @TableField(exist = false)
    private Member executor;
    @TableField(exist = false)
    private Project projectInfo;
}
