package me.wuwenbin.noteblogv5.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

import static cn.hutool.core.util.RandomUtil.randomInt;

/**
 * @author wuwen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    @TableId(type = IdType.INPUT)
    private String id;
    @Builder.Default
    private Boolean appreciable = Boolean.FALSE;
    @Builder.Default
    private Integer approveCnt = randomInt(6, 169);
    private Long authorId;
    @Builder.Default
    private Boolean commented = Boolean.FALSE;
    @Builder.Default
    private Boolean reprinted = Boolean.FALSE;
    @NotEmpty(message = "文章内容不能为空")
    private String content;
    private String cover;
    @Builder.Default
    private Boolean draft = Boolean.TRUE;
    private String mdContent;
    @TableField("`post`")
    private Date post;
    @TableField("`modify`")
    private Date modify;
    private String summary;
    private String textContent;
    @NotEmpty(message = "文章标题不能为空")
    private String title;
    @Builder.Default
    @TableField("`top`")
    private Integer top = 0;
    private String urlSeq;
    @Builder.Default
    private Integer views = randomInt(666, 1609);


}
