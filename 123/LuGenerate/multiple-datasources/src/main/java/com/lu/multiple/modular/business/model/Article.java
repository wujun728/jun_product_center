package com.lu.multiple.modular.business.model;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "lu_article")
public class Article {

    private static final long serialVersionUID = 1L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Date createTime;
    private Date updateTime;
    private Long headerPid;
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
    private Integer thumbsUp;
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

}
