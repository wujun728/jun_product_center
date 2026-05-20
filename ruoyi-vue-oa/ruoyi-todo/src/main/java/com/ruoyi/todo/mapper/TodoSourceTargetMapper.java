package com.ruoyi.todo.mapper;

import com.ruoyi.todo.domain.Done;
import com.ruoyi.todo.domain.Todo;
import com.ruoyi.todo.domain.vo.TodoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 转换实体
 *
 * @Author : wocurr.com
 */
@Mapper(componentModel = "spring")
public interface TodoSourceTargetMapper {
    TodoSourceTargetMapper INSTANCE = Mappers.getMapper(TodoSourceTargetMapper.class);

    /**
     * 转换待办
     *
     * @param todo
     * @return
     */
    Done todo2Done(Todo todo);

    Todo todoVo2Todo(TodoVO todoVO);
}
