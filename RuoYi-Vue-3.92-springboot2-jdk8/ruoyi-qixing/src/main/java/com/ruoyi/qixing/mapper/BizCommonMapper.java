package com.ruoyi.qixing.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;

import com.ruoyi.qixing.domain.BizCommon;

public interface BizCommonMapper {

    public List<BizCommon> selectBizCommonList(BizCommon bizCommon);

    public BizCommon selectBizCommonById(String id);

    public int insertBizCommon(BizCommon bizCommon);

    public int updateBizCommon(BizCommon bizCommon);

    public int deleteBizCommonByIds(String[] ids);

    @Select(" SELECT t.ref_member_name from pj_project_member t LEFT JOIN pj_project p on t.ref_project_code=p.project_code where p.id = #{code} ")
    List<String> getProjectMemberList(@Param("code") String code);

    @Select(" SELECT * from oa_notes_info t where t.deleted=1 and t.dict_is_draft=1 ORDER BY t.publish_date desc LIMIT 5 ")
    List<Map> getNotesInfoList();

    @Select(" SELECT * from oa_law_info t where t.deleted=1 and t.dict_is_draft=1 ORDER BY t.publish_date desc LIMIT 5 ")
    List<Map> getLawInfoList();

    @Select(" SELECT * from oa_learn_info t where t.deleted=1 and t.dict_is_draft=1 ORDER BY t.publish_date desc LIMIT 5 ")
    List<Map> getLearnInfoList();

    @Select(" select t.project_name as name,(CONCAT(t.id,'')) as url, DATE_FORMAT((case when t.project_starttime is null then t.create_time else t.project_starttime end),'%Y/%m/%d') as actualStartTime from pj_project t ")
    List<Map> getAllProjectList();

    @Select("SELECT t.id,t.project_code,t.project_name AS name,(CONCAT(t.id, '.json')) AS url,t.project_desc as 'desc','false' as mobile,t.dict_project_status as status1,v1.label as status,DATE_FORMAT(t.project_starttime,'%Y/%m/%d') as actualStartTime,DATE_FORMAT(t.project_endtime,'%Y/%m/%d') as actualEndTime,DATE_FORMAT(t.project_starttime,'%Y/%m/%d') as estimatedStartTime,DATE_FORMAT(t.project_endtime,'%Y/%m/%d') as estimatedEndTime,CONVERT(t.project_progress, unsigned integer) as process,(case when t.dict_project_status != 9 and (TO_DAYS(NOW()) - TO_DAYS(t.project_endtime ) <= 7) then 'false' else 'true' end) as delay,(select IFNULL(c.contract_money,0) from pj_contract c where c.refid_project_code_hide = t.project_code) as money,t.dict_project_type_sub as technology1,v2.label as technology,t.ref_cusname as demander,t.dict_project_type as type1,v3.label as type,t.ref_project_manager as manager,t.ref_undertak_tperson_do as charge,'' as affiliate,(select count(1) from pj_project_prodess_task t2 where t2.ref_project_code = t.project_code) as total,(select count(1) from pj_project_prodess_task t2 where t2.ref_project_code = t.project_code and t2.task_progress=100) as resolved,(select count(1) from pj_project_prodess_task t2 where t2.ref_project_code = t.project_code and t2.task_progress !=100) as unsolved,t.remark as remark FROM pj_project t left join dict_view v1 on v1.value=t.dict_project_status and v1.name='dict_project_status' left join dict_view v2 on v2.value=t.dict_project_type_sub and v2.name='dict_project_type_sub' left join dict_view v3 on v3.value=t.dict_project_type_sub and v3.name='dict_project_type' ")
    List<Map> getAllProjectDetailList();

    @Select("SELECT count(1) as count from pj_customer t where t.deleted=1 UNION ALL SELECT count(1) as count from pj_project t where t.project_progress != 100 and t.deleted=1 UNION ALL SELECT count(1) as count from pj_contract t where t.deleted=1 UNION ALL SELECT count(1) as count from pj_project_report t")
    List<Map> getAllProjectCount();

