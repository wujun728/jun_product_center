# 七星（qixing）项目迁移分析文档 —— 迁入 RuoYi-Vue-3.92 基座

> 生成时间：2026-08-20（v2：工作流方向调整）
> 老项目（迁移源）：`D:\workspace_github_v1\product\jun_product_center_private_qixing\`
> 新基座（迁移目标）：`D:\workspace_github_v1\product\jun_product_center_qixing\RuoYi-Vue-3.92-springboot2-jdk8\`
> 配套 TODO 清单：`MIGRATION_TODO.md`（三层结构：qixing 模块 / ruoyi-oa 模块 / 其他）

## 顶层决策（v2 修订）

**工作流以老 ruoyi-oa 为准**：整体搬迁 `ruoyi-parent-flow` 全家桶（ruoyi-flowable 6.7.2 + ruoyi-workflow + ruoyi-template + ruoyi-workflow-file + ruoyi-seal，含 `IFlowHandleService`/t_template 体系/23 个 BPMN），基座自带 `ruoyi-modules/ruoyi-module-flowable`（Flowable 8.0.0）**不接入**（2.3 节的差异分析转为背景知识，不再是适配目标）。

---

## 一、迁移源盘点（老项目）

老项目根目录下的历史分析文档（本次已全部复核）：

| 文档 | 结论摘要 |
|------|---------|
| `QIXING_MIGRATION_PLAN.md` | 早期迁移计划：业务代码 → ruoyi-qixing 模块（已执行完毕） |
| `TODO.md` | 迁移进度：老库 81 张表中 35 张业务表已迁移；工作流 Snakerflow → Flowable 待替换 |
| `session_record_2026-06-16.md` | 已完成 Snaker→BPMN 转换（28→23 个流程）、29 个 Controller 加 submitFlow、BUILD SUCCESS |
| `workflow_migration_context.md` | 工作流迁移完整上下文：FlowFinishService 23 个流程回调、t_template 初始化 SQL |
| `未迁移内容.md` | 前端功能差距：字典下拉、工作流状态列、附件、tableSelect、多 Tab 编辑等 8 项 |
| `可搬迁模块分析.md` | PrimaryKeyService（编码生成）、文件端点、流程回调、七牛云存储 |
| `模块归类管理方案.md` | 老项目后端 6 原生模块 + 21 自定义模块的分组方案 |

### 1.1 老项目核心结构

```
jun_product_center_private_qixing\
├── ruoyi-vue-oa\                    # 老后端（SpringBoot 2.5.15, JDK8, RuoYi 3.9.0 深度改造版）
│   ├── ruoyi-qixing\                # ★ 七星业务模块（迁移核心，39 套四层代码）
│   ├── ruoyi-parent-flow\           # flowable / workflow / workflow-file / template / seal
│   ├── ruoyi-parent-msg\            # im-broker / im-process / message / mq
│   ├── ruoyi-parent-biz\            # information / kbs / schedule / worksetting / serial / biz-sdk
│   ├── ruoyi-parent-common\         # file / sms / todo / tools
│   └── ruoyi-admin\resources\env\   # dev/test/prod 三套环境配置
├── ruoyi-vue-oa-ui\                 # 老前端（Vue 2.6.14 + Element UI 2.15.14）
│   └── src\views\qixing\            # 34 个业务页面
│   └── src\api\qixing\              # 39 个 API 文件
└── doc1\jun_product_center_private_dev_202506\   # 最老 Layui 版源码（参考用）
```

### 1.2 ruoyi-qixing 模块清单（39 个业务单元）

**后端结构**（`com.ruoyi.qixing`，四层：controller / domain / mapper / service+impl）：

| 领域 | Controller（同结构 domain/mapper/service 均齐备） |
|------|--------------------------------------------------|
| 项目管理 (14) | PjProject, PjCustomer, PjContract, PjProjectMember, PjProjectPlan, PjProjectDaily, PjProjectDraft, PjProjectInvoice, PjProjectBorrow, PjProjectAppraise, PjProjectRecheck, PjProjectReport, PjProjectReportnumber, PjProjectProdessTask |
| HR人事 (12) | HrUserResume, HrUserInterview, HrUserOffer, HrUserHire, HrUserEntryReported, HrUserBecomeMember, HrUserDimission, HrAssessmentTemplate, HrAssessmentTemplateDetail, HrAssessmentUserRecord, HrAssessmentUserRecordDetail |
| OA办公 (10) | OaLawInfo, OaLearnInfo, OaNotesInfo, OaOfficeCount, OaOfficeCount2, OaPomsWorkmarksClaimExpense, OaPomsWorkmarksLeave, OaPomsWorkmarksOutsite, OaPomsWorkmarksPayroll, OaPomsWorkmarksWorktimes |
| 通用 (3) | BizCommon（通用审批/流程查询）, BizMail（邮件）, BizTest（测试） |

**特殊服务类**（非 CRUD 四层）：
- `PrimaryKeyService` — 基于 Redis+FreeMarker 的业务编码生成（PRJ/CUS/CON/COST 前缀 + 报告文号模板 `鄂齐会师审字〔${year}〕第${number}号`）
- `FlowFinishService` — 23 个流程完成回调（更新 `dict_wf_state`/`order_status`，recheck 流程生成报告文号）

**Mapper XML 分布**：`resources/mapper/system/`（35 个）+ `resources/mapper/qixing/`（4 个：BizCommon/BizMail/BizTest/OaPomsWorkmarksTimes）

**SQL 脚本**：`ruoyi-qixing/sql/` 下 31 个 `xxxMenu.sql` 菜单脚本 + `ruoyi-vue-oa/sql/`（table.sql 116 表 / data.sql / init_template_qixing.sql 23 条 t_template）

### 1.3 前端清单

- 34 个页面：`src/views/qixing/{appraise,bizCommon,bizmail,biztest,borrow,contract,count,count2,customer,daily,detail,dimission,draft,expense,hire,info,interview,invoice,leave,member,offer,outsite,payroll,plan,project,recheck,record,report,reported,reportnumber,resume,task,template,worktimes}/index.vue`
- 39 个 API：`src/api/qixing/*.js`（含 law.js/learn.js/notes.js/becomeMember.js/templateDetail.js/recordDetail.js 等 6 个路由冲突修复后新增文件）
- 工作流定义：23 个 `*.bpmn20.xml`（`ruoyi-flowable/src/main/resources/bpmn/`）+ `BpmnAutoDeploy.java`

---

## 二、迁移目标盘点（新基座 RuoYi-Vue-3.92）

### 2.1 后端结构

```
RuoYi-Vue-3.92-springboot2-jdk8\
├── pom.xml                     # ruoyi 3.9.2, SpringBoot 2.5.15, JDK8, mybatis-plus 3.5.3.1
├── ruoyi-admin\                # Web入口（依赖 tenant/oss/datarule/biz-demo）
├── ruoyi-framework\            # MyBatisConfig + MybatisPlusConfig(@MapperScan("com.ruoyi.**.mapper"))
├── ruoyi-system\ ruoyi-common\ ruoyi-quartz\ ruoyi-generator\
├── ruoyi-modules\
│   ├── ruoyi-module-flowable\  # ★ Flowable 8.0.0 封装（IFlowTaskService，与老项目 API 不同）
│   ├── ruoyi-module-form\ ruoyi-module-online\ ruoyi-module-oss\
│   ├── ruoyi-module-tenant\ ruoyi-module-datarule\ ruoyi-modules-starter\
├── ruoyi-plugins\              # redis/minio/alioss/netty/rabbitmq/websocket
├── ruoyi-business\             # ruoyi-biz-demo + ruoyi-scene-auth（自定义业务模块目录）
├── scrm_ui\                    # 前端（Vue 2.6.10 + Element UI 2.15.6，RuoYi 风格）
└── sql\                        # ry_20260417.sql（21 张 sys/gen 表）+ ruoyi-s.sql + quartz.sql
```

### 2.2 关键技术差异比对

| 维度 | 老项目 ruoyi-vue-oa | 新基座 RuoYi-Vue-3.92 | 迁移动作 |
|------|--------------------|-----------------------|---------|
| 父 POM 版本 | ruoyi 3.9.0 | ruoyi 3.9.2 | qixing pom parent 需改指向新基座 |
| ORM | MyBatis（原生） | MyBatis + MyBatis-Plus 3.5.3.1 双配置 | XML mapper 可直接迁移；MapperScan 通配 `com.ruoyi.**.mapper` 兼容 |
| mapper 扫描 | `classpath*:mapper/**/*Mapper.xml` | `classpath*:mapper/**/*Mapper.xml`（一致） | 无需改 |
| 工作流 | ruoyi-flowable(自研封装 `IFlowHandleService`) + ruoyi-workflow(模板/草稿/回收) | ruoyi-module-flowable 8.0.0（`IFlowTaskService`，社区版封装） | **最大差异点**，见 2.3 |
| 业务模块目录 | ruoyi-parent-biz 分组 | ruoyi-business（flat） | 新建 `ruoyi-business/ruoyi-qixing` 或顶层平铺 |
| 前端 | ruoyi-vue-oa-ui (vue 2.6.14 / element 2.15.14) | scrm_ui (vue 2.6.10 / element 2.15.6) | 同为 Vue2+ElementUI，页面可平移 |
| 数据库 | db_oa_main (localhost:3307) | ry-vue (localhost:3307) | 表结构脚本需导入 ry-vue 库 |
| 服务端口 | 8080 (env/dev) | 8081 | 注意前端代理 |
| 消息/文件 | RabbitMQ+Minio+Netty IM | ruoyi-plugins(rabbitmq/minio/netty) 均具备 | qixing 本体不依赖，仅老 OA 全家桶依赖 |

### 2.3 工作流差异（核心风险点）

老项目 qixing Controller 依赖两个外部包：
```java
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.workflow.service.IFlowHandleService;   // startFlow(flowTaskVo) 等 28 个方法
```

新基座 `ruoyi-module-flowable` 提供的是**不同的 API**：
- `IFlowTaskService`：`complete(FlowTaskVo)` / `taskReject` / `stopProcess` / `myProcess` 等（Flowable 8.0.0）
- `FlowTaskVo` 包路径同为 `com.ruoyi.flowable.domain.vo.FlowTaskVo`（字段兼容性需核对）
- 无 `IFlowHandleService`、无 t_template 模板体系、无 FlowFinishService 回调挂载点

**结论**：39 个 Controller 的 `submitFlow` 端点不能直接编译通过，需二选一：
- 方案 A（推荐）：在新基座新建 `ruoyi-workflow` 适配层，按老项目接口签名实现 `IFlowHandleService`（内部委托 `IFlowTaskService`），同时移植 `FlowFinishService` 挂到流程结束监听器；
- 方案 B：批量改写 39 个 Controller，去掉 IFlowHandleService，直接调 `IFlowTaskService.startProcess`（改动面大，不推荐）。

### 2.4 qixing 模块实际外部依赖（已扫描 import）

老 qixing 模块 Java 文件仅依赖：`com.ruoyi.common.*`、`com.ruoyi.flowable.domain.{dto,vo}.*`、`com.ruoyi.workflow.{module.FlowRecordParam, service.IFlowHandleService}`、`freemarker.*`。
→ 除工作流两项外全部与基座兼容，`freemarker` 需在 qixing pom 中显式声明（基座 parent 未管理）。

---

## 三、迁移方案

### 3.1 后端迁移

1. 新建 `ruoyi-business/ruoyi-qixing` 模块（参照 ruoyi-biz-demo 的 pom 写法，parent 指向 `ruoyi-business`，dependencies 加 `ruoyi-common` + `freemarker` + 可选 `ruoyi-module-flowable`）。
2. 复制老 `ruoyi-qixing/src/main/java/com/ruoyi/qixing/**` 全量（39 四层 + PrimaryKeyService + FlowFinishService）。
3. Mapper XML 复制到 `src/main/resources/mapper/qixing/`（老项目 system/qixing 两目录合并为一个，避免与基座 system 冲突）。
4. `ruoyi-business/pom.xml` 增加 `<module>ruoyi-qixing</module>`；`ruoyi-admin/pom.xml` 增加依赖（或经 modules-starter 聚合）。
5. 工作流适配：按 2.3 方案 A 实现 `IFlowHandleService` 适配层（放入 ruoyi-qixing 内部 `workflow` 包或独立 `ruoyi-module-workflow`）。
6. BPMN 迁移：23 个 `*.bpmn20.xml` 复制到 `ruoyi-module-flowable/src/main/resources/bpmn/`，移植 `BpmnAutoDeploy`（注意加 `enableDuplicateFiltering()` 优化）。
7. 挂接 `FlowFinishService`：在新基座 Flowable 全局事件监听（ProcessCompleteListener）中调用。

### 3.2 前端迁移（scrm_ui）

1. `src/api/qixing/` 整目录复制（39 个 js）。
2. `src/views/qixing/` 整目录复制（34 个页面）。
3. 菜单通过后端 sys_menu 动态路由加载（scrm_ui 为 RuoYi 标准动态菜单模式），需执行菜单 SQL。
4. 核对 request.js 的 baseURL 与新后端 8081 端口代理（vue.config.js devServer.proxy）。

### 3.3 SQL 与配置迁移

| 内容 | 来源（老） | 目标（新） |
|------|-----------|-----------|
| 35 张业务表结构 | `ruoyi-vue-oa/sql/table.sql`（含其中 pj_/hr_/oa_/biz_ 表） | 提取为 `sql/qixing/qixing_tables.sql`，在 ry-vue 库执行 |
| 菜单脚本 | `ruoyi-qixing/sql/*Menu.sql`（31 个）+ `sql/init_template_qixing.sql` | 合并整理到 `sql/qixing/` |
| 字典数据 | `sql/qixing/dict_data.sql`（qixing 专有字典） | 导入 ry-vue sys_dict_type/sys_dict_data |
| BPMN 定义 | 23 个 bpmn20.xml | 随代码自动部署 |
| t_template 初始化 | init_template_qixing.sql | 若采用模板体系则导入；若简化则改由菜单+流程 key 直连 |

### 3.4 老 TODO.md 遗留事项继承

- OA 工资/工时/办公统计：老项目已有代码（Payroll/Worktimes/Count/Count2 Controller 齐全），随本次整体迁移带过来，无需单独开发。
- HR 招聘流程前端页面：老项目只有简历管理独立页，其余招聘环节页面仍缺（继承遗留项）。
- 前端功能差距 8 项（字典下拉、流程状态列、附件、tableSelect、多 Tab、高级搜索、数据权限标识、批量提交流审）：继承为后续增强任务。

---

## 四、风险清单

| # | 风险 | 等级 | 缓解 |
|---|------|------|------|
| 1 | IFlowHandleService/IFlowTaskService API 不兼容，39 个 Controller 编译失败 | 高 | 适配层方案 A；先跑通 1 个模块再批量 |
| 2 | Flowable 6.7.2（老） vs 8.0.0（新）BPMN 兼容性 | 高 | BPMN 2.0 标准向下兼容，逐一部署验证 |
| 3 | 老 table.sql 为 GBK 编码导出，直接执行中文注释乱码 | 中 | 转 UTF-8 后执行 |
| 4 | FlowFinishService 的 `${}` 动态 SQL 注入风险（BizCommonMapper） | 中 | 迁移时做表名/列名白名单 |
| 5 | 菜单 ID 冲突（老脚本 parent_id=3 等假设在新库不成立） | 中 | 重写菜单 SQL，使用新库目录节点 |
| 6 | t_template 体系在新基座不存在 | 中 | 适配层内简化为 defKey 直连，或导入模板表 |
| 7 | MyBatis-Plus 与纯 MyBatis XML 共存（分页插件差异） | 低 | qixing XML 使用 PageHelper startPage() 模式，基座同时引入了 pagehelper，需验证 |
| 8 | 端口/数据源/Redis 环境差异 | 低 | 统一走基座 application-druid.yml |

---

## 五、验证清单（迁移完成的定义）

- [ ] `mvn clean install -DskipTests` 全模块 BUILD SUCCESS
- [ ] 应用启动无 Bean 缺失 / Mapper 绑定异常
- [ ] 23 个 BPMN 流程自动部署成功（ACT_RE_PROCDEF 可查）
- [ ] 菜单加载：七星项目管理/HR/OA 目录可见
- [ ] 项目管理 CRUD + 导出可用
- [ ] submitFlow 启动流程 → 待办 → 审批 → FlowFinishService 回调更新业务状态
- [ ] PrimaryKeyService 编码生成（PRJ/CUS/CON/COST）可用
- [ ] scrm_ui `npm run dev` 编译通过，34 个页面路由可达

---

## 六、工作量估算

| 阶段 | 内容 | 估算 |
|------|------|------|
| P1 | 后端 qixing 模块平移 + pom 接入 + 编译通过 | 2 天 |
| P2 | 工作流适配层（IFlowHandleService 实现 + FlowFinishService 挂接 + BPMN 部署） | 3 天 |
| P3 | 前端 api/views 平移 + 菜单 SQL + 联调 | 2 天 |
| P4 | SQL 脚本整理（表/字典/菜单，GBK→UTF-8） | 1 天 |
| P5 | 功能验证（CRUD + 流程端到端）与缺陷修复 | 3 天 |
| 合计 | | **约 11 人天** |
