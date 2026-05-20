package com.ruoyi.flowable.domain.dto;

import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 动态人员、组
 *
 * @author wocurr.com
 */
@Data
public class FlowNextDto implements Serializable {

    /**
     * 审批人类型
     */
    private String type;

    /**
     * 是否需要动态指定任务审批人
     */
    private String dataType;

    /**
     * 流程变量
     */
    private String vars;

    /**
     * 下一个环节ID
     */
    private String nodeId;

    /**
     * 下一个节点名称
     */
    private String nodeName;

    /**
     * 下一个环节处理人
     */
    private List<SysUser> assignees;

    /**
     * 选人范围：公司：corp, 部门：dept
     */
    private String selectRange;

}
