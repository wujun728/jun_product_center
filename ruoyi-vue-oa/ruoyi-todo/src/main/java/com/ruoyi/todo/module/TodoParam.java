package com.ruoyi.todo.module;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p> 待办参数 </p>
 *
 * @Author wocurr.com
 */
@Data
public class TodoParam {

    /**
     * 标题
     */
    private String title;

    /**
     * 删除标识
     */
    private String delFlag;

    /**
     * 更新人ID
     */
    private String updateId;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 催办标识，0-否，1-是
     */
    private String urgeFlag;

    /**
     * 待办列表
     */
    private List<String> todoIds;
}
