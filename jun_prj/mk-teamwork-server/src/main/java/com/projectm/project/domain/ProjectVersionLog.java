package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.common.CommUtils;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
@Getter
@TableName("team_project_version_log")
@Data
@ToString
@AllArgsConstructor
@Builder
public class ProjectVersionLog   extends BaseDomain implements Serializable {

    public ProjectVersionLog(){
        setCode(CommUtils.getUUID());
    }
    public Integer getId() {
        return id;
    }

    public ProjectVersionLog setId(Integer id) {
        this.id = id;return this;
    }

    public String getCode() {
        return code;
    }

    public ProjectVersionLog setCode(String code) {
        this.code = code;return this;
    }

    public String getMember_code() {
        return member_code;
    }

    public ProjectVersionLog setMember_code(String member_code) {
        this.member_code = member_code;return this;
    }

    public String getContent() {
        return content;
    }

    public ProjectVersionLog setContent(String content) {
        this.content = content;return this;
    }

    public String getRemark() {
        return remark;
    }

    public ProjectVersionLog setRemark(String remark) {
        this.remark = remark;return this;
    }

    public String getType() {
        return type;
    }

    public ProjectVersionLog setType(String type) {
        this.type = type;return this;
    }

    public String getCreate_time() {
        return create_time;
    }

    public ProjectVersionLog setCreate_time(String create_time) {
        this.create_time = create_time;return this;
    }

    public String getSource_code() {
        return source_code;
    }

    public ProjectVersionLog setSource_code(String source_code) {
        this.source_code = source_code;return this;
    }

    public String getProject_code() {
        return project_code;
    }

    public ProjectVersionLog setProject_code(String project_code) {
        this.project_code = project_code;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public ProjectVersionLog setIcon(String icon) {
        this.icon = icon;return this;
    }

    public String getFeatures_code() {
        return features_code;
    }

    public ProjectVersionLog setFeatures_code(String features_code) {
        this.features_code = features_code;return this;
    }

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String member_code;
    private String content;
    private String remark;
    private String type;
    private String create_time;
    private String source_code;
    private String project_code;
    private String icon;
    private String features_code;
    public  ProjectVersionLog(String memberCode,String content,String remark,String type,String createTime,String sourceCode,String projectCode,String icon,String featureCode){
        if(!CommUtils.isEmpty(memberCode))setMember_code(memberCode);
        if(!CommUtils.isEmpty(content))setContent(content);
        if(!CommUtils.isEmpty(remark))setRemark(remark);
        if(!CommUtils.isEmpty(type))setType(type);
        if(!CommUtils.isEmpty(createTime))setCreate_time(createTime);
        if(!CommUtils.isEmpty(sourceCode))setSource_code(sourceCode);
        if(!CommUtils.isEmpty(projectCode))setProject_code(projectCode);
        if(!CommUtils.isEmpty(icon))setIcon(icon);
        if(!CommUtils.isEmpty(featureCode))setFeatures_code(featureCode);
        setCode(CommUtils.getUUID());
    }
}
