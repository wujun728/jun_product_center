1. 客户基本信息
2. 客户关联项目(关联查询)
3．合同(业务约定书)
4．项目管理(清单)
4．项目管理(查询)
4．项目管理(立项)

4．项目管理(总览)
4．项目管理(公共信息)
4．项目管理(业务约定书)
4．项目管理(项目计划)

4．项目管理(底稿)
4．项目管理(审批)
4．项目管理(发票&收款)
4．项目管理(团队成员&结算分成)

5．报告查询
6．报告归档
7. 项目开票申请
8. 项目开票查询
9. 项目发票
10．我的信息
11．人员信息
12．员工定级及考核
13．工资审核发放
14．请假管理
15．考勤管理

16．最新通知
17．行业资讯
18．政策法规(文件)
19．公司制度
20．培训学习

21．组织机构管理
22．账号管理
23．菜单管理
24．角色管理
25．权限管理

1、行政管理-月度需求采购收集
一级部门
二级部门
申请人
需求数量
需求数量
申请时间
审批状态
操作
2、财务-报销
费用编号:
BZ-211103-SYJ0
费用金额:
0.00
货币币种:
人民币
报销人:
[B-82921]吴俊
报销人部门:
产品部-金融技术服务部
报销人岗位类型:
C2-管理
办理人:
[B-82921]吴俊
办理人部门:
产品部-金融技术服务部
是否列入预算:
报销人公司主体:
武汉佰钧成技术有限责任公司
费用归属公司主体:
武汉佰钧成技术有限责任公司
年份:
2021
费用类型:
说明:
111
费用明细
费用明细列表(展开记录以查看发票信息和分摊信息)
受款人
附件信息
无附件信息
审批记录

1、行政小助手
财务制度及相关表格
财务相关制度及文档
《员工借款及费用报销规定V7.0》
《000-FM-P017财务单据抽查制度V1.0》
表格模板
《000-FM-T020财务表单模板集V3.1》
《000-FM-T007会议费预算模板V3.0》
《000-FM-T010咨询与鉴证服务费预算模板V2.0》
《000-FM-T011软件采购费预算模板V2.0》
《000-FM-T012广告和宣传费预算模板V2.0》
《000-FM-T013招聘费预算模板V2.0》
公司开票信息
公司开票信息汇总表

3、招聘
招聘
面试候选人列表
面试汇总

录用
录用审批
Offer发放
入职流程

薪酬
1、考勤查看
2、异地办公申请
3、请假
4、工资单

吴俊[B-82921]

产品部-金融技术服务部

本月工资单全部展开
实发工资9849.43
工资期间2021/09/17-2021/09/30
税款所属期2021-10
标准月薪23000.00
本月月薪11000.00
税前补发0.00
税前扣款62.50
请假记录
缺勤记录
收入10937.50
社保扣款844.60
公积金扣款205.00
个税0.00
税后补发0.00
税后扣款38.47
调薪信息
社保公积金费用信息
累计专项附加扣除0.0
个税 0.00



已完成事项：
1、系统首页-模板已建立-图标数据接口待开发，完成度50%；
2、项目管理-业务表接口及业务开发完成，目前调试中，同时工作流正在匹配中，目前完成度75%；
3、人事管理-业务表接口开发完成，考核正在开发，目前调试中，目前完成度60%；
4、行政管理，业务表及接口开发完成，目前调试中，完成度75%；
5、招聘管理，目前未启动，漆总提的入职离职等
6、工作流引擎目前引入完成，各种工作流正在匹配中；
7、之前的前端模板由缺陷，重新导入了新的模板；
8、梳理了项目的人员角色部门等，关于各个角色的数据权限的功能的开发；

待办事项：
1、补充齐兴会计师事务所组织架构信息及组织架构图；
2、补充齐兴会计师事务所人员清单、人员的角色清单；
3、项目立项工作流
4、项目审核工作流
5、项目文号工作流
6、办公用品领用，办公用品申购流程；
7、培训学习
8、项目底稿
9、项目审批
10、项目日报周报
11、项目立项流程
12、员工定级与考核流程
12、工资审核与发放流程

