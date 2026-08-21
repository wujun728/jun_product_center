package com.ruoyi.workflow.module;

import com.ruoyi.workflow.domain.WorkflowMainSeal;
import lombok.Data;

import java.io.Serializable;

/**
 * 流程正文印章
 * @Author wocurr.com
 */
@Data
public class WorkflowSealResult extends WorkflowMainSeal implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 印章图片base64
     */
    private String previewBase64;
}
