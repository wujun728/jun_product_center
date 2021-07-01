package com.projectm.task.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@TableName("team_task_workflow")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskWorkflow  extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String name;
    private String create_time;
    private String update_time;
    private String organization_code;
    private String project_code;
    @TableField(exist = false)
    private List<TaskWorkflowRule> workflowRuleList;
}
