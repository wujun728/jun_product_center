package com.ruoyi.todo.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.todo.domain.Done;
import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.mapper.DoneMapper;
import com.ruoyi.todo.mapper.TodoSourceTargetMapper;
import com.ruoyi.todo.service.IDoneService;
import com.ruoyi.todo.service.ITodoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 已办Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class DoneServiceImpl implements IDoneService {
    @Autowired
    private DoneMapper doneMapper;
    @Autowired
    private ITodoService todoService;

    /**
     * 查询已办
     * 
     * @param id 已办主键
     * @return 已办
     */
    @Override
    public Done getDoneById(String id) {
        return doneMapper.selectDoneById(id);
    }

    /**
     * 查询已办列表
     * 
     * @param done 已办
     * @return 已办
     */
    @Override
    public List<Done> listDone(Done done) {
        done.setHandler(SecurityUtils.getUserId());
        return doneMapper.selectLastDoneList(done);
    }

    /**
     * 生成已办
     *
     * @param todos 待办列表
     */
    public void createDone(List<Todo> todos) {
       List<Done> dones = new ArrayList<>();
       todos.forEach(todo -> {
            dones.add(buildDone(todo, null));
        });
        doneMapper.batchInsert(dones);
    }

    /**
     * 根据任务ID集合查询已办列表
     *
     * @param taskIds 任务ID集合
     * @return
     */
    @Override
    public List<Done> selectDoneByTaskIds(List<String> taskIds) {
        return doneMapper.selectDoneByTaskIds(taskIds);
    }

    /**
     * 催办所有待办
     *
     * @param done 已办信息
     * @return
     */
    @Override
    public int urgeAll(Done done) {
        if (StringUtils.isBlank(done.getProcInstId())) {
            return 0;
        }
        List<Todo> list = todoService.listTodoByProcInsId(done.getProcInstId());
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        List<String> ids = list.stream().map(Todo::getId).collect(Collectors.toList());
        todoService.updateBatchUrge(Constants.YES_VALUE, ids);
        return 1;
    }

    /**
     * 生成已办
     *
     * @param taskId    任务ID
     * @param todos     待办列表
     * @param creatorId 已办创建人ID
     */
    public void createDone(String taskId, List<Todo> todos, String creatorId) {
        List<Done> dones = todos.stream()
                .filter(todo -> todo.getTaskId().equals(taskId))
                .filter(todo -> Objects.equals(todo.getCurHandler(), creatorId))
                .map(todo -> buildDone(todo, creatorId))
                .collect(Collectors.toList());

        doneMapper.batchInsert(dones);
    }

    /**
     * 根据流程实例ID查询最新已办
     *
     * @param procInsId 流程实例ID
     * @return
     */
    @Override
    public List<Done> listLastDoneByProcInstIds(String procInsId) {
        return doneMapper.selectLastListByProcInstIds(Collections.singletonList(procInsId));
    }

    /**
     * 构建已办
     *
     * @param todo      待办
     * @param creatorId 已办创建人ID
     * @return 已办
     */
    private Done buildDone(Todo todo, String creatorId) {
        Done done = TodoSourceTargetMapper.INSTANCE.todo2Done(todo);
        done.setId(IdUtils.fastSimpleUUID());
        done.setTodoId(todo.getId());
        done.setHandleNode(todo.getCurNode());
        done.setHandler(todo.getCurHandler());
        done.setHandlerName(todo.getCurHandlerName());
        done.setHandleTime(DateUtils.getNowDate());
        done.setTemplateId(todo.getTemplateId());
        done.setTemplateName(todo.getTemplateName());
        done.setTemplateType(todo.getTemplateType());
        done.setType(todo.getType());
        done.setCreateId(StringUtils.isNotBlank(creatorId)? creatorId : SecurityUtils.getUserId());
        done.setCreateTime(DateUtils.getNowDate());
        return done;
    }
}
