package com.projectm.member.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@TableName("team_member")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member  extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String mobile;
    private String realname;
    private String create_time;
    private Integer status;
    private String last_login_time;
    private String sex;
    private String avatar;
    private String idcard;
    private Integer province;
    private Integer city;
    private Integer area;
    private String address;
    private String description;
    private String email;
    private String code;
    private String dingtalk_openid;
    private String dingtalk_unionid;
    private String dingtalk_userid;

    @TableField(exist = false)
    private String orgCode;
    @TableField(exist = false)
    private String account_id;
    @TableField(exist = false)
    private String is_owner;
    @TableField(exist = false)
    private String authorize;
    @TableField(exist = false)
    private String position;
    @TableField(exist = false)
    private String department;
    @TableField(exist = false)
    private List<String> nodes;
    @TableField(exist = false)
    private List<MemberAccount> memberAccountList;

}
