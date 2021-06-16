package me.wuwenbin.noteblogv5.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import me.wuwenbin.noteblogv5.model.bo.MessageBo;
import me.wuwenbin.noteblogv5.model.bo.MessageRankBo;
import me.wuwenbin.noteblogv5.model.entity.Message;

import java.util.List;

/**
 * @author wuwen
 */
public interface MessageService extends IService<Message> {

    /**
     * 查找评论页面
     *
     * @param page
     * @param nickname
     * @param clearComment
     * @param enable
     * @return
     */
    IPage<MessageBo> findMessagePage(IPage<MessageBo> page, String nickname, String clearComment, boolean enable);

    /**
     * 查找最新的留言
     *
     * @return
     */
    MessageBo findLatestMessage();

    /**
     * 统计今日评论数量
     * @return
     */
    long findTodayMessage();

    /**
     * 查找留言页的留言榜单
     * @return
     */
    List<MessageRankBo> findMessageRankBoList();
}
