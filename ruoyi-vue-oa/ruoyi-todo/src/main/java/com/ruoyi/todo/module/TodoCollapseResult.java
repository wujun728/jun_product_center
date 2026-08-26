package com.ruoyi.todo.module;

import com.ruoyi.todo.domain.Todo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author wocurr.com
 */
@Data
public class TodoCollapseResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 待办数量
     */
    private int count;

    /**
     * 待办列表
     */
    private List<Todo> todoList;

}
