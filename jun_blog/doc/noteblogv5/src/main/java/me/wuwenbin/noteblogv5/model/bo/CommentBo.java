package me.wuwenbin.noteblogv5.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import me.wuwenbin.noteblogv5.model.entity.Comment;

/**
 * @author wuwen
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentBo extends Comment {

    private String articleId;
    private String nickname;
    private String avatar;
    private String title;
    private String role;
}
