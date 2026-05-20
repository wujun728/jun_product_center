package com.ruoyi.flowable.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>流程定义<p>
 *
 * @author wocurr.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowProcDefDto implements Serializable {

    /** 流程id */
    private String id;

    /** 流程名称 */
    private String name;

    /** 流程key */
    private String flowKey;

    /** 流程分类 */
    private String category;

    /** 配置表单名称 */
    private String formName;

    /** 配置表单id */
    private Long formId;

    /** 版本 */
    private int version;

    /** 部署ID */
    private String deploymentId;

    /** 流程定义状态: 1:激活 , 2:中止 */
    private int suspensionState;

    /** 部署时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deploymentTime;


}
