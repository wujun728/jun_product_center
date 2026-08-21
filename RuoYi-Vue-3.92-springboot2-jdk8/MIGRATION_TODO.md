# 七星项目迁移 TODO 清单 v2.1（三层结构）

> 更新时间：2026-08-21（v2.1：全面比对老仓库功能后的修正）
> 分析文档：`MIGRATION_ANALYSIS.md` ＋ `docs/chat/老仓库功能盘点与TODO比对报告.md`
> 源：`D:\workspace_github_v1\product\jun_product_center_private_qixing\`（ruoyi-vue-oa 后端 + ruoyi-vue-oa-ui 前端，权威业务源；其余子目录为副本/原型/无关项目）
> 目标：`D:\workspace_github_v1\product\jun_product_center_qixing\RuoYi-Vue-3.92-springboot2-jdk8\`

### v2.1 修正记录（2026-08-21）

| # | 项 | 修正 |
|---|----|------|
| 1 | 业务表 DDL 来源 | 老 `sql/table.sql` 无业务表；改为从本地 `db_qixing` 库 SHOW CREATE TABLE 导出 38 张（详见 1.3.1） |
| 2 | 登录 RSA | 老 SysLogin 用 RsaUtils+JSEncrypt 解密 → 不迁移，基座原生明文登录（详见 2.7.2） |
| 3 | 前端 mq/contact 页 | 补 `views/mq/async/` + `api/mq/async.js`、`views/system/user/contact/`（详见 1.5/2.8） |
| 4 | 前端 12 独有组件 | customBpmn/vform/parser/Process/render/RuoYi/SerialNo/org/flow/form/SizeSelect/WangEditorExtend（详见 1.2.6/2.8.4） |
| 5 | 数据迁移源 | `db_oa_main` 无业务表，以本地克隆库 `db_qixing` 为权威数据源（详见 3.3） |
| 6 | 迁移范围 | 权威源仅 `ruoyi-vue-oa`(后端)+`ruoyi-vue-oa-ui`(前端)；`ruoyi-vue-oa-new` 与基座原型一致不迁；`jun_vue_mybatisplus` 已排除；历史 Layui 版只作功能/行为参照 |

## 顶层决策（v2 修订）

| 决策 | 结论 |
|------|------|
| D1 工作流引擎 | **以老 ruoyi-oa 的工作流为准**（ruoyi-parent-flow 全家桶整体搬迁，Flowable 6.7.2 + IFlowHandleService + t_template 体系），基座自带 `ruoyi-modules/ruoyi-module-flowable`（Flowable 8.0.0）**废弃不用、保持原样不接入** |
| D2 模块布局 | 按老项目四组父 POM 结构平移：`ruoyi-parent-flow` / `ruoyi-parent-msg` / `ruoyi-parent-biz` / `ruoyi-parent-common` + 顶层 `ruoyi-qixing` |
| D3 数据库 | qixing + workflow 表并入 ry-vue 库（localhost:3307） |
| D4 端口 | 后端沿用基座 8081（前端代理同步改） |

## 执行方式标注

- 🧑 **【用户手工】**：纯文件复制/目录搬迁/SQL 执行，无需改代码
- 🤖 **【AI 辅助】**：pom 接线、包名/版本适配、代码改造、缺陷修复
- 🤝 **【共同】**：联调排错

## 模块依赖编译顺序（重要，勿颠倒）

```
tools → file → sms → mq-core → mq-async → im-broker/im-process → message
      → flowable → template → seal → todo → workflow-file → workflow
      → biz-sdk → information/kbs/schedule/worksetting/serial → qixing
