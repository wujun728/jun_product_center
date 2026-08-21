package com.ruoyi.biz.service.abs;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.biz.domain.CommonFlowSubmit;
import com.ruoyi.biz.enums.OperateTypeEnum;
import com.ruoyi.biz.flow.ProcessActivityService;
import com.ruoyi.biz.service.IBizFLowSubmitService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.common.enums.FLowOperateTypeEnum;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.mq.api.ISyncPush;
import com.ruoyi.mq.enums.QueueEnum;
import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.service.ITodoService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> 业务表单数据抽象类 </p>
 *
 * @Author wocurr.com
 */
public abstract class AbstractBizFlowSubmitData implements IBizFLowSubmitService {

    @Autowired
    private ISyncPush syncPush;
    @Autowired
    private ITodoService todoService;
    @Autowired
    private ProcessActivityService processActivityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(CommonFlowSubmit submit) {
        buildBizFlowData(submit);
        handleBizFlowData(submit);
        beforeSubmit(submit);
        submitFlowTaskBySync(submit);
        afterSubmit(submit);
        finishCurrentTodo(submit);
        updateMyDraft(submit);
    }

    /**
     * 完成当前待办
     *
     * @param submit 请求参数
     */
    private void finishCurrentTodo(CommonFlowSubmit submit) {
        String userId = submit.getFlowTask().getUserId();
        String handlerId = StringUtils.isBlank(userId) ? SecurityUtils.getUserIdStr(): userId;
        todoService.completeCurrentTodo(submit.getFlowTask().getTaskId(), handlerId);
    }

    /**
     * 处理流程数据
     *
     * @param submit 请求参数
     */
    private void handleBizFlowData(CommonFlowSubmit submit) {
        if (StringUtils.isNotBlank(submit.getOperateType())) {
            OperateTypeEnum operateTypeEnum = OperateTypeEnum.getByType(submit.getOperateType());
            if (operateTypeEnum == null) {
                return;
            }
            switch(operateTypeEnum) {
                case COMPLETE:
                    submit.getFlowTask().setOperateType(FLowOperateTypeEnum.COMPLETE.getType());
                    break;
                case REJECT:
                    submit.getFlowTask().setOperateType(FLowOperateTypeEnum.REJECT.getType());
                    break;
                case BACK:
                case BACK_ME:
                    submit.getFlowTask().setOperateType(FLowOperateTypeEnum.BACK.getType());
                    break;
                case CLAIM:
                    submit.getFlowTask().setOperateType(FLowOperateTypeEnum.CLAIM.getType());
                    break;
                case UNCLAIM:
                    submit.getFlowTask().setOperateType(FLowOperateTypeEnum.UNCLAIM.getType());
                    break;
                case DELEGATE:
                    submit.getFlowTask().setOperateType(FLowOperateTypeEnum.DELEGATE.getType());
                    break;
                case RESOLVE:
                    submit.getFlowTask().setOperateType(FLowOperateTypeEnum.RESOLVE.getType());
                    break;
            }
        }
    }

    /**
     * 异步提交流程任务
     *
     * @param submit 请求参数
     */
    private void submitFlowTaskBySync(CommonFlowSubmit submit) {
        FlowTaskVo flowTaskVo = submit.getFlowTask();
        List<Todo> todos = todoService.listTodoByTaskId(flowTaskVo.getTaskId());
        if (CollectionUtils.isNotEmpty(todos)) {
            setDefaultTodo(todos.get(0), flowTaskVo);
        }
        syncPush.push(QueueEnum.ASYNC_FLOW_QUEUE.getConsumerBeanName(), JSON.toJSONString(flowTaskVo), QueueEnum.ASYNC_FLOW_QUEUE.getQueueName());
    }

    /**
     * 设置默认的待办信息
     *
     * @param todo 待办
     * @param flowTaskVo 请求参数
     */
    private void setDefaultTodo(Todo todo, FlowTaskVo flowTaskVo) {
        flowTaskVo.setTitle(todo.getTitle());
        flowTaskVo.setBusinessId(todo.getBusinessId());
        flowTaskVo.setTemplateId(todo.getTemplateId());
        flowTaskVo.setTemplateName(todo.getTemplateName());
        flowTaskVo.setTemplateType(todo.getTemplateType());
        flowTaskVo.setUrgencyStatus(todo.getUrgencyStatus());
        // 表单保存时，默认type为草稿，表单提交时，将草稿改为待办
        flowTaskVo.setType(StringUtils.equals(Constants.NO_VALUE, todo.getType()) ? Constants.YES_VALUE : todo.getType());
        if (StringUtils.isBlank(flowTaskVo.getUserId())) {
            flowTaskVo.setUserId(todo.getCurHandler());
        }
        if (StringUtils.isBlank(flowTaskVo.getProcInsId())) {
            flowTaskVo.setProcInsId(todo.getProcInstId());
        }
    }

    /**
     * 异步处理我起草的流程
     */
    private void updateMyDraft(CommonFlowSubmit commFlowSubmit) {
        FlowTaskVo flowTask = commFlowSubmit.getFlowTask();
        JSONObject formDataObj = new JSONObject();
        formDataObj.put("businessId", flowTask.getBusinessId());
        formDataObj.put("title", flowTask.getTitle());
        String status = processActivityService.isFlowFinished(flowTask.getDefId(), flowTask.getTaskDefKey(), flowTask.getProcInsId(), flowTask.getVariables())? "2" : "1";
        formDataObj.put("status", status);
        syncPush.push(QueueEnum.ASYNC_MY_DRAFT_QUEUE.getConsumerBeanName(), JSON.toJSONString(formDataObj), QueueEnum.ASYNC_MY_DRAFT_QUEUE.getQueueName());
    }
}
