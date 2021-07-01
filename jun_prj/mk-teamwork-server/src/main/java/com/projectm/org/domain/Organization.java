package com.projectm.org.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.projectm.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@TableName("team_organization")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Organization extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String avatar;
    private String description;
    private String owner_code;
    private String create_time;
    private Integer personal;
    private String code;
    private String address;
    private Integer province;
    private Integer city;
    private Integer area;
}
