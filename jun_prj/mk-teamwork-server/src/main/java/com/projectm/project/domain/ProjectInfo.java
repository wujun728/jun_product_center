package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("team_project_info")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String value;
    private String description;
    private String create_time;
    private String update_time;
    private String organization_code;
    private String project_code;
    private Integer sort;
    private String code;
}
