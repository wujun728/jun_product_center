package com.projectm.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.task.domain.Task;
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
public interface TaskMapper extends BaseMapper<Task> {

    @Select("SELECT * FROM team_task WHERE code = #{code} LIMIT 1")
    Map selectTaskByCode(@Param("code") String code);
    @Select("SELECT * FROM team_task WHERE code = #{code} and deleted= 0 LIMIT 1")
    Map selectTaskByCodeNoDel(@Param("code") String code);
    @Select("SELECT * FROM team_task WHERE code = #{code} and deleted= 1 LIMIT 1")
    Map selectTaskByCodeDel(@Param("code") String code);
    @Select("SELECT a.id,a.code,a.project_code,a.name,a.pri,a.execute_status,a.description,a.create_by,a.create_time,a.assign_to,a.deleted,a.stage_code,a.task_tag,a.done,a.begin_time,a.end_time,a.remind_time,a.pcode,a.sort,a.liked,a.star,a.deleted_time,a.private,a.id_num,a.path,a.schedule,a.version_code,a.features_code,a.work_time,a.status FROM team_task a WHERE a.code = #{code} ")
    Task selTaskByCode(@Param("code") String code);

    @Select("SELECT code,project_code,name,pri,execute_status,description,create_by,create_time,assign_to,status,deleted,stage_code,task_tag,done,begin_time,end_time,remind_time,pcode,sort,`liked`,star,deleted_time,private,id_num,path,schedule,version_code,features_code,work_time FROM team_task t WHERE pcode = #{params.pcode} AND deleted = #{params.deleted} AND t.stage_code = #{params.stageCode} ORDER BY t.sort ASC,t.id ASC")
    List<Map> selectTaskByParams(@Param("params") Map params);

    @Select("SELECT code,task_code,tag_code,create_time FROM team_task_to_tag WHERE task_code = #{taskCode} ORDER BY id ASC")
    List<Map> selectTaskToTagByTaskCode(@Param("taskCode") String taskCode);

    @Select("SELECT * FROM team_task_tag WHERE code = #{code}")
    Map selectTaskTagByCode(@Param("code") String code);

    //HasUnDone 是否有子任务未完成
    @Select("SELECT COUNT(id) AS tp_count FROM team_task WHERE pcode = #{pcode} AND done = 0 AND deleted = 0")
    Map selectHasUnDone(@Param("pcode") String pcode);

    //HasComment
    @Select("SELECT COUNT(id) AS tp_count FROM team_project_log WHERE source_code = #{code} AND type = 'task' AND is_comment = 1")
    Map selectHasComment(@Param("code") String code);

    //HasSource
    @Select("SELECT COUNT(id) AS tp_count FROM team_source_link WHERE link_code = #{code} AND link_type = 'task'")
    Map selectHasSource(@Param("code") String code);

    //ChildCount0
    @Select("SELECT COUNT(id) AS tp_count FROM team_task WHERE pcode = #{pcode} AND deleted = 0")
    Map selectChildCount0(@Param("pcode") String pcode);
    //ChildCount1
    @Select("SELECT COUNT(id) AS tp_count FROM team_task WHERE pcode = #{pcode} AND deleted = 0 AND done = 1")
    Map selectChildCount1(@Param("pcode") String pcode);

    //canRead
    @Select("SELECT * FROM team_task_member WHERE task_code = #{taskCode} AND member_code = #{memberCode}")
    Map selectCanRead(@Param("taskCode") String taskCode,@Param("memberCode") String memberCode);
    @Select("select * from team_task_like where task_code= #{taskCode} and member_code = #{memberCode}")
    Map selectTaskLike(@Param("taskCode") String taskCode,@Param("memberCode") String memberCode);
    @Select("select * from team_collection a where a.type='task' and a.source_code=#{taskCode} and a.member_code=#{memberCode}")
    Map selectTaskStared(@Param("taskCode") String taskCode,@Param("memberCode") String memberCode);
    //parentDone
    @Select("SELECT done,deleted FROM team_task WHERE code = #{pcode}")
    Map selectParentDone(@Param("pcode") String pcode);

