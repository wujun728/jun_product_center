package com.lu.single.modular.business.model;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("sk_article")
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("create_time")
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
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

    public static void main(String[] args) {
        String s = "53312119680518163Ｘ";
        System.out.println(ToDBC(s));
    }

    public static String ToDBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        String returnString = new String(c);
        return returnString;
    }

}
