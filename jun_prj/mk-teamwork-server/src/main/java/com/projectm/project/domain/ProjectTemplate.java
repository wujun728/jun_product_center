package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@TableName("team_project_template")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTemplate extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer sort;
    private String create_time;
    private String code;
    private String organization_code;
    private String cover;
    private String member_code;
    @TableField("is_system")
    private Integer is_system;
    @TableField(exist = false)
    private List<String> task_stages;
}
