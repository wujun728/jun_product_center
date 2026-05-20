package com.ruoyi.im.chat.service;

import java.util.List;
import com.ruoyi.im.chat.domain.SystemMessage;

/**
 * im系统消息Service接口
 * 
 * @author wocurr.com
 */
public interface ISystemMessageService {
    /**
     * 查询im系统消息
     * 
     * @param id im系统消息主键
     * @return im系统消息
     */
    public SystemMessage selectSystemMessageById(String id);

    /**
     * 查询im系统消息列表
     * 
     * @param systemMessage im系统消息
     * @return im系统消息集合
     */
    public List<SystemMessage> selectSystemMessageList(SystemMessage systemMessage);

    /**
     * 新增im系统消息
     * 
     * @param systemMessage im系统消息
     * @return 结果
     */
    public int insertSystemMessage(SystemMessage systemMessage);

    /**
     * 修改im系统消息
     * 
     * @param systemMessage im系统消息
     * @return 结果
     */
    public int updateSystemMessage(SystemMessage systemMessage);

    /**
     * 批量删除im系统消息
     * 
     * @param ids 需要删除的im系统消息主键集合
     * @return 结果
     */
    public int deleteSystemMessageByIds(String[] ids);

    /**
     * 删除im系统消息信息
     * 
     * @param id im系统消息主键
     * @return 结果
     */
    public int deleteSystemMessageById(String id);
}
