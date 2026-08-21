package com.ruoyi.biz.domain;

import lombok.Data;

import java.util.Map;

/**
 * <p> 公共按钮 </p>
 *
 * @Author wocurr.com
 */
@Data
public class CommonButton {

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 流程实例ID
     */
    private String procInsId;

    /**
     * 页面类型
     */
    private String pageType;

    /**
     * 流程环节参数
     */
    private Map<String, Object> variables;
}
