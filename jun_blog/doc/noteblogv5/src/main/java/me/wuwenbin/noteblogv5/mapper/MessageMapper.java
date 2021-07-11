package me.wuwenbin.noteblogv5.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.wuwenbin.noteblogv5.annotation.MybatisMapper;
import me.wuwenbin.noteblogv5.model.bo.MessageBo;
import me.wuwenbin.noteblogv5.model.bo.MessageRankBo;
import me.wuwenbin.noteblogv5.model.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuwen
 */
@MybatisMapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询评论分页
     *
     * @param page
     * @param nickname
     * @param clearComment
     * @param enable
     * @return
     */
    IPage<MessageBo> findMessagePage(IPage<MessageBo> page,
                                     @Param("nickname") String nickname,
                                     @Param("clearComment") String clearComment,
                                     @Param("enable") boolean enable);

    /**
     * 查找最新的留言
     *
     * @return
     */
    MessageBo findLatestMessage();

    /**
     * 统计今日留言数量
     *
     * @return
     */
    long findTodayMessage();

    /**
     * 查找留言页的留言榜单
     * @return
     */
    List<MessageRankBo> findMessageRankBoList();
}
