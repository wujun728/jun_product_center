package com.ruoyi.qixing.service;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.ruoyi.qixing.mapper.BizCommonMapper;

@Service
public class FlowFinishService {
    private static final Logger log = LoggerFactory.getLogger(FlowFinishService.class);

    @Autowired
    private BizCommonMapper bizCommonMapper;

    @Autowired
    private PrimaryKeyService primaryKeyService;

    public String doAfterFlowFinish(String processName, String orderId, String businessId, String taskname) {
        String errMsg = null;
        try {
            log.info("doAfterFlowFinish processName={}, orderId={}, businessId={}", processName, orderId, businessId);
            switch (processName) {
                case "project":
                    updateFlowStatus("pj_project", businessId, "已立项");
                    break;
                case "recheck":
                    errMsg = handleRecheckFinish(orderId);
                    break;
                case "plan":
                    updateFlowStatus("pj_project_plan", businessId, "已审批");
                    break;
                case "draft":
                    updateFlowStatus("pj_project_draft", businessId, "已审批");
                    break;
                case "daily":
                    updateFlowStatus("pj_project_daily", businessId, "已审批");
                    break;
                case "weekly":
                    updateFlowStatus("pj_project_daily", businessId, "已审批");
                    break;
                case "member":
                    updateFlowStatus("pj_project_member", businessId, "已审批");
                    break;
                case "borrow":
                    updateFlowStatus("pj_project_borrow", businessId, "已审批");
                    break;
                case "invoice":
                    updateFlowStatus("pj_project_invoice", businessId, "已审批");
                    break;
                case "contract":
                    updateFlowStatus("pj_contract", businessId, "已审批");
                    break;
                case "customer":
                    updateFlowStatus("pj_customer", businessId, "已审批");
                    break;
                case "leave":
                case "leaveNew":
                    updateFlowStatus("oa_poms_workmarks_leave", businessId, "已批准");
                    break;
                case "outsite":
                    updateFlowStatus("oa_poms_workmarks_outsite", businessId, "已批准");
                    break;
                case "expense":
                    updateFlowStatus("oa_poms_workmarks_claim_expense", businessId, "已批准");
                    break;
                case "office":
                    updateFlowStatus("oa_office_count2", businessId, "已审批");
                    break;
                case "office2":
                    updateFlowStatus("oa_office_count", businessId, "已审批");
                    break;
                case "hire":
                    updateFlowStatus("hr_user_hire", businessId, "已录用");
                    break;
                case "dimission":
                    updateFlowStatus("hr_user_dimission", businessId, "已离职");
                    break;
                case "becomemember":
                    updateFlowStatus("hr_user_become_member", businessId, "已转正");
                    break;
                case "entryJob":
                    updateFlowStatus("hr_user_entry", businessId, "已入职");
                    break;
                case "assessment":
                    updateFlowStatus("hr_assessment_user_record", businessId, "已考核");
                    break;
                case "examination":
                    updateFlowStatus("examination", businessId, "已发布");
                    break;
                default:
                    log.warn("doAfterFlowFinish 未处理的流程类型: {}", processName);
                    break;
            }
        } catch (Exception e) {
            log.error("doAfterFlowFinish 异常 processName={}, orderId={}", processName, orderId, e);
            errMsg = "流程完成后置处理异常: " + e.getMessage();
        }
        return errMsg;
    }

    private void updateFlowStatus(String tableName, String businessId, String status) {
        if (!StringUtils.isEmpty(businessId)) {
            bizCommonMapper.updateRecordByColumnValue(tableName, "dict_wf_state", status, businessId);
            bizCommonMapper.updateRecordByColumnValue(tableName, "order_status", "0", businessId);
            log.info("更新业务状态: table={}, id={}, status={}", tableName, businessId, status);
        }
    }

    private String handleRecheckFinish(String orderId) {
        String errMsg = "项目类型及项目明细类型未维护";
        String ftlStr = bizCommonMapper.queryForProjectCodeStr(orderId);
        if (!StringUtils.isEmpty(ftlStr)) {
            String reportNumberStr = primaryKeyService.genFtlContentByFtlData(ftlStr,
                    primaryKeyService.getReportNumberDataC("%"));
            String dbStr = bizCommonMapper.queryProjectReportCode(reportNumberStr);
            if (StringUtils.isEmpty(dbStr)) {
                reportNumberStr = primaryKeyService.genFtlContentByFtlData(ftlStr,
                        primaryKeyService.getReportNumberData(1));
            } else {
                reportNumberStr = primaryKeyService.genFtlContentByFtlData(ftlStr,
                        primaryKeyService.getReportNumberDataC2(dbStr));
            }
            errMsg = null;
        }
        return errMsg;
    }
}