package com.projectm.task.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_task_member")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskMember  extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String task_code;
    private Integer is_executor;
    private String member_code;
    private String join_time;
    private Integer is_owner;
}
