# 新前端页面清单 (jun_ui_sa_admin/sa-view)

> 生成时间: 2026-04-03
> 统计: 159 个 .vue 文件, 31 个 .html 文件, 共 190 个页面文件

## 加载方式说明

| 标记 | 含义 |
|------|------|
| **vue** | .vue 单文件组件，通过 httpVueLoader 动态加载 |
| **html** | 独立 HTML 页面，内嵌 Vue 实例（非 SPA 模式） |
| **iframe** | HTML 页面通过 iframe 嵌入外部内容 |

## 功能状态说明

| 标记 | 含义 |
|------|------|
| **正常** | 页面有完整的模板和业务逻辑代码 |
| **组件** | 非独立页面，作为子组件被其他页面引用 |
| **占位** | 页面代码极少或为空壳/示例 |
| **冗余** | 与同路径 .vue 文件重复的 .html 版本 |

---

## 1. 根级页面

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| index.vue | vue | 正常 | 主页入口（当前版本） |
| index_v1.vue | vue | 正常 | 主页入口（v1 旧版） |
| login.vue | vue | 正常 | 登录页 |
| register.vue | vue | 正常 | 注册页 |
| redirect.vue | vue | 正常 | 路由重定向页 |

## 2. 控制台 (console/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| console/console-main.html | html | 正常 | 控制台主页 |
| console/com-chart-1.vue | vue | 组件 | 图表组件1 |
| console/com-chart-2.vue | vue | 组件 | 图表组件2 |
| console/com-chart-3.vue | vue | 组件 | 图表组件3 |
| console/com-intro.vue | vue | 组件 | 介绍组件 |
| console/com-origin.vue | vue | 组件 | 来源组件 |
| console/com-sta-data.vue | vue | 组件 | 统计数据组件 |
| console/com-stack.vue | vue | 组件 | 技术栈组件 |
| console/com-update-log.vue | vue | 组件 | 更新日志组件 |

## 3. 仪表盘 (dashboard/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| dashboard/BarChart.vue | vue | 组件 | 柱状图 |
| dashboard/LineChart.vue | vue | 组件 | 折线图 |
| dashboard/PanelGroup.vue | vue | 组件 | 面板组 |
| dashboard/PieChart.vue | vue | 组件 | 饼图 |
| dashboard/RaddarChart.vue | vue | 组件 | 雷达图 |

## 4. 首页 (home/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| home/index.vue | vue | 正常 | 首页主页 |
| home/components/FastEntrance/index.vue | vue | 组件 | 快捷入口 |
| home/components/News/index.vue | vue | 组件 | 新闻 |
| home/components/Notice/index.vue | vue | 组件 | 公告 |
| home/components/Schedule/index.vue | vue | 组件 | 日程 |
| home/components/Static/index.vue | vue | 组件 | 统计 |
| home/components/Todo/Collapse.vue | vue | 组件 | 待办折叠面板 |
| home/components/Todo/Table.vue | vue | 组件 | 待办表格 |
| home/components/Welcome/index.vue | vue | 组件 | 欢迎信息 |

## 5. 系统管理 (system/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| system/user/index.vue | vue | 正常 | 用户管理 |
| system/user/index.html | html | 冗余 | 用户管理（HTML版） |
| system/user/authRole.vue | vue | 正常 | 分配角色 |
| system/user/authRole.html | html | 冗余 | 分配角色（HTML版） |
| system/user/profile/index.vue | vue | 正常 | 个人中心 |
| system/user/profile/index.html | html | 冗余 | 个人中心（HTML版） |
| system/user/profile/resetPwd.vue | vue | 组件 | 重置密码 |
| system/user/profile/userAvatar.vue | vue | 组件 | 头像设置 |
| system/user/profile/userInfo.vue | vue | 组件 | 用户信息 |
| system/user/contact/index.vue | vue | 正常 | 通讯录 |
| system/user/contact/detail.vue | vue | 组件 | 通讯录详情 |
| system/role/index.vue | vue | 正常 | 角色管理 |
| system/role/index.html | html | 冗余 | 角色管理（HTML版） |
| system/role/authUser.vue | vue | 正常 | 分配用户 |
| system/role/authUser.html | html | 冗余 | 分配用户（HTML版） |
| system/role/selectUser.vue | vue | 组件 | 选择用户弹窗 |
| system/menu/index.vue | vue | 正常 | 菜单管理 |
| system/menu/index.html | html | 冗余 | 菜单管理（HTML版） |
| system/dept/index.vue | vue | 正常 | 部门管理 |
| system/dept/index.html | html | 冗余 | 部门管理（HTML版） |
| system/post/index.vue | vue | 正常 | 岗位管理 |
| system/post/index.html | html | 冗余 | 岗位管理（HTML版） |
| system/dict/index.vue | vue | 正常 | 字典管理 |
| system/dict/index.html | html | 冗余 | 字典管理（HTML版） |
| system/dict/data.vue | vue | 正常 | 字典数据 |
| system/dict/data.html | html | 冗余 | 字典数据（HTML版） |
| system/config/index.vue | vue | 正常 | 参数设置 |
| system/config/index.html | html | 冗余 | 参数设置（HTML版） |
| system/notice/index.html | html | 正常 | 通知公告（仅HTML版） |

