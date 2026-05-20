package com.ruoyi.workflow.async;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.im.chat.enums.BusinessMessageType;
import com.ruoyi.message.service.IBusinessSystemMessageService;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.execute.IAsyncHandler;
import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.domain.vo.TodoVO;
import com.ruoyi.todo.mapper.TodoSourceTargetMapper;
import com.ruoyi.todo.service.ITodoService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collections;

/**
 * <p> 新启待办异步处理服务 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class TodoAsyncService implements IAsyncHandler {

    @Autowired
    private ITodoService todoService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IBusinessSystemMessageService systemMessageService;

    private static final String MSG_CONTENT = "您的待办任务已更新！";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAsync(AsyncLog asyncLog) {
        log.info("收到消息，消息内容：{}", asyncLog);
        String msg = asyncLog.getMessageContent();
        Assert.hasText(msg, "消息内容为空");
        try {
            TodoVO todoVO = JSONObject.parseObject(msg, TodoVO.class);
            Todo todo = TodoSourceTargetMapper.INSTANCE.todoVo2Todo(todoVO);
            todoService.saveAndUpdateTodo(todo);
            runtimeService.updateBusinessKey(todo.getProcInstId(), todo.getBusinessId());
            systemMessageService.sendBusinessSystemMessage(BusinessMessageType.TODO_LIST_REFRESH.getCode(), null, Collections.singletonList(todo.getCreateId()), MSG_CONTENT);
        } catch (Exception e) {
            log.error("异步处理待办失败，原因：", e);
            throw new BaseException("异步处理待办失败:" + e.getMessage());
        }
    }
}
