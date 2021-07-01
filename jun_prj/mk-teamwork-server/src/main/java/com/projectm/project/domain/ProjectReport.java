package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_project_report")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectReport extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String project_code;
    private String date;
    private String content;
    private String create_time;
    private String update_time;
}
