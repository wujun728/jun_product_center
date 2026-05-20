package com.ruoyi.workflow.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.domain.dto.FlowNextDto;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.workflow.module.FlowRecordParam;
import com.ruoyi.workflow.service.IFlowHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>流程处理管理<p>
 *
 * @author wocurr.com
 */
@Slf4j
@RestController
@RequestMapping("/workflow/handle")
public class FlowHandleController extends BaseController {

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 启动流程
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/startFlow")
    public AjaxResult startFlow(@RequestBody FlowTaskVo flowTaskVo) {
        return AjaxResult.success(flowHandleService.startFlow(flowTaskVo));
    }

    /**
     * 取回流程
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/revokeProcess")
    public AjaxResult revokeProcess(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.revokeProcess(flowTaskVo);
        return AjaxResult.success();
    }

    /**
     * 驳回任务
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/reject")
    public AjaxResult taskReject(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.taskReject(flowTaskVo, SecurityUtils.getUserId());
        return AjaxResult.success();
    }

    /**
     * 终止流程（发起人）
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/finishProcess")
    public AjaxResult finishProcess(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.terminateProcess(flowTaskVo);
        return AjaxResult.success();
    }

    /**
     * 退回任务
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/return")
    public AjaxResult taskReturn(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.taskReturn(flowTaskVo, SecurityUtils.getUserId());
        return AjaxResult.success();
    }

    /**
     * 获取所有可回退的节点
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/returnList")
    public AjaxResult findReturnTaskList(@RequestBody FlowTaskVo flowTaskVo) {
        return AjaxResult.success(flowHandleService.findReturnTaskList(flowTaskVo));
    }

    /**
     * 认领/签收任务
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/claim")
    public AjaxResult claim(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.claim(flowTaskVo, SecurityUtils.getUserId());
        return AjaxResult.success();
    }

    /**
     * 取消认领/签收任务
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/unClaim")
    public AjaxResult unClaim(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.unClaim(flowTaskVo, SecurityUtils.getUserId());
        return AjaxResult.success();
    }

    /**
     * 委派任务
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/delegate")
    public AjaxResult delegate(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.delegateTask(flowTaskVo);
        return AjaxResult.success();
    }

    /**
     * 转办任务
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/assign")
    public AjaxResult assign(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.assignTask(flowTaskVo);
        return AjaxResult.success();
    }

    /**
     * 多实例加签
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/addMultiInstanceExecution")
    public AjaxResult addMultiInstanceExecution(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.addMultiInstanceExecution(flowTaskVo);
        return AjaxResult.success("加签成功");
    }

    /**
     * 多实例减签
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/deleteMultiInstanceExecution")
    public AjaxResult deleteMultiInstanceExecution(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.deleteMultiInstanceExecution(flowTaskVo);
        return AjaxResult.success("减签成功");
    }

    /**
     * 抄送
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/copyTask")
    public AjaxResult copyTask(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.copyTask(flowTaskVo);
        return AjaxResult.success("抄送成功");
    }

    /**
     * 获取下一个节点
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/nextFlowNode")
    public AjaxResult getNextFlowNode(@RequestBody FlowTaskVo flowTaskVo) {
        List<FlowNextDto> nextFlowNodes = flowHandleService.getNextFlowNode(flowTaskVo);
        return AjaxResult.success(nextFlowNodes);
    }

    /**
     * 获取流程变量
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/processVariables/{taskId}")
    public AjaxResult processVariables(@PathVariable(value = "taskId") String taskId) {
        return AjaxResult.success(flowHandleService.processVariables(taskId));
    }

    /**
     * 生成流程图
     *
     * @param response
     * @param processId
     */
    @GetMapping("/diagram/{processId}")
    public void genProcessDiagram(HttpServletResponse response,
                                  @PathVariable("processId") String processId) {
        flowHandleService.diagram(processId, response);
    }

