package com.ruoyi.todo.mapper;

import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.module.TodoParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 待办Mapper接口
 * 
 * @author wocurr.com
 */
public interface TodoMapper {
    /**
     * 查询待办
     * 
     * @param id 待办主键
     * @return 待办
     */
    public Todo selectTodoById(String id);

    /**
     * 查询待办列表
     * 
     * @param todo 待办
     * @return 待办集合
     */
    public List<Todo> selectTodoList(Todo todo);

    /**
     * 新增待办
     * 
     * @param todo 待办
     * @return 结果
     */
    public int insertTodo(Todo todo);

    /**
     * 更新待办
     * 
     * @param todo 待办
     * @return 结果
     */
    public int updateTodo(Todo todo);

    /**
     * 删除待办
     * 
     * @param id 待办主键
     * @return 结果
     */
    public int deleteTodoById(String id);

    /**
     * 批量删除待办
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTodoByIds(String[] ids);

    /**
     * 查询待办信息
     *
     * @param taskId 任务ID
     * @return todoList 待办列表
     */
    List<Todo> selectTodoByTaskId(String taskId);

    /**
     * 查询待办信息
     *
     * @param taskIds 任务ID列表
     * @return todoList 待办列表
     */
    List<Todo> selectTodoByTaskIds(List<String> taskIds);

    /**
     * 批量插入
     *
     * @param todoList 待办列表
     */
    void batchInsert(List<Todo> todoList);

    /**
     * 批量更新
     *
     * @param param
     */
    void batchUpdate(TodoParam param);

    /**
     * 批量更新催办
     * @param param
     */
    public void batchUpdateUrge(TodoParam param);

    /**
     * 查询待办信息
     *
     * @param procInsId 任务ID
     * @return todoList 待办列表
     */
    List<Todo> selectTodoByProcInsId(String procInsId);

    /**
     * 获取当前处理人待办数量
     * @param curHandler
     * @return
     */
    public int getNoRead(String curHandler);

    /**
     * 设置已读
     * @param id
     * @return
     */
    public int updateReadTodo(String id);

    /**
     * 统计待办总数
     * @param type
     * @param curHandler
     * @return
     */
    public int staticTodo(@Param("type") String type, @Param("curHandler") String curHandler);

	/**
     * 查询最新的待办
     *
     * @param procInstIds 流程实例ID集合
     * @return
     */
    List<Todo> selectLastListByProcInstIds(List<String> procInstIds);

    /**
     * 根据流程实例ID删除待办
     *
     * @param procInsId 流程实例ID
     * @return
     */
    int deleteTodoByProcInsId(String procInsId);

    /**
     * 根据任务ID删除待办
     *
     * @param taskIds 任务ID集合
     * @return
     */
    void deleteTodoByTaskIds(List<String> taskIds);
}