```

---

# 第一部分：qixing 模块（七星业务，39 个业务单元）

## 1.1 后端模块平移

- [ ] 1.1.1 🧑 复制老 `ruoyi-vue-oa/ruoyi-qixing/src/main/java/com/ruoyi/qixing/**` 全量到新工程同路径（39 Controller + 39 Domain + 39 Mapper + 41 Service + PrimaryKeyService + FlowFinishService）
- [ ] 1.1.2 🧑 复制 Mapper XML：老 `mapper/system/`（35 个）+ `mapper/qixing/`（4 个）→ 新 `src/main/resources/mapper/qixing/`（合并为一个目录）
- [ ] 1.1.3 🤖 编写 `ruoyi-qixing/pom.xml`：parent 从老 ruoyi 3.9.0 改挂新基座（顶层 ruoyi 3.9.2 或 ruoyi-parent-biz），依赖 ruoyi-common + ruoyi-flowable(老版) + ruoyi-workflow(老版) + freemarker 2.3.31 + mapstruct
- [ ] 1.1.4 🤖 顶层 `pom.xml` 注册 `<module>ruoyi-qixing</module>`
- [ ] 1.1.5 🤖 编译排错：核对 `com.ruoyi.common.*` 在基座 3.9.2 中的 API 差异（BaseController/AjaxResult/TableDataInfo/ExcelUtil 预期兼容，重点是 SecurityUtils 与分页）
- [ ] 1.1.6 🤖 BizCommonMapper 4 处 `${}` 动态 SQL 加白名单（tableNames/columnNames 校验），消除 SQL 注入

## 1.2 前端页面平移（scrm_ui）

- [ ] 1.2.1 🧑 复制老 `ruoyi-vue-oa-ui/src/api/qixing/`（40 个 js）→ `scrm_ui/src/api/qixing/`
- [ ] 1.2.2 🧑 复制老 `ruoyi-vue-oa-ui/src/views/qixing/`（34 个页面目录）→ `scrm_ui/src/views/qixing/`
- [ ] 1.2.3 🤖 核对 scrm_ui 请求封装与老 UI 差异：request.js 拦截器、token header、分页参数（pageNum/pageSize）、下载/导出 blob 处理
- [ ] 1.2.4 🤖 核对 vue.config.js 代理：/dev-api → http://localhost:8081
- [ ] 1.2.5 🤝 `npm run dev` 编译通过，检查老页面引用的公共组件（Pagination/RightToolbar/DictTag 等）在 scrm_ui 是否齐备，缺什么补什么
- [ ] 1.2.6 🧑 核对并补充基座缺失的自定义组件（12 个）：`customBpmn/flow/form/org/parser/Process/render/RuoYi/SerialNo/SizeSelect/vform/WangEditorExtend`，从老 UI `src/components/` 复制，并核对 package.json 依赖 diff（含 bpmn-js 扩展、富文本、spark-md5、vue-esign 等）

## 1.3 SQL 与菜单

- [ ] 1.3.1 🤖 从本地 MySQL `db_qixing` 库（用户克隆的老库，权威业务源，80 张表）用 `SHOW CREATE TABLE` 导出 38 张业务表（14 pj_ + 11 hr_ + 10 oa_ + 3 biz_，主键 varchar(64)），转 UTF-8 输出 `sql/qixing/qixing_tables.sql`（⚠️ 修正：老 `sql/table.sql` 中无业务表，勿再引用）
- [ ] 1.3.2 🧑 在 ry-vue 库执行 qixing_tables.sql 并核对 38 张业务表建成
- [ ] 1.3.3 🤖 重写 31 个 `xxxMenu.sql` 菜单脚本（老脚本 parent_id=3 的假设在 ry-vue 不成立）：统一输出 `sql/qixing/qixing_menu.sql`，菜单 ID 段 3000-3999，目录节点对齐 ry-vue 现有结构
- [ ] 1.3.4 🤖 整理 qixing 专有字典：老 `dict_data.sql` 中 `dict_xxx` 前缀字典 → `sql/qixing/qixing_dict.sql`（sys_dict_type + sys_dict_data）
- [ ] 1.3.5 🧑 执行菜单/字典 SQL，管理员角色分配 qixing 菜单权限

## 1.4 qixing 模块验证（依赖第二部分工作流就绪）

- [ ] 1.4.1 🤝 项目管理 CRUD + 导出端到端可用
- [ ] 1.4.2 🤝 PrimaryKeyService 编码生成验证（PRJ/CUS/CON/COST + 报告文号模板 `鄂齐会师审字〔${year}〕第${number}号`）
- [ ] 1.4.3 🤝 34 个页面逐一冒烟（列表/新增/编辑/删除）

## 1.5 前端补充页面（比对发现，TODO 2.8 之外）

- [ ] 1.5.1 🧑 复制 `src/views/mq/async/` + `src/api/mq/async.js`（MQ 异步日志页，对应后端 AsyncLogController）
- [ ] 1.5.2 🧑 核对 `src/views/system/user/contact/`（联系人 2 页 detail/index，基座 scrm_ui 无此页），确认后端接口是否存在并迁移/废弃

---

# 第二部分：ruoyi-oa 模块（老 OA 基础设施，工作流以老 OA 为准）

## 2.1 ruoyi-parent-common 组（最先迁，被所有模块依赖）

- [ ] 2.1.1 🧑 复制 `ruoyi-parent-common/ruoyi-tools` → 新工程（包名不变 com.ruoyi.tools）
- [ ] 2.1.2 🧑 复制 `ruoyi-parent-common/ruoyi-file`（Minio + 七牛云双存储）
- [ ] 2.1.3 🧑 复制 `ruoyi-parent-common/ruoyi-sms`（腾讯云/阿里云短信）
- [ ] 2.1.4 🧑 复制 `ruoyi-parent-common/ruoyi-todo`（待办服务）
- [ ] 2.1.5 🤖 四个 pom 的 parent/依赖版本对齐新基座（ruoyi 3.9.0 → 3.9.2；minio/qiniu/tencentcloud-sdk 版本号并入顶层 dependencyManagement）
- [ ] 2.1.6 🤖 编译通过（tools → file → sms → todo 顺序，todo 依赖 flowable/template 可暂以先迁 flowable 为前提，或先注释其流程集成代码）

## 2.2 ruoyi-flowable 引擎封装（老版 Flowable 6.7.2）

- [ ] 2.2.1 🧑 复制 `ruoyi-parent-flow/ruoyi-flowable` 全模块（含 `resources/bpmn/` 23 个 `*.bpmn20.xml`）
- [ ] 2.2.2 🤖 pom 接线：flowable-spring-boot-starter 6.7.2 版本锁定（勿用基座 8.0.0）；依赖 ruoyi-common/framework/tools
- [ ] 2.2.3 🤖 核对引擎配置类与基座冲突：老 `FlowableConfig`/`FlowServiceFactory` vs 基座 framework 自动装配（DataSource/事务管理器注入方式）
- [ ] 2.2.4 🤖 `BpmnAutoDeploy` 优化：加 `enableDuplicateFiltering()` 防止每次重启生成新流程版本
- [ ] 2.2.5 🤖 Flowable 6.7.2 建表：用老 `table.sql` 中 ACT_*/FLW_* 表（GBK→UTF-8）或官方 `flowable-6.7.2.sql`，在 ry-vue 库执行
- [ ] 2.2.6 🤝 应用启动验证 23 个 BPMN 自动部署成功（ACT_RE_PROCDEF 可查 23 条记录）

