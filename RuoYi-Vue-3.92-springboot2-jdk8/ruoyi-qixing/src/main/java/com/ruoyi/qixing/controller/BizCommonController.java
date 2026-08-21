package com.ruoyi.qixing.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.qixing.mapper.BizCommonMapper;
import com.ruoyi.qixing.service.PrimaryKeyService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.domain.dto.FlowTaskDto;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.workflow.module.FlowRecordParam;
import com.ruoyi.workflow.service.IFlowHandleService;

@RestController
@RequestMapping("/qixing/common")
public class BizCommonController extends BaseController {
    
@Autowired
    private PrimaryKeyService primaryKeyService;

    @Autowired
    private BizCommonMapper bizCommonMapper;

    @Autowired
    private IFlowHandleService flowHandleService;

    @GetMapping("/getAllProjectList")
    public AjaxResult getAllProjectList() {
        return success(bizCommonMapper.getAllProjectList());
    }

    @GetMapping("/getAllProjectDetail")
    public AjaxResult getAllProjectDetail() {
        return success(bizCommonMapper.getAllProjectDetailList());
    }

    @GetMapping("/getAllProjectDetail/{projectId}")
    public AjaxResult getProjectDetailByPathId(@PathVariable String projectId) {
        return success(bizCommonMapper.getAllProjectDetailListByCode(projectId));
    }

    @GetMapping("/getAllProjectCount")
    public AjaxResult getAllProjectCount() {
        return success(bizCommonMapper.getAllProjectCount());
    }

    @GetMapping("/getCustomerProjects")
    public AjaxResult getCustomerProjects() {
        return success(bizCommonMapper.getCustomerProjects());
    }

    @GetMapping("/getProjectState")
    public AjaxResult getProjectState() {
        return success(bizCommonMapper.getProjectState());
    }

    @GetMapping("/getPageIndexInfoList")
    public AjaxResult getPageIndexInfoList() {
        List<Object> datas = new ArrayList<>();
        datas.add(bizCommonMapper.getNotesInfoList());
        datas.add(bizCommonMapper.getLawInfoList());
        datas.add(bizCommonMapper.getLearnInfoList());
        return success(datas);
    }

    @GetMapping("/getDictDetailList/{code}")
    public AjaxResult getDictDetailList(@PathVariable String code) {
        return success(bizCommonMapper.getDictDetailList(code));
    }

    @GetMapping("/getProjectMemberList/{code}")
    public AjaxResult getProjectMemberList(@PathVariable String code) {
        return success(bizCommonMapper.getProjectMemberList(code));
    }

    @GetMapping("/getProjectMenberList")
    public AjaxResult getProjectMenberList(@RequestParam String projectCode, @RequestParam String columns) {
        return success(bizCommonMapper.getProjectMenberList(projectCode, columns));
    }

    @GetMapping("/checkRecordExists")
    public AjaxResult checkRecordExists(@RequestParam String tableName, @RequestParam String columnName, @RequestParam String columnValue) {
        return success(bizCommonMapper.checkRecordExists(tableName, columnName, columnValue));
    }

    @GetMapping("/checkRecordExistsV2")
    public AjaxResult checkRecordExistsV2(@RequestParam String tableName, @RequestParam String columnName, @RequestParam String columnValue, @RequestParam String columnName2, @RequestParam String columnValue2) {
        return success(bizCommonMapper.checkRecordExistsV2(tableName, columnName, columnValue, columnName2, columnValue2));
    }

    @PutMapping("/updateRecordByColumnValue")
    public AjaxResult updateRecordByColumnValue(@RequestParam String tableName, @RequestParam String columnName, @RequestParam String columnValue, @RequestParam String id) {
        return toAjax(bizCommonMapper.updateRecordByColumnValue(tableName, columnName, columnValue, id));
    }

    @GetMapping("/queryCustomerIdByProjectId/{id}")
    public AjaxResult queryCustomerIdByProjectId(@PathVariable String id) {
        return success(bizCommonMapper.queryCustomerIdByProjectId(id));
    }

    @GetMapping("/queryContractIdByProjectId/{id}")
    public AjaxResult queryContractIdByProjectId(@PathVariable String id) {
        return success(bizCommonMapper.queryContractIdByProjectId(id));
    }

    @GetMapping("/queryPlanIdByProjectId/{id}")
    public AjaxResult queryPlanIdByProjectId(@PathVariable String id) {
        return success(bizCommonMapper.queryPlanIdByProjectId(id));
    }

    @GetMapping("/queryDraftIdByProjectId/{id}")
    public AjaxResult queryDraftIdByProjectId(@PathVariable String id) {
        return success(bizCommonMapper.queryDraftIdByProjectId(id));
    }

    @GetMapping("/queryReportIdByProjectId/{id}")
    public AjaxResult queryReportIdByProjectId(@PathVariable String id) {
        return success(bizCommonMapper.queryReportIdByProjectId(id));
    }

    @GetMapping("/queryInvoiceReportIdByProjectId/{id}")
    public AjaxResult queryInvoiceReportIdByProjectId(@PathVariable String id) {
        return success(bizCommonMapper.queryInvoiceReportIdByProjectId(id));
    }

