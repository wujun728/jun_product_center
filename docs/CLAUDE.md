# CLAUDE.md

本文件为 Claude Code (claude.ai/code) 在此代码仓库中工作时提供指导。

重要： D:\workspace_github_2026\jun_base\ruoyi-vue-oa\jun_ui_sa_admin 下面所有的功能跟页面，优先使用httpVueLoader来加载，特别是来源页面是vue页面的时候，不用重写为html的vue的模式。

## 项目概览

RuoYi-Vue-OA 是一个完整的企业 OA（办公自动化）系统，采用 Spring Boot 后端和 Vue.js 前端构建。系统包含工作流管理（Flowable）、实时消息（Netty）、文件服务（Minio）和消息队列（RabbitMQ）。

**仓库结构：**
- `ruoyi-vue-oa/` - 后端（Spring Boot 多模块 Maven 项目）
- `ruoyi-vue-oa-ui/` - 前端（Vue 2 单页应用）
- `jun_ui_sa_admin/` - 附加管理界面

**版本：** 3.9.1（基于 RuoYi-Vue 3.9.1）
**技术栈：** Spring Boot 2.5.15、Java 8、Vue 2.6.14、Element UI 2.15.14

## 常用开发命令

### 后端 (ruoyi-vue-oa)

**构建和运行：**
```bash
cd ruoyi-vue-oa

# 使用 Maven 构建（默认：dev 配置）
mvn clean package

# 为特定环境构建
mvn clean package -P test  # 或 -P prod

# 运行应用
./ry.sh start              # Linux/Mac
ry.bat start               # Windows

# 或直接使用 Maven 运行
mvn spring-boot:run

# 停止应用
./ry.sh stop
```

**数据库设置：**
```bash
# 导入 SQL 脚本（需要 MySQL）
mysql -u root -p < sql/table.sql
mysql -u root -p < sql/data.sql
```

**测试：**
- 未配置自动化测试套件；通过 Swagger UI 进行手动测试：`http://localhost:8080/swagger-ui/`

### 前端 (ruoyi-vue-oa-ui)

**前置要求：** Node 14+（Vue 2 项目）

**开发：**
```bash
cd ruoyi-vue-oa-ui

# 安装依赖
npm install

# 启动开发服务器（带后端代理）
npm run dev
# 访问地址：http://localhost:80

# 生产环境构建
npm run build:prod

# 预发布环境构建
npm run build:stage
```

**代码检查：**
```bash
# package.json 中没有明确的 lint 命令
# 项目中配置了 ESLint 但未作为脚本暴露
```

## 高层架构

### 后端多模块结构

**核心框架模块：**
- **ruoyi-admin** - 主入口，REST API 端点，Spring Boot 应用
- **ruoyi-framework** - 核心基础设施：Spring Security、JWT 认证、Redis 配置、AOP 切面、CORS
- **ruoyi-system** - 系统域：用户、角色、部门、菜单、权限
- **ruoyi-common** - 共享工具类、基础类、常量
- **ruoyi-quartz** - 定时任务的 Quartz 调度器
- **ruoyi-generator** - 基于 Velocity 的数据库表代码生成器

**业务/OA 模块：**
- **ruoyi-workflow** - 工作流编排和流程管理
- **ruoyi-flowable** - Flowable 6.7.2 引擎集成（BPMN 流程执行）
- **ruoyi-todo** - 工作流审批的任务/待办管理
- **ruoyi-template** - 动态表单模板设计器和管理
- **ruoyi-file** - 集中式文件服务（Minio、阿里云 OSS、腾讯云 COS、FastDFS、本地磁盘）
- **ruoyi-seal** - 数字印章/签名及 PDF 操作（iText）
- **ruoyi-serial** - 可自定义规则的编号/序列号生成
- **ruoyi-message** - 统一消息和通知
- **ruoyi-sms** - 短信集成（腾讯云、阿里云）
- **ruoyi-information** - 新闻和公告
- **ruoyi-schedule** - 日历和日程安排
- **ruoyi-kbs** - 知识库管理
- **ruoyi-worksetting** - 工作设置和偏好
- **ruoyi-workflow-file** - 工作流与文件附件的集成
- **ruoyi-biz-sdk** - 常用工作流操作的业务 SDK

