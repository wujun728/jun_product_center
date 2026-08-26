package com.ruoyi.todo.service;


import com.ruoyi.todo.domain.Done;
import com.ruoyi.todo.domain.Todo;

import java.util.List;

/**
 * 已办Service接口
 * 
 * @author wocurr.com
 */
public interface IDoneService {
    /**
     * 查询已办
     * 
     * @param id 已办主键
     * @return 已办
     */
    public Done getDoneById(String id);

    /**
     * 查询已办列表
     * 
     * @param done 已办
     * @return 已办集合
     */
    public List<Done> listDone(Done done);

    /**
     * 异步生成已办
     *
     * @param taskId    任务ID
     * @param todos     待办
     * @param creatorId 已办创建人ID
     */
    void createDone(String taskId, List<Todo> todos, String creatorId);

    /**
     * 同步生成已办
     *
     * @param todos 待办
     */
    void createDone(List<Todo> todos);

    /**
     * 批量查询已办
     *
     * @param taskIds 任务ID集合
     */
    List<Done> selectDoneByTaskIds(List<String> taskIds);

    /**
     * 催办下个环节正在处理的所有审批待办
     * @param done
     */
    int urgeAll(Done done);

    /**
     * 根据流程实例ID查询最新已办
     *
     * @param procInsId 流程实例ID
     * @return
     */
    List<Done> listLastDoneByProcInstIds(String procInsId);
}
