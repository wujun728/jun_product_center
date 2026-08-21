# AGENTS.md

## 本仓库是什么

- **产品**：七星（qixing）项目。本仓库是**迁移目标（"基座"）**：一个原厂的 RuoYi-Vue 3.9.2（Spring Boot 2.5.15，JDK 1.8），用作把老项目 `D:\workspace_github_v1\product\jun_product_center_private_qixing\` 的业务代码移植过来的基座。
- **迁移尚未开始**（`ruoyi-qixing` 模块还不存在、没有 qixing 前端页面、业务表也未导入）。权威计划在 `RuoYi-Vue-3.92-springboot2-jdk8/MIGRATION_ANALYSIS.md` 和 `MIGRATION_TODO.md` —— **这两个文件是未跟踪/未提交的；在改动构建接线之前必须先读它们。**
- 忽略根目录的 `README.md` 和根 `pom.xml` 的说明——它们是历史笔记，不是有效指南。真正的项目根是 `RuoYi-Vue-3.92-springboot2-jdk8/`（顶层聚合器的 Maven 模块）。

## 构建与运行

- **仅 JDK 8**（项目目标是 1.8；更高版本的 JDK 会在 `maven-compiler-plugin` 3.8.0 上失败）。相应切换工具链。
- 后端：在 `RuoYi-Vue-3.92-springboot2-jdk8/` 下执行 `mvn clean install -DskipTests`（入口类为 `ruoyi-admin` 中的 `com.ruoyi.RuoYiApplication`）。从仓库根目录也可以做全 reactor 构建。
- 前端（`scrm_ui/`，Vue 2.6.10 + Element UI 2.15.6，Vue CLI 4）：先 `npm install` 再 `npm run dev`。`node_modules/` 和 `package-lock.json` 已存在——直接复用（Node < 17；sass v1.26.2 在新版 Node 上会失败）。
- Lint：`npm run lint`（eslint，`.eslintrc.js`）。测试：`npm run test:unit`（jest）——很慢，很少跑。

## 开发环境（写死在本地的个人配置）

- MySQL `localhost:3307`，库 `ry-vue`，用户 `root`，密码 `mysqladmin` —— `ruoyi-admin/src/main/resources/application-druid.yml`。只有一个 profile（`spring.profiles.active: druid`）。
- Redis `localhost:6379`，无密码 —— `application.yml`。
- 后端端口 **8081**。前端开发端口 **9527**，代理 `/dev-api → http://localhost:8081`（`scrm_ui/vue.config.js`）。
- SQL 基线：`RuoYi-Vue-3.92-springboot2-jdk8/sql/`（`ry_20260417.sql` sys/gen、`ruoyi-s.sql`、`quartz.sql`）。qixing 业务表（`pj_`/`hr_`/`oa_`/`biz_`）、菜单和字典**尚未导入**。
- `.bat`/`.sh` 运行脚本和大多数 SQL 导出都是 GBK 编码——编辑/执行前请转成 UTF-8。文档文件可能出现乱码。

## 模块布局与工作流陷阱（重要）

- 自定义业务模块放在 `ruoyi-business/` 下（父 POM：`ruoyi-business`）。参考 `ruoyi-biz-demo` 的模式：相对父级的 pom、包 `com.ruoyi.web.*` / `com.ruoyi.common.core.*`、经典 MyBatis XML mapper。
- **不要重新启用基座自带的额外模块**：`ruoyi-module-flowable`（Flowable 8.0.0）、`ruoyi-module-online`、`ruoyi-module-message` 已在 `ruoyi-modules/pom.xml` 和 `ruoyi-modules-starter/pom.xml` 中被注释掉；flowable 源码已从工作树删除（git 中仍被跟踪——删除是未提交的改动）。迁移决策 D1：工作流引擎将采用**老项目的** `ruoyi-parent-flow` 家族（Flowable 6.7.2、`IFlowHandleService`、t_template、23 个 BPMN）——qixing 的 39 个 Controller 依赖 `IFlowHandleService`，而不是基座的 `IFlowTaskService`。
- Mapper/MyBatis 约定（已接好，按原样使用）：`classpath*:mapper/**/*Mapper.xml` 解析 XML mapper；`ruoyi-framework` 中的 `@MapperScan("com.ruoyi.**.mapper")`。MyBatis-Plus 3.5.3.1 与经典 MyBatis 共存——新业务代码应遵循经典的 RuoYi XML-mapper + PageHelper `startPage()` 模式。
- qixing 模块的 pom 必须显式声明 `freemarker`（基座 `dependencyManagement` 中没有）。
- 老项目的菜单 SQL 假设 `parent_id=3` 等，在这套基座上不成立——需要重写（按 MIGRATION_TODO 1.3.3，规划 ID 段 3000-3999）。

## 前端约定

- `scrm_ui` 是标准的 RuoYi-Vue 动态菜单前端（菜单来自 `sys_menu`；页面在 `src/views/`，接口在 `src/api/`）。迁移 qixing 页面时，复制 `src/api/qixing/**` 和 `src/views/qixing/**`，并通过 SQL 接菜单——不要加静态路由。
- 保持 Vue 2 + Element UI 风格；老 qixing UI 是同一套技术栈，页面可直接迁移。

## 迁移工作流程

1. 遵循 `MIGRATION_TODO.md` 的模块编译顺序（`tools → file → sms → mq-core → mq-async → im-* → message → flowable → template → seal → todo → workflow-file → workflow → biz-sdk → ... → qixing`）。不要打乱顺序。
2. 任何 pom/模块改动后，用 `mvn clean install -DskipTests` 验证所涉及的子树。
3. 尽量不修改ruoyi的原有模块，这块需要后续升级到最新版本 【D:\workspace_github_v1\product\jun_product_center_qixing\RuoYi-Vue-3.92-springboot2-jdk8\ruoyi-admin\
D:\workspace_github_v1\product\jun_product_center_qixing\RuoYi-Vue-3.92-springboot2-jdk8\ruoyi-framework\
D:\workspace_github_v1\product\jun_product_center_qixing\RuoYi-Vue-3.92-springboot2-jdk8\ruoyi-system\
D:\workspace_github_v1\product\jun_product_center_qixing\RuoYi-Vue-3.92-springboot2-jdk8\ruoyi-common\】



# 开发指引

# 全局工作规则
## 一、基础交互 & 文件管理规则（最高优先级）
1. 全程使用**简体中文**沟通，回答保持专业、简洁。
2. 每次无条件将当前会话内容（提问及回答均保存）导出为 MD 文件并保存至 `./docs/chat` 目录；文件名称根据对话核心内容自动提炼，文档名称必须为中文。
3. 工作前优先读取 `./docs` 目录历史内容，涉及过往业务、代码、方案时参考已有文档。
4. 如需生成脚本文件，统一存放至 `./script` 目录。python脚本放到 `./script/python` 目录。sql脚本放到 `./script/sql` 目录。
5. 没有说明代码要编译及运行的时候，无需编译、运行。我会自己尝试自动编译、运行，但请勿依赖此功能。
6. 在对话中发现对全局配置、依赖关系、运行方式时、系统配置、全局规则等配置，对其他的会话有益或者有效的，可以更新到当前的文档claude.md中。
7. 使用mcp的时候需要在控制台打印出log日志。mcp连接mysql的时候，需要打印出 本次查询什么数据，查询的结构，以及查询的SQL等等，查询的条件等等。
8. 当需要生成脚本的时候优先选择Python脚本，优先使用Python脚本。次选powershell脚本，次选shell脚本。
9. 当ID为较长的数字的时候，例如雪花ID，bigInteger等的时候，不可丢失ID的精度。 通过 MCP 查 MySQL 雪花 ID 时，统一用 CAST(id AS CHAR) 避免精度丢失。
10. 不能浪费时间，不要重复写代码，不要幻想，需要有理有据，根据已有进行推断，不确认的逻辑，需要先行确认，如果在执行中遇到问题，请及时反馈给使用者，或者注释、或者自行处理。
11. 没有我的允许，不可编译项目，不可运行项目；不可在完成任务后自动编译项目、运行项目；在完成任务后，提示我是否生成对话文档或者接口文档等；
12. 生成的代码需要添加注释，注释中必须包含：功能描述、参数说明、返回值说明、调用示例、CURL 示例。
13. 生成的代码需要有详细的日志，日志必须能准确的定位关键节点及步骤与流程走向，能便于定位问题，便于后续排查，解决bug。
14. 所有调用外部系统的请求或者被外部调用的接口、包括定时任务的接口，必须添加调用日志，打印完整curl请求，包括入参、出参及返回值等。
15. 生成代码里面不用写重复的工具类及方法，统一使用项目已有的工具类，或者hutools工具类，实在没有就自行实现工具类（正常都是有的）。
16. 写完代码后立马检查一下、依赖，包映入、方法调用、类引用，无异常，不能丢三落四，反复折腾浪费时间。
17. 同类型mcp连接，比如数据库连接，你不确定是那个mcp就问我或者你自己尝试一下，不要过早的下结论，mcp数据走不通、表没有等等；
18. 默认使用的id是雪花id，需要考虑丢失进度的场景；特别是mcp取id的时候，请务必使用CAST(id AS CHAR)来处理超长数字；
19. PaaS的表分两种一种是不带_PV后缀的，一种是带_PV后缀的，区别在于请求是否带预览的应用code，预览的应用code是走的_PV表，非预览应用code是走正常的表。
20. PaaS日志文件路径：D:\data\logs\app.log
21. 本地redis路径：D:\Java\Redis-x64-5.0.10\
22. 本地JDK路径：D:\Java\jdk1.8.0_181
23. 本地arthas路径：【D:\Java\arthas-boot.jar】，运行参考：【java -jar arthas-boot.jar --telnet-port 3658 --http-port 8563】
24. 注意乱码问题，请使用utf-8编码。还要控制台乱码、shell乱码、powershell乱码、CMD乱码等等
25. 将执行任务的时候觉得重要的信息、后面可能会用到的信息、规则、流程等等，汇总后精炼后(每次不超过200字)追加到【D:\workspace\scrm-paas\AGENTS.md】文档中。
26. 新增接口协议默认使用GET、POST以及JSON出入参格式（默认通用使用）。特殊情况PUT、DELETE等及XML报文等其他格式报文体需要主动提示用户确认；
27. 提前预防所有NPE、空指针异常、常见异常等错误，提前处理，并在代码里面体现。
28. 新增及修改代码，完成后必须立马检查是否修改正确，不能有最低级的语法错误或编译错误，一定得先判断是否有NPE、空指针异常、常见异常等错误，提前处理，并在代码里面体现。同时需要主要引用这块代码及方法的地方的处理。不能只改当前一点，需要顾全全局。
29. curl 请求如果带有 preview-application-code报文头，那么默认是预览模式，查询的表是带有_PV表。
30. 数据层ORM如果是mybatis或者mybatis plus，优先使用单实体CURD，复杂逻辑或者部分更新请使用注解SQL方式实现，能不写xml的sql就不写xml。


## 二、启动前置流程（核心原则：先对齐，再动手）（高优先级）
在正式编写代码、修改文件、落地方案前，严格执行以下步骤：
1. (重要)先用自己的语言复述：本次需要解决的问题、最终交付物；**明确标出所有存疑内容与自行做出的假设**。若有更优技术方案，直接提出，由使用者决策。
2. 需求信息不明确时，可向使用者提问，**单次最多提 3 个核心问题**，每个问题附带可选方案，并标注默认最优组合。
   需直至完全确认以下三点，方可继续：
    - 真实业务目标（区分字面描述与实际诉求）
    - 隐藏约束与偏好（技术栈、性能要求、兼容规则、不可修改内容等）
    - 整体实现思路、核心方案及选型原因
3. 未收到使用者明确的「可以开始」指令前，**禁止编写任何代码、修改任何文件**。

## 三、代码开发通用规范
1. 修改现有方法时，必须检查关联依赖；若存在依赖关系，同步完成依赖更新。
2. 代码类注释中，若 `author` 字段为空，统一默认填写：`@author Wujun`。
3. 新增接口必须附带完整接口文档，内容包含：接口URL、功能描述、通信协议、请求信息、入参说明、响应信息、返回值说明、调用示例、CURL 示例。
4. 代码与功能开发完成后，先自行校验功能可用性；校验通过后同步生成对应测试用例，并完成基础测试。

## 四、模型行为 & 调度策略（高优先级）
### 4.1 Sonnet 模型专属行为
若当前使用 Sonnet 模型，解读逻辑以**代码字面内容为主**，优先解答字面问题，不主动质疑原有代码设计与假设。

### 4.2 模型调用路径规则
#### （1）常规调用路径
Sonnet 负责主体开发与代码阅读 → codeGraph 辅助代码定位与依赖分析 → Opus 执行复审校验。

#### （2）模型升级切换规则
默认优先使用 Sonnet 快速探测问题复杂度，再按以下规则判定是否升级为 Opus：
- 仅 CRUD 开发、字段补全、标准化套路实现：全程使用 Sonnet
- 满足以下任一条件，**必须升级为 Opus**：
    1. 需求涉及跨 3 个及以上模块；
    2. 工作内容为否定类判断（排查缺失项、问题漏洞，而非罗列现有内容），可搭配复审机制；
    3. Sonnet 输出的方案出现自相矛盾、需要反复修订。
- 问题分析阶段优先使用 Opus，问题落地开发阶段使用 Sonnet。

## 五、codeGraph 使用规范（高优先级）
### 5.1 前置校验
启动工作后**先执行 `codegraph_status` 校验工具可用性**，再开展后续操作。

### 5.2 强制使用场景（不可用 Read/Grep 替代）
codeGraph 可用时，以下场景**必须优先调用对应能力**：
1. 分析、排查、梳理现有代码逻辑 → 使用 `codegraph_context`
2. 定位指定类名、方法名、接口路径 → 使用 `codegraph_search`
3. 修改已有方法前 → 必须调用 `codegraph_callers` + `codegraph_impact` 检查调用方与影响范围
4. 跨模块、跨服务逻辑追踪 → 使用 `codegraph_trace`
5. 输出代码影响范围结论前 → 必须通过 `codegraph_impact` 验证

### 5.3 可豁免场景（可跳过 codeGraph）
满足以下任一情况，无需调用 codeGraph：
1. 使用者已提供**精确文件路径 + 具体行号**；
2. 单文件内少于 10 行的局部小修改；
3. 纯配置文件、文档、独立脚本编辑；
4. 全新空白文件创建。


## 六、AI Code 项目编码规范（高优先级）
### 0. 通用行为铁律（最高优先级）
1. 只做需求内功能，禁止私自新增特性、额外抽象、全局重构、无关优化code...。
2. 先确认再编码：模糊需求主动询问假设、边界、多方案对比，不自行脑补实现。
3. 最小实现原则：无一次性工具抽象、无无用容错、无过度配置；函数控制 50 行内。
4. 存量代码优先对齐：完全匹配项目现有格式、库、目录，不引入新项目技术栈GitHub。
5. 高危操作必须询问：删文件、删表、强制推送、重置 Git、安装依赖前先确认。
6. 增量输出：单次最多输出 200 行 / 单个文件，完成一段做校验总结再继续稀土掘金。

### 1. 行为约束
1. 仅实现明确需求，不额外新增功能与抽象
2. 需求模糊先确认假设与边界，不自行脑补
3. 单次输出≤200行/单个文件，分步校验推进
4. 删除文件、删库、强制推送、安装依赖前必须询问

### 2. 代码风格
1. 命名：变量camelCase，类PascalCase，常量UPPER_SNAKE_CASE
2. 导入顺序：第三方包 → 内部模块 → 相对路径，空行分隔
3. TypeScript 严格模式，禁用 any，复杂类型抽至types目录
4. 函数不超过50行，优先拆分

### 3. 注释规则
1. 仅注释业务特殊逻辑「为什么」，清晰代码不注释「做什么」
2. 公共API使用JSDoc，私有代码不冗余注释
3. 清除所有console.log、debugger、无绑定TODO

### 4. 安全与异常
1. 异常抛出标准Error对象，统一接口返回 {code,message,data}
2. 敏感配置使用环境变量，禁止硬编码密钥
3. 所有入参、外部接口做参数校验

### 5. 测试与工程
1. 新功能先编写单元测试，覆盖边界场景
2. 提交遵循 Conventional Commits，提交前执行lint修复
3. 禁止直接提交main分支，新建功能分支开发
4. 
## 构建命令

```bash
** maven构建 **
maven构建工具地址：D:\Java\apache-maven-3.9.8\bin\
maven仓库地址：D:\Java\.m2\repository\


mvn clean install -Dmaven.test.skip=true
mvn clean install
mvn clean install -Dmaven.test.skip=true -P dev

```

## MCP 连接
说明：下面的两个mcp都可以连通并且可以使用，不要生成Python脚本取连接数据库了，浪费时间。
MCP 连接名称：`mysql` 是MySQL 5.7 本地开发调试的数据库
MCP 连接名称：`paas-mcp-mysql` 是MySQL 5.7 scrm-paas开发调试的数据库

配置在 `.mcp.json`，提供 MySQL/MongoDB/Redis 只读查询。

**MySQL 查询强制使用 MCP 工具**，不要建议用户手动执行查询类 SQL。禁止直接修改数据库数据，写操作生成 SQL 交给用户执行。

## CodeGraph

分析代码时优先使用 CodeGraph 工具（`codegraph_context`、`codegraph_search`、`codegraph_callers`、`codegraph_impact`）。

可跳过场景：用户已提供精确文件路径+行号、10 行以内的单文件修改、纯配置文件编辑、新建空白文件。


### 序列化 & JSON

- VO/PO/DTO **不实现** `Serializable`
- Jackson 为主（`JsonNode`、`ObjectMapper`），FastJSON 仅允许在局部作用域使用，**禁止出现在 VO/DTO 字段中**
- 禁止 API 直接返回数据库实体，必须转换为 VO

### @author 注释

如果 `@author` 为空，统一填写 `@author Wujun`。


### Service 模式

新代码直接使用 `@Service`（不创建接口），旧代码中仍有 interface+impl 模式并存。

### 日期类型

使用 `java.time.LocalDateTime/LocalDate/LocalTime`，禁止 `java.util.Date` 和 `java.sql.Timestamp`。





