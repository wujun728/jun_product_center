package com.ruoyi.workflow.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.flowable.domain.vo.FlowActivityVo;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.workflow.service.IFlowMonitorHandleService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> 流程监控 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@RestController
@RequestMapping("/flowable/monitor")
public class FlowMonitorController extends BaseController {

    @Autowired
    private IFlowMonitorHandleService flowMonitorHandleService;

    /**
     * 查询所有正在运行的流程实例列表
     * @param businessKey
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/listProcess", method = RequestMethod.GET)
    @ResponseBody
    public TableDataInfo getProcessInstanceList(@RequestParam(required = false) String procInstId,
                                                @RequestParam(required = false) String businessKey,
                                                @RequestParam(required = false) String name,
                                                @RequestParam Integer pageSize,
                                                @RequestParam Integer pageNum) {
        startPage();
        return flowMonitorHandleService.getProcessInstanceList(procInstId, businessKey, name, pageSize, pageNum);
    }

    /**
     * 查询所有流程实例列表-包含在运行和已结束
     * @param businessKey
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/listHistoryProcess", method = RequestMethod.GET)
    @ResponseBody
    public TableDataInfo getHistoryListProcess(@RequestParam(required = false) String procInstId,
                                               @RequestParam(required = false) String businessKey,
                                               @RequestParam(required = false) String name,
                                               @RequestParam Integer pageSize,
                                               @RequestParam Integer pageNum) {
        startPage();
        return flowMonitorHandleService.getHistoryListProcess(procInstId, businessKey, name, pageSize, pageNum);
    }

    /**
     * 查询一个流程的活动历史
     * @param processInstanceId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/history/{processInstanceId}", method = RequestMethod.GET)
    @ResponseBody
    public TableDataInfo getHistoryList(@PathVariable String processInstanceId,
                                        @RequestParam Integer pageSize,
                                        @RequestParam Integer pageNum) {
        startPage();
        return flowMonitorHandleService.getHistoryList(processInstanceId, pageSize, pageNum);
    }

    /**
     * 挂起一个流程实例
     */
    @RequestMapping(value = "/suspend/{processInstanceId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult suspend(@PathVariable String processInstanceId) {
        flowMonitorHandleService.suspend(processInstanceId);
        return AjaxResult.success();
    }

    /**
     * 唤醒一个挂起的流程实例
     */
    @RequestMapping(value = "/run/{processInstanceId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult rerun(@PathVariable String processInstanceId) {
        flowMonitorHandleService.reRun(processInstanceId);
        return AjaxResult.success();
    }

    /**
     * 查询跳转环节列表
     */
    @RequestMapping(value = "/jumpActivityList", method = RequestMethod.GET)
    @ResponseBody
    public List<UserTask> getJumpActivityList(FlowActivityVo flowActivityVo) {
        return flowMonitorHandleService.getJumpActivityList(flowActivityVo);
    }

    /**
     * 查询跳转环节
     */
    @RequestMapping(value = "/jumpActivityNode", method = RequestMethod.GET)
    public AjaxResult getJumpActivityNode(FlowActivityVo flowActivityVo) {
        return AjaxResult.success(flowMonitorHandleService.getJumpActivityNode(flowActivityVo));
    }

    /**
     * 查询流程环节任务列表
     *
     * @param flowTaskVo
     * @return
     */
    @GetMapping("/flowNodeTasks")
    public AjaxResult getFlowNodeTasks(FlowTaskVo flowTaskVo) {
        return AjaxResult.success(flowMonitorHandleService.getFlowNodeTasks(flowTaskVo));
    }

    /**
     * 查询已完成流程环节任务列表
     *
     * @param flowTaskVo
     * @return
     */
    @GetMapping("/flowFinishNodeTasks")
    public AjaxResult getFinishFlowNodeTasks(FlowTaskVo flowTaskVo) {
        return AjaxResult.success(flowMonitorHandleService.getFinishFlowNodeTasks(flowTaskVo));
    }
}