3、项目立项，流程中-新增项目风险-风险级别；
4、项目立项，新增是否需要项目后评价；
5、新增项目后评价功能，根据项目规模-项目完成后评价自评，根据参与项目的角色给出每个人的评价，有项目权限的人员均可见，但是只能修改自己的评价；
	部门经理(二级三级)，领导层评估（调度人员），督导人员，签字会计师等评价
6、项目进度功能-PM反馈项目进度，填写项目日报及周报
7、督导人员介入(根据项目的风险级别)，一般是签字会计师
8、新增出差、外出、请假流程
9、新增人员招聘模块：招聘，入职，转正，离职




select t.id,t.project_code,t.project_name,t.ref_cusname,
count(pjc.refid_project_code_hide) as pjc,
count(pjp.ref_project_code) as pjp,
count(pjd.ref_project_code) as pjd,
count(pjr.ref_pcode) as pjr,
count(pja.ref_project_code) as pja,
count(pjb.ref_project_code) as pjb,
count(pji.ref_project_code) as pji,
count(pjm.ref_project_code) as pjm,
count(pjt.ref_project_code) as pjt,
count(pjr1.ref_project_code) as pjr1,
count(pjrn.ref_reportnumber_code) as pjrn

from pj_project t
left join pj_contract pjc on t.project_code=pjc.refid_project_code_hide
left join pj_project_plan pjp on t.project_code=pjp.ref_project_code
left join pj_project_draft pjd on t.project_code=pjd.ref_project_code
left join pj_project_recheck pjr on t.project_code=pjr.ref_pcode
left join pj_project_appraise pja on t.project_code=pja.ref_project_code
left join pj_project_borrow pjb on t.project_code=pjb.ref_project_code
left join pj_project_invoice pji on t.project_code=pji.ref_project_code
left join pj_project_member pjm on t.project_code=pjm.ref_project_code
left join pj_project_prodess_task pjt on t.project_code=pjt.ref_project_code
left join pj_project_report pjr1 on t.project_code=pjr1.ref_project_code
left join pj_project_reportnumber pjrn on t.project_code=pjrn.ref_reportnumber_code
GROUP BY t.id,t.project_code,t.project_name,t.ref_cusname




SELECT
	t.id,
	t.project_code,
	t.project_name AS name,
	(CONCAT(t.id, '.json')) AS url,
	t.project_desc as 'desc',
	'false' as mobile,
	t.dict_project_status as status1,
	v1.label as status,
	t.project_starttime as actualStartTime,
	t.project_endtime as actualEndTime,
	t.project_starttime as estimatedStartTime,
	t.project_endtime as estimatedEndTime,
	t.project_progress as process,
	(case when t.dict_project_status != 9 and (TO_DAYS(NOW()) - TO_DAYS(t.project_endtime ) <= 7) then 'false' else 
	'true' end) as delay,
	t.dict_project_type_sub as technology1,
	v2.label as technology,
	t.ref_cusname as demander,
	t.dict_project_type as type1,
	v3.label as type,
	t.ref_project_manager as manager,
	t.ref_undertak_tperson_do as charge,
	'' as affiliate,
	(select count(1) from pj_project_prodess_task t where t.ref_project_code = t.ref_project_code) as total,
	(select count(1) from pj_project_prodess_task t where t.ref_project_code = t.ref_project_code and t.task_progress=100) as resolved,
	(select count(1) from pj_project_prodess_task t where t.ref_project_code = t.ref_project_code and t.task_progress !=100) as unsolved,
	t.remark as remark
FROM
	pj_project t
left join dict_view v1 on v1.value=t.dict_project_status and v1.name='dict_project_status'
left join dict_view v2 on v2.value=t.dict_project_type_sub and v2.name='dict_project_type_sub'
left join dict_view v3 on v3.value=t.dict_project_type_sub and v3.name='dict_project_type' 
 
