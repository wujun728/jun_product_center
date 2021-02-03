package me.wuwenbin.noteblogv5.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import me.wuwenbin.noteblogv5.model.bo.CommentBo;
import me.wuwenbin.noteblogv5.model.bo.ReplyBo;
import me.wuwenbin.noteblogv5.model.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuwen
 */
public interface CommentService extends IService<Comment> {

    /**
     * 查找评论页面
     *
     * @param page
     * @param nickname
     * @param clearComment
     * @param articleIds
     * @param userId
     * @param enable
     * @return
     */
    IPage<CommentBo> findCommentPage(IPage<CommentBo> page, String nickname,
                                     String clearComment, List<String> articleIds,
                                     Long userId, boolean enable);

    /**
     * 查找最新的评论
     *
     * @return
     */
    CommentBo findLatestComment();

    /**
     * 统计今日评论数量
     *
     * @return
     */
    long findTodayComment();

    /**
     * 查找回复我的
     *
     * @param page
     * @param userId
     * @return
     */
    IPage<ReplyBo> findReplyPage(IPage<ReplyBo> page, @Param("userId") Long userId);

    /**
     * 查找谋篇文章评论最大的楼层
     *
     * @param articleId
     * @return
     */
    int findMaxFloorByArticleId(String articleId);
}
