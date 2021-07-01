package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@TableName("team_source_link")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SourceLink  extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String source_type;
    private String source_code;
    private String link_type;
    private String link_code;
    private String organization_code;
    private String create_by;
    private String create_time;
    private Integer sort;

    @TableField(exist = false)
    private String title;
    @TableField(exist = false)
    private Map sourceDetail;
}
