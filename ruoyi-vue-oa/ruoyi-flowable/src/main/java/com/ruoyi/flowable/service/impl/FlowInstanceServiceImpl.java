package com.ruoyi.flowable.service.impl;


import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.common.constant.ProcessConstants;
import com.ruoyi.flowable.factory.FlowServiceFactory;
import com.ruoyi.flowable.service.IFlowInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

/**
 * <p>工作流流程实例管理<p>
 *
 * @author wocurr.com
 */
@Service
@Slf4j
public class FlowInstanceServiceImpl extends FlowServiceFactory implements IFlowInstanceService {

    /**
     * 流程启动方式
     */
    protected enum FlowStartType {
        KEY, // 通过key启动
        ID; // 通过id启动
    }

    /**
     * 激活或挂起流程实例
     *
     * @param state      状态
     * @param instanceId 流程实例ID
     */
    @Override
    public void updateState(Integer state, String instanceId) {
        // 激活
        if (state == 1) {
            runtimeService.activateProcessInstanceById(instanceId);
        }
        // 挂起
        if (state == 2) {
            runtimeService.suspendProcessInstanceById(instanceId);
        }
    }

    /**
     * 删除流程实例ID
     *
     * @param instanceId   流程实例ID
     * @param deleteReason 删除原因
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String instanceId, String deleteReason) {
        // 查询历史数据
        HistoricProcessInstance historicProcessInstance = getHistoricProcessInstanceById(instanceId);
        if (historicProcessInstance.getEndTime() != null) {
            historyService.deleteHistoricProcessInstance(historicProcessInstance.getId());
            return;
        }
        // 删除流程实例
        runtimeService.deleteProcessInstance(instanceId, deleteReason);
        // 删除历史流程实例
        historyService.deleteHistoricProcessInstance(instanceId);
    }

    /**
     * 根据实例ID查询历史实例数据
     *
     * @param processInstanceId 流程实例Id
     * @return HistoricProcessInstance 历史流程实例对象
     */
    @Override
    public HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId) {
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (Objects.isNull(historicProcessInstance)) {
            throw new FlowableObjectNotFoundException("流程实例不存在: " + processInstanceId);
        }
        return historicProcessInstance;
    }

    /**
     * 根据流程定义ID启动流程实例
     *
     * @param procDefId 流程定义ID
     * @param variables 流程变量
     * @return String 流程实例ID
     */
    @Override
    public String startProcessInstanceById(String procDefId, Map<String, Object> variables) {
        return startProcessInstance(procDefId, variables, FlowStartType.ID);
    }

    /**
     * 根据流程定义ID启动流程实例
     *
     * @param procDefKey 流程定义ID
     * @param variables 流程变量
     * @return String 流程实例ID
     */
    @Override
    public String startProcessInstanceByKey(String procDefKey, Map<String, Object> variables) {
        return startProcessInstance(procDefKey, variables, FlowStartType.KEY);
    }

    /**
     * 启动流程实例
     *
     * @param procDef 流程定义ID或key
     * @param variables 流程变量
     * @param flowStartType 启动方式
     * @return String 流程实例ID
     */
    private String startProcessInstance(String procDef, Map<String, Object> variables, FlowStartType flowStartType) {
        ProcessDefinition processDefinition;
        switch (flowStartType) {
            case KEY:
                processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(procDef)
                        .latestVersion().singleResult();
                break;
            case ID:
                processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDef)
                        .latestVersion().singleResult();
                break;
            default:
                throw new BaseException("流程定义不存在，请联系管理员");
        }
        if (Objects.nonNull(processDefinition) && processDefinition.isSuspended()) {
            throw new BaseException("流程已被挂起,请先激活流程");
        }
        try {
            // 设置流程发起人Id到流程中
            String userId = SecurityUtils.getLoginUser().getUser().getUserId();
            variables.put(ProcessConstants.PROCESS_INITIATOR, userId);
            variables.put(ProcessConstants.FLOWABLE_SKIP_EXPRESSION_ENABLED, true);
            ProcessInstance processInstance =  runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
            return processInstance.getProcessInstanceId();
        } catch (Exception e) {
            log.error("流程启动失败", e);
            throw new BaseException("流程启动失败，请联系管理员");
        }
    }
}