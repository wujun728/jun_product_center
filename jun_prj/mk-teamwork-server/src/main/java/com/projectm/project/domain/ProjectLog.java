package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import com.projectm.member.domain.Member;
import lombok.*;

import java.io.Serializable;

@TableName("team_project_log")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProjectLog  extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String member_code;
    private String content;
    private String remark;
    private String type;
    private String create_time;
    private String source_code;
    private String action_type;
    private String to_member_code;
    private Integer is_comment;
    private String project_code;
    private String icon;
    private Integer is_robot;

    @TableField(exist = false)
    private Member member;

    public Integer getId() {
        return id;
    }

    public ProjectLog setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ProjectLog setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMember_code() {
        return member_code;
    }

    public ProjectLog setMember_code(String member_code) {
        this.member_code = member_code;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ProjectLog setContent(String content) {
        this.content = content;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public ProjectLog setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getType() {
        return type;
    }

    public ProjectLog setType(String type) {
        this.type = type;
        return this;
    }

    public String getCreate_time() {
        return create_time;
    }

    public ProjectLog setCreate_time(String create_time) {
        this.create_time = create_time;
        return this;
    }

    public String getSource_code() {
        return source_code;
    }

    public ProjectLog setSource_code(String source_code) {
        this.source_code = source_code;
        return this;
    }

    public String getAction_type() {
        return action_type;
    }

    public ProjectLog setAction_type(String action_type) {
        this.action_type = action_type;
        return this;
    }

    public String getTo_member_code() {
        return to_member_code;
    }

    public ProjectLog setTo_member_code(String to_member_code) {
        this.to_member_code = to_member_code;
        return this;
    }

    public Integer getIs_comment() {
        return is_comment;
    }

    public ProjectLog setIs_comment(Integer is_comment) {
        this.is_comment = is_comment;
        return this;
    }

    public String getProject_code() {
        return project_code;
    }

    public ProjectLog setProject_code(String project_code) {
        this.project_code = project_code;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public ProjectLog setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Integer getIs_robot() {
        return is_robot;
    }

    public ProjectLog setIs_robot(Integer is_robot) {
        this.is_robot = is_robot;
        return this;
    }
}
