package com.projectm.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.task.domain.TaskToTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface  TaskToTagMapper  extends BaseMapper<TaskToTag> {

    @Select("SELECT * FROM team_task_to_tag WHERE task_code = #{taskCode}")
    List<Map> selectTaskToTagByTaskCode(@Param("taskCode") String taskCode);

    @Select("SELECT * FROM team_task_to_tag WHERE tag_code = #{tagCode} AND task_code = #{taskCode} LIMIT 1")
    Map selectTaskToTagByTagCodeAndTaskCode(@Param("tagCode") String tagCode,@Param("taskCode") String taskCode);
}
