package com.ruoyi.citylife.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动对象 c_activity
 *
 * @author ruoyi
 * @date 2020-11-17
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
@TableName("c_activity")
public class CActivity implements Serializable {

private static final long serialVersionUID=1L;


    /** 活动id */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    /** 发布人id */
    @Excel(name = "发布人id")
    private String pbid;

    /** 活动标题 */
    @Excel(name = "活动标题")
    private String title;

    /** 举办时间 */
    @Excel(name = "举办时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;

    /** 活动地点 */
    @Excel(name = "活动地点")
    private String position;

    /** 活动标签 */
    @Excel(name = "活动标签")
    private String label;

    /** 主图 */
    @Excel(name = "主图")
    private String img;

    /** 活动详情 */
    @Excel(name = "活动详情")
    private String detail;

    /** 浏览数 */
    @Excel(name = "浏览数")
    private Integer views;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Integer collections;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Integer likes;

    /** 点踩数 */
    @Excel(name = "点踩数")
    private Integer unlikes;

    /** 评论数 */
    @Excel(name = "评论数")
    private Integer comments;

    /** 审核与否 */
    @Excel(name = "审核与否")
    private Boolean checked;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    @TableLogic
    private Boolean deleted;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
