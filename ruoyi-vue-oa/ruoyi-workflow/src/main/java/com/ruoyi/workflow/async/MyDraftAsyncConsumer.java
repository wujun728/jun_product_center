package com.ruoyi.workflow.async;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.execute.IAsyncHandler;
import com.ruoyi.tools.lock.RedisLock;
import com.ruoyi.workflow.domain.WorkflowMyDraft;
import com.ruoyi.workflow.service.IWorkflowMyDraftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 我起草的流程消费者
 * @Author wocurr.com
 */
@Slf4j
@Service("myDraftAsyncConsumer")
public class MyDraftAsyncConsumer implements IAsyncHandler {

    @Autowired
    private IWorkflowMyDraftService workflowMyDraftService;
    @Autowired
    private RedisLock redisLock;
    private static final String LOCK_KEY = "async:myDraft:Consumer:";


    @Override
    public void doAsync(AsyncLog asyncLog) {
        log.info("## 开始异步消费-我起草的流程，消息内容：{}", asyncLog);
        String msg = asyncLog.getMessageContent();
        if (StringUtils.isBlank(msg)) {
            log.error("## 异步消费-我起草的流程，消息内容不能为空！");
            return;
        }
        JSONObject json = JSON.parseObject(msg);
        if (json == null) {
            log.error("异步消费-我起草的流程，业务id不能为空！");
            return;
        }
        String businessId = json.getString("businessId");
        String title = json.getString("title");
        String templateId = json.getString("templateId");
        String type = json.getString("type");
        String status = json.getString("status");
        String operatorId = json.getString("operatorId");
        try {
            String lockKey = LOCK_KEY + businessId;
            redisLock.doLock(lockKey, () -> {
                WorkflowMyDraft workflowMyDraft = new WorkflowMyDraft();
                workflowMyDraft.setStatus(status);
                workflowMyDraft.setTemplateId(templateId);
                workflowMyDraft.setBizId(businessId);
                workflowMyDraft.setBizTitle(title);
                if(StringUtils.equals("0", status)) {
                    workflowMyDraft.setType(type);
                    workflowMyDraft.setCreateId(operatorId);
                    workflowMyDraft.setProcInstId(json.getString("procInstId"));
                    workflowMyDraft.setTaskId(json.getString("taskId"));
                    workflowMyDraftService.createMyDraft(workflowMyDraft);
                } else {
                    workflowMyDraftService.updateMyDraft(workflowMyDraft);
                }
            });
        } catch (Exception e) {
            log.error("## 处理正文转换失败", e.getMessage());
            throw new RuntimeException("## 异步消费-我起草的流程失败:" + e.getMessage());
        }
    }
}
