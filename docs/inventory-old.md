# 原前端页面清单 (ruoyi-vue-oa-ui)

> 生成时间: 2026-04-03
> 扫描路径: `ruoyi-vue-oa-ui/src/views/`
> 文件总数: **149 个 .vue 文件**，分布在 **16 个模块**

---

## 模块概览

| 模块 | 文件数 | 说明 |
|------|--------|------|
| workflow | 45 | 工作流管理（流程定义/审批/表单/模板/待办/已办） |
| system | 17 | 系统管理（用户/角色/部门/菜单/字典/岗位/配置） |
| tool | 17 | 系统工具（表单构建器/代码生成/Swagger） |
| kbs | 16 | 知识库管理（主题/文档/评论/收藏） |
| schedule | 10 | 日程管理（日历/日程详情/类型） |
| monitor | 9 | 系统监控（缓存/Druid/定时任务/日志/在线用户/服务器） |
| home | 9 | 首页门户（快捷入口/新闻/公告/日程/待办/统计） |
| dashboard | 5 | 仪表盘（图表组件） |
| information | 3 | 资讯管理（列表/详情/发布） |
| notice | 3 | 通知公告（列表/详情/发布） |
| holiday | 3 | 假期/工作日设置 |
| error | 2 | 错误页面（401/404） |
| serial | 2 | 编号管理（配置/日志） |
| setting | 2 | 个人设置（委托/秘书） |
| mq | 1 | 消息队列管理 |
| 根级别 | 5 | 登录/注册/重定向/首页 |
| **合计** | **149** | |

---

## 详细清单

### 根级别页面 (4)

| 文件 | 说明 |
|------|------|
| `index.vue` | 默认首页 |
| `index_v1.vue` | 首页 V1 版本 |
| `login.vue` | 登录页 |
| `register.vue` | 注册页 |
| `redirect.vue` | 路由重定向 |

### dashboard — 仪表盘 (5)

| 文件 | 说明 |
|------|------|
| `dashboard/BarChart.vue` | 柱状图组件 |
| `dashboard/LineChart.vue` | 折线图组件 |
| `dashboard/PanelGroup.vue` | 面板组组件 |
| `dashboard/PieChart.vue` | 饼图组件 |
| `dashboard/RaddarChart.vue` | 雷达图组件 |

### error — 错误页面 (2)

| 文件 | 说明 |
|------|------|
| `error/401.vue` | 401 未授权页面 |
| `error/404.vue` | 404 未找到页面 |

### holiday — 假期设置 (3)

| 文件 | 说明 |
|------|------|
| `holiday/index.vue` | 假期管理主页 |
| `holiday/holiday-setting/index.vue` | 假期设置 |
| `holiday/work-setting/index.vue` | 工作日设置 |

### home — 首页门户 (9)

| 文件 | 说明 |
|------|------|
| `home/index.vue` | 首页主入口 |
| `home/components/FastEntrance/index.vue` | 快捷入口组件 |
| `home/components/News/index.vue` | 新闻组件 |
| `home/components/Notice/index.vue` | 公告组件 |
| `home/components/Schedule/index.vue` | 日程组件 |
| `home/components/Static/index.vue` | 统计组件 |
| `home/components/Todo/Collapse.vue` | 待办折叠组件 |
| `home/components/Todo/Table.vue` | 待办表格组件 |
| `home/components/Welcome/index.vue` | 欢迎组件 |

### information — 资讯管理 (3)

| 文件 | 说明 |
|------|------|
| `information/index.vue` | 资讯列表 |
| `information/detail.vue` | 资讯详情 |
| `information/pub-list.vue` | 资讯发布列表 |

### kbs — 知识库 (16)

