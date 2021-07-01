package com.projectm.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.task.domain.TaskWorkTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface  TaskWorkTimeMapper  extends BaseMapper<TaskWorkTime> {


    @Select("SELECT * FROM team_task_work_time WHERE task_code = #{taskCode}")
    List<Map> selectTaskWorkTimeByTaskCode(@Param("taskCode") String taskCode);

    @Select("SELECT * FROM team_task_work_time WHERE code = #{code}")
    Map selectTaskWorkTimeByCode(@Param("code") String code);

    @Update("DELETE FROM team_task_work_time WHERE code = #{code}")
    Integer deleteTaskWorkTimeByCode(@Param("code") String code);

}
