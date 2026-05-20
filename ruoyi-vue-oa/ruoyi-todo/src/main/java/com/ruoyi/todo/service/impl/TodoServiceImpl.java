package com.ruoyi.todo.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.flowable.common.service.FlowCommonService;
import com.ruoyi.flowable.domain.dto.FlowTaskDto;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.domain.TemplateType;
import com.ruoyi.template.service.ITemplateService;
import com.ruoyi.template.service.ITemplateTypeService;
import com.ruoyi.todo.domain.Done;
import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.enums.TodoHandleTypeEnum;
import com.ruoyi.todo.enums.TodoTypeEnum;
import com.ruoyi.todo.mapper.TodoMapper;
import com.ruoyi.todo.module.TodoCollapseResult;
import com.ruoyi.todo.module.TodoParam;
import com.ruoyi.todo.service.IDoneService;
import com.ruoyi.todo.service.ITodoService;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.flowable.engine.HistoryService;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 待办Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class TodoServiceImpl implements ITodoService {
    @Autowired
    private TodoMapper todoMapper;
    @Autowired
    private FlowCommonService flowCommonService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IDoneService doneService;
    @Autowired
    private ITemplateService templateService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ITemplateTypeService templateTypeService;

    /**
     * 当前处理人变量名
     */
    private static final String INITIATOR = "initiator";

    /**
     * 查询待办
     * 
     * @param id 待办主键
     * @return 待办
     */
    @Override
    public Todo getTodoById(String id) {
        return todoMapper.selectTodoById(id);
    }

    /**
     * 查询待办列表
     * 
     * @param todo 待办
     * @return 待办
     */
    @Override
    public List<Todo> listTodo(Todo todo) {
        todo.setDelFlag(WhetherStatus.NO.getCode());
        todo.setCurHandler(SecurityUtils.getUserId());
        return todoMapper.selectTodoList(todo);
    }

    /**
     * 查询待办列表（折叠）
     *
     * @param todo
     * @return
     */
    @Override
    public List<TodoCollapseResult> listCollapse(Todo todo) {
        todo.setDelFlag(WhetherStatus.NO.getCode());
        todo.setCurHandler(SecurityUtils.getUserId());
        List<Todo> list = todoMapper.selectTodoList(todo);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        // 按模板分类分组
        List<String> templateTypes = list.stream().map(Todo::getTemplateType).distinct().collect(Collectors.toList());
        List<TemplateType> templateTypList = templateTypeService.listTemplateType(templateTypes);
        Map<String, String> templateMap = templateTypList.stream().collect(Collectors.toMap(TemplateType::getId, TemplateType::getName));
        Map<String, List<Todo>> map = list.stream().collect(Collectors.groupingBy(Todo::getTemplateType));
        return map.entrySet().stream().map(entry -> {
            TodoCollapseResult result = new TodoCollapseResult();
            String templateTypeId = entry.getKey();
            result.setTemplateId(templateTypeId); // 此处仅用作前端展示时的折叠分类，不用作跳转
            result.setTemplateName(templateMap.get(templateTypeId));
            result.setCount(entry.getValue().size());
            result.setTodoList(entry.getValue());
            return result;
        }).collect(Collectors.toList());
    }

    /**
     * 批量删除待办
     * 
     * @param ids 需要删除的待办主键
     * @return 结果
     */
    @Override
    public int deleteTodoByIds(String[] ids) {
        return todoMapper.deleteTodoByIds(ids);
    }

    /**
     * 根据流程实例ID删除待办
     *
     * @param procInsId 流程实例ID
     * @return 结果
     */
    public int deleteTodoByProcInsId(String procInsId) {
        return todoMapper.deleteTodoByProcInsId(procInsId);
    }

    /**
     * 查询待办信息
     *
     * @param taskId 任务ID
     * @return List<Todo> 待办列表
     */
    @Override
    public List<Todo> listTodoByTaskId(String taskId) {
        return todoMapper.selectTodoByTaskId(taskId);
    }

    /**
     * 查询待办信息
     *
     * @param taskIds 任务ID列表
     * @return List<Todo> 待办列表
     */
    @Override
    public List<Todo> listTodoByTaskIds(List<String> taskIds) {
        return todoMapper.selectTodoByTaskIds(taskIds);
    }

    /**
     * 批量插入
     *
     * @param todoList 待办列表
     */
    @Override
    public void saveBatch(List<Todo> todoList) {
        todoMapper.batchInsert(todoList);
    }

    /**
     * 生成创建人待办
     *
     * @param todo
     */
    public void createTodo(Todo todo) {
        List<FlowTaskDto> currentTaskList = flowCommonService.getCurrentTaskList(todo.getProcInstId());
        Assert.notEmpty(currentTaskList, "下一个环节待办为空");
        FlowTaskDto task =  currentTaskList.get(0);
        todo.setTaskId(task.getTaskId());
        todo.setCurNode(task.getTaskName());
        todo.setCurHandlerName(getCurHandlerName(todo, task));
        setDefaultTodoProperties(todo);
        todoMapper.insertTodo(todo);
    }

    /**
     * 保存或更新待办
     *
     * @param todo 待办
     */
    public void saveAndUpdateTodo(Todo todo) {
        List<Todo> saveAndUpdateTodos = getSaveAndUpdateTodos(todo);
        if (CollectionUtils.isEmpty(saveAndUpdateTodos)) {
            createTodo(todo);
            return;
        }
        TodoParam param = new TodoParam();
        param.setTitle(todo.getTitle());
        param.setUpdateId(todo.getCreateId());
        param.setUpdateTime(DateUtils.getNowDate());
        param.setTodoIds(saveAndUpdateTodos.stream().map(Todo::getId).collect(Collectors.toList()));
        todoMapper.batchUpdate(param);
    }

    /**
     * 完成当前待办
     *
     * @param taskId 任务ID
     * @param creatorId 创建人ID
     */
    @Override
    public void completeCurrentTodo(String taskId, String creatorId) {
        Assert.hasText(taskId, "任务ID为空");
        List<Todo> todos = todoMapper.selectTodoByTaskId(taskId);
        if (CollectionUtils.isEmpty(todos)) {
            return;
        }
        // 删除待办
        String[] ids = todos.stream().map(Todo::getId).toArray(String[]::new);
        todoMapper.deleteTodoByIds(ids);
        // 生成已办
        doneService.createDone(taskId, todos, creatorId);
    }

    /**
     * 下一个环节生成待办
     *
     * @param flowTaskVo 流程任务
     * @param creatorId  待办创建人ID
     * @param isDone     是否生成已办
     */
    public List<Todo> createNextTodo(FlowTaskVo flowTaskVo, String creatorId, Boolean isDone) {
        if (isDone) {
            completeCurrentTodo(flowTaskVo.getTaskId(), creatorId);
        }
        return handleNextTodo(flowTaskVo, creatorId);
    }

    /**
     * 跳转环节生成待办
     *
     * @param flowTaskVo 流程任务
     * @param creatorId  待办创建人ID
     */
    public List<Todo> createJumpTodo(FlowTaskVo flowTaskVo, String creatorId) {
        return handleNextTodo(flowTaskVo, creatorId);
    }

    /**
     * 取回任务生成待办
     *
     * @param flowTaskVo 流程任务
     * @param taskIds 任务ID列表
     * @param creatorId  待办创建人ID
     */
    public void createReturnFinishTaskTodo(FlowTaskVo flowTaskVo, List<String> taskIds, String creatorId) {
        Assert.notNull(flowTaskVo, "参数为空");
        List<Done> dones = doneService.selectDoneByTaskIds(taskIds);
        Assert.notEmpty(dones, "已办列表为空");
        setReturnTodo(dones.get(0), flowTaskVo);

        SysUser createUser = userService.selectUserById(creatorId);
        List<Todo> newTodoList = new ArrayList<>();
        for (String taskId : taskIds) {
            HistoricTaskInstance hisTaskInst = historyService.createHistoricTaskInstanceQuery()
                    .taskId(taskId).singleResult();
            if (StringUtils.isBlank(hisTaskInst.getAssignee())) {
                continue;
            }
            FlowTaskDto flowTaskDto = new FlowTaskDto();
            flowTaskDto.setTaskId(hisTaskInst.getId());
            flowTaskDto.setTaskName(hisTaskInst.getName());
            flowTaskDto.setProcInsId(hisTaskInst.getProcessInstanceId());
            newTodoList.add(buildTodo(flowTaskVo, flowTaskDto, createUser, hisTaskInst.getAssignee()));
        }
        //生成新待办
        if (CollectionUtils.isNotEmpty(newTodoList)) {
            todoMapper.batchInsert(newTodoList);
        }
    }

    /**
     * 删除取回待办
     *
     * @param todoId 待办ID
     */
    @Override
    public void deleteReturnTodo(String todoId) {
        Todo todo = todoMapper.selectTodoById(todoId);
        doneService.createDone(Collections.singletonList(todo));
        todoMapper.deleteTodoById(todoId);
    }

    /**
     * 创建抄送任务待办
     *
     * @param flowTaskVo
     * @return 新待办
     */
    @Override
    public List<Todo> createCopyTaskTodo(FlowTaskVo flowTaskVo) {
        List<Todo> todos = todoMapper.selectTodoByTaskId(flowTaskVo.getTaskId());
        Todo currentTodo = todos.get(0);
        String[] assignees = flowTaskVo.getAssignee().split(",");
        SysUser curSysUser = userService.selectUserById(SecurityUtils.getUserId());

        List<Todo> newTodoList = new ArrayList<>();
        for (String assignee : assignees) {
            newTodoList.add(buildCopyTaskTodo(currentTodo, curSysUser, assignee));
        }
        return newTodoList;
    }

    /**
     * 更新待办
     *
     * @param todo
     * @return
     */
    @Override
    public int updateTodo(Todo todo) {
        return todoMapper.updateTodo(todo);
    }

    /**
     * 创建委派任务待办
     *
     * @param flowTaskVo 流程任务
     * @param currentTodo 当前待办
     * @return
     */
    @Override
    public List<Todo> createDelegateTaskTodo(FlowTaskVo flowTaskVo, Todo currentTodo) {
        String[] assignees = flowTaskVo.getAssignee().split(",");
        SysUser curSysUser = userService.selectUserById(SecurityUtils.getUserId());

        List<Todo> newTodoList = new ArrayList<>();
        for (String assignee : assignees) {
            newTodoList.add(buildDelegateTaskTodo(currentTodo, curSysUser, assignee));
        }
        todoMapper.batchInsert(newTodoList);
        todoMapper.deleteTodoById(currentTodo.getId());
        return newTodoList;
    }

    /**
     * 获取当前人加签待办
     *
     * @param flowTaskVo 流程任务
     * @return
     */
    @Override
    public List<Todo> getCurrentAddMultiTodos(FlowTaskVo flowTaskVo) {
        Todo queryParam = new Todo();
        queryParam.setProcInstId(flowTaskVo.getProcInsId());
        queryParam.setType(TodoTypeEnum.TODO.getCode());
        queryParam.setHandleType(TodoHandleTypeEnum.ADD_MULTI.getCode());
        queryParam.setCreateId(SecurityUtils.getUserId());
        return todoMapper.selectTodoList(queryParam);
    }

    /**
     * 统计待办总数
     *
     * @param type
     * @return
     */
    @Override
    public int staticTodo(String type) {
        Assert.hasText(type, "待办类型不能为空");
        return todoMapper.staticTodo(type, SecurityUtils.getUserId());
    }

    /**
     * 从已办中设置默认的待办信息
     *
     * @param done 已办
     * @param flowTaskVo 请求参数
     */
    private void setReturnTodo(Done done, FlowTaskVo flowTaskVo) {
        flowTaskVo.setTitle(done.getTitle());
        flowTaskVo.setBusinessId(done.getBusinessId());
        flowTaskVo.setTemplateId(done.getTemplateId());
        flowTaskVo.setTemplateName(done.getTemplateName());
        flowTaskVo.setTemplateType(done.getTemplateType());
        flowTaskVo.setType(TodoTypeEnum.TODO.getCode());
        flowTaskVo.setHandleType(TodoHandleTypeEnum.MULTI_REVOKE.getCode());
        if (StringUtils.isBlank(flowTaskVo.getUserId())) {
            flowTaskVo.setUserId(done.getHandler());
        }
        if (StringUtils.isBlank(flowTaskVo.getProcInsId())) {
            flowTaskVo.setProcInsId(done.getProcInstId());
        }
    }

    /**
     * 同步生成下一个环节待办
     *
     * @param flowTaskVo 流程任务
     */
    public List<Todo> createNextTodo(FlowTaskVo flowTaskVo){
        return createNextTodo(flowTaskVo, SecurityUtils.getUserId(), true);
    }

    /**
     * 批量删除（软删除）
     *
     * @param todos 待办列表
     */
    @Override
    public void batchDelete(List<Todo> todos) {
        Todo todo = todos.get(0);
        TodoParam param = new TodoParam();
        param.setDelFlag(todo.getDelFlag());
        param.setUpdateId(todo.getUpdateId());
        param.setUpdateTime(DateUtils.getNowDate());
        if (todo.getUpdateId() != null) {
            SysUser sysUser = userService.selectUserById(todo.getUpdateId());
            param.setUpdateBy(Objects.nonNull(sysUser)? sysUser.getNickName() : StringUtils.EMPTY);
        }
        param.setTodoIds(todos.stream().map(Todo::getId).collect(Collectors.toList()));
        todoMapper.batchUpdate(param);
    }

    /**
     * 批量更新催办状态
     *
     * @param urgeFlag 催办标志
     * @param todoIds 待办ID列表
     */
    @Override
    public void updateBatchUrge(String urgeFlag, List<String> todoIds) {
        TodoParam param = new TodoParam();
        param.setUrgeFlag(urgeFlag);
        param.setUpdateId(SecurityUtils.getUserId());
        param.setUpdateTime(DateUtils.getNowDate());
        param.setUpdateBy(SecurityUtils.getLoginUser().getUser().getNickName());
        param.setTodoIds(todoIds);
        todoMapper.batchUpdateUrge(param);
    }

    /**
     * 获取用户未读待办数
     *
     * @return
     */
    @Override
    public int getNoRead() {
        return todoMapper.getNoRead(SecurityUtils.getUserId());
    }

    /**
     * 已读待办
     *
     * @param id 待办ID
     * @return 结果
     */
    @Override
    public int readTodo(String id) {
        if (StringUtils.isBlank(id)) {
            return 0;
        }
        Todo todo = getTodoById(id);
        if (todo == null) {
            return 0;
        }
        todoMapper.updateReadTodo(id);
        return 1;
    }

    /**
     * 按流程实例ID查询待办
     *
     * @param procInsId
     * @return
     */
    @Override
    public List<Todo> listTodoByProcInsId(String procInsId) {
        return todoMapper.selectTodoByProcInsId(procInsId);
    }

    /**
     * 删除待办
     *
     * @param taskIds 任务ID列表
     */
    @Override
    public void deleteTodoByTaskIds(List<String> taskIds) {
        todoMapper.deleteTodoByTaskIds(taskIds);
    }

    /**
     * 设置待办默认属性
     *
     * @param todo 待办
     */
    private void setDefaultTodoProperties(Todo todo) {
        if (StringUtils.isBlank(todo.getId())) {
            todo.setId(IdUtils.fastSimpleUUID());
        }
        todo.setType(TodoTypeEnum.TODO.getCode());
        todo.setHandleType(TodoHandleTypeEnum.DRAFT.getCode());
        todo.setReadFlag(Constants.YES_VALUE);
        todo.setSender(todo.getCreateId());
        todo.setSendTime(DateUtils.getNowDate());
        todo.setCreateId(todo.getCreateId());
        todo.setCreateTime(DateUtils.getNowDate());
        if (todo.getCreateId() != null) {
            SysUser user = userService.selectUserById(todo.getCreateId());
            todo.setCreateBy(user.getNickName());
            todo.setSenderName(user.getNickName());
        }
        if (StringUtils.isNotBlank(todo.getTemplateId())) {
            Template template = templateService.getTemplateById(todo.getTemplateId());
            todo.setTemplateName(template.getName());
            todo.setTemplateType(template.getType());
        }
    }

    /**
     * 获取当前处理人名称
     *
     * @param todo 待办
     * @param task 流程任务
     * @return 当前处理人名称
     */
    private String getCurHandlerName(Todo todo, FlowTaskDto task) {
        SysUser assignee = null;
        if (StringUtils.isNotBlank(task.getAssignee())) {
            todo.setCurHandler(task.getAssignee());
        } else {
            // 当前流程创建人变量
            Object initiator = flowCommonService.getProcessVariables(todo.getProcInstId(), INITIATOR);
            if (initiator != null) {
                todo.setCurHandler(initiator.toString());
            }
        }
        if (Objects.nonNull(todo.getCurHandler())) {
            assignee = userService.selectUserById(todo.getCurHandler());
        }
        return Objects.nonNull(assignee) ? assignee.getNickName() : StringUtils.EMPTY;
    }

    /**
     * 获取待办列表，用于保存或更新
     *
     * @param todo 待办
     * @return List<Todo> 待办列表
     */
    private List<Todo> getSaveAndUpdateTodos(Todo todo) {
        if (StringUtils.isNotBlank(todo.getProcInstId())) {
            return todoMapper.selectTodoByProcInsId(todo.getProcInstId());
        }
        if (StringUtils.isNotBlank(todo.getId())) {
            return Collections.singletonList(todoMapper.selectTodoById(todo.getId()));
        }
        return Collections.emptyList();
    }

    /**
     * 获取历史待办
     *
     * @param procInsId 流程实例ID
     * @return List<Todo> 历史待办列表
     */
    private List<Todo> getHistoryTodos(String procInsId, List<String> taskIds) {
        List<Todo> hisTodos = todoMapper.selectTodoByProcInsId(procInsId);
        if (CollectionUtils.isEmpty(hisTodos)) {
            return Collections.emptyList();
        }
        List<Todo> deleteTodos = hisTodos.stream()
                .filter(e -> !taskIds.contains(e.getTaskId()))
                .collect(Collectors.toList());
        // 流程中的任务与当前待办记录匹配，如果待办不存在taskId记录，则移除待办
        if (CollectionUtils.isNotEmpty(deleteTodos)) {
            String[] ids = deleteTodos.stream().map(Todo::getId).toArray(String[]::new);
            todoMapper.deleteTodoByIds(ids);
        }
        return hisTodos.stream()
                .filter(e -> taskIds.contains(e.getTaskId()))
                .collect(Collectors.toList());
    }

    /**
     * 创建新待办
     *
     * @param flowTask 流程任務
     * @param task 流程任务
     * @param createUser 待办创建人
     * @param curHandlerId 当前处理人ID
     * @return Todo 待办
     */
    private Todo buildTodo(FlowTaskVo flowTask, FlowTaskDto task, SysUser createUser, String curHandlerId) {
        Todo todo = new Todo();
        todo.setId(IdUtils.fastSimpleUUID());
        // 设置当前处理人
        SysUser curHandler = userService.selectUserById(curHandlerId);
        todo.setCurHandler(curHandler.getUserId());
        todo.setCurHandlerName(curHandler.getNickName());
        // 设置待办属性
        todo.setTitle(flowTask.getTitle());
        todo.setTaskId(task.getTaskId());
        todo.setCurNode(task.getTaskName());
        todo.setProcInstId(task.getProcInsId());
        todo.setBusinessId(flowTask.getBusinessId());
        todo.setTemplateId(flowTask.getTemplateId());
        todo.setTemplateName(flowTask.getTemplateName());
        todo.setTemplateType(flowTask.getTemplateType());
        todo.setType(flowTask.getType());
        todo.setHandleType(flowTask.getHandleType());
        todo.setUrgencyStatus(flowTask.getUrgencyStatus());
        // 设置默认创建人属性
        setDefaultCreateProperty(todo, createUser);
        // 设置默认属性
        todo.setUrgeFlag(WhetherStatus.NO.getCode());
        todo.setReadFlag(WhetherStatus.NO.getCode());
        todo.setDelFlag(WhetherStatus.NO.getCode());
        return todo;
    }

    /**
     * 获取新的待办列表
     *
     * @param currentTaskList 当前任务列表
     * @param flowTaskVo 流程任务
     * @param createUser 待办创建人
     * @return List<Todo> 新的待办列表
     */
    private List<Todo> getNewTodos(List<FlowTaskDto> currentTaskList, FlowTaskVo flowTaskVo, SysUser createUser) {
        List<Todo> newTodoList = new ArrayList<>();
        for (FlowTaskDto task : currentTaskList) {
            if (StringUtils.isBlank(task.getAssignee()) && StringUtils.isBlank(task.getCandidate())) {
                continue;
            }
            String handleUser = StringUtils.isNotBlank(task.getAssignee())? task.getAssignee() : task.getCandidate();
            for (String assigneeId : handleUser.split(Constants.COMMA)) {
                newTodoList.add(buildTodo(flowTaskVo, task, createUser, assigneeId));
            }
        }
        return newTodoList;
    }

    /**
     * 下一个环节生成待办
     *
     * @param flowTaskVo 流程任务
     * @param creatorId  待办创建人ID
     */
    private List<Todo> handleNextTodo(FlowTaskVo flowTaskVo, String creatorId) {
        List<FlowTaskDto> currentTaskList = flowCommonService.getCurrentTaskList(flowTaskVo.getProcInsId());
        if (CollectionUtils.isEmpty(currentTaskList)) {
            return Collections.emptyList();
        }
        List<String> taskIds = currentTaskList.stream()
                .map(FlowTaskDto::getTaskId)
                .collect(Collectors.toList());

        SysUser createUser =  userService.selectUserById(creatorId);
        List<Todo> hisTodoList = getHistoryTodos(flowTaskVo.getProcInsId(), taskIds);
        if (CollectionUtils.isEmpty(hisTodoList)) {
            return getNewTodos(currentTaskList, flowTaskVo, createUser);
        }
        // 过滤掉非目标环节任务（并行网关）
        Set<String> hisTodoIds = hisTodoList.stream().map(Todo::getTaskId).collect(Collectors.toSet());
        currentTaskList = currentTaskList.stream()
                .filter(e -> !hisTodoIds.contains(e.getTaskId()))
                .collect(Collectors.toList());
        return getNewTodos(currentTaskList, flowTaskVo, createUser);
    }

    /**
     * 设置待办属性
     *
     * @param todo       待办
     * @param curTodo    当前待办
     * @param curSysUser 当前系统用户
     * @param assignee   处理人ID
     */
    private void setPropertyByCurTodo(Todo todo, Todo curTodo, SysUser curSysUser, String assignee) {
        // 设置待办ID
        todo.setId(IdUtils.fastSimpleUUID());
        // 设置当前处理人
        SysUser copyHandler = userService.selectUserById(assignee);
        todo.setCurHandler(copyHandler.getUserId());
        todo.setCurHandlerName(copyHandler.getNickName());
        // 设置待办属性
        todo.setTitle(curTodo.getTitle());
        todo.setTaskId(curTodo.getTaskId());
        todo.setCurNode(curTodo.getCurNode());
        todo.setProcInstId(curTodo.getProcInstId());
        todo.setBusinessId(curTodo.getBusinessId());
        todo.setTemplateId(curTodo.getTemplateId());
        todo.setTemplateName(curTodo.getTemplateName());
        todo.setTemplateType(curTodo.getTemplateType());
        todo.setUrgencyStatus(curTodo.getUrgencyStatus());
        todo.setUrgeFlag(curTodo.getUrgeFlag());
        // 设置创建人信息
        setDefaultCreateProperty(todo, curSysUser);
        // 设置默认属性
        todo.setReadFlag(WhetherStatus.NO.getCode());
        todo.setDelFlag(WhetherStatus.NO.getCode());
        todo.setReadFlag(WhetherStatus.NO.getCode());
    }

    /**
     * 设置默认创建人属性
     *
     * @param todo 待办
     */
    private void setDefaultCreateProperty(Todo todo, SysUser curSysUser) {
        todo.setSender(curSysUser.getUserId());
        todo.setSenderName(curSysUser.getNickName());
        todo.setSendTime(DateUtils.getNowDate());
        todo.setCreateId(curSysUser.getUserId());
        todo.setCreateTime(DateUtils.getNowDate());
        todo.setCreateBy(curSysUser.getNickName());
    }

    /**
     * 构建抄送任务待办
     *
     * @param curTodo 当前待办
     * @param curSysUser 当前系统用户
     * @param assignee 抄送人ID
     * @return Todo 新待办
     */
    private Todo buildCopyTaskTodo(Todo curTodo, SysUser curSysUser, String assignee) {
        Todo todo = new Todo();
        setPropertyByCurTodo(todo, curTodo, curSysUser, assignee);
        todo.setType(TodoTypeEnum.READ.getCode());
        todo.setHandleType(TodoHandleTypeEnum.COPY.getCode());
        return todo;
    }

    /**
     * 构建委派任务待办
     *
     * @param curTodo 当前待办
     * @param curSysUser 当前系统用户
     * @param assignee 处理人ID
     * @return Todo 新待办
     */
    private Todo buildDelegateTaskTodo(Todo curTodo, SysUser curSysUser, String assignee) {
        Todo todo = new Todo();
        setPropertyByCurTodo(todo, curTodo, curSysUser, assignee);
        todo.setType(TodoTypeEnum.TODO.getCode());
        todo.setHandleType(TodoHandleTypeEnum.DELEGATE.getCode());
        return todo;
    }
}