    @Select("SELECT t.id,t.project_code,t.project_name AS name,(CONCAT(t.id, '.json')) AS url,t.project_desc as 'desc','false' as mobile,t.dict_project_status as status1,v1.label as status,DATE_FORMAT((case when t.project_starttime is null then t.create_time else t.project_starttime end),'%Y/%m/%d') as actualStartTime,DATE_FORMAT(t.project_endtime,'%Y/%m/%d') as actualEndTime,DATE_FORMAT(t.project_starttime,'%Y/%m/%d') as estimatedStartTime,DATE_FORMAT(t.project_endtime,'%Y/%m/%d') as estimatedEndTime,CONVERT(t.project_progress, unsigned integer) as process,(case when t.dict_project_status != 9 and (TO_DAYS(NOW()) - TO_DAYS(t.project_endtime ) <= 7) then 'false' else 'true' end) as delay,(select sum(IFNULL(c.contract_money,0)) from pj_contract c where c.refid_project_code_hide = t.project_code) as money,t.dict_project_type_sub as technology1,v2.label as technology,t.ref_cusname as demander,t.dict_project_type as type1,v3.label as type,t.ref_project_manager as manager,t.ref_undertak_tperson_do as charge,'' as affiliate,(select count(1) from pj_project_prodess_task t2 where t2.ref_project_code = t.project_code) as total,(select count(1) from pj_project_prodess_task t2 where t2.ref_project_code = t.project_code and t2.task_progress=100) as resolved,(select count(1) from pj_project_prodess_task t2 where t2.ref_project_code = t.project_code and t2.task_progress !=100) as unsolved,t.remark as remark FROM pj_project t left join dict_view v1 on v1.value=t.dict_project_status and v1.name='dict_project_status' left join dict_view v2 on v2.value=t.dict_project_type_sub and v2.name='dict_project_type_sub' left join dict_view v3 on v3.value=t.dict_project_type and v3.name='dict_project_type' where t.id = #{project_code} ")
    Map getAllProjectDetailListByCode(@Param("project_code") String project_code);

    @Select(" select value,label,sort from sys_dict_detail dd1 INNER JOIN sys_dict d1 on d1.id= dd1.dict_id where d1.name=#{code} ")
    List<Map> getDictDetailList(@Param("code") String code);

    @Select(" select ${columns} from pj_project_member ppm where ppm.ref_project_code = #{projectCode} ")
    List<Map> getProjectMenberList(@Param("projectCode") String projectCode, @Param("columns") String columns);

    @Select(" select count(1) as count from ${tableName} where ${columnName} = #{columnValue} ")
    int checkRecordExists(@Param("tableName") String tableName, @Param("columnName") String columnName, @Param("columnValue") String columnValue);

    @Update(" update ${tableName} set ${columnName} = #{columnValue} where id = #{id} ")
    int updateRecordByColumnValue(@Param("tableName") String tableName, @Param("columnName") String columnName, @Param("columnValue") String columnValue, @Param("id") String id);

    @Select(" select count(1) as count from ${tableName} where ${columnName} = #{columnValue} and ${columnName2} = #{columnValue2} ")
    int checkRecordExistsV2(@Param("tableName") String tableName, @Param("columnName") String columnName, @Param("columnValue") String columnValue, @Param("columnName2") String columnName2, @Param("columnValue2") String columnValue2);

    @Select("select t.id,t.project_code,t.project_name,t.ref_cusname,t.ref_id_cuscode as pid,count(pjc.refid_project_code_hide) as pjc,count(pjp.ref_project_code) as pjp,count(pjd.ref_project_code) as pjd,count(pjr.ref_pcode) as pjr,count(pja.ref_project_code) as pja,count(pjb.ref_project_code) as pjb,count(pji.ref_project_code) as pji,count(pjm.ref_project_code) as pjm,count(pjt.ref_project_code) as pjt,count(pjr1.ref_project_code) as pjr1,count(pjrn.ref_reportnumber_code) as pjrn from pj_project t left join pj_contract pjc on t.project_code=pjc.refid_project_code_hide left join pj_project_plan pjp on t.project_code=pjp.ref_project_code left join pj_project_draft pjd on t.project_code=pjd.ref_project_code left join pj_project_recheck pjr on t.project_code=pjr.ref_pcode left join pj_project_appraise pja on t.project_code=pja.ref_project_code left join pj_project_borrow pjb on t.project_code=pjb.ref_project_code left join pj_project_invoice pji on t.project_code=pji.ref_project_code left join pj_project_member pjm on t.project_code=pjm.ref_project_code left join pj_project_prodess_task pjt on t.project_code=pjt.ref_project_code left join pj_project_report pjr1 on t.project_code=pjr1.ref_project_code left join pj_project_reportnumber pjrn on t.project_code=pjrn.ref_reportnumber_code GROUP BY t.id,t.project_code,t.project_name,t.ref_cusname union all select cc.id,cc.customer_code,cc.customer_name,cc.customer_code,-1 as pid,'' as pjc,'' as pjp,'' as pjd,'' as pjr,'' as pja,'' as pjb,'' as pji,'' as pjm,'' as pjt,'' as pjr1,'' as pjrn from pj_customer cc")
    List<Map> getCustomerProjects();

