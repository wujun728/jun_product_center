package com.projectm.task.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_task_stages_template")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskStagesTemplete extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String project_template_code;
    private String create_time;
    private Integer sort;
    private String code;
}
