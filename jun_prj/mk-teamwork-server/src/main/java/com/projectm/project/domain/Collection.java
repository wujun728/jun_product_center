package com.projectm.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_collection")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Collection extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String type;
    private String source_code;
    private String member_code;
    private String create_time;
}
