# 原前端功能详细清单（RuoYi-Vue-OA）

**生成时间**: 2026-04-13
**源目录**: ruoyi-vue-oa-ui/src/views
**Vue 文件总数**: 149
**技术栈**: Vue 2.6.14 + Element UI 2.15.14 + Vue Router 3.4.9 + Vuex 3.6.0

---

## 目录

1. [登录认证](#1-登录认证)
2. [首页 (index/home/dashboard)](#2-首页-indexhomedashboard)
3. [系统管理 (system)](#3-系统管理-system)
4. [系统监控 (monitor)](#4-系统监控-monitor)
5. [工作流管理 (workflow)](#5-工作流管理-workflow)
6. [知识库 (kbs)](#6-知识库-kbs)
7. [新闻公告 (information)](#7-新闻公告-information)
8. [日程管理 (schedule)](#8-日程管理-schedule)
9. [编号管理 (serial)](#9-编号管理-serial)
10. [工作设置 (setting)](#10-工作设置-setting)
11. [节假日设置 (holiday)](#11-节假日设置-holiday)
12. [消息队列 (mq)](#12-消息队列-mq)
13. [通知公告 (notice)](#13-通知公告-notice)
14. [开发工具 (tool)](#14-开发工具-tool)
15. [错误页面 (error)](#15-错误页面-error)

---

## 1. 登录认证

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/login` | `views/login.vue` | 用户登录页面，支持验证码 |
| `/register` | `views/register.vue` | 用户注册页面 |
| `/redirect/:path` | `views/redirect.vue` | 路由重定向中转页 |

### API 依赖

```javascript
// src/api/login.js
- POST /login              // 用户登录
- GET /getInfo             // 获取用户信息
- POST /logout             // 用户登出
- GET /captchaImage        // 获取验证码
```

### 技术栈

- **认证方式**: JWT Token（Bearer Token）
- **状态管理**: Vuex (store/modules/user.js)
- **表单验证**: Element UI Form Validation
- **验证码**: Base64 图片验证码

---

## 2. 首页 (index/home/dashboard)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/index` | `views/index.vue` | 工作台首页（旧版） |
| `/` | `views/home/index.vue` | 新版首页（工作台） |
| N/A | `views/dashboard/*` | Dashboard 组件（可能未使用） |

### 首页组件

**views/home/components/**

1. **FastEntrance** - 快捷入口
   - `FastEntrance.vue` - 快捷功能入口

2. **News** - 新闻资讯
   - `News.vue` - 新闻列表组件

3. **Notice** - 通知公告
   - `Notice.vue` - 公告列表组件

4. **Schedule** - 日程安排
   - `Schedule.vue` - 日程卡片

5. **Static** - 统计信息
   - `Static.vue` - 数据统计卡片

6. **Todo** - 待办任务
   - `Todo.vue` - 待办事项列表

7. **Welcome** - 欢迎信息
   - `Welcome.vue` - 欢迎页头

### API 依赖

```javascript
// 依赖多个模块的 API
- workflow/todo.js    // 待办任务
- schedule/schedule.js // 日程
- information/information.js // 新闻
- system/notice.js    // 公告
```

### 技术特点

- **组件化设计**: 首页采用组件化拼装
- **实时更新**: WebSocket 推送待办更新
- **数据聚合**: 整合多个业务模块数据

---

## 3. 系统管理 (system)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/system/user` | `views/system/user/index.vue` | 用户管理 |
| `/system/user/profile` | `views/system/user/profile/index.vue` | 个人中心 |
| `/system/user-auth/role/:userId` | `views/system/user/authRole.vue` | 分配角色 |
| `/system/user/contact` | `views/system/user/contact/index.vue` | 通讯录 |
| `/system/role` | `views/system/role/index.vue` | 角色管理 |
| `/system/role-auth/user/:roleId` | `views/system/role/authUser.vue` | 角色分配用户 |
| `/system/menu` | `views/system/menu/index.vue` | 菜单管理 |
| `/system/dept` | `views/system/dept/index.vue` | 部门管理 |
| `/system/post` | `views/system/post/index.vue` | 岗位管理 |
| `/system/dict` | `views/system/dict/index.vue` | 字典管理 |
| `/system/dict-data/index/:dictId` | `views/system/dict/data.vue` | 字典数据 |
| `/system/config` | `views/system/config/index.vue` | 参数配置 |

### API 依赖

```javascript
// src/api/system/
- user.js          // 用户 CRUD、密码重置、导出
- role.js          // 角色 CRUD、权限分配
- menu.js          // 菜单 CRUD、菜单树
- dept.js          // 部门 CRUD、部门树
- post.js          // 岗位 CRUD
- dict/type.js     // 字典类型
- dict/data.js     // 字典数据
- config.js        // 系统参数
- notice.js        // 通知公告
- holidaySetting.js // 节假日设置
- holidayWorkSetting.js // 调休设置
```

### 技术栈

- **权限控制**: `@PreAuthorize` 注解 + `v-hasPermi` 指令
- **数据权限**: 数据范围过滤（全部/自定义/部门/本人）
- **树形组件**: Element Tree 组件
- **Excel 导入导出**: JS-XLSX

---

## 4. 系统监控 (monitor)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/monitor/online` | `views/monitor/online/index.vue` | 在线用户 |
| `/monitor/job` | `views/monitor/job/index.vue` | 定时任务 |
| `/monitor/job-log/index/:jobId` | `views/monitor/job/log.vue` | 调度日志 |
| `/monitor/logininfor` | `views/monitor/logininfor/index.vue` | 登录日志 |
| `/monitor/operlog` | `views/monitor/operlog/index.vue` | 操作日志 |
| `/monitor/cache` | `views/monitor/cache/index.vue` | 缓存监控 |
| `/monitor/server` | `views/monitor/server/index.vue` | 服务监控 |
| `/monitor/druid` | `views/monitor/druid/index.vue` | Druid 监控 |

### API 依赖

```javascript
// src/api/monitor/
- online.js        // 在线用户列表、强退
- job.js           // 定时任务 CRUD、执行、状态切换
- jobLog.js        // 调度日志、清空
- logininfor.js    // 登录日志、清空、解锁
- operlog.js       // 操作日志、清空
- cache.js         // 缓存信息、清理
- server.js        // 服务器信息
```

### 技术特点

- **实时监控**: 定时刷新服务器状态
- **Quartz 集成**: 定时任务管理
- **Redis 监控**: 缓存命中率、键值监控
- **日志分析**: 操作日志和登录日志审计

---

## 5. 工作流管理 (workflow)

### 5.1 流程管理 (Flowable)

#### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/flowable/definition` | `views/workflow/flowable/definition/index.vue` | 流程定义列表 |
| `/flowable/definition/model` | `views/workflow/flowable/definition/model.vue` | 流程设计器（BPMN） |
| `/flowable/instance` | `views/workflow/flowable/instance/index.vue` | 流程实例 |
| `/flowable/history` | `views/workflow/flowable/history/index.vue` | 历史流程 |
| `/flowable/listener` | `views/workflow/flowable/listener/index.vue` | 监听器管理 |
| `/flowable/expression` | `views/workflow/flowable/expression/index.vue` | 表达式管理 |

#### 关键组件

**流程设计器**: `views/workflow/flowable/definition/model.vue`
- **依赖**: bpmn-js 11.1.0（BPMN 2.0 可视化建模）
- **功能**: 流程图绘制、节点配置、属性编辑、XML 导入导出

### 5.2 流程模板

#### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/workflow/template` | `views/workflow/template/index.vue` | 流程模板列表 |
| `/workflow/template/add` | `views/workflow/template/add.vue` | 添加/编辑模板 |
| `/workflow/template/type` | `views/workflow/template/type/index.vue` | 模板类型管理 |

### 5.3 流程表单

#### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/workflow/flow-form/:timer` | `views/workflow/flow-form/index.vue` | 动态流程表单 |
| N/A | `views/workflow/flow-form/component/*` | 表单组件库 |
| N/A | `views/workflow/dynamic-form/*` | 动态表单设计器 |

#### 特殊组件

**动态表单设计器**: `views/workflow/dynamic-form/`
- **依赖**: VForm（Vue 表单设计器）
- **功能**: 拖拽式表单设计、字段配置、表单渲染

### 5.4 我的流程

#### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/workflow/newstart` | `views/workflow/newstart/index.vue` | 发起流程 |
| `/workflow/todo` | `views/workflow/todo/index.vue` | 待办任务 |
| `/workflow/done` | `views/workflow/done/index.vue` | 已办任务 |
| `/workflow/my-draft` | `views/workflow/my-draft/index.vue` | 我的草稿 |
| `/workflow/recycle` | `views/workflow/recycle/index.vue` | 回收站 |

### 5.5 电子印章

#### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/workflow/main-seal` | `views/workflow/main-seal/index.vue` | 印章管理（新） |
| `/workflow/mainSeal` | `views/workflow/mainSeal/index.vue` | 印章管理（旧） |

#### 技术栈

- **PDF 操作**: iText（后端）
- **图片资源**: `views/workflow/mainSeal/images/`

### API 依赖

```javascript
// src/api/workflow/
- flowable/definition.js   // 流程定义 CRUD、部署、XML 导入导出
- flowable/instance.js     // 流程实例（通过 monitor.js）
- flowable/monitor.js      // 流程监控
- flowable/listener.js     // 监听器
- flowable/expression.js   // 表达式
- template.js              // 流程模板
- form.js                  // 表单配置
- dynamicForm.js           // 动态表单
- todo.js                  // 待办任务
- done.js                  // 已办任务
- draft.js                 // 草稿
- recycle.js               // 回收站
- process.js               // 流程处理
- task.js                  // 任务操作
- mainSeal.js              // 印章管理
- attachment.js            // 附件
- comment.js               // 评论
```

### 技术特点

- **流程引擎**: Flowable 6.7.2（BPMN 2.0）
- **流程设计**: bpmn-js 可视化建模器
- **动态表单**: VForm 表单设计器 + JSON Schema
- **实时通知**: WebSocket 推送待办任务
- **文件上传**: vue-simple-uploader（分块上传）

---

## 6. 知识库 (kbs)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/kbs/topic` | `views/kbs/topic/index.vue` | 知识主题 |
| N/A | `views/kbs/topic/components/info/*` | 主题信息组件 |
| N/A | `views/kbs/topic/components/category/*` | 分类管理组件 |
| N/A | `views/kbs/topic/components/auth/*` | 权限管理组件 |
| `/kbs/favorite` | `views/kbs/favorite/index.vue` | 我的收藏 |
| `/kbs/recycle` | `views/kbs/recycle/index.vue` | 回收站 |

### 组件结构

**views/kbs/topic/components/**
- **info/** - 主题信息编辑
- **category/** - 分类树管理
- **auth/** - 权限配置

### API 依赖

```javascript
// src/api/kbs/
- topic/info.js        // 主题 CRUD
- topic/category.js    // 分类管理
- topic/user.js        // 用户权限
- document/document.js // 文档 CRUD
- document/comment.js  // 评论
- document/like.js     // 点赞
- favorite/favorite.js // 收藏
- favorite/group.js    // 收藏分组
- recycle/recycle.js   // 回收站
```

### 技术栈

- **富文本编辑器**: wangEditor v5.1.23 / TinyMCE
- **树形结构**: Element Tree（分类树）
- **Markdown 支持**: marked.js（可选）

---

## 7. 新闻公告 (information)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/information` | `views/information/index.vue` | 新闻资讯管理 |

### API 依赖

```javascript
// src/api/information/
- information.js   // 新闻 CRUD、发布、置顶
```

### 技术栈

- **富文本编辑器**: wangEditor / TinyMCE
- **图片上传**: Element Upload

---

## 8. 日程管理 (schedule)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/schedule` | `views/schedule/index.vue` | 日程管理主页 |

### 组件结构

**views/schedule/components/**

1. **calendar/** - 日历组件
   - 日历视图渲染

2. **custom-calendar/** - 自定义日历
   - `custom-calendar/data-map/` - 数据映射

3. **header/** - 日程头部
   - 工具栏、视图切换

4. **schedule/** - 日程详情
   - 日程卡片、编辑表单

5. **type/** - 日程类型
   - 类型管理

### API 依赖

```javascript
// src/api/schedule/
- schedule.js      // 日程 CRUD
- type.js          // 日程类型
- parts.js         // 参与人
```

### 技术栈

- **日历组件**: 自定义日历组件
- **视图切换**: 日/周/月视图
- **拖拽支持**: 日程拖拽排期

---

## 9. 编号管理 (serial)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/serial/config` | `views/serial/config/index.vue` | 编号规则配置 |
| `/serial/log` | `views/serial/log/index.vue` | 编号生成日志 |

### API 依赖

```javascript
// src/api/serial/
- config.js        // 编号规则 CRUD
- log.js           // 编号日志查询
```

### 功能特点

- **自定义规则**: 前缀 + 日期格式 + 流水号
- **规则示例**: `ORDER-20260413-0001`
- **重置策略**: 按天/月/年重置

---

## 10. 工作设置 (setting)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/setting/entrust` | `views/setting/entrust/index.vue` | 工作委托 |
| `/setting/secretary` | `views/setting/secretary/index.vue` | 秘书设置 |

### API 依赖

```javascript
// src/api/setting/
- entrust.js       // 委托设置
- secretary.js     // 秘书配置
```

---

## 11. 节假日设置 (holiday)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/holiday/holiday-setting` | `views/holiday/holiday-setting/index.vue` | 节假日配置 |
| `/holiday/work-setting` | `views/holiday/work-setting/index.vue` | 调休设置 |

### API 依赖

```javascript
// src/api/system/
- holidaySetting.js      // 节假日
- holidayWorkSetting.js  // 调休
```

---

## 12. 消息队列 (mq)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/mq/async` | `views/mq/async/index.vue` | 异步消息监控 |

### API 依赖

```javascript
// src/api/mq/
- async.js         // RabbitMQ 消息队列监控
```

### 技术栈

- **消息队列**: RabbitMQ
- **监控功能**: 队列状态、消息数量、消费情况

---

## 13. 通知公告 (notice)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/notice` | `views/notice/index.vue` | 通知公告列表（可能重复） |

### API 依赖

```javascript
// src/api/system/
- notice.js        // 通知公告
```

---

## 14. 开发工具 (tool)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/tool/gen` | `views/tool/gen/index.vue` | 代码生成列表 |
| `/tool/gen-edit/index/:tableId` | `views/tool/gen/editTable.vue` | 生成配置 |
| `/tool/build` | `views/tool/build/index.vue` | 表单设计器 |
| `/tool/swagger` | `views/tool/swagger/index.vue` | Swagger 文档（iframe） |

### API 依赖

```javascript
// src/api/tool/
- gen.js           // 代码生成 CRUD、预览、下载
```

### 技术特点

- **代码生成**: Velocity 模板引擎
- **表单设计**: VForm 表单设计器
- **Swagger UI**: 内嵌 Swagger 文档

---

## 15. 错误页面 (error)

### 页面列表

| 路由 | 组件路径 | 功能描述 |
|------|----------|----------|
| `/404` | `views/error/404.vue` | 404 页面 |
| `/401` | `views/error/401.vue` | 401 无权限页面 |

---

## 特殊依赖组件汇总

### 1. BPMN 流程设计器

**依赖库**: bpmn-js 11.1.0

**使用位置**:
- `views/workflow/flowable/definition/model.vue`

**功能**:
- BPMN 2.0 流程图绘制
- 节点属性配置
- XML 导入导出
- 流程验证

**相关文件**:
```
node_modules/bpmn-js/
node_modules/diagram-js/
```

### 2. 富文本编辑器

#### wangEditor v5.1.23

**使用位置**:
- 新闻公告编辑
- 知识库文档编辑
- 流程表单富文本字段

**功能**:
- 富文本编辑
- 图片上传
- 表格插入
- 代码高亮

#### TinyMCE

**使用位置**:
- 可选的富文本编辑器

### 3. 表单设计器

**依赖库**: VForm

**使用位置**:
- `views/workflow/dynamic-form/`
- `views/tool/build/index.vue`

**功能**:
- 拖拽式表单设计
- 字段配置
- 表单验证规则
- JSON Schema 生成

### 4. 文件上传

**依赖库**: vue-simple-uploader

**使用位置**:
- 工作流附件上传
- 知识库文件上传

**功能**:
- 分块上传
- 断点续传
- 大文件支持
- 上传进度显示

### 5. WebSocket 通信

**使用位置**:
- 待办任务实时推送
- 在线用户状态
- 消息通知

**实现**:
```javascript
// src/utils/wssocket.js
- WebSocket 连接管理
- 消息订阅/发布
- 心跳保活
```

**IM Broker**:
- 独立 Netty 服务
- 端口: 8544
- 路径: ws://localhost:8544/im

---

## API 接口路径汇总

### 认证授权

```
POST /login                    # 用户登录
GET /getInfo                   # 获取用户信息
POST /logout                   # 用户登出
GET /captchaImage              # 获取验证码
```

### 系统管理

```
# 用户管理
GET /system/user/list          # 用户列表
POST /system/user              # 添加用户
PUT /system/user               # 修改用户
DELETE /system/user/{userId}   # 删除用户
GET /system/user/{userId}      # 用户详情
POST /system/user/resetPwd     # 重置密码
PUT /system/user/changeStatus  # 修改状态

# 角色管理
GET /system/role/list          # 角色列表
POST /system/role              # 添加角色
PUT /system/role               # 修改角色
DELETE /system/role/{roleId}   # 删除角色

# 菜单管理
GET /system/menu/list          # 菜单列表
GET /system/menu/treeselect    # 菜单树
POST /system/menu              # 添加菜单
PUT /system/menu               # 修改菜单
DELETE /system/menu/{menuId}   # 删除菜单

# 部门管理
GET /system/dept/list          # 部门列表
GET /system/dept/treeselect    # 部门树
POST /system/dept              # 添加部门
PUT /system/dept               # 修改部门
DELETE /system/dept/{deptId}   # 删除部门

# 岗位管理
GET /system/post/list          # 岗位列表
POST /system/post              # 添加岗位
PUT /system/post               # 修改岗位
DELETE /system/post/{postId}   # 删除岗位

# 字典管理
GET /system/dict/type/list     # 字典类型列表
GET /system/dict/data/list     # 字典数据列表
POST /system/dict/type         # 添加字典类型
POST /system/dict/data         # 添加字典数据

# 参数配置
GET /system/config/list        # 参数列表
POST /system/config            # 添加参数
PUT /system/config             # 修改参数
DELETE /system/config/{configId} # 删除参数
```

### 系统监控

```
# 在线用户
GET /monitor/online/list       # 在线用户列表
DELETE /monitor/online/{tokenId} # 强退用户

# 定时任务
GET /monitor/job/list          # 任务列表
POST /monitor/job              # 添加任务
PUT /monitor/job               # 修改任务
PUT /monitor/job/changeStatus  # 修改状态
POST /monitor/job/run          # 执行一次
DELETE /monitor/job/{jobId}    # 删除任务
GET /monitor/job/log/list      # 调度日志

# 日志管理
GET /monitor/logininfor/list   # 登录日志
DELETE /monitor/logininfor/clean # 清空登录日志
GET /monitor/operlog/list      # 操作日志
DELETE /monitor/operlog/clean  # 清空操作日志

# 缓存监控
GET /monitor/cache             # 缓存信息
DELETE /monitor/cache/clearCache # 清理缓存

# 服务监控
GET /monitor/server            # 服务器信息
```

### 工作流管理

```
# 流程定义
GET /flowable/definition/list  # 流程定义列表
POST /flowable/definition/deploy # 部署流程
DELETE /flowable/definition/{id} # 删除定义
GET /flowable/definition/xml/{id} # 获取 XML
POST /flowable/definition/import # 导入流程

# 流程实例
GET /flowable/instance/list    # 实例列表
DELETE /flowable/instance/{id} # 删除实例
GET /flowable/instance/diagram/{id} # 流程图

# 监听器
GET /flowable/listener/list    # 监听器列表
POST /flowable/listener        # 添加监听器
PUT /flowable/listener         # 修改监听器
DELETE /flowable/listener/{id} # 删除监听器

# 表达式
GET /flowable/expression/list  # 表达式列表
POST /flowable/expression      # 添加表达式

# 流程模板
GET /workflow/template/list    # 模板列表
POST /workflow/template        # 添加模板
PUT /workflow/template         # 修改模板
DELETE /workflow/template/{id} # 删除模板
PUT /workflow/template/publish # 发布模板

# 待办任务
GET /workflow/todo/list        # 待办列表
POST /workflow/todo/complete   # 完成任务
POST /workflow/todo/reject     # 驳回任务
POST /workflow/todo/transfer   # 转办任务
POST /workflow/todo/entrust    # 委托任务

# 已办任务
GET /workflow/done/list        # 已办列表

# 草稿
GET /workflow/draft/list       # 草稿列表
POST /workflow/draft           # 保存草稿
PUT /workflow/draft            # 修改草稿
DELETE /workflow/draft/{id}    # 删除草稿

# 回收站
GET /workflow/recycle/list     # 回收站列表
POST /workflow/recycle/restore # 恢复
DELETE /workflow/recycle/{id}  # 彻底删除

# 印章管理
GET /workflow/mainSeal/list    # 印章列表
POST /workflow/mainSeal        # 添加印章
PUT /workflow/mainSeal         # 修改印章
DELETE /workflow/mainSeal/{id} # 删除印章
```

### 知识库

```
# 主题管理
GET /kbs/topic/list            # 主题列表
POST /kbs/topic                # 添加主题
PUT /kbs/topic                 # 修改主题
DELETE /kbs/topic/{id}         # 删除主题
GET /kbs/topic/tree            # 主题树

# 分类管理
GET /kbs/category/list         # 分类列表
POST /kbs/category             # 添加分类
PUT /kbs/category              # 修改分类
DELETE /kbs/category/{id}      # 删除分类

# 文档管理
GET /kbs/document/list         # 文档列表
POST /kbs/document             # 添加文档
PUT /kbs/document              # 修改文档
DELETE /kbs/document/{id}      # 删除文档
POST /kbs/document/like        # 点赞
POST /kbs/document/comment     # 评论

# 收藏
GET /kbs/favorite/list         # 收藏列表
POST /kbs/favorite             # 添加收藏
DELETE /kbs/favorite/{id}      # 取消收藏

# 回收站
GET /kbs/recycle/list          # 回收站列表
POST /kbs/recycle/restore      # 恢复
DELETE /kbs/recycle/{id}       # 彻底删除
```

### 新闻公告

```
GET /information/list          # 新闻列表
POST /information              # 添加新闻
PUT /information               # 修改新闻
DELETE /information/{id}       # 删除新闻
PUT /information/publish       # 发布新闻
PUT /information/top           # 置顶新闻
```

### 日程管理

```
GET /schedule/list             # 日程列表
POST /schedule                 # 添加日程
PUT /schedule                  # 修改日程
DELETE /schedule/{id}          # 删除日程
GET /schedule/type/list        # 日程类型
POST /schedule/parts           # 添加参与人
```

### 编号管理

```
GET /serial/config/list        # 编号规则列表
POST /serial/config            # 添加规则
PUT /serial/config             # 修改规则
DELETE /serial/config/{id}     # 删除规则
GET /serial/log/list           # 生成日志
POST /serial/generate          # 生成编号
```

### 工作设置

```
GET /setting/entrust/list      # 委托列表
POST /setting/entrust          # 添加委托
PUT /setting/entrust           # 修改委托
DELETE /setting/entrust/{id}   # 删除委托

GET /setting/secretary/list    # 秘书列表
POST /setting/secretary        # 添加秘书
DELETE /setting/secretary/{id} # 删除秘书
```

### 节假日设置

```
GET /holiday/setting/list      # 节假日列表
POST /holiday/setting          # 添加节假日
PUT /holiday/setting           # 修改节假日
DELETE /holiday/setting/{id}   # 删除节假日

GET /holiday/work/list         # 调休列表
POST /holiday/work             # 添加调休
DELETE /holiday/work/{id}      # 删除调休
```

### 文件管理

```
POST /file/upload              # 文件上传（单文件）
POST /file/uploads             # 文件上传（多文件）
POST /file/chunk/upload        # 分块上传
POST /file/chunk/merge         # 合并分块
GET /file/download/{id}        # 文件下载
DELETE /file/{id}              # 删除文件
GET /file/info/{id}            # 文件信息
```

### 开发工具

```
# 代码生成
GET /tool/gen/list             # 数据表列表
GET /tool/gen/{tableId}        # 表详情
PUT /tool/gen                  # 修改配置
POST /tool/gen/preview/{tableId} # 预览代码
POST /tool/gen/download/{tables} # 下载代码
POST /tool/gen/genCode/{tableName} # 生成代码
POST /tool/gen/importTable     # 导入表
```

---

## 路由配置说明

### 静态路由（constantRoutes）

定义在 `src/router/index.js`，包含：
- 登录、注册页面
- 错误页面（401、404）
- 首页
- 个人中心
- 流程设计器
- 表单设计器
- 流程表单

### 动态路由（dynamicRoutes）

基于用户权限从后端获取，包含：
- 系统管理模块
- 系统监控模块
- 工作流模块
- 知识库模块
- 其他业务模块

**权限控制**:
- `permissions: ['system:user:list']` - 菜单权限
- `roles: ['admin', 'common']` - 角色权限

---

## 状态管理（Vuex）

### Store 模块

定义在 `src/store/modules/`：

1. **app.js** - 应用状态
   - 侧边栏折叠状态
   - 设备类型（移动端/桌面端）
   - 主题颜色

2. **user.js** - 用户状态
   - Token
   - 用户信息
   - 角色
   - 权限

3. **permission.js** - 权限路由
   - 动态路由生成
   - 菜单树构建

4. **tagsView.js** - 标签页
   - 打开的标签列表
   - 固定标签

5. **dict.js** - 字典数据
   - 字典缓存

6. **settings.js** - 系统设置
   - 布局配置
   - 主题配置

---

## 组件复用说明

### 全局组件

定义在 `src/components/`：

1. **Editor** - 富文本编辑器封装
2. **FileUpload** - 文件上传组件
3. **ImageUpload** - 图片上传组件
4. **Pagination** - 分页组件
5. **RightToolbar** - 表格右侧工具栏
6. **DictTag** - 字典标签
7. **ProcessDesigner** - 流程设计器
8. **FormDesigner** - 表单设计器

### 页面组件

每个业务模块内部的组件，位于各自的 `components/` 子目录。

---

## 构建配置

### 环境变量

**开发环境** (.env.development):
```
VUE_APP_BASE_API = '/dev-api'
```

**生产环境** (.env.production):
```
VUE_APP_BASE_API = '/prod-api'
```

### 代理配置

`vue.config.js` 中配置开发代理：
```javascript
proxy: {
  '/dev-api': {
    target: 'http://localhost:8080',
    changeOrigin: true,
    pathRewrite: {
      '^/dev-api': ''
    }
  }
}
```

---

## 待升级事项

### 技术债务

1. **Vue 2 升级到 Vue 3**
   - 组件 API 重写
   - 依赖库升级

2. **Element UI 升级到 Element Plus**
   - 组件 API 变更
   - 样式调整

3. **依赖库版本**
   - bpmn-js 11.1.0 → 最新版
   - wangEditor v5 → v6
   - VForm → 最新版

4. **代码优化**
   - 移除重复组件（dashboard 未使用）
   - 统一印章管理页面（main-seal 和 mainSeal 重复）
   - 优化路由配置

### 新技术栈建议

- **UI 框架**: Element Plus / Ant Design Vue
- **状态管理**: Pinia（替代 Vuex）
- **构建工具**: Vite（替代 Webpack）
- **类型检查**: TypeScript
- **代码规范**: ESLint + Prettier

---

## 总结

### 模块统计

| 模块 | Vue 文件数 | 主要功能 |
|------|-----------|----------|
| workflow | 35+ | 工作流引擎、流程管理、待办任务 |
| system | 20+ | 用户、角色、权限、菜单、部门 |
| monitor | 8 | 在线用户、定时任务、日志、缓存 |
| kbs | 15+ | 知识库、文档、收藏、分类 |
| schedule | 10+ | 日程、日历、类型管理 |
| home/dashboard | 10+ | 工作台首页、统计卡片 |
| tool | 5 | 代码生成、表单设计、Swagger |
| information | 2 | 新闻公告 |
| serial | 2 | 编号规则、生成日志 |
| setting | 2 | 委托、秘书设置 |
| holiday | 2 | 节假日、调休 |
| mq | 1 | 消息队列监控 |
| notice | 1 | 通知公告 |
| error | 2 | 401、404 错误页 |
| **总计** | **149** | **完整的 OA 办公系统** |

### 核心特性

1. **RBAC 权限模型** - 用户、角色、菜单、部门四层权限控制
2. **工作流引擎** - 基于 Flowable 的 BPMN 2.0 流程管理
3. **动态表单** - VForm 拖拽式表单设计器
4. **实时通信** - WebSocket + Netty IM
5. **文件服务** - Minio/阿里云 OSS/本地存储
6. **知识管理** - 文档、分类、收藏、评论
7. **日程管理** - 日历视图、日程提醒
8. **系统监控** - 在线用户、定时任务、日志审计

---

**文档版本**: v1.0
**最后更新**: 2026-04-13
**维护者**: Claude Code Agent
