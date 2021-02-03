package me.wuwenbin.noteblogv5.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.wuwenbin.noteblogv5.mapper.MessageMapper;
import me.wuwenbin.noteblogv5.model.bo.MessageBo;
import me.wuwenbin.noteblogv5.model.bo.MessageRankBo;
import me.wuwenbin.noteblogv5.model.entity.Message;
import me.wuwenbin.noteblogv5.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wuwen
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public IPage<MessageBo> findMessagePage(IPage<MessageBo> page, String nickname, String clearComment, boolean enable) {
        return messageMapper.findMessagePage(page, nickname, clearComment, enable);
    }

    @Override
    public MessageBo findLatestMessage() {
        return messageMapper.findLatestMessage();
    }

    @Override
    public long findTodayMessage() {
        return messageMapper.findTodayMessage();
    }

    @Override
    public List<MessageRankBo> findMessageRankBoList() {
        return messageMapper.findMessageRankBoList();
    }
}