    @Select("select t.id,t.project_code,t.project_name,t.ref_cusname,t.ref_id_cuscode as pid,count(pjc.refid_project_code_hide) as pjc,count(pjp.ref_project_code) as pjp,count(pjd.ref_project_code) as pjd,count(pjr.ref_pcode) as pjr,count(pja.ref_project_code) as pja,count(pjb.ref_project_code) as pjb,count(pji.ref_project_code) as pji,count(pjm.ref_project_code) as pjm,count(pjt.ref_project_code) as pjt,count(pjr1.ref_project_code) as pjr1,count(pjrn.ref_reportnumber_code) as pjrn from pj_project t left join pj_contract pjc on t.project_code=pjc.refid_project_code_hide left join pj_project_plan pjp on t.project_code=pjp.ref_project_code left join pj_project_draft pjd on t.project_code=pjd.ref_project_code left join pj_project_recheck pjr on t.project_code=pjr.ref_pcode left join pj_project_appraise pja on t.project_code=pja.ref_project_code left join pj_project_borrow pjb on t.project_code=pjb.ref_project_code left join pj_project_invoice pji on t.project_code=pji.ref_project_code left join pj_project_member pjm on t.project_code=pjm.ref_project_code left join pj_project_prodess_task pjt on t.project_code=pjt.ref_project_code left join pj_project_report pjr1 on t.project_code=pjr1.ref_project_code left join pj_project_reportnumber pjrn on t.project_code=pjrn.ref_reportnumber_code where t.project_progress != 100 and t.deleted=1 GROUP BY t.id,t.project_code,t.project_name,t.ref_cusname")
    List<Map> getProjectState();

    @Select("SELECT c.id FROM pj_project p INNER JOIN pj_customer c on c.customer_code = p.ref_id_cuscode where p.id=#{id} limit 1")
    String queryCustomerIdByProjectId(@Param("id") String id);

    @Select("SELECT c.id from pj_contract c where c.refid_project_code_hide in (SELECT p.project_code FROM pj_project p where p.id=#{id}) limit 1")
    String queryContractIdByProjectId(@Param("id") String id);

    @Select("SELECT c.id from pj_project_plan c where c.ref_project_code in (SELECT p.project_code FROM pj_project p where p.id=#{id}) limit 1")
    String queryPlanIdByProjectId(@Param("id") String id);

    @Select("SELECT c.id from pj_project_draft c where c.ref_project_code in (SELECT p.project_code FROM pj_project p where p.id=#{id}) limit 1")
    String queryDraftIdByProjectId(@Param("id") String id);

    @Select("SELECT c.id from pj_project_report c where c.ref_project_code in (SELECT p.project_code FROM pj_project p where p.id=#{id}) limit 1")
    String queryReportIdByProjectId(@Param("id") String id);

    @Select("SELECT c.id from pj_project_invoice c where c.ref_project_code in (SELECT p.project_code FROM pj_project p where p.id=#{id}) limit 1")
    String queryInvoiceReportIdByProjectId(@Param("id") String id);

    @Select("WITH RECURSIVE temp AS (SELECT * FROM oa_law_info r WHERE r.pid =#{id} UNION ALL SELECT r.* FROM oa_law_info r,temp t WHERE t.id = r.pid) select * from temp")
    List<Map> queryOaLawInfoEntityTreeBypid(@Param("id") String id);

    @Select("SELECT username as 'value',real_name as 'title' from sys_user")
    List<Map> getEditorInfoList();

    @Select("SELECT editor as chain from pj_customer WHERE id = #{id}")
    String getEditorInfoList2(@Param("id") String id);

    @Update("update pj_customer t set t.editor = #{data} WHERE t.id = #{id}")
    int saveCustomerEditorList(@Param("id") String id, @Param("data") String data);

    @Select("SELECT * from wf_hist_task o where o.order_id = #{id} ORDER BY create_time asc")
    List<Map> getWfHisTaskActors(@Param("id") String order_id);

    @Select("SELECT * from pj_project_report t where order_id is not null and order_status = 0 and length(reportnum_code)=0")
    List<Map> getProjectFinishReportList();

    @Update("update pj_project_report t set t.reportnum_code = #{data} WHERE t.id = #{id}")
    int updateReportNumber(@Param("id") String id, @Param("data") String data);

    @Update("update pj_project_reportnumber t set t.reportnumber_code = #{data} WHERE t.id = #{id}")
    int updateReportNumber2(@Param("id") String id, @Param("data") String data);

    @Select("SELECT value2 FROM sys_dict_detail d inner join pj_project p on d.value = p.dict_project_type_sub and d.dict_name like 'dict_project_type_sub_' INNER JOIN pj_project_report r on r.ref_project_code = p.project_code where r.order_id=#{id}")
    String queryForProjectCodeStr(@Param("id") String id);

    @Select("SELECT r.reportnum_code from pj_project_report r where r.reportnum_code like '${code}' ORDER BY r.create_time desc limit 1")
    String queryProjectReportCode(@Param("code") String code);

    @Select("SELECT count(1) from sys_user")
    int selectCountUser();
}