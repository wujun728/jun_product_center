package com.ruoyi.todo.service;


import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.module.TodoCollapseResult;

import java.util.List;

/**
 * 待办Service接口
 * 
 * @author wocurr.com
 */
public interface ITodoService {
    /**
     * 查询待办
     * 
     * @param id 待办主键
     * @return 待办
     */
    public Todo getTodoById(String id);

    /**
     * 查询待办列表
     * 
     * @param todo 待办
     * @return 待办集合
     */
    public List<Todo> listTodo(Todo todo);

    /**
     * 查询待办列表（折叠）
     * @param todo
     * @return
     */
    public List<TodoCollapseResult> listCollapse(Todo todo);


    /**
     * 批量删除待办
     * 
     * @param ids 需要删除的待办主键集合
     * @return 结果
     */
    public int deleteTodoByIds(String[] ids);

    /**
     * 根据流程实例ID删除所有待办
     *
     * @param procInsId 流程实例ID
     * @return 结果
     */
    public int deleteTodoByProcInsId(String procInsId);

    /**
     * 查询待办信息
     *
     * @param taskId 任务ID
     * @return List<Todo> 待办列表
     */
    List<Todo> listTodoByTaskId(String taskId);

    /**
     * 查询待办信息
     *
     * @param taskIds 任务ID列表
     * @return List<Todo> 待办列表
     */
    List<Todo> listTodoByTaskIds(List<String> taskIds);

    /**
     * 批量插入
     *
     * @param todoList 待办列表
     */
    void saveBatch(List<Todo> todoList);

    /**
     * 生成创建人待办
     *
     * @param todo 待办
     */
    void createTodo(Todo todo);

    /**
     * 更新待办
     *
     * @param todo 待办
     */
    void saveAndUpdateTodo(Todo todo);

    /**
     * 完成当前待办
     *
     * @param taskId 任务ID
     * @param creatorId 创建人ID
     */
    void completeCurrentTodo(String taskId, String creatorId);

    /**
     * 异步生成下一个环节待办
     *
     * @param flowTaskVo 流程任务
     * @param creatorId  待办创建人ID
     * @param isDone     是否生成已办
     */
    List<Todo> createNextTodo(FlowTaskVo flowTaskVo, String creatorId, Boolean isDone);

    /**
     * 跳转环节生成待办
     *
     * @param flowTaskVo 流程任务
     * @param creatorId  待办创建人ID
     */
    List<Todo> createJumpTodo(FlowTaskVo flowTaskVo, String creatorId);

    /**
     * 同步生成下一个环节待办
     *
     * @param flowTaskVo 流程任务
     */
    List<Todo> createNextTodo(FlowTaskVo flowTaskVo);

    /**
     * 批量删除（软删除）
     *
     * @param todos 待办列表
     */
    void batchDelete(List<Todo> todos);

    /**
     * 批量更新催办状态
     */
    public void updateBatchUrge(String urgeFlag, List<String> todoIds);

    /**
     * 获取用户未读待办数
     * @return
     */
    public int getNoRead();

    /**
     * 已读待办
     * @param id
     * @return
     */
    public int readTodo(String id);

    /**
     * 按流程实例ID查询待办
     *
     * @param procInsId
     * @return
     */
    List<Todo> listTodoByProcInsId(String procInsId);

    /**
     * 取回任务生成待办
     *
     * @param flowTaskVo
     * @param taskIds
     * @param userId
     */
    void createReturnFinishTaskTodo(FlowTaskVo flowTaskVo, List<String> taskIds, String userId);

    /**
     * 删除取回待办
     *
     * @param todoId
     */
    void deleteReturnTodo(String todoId);

    /**
     * 创建抄送任务待办
     *
     * @param flowTaskVo
     * @return 新待办
     */
    List<Todo> createCopyTaskTodo(FlowTaskVo flowTaskVo);

    /**
     * 更新待办
     *
     * @param todo 待办
     * @return
     */
    int updateTodo(Todo todo);

    /**
     * 创建委派任务待办
     *
     * @param flowTaskVo 流程任务
     * @param currentTodo 当前待办
     * @return
     */
    List<Todo> createDelegateTaskTodo(FlowTaskVo flowTaskVo, Todo currentTodo);

    /**
     * 获取当前人加签待办
     *
     * @param flowTaskVo 流程任务
     * @return
     */
    List<Todo> getCurrentAddMultiTodos(FlowTaskVo flowTaskVo);

    /**
     * 统计待办总数
     * @param type 待办类型
     * @return
     */
    int staticTodo(String type);

    /**
     * 批量删除待办
     *
     * @param taskIds 任务ID列表
     */
    void deleteTodoByTaskIds(List<String> taskIds);
}