**IM 和消息队列模块：**
- **ruoyi-im-broker** - 独立的 Netty TCP socket 服务器，用于实时消息（独立服务）
- **ruoyi-im-process** - IM 消息路由和处理
- **ruoyi-mq** - RabbitMQ 集成，使用 Spring AMQP 进行异步任务处理

**模块依赖模式：**
所有业务模块都依赖于 `ruoyi-common` 和 `ruoyi-framework`。`ruoyi-admin` 模块聚合了所有其他模块，是主要的可部署 JAR。

### 后端关键技术

**认证与安全：**
- Spring Security 5.7.12，基于 JWT token 的身份验证
- 自定义 `JwtAuthenticationTokenFilter` 进行无状态验证
- Token 过期时间：2 小时（可通过 `token.expireTime` 配置）
- BCrypt 密码加密
- 方法级安全控制，使用 `@PreAuthorize`、`@Secured`

**数据库与持久化：**
- Druid 连接池
- MyBatis 配合 PageHelper 分页插件
- 多数据库支持：MySQL、Oracle、PostgreSQL、SQL Server、MariaDB、达梦 DM
- 从 `com.ruoyi.**.domain` 包扫描实体类

**缓存与分布式锁：**
- Redis（Lettuce 客户端）用于缓存和会话管理
- Redisson 用于分布式锁
- FastJson2JsonRedisSerializer 用于 Redis 序列化

**工作流引擎：**
- Flowable 6.7.2 用于 BPMN 流程执行
- 流程定义存储在数据库中
- 自定义权限处理（排除 Flowable Spring Security）

**实时消息：**
- Netty TCP socket 服务器（`ruoyi-im-broker`）作为独立服务
- Socket 层的 JWT 认证
- 基于 Redis 的分布式消息传递用于通道管理
- WebSocket 端点：`ws://localhost:8544/im`

**消息队列：**
- RabbitMQ 配合主题交换机
- 预配置队列：flow-router、todo-router、system-router、message-notice-router、sms-router 等
- 用于工作流、待办、通知的异步处理

**文件服务：**
- 默认：Minio（S3 兼容）在 `http://localhost:9000`
- 同时支持：阿里云 OSS、腾讯云 COS、七牛云、FastDFS、本地磁盘
- 文档转换：JodConverter 配合 LibreOffice
- 上传限制：每个文件/请求 500MB

### 后端配置结构

**基于环境的配置：** `src/main/resources/env/{env}/`
- 配置文件：`dev`（默认）、`test`、`prod`
- 使用 Maven profile 构建：`mvn clean package -P prod`

**主应用类：** `com.ruoyi.RuoYiApplication`
- 位置：`ruoyi-admin/src/main/java/com/ruoyi/RuoYiApplication.java`
- 排除 DataSourceAutoConfiguration 以使用自定义数据源

**关键配置文件：**
- `application.yml` - 主配置：服务器、日志、Spring、token、MyBatis、Swagger
- `application-{env}.yml` - 环境特定的覆盖配置
- 模块配置：`application-file.properties`、`application-im-broker.properties` 等

**重要配置属性：**
```yaml
server.port: 8080
ruoyi.profile: 文件上传基础路径
spring.redis: Redis 连接设置
spring.rabbitmq: 消息队列配置
token.secret: JWT 密钥
token.expireTime: Token 过期时间（分钟）
file.storage.type: 存储后端（minio/local/aliyun/tencent）
```

### 前端架构

**框架：** Vue 2.6.14 单页应用，配合 Vue Router 3.4.9 和 Vuex 3.6.0

**目录结构：**
```
src/
├── api/              # 后端 API 集成（按业务域组织）
├── assets/           # 静态资源（样式、图标、图片）
├── components/       # 可复用组件（编辑器、流程、表单、上传）
├── layout/           # 布局包装器（侧边栏、导航栏、标签视图、主内容区）
├── router/           # 路由定义，带基于角色的访问控制
├── store/            # Vuex 模块（app、user、permission、tagsView、dict、settings）
├── utils/            # 工具函数（request、auth、ruoyi、socket）
├── views/            # 页面组件（system、workflow、monitor、kbs 等）
├── main.js           # 入口文件
└── permission.js     # 路由守卫用于认证
```

