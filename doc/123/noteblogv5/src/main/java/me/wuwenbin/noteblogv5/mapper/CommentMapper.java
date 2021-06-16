package me.wuwenbin.noteblogv5.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.wuwenbin.noteblogv5.annotation.MybatisMapper;
import me.wuwenbin.noteblogv5.model.bo.CommentBo;
import me.wuwenbin.noteblogv5.model.bo.ReplyBo;
import me.wuwenbin.noteblogv5.model.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuwen
 */
@MybatisMapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 查询评论分页
     *
     * @param page
     * @param nickname
     * @param clearComment
     * @param articleIds
     * @param userId
     * @param enable
     * @return
     */
    IPage<CommentBo> findCommentPage(IPage<CommentBo> page,
                                     @Param("nickname") String nickname,
                                     @Param("clearComment") String clearComment,
                                     @Param("articleIds") List<String> articleIds,
                                     @Param("userId") Long userId,
                                     @Param("enable") boolean enable);

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
    int findMaxFloorByArticleId(@Param("articleId") String articleId);
}
