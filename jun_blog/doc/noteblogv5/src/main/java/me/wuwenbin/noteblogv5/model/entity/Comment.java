package me.wuwenbin.noteblogv5.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;
import static java.time.LocalDateTime.now;

/**
 * created by Wuwenbin on 2018/7/15 at 11:52
 *
 * @author wuwenbin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    private Long id;
    @NotNull
    private String articleId;
    @NotNull
    private Long userId;
    private Long replyId;
    private String clearComment;
    @NotNull
    @Length(min = 1, max = 1000, message = "字数必须在1000字以内")
    private String comment;
    @Builder.Default
    private Boolean enable = TRUE;
    private String ipAddr;
    private String ipInfo;
    @Builder.Default
    private LocalDateTime post = now();
    private String userAgent;
    private Integer floor;

}
