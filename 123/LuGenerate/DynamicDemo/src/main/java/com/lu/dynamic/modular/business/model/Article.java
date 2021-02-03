package com.lu.dynamic.modular.business.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章列表
 * </p>
 *
 * @author lu
 * @since 2019-02-07
 */
@Data
@Accessors(chain = true)
@TableName("lu_article")
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    /**
     * 父id
     */
    @TableField("header_pid")
    private Long headerPid;
    /**
     * 作者id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 授权等级
     */
    private String authLevel;
    /**
     * 积分
     */
    private String points;
    /**
     * 浏览量
     */
    private Integer views;
    /**
     * 点赞数
     */
    @TableField("thumbs_up")
    private Integer thumbsUp;
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

}
