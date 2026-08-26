package com.ruoyi.flowable.service;

import org.flowable.engine.history.HistoricProcessInstance;

import java.util.Map;

/**
 * 流程实例服务接口
 *
 * @author wocurr.com
 */
public interface IFlowInstanceService {

    /**
     * 激活或挂起流程实例
     *
     * @param state      状态
     * @param instanceId 流程实例ID
     */
    void updateState(Integer state, String instanceId);

    /**
     * 删除流程实例ID
     *
     * @param instanceId   流程实例ID
     * @param deleteReason 删除原因
     */
    void delete(String instanceId, String deleteReason);

    /**
     * 根据实例ID查询历史实例数据
     *
     * @param processInstanceId 流程实例ID
     * @return HistoricProcessInstance
     */
    HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId);

    /**
     * 根据流程定义ID启动流程实例
     *
     * @param procDefId 流程定义ID
     * @param variables 流程变量
     * @return 流程实例Id
     */
    String startProcessInstanceById(String procDefId, Map<String, Object> variables);

    /**
     * 根据流程定义key启动流程实例
     *
     * @param procDefKey 流程定义key
     * @param variables 流程变量
     * @return 流程实例ID
     */
    String startProcessInstanceByKey(String procDefKey, Map<String, Object> variables);
}