## 2.3 template / seal / workflow-file（工作流配套）

- [ ] 2.3.1 🧑 复制 `ruoyi-template`（模板管理：动态表单/正文模板）
- [ ] 2.3.2 🧑 复制 `ruoyi-seal`（在线盖章/电子印章）
- [ ] 2.3.3 🧑 复制 `ruoyi-workflow-file`（流程附件/正文）
- [ ] 2.3.4 🤖 三个 pom 接线（依赖 file/template/seal/mq-async 已就绪的前提下）
- [ ] 2.3.5 🤖 t_template 及 t_template_* 6 张表、esign 相关表结构提取到 `sql/oa/workflow_tables.sql` 并导入

## 2.4 ruoyi-workflow 业务层（IFlowHandleService 所在，核心）

- [ ] 2.4.1 🧑 复制 `ruoyi-parent-flow/ruoyi-workflow` 全模块（FlowHandleServiceImpl、草稿、回收、委托、秘书、超时任务等）
- [ ] 2.4.2 🤖 pom 接线：依赖 flowable/seal/workflow-file/biz-sdk/message/sms/worksetting（注意编译顺序，biz-sdk 需先行，见 2.6）
- [ ] 2.4.3 🤖 核对 `IFlowHandleService` 全部 28 个方法的实现依赖完整性（FlowTaskVo/FlowNextDto/FlowRecordParam 等 DTO 一并迁移）
- [ ] 2.4.4 🤖 移植 `FlowFinishService` 挂接点：老项目在流程完成监听中回调，确认监听器（FlowExecutionListener/事件订阅）随模块迁移后生效
- [ ] 2.4.5 🤖 t_workflow_* 17 张表（todo/done/entrust/secretary/recycle/my_draft/timeout_job 等）提取导入
- [ ] 2.4.6 🤝 工作流端到端验证：启动流程 → 待办 → 审批/驳回/退回/转办/委派 → FlowFinishService 回调更新业务表 dict_wf_state

## 2.5 ruoyi-parent-msg 组（workflow/message 依赖，需在 2.4 前完成）

- [ ] 2.5.1 🧑 复制 `ruoyi-parent-msg/ruoyi-mq`（mq-core + mq-async）
- [ ] 2.5.2 🧑 复制 `ruoyi-parent-msg/ruoyi-im-broker` + `ruoyi-im-process`（Netty IM）
- [ ] 2.5.3 🧑 复制 `ruoyi-parent-msg/ruoyi-message`（消息中心）
- [ ] 2.5.4 🤖 pom 接线 + netty-all/amqp 版本并入顶层依赖管理
- [ ] 2.5.5 🤖 RabbitMQ 连接配置并入基座 application.yml（老 dev 配置：111.228.33.18:5672，建议本地化改 localhost）
- [ ] 2.5.6 🤖 t_mq_async_log 表导入；IM 端口（Netty 监听）与基座不冲突确认
- [ ] 2.5.7 🤝 消息链路验证：流程审批触发站内通知（message + mq-async 链路）

## 2.6 ruoyi-parent-biz 组（biz-sdk 为 workflow 前置，其余为独立业务）

- [ ] 2.6.1 🧑 复制 `ruoyi-biz-sdk`（业务扩展 SDK，workflow 依赖它）— 编译顺序在 2.4 之前
- [ ] 2.6.2 🧑 复制 `ruoyi-information`（资讯公告）
- [ ] 2.6.3 🧑 复制 `ruoyi-kbs`（知识库，14 张 t_kbs_* 表）
- [ ] 2.6.4 🧑 复制 `ruoyi-schedule`（日程管理，t_schedule* 3 张表）
- [ ] 2.6.5 🧑 复制 `ruoyi-worksetting`（个人设置：委托/秘书）
- [ ] 2.6.6 🧑 复制 `ruoyi-serial`（编号管理，t_code_* 3 张表）
- [ ] 2.6.7 🤖 六个模块 pom 接线 + 编译（information 无 framework 依赖最简单，先拿它打通一个样例）
- [ ] 2.6.8 🤖 对应表结构（t_information/t_kbs_*/t_schedule*/t_holiday_*/t_code_*）提取到 `sql/oa/` 并导入

## 2.7 admin 层业务 Controller 平移

- [ ] 2.7.1 🧑 复制老 `ruoyi-admin` 中新增的 3 个 Controller：HolidaySettingController、HolidayWorkSettingController、AsyncLogController → 新基座 ruoyi-admin `web/controller/system/`
- [ ] 2.7.2 🤖 核对老 admin 的 `SysIndexController/SysLoginController` 等对基座 3.9.2 同名类的定制改动（如首页统计、登录扩展），有差异则合并移植
  - ⚠️ **决策（2026-08-21）**：老 `SysLoginController.login` 用 `RsaUtils.decryptByPrivateKey` 解密前端 JSEncrypt 密码 → **不迁移 RSA**，登录走基座原生明文登录，老前端去掉 jsencrypt 加密；`SysIndexController` 用基座原生（无需迁移）
- [ ] 2.7.3 🤖 `ruoyi-admin/pom.xml` 增加依赖：ruoyi-flowable/ruoyi-workflow/ruoyi-template/ruoyi-serial/ruoyi-biz-sdk/ruoyi-information/ruoyi-worksetting/ruoyi-kbs/ruoyi-schedule/ruoyi-qixing（对齐老 admin 依赖清单）
- [ ] 2.7.4 🤖 mybatis-config.xml / application.yml mapper 扫描路径确认（`classpath*:mapper/**/*Mapper.xml` 基座已一致，验证即可）

## 2.8 工作流/配套前端页面平移

- [ ] 2.8.1 🧑 复制老 UI `src/views/workflow/`（11 个子目录：todo/done/newstart/my-draft/recycle/template/flowable/dynamic-form/flow-form/main-seal/mainSeal）→ scrm_ui
- [ ] 2.8.2 🧑 复制 `src/views/kbs|schedule|serial|setting|holiday|information|notice|home|dashboard|mq|system/user/contact` → scrm_ui
- [ ] 2.8.3 🧑 复制对应 api：`src/api/workflow/`（18 js）、kbs（9）、schedule（3）、serial（2）、setting（2）、file（2）、information（1）、`mq/async.js`
- [ ] 2.8.4 🤖 核对老 UI 全局组件/指令依赖：bpmn-js 流程设计器、流程图查看器、富文本编辑器组件是否随 views 搬全（package.json 依赖 diff，缺的补装）
  - ✅ 已知需补 12 个基座缺失组件：`customBpmn/flow/form/org/parser/Process/render/RuoYi/SerialNo/SizeSelect/vform/WangEditorExtend`
- [ ] 2.8.5 🤖 工作流菜单 SQL：待办/已办/发起流程/流程模板/印章管理等菜单树 → `sql/oa/oa_menu.sql`
- [ ] 2.8.6 🤝 工作流页面冒烟：流程设计器打开、发起流程、待办审批、流程图跟踪

---

# 第三部分：其他（基座对接、配置、数据、全量验证）

## 3.1 基座侧调整

- [ ] 3.1.1 🤖 顶层 pom.xml：modules 增加四个 parent 组 + ruoyi-qixing；dependencyManagement 补 netty/rabbitmq/minio/qiniu/tencentcloud/flowable6.7.2/freemarker/mapstruct 版本
- [ ] 3.1.2 🤖 确认基座 `ruoyi-modules/ruoyi-module-flowable` 不接入（admin 无依赖、modules-starter 中引用保持注释，保留代码不删）
- [ ] 3.1.3 🤖 确认基座 ruoyi-plugins（redis/minio/netty/rabbitmq）与老 OA 自带配置的共存关系：统一走基座 starter 还是老模块自带（原则：老模块 pom 自带依赖不动，避免双数据源/双 Redis 冲突）
- [ ] 3.1.4 🤖 `MybatisPlusConfig @MapperScan("com.ruoyi.**.mapper")` 与老模块 Mapper 包兼容性验证（通配已覆盖，验证即可）

## 3.2 环境与配置迁移

- [ ] 3.2.1 🧑 参照老 `ruoyi-admin/src/main/resources/env/{dev,test,prod}` 三套环境配置，在新基座建立对应 profile（或简化为 dev/prod 两套）
- [ ] 3.2.2 🤖 application.yml 合并：RabbitMQ/Minio/Netty IM 端口/文件上传路径（ruoyi.profile: D:/ruoyi/uploadPath）注入基座配置
- [ ] 3.2.3 🤖 敏感配置环境变量化（DB_PASSWORD/RABBITMQ_PASSWORD，沿用老项目约定）
- [ ] 3.2.4 🤖 logback 日志路径核对（老 dev 默认 E:/home/ruoyi/logs）

## 3.3 数据迁移（如需带历史数据）

> ⚠️ 2026-08-21 修正：老项目原始连接库 `db_oa_main` 无业务表；**权威业务数据源已克隆至本地 `db_qixing` 库（80 张表，含 38 张业务表 + 数据）**，本阶段一律以 db_qixing 为源。

- [ ] 3.3.1 🧑 老库 `db_qixing` 业务数据导出：pj_/hr_/oa_/biz_ 38 张表 + t_workflow_*（按需）
- [ ] 3.3.2 🤖 用户/部门/角色数据映射脚本：老 sys_user(字符串主键) → ry-vue sys_user(数值主键)，含关联表外键重映射
- [ ] 3.3.3 🤖 老 Snakerflow wf_* 历史数据只读兼容（getWfHisTaskActors 端点，按需保留查询）
- [ ] 3.3.4 🤝 数据迁移验证：行数对账、关联完整性抽查

## 3.4 全量编译与集成验证

- [ ] 3.4.1 🤖 全模块 `mvn clean install -DskipTests` BUILD SUCCESS（按依赖顺序逐模块提交）
- [ ] 3.4.2 🤝 应用启动零报错：Bean 装配 / Mapper 绑定 / BPMN 自动部署 / Netty+RabbitMQ 连接
- [ ] 3.4.3 🤝 基座原生功能回归：登录、用户/角色/菜单/字典管理（确认 3.9.2 原有能力未被迁移破坏）
- [ ] 3.4.4 🤝 qixing + 工作流联合验证：项目立项 submitFlow → 三级审批 → 已立项状态回写 → 报告文号生成（recheck 流程）
- [ ] 3.4.5 🤝 前端 build:prod 产物验证

## 3.5 遗留增强项（老 TODO 继承，不阻塞本次迁移）

> 2026-08-21：对比确认，这些增强项在 `doc1/jun_product_center_private_dev_202506/`（最老 Layui 版）和 `50_jun_product_center_private/` 中有原始实现可作行为参照；老 UI 前端 `src/settings` 相关页面与新增的 12 个自定义组件一并迁移。

- [ ] 3.5.1 🤖 全部 dict_* 字段从 el-input 改为 el-select 字典下拉（31 个页面）
- [ ] 3.5.2 🤖 表格增加流程状态/当前节点/当前处理人三列 + 提交审批按钮
- [ ] 3.5.3 🤖 文件附件功能（对接 ruoyi-file Minio）
- [ ] 3.5.4 🤖 tableSelect 关联弹出选择器（选客户/选项目/选人员）
- [ ] 3.5.5 🤖 PjCustomer 编辑页多 Tab 布局（客户基本/营业/开票/其他 4 个 tab）
- [ ] 3.5.6 🤖 补充高级搜索字段（对照老 Layui static6/{module}/list.html）
- [ ] 3.5.7 🤖 HR 招聘流程独立页面（面试/Offer/录用/入职/转正/离职，当前仅简历管理页）
- [ ] 3.5.8 🤖 批量提交流审按钮（工作流就绪后）
- [ ] 3.5.9 🤖 表格创建人"我的"数据权限标识

---

## 推荐执行批次（考虑编译依赖）

| 批次 | 内容 | 前置 |
|------|------|------|
| 批次1 | 3.1 基座侧调整 + 2.1 parent-common 组 + 2.2 flowable | 无 |
| 批次2 | 2.5 parent-msg 组 + 2.6 biz-sdk | 批次1 |
| 批次3 | 2.3 template/seal/workflow-file + 2.4 workflow | 批次2 |
| 批次4 | 1.1 qixing 后端 + 2.6 其余 biz 模块 + 2.7 admin 聚合 | 批次3 |
| 批次5 | 1.2/2.8 全部前端平移 + 1.3/2.x 各 SQL | 批次4 |
| 批次6 | 3.2 配置 + 3.4 全量验证 + 1.4 qixing 验证 | 批次5 |
| 批次7 | 3.3 数据迁移 + 3.5 遗留增强 | 按需 |

---

**当前状态**：v2.1 清单完成（已完成老仓库功能全面比对并修正遗漏）
**下一步**：批次1（顶层 pom 调整 + tools/file/sms/todo 平移 + 老 flowable 平移）
