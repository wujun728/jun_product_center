package com.ruoyi.worksetting.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.worksetting.domain.WorkflowSecretary;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 流程办理委托对象
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkflowSecretaryVO extends WorkflowSecretary {
    private static final long serialVersionUID = 1L;

    /**
     * 领导
     */
    private SysUser leader;

    /**
     * 秘书
     */
    private SysUser secretary;
}
