package com.ruoyi.workflow.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 待办Controller
 *
 * @author wocurr.com
 */
@RestController
@RequestMapping("/workflow/todo")
public class TodoController extends BaseController {
    @Autowired
    private ITodoService todoService;

    /**
     * 查询待办列表
     */
    @GetMapping("/list/table")
    public TableDataInfo list(Todo todo) {
        startPage();
        List<Todo> list = todoService.listTodo(todo);
        return getDataTable(list);
    }

    /**
     * 查询待办列表（折叠，不分页）
     *
     * @param todo
     * @return
     */
    @GetMapping("/list/collapse")
    public AjaxResult listCollapse(Todo todo) {
        return success(todoService.listCollapse(todo));
    }

    /**
     * 统计待办/待阅总数
     *
     * @param tpye
     * @return
     */
    @GetMapping("/count/{tpye}")
    public AjaxResult staticTodo(@PathVariable("tpye") String tpye) {
        return success(todoService.staticTodo(tpye));
    }

    /**
     * 获取待办详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(todoService.getTodoById(id));
    }

    /**
     * 设置待办为已读
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/readTodo/{id}")
    public AjaxResult readTodo(@PathVariable("id") String id) {
        return success(todoService.readTodo(id));
    }

    /**
     * 设置待办为未读
     *
     * @return
     */
    @GetMapping(value = "/noRead")
    public AjaxResult getNoRead() {
        return AjaxResult.success("操作成功", todoService.getNoRead());
    }

}
