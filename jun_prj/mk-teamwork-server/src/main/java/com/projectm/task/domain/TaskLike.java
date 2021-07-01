package com.projectm.task.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_task_like")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskLike extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String task_code;
    private String member_code;
    private String create_time;
}
