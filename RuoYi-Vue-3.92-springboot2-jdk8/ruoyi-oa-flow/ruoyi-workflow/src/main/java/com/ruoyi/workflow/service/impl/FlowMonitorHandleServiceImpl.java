package com.ruoyi.workflow.service.impl;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.flowable.domain.dto.FlowNextDto;
import com.ruoyi.flowable.domain.dto.FlowTaskDto;
import com.ruoyi.flowable.domain.vo.FlowActivityVo;
import com.ruoyi.flowable.domain.vo.FlowInstanceInfoVo;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.flowable.service.IFlowMonitorService;
import com.ruoyi.workflow.domain.WorkflowMyDraft;
import com.ruoyi.workflow.service.IFlowMonitorHandleService;
import com.ruoyi.workflow.service.IWorkflowMyDraftService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.flowable.bpmn.model.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p> 流程监控服务实现 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class FlowMonitorHandleServiceImpl implements IFlowMonitorHandleService {

    @Autowired
    private IFlowMonitorService flowMonitorService;
    @Autowired
    private IWorkflowMyDraftService workflowMyDraftService;

    @Override
    public TableDataInfo getProcessInstanceList(String procInstId, String businessKey, String name, Integer pageSize, Integer pageNum) {
        TableDataInfo rspData = flowMonitorService.getProcessInstanceList(procInstId, businessKey, name, pageSize, pageNum);
        if (CollectionUtils.isEmpty(rspData.getRows())) {
            return rspData;
        }
        List<FlowInstanceInfoVo> rows = (List<FlowInstanceInfoVo>) rspData.getRows();
        setBizTitle(rows);
        rspData.setRows(rows);
        return rspData;
    }

    @Override
    public TableDataInfo getHistoryListProcess(String procInstId, String businessKey, String name, Integer pageSize, Integer pageNum) {
        TableDataInfo rspData = flowMonitorService.getHistoryListProcess(procInstId, businessKey, name, pageSize, pageNum);
        if (CollectionUtils.isEmpty(rspData.getRows())) {
            return rspData;
        }
        List<FlowInstanceInfoVo> rows = (List<FlowInstanceInfoVo>) rspData.getRows();
        setBizTitle(rows);
        rspData.setRows(rows);
        return rspData;
    }

    @Override
    public TableDataInfo getHistoryList(String processInstanceId, Integer pageSize, Integer pageNum) {
        return flowMonitorService.getHistoryList(processInstanceId, pageSize, pageNum);
    }

    @Override
    public void suspend(String processInstanceId) {
        flowMonitorService.suspend(processInstanceId);
    }

    @Override
    public void reRun(String processInstanceId) {
        flowMonitorService.reRun(processInstanceId);
    }

    /**
     * 获取所有可回退的节点
     *
     * @param flowActivityVo 请求实体参数
     * @return List<UserTask>
     */
    @Override
    public List<UserTask> getJumpActivityList(FlowActivityVo flowActivityVo) {
        return flowMonitorService.getJumpActivityList(flowActivityVo);
    }

    @Override
    public void jumpActivity(FlowTaskVo flowTaskVo) {
        flowMonitorService.jumpActivity(flowTaskVo);
    }

    /**
     * 查询跳转环节
     *
     * @param flowActivityVo
     * @return
     */
    @Override
    public List<FlowNextDto> getJumpActivityNode(FlowActivityVo flowActivityVo) {
        return flowMonitorService.getJumpActivityNode(flowActivityVo);
    }

    /**
     * 获取流程环节任务列表
     *
     * @param flowTaskVo
     * @return
     */
    @Override
    public List<FlowTaskDto> getFlowNodeTasks(FlowTaskVo flowTaskVo) {
        return flowMonitorService.getFlowNodeTasks(flowTaskVo);
    }

    /**
     * 获取完成流程环节任务列表
     *
     * @param flowTaskVo
     * @return
     */
    @Override
    public List<FlowTaskDto> getFinishFlowNodeTasks(FlowTaskVo flowTaskVo) {
        return flowMonitorService.getFinishFlowNodeTasks(flowTaskVo);
    }

    /**
     * 设置业务标题
     *
     * @param rows
     */
    private void setBizTitle(List<FlowInstanceInfoVo> rows) {
        List<String> businessKeys =  rows.stream()
                .map(FlowInstanceInfoVo::getBusinessKey)
                .collect(Collectors.toList());
        List<WorkflowMyDraft> workflowMyDrafts = workflowMyDraftService.listWorkflowMyDraftByBizIds(businessKeys);
        Map<String, WorkflowMyDraft> workflowMyDraftMap = workflowMyDrafts.stream()
                .collect(Collectors.toMap(WorkflowMyDraft::getBizId, Function.identity()));

        rows.parallelStream().forEach(row -> {
            if (workflowMyDraftMap.containsKey(row.getBusinessKey())) {
                row.setTitle(workflowMyDraftMap.get(row.getBusinessKey()).getBizTitle());
            }
        });
    }
}
