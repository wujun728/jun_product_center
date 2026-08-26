package com.ruoyi.todo.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 待办参数
 *
 * @author wocurr.com
 */
@Data
public class TodoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 表单标题
     */
    private String title;

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 待办ID
     */
    private String todoId;

    /**
     * 流程实例ID
     */
    private String procInstId;
}
