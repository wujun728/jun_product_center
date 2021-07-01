package com.projectm.task.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@TableName("team_task_workflow_rule")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskWorkflowRule   extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    /**
    * 规则类型，0：任务分组，1：人员，2：条件
    */    
    private Integer type;
    private String object_code;
    /**
    * 场景。0：增加任务，1：被完成，2：被重做，3：设置执行人，（4：截止时间，5：优先级）
    */
    private Integer action;    
    private String create_time;
    private String update_time;
    private String workflow_code;
    private Integer sort;
}
