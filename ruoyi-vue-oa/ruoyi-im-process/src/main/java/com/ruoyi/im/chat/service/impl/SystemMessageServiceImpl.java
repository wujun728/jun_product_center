package com.ruoyi.im.chat.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.im.chat.mapper.SystemMessageMapper;
import com.ruoyi.im.chat.domain.SystemMessage;
import com.ruoyi.im.chat.service.ISystemMessageService;

/**
 * im系统消息Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class SystemMessageServiceImpl implements ISystemMessageService {
    @Autowired
    private SystemMessageMapper systemMessageMapper;

    /**
     * 查询im系统消息
     * 
     * @param id im系统消息主键
     * @return im系统消息
     */
    @Override
    public SystemMessage selectSystemMessageById(String id) {
        return systemMessageMapper.selectSystemMessageById(id);
    }

    /**
     * 查询im系统消息列表
     * 
     * @param systemMessage im系统消息
     * @return im系统消息
     */
    @Override
    public List<SystemMessage> selectSystemMessageList(SystemMessage systemMessage) {
        return systemMessageMapper.selectSystemMessageList(systemMessage);
    }

    /**
     * 新增im系统消息
     * 
     * @param systemMessage im系统消息
     * @return 结果
     */
    @Override
    public int insertSystemMessage(SystemMessage systemMessage) {
        systemMessage.setCreateTime(DateUtils.getNowDate());
        return systemMessageMapper.insertSystemMessage(systemMessage);
    }

    /**
     * 修改im系统消息
     * 
     * @param systemMessage im系统消息
     * @return 结果
     */
    @Override
    public int updateSystemMessage(SystemMessage systemMessage) {
        return systemMessageMapper.updateSystemMessage(systemMessage);
    }

    /**
     * 批量删除im系统消息
     * 
     * @param ids 需要删除的im系统消息主键
     * @return 结果
     */
    @Override
    public int deleteSystemMessageByIds(String[] ids) {
        return systemMessageMapper.deleteSystemMessageByIds(ids);
    }

    /**
     * 删除im系统消息信息
     * 
     * @param id im系统消息主键
     * @return 结果
     */
    @Override
    public int deleteSystemMessageById(String id) {
        return systemMessageMapper.deleteSystemMessageById(id);
    }
}
