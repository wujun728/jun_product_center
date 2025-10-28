package com.jun.plugin.qixing.mapper;

import java.util.List;
import java.util.Map;

import com.jun.plugin.common.utils.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.qixing.entity.BizCommonEntity;
import com.jun.plugin.qixing.entity.OaLawInfoEntity;

/**
 * 公共信息
 * 
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
@SuppressWarnings("rawtypes")
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface BizCommonMapper extends BaseMapper<BizCommonEntity> {
	
	
	@Select(" SELECT t.ref_member_name from pj_project_member t \r\n"
			+ "LEFT JOIN pj_project p on t.ref_project_code=p.project_code\r\n"
			+ "where  p.id = #{code}  ")
	List<String> getProjectMemberList(@Param("code") String code);
	
	@Select(" SELECT * from oa_notes_info t where t.deleted=1 and t.dict_is_draft=1 ORDER BY t.publish_date desc  LIMIT 5   ")
	List<Map> getNotesInfoList();
	
	@Select(" SELECT * from oa_law_info t where t.deleted=1 and t.dict_is_draft=1 ORDER BY t.publish_date desc  LIMIT 5;  ")
	List<Map> getLawInfoList();
	
	@Select(" SELECT * from oa_learn_info t where t.deleted=1 and t.dict_is_draft=1 ORDER BY t.publish_date desc  LIMIT 5   ")
	List<Map> getLearnInfoList();

	@Select("SELECT count(1) from sys_user")
	int selectCountUser();

	@Select(" select t.project_name as name,(CONCAT(t.id,'')) as url,\n" +
			"DATE_FORMAT(\n" +
			"(case when t.project_starttime is null then t.create_time else  \n" +
			" t.project_starttime  end )  ,'%Y/%m/%d')   as actualStartTime from pj_project t ")
	List<Map> getAllProjectList();

	@Select("  SELECT\n" +
			"\t\t\t\tt.id,\n" +
			"\t\t\t\tt.project_code,\n" +
			"\t\t\t\tt.project_name AS name,\n" +
			"\t\t\t\t(CONCAT(t.id, '.json')) AS url,\n" +
			"\t\t\t\tt.project_desc as 'desc',\n" +
			"\t\t\t\t'false' as mobile,\n" +
			"\t\t\t\tt.dict_project_status as status1,\n" +
			"\t\t\t\tv1.label as status,\n" +
			"\t\t\t\t DATE_FORMAT(  t.project_starttime  ,'%Y/%m/%d')  as actualStartTime,\n" +
			"\t\t\t\t DATE_FORMAT(  t.project_endtime  ,'%Y/%m/%d') as actualEndTime,\n" +
			"\t\t\t\t DATE_FORMAT(  t.project_starttime ,'%Y/%m/%d')  as estimatedStartTime,\n" +
			"\t\t\t\t DATE_FORMAT(  t.project_endtime ,'%Y/%m/%d')  as estimatedEndTime,\n" +
			"\t\t\t\tCONVERT(t.project_progress, unsigned integer) as process,\n" +
			"\t\t\t\t(case when t.dict_project_status != 9 and (TO_DAYS(NOW()) - TO_DAYS(t.project_endtime ) <= 7) then 'false' else \n" +
			"\t\t\t\t'true' end) as delay,\n" +
			"\t\t\t\t(select  IFNULL(c.contract_money,0) from pj_contract c where c.refid_project_code_hide  = t.project_code) as money,\n" +
			"\t\t\t\tt.dict_project_type_sub as technology1,\n" +
			"\t\t\t\tv2.label as technology,\n" +
			"\t\t\t\tt.ref_cusname as demander,\n" +
			"\t\t\t\tt.dict_project_type as type1,\n" +
			"\t\t\t\tv3.label as type,\n" +
			"\t\t\t\tt.ref_project_manager as manager,\n" +
			"\t\t\t\tt.ref_undertak_tperson_do as charge,\n" +
			"\t\t\t\t'' as affiliate,\n" +
			"\t\t\t\t(select count(1) from pj_project_prodess_task t where t.ref_project_code = t.ref_project_code) as total,\n" +
			"\t\t\t\t(select count(1) from pj_project_prodess_task t where t.ref_project_code = t.ref_project_code and t.task_progress=100) as resolved,\n" +
			"\t\t\t\t(select count(1) from pj_project_prodess_task t where t.ref_project_code = t.ref_project_code and t.task_progress !=100) as unsolved,\n" +
			"\t\t\t\tt.remark as remark\n" +
			"\t\t\tFROM\n" +
			"\t\t\t\tpj_project t\n" +
			"\t\t\tleft join dict_view v1 on v1.value=t.dict_project_status and v1.name='dict_project_status'\n" +
			"\t\t\tleft join dict_view v2 on v2.value=t.dict_project_type_sub and v2.name='dict_project_type_sub'\n" +
			"\t\t\tleft join dict_view v3 on v3.value=t.dict_project_type_sub and v3.name='dict_project_type'  ")
	List<Map> getAllProjectDetailList();

	@Select("  SELECT count(1) as count from pj_customer t where t.deleted=1\n" +
			"\t\tUNION ALL\n" +
			"\t\tSELECT count(1) as count from pj_project t where t.project_progress != 100 and  t.deleted=1\n" +
			"\t\tUNION  ALL\n" +
			"\t\tSELECT count(1) as count from pj_contract t where t.deleted=1\n" +
			"\t\tUNION ALL\n" +
			"\t\tSELECT count(1) as count from pj_project_report t   ")
	List<Map> getAllProjectCount();

	@Select("   SELECT\n" +
			"\t\t\t\tt.id,\n" +
			"\t\t\t\tt.project_code,\n" +
			"\t\t\t\tt.project_name AS name,\n" +
			"\t\t\t\t(CONCAT(t.id, '.json')) AS url,\n" +
			"\t\t\t\tt.project_desc as 'desc',\n" +
			"\t\t\t\t'false' as mobile,\n" +
			"\t\t\t\tt.dict_project_status as status1,\n" +
			"\t\t\t\t v1.label as status,\n" +
			"\t\t\t\t  DATE_FORMAT(   (case when t.project_starttime is null then t.create_time else  t.project_starttime  end )  ,'%Y/%m/%d')   as actualStartTime,\n" +
			"\t\t\t\t DATE_FORMAT(  t.project_endtime  ,'%Y/%m/%d')  as actualEndTime,\n" +
			"\t\t\t\t DATE_FORMAT(  t.project_starttime ,'%Y/%m/%d')  as estimatedStartTime,\n" +
			"\t\t\t\t DATE_FORMAT(  t.project_endtime ,'%Y/%m/%d')  as estimatedEndTime,\n" +
			"\t\t\t\tCONVERT(t.project_progress, unsigned integer) as process,\n" +
			"\t\t\t\t(case when t.dict_project_status != 9 and (TO_DAYS(NOW()) - TO_DAYS(t.project_endtime ) <= 7) then 'false' else \n" +
			"\t\t\t\t'true' end) as delay,\n" +
			"\t\t\t\t(select  sum(IFNULL(c.contract_money,0)) from pj_contract c where c.refid_project_code_hide  = t.project_code) as money,\n" +
			"\t\t\t\tt.dict_project_type_sub as technology1,\n" +
			"\t\t\t\tv2.label as technology,\n" +
			"\t\t\t\tt.ref_cusname as demander,\n" +
			"\t\t\t\tt.dict_project_type as type1,\n" +
			"\t\t\t\tv3.label as type,\n" +
			"\t\t\t\tt.ref_project_manager as manager,\n" +
			"\t\t\t\tt.ref_undertak_tperson_do as charge,\n" +
			"\t\t\t\t'' as affiliate,\n" +
			"\t\t\t\t(select count(1) from pj_project_prodess_task t where t.ref_project_code = t.ref_project_code) as total,\n" +
			"\t\t\t\t(select count(1) from pj_project_prodess_task t where t.ref_project_code = t.ref_project_code and t.task_progress=100) as resolved,\n" +
			"\t\t\t\t(select count(1) from pj_project_prodess_task t where t.ref_project_code = t.ref_project_code and t.task_progress !=100) as unsolved,\n" +
			"\t\t\t\tt.remark as remark\n" +
			"\t\t\tFROM\n" +
			"\t\t\t\tpj_project t\n" +
			"\t\t\tleft join dict_view v1 on v1.value=t.dict_project_status and v1.name='dict_project_status'\n" +
			"\t\t\tleft join dict_view v2 on v2.value=t.dict_project_type_sub and v2.name='dict_project_type_sub'\n" +
			"\t\t\tleft join dict_view v3 on v3.value=t.dict_project_type and v3.name='dict_project_type'\n" +
			"\t\t\twhere t.id = #{project_code}  ")
	Map getAllProjectDetailListByCode(@Param("project_code") String project_code);

	@Select(" select value,label,sort from sys_dict_detail dd1\r\n"
			+ "INNER JOIN sys_dict d1 on d1.id= dd1.dict_id where d1.name=#{code}   ")
	List<Map> getDictDetailList(@Param("code") String code);

	@Select(" select ${columns} from pj_project_member ppm where ppm.ref_project_code = #{projectCode} ")
	List<Map> getProjectMenberList(@Param("projectCode") String projectCode, @Param("columns") String columns);

	@Select(" select count(1) as count from ${tableName}  where  ${columnName}  = #{columnValue} ")
	int checkRecordExists(@Param("tableName") String tableName, @Param("columnName") String columnName,
			@Param("columnValue") String columnValue);

	@Update(" update ${tableName}  set  ${columnName}  = #{columnValue}  where id = #{id} ")
	int updateRecordByColumnValue(@Param("tableName") String tableName, @Param("columnName") String columnName,
			@Param("columnValue") String columnValue, @Param("id") String id);

	@Select(" select count(1) as count from ${tableName} pj_project_member  where  ${columnName}  = #{columnValue} "
			+ "and ${columnName2}  = #{columnValue2} ")
	int checkRecordExistsV2(@Param("tableName") String tableName, @Param("columnName") String columnName,
			@Param("columnValue") String columnValue, @Param("columnName2") String columnName2,
			@Param("columnValue2") String columnValue2);

	@Select("  select t.id,t.project_code,t.project_name,t.ref_cusname,t.ref_id_cuscode as pid,\n" +
			"\t\t\tcount(pjc.refid_project_code_hide) as pjc,\n" +
			"\t\t\tcount(pjp.ref_project_code) as pjp,\n" +
			"\t\t\tcount(pjd.ref_project_code) as pjd,\n" +
			"\t\t\tcount(pjr.ref_pcode) as pjr,\n" +
			"\t\t\tcount(pja.ref_project_code) as pja,\n" +
			"\t\t\tcount(pjb.ref_project_code) as pjb,\n" +
			"\t\t\tcount(pji.ref_project_code) as pji,\n" +
			"\t\t\tcount(pjm.ref_project_code) as pjm,\n" +
			"\t\t\tcount(pjt.ref_project_code) as pjt,\n" +
			"\t\t\tcount(pjr1.ref_project_code) as pjr1,\n" +
			"\t\t\tcount(pjrn.ref_reportnumber_code) as pjrn\n" +
			"\t\tfrom pj_project t\n" +
			"\t\tleft join pj_contract pjc on t.project_code=pjc.refid_project_code_hide\n" +
			"\t\tleft join pj_project_plan pjp on t.project_code=pjp.ref_project_code\n" +
			"\t\tleft join pj_project_draft pjd on t.project_code=pjd.ref_project_code\n" +
			"\t\tleft join pj_project_recheck pjr on t.project_code=pjr.ref_pcode\n" +
			"\t\tleft join pj_project_appraise pja on t.project_code=pja.ref_project_code\n" +
			"\t\tleft join pj_project_borrow pjb on t.project_code=pjb.ref_project_code\n" +
			"\t\tleft join pj_project_invoice pji on t.project_code=pji.ref_project_code\n" +
			"\t\tleft join pj_project_member pjm on t.project_code=pjm.ref_project_code\n" +
			"\t\tleft join pj_project_prodess_task pjt on t.project_code=pjt.ref_project_code\n" +
			"\t\tleft join pj_project_report pjr1 on t.project_code=pjr1.ref_project_code\n" +
			"\t\tleft join pj_project_reportnumber pjrn on t.project_code=pjrn.ref_reportnumber_code\n" +
			"\t\tGROUP BY t.id,t.project_code,t.project_name,t.ref_cusname\n" +
			"\t\t union all\n" +
			"\t\tselect cc.id,cc.customer_code,cc.customer_name,cc.customer_code,-1 as pid,\n" +
			"\t\t\t\"\" as pjc,\n" +
			"\t\t\t\"\" as pjp,\n" +
			"\t\t\t\"\" as pjd,\n" +
			"\t\t\t\"\" as pjr,\n" +
			"\t\t\t\"\" as pja,\n" +
			"\t\t\t\"\" as pjb,\n" +
			"\t\t\t\"\" as pji,\n" +
			"\t\t\t\"\" as pjm,\n" +
			"\t\t\t\"\" as pjt,\n" +
			"\t\t\t\"\" as pjr1,\n" +
			"\t\t\t\"\" as pjrn\n" +
			"\t\tfrom pj_customer cc   ")
	List<Map> getCustomerProjects();

	@Select("  select t.id,t.project_code,t.project_name,t.ref_cusname,t.ref_id_cuscode as pid,\n" +
			"\t\t\tcount(pjc.refid_project_code_hide) as pjc,\n" +
			"\t\t\tcount(pjp.ref_project_code) as pjp,\n" +
			"\t\t\tcount(pjd.ref_project_code) as pjd,\n" +
			"\t\t\tcount(pjr.ref_pcode) as pjr,\n" +
			"\t\t\tcount(pja.ref_project_code) as pja,\n" +
			"\t\t\tcount(pjb.ref_project_code) as pjb,\n" +
			"\t\t\tcount(pji.ref_project_code) as pji,\n" +
			"\t\t\tcount(pjm.ref_project_code) as pjm,\n" +
			"\t\t\tcount(pjt.ref_project_code) as pjt,\n" +
			"\t\t\tcount(pjr1.ref_project_code) as pjr1,\n" +
			"\t\t\tcount(pjrn.ref_reportnumber_code) as pjrn\n" +
			"\t\tfrom pj_project t\n" +
			"\t\tleft join pj_contract pjc on t.project_code=pjc.refid_project_code_hide\n" +
			"\t\tleft join pj_project_plan pjp on t.project_code=pjp.ref_project_code\n" +
			"\t\tleft join pj_project_draft pjd on t.project_code=pjd.ref_project_code\n" +
			"\t\tleft join pj_project_recheck pjr on t.project_code=pjr.ref_pcode\n" +
			"\t\tleft join pj_project_appraise pja on t.project_code=pja.ref_project_code\n" +
			"\t\tleft join pj_project_borrow pjb on t.project_code=pjb.ref_project_code\n" +
			"\t\tleft join pj_project_invoice pji on t.project_code=pji.ref_project_code\n" +
			"\t\tleft join pj_project_member pjm on t.project_code=pjm.ref_project_code\n" +
			"\t\tleft join pj_project_prodess_task pjt on t.project_code=pjt.ref_project_code\n" +
			"\t\tleft join pj_project_report pjr1 on t.project_code=pjr1.ref_project_code\n" +
			"\t\tleft join pj_project_reportnumber pjrn on t.project_code=pjrn.ref_reportnumber_code\n" +
			"\t\twhere t.project_progress != 100 and  t.deleted=1\n" +
			"\t\tGROUP BY t.id,t.project_code,t.project_name,t.ref_cusname    ")
	List<Map> getProjectState();

	// 传参。 重点： @Param("invoiceSettingLogDO") 与 $， 试过#不行不知道为什么
	@Insert("INSERT INTO setting_log(action_type, setting_type, operator_staff_id, old_value, new_value, remark) "
			+ "VALUES  (${para.actionType}, ${para.actionType}, ${para.operatorStaffId}, '${para.oldValue}', "
			+ "'${para.newValue}', '${para.remark}');")
	int insert(@Param("para") BizCommonEntity para);
	
//	@Select(" SELECT id id,\r\n"
//			+ "usercode usercode,\r\n"
//			+ "ref_username refUsername,\r\n"
//			+ "ref_template_id refTemplateId,\r\n"
//			+ "ref_template_name refTemplateName,\r\n"
//			+ "sortno sortno,\r\n"
//			+ "score score,\r\n"
//			+ "order_id orderId,\r\n"
//			+ "create_time createTime,\r\n"
//			+ "create_id createId,\r\n"
//			+ "update_time updateTime,\r\n"
//			+ "update_id updateId FROM hr_assessment_user_record_detail"+"  limit  ${para.star},${para.end} ")
	@Select("  SELECT d.sortno,d.teamplate_id,d.ref_teamplate_name,d.ass_type,d.ass_attr_type,d.ass_attr_name,d.attr_score,d.attr_desc,d.ass_attr_desc,\r\n"
			+ "		r.ref_username,rd.ref_template_id refTemplateId,rd.ref_record_id,rd.sortno sortno2,rd.score score,\r\n"
			+ "		rd.id id,rd.usercode usercode,rd.ref_username refUsername,rd.ref_template_name refTemplateName,\r\n"
			+ "		rd.order_id orderId,rd.create_time createTime,rd.create_id createId,rd.update_time updateTime,rd.update_id updateId\r\n"
			+ " from hr_assessment_template_detail d\r\n"
			+ " left join hr_assessment_template t on d.teamplate_id = t.id\r\n"
			+ " left join hr_assessment_user_record r on d.teamplate_id = r.teamplate_id\r\n"
			+ " left join hr_assessment_user_record_detail rd on d.teamplate_id = r.teamplate_id and r.id = rd.ref_record_id and rd.sortno = d.sortno\r\n"
			+ " \r\n"
			+ "  where\r\n"
			+ "	r.id = #{para.ref_record_id} \r\n"
//			+ "	-- d.teamplate_id = '1467343706662879233'\r\n"
//			+ "	-- d.ref_teamplate_name='（2021）年度部门负责人岗位职责考核标准';\r\n"
			+ "	order by d.sortno "+"  limit  ${para.star},${para.end} ")
	List<Map> getDataList(@Param("para") Map para);  
	
	@Select(" SELECT count(1) FROM hr_assessment_user_record_detail" )
	long getDataListTotal(@Param("para") Map para);
	
	@Update(" update  hr_assessment_user_record t set t.score = (\r\n"
			+ "	SELECT sum(rd.score) from  hr_assessment_user_record_detail rd WHERE rd.ref_record_id = #{id} \r\n"
			+ ")\r\n"
			+ " WHERE t.id = #{id}  ")
	int updateAssessmentUserRecord(@Param("id") String id);
	
	@Select("  SELECT value2  FROM sys_dict_detail d \r\n"
			+ "inner join pj_project p on d.value = p.dict_project_type_sub and d.dict_name like 'dict_project_type_sub_' \r\n"
			+ "INNER JOIN pj_project_report r on r.ref_project_code = p.project_code\r\n"
			+ "where   r.order_id=#{id}   ")
	String queryForProjectCodeStr(@Param("id") String id);
	
	@Select("SELECT r.reportnum_code from pj_project_report r where r.reportnum_code like '${code}' ORDER BY r.create_time desc  limit 1  ")
	String queryProjectReportCode(@Param("code") String code);
	
	@Select(" SELECT c.id FROM `pj_project` p \r\n"
			+ "INNER JOIN pj_customer c on c.customer_code = p.ref_id_cuscode\r\n"
			+ "where p.id=#{id}   limit 1  ")
	String queryCustomerIdByProjectId(@Param("id") String id);
	
	@Select(" SELECT c.id from pj_contract c where c.refid_project_code_hide  in ( \r\n"
			+ "  SELECT p.project_code FROM `pj_project` p   where p.id=#{id}  ) limit 1  ")
	String queryContractIdByProjectId(@Param("id") String id);
	
	@Select(" SELECT c.id from pj_project_plan c where c.ref_project_code  in ( \r\n"
			+ "  SELECT p.project_code FROM `pj_project` p   where p.id=#{id}  ) limit 1;  ")
	String queryPlanIdByProjectId(@Param("id") String id);
	
	@Select(" SELECT c.id from pj_project_draft c where c.ref_project_code  in (  \r\n"
			+ "  SELECT p.project_code FROM `pj_project` p   where p.id=#{id}  ) limit 1;  ")
	String queryDraftIdByProjectId(@Param("id") String id);
	
	@Select(" SELECT c.id from pj_project_report c where c.ref_project_code  in (  \r\n"
			+ "  SELECT p.project_code FROM `pj_project` p   where p.id=#{id}  ) limit 1;  ")
	String queryReportIdByProjectId(@Param("id") String id);
	
	@Select(" SELECT c.id from pj_project_invoice c where c.ref_project_code  in (   \r\n"
			+ "  SELECT p.project_code FROM `pj_project` p   where p.id=#{id}  ) limit 1;  ")
	String queryInvoiceReportIdByProjectId(@Param("id") String id);
	
	@Select(" WITH RECURSIVE temp AS (  \r\n"
			+ "    SELECT * FROM oa_law_info r WHERE r.pid =#{id}  \r\n"
			+ "    UNION ALL \r\n"
			+ "    SELECT r.* FROM oa_law_info r,temp t WHERE t.id = r.pid\r\n"
			+ ")select * from temp ")
	List<OaLawInfoEntity> queryOaLawInfoEntityTreeBypid(@Param("id") String id);
	
//	show create table sys_user
//	ALTER DATABASE db_qixing CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci; 
//	ALTER TABLE dim_date_tcl CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci; 
	@Select(" SELECT username as 'value',real_name as 'title' from sys_user ")
	List<Map> getEditorInfoList(@Param("id") String id);

//	SELECT
//	substring_index(substring_index(A.COL,',', b.help_topic_id + 0), ',', -1)   result
//	FROM
//			(SELECT editor  COL from pj_customer where id = '1671402363726102529') A join
//	mysql.help_topic b
//	where
//    b.help_topic_id <  (LENGTH(A.COL) - LENGTH(REPLACE(A.COL, ',', '')) + 2);

	@Select(  " SELECT editor  as chain from pj_customer WHERE id = #{id}  ")
	String getEditorInfoList2(@Param("id") String id);
	
	@Update(" update pj_customer t set t.editor = #{data}  WHERE t.id = #{id} ")
	int saveCustomerEditorList(@Param("id") String id,@Param("data") String data);


	@Select(  " SELECT * from wf_hist_task o where o.order_id =  #{id} ORDER BY create_time asc   ")
	List<Map>  getWfHisTaskActors(@Param("id") String order_id);


	@Select(  " SELECT * from pj_project_report t where order_id is not null and order_status = 0 and length(reportnum_code)=0  ")
	List<Map>  getProjectFinishReportList();


	@Update(" update pj_project_report t set t.reportnum_code = #{data}  WHERE t.id = #{id} ")
	int updateReportNumber(@Param("id") String id,@Param("data") String data);

	@Update(" update pj_project_reportnumber t set t.reportnumber_code = #{data}  WHERE t.id = #{id} ")
	int updateReportNumber2(@Param("id") String id,@Param("data") String data);

}