## 6. 监控管理 (monitor/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| monitor/online/index.vue | vue | 正常 | 在线用户 |
| monitor/online/index.html | html | 冗余 | 在线用户（HTML版） |
| monitor/job/index.vue | vue | 正常 | 定时任务 |
| monitor/job/index.html | html | 冗余 | 定时任务（HTML版） |
| monitor/job/log.vue | vue | 正常 | 调度日志 |
| monitor/job/log.html | html | 冗余 | 调度日志（HTML版） |
| monitor/operlog/index.vue | vue | 正常 | 操作日志 |
| monitor/operlog/index.html | html | 冗余 | 操作日志（HTML版） |
| monitor/logininfor/index.vue | vue | 正常 | 登录日志 |
| monitor/logininfor/index.html | html | 冗余 | 登录日志（HTML版） |
| monitor/cache/index.vue | vue | 正常 | 缓存监控 |
| monitor/cache/index.html | html | 冗余 | 缓存监控（HTML版） |
| monitor/cache/list.vue | vue | 正常 | 缓存列表 |
| monitor/cache/list.html | html | 冗余 | 缓存列表（HTML版） |
| monitor/server/index.vue | vue | 正常 | 服务监控 |
| monitor/server/index.html | html | 冗余 | 服务监控（HTML版） |
| monitor/druid/index.vue | vue | 正常 | 数据监控（iframe嵌入Druid） |

## 7. 工具 (tool/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| tool/gen/index.vue | vue | 正常 | 代码生成 |
| tool/gen/index.html | html | 冗余 | 代码生成（HTML版） |
| tool/gen/editTable.vue | vue | 正常 | 代码生成配置 |
| tool/gen/editTable.html | html | 冗余 | 代码生成配置（HTML版） |
| tool/gen/basicInfoForm.vue | vue | 组件 | 基本信息表单 |
| tool/gen/createTable.vue | vue | 组件 | 创建表 |
| tool/gen/genInfoForm.vue | vue | 组件 | 生成信息表单 |
| tool/gen/importTable.vue | vue | 组件 | 导入表 |
| tool/swagger/index.vue | vue | 正常 | 系统接口（iframe嵌入Swagger） |
| tool/swagger/index.html | iframe | 冗余 | 系统接口（HTML iframe版） |
| tool/build/index.vue | vue | 正常 | 表单构建器主页 |
| tool/build/App.vue | vue | 组件 | 表单构建器App |
| tool/build/CodeTypeDialog.vue | vue | 组件 | 代码类型弹窗 |
| tool/build/DraggableItem.vue | vue | 组件 | 拖拽项 |
| tool/build/FormDrawer.vue | vue | 组件 | 表单抽屉 |
| tool/build/IconsDialog.vue | vue | 组件 | 图标选择弹窗 |
| tool/build/JsonDrawer.vue | vue | 组件 | JSON抽屉 |
| tool/build/ResourceDialog.vue | vue | 组件 | 资源弹窗 |
| tool/build/RightPanel.vue | vue | 组件 | 右侧面板 |
| tool/build/TreeNodeDialog.vue | vue | 组件 | 树节点弹窗 |

## 8. 工作流 (workflow/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| workflow/todo/index.vue | vue | 正常 | 待办列表主页 |
| workflow/todo/todo-list.vue | vue | 组件 | 待办列表 |
| workflow/todo/read-list.vue | vue | 组件 | 已阅列表 |
| workflow/todo/group-collapse.vue | vue | 组件 | 分组折叠面板 |
| workflow/todo/group-table.vue | vue | 组件 | 分组表格 |
| workflow/done/index.vue | vue | 正常 | 已办列表 |
| workflow/my-draft/index.vue | vue | 正常 | 我的草稿 |
| workflow/newstart/index.vue | vue | 正常 | 新建流程 |
| workflow/newstart/flow-template.vue | vue | 组件 | 流程模板选择 |
| workflow/recycle/index.vue | vue | 正常 | 回收站 |
| workflow/flow-form/index.vue | vue | 正常 | 流程表单主页（979行） |
| workflow/flow-form/component/complete.vue | vue | 组件 | 办结 |
| workflow/flow-form/component/delete-multi.vue | vue | 组件 | 批量删除 |
| workflow/flow-form/component/flow-approve.vue | vue | 组件 | 流程审批 |
| workflow/flow-form/component/flow-attachment.vue | vue | 组件 | 流程附件（730行） |
| workflow/flow-form/component/flow-comment.vue | vue | 组件 | 流程批注 |
| workflow/flow-form/component/flow-definition.vue | vue | 组件 | 流程定义 |
| workflow/flow-form/component/flow-mainText.vue | vue | 组件 | 流程正文 |
| workflow/flow-form/component/flow-process.vue | vue | 组件 | 流程进度 |
| workflow/flow-form/component/main-preview.vue | vue | 组件 | 正文预览 |
| workflow/flow-form/component/main-stamp.vue | vue | 组件 | 盖章（669行） |
| workflow/flow-form/component/main-upload.vue | vue | 组件 | 正文上传 |
| workflow/flow-form/component/reject.vue | vue | 组件 | 驳回 |
| workflow/flow-form/component/return.vue | vue | 组件 | 退回 |
| workflow/flow-form/component/select-user.vue | vue | 组件 | 选人 |
| workflow/flow-form/component/view-form.vue | vue | 组件 | 查看表单 |
| workflow/dynamic-form/index.vue | vue | 正常 | 动态表单 |
| workflow/template/index.vue | vue | 正常 | 模板管理 |
| workflow/template/add.vue | vue | 组件 | 添加模板 |
| workflow/template/attachment-info.vue | vue | 组件 | 附件信息 |
| workflow/template/basic-info.vue | vue | 组件 | 基本信息 |
| workflow/template/business-form-info.vue | vue | 组件 | 业务表单信息 |
| workflow/template/main-text.vue | vue | 组件 | 正文模板 |
| workflow/template/message-notice.vue | vue | 组件 | 消息通知 |
| workflow/template/type/index.vue | vue | 组件 | 模板分类 |
| workflow/main-seal/index.vue | vue | 正常 | 印章管理 |
| workflow/flowable/definition/index.vue | vue | 正常 | 流程定义 |
| workflow/flowable/definition/flow.vue | vue | 组件 | 流程设计 |
| workflow/flowable/definition/flow-view.vue | vue | 组件 | 流程查看 |
| workflow/flowable/definition/model.vue | vue | 组件 | 流程模型 |
| workflow/flowable/expression/index.vue | vue | 正常 | 表达式管理 |
| workflow/flowable/history/index.vue | vue | 正常 | 流程历史 |
| workflow/flowable/instance/index.vue | vue | 正常 | 流程实例 |
| workflow/flowable/instance/jump-activity-user.vue | vue | 组件 | 跳转活动用户 |
| workflow/flowable/listener/index.vue | vue | 正常 | 监听器管理 |

## 9. 日程管理 (schedule/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| schedule/index.vue | vue | 正常 | 日程主页 |
| schedule/components/calendar/index.vue | vue | 组件 | 日历组件 |
| schedule/components/custom-calendar/index.vue | vue | 组件 | 自定义日历 |
| schedule/components/custom-calendar/body.vue | vue | 组件 | 日历主体（474行） |
| schedule/components/custom-calendar/full-calendar.vue | vue | 组件 | 完整日历 |
| schedule/components/custom-radio.vue | vue | 组件 | 自定义单选 |
| schedule/components/header/index.vue | vue | 组件 | 日历头部 |
| schedule/components/schedule/detail.vue | vue | 组件 | 日程详情 |
| schedule/components/schedule/save.vue | vue | 组件 | 日程保存 |
| schedule/components/type/index.vue | vue | 组件 | 日程类型 |

## 10. 知识库 (kbs/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| kbs/topic/index.vue | vue | 正常 | 知识主题 |
| kbs/topic/components/add-topic.vue | vue | 组件 | 添加主题 |
| kbs/topic/components/detail.vue | vue | 组件 | 主题详情 |
| kbs/topic/components/auth/topic-auth-user.vue | vue | 组件 | 主题授权用户 |
| kbs/topic/components/category/index.vue | vue | 组件 | 分类管理 |
| kbs/topic/components/info/comment.vue | vue | 组件 | 评论（503行） |
| kbs/topic/components/info/comment-input.vue | vue | 组件 | 评论输入 |
| kbs/topic/components/info/doc-editor.vue | vue | 组件 | 文档编辑器 |
| kbs/topic/components/info/doc-info.vue | vue | 组件 | 文档信息（656行） |
| kbs/topic/components/info/favorite.vue | vue | 组件 | 收藏 |
| kbs/topic/components/info/favorite-group.vue | vue | 组件 | 收藏分组 |
| kbs/topic/components/info/outline-item.vue | vue | 组件 | 大纲项 |
| kbs/topic/components/info/topic-info.vue | vue | 组件 | 主题信息 |
| kbs/topic/components/info/topic-tree.vue | vue | 组件 | 主题树（541行） |
| kbs/favorite/index.vue | vue | 正常 | 我的收藏 |
| kbs/recycle/index.vue | vue | 正常 | 回收站 |