    //未完成的任务列表
    @Select("SELECT t.id,t.code,t.project_code,t.name,t.pri,t.execute_status,t.description,t.create_by,t.create_time,t.assign_to,t.deleted,t.stage_code,t.task_tag,t.done,t.begin_time,t.end_time,t.remind_time,t.pcode,t.sort,t.liked,t.star,t.deleted_time,t.private,t.id_num,t.path,t.schedule,t.version_code,t.features_code,t.work_time,p.cover,p.access_control_type,p.white_list,p.order,p.template_code,p.organization_code,p.prefix,p.open_prefix,p.archive,p.open_begin_time,p.open_task_private,p.task_board_theme,p.auto_update_schedule FROM team_task AS t JOIN team_project AS p ON t.project_code = p.CODE WHERE t.deleted = 0 AND t.done = #{params.done} AND t.assign_to = #{params.memberCode} AND p.deleted = 0 ORDER BY t.id DESC")
    IPage<Map> selectTaskSelfListNoFinish(IPage<Map> page, @Param("params") Map params);
    @Select("SELECT t.id,t.code,t.project_code,t.name,t.pri,t.execute_status,t.description,t.create_by,t.create_time,t.assign_to,t.deleted,t.stage_code,t.task_tag,t.done,t.begin_time,t.end_time,t.remind_time,t.pcode,t.sort,t.liked,t.star,t.deleted_time,t.private,t.id_num,t.path,t.schedule,t.version_code,t.features_code,t.work_time,p.cover,p.access_control_type,p.white_list,p.order,p.template_code,p.organization_code,p.prefix,p.open_prefix,p.archive,p.open_begin_time,p.open_task_private,p.task_board_theme,p.auto_update_schedule FROM team_task AS t JOIN team_project AS p ON t.project_code = p.CODE WHERE t.deleted = 0 AND t.assign_to = #{params.memberCode} AND p.deleted = 0 ORDER BY t.id DESC")
    IPage<Map> selectTaskSelfListAll(IPage<Map> page,@Param("params") Map params);

    @Select({"<script>",
            "select t.project_code,t.assign_to,t.deleted,t.stage_code,t.task_tag,t.done,t.begin_time,t.end_time,t.remind_time," +
                    "t.pcode,t.sort,t.`liked`,t.star,t.deleted_time,t.pri,t.private,t.id_num,t.path,t.`schedule`,t.version_code," +
                    "t.features_code,t.work_time,p.cover,p.access_control_type,p.white_list,p.`order`," +
                    "p.template_code,p.organization_code,p.prefix,p.open_prefix,p.archive,p.archive_time," +
                    "p.open_begin_time,p.open_task_private,p.task_board_theme,p.auto_update_schedule," +
                    "t.create_time,t.create_by,p.description,t.id as id,t.name as name,t.code as code," +
                    "t.create_time  from team_task as t join team_project as p on t.project_code = p.code " +
                    "where  t.deleted = 0  ",
            "<if test='params.done!=-1 '>",
            "AND t.done <![CDATA[ = ]]> #{params.done}",
            "</if>",
            "and t.assign_to = #{params.memberCode} and p.deleted = 0 order by t.id desc" ,

            "</script>"})
    IPage<Map> selectMemberTasks(IPage<Map> page, @Param("params") Map params);

    @Update("UPDATE team_task SET features_code = '' , version_code = '' WHERE features_code = #{featuresCode}")
    Integer updateTaskFeaAndVerByFeaCode(@Param("featuresCode") String featuresCode);

    @Update("UPDATE team_task SET features_code = '' , version_code = '' WHERE version_code = #{versionCode}")
    Integer updateTaskFeaAndVerByVerCode(@Param("versionCode") String versionCode);

    @Select("SELECT * FROM team_task WHERE version_code = #{params.versionCode} and deleted = #{params.deleted}")
    List<Map> selectTaskListByVersionAndDelete(@Param("params")  Map params);

    @Select("select max(a.id_num) id_num from team_task a where a.project_code = #{projectCode}")
    Integer selectMaxIdNumByProjectCode(@Param("projectCode") String projectCode);

    IPage<Map> selectTaskListByParam(IPage<Map> page,@Param("params") Map params);

    @Select(" select count(a.id) id from team_task a where a.project_code = #{projectCode} and (a.create_time BETWEEN #{beginTime} and #{endTime}) ")
    Integer selectDateTaskTotalForProject(@Param("projectCode") String projectCode,@Param("beginTime") String beginTime, @Param("endTime")String endTime);

    @Select("SELECT  id,code,project_code,name,pri,execute_status,description,create_by,create_time,assign_to,deleted,stage_code,task_tag,done,begin_time,end_time,remind_time,pcode,sort,`liked`,star,deleted_time,private,id_num,path,schedule,version_code,features_code,work_time,status  FROM team_task     WHERE project_code = #{projectCode} AND deleted = #{deleted}")
    List<Map> selectTaskByProjectCodeAndDel(@Param("projectCode")  String projectCode,@Param("deleted")  Integer deleted);

    @Update("UPDATE team_task SET `like` = #{like}  WHERE code = #{code}")
    Integer updateTaskLike(@Param("like") Integer like,@Param("code") String code);
    @Update("UPDATE team_task SET `star` = #{star}  WHERE code = #{code}")
    Integer updateTaskStar(@Param("star") Integer star,@Param("code") String code);

    @Select("select count(1) from team_task where project_code=#{projectCode}")
    Integer selectCountByProjectCode(@Param("projectCode") String projectCode);
    @Select("select count(1) from team_task where project_code=#{projectCode} and done = 1 ")
    Integer selectCountByProjectCodeAndDone(@Param("projectCode") String projectCode);

    @Select("select tm.member_code from team_task_member as tm join team_member as m on tm.member_code = m.code where tm.task_code = #{taskCode} and name = #{name} ")
    String selectMemberCodeOne(@Param("taskCode") String taskCode,@Param("name") String name);

}