**关键前端组件：**
- **流程设计器（BPMN）：** bpmn-js 11.1.0 用于可视化工作流建模
- **富文本编辑器：** wangEditor v5.1.23、TinyMCE
- **表单设计器：** VForm 用于动态表单创建/渲染
- **文件上传：** vue-simple-uploader 支持分块上传
- **WebSocket：** 通过 `wssocket.js` 集成实时消息

**API 集成：**
- 基于 Axios，带集中式拦截器
- Bearer token 认证：`Authorization: Bearer {token}`
- 基础 API：`/dev-api`（开发）、`/prod-api`（生产）
- 响应格式：`{code: 200, msg: "success", data: {}}`
- 错误码：200=成功、401=未授权、500=错误、601=警告

**认证流程：**
1. 通过 POST `/system/login` 登录 → 获取 JWT token
2. Token 存储在 localStorage
3. 路由守卫检查 token，加载用户信息 + 动态路由
4. 所有 API 请求在 Authorization 头中包含 token

**状态管理（Vuex）：**
- `app` - 设备、侧边栏、主题状态
- `user` - Token、个人资料、角色、权限
- `permission` - 基于角色从后端获取的动态路由
- `tagsView` - 多标签导航
- `dict` - 缓存的字典数据
- `settings` - UI 主题和布局

**构建配置：**
- 开发代理：`/dev-api` → `http://localhost:8080`
- 代码分割：chunk-libs、chunk-elementUI、chunk-commons
- 生产环境启用 Gzip 压缩
- 输出：`dist/` 目录

### 跨模块通信

**后端模块依赖关系：**
```
ruoyi-admin（入口）
  ├─ ruoyi-framework（安全、配置、AOP）
  ├─ ruoyi-system（系统域）
  ├─ ruoyi-workflow（编排）
  │   ├─ ruoyi-flowable（引擎）
  │   ├─ ruoyi-seal（数字印章）
  │   ├─ ruoyi-biz-sdk（业务操作）
  │   └─ ruoyi-message（通知）
  ├─ ruoyi-template（表单）
  ├─ ruoyi-file（存储）
  └─ ruoyi-todo（任务）
```

**前后端通信：**
- 前端通过 `/dev-api` 代理调用 REST API
- 后端返回标准格式的 JSON
- 每个请求中都包含 JWT token 用于认证
- WebSocket 连接用于实时更新（待办、消息）

**服务间通信：**
- `ruoyi-im-broker` 作为独立的 Netty 服务运行
- RabbitMQ 用于模块间的异步消息传递
- Redis 用于分布式缓存和锁

## 重要代码模式

### 后端模式

**REST 控制器：**
- 位于各模块的 `controller` 包中
- 使用 `@RestController` 和 `@RequestMapping` 注解
- 使用 `AjaxResult` 返回响应：`AjaxResult.success()`、`AjaxResult.error()`
- 安全注解：`@PreAuthorize("@ss.hasPermi('system:user:list')")`

**服务层：**
- 接口 + 实现类模式
- 使用 `@Transactional` 进行事务管理
- 业务逻辑在 service 中，而非 controller

**数据访问：**
- MyBatis mapper 接口配合 XML mapper
- Mapper XML 文件在 `src/main/resources/mapper/` 目录
- 使用 `PageHelper.startPage()` 进行分页

**对象映射：**
- MapStruct 用于 DTO ↔ Entity 转换
- 定义 mapper 接口，使用 `@Mapper(componentModel = "spring")` 注解

**安全：**
- `SecurityUtils` 获取当前用户信息：`SecurityUtils.getUsername()`、`SecurityUtils.getUserId()`
- 权限检查：`@PreAuthorize("@ss.hasPermi('permission:code')")`
- 通过 `DataScopeAspect` 进行数据范围过滤

### 前端模式

**API 调用：**
```javascript
import request from '@/utils/request'

export function listUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}
```

**Vuex Store 使用：**
```javascript
// 在组件中
this.$store.dispatch('user/login', loginForm)
this.$store.getters.roles
this.$store.commit('SET_TOKEN', token)
```

**权限指令：**
```vue
<el-button v-hasPermi="['system:user:add']">添加</el-button>
<el-button v-hasRole="['admin']">仅管理员</el-button>
```

**动态路由：**
- 基于用户权限从后端获取路由
- 在 `store/modules/permission.js` 中生成
- 在应用初始化前加载

## 数据库架构

