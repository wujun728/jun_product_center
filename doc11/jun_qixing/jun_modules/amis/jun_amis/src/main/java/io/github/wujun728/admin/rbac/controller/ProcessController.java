package io.github.wujun728.admin.rbac.controller;

import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.rbac.constants.AuditResult;
import io.github.wujun728.admin.rbac.data.Process;
import io.github.wujun728.admin.rbac.data.ProcessNode;
import io.github.wujun728.admin.rbac.service.ApiService;
import io.github.wujun728.admin.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/process")
@Slf4j
public class ProcessController {

    @Resource
    private JdbcService jdbcService;

    @Resource
    private ApiService apiService;

    @PostMapping("/{code}/{nodeCode}/{auditResult}/{id}")
    public Result submit(@PathVariable String code,
                         @PathVariable String nodeCode,
                         @PathVariable String auditResult,
                         @PathVariable Long id,
                         @RequestBody Map<String,Object> params){
        Process process = jdbcService.findOne(Process.class, "code", code);
        if(process == null){
            return Result.error("流程不存在");
        }
        Map<String, Object> obj = jdbcService.getById(process.getMainTable(), id);
        ProcessNode processNode = jdbcService.findOne(ProcessNode.class,new String[]{
                "processId",
                "code"
        }, new Object[]{
                process.getId(),
                nodeCode
        });
        if(processNode == null){
            return Result.error("流程节点编号错误");
        }
        String statusField = StringUtil.toFieldColumn(process.getStatusField());
        String curStatus = (String) obj.get(statusField);
        if(!processNode.getCurStatus().equals(curStatus)){
            return Result.error("当前状态不能"+processNode.getName());
        }
        boolean pass = false;
        if("pass".equals(auditResult)){
            pass = true;
            if(StringUtils.isBlank(processNode.getSuccessStatus())){
                return Result.error("流程节点配置错误,没有配置成功状态");
            }
        }else if("back".equals(auditResult)){
            if(StringUtils.isBlank(processNode.getBackStatus())){
                return Result.error("流程节点配置错误,没有配置退回状态");
            }
            if(StringUtils.isBlank((String)params.get("auditRemark"))){
                return Result.error("请填写原因");
            }
        }else{
            return Result.error("流程url错误,auditResult无法识别");
        }
        final boolean isPass = pass;

        Map<String,Object> context = new HashMap<>();
        context.put("obj",obj);
        context.put("tableName",process.getMainTable());
        Result<String> call = apiService.call(processNode.getBeforeApi(), context);
        if(!call.isSuccess()){
            return call;
        }

        obj.put(statusField,isPass ? processNode.getSuccessStatus():processNode.getBackStatus());

        Map<String,Object> record = new HashMap<>();
        record.put(StringUtil.toFieldColumn(process.getRecordMainIdField()),id);
        record.put("createTime",new Date());
        record.put("auditUserId", SessionContext.getSession().getUserId());
        record.put(statusField,processNode.getSuccessStatus());
        record.put("auditResult", isPass ? AuditResult.SUCCESS : AuditResult.FAIL);
        record.put("auditRemark",params.get("auditRemark"));
        record.put("auditImgs",params.get("auditImgs"));
        record.put("auditFiles",params.get("auditFiles"));

        try {
            jdbcService.transactionOption(()->{
                jdbcService.update(obj,process.getMainTable());
                jdbcService.saveOrUpdate(record,process.getRecordTable());
                Result<String> r = apiService.call(isPass ? processNode.getSuccessAfterApi():processNode.getBackAfterApi(), context);
                if(!r.isSuccess()){
                    throw new RuntimeException(r.getMsg());
                }
            });
        }catch (Exception e){
            log.error(processNode.getName()+"失败",e);
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

}
