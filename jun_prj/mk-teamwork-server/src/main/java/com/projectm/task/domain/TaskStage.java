package com.projectm.task.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@TableName("team_task_stages")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskStage extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String project_code;
    private Integer sort;
    private String description;
    private String create_time;
    private String code;
    private Integer deleted;

    @TableField(exist = false)
    private boolean tasksLoading = true;
    @TableField(exist = false)
    private  boolean fixedCreator = false;
    @TableField(exist = false)
    private  boolean showTaskCard = false;
    @TableField(exist = false)
    private List tasks = new ArrayList();
    @TableField(exist = false)
    private List doneTasks = new ArrayList();
    @TableField(exist = false)
    private List unDoneTasks = new ArrayList();
}