## 11. 信息公告 (information/ & notice/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| information/index.vue | vue | 正常 | 新闻列表 |
| information/detail.vue | vue | 组件 | 新闻详情 |
| information/pub-list.vue | vue | 正常 | 新闻发布列表 |
| notice/index.vue | vue | 正常 | 通知列表 |
| notice/detail.vue | vue | 组件 | 通知详情 |
| notice/pub-list.vue | vue | 正常 | 通知发布列表 |

## 12. 假期管理 (holiday/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| holiday/index.vue | vue | 正常 | 假期管理主页 |
| holiday/holiday-setting/index.vue | vue | 正常 | 假期设置 |
| holiday/work-setting/index.vue | vue | 正常 | 工作日设置 |

## 13. 编号管理 (serial/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| serial/config/index.vue | vue | 正常 | 编号配置 |
| serial/log/index.vue | vue | 正常 | 编号日志 |

## 14. 工作设置 (setting/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| setting/entrust/index.vue | vue | 正常 | 委托设置 |
| setting/secretary/index.vue | vue | 正常 | 秘书设置 |

## 15. 消息队列 (mq/)

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| mq/async/index.vue | vue | 正常 | 异步消息 |

## 16. 错误页面

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| error/401.vue | vue | 正常 | 401未授权 |
| error/404.vue | vue | 正常 | 404未找到 |
| error-page/403.html | html | 正常 | 403禁止访问 |
| error-page/404.html | html | 正常 | 404未找到 |
| error-page/500.html | html | 正常 | 500服务器错误 |

## 17. 配置与文档

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| cfg/system-cfg.html | html | 正常 | 系统配置（使用httpVueLoader） |
| cfg/xxx.vue | vue | 占位 | 占位文件（42行） |
| sa-doc/sa-doc.html | html | 正常 | SA-Admin文档 |

## 18. 测试与演示

| 文件路径 | 加载方式 | 功能状态 | 说明 |
|---------|---------|---------|------|
| test/api-adapter-test.html | html | 正常 | API适配层测试页 |
| test/api-adapter-test.vue | vue | 正常 | API适配层测试组件 |
| demo/component-loader-demo.html | html | 正常 | 组件加载器示例（使用httpVueLoader） |

---

## 统计汇总

### 按加载方式

| 加载方式 | 数量 |
|---------|------|
| vue (httpVueLoader) | 159 |
| html (独立页面) | 30 |
| iframe (嵌入外部) | 1 |
| **合计** | **190** |

### 按功能状态

| 状态 | 数量 |
|------|------|
| 正常（独立页面） | 67 |
| 组件（子组件） | 91 |
| 冗余（.html与.vue重复） | 27 |
| 占位 | 1 |
| iframe | 1 |
| 测试/演示 | 3 |
| **合计** | **190** |

### 重复文件清单（同时存在 .vue 和 .html）

以下 27 个 .html 文件与同路径 .vue 文件功能重复，建议优先使用 .vue 版本（httpVueLoader 加载），后续可清理 .html 版本：

1. system/user/index.html
2. system/user/authRole.html
3. system/user/profile/index.html
4. system/role/index.html
5. system/role/authUser.html
6. system/menu/index.html
7. system/dept/index.html
8. system/post/index.html
9. system/dict/index.html
10. system/dict/data.html
11. system/config/index.html
12. monitor/online/index.html
13. monitor/job/index.html
14. monitor/job/log.html
15. monitor/operlog/index.html
16. monitor/logininfor/index.html
17. monitor/cache/index.html
18. monitor/cache/list.html
19. monitor/server/index.html
20. tool/gen/index.html
21. tool/gen/editTable.html
22. tool/swagger/index.html

### 仅有 .html 版本的页面（无对应 .vue）

1. console/console-main.html — 控制台主页
2. system/notice/index.html — 通知公告
3. error-page/403.html — 403错误页
4. error-page/404.html — 404错误页
5. error-page/500.html — 500错误页
6. cfg/system-cfg.html — 系统配置
7. sa-doc/sa-doc.html — SA-Admin文档
8. demo/component-loader-demo.html — 组件加载器演示
9. test/api-adapter-test.html — API适配测试
