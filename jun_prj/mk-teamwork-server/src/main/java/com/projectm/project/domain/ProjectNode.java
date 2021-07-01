package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_project_node")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectNode  extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String node;
    private String title;
    private Integer is_menu;
    private Integer is_auth;
    private Integer is_login;
    private String create_at;
}