| 文件 | 说明 |
|------|------|
| `kbs/favorite/index.vue` | 收藏管理 |
| `kbs/recycle/index.vue` | 回收站 |
| `kbs/topic/index.vue` | 主题列表 |
| `kbs/topic/components/add-topic.vue` | 新建主题 |
| `kbs/topic/components/detail.vue` | 主题详情 |
| `kbs/topic/components/auth/topic-auth-user.vue` | 主题权限-用户授权 |
| `kbs/topic/components/category/index.vue` | 分类管理 |
| `kbs/topic/components/info/comment.vue` | 评论列表 |
| `kbs/topic/components/info/comment-input.vue` | 评论输入 |
| `kbs/topic/components/info/doc-editor.vue` | 文档编辑器 |
| `kbs/topic/components/info/doc-info.vue` | 文档信息 |
| `kbs/topic/components/info/favorite.vue` | 收藏操作 |
| `kbs/topic/components/info/favorite-group.vue` | 收藏分组 |
| `kbs/topic/components/info/outline-item.vue` | 大纲项 |
| `kbs/topic/components/info/topic-info.vue` | 主题信息 |
| `kbs/topic/components/info/topic-tree.vue` | 主题树 |

### monitor — 系统监控 (8)

| 文件 | 说明 |
|------|------|
| `monitor/cache/index.vue` | 缓存监控 |
| `monitor/cache/list.vue` | 缓存列表 |
| `monitor/druid/index.vue` | Druid 数据源监控 |
| `monitor/job/index.vue` | 定时任务管理 |
| `monitor/job/log.vue` | 定时任务日志 |
| `monitor/logininfor/index.vue` | 登录日志 |
| `monitor/online/index.vue` | 在线用户 |
| `monitor/operlog/index.vue` | 操作日志 |
| `monitor/server/index.vue` | 服务器监控 |

### mq — 消息队列 (1)

| 文件 | 说明 |
|------|------|
| `mq/async/index.vue` | 异步消息管理 |

### notice — 通知公告 (3)

| 文件 | 说明 |
|------|------|
| `notice/index.vue` | 通知列表 |
| `notice/detail.vue` | 通知详情 |
| `notice/pub-list.vue` | 通知发布列表 |

### schedule — 日程管理 (10)

| 文件 | 说明 |
|------|------|
| `schedule/index.vue` | 日程主页 |
| `schedule/components/calendar/index.vue` | 日历组件 |
| `schedule/components/custom-calendar/index.vue` | 自定义日历入口 |
| `schedule/components/custom-calendar/body.vue` | 自定义日历主体 |
| `schedule/components/custom-calendar/full-calendar.vue` | 全日历视图 |
| `schedule/components/custom-radio.vue` | 自定义单选组件 |
| `schedule/components/header/index.vue` | 日程头部 |
| `schedule/components/schedule/detail.vue` | 日程详情 |
| `schedule/components/schedule/save.vue` | 日程保存 |
| `schedule/components/type/index.vue` | 日程类型管理 |

### serial — 编号管理 (2)

| 文件 | 说明 |
|------|------|
| `serial/config/index.vue` | 编号配置 |
| `serial/log/index.vue` | 编号日志 |

### setting — 个人设置 (2)

| 文件 | 说明 |
|------|------|
| `setting/entrust/index.vue` | 委托设置 |
| `setting/secretary/index.vue` | 秘书设置 |

### system — 系统管理 (15)

| 文件 | 说明 |
|------|------|
| `system/config/index.vue` | 参数配置 |
| `system/dept/index.vue` | 部门管理 |
| `system/dict/index.vue` | 字典管理 |
| `system/dict/data.vue` | 字典数据 |
| `system/menu/index.vue` | 菜单管理 |
| `system/post/index.vue` | 岗位管理 |
| `system/role/index.vue` | 角色管理 |
| `system/role/authUser.vue` | 角色授权用户 |
| `system/role/selectUser.vue` | 选择用户 |
| `system/user/index.vue` | 用户管理 |
| `system/user/authRole.vue` | 用户授权角色 |
| `system/user/contact/index.vue` | 通讯录 |
| `system/user/contact/detail.vue` | 通讯录详情 |
| `system/user/profile/index.vue` | 个人中心 |
| `system/user/profile/resetPwd.vue` | 修改密码 |
| `system/user/profile/userAvatar.vue` | 修改头像 |
| `system/user/profile/userInfo.vue` | 个人信息 |

### tool — 系统工具 (12)

