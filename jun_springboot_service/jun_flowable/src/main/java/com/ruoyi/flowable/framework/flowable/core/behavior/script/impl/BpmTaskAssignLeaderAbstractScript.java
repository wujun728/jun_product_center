package com.ruoyi.flowable.framework.flowable.core.behavior.script.impl;

import com.ruoyi.common.utils.NumberUtils;
import com.ruoyi.flowable.domain.dto.user.AdminUserRespDTO;
import com.ruoyi.flowable.domain.dto.user.DeptRespDTO;
import com.ruoyi.flowable.framework.flowable.core.behavior.script.BpmTaskAssignScript;
import com.ruoyi.flowable.service.task.BpmProcessInstanceService;
import com.ruoyi.flowable.service.user.AdminUserApi;
import com.ruoyi.flowable.service.user.DeptApi;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Set;

import static com.ruoyi.common.utils.collection.SetUtils.asSet;
import static java.util.Collections.emptySet;

/**
 * 分配给发起人的 Leader 审批的 Script 实现类
 * 目前 Leader 的定义是，
 *
 * hasPermi
 */
public abstract class BpmTaskAssignLeaderAbstractScript implements BpmTaskAssignScript {

    @Resource
    private AdminUserApi adminUserApi;
    @Resource
    private DeptApi deptApi;
    @Resource
    @Lazy // 解决循环依赖
    private BpmProcessInstanceService bpmProcessInstanceService;

    protected Set<Long> calculateTaskCandidateUsers(DelegateExecution execution, int level) {
        Assert.isTrue(level > 0, "level 必须大于 0");
        // 获得发起人
        ProcessInstance processInstance = bpmProcessInstanceService.getProcessInstance(execution.getProcessInstanceId());
        Long startUserId = NumberUtils.parseLong(processInstance.getStartUserId());
        // 获得对应 leve 的部门

        DeptRespDTO dept = null;
        for (int i = 0; i < level; i++) {
            // 获得 level 对应的部门
            if (dept == null) {
                dept = getStartUserDept(startUserId);
                // 找不到发起人的部门，所以无法使用该规则
                if (dept == null) {
                    return emptySet();
                }
            } else {
                DeptRespDTO parentDept = deptApi.getDept(dept.getParentId());
                // 找不到父级部门，所以只好结束寻找。原因是：例如说，级别比较高的人，所在部门层级比较少
                if (parentDept == null) {
                    break;
                }
                dept = parentDept;
            }
        }
        return dept.getLeaderUserId() != null ? asSet(dept.getLeaderUserId()) : emptySet();
    }

    private DeptRespDTO getStartUserDept(Long startUserId) {
        AdminUserRespDTO startUser = adminUserApi.getUser(startUserId);
        // 找不到部门，所以无法使用该规则
        if (startUser.getDeptId() == null) {
            return null;
        }
        return deptApi.getDept(startUser.getDeptId());
    }

}
