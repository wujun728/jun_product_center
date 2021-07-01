package com.projectm.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.task.domain.TaskMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface TaskMemberMapper  extends BaseMapper<TaskMember> {

    @Select("SELECT * FROM team_task_member WHERE task_code = #{taskCode} ORDER BY is_owner")
    IPage<Map> selectTaskMemberByTaskCode(IPage<Map> page, @Param("taskCode") String taskCode);
}