    @GetMapping("/queryOaLawInfoEntityTreeBypid/{id}")
    public AjaxResult queryOaLawInfoEntityTreeBypid(@PathVariable String id) {
        return success(bizCommonMapper.queryOaLawInfoEntityTreeBypid(id));
    }

    @GetMapping("/getEditorInfoList")
    public AjaxResult getEditorInfoList() {
        return success(bizCommonMapper.getEditorInfoList());
    }

    @GetMapping("/getEditorInfoList2/{id}")
    public AjaxResult getEditorInfoList2(@PathVariable String id) {
        return success(bizCommonMapper.getEditorInfoList2(id));
    }

    @PutMapping("/saveCustomerEditorList/{id}")
    public AjaxResult saveCustomerEditorList(@PathVariable String id, @RequestParam String data) {
        return toAjax(bizCommonMapper.saveCustomerEditorList(id, data));
    }

@GetMapping("/getWfHisTaskActors/{orderId}")
    public AjaxResult getWfHisTaskActors(@PathVariable String orderId) {
        return success(bizCommonMapper.getWfHisTaskActors(orderId));
    }

    @GetMapping("/getFlowableHistoryTasks/{procInsId}")
    public AjaxResult getFlowableHistoryTasks(@PathVariable String procInsId) {
        FlowRecordParam param = new FlowRecordParam();
        param.setProcInsId(procInsId);
        return success(flowHandleService.flowRecord(param));
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody Map<String, Object> params) {
        String businessId = (String) params.get("businessId");
        String templateId = (String) params.get("templateId");
        String title = (String) params.get("title");
        String tableName = (String) params.get("tableName");

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(businessId);
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle(title);
        if (params.get("variables") != null) {
            flowTaskVo.setVariables((Map<String, Object>) params.get("variables"));
        }

        FlowTaskDto result = flowHandleService.startFlow(flowTaskVo);

        if (result != null && result.getProcInsId() != null && tableName != null && businessId != null) {
            bizCommonMapper.updateRecordByColumnValue(tableName, "order_id", result.getProcInsId(), businessId);
        }

        return success(result);
    }

    @PostMapping("/rejectToStart")
    public AjaxResult rejectToStart(@RequestBody Map<String, Object> params) {
        String taskId = (String) params.get("taskId");
        String procInsId = (String) params.get("procInsId");
        String comment = (String) params.get("comment");

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setTaskId(taskId);
        flowTaskVo.setProcInsId(procInsId);
        flowTaskVo.setComment(comment);

        flowHandleService.taskReject(flowTaskVo, SecurityUtils.getUserIdStr());
        return success();
    }

    @PostMapping("/addTaskActor")
    public AjaxResult addTaskActor(@RequestBody Map<String, Object> params) {
        String taskId = (String) params.get("taskId");
        String userId = (String) params.get("userId");

        if (taskId != null && userId != null) {
            FlowTaskVo flowTaskVo = new FlowTaskVo();
            flowTaskVo.setTaskId(taskId);
            flowTaskVo.setAssignee(userId);
            flowHandleService.addMultiInstanceExecution(flowTaskVo);
            return success();
        }
        return error("参数错误");
    }

    @GetMapping("/getProjectFinishReportList")
    public AjaxResult getProjectFinishReportList() {
        return success(bizCommonMapper.getProjectFinishReportList());
    }

    
    @GetMapping("/getCodeByType/{type}")
    public AjaxResult getCodeByType(@PathVariable String type) {
        String code = primaryKeyService.genCodeAndCheckExists(type);
        return success(code.toUpperCase());
    }

    @GetMapping("/getProjectFilesRefIDS/{pid}")
    public AjaxResult getProjectFilesRefIDS(@PathVariable String pid) {
        String tmpSQL = getProjectFilesRefIDSSQL(pid);
        return success(tmpSQL);
    }

    private String getProjectFilesRefIDSSQL(String pid) {
        return " select pjc.id from pj_project t left join pj_contract pjc on t.project_code=pjc.refid_project_code_hide where t.id='"+pid+"'  union SELECT '"+pid+"' from pj_project union select pjp.id from pj_project t left join pj_project_plan pjp on t.project_code=pjp.ref_project_code where t.id='"+pid+"' union select pjd.id from pj_project t left join pj_project_draft pjd on t.project_code=pjd.ref_project_code where t.id='"+pid+"' union select pjr.id from pj_project t left join pj_project_recheck pjr on t.project_code=pjr.ref_pcode where t.id='"+pid+"' union select pja.id from pj_project t left join pj_project_appraise pja on t.project_code=pja.ref_project_code where t.id='"+pid+"' union select pji.id from pj_project t left join pj_project_invoice pji on t.project_code=pji.ref_project_code where t.id='"+pid+"' union select pjm.id from pj_project t left join pj_project_member pjm on t.project_code=pjm.ref_project_code where t.id='"+pid+"' union select pjt.id from pj_project t left join pj_project_prodess_task pjt on t.project_code=pjt.ref_project_code where t.id='"+pid+"' union select pjr1.id from pj_project t left join pj_project_report pjr1 on t.project_code=pjr1.ref_project_code where t.id='"+pid+"' union select pjrn.id from pj_project t left join pj_project_reportnumber pjrn on t.project_code=pjrn.ref_reportnumber_code where t.id='"+pid+"'";
    }
}