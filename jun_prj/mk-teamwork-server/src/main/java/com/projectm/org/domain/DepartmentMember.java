package com.projectm.org.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_department_member")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentMember extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String department_code;
    private String organization_code;
    private String account_code;
    private String join_time;
    private Integer is_principal;
    private Integer is_owner;
    private String authorize;
}
