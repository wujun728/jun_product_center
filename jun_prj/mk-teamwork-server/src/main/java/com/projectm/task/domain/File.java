package com.projectm.task.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_file")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String path_name;
    private String title;
    private String extension;
    @TableField("size")
    private Long fsize;
    private String object_type;
    private String organization_code;
    private String task_code;
    private String project_code;
    private String create_by;
    private String create_time;
    private Long downloads;
    private String extra;
    private Integer deleted;
    private String file_url;
    private String file_type;
    private String deleted_time;
	
	
    @TableField(exist = false)
    private String creatorName;
    @TableField(exist = false)
    private String fullName;
}
