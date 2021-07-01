package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@TableName("team_project_features")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFeatures  extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String	code;
    private String	name;
    private String	description;
    private String	create_time;
    private String	update_time;
    private String	organization_code;
    private String	project_code;
}