    /**
     * 流程节点信息
     *
     * @param procInsId
     * @param deployId
     * @return
     */
    @GetMapping("/flowXmlAndNode")
    public AjaxResult flowXmlAndNode(@RequestParam(value = "procInsId", required = false) String procInsId,
                                     @RequestParam(value = "deployId", required = false) String deployId) {
        return AjaxResult.success(flowHandleService.flowXmlAndNode(procInsId, deployId));
    }

    /**
     * 流程任务
     *
     * @param taskId
     * @return
     */
    @GetMapping("/info")
    public AjaxResult getFlowTask(@RequestParam(value = "taskId", required = false) String taskId) {
        return AjaxResult.success(flowHandleService.getFlowTask(taskId));
    }

    /**
     * 历史流程任务
     *
     * @param taskId
     * @return
     */
    @GetMapping("/history/info")
    public AjaxResult getHistoryFlowTask(@RequestParam(value = "taskId", required = false) String taskId) {
        return AjaxResult.success(flowHandleService.getHistoryFlowTask(taskId));
    }

    /**
     * 审批意见记录
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/flowCmts")
    public AjaxResult flowCmts(@RequestBody FlowRecordParam param) {
        return AjaxResult.success(flowHandleService.flowCmts(param));
    }

    /**
     * 流程历史流转记录
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/flowRecord")
    public AjaxResult flowRecord(@RequestBody FlowRecordParam param) {
        return AjaxResult.success(flowHandleService.flowRecord(param));
    }

    /**
     * 催办
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/urge")
    public AjaxResult urge(@RequestBody FlowTaskVo param) {
        return toAjax(flowHandleService.urge(param));
    }

    /**
     * 流程跳转
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/jumpActivity")
    public AjaxResult jumpActivity(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.jumpActivity(flowTaskVo);
        return AjaxResult.success();
    }

    /**
     * 终止流程（管理员）
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/admin/terminate")
    public AjaxResult terminateProcess(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.terminateProcessByAdmin(flowTaskVo);
        return AjaxResult.success();
    }

    /**
     * 取回任务（管理员）
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/admin/return")
    public AjaxResult returnFinishTask(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.returnFinishTaskByAdmin(flowTaskVo);
        return AjaxResult.success();
    }

    /**
     * 取回任务（管理员）
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping(value = "/returnSubmit")
    public AjaxResult returnSubmit(@RequestBody FlowTaskVo flowTaskVo) {
        flowHandleService.returnSubmit(flowTaskVo);
        return AjaxResult.success();
    }

    /**
     * 校验提交
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping("/checkComplete")
    public AjaxResult checkCompleteCondition(@RequestBody FlowTaskVo flowTaskVo) {
        return AjaxResult.success(flowHandleService.checkCompleteCondition(flowTaskVo));
    }

    /**
     * 校验退回
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping("/checkReturn")
    public AjaxResult checkReturnCondition(@RequestBody FlowTaskVo flowTaskVo) {
        return AjaxResult.success(flowHandleService.checkReturnCondition(flowTaskVo));
    }

    /**
     * 校验驳回
     *
     * @param flowTaskVo
     * @return
     */
    @PostMapping("/checkReject")
    public AjaxResult checkRejectCondition(@RequestBody FlowTaskVo flowTaskVo) {
        return AjaxResult.success(flowHandleService.checkRejectCondition(flowTaskVo));
    }

    /**
     * 设置抄送阅办完成
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/copy/read/{id}")
    public AjaxResult readCopyTodo(@PathVariable("id") String id) {
        flowHandleService.readCopyTodo(id);
        return AjaxResult.success();
    }

    /**
     * 查询减签任务列表
     *
     * @param flowTaskVo
     * @return
     */
    @GetMapping("/deleteMultiTask/list")
    public AjaxResult getDeleteMultiTasks(FlowTaskVo flowTaskVo) {
        return AjaxResult.success(flowHandleService.getDeleteMultiTasks(flowTaskVo));
    }
}
