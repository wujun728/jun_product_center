package com.projectm.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_notify")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notify  extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String type;
    @TableField("`from`")
    private String from;
    @TableField("`to`")
    private String to;
    private String create_time;
    private Integer is_read;
    private String read_time;
    private String send_data;
    private String finally_send_time;
    private String send_time;
    private String action;
    private String terminal;
    private String from_type;
    private String avatar;
    private String source_code;
}
