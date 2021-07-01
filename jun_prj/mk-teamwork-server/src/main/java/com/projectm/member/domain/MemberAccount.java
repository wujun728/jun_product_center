package com.projectm.member.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@TableName("team_member_account")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberAccount extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String member_code;
    private String organization_code;
    private String department_code;
    private String authorize;
    private Integer is_owner;
    private String name;
    private String mobile;
    private String email;
    private String create_time;
    private String last_login_time;
    private Integer status;
    private String description;
    private String avatar;
    private String position;
    private String department;
    @TableField(exist = false)
    private List<String> nodeList;
    @TableField(exist = false)
    private List<Map<String, String>> departList;

}
