package com.projectm.member.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_project_member")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember  extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String project_code;
    private String member_code;
    private String join_time;
    private Integer is_owner;
    private String authorize;
}