| 文件 | 说明 |
|------|------|
| `tool/build/index.vue` | 表单构建器主页 |
| `tool/build/App.vue` | 表单构建器应用 |
| `tool/build/CodeTypeDialog.vue` | 代码类型对话框 |
| `tool/build/DraggableItem.vue` | 可拖拽项 |
| `tool/build/FormDrawer.vue` | 表单抽屉 |
| `tool/build/IconsDialog.vue` | 图标对话框 |
| `tool/build/JsonDrawer.vue` | JSON 抽屉 |
| `tool/build/ResourceDialog.vue` | 资源对话框 |
| `tool/build/RightPanel.vue` | 右侧面板 |
| `tool/build/TreeNodeDialog.vue` | 树节点对话框 |
| `tool/gen/index.vue` | 代码生成列表 |
| `tool/gen/basicInfoForm.vue` | 基本信息表单 |
| `tool/gen/createTable.vue` | 创建表 |
| `tool/gen/editTable.vue` | 编辑表 |
| `tool/gen/genInfoForm.vue` | 生成信息表单 |
| `tool/gen/importTable.vue` | 导入表 |
| `tool/swagger/index.vue` | Swagger API 文档 |

### workflow — 工作流管理 (40)

| 文件 | 说明 |
|------|------|
| **已办** | |
| `workflow/done/index.vue` | 已办列表 |
| **动态表单** | |
| `workflow/dynamic-form/index.vue` | 动态表单 |
| **Flowable 引擎** | |
| `workflow/flowable/definition/index.vue` | 流程定义列表 |
| `workflow/flowable/definition/flow.vue` | 流程设计器 |
| `workflow/flowable/definition/flow-view.vue` | 流程查看 |
| `workflow/flowable/definition/model.vue` | 流程模型 |
| `workflow/flowable/expression/index.vue` | 表达式管理 |
| `workflow/flowable/history/index.vue` | 流程历史 |
| `workflow/flowable/instance/index.vue` | 流程实例 |
| `workflow/flowable/instance/jump-activity-user.vue` | 跳转节点用户 |
| `workflow/flowable/listener/index.vue` | 监听器管理 |
| **流程表单** | |
| `workflow/flow-form/index.vue` | 流程表单主页 |
| `workflow/flow-form/component/complete.vue` | 办结组件 |
| `workflow/flow-form/component/delete-multi.vue` | 批量删除 |
| `workflow/flow-form/component/flow-approve.vue` | 审批组件 |
| `workflow/flow-form/component/flow-attachment.vue` | 附件组件 |
| `workflow/flow-form/component/flow-comment.vue` | 意见组件 |
| `workflow/flow-form/component/flow-definition.vue` | 定义组件 |
| `workflow/flow-form/component/flow-mainText.vue` | 正文组件 |
| `workflow/flow-form/component/flow-process.vue` | 流程组件 |
| `workflow/flow-form/component/main-preview.vue` | 正文预览 |
| `workflow/flow-form/component/main-stamp.vue` | 盖章组件 |
| `workflow/flow-form/component/main-upload.vue` | 正文上传 |
| `workflow/flow-form/component/reject.vue` | 驳回组件 |
| `workflow/flow-form/component/return.vue` | 退回组件 |
| `workflow/flow-form/component/select-user.vue` | 选择用户 |
| `workflow/flow-form/component/view-form.vue` | 查看表单 |
| **印章管理** | |
| `workflow/main-seal/index.vue` | 印章管理 |
| **我的草稿** | |
| `workflow/my-draft/index.vue` | 草稿箱 |
| **发起流程** | |
| `workflow/newstart/index.vue` | 发起流程 |
| `workflow/newstart/flow-template.vue` | 流程模板选择 |
| **回收站** | |
| `workflow/recycle/index.vue` | 回收站 |
| **流程模板** | |
| `workflow/template/index.vue` | 模板列表 |
| `workflow/template/add.vue` | 新建模板 |
| `workflow/template/attachment-info.vue` | 附件信息 |
| `workflow/template/basic-info.vue` | 基本信息 |
| `workflow/template/business-form-info.vue` | 业务表单信息 |
| `workflow/template/main-text.vue` | 正文模板 |
| `workflow/template/message-notice.vue` | 消息通知 |
| `workflow/template/type/index.vue` | 模板类型 |
| **待办** | |
| `workflow/todo/index.vue` | 待办主页 |
| `workflow/todo/group-collapse.vue` | 分组折叠 |
| `workflow/todo/group-table.vue` | 分组表格 |
| `workflow/todo/read-list.vue` | 已读列表 |
| `workflow/todo/todo-list.vue` | 待办列表 |