数据库脚本位于 `ruoyi-vue-oa/sql/`：
- `table.sql` - 表结构
- `data.sql` - 初始数据

**关键表：**
- `sys_user`、`sys_role`、`sys_dept`、`sys_menu` - 系统表
- `act_*` - Flowable 工作流表
- `flow_*` - 自定义工作流表（模板、实例、待办）
- `sys_file` - 文件元数据
- `sys_dict_*` - 字典表

## 外部服务依赖

**必需服务：**
- MySQL 数据库（默认：localhost:3306）
- Redis（默认：localhost:6379）
- RabbitMQ（默认：localhost:5672）
- Minio（默认：http://localhost:9000，凭据：minioadmin/minioadmin）
- LibreOffice（用于文档转换，路径在 `file.office.home` 中配置）

**可选服务：**
- 阿里云 OSS / 腾讯云 COS（如果不使用 Minio）
- 短信服务商（腾讯云、阿里云）

**Netty IM Broker：**
- 作为独立服务在 TCP 端口上运行（在 `application-im-broker.properties` 中配置）
- WebSocket 端点：ws://localhost:8544/im

## 开发工作流

1. **启动外部服务：** MySQL、Redis、RabbitMQ、Minio
2. **导入数据库：** 运行 `sql/table.sql` 和 `sql/data.sql`
3. **配置后端：** 更新 `ruoyi-admin/src/main/resources/env/dev/application-dev.yml`，配置数据库和 Redis 设置
4. **启动后端：**
   - `cd ruoyi-vue-oa && mvn spring-boot:run`
   - 或 `./ry.sh start`
   - 验证地址：http://localhost:8080/swagger-ui/
5. **启动前端：**
   - `cd ruoyi-vue-oa-ui && npm install && npm run dev`
   - 访问地址：http://localhost:80
   - 默认登录：test/test123

## 代码生成

**后端生成器：**
- 访问路径：系统工具 → 代码生成
- 选择表 → 生成 → 下载代码
- 生成内容：Entity、Mapper、Service、Controller、XML、Vue 组件

**前端表单设计器：**
- 访问路径：工作流管理 → 表单设计
- 拖放表单组件
- 生成 JSON 表单架构用于动态渲染

## 测试和调试

**后端：**
- Swagger UI：http://localhost:8080/swagger-ui/
- Druid 监控：http://localhost:8080/druid/login.html
- 日志文件：`logs/` 目录（在 `application.yml` 中配置）
- 默认日志级别：INFO（开发时可改为 DEBUG）

**前端：**
- Vue DevTools 浏览器扩展
- 通过 `console.log()` 输出控制台日志（生产前移除）
- 网络选项卡用于 API 调试
- 未配置自动化测试套件

## 部署说明

**后端：**
- 构建：`mvn clean package -P prod`
- 输出：`ruoyi-admin/target/ruoyi-admin.jar`
- 部署：`java -jar ruoyi-admin.jar` 或使用 `ry.sh start`
- 在 `env/prod/application-prod.yml` 中配置生产环境的数据库、Redis、RabbitMQ

**前端：**
- 构建：`npm run build:prod`
- 输出：`dist/` 目录
- 部署：将静态文件部署到 Nginx 或 CDN
- 在 `.env.production` 中配置生产环境 API 端点

**IM Broker：**
- 将 `ruoyi-im-broker` 作为独立服务部署
- 确保 WebSocket 连接的网络配置正确

## 常见陷阱

1. **多模块依赖：** 添加新依赖时，检查应该放在父 `pom.xml` 的 `<dependencyManagement>` 中还是模块特定的 `pom.xml` 中
2. **MapStruct：** 修改 mapper 接口后，需要重新构建项目以重新生成实现类
3. **Flowable 数据库：** Flowable 表在首次运行时自动创建；除非重置工作流数据，否则不要删除它们
4. **前端代理：** 在开发时，确保在启动前端之前后端已运行（代理需要后端）
5. **WebSocket 连接：** IM 功能需要 `ruoyi-im-broker` 服务运行
6. **文件上传路径：** 确保 `ruoyi.profile` 目录存在且具有写入权限
7. **LibreOffice：** 文档转换需要安装 LibreOffice 并正确配置 `file.office.home`
8. **Node 版本：** 前端需要 Node 14+；更新的版本可能存在依赖兼容性问题
