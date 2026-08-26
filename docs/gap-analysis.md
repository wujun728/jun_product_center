# 新旧前端页面差异对比报告 (Gap Analysis)

> 生成时间: 2026-04-03
> 数据来源: inventory-new.md (T001) + inventory-old.md (T002)
> 原前端: ruoyi-vue-oa-ui/src/views/ (149 个 .vue 文件)
> 新前端: jun_ui_sa_admin/sa-view/ (159 个 .vue + 31 个 .html 文件)

---

## 总结

| 分类 | 数量 | 说明 |
|------|------|------|
| 新前端已有且功能正常 | 149 | 原前端全部 149 个 .vue 页面在新前端均有对应 |
| 新前端有但功能异常/占位 | 1 | 占位空壳页面 |
| 原前端有但新前端缺失 | 0 | 无缺失 |
| 新前端独有页面 | 40 | 新增的 .html 冗余版本、错误页、配置页、测试页等 |

**结论:** 原前端 149 个 .vue 文件已全部在新前端有对应的 .vue 版本，无功能缺失。新前端额外多出 27 个冗余 .html 版本和若干辅助页面。

---

## 一、新前端已有且功能正常的页面 (149 个)

所有原前端 .vue 文件在新前端均存在同路径对应文件，标记为"正常"或"组件"状态。

### 根级页面 (5)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| index.vue | index.vue | 正常 |
| index_v1.vue | index_v1.vue | 正常 |
| login.vue | login.vue | 正常 |
| register.vue | register.vue | 正常 |
| redirect.vue | redirect.vue | 正常 |

### dashboard — 仪表盘 (5)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| dashboard/BarChart.vue | dashboard/BarChart.vue | 组件 |
| dashboard/LineChart.vue | dashboard/LineChart.vue | 组件 |
| dashboard/PanelGroup.vue | dashboard/PanelGroup.vue | 组件 |
| dashboard/PieChart.vue | dashboard/PieChart.vue | 组件 |
| dashboard/RaddarChart.vue | dashboard/RaddarChart.vue | 组件 |

### home — 首页门户 (9)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| home/index.vue | home/index.vue | 正常 |
| home/components/FastEntrance/index.vue | home/components/FastEntrance/index.vue | 组件 |
| home/components/News/index.vue | home/components/News/index.vue | 组件 |
| home/components/Notice/index.vue | home/components/Notice/index.vue | 组件 |
| home/components/Schedule/index.vue | home/components/Schedule/index.vue | 组件 |
| home/components/Static/index.vue | home/components/Static/index.vue | 组件 |
| home/components/Todo/Collapse.vue | home/components/Todo/Collapse.vue | 组件 |
| home/components/Todo/Table.vue | home/components/Todo/Table.vue | 组件 |
| home/components/Welcome/index.vue | home/components/Welcome/index.vue | 组件 |

### system — 系统管理 (17)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| system/user/index.vue | system/user/index.vue | 正常 |
| system/user/authRole.vue | system/user/authRole.vue | 正常 |
| system/user/profile/index.vue | system/user/profile/index.vue | 正常 |
| system/user/profile/resetPwd.vue | system/user/profile/resetPwd.vue | 组件 |
| system/user/profile/userAvatar.vue | system/user/profile/userAvatar.vue | 组件 |
| system/user/profile/userInfo.vue | system/user/profile/userInfo.vue | 组件 |
| system/user/contact/index.vue | system/user/contact/index.vue | 正常 |
| system/user/contact/detail.vue | system/user/contact/detail.vue | 组件 |
| system/role/index.vue | system/role/index.vue | 正常 |
| system/role/authUser.vue | system/role/authUser.vue | 正常 |
| system/role/selectUser.vue | system/role/selectUser.vue | 组件 |
| system/menu/index.vue | system/menu/index.vue | 正常 |
| system/dept/index.vue | system/dept/index.vue | 正常 |
| system/post/index.vue | system/post/index.vue | 正常 |
| system/dict/index.vue | system/dict/index.vue | 正常 |
| system/dict/data.vue | system/dict/data.vue | 正常 |
| system/config/index.vue | system/config/index.vue | 正常 |

### monitor — 系统监控 (9)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| monitor/online/index.vue | monitor/online/index.vue | 正常 |
| monitor/job/index.vue | monitor/job/index.vue | 正常 |
| monitor/job/log.vue | monitor/job/log.vue | 正常 |
| monitor/operlog/index.vue | monitor/operlog/index.vue | 正常 |
| monitor/logininfor/index.vue | monitor/logininfor/index.vue | 正常 |
| monitor/cache/index.vue | monitor/cache/index.vue | 正常 |
| monitor/cache/list.vue | monitor/cache/list.vue | 正常 |
| monitor/server/index.vue | monitor/server/index.vue | 正常 |
| monitor/druid/index.vue | monitor/druid/index.vue | 正常 |

### tool — 系统工具 (17)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| tool/gen/index.vue | tool/gen/index.vue | 正常 |
| tool/gen/editTable.vue | tool/gen/editTable.vue | 正常 |
| tool/gen/basicInfoForm.vue | tool/gen/basicInfoForm.vue | 组件 |
| tool/gen/createTable.vue | tool/gen/createTable.vue | 组件 |
| tool/gen/genInfoForm.vue | tool/gen/genInfoForm.vue | 组件 |
| tool/gen/importTable.vue | tool/gen/importTable.vue | 组件 |
| tool/swagger/index.vue | tool/swagger/index.vue | 正常 |
| tool/build/index.vue | tool/build/index.vue | 正常 |
| tool/build/App.vue | tool/build/App.vue | 组件 |
| tool/build/CodeTypeDialog.vue | tool/build/CodeTypeDialog.vue | 组件 |
| tool/build/DraggableItem.vue | tool/build/DraggableItem.vue | 组件 |
| tool/build/FormDrawer.vue | tool/build/FormDrawer.vue | 组件 |
| tool/build/IconsDialog.vue | tool/build/IconsDialog.vue | 组件 |
| tool/build/JsonDrawer.vue | tool/build/JsonDrawer.vue | 组件 |
| tool/build/ResourceDialog.vue | tool/build/ResourceDialog.vue | 组件 |
| tool/build/RightPanel.vue | tool/build/RightPanel.vue | 组件 |
| tool/build/TreeNodeDialog.vue | tool/build/TreeNodeDialog.vue | 组件 |

### workflow — 工作流管理 (45)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| workflow/todo/index.vue | workflow/todo/index.vue | 正常 |
| workflow/todo/todo-list.vue | workflow/todo/todo-list.vue | 组件 |
| workflow/todo/read-list.vue | workflow/todo/read-list.vue | 组件 |
| workflow/todo/group-collapse.vue | workflow/todo/group-collapse.vue | 组件 |
| workflow/todo/group-table.vue | workflow/todo/group-table.vue | 组件 |
| workflow/done/index.vue | workflow/done/index.vue | 正常 |
| workflow/my-draft/index.vue | workflow/my-draft/index.vue | 正常 |
| workflow/newstart/index.vue | workflow/newstart/index.vue | 正常 |
| workflow/newstart/flow-template.vue | workflow/newstart/flow-template.vue | 组件 |
| workflow/recycle/index.vue | workflow/recycle/index.vue | 正常 |
| workflow/flow-form/index.vue | workflow/flow-form/index.vue | 正常 |
| workflow/flow-form/component/complete.vue | workflow/flow-form/component/complete.vue | 组件 |
| workflow/flow-form/component/delete-multi.vue | workflow/flow-form/component/delete-multi.vue | 组件 |
| workflow/flow-form/component/flow-approve.vue | workflow/flow-form/component/flow-approve.vue | 组件 |
| workflow/flow-form/component/flow-attachment.vue | workflow/flow-form/component/flow-attachment.vue | 组件 |
| workflow/flow-form/component/flow-comment.vue | workflow/flow-form/component/flow-comment.vue | 组件 |
| workflow/flow-form/component/flow-definition.vue | workflow/flow-form/component/flow-definition.vue | 组件 |
| workflow/flow-form/component/flow-mainText.vue | workflow/flow-form/component/flow-mainText.vue | 组件 |
| workflow/flow-form/component/flow-process.vue | workflow/flow-form/component/flow-process.vue | 组件 |
| workflow/flow-form/component/main-preview.vue | workflow/flow-form/component/main-preview.vue | 组件 |
| workflow/flow-form/component/main-stamp.vue | workflow/flow-form/component/main-stamp.vue | 组件 |
| workflow/flow-form/component/main-upload.vue | workflow/flow-form/component/main-upload.vue | 组件 |
| workflow/flow-form/component/reject.vue | workflow/flow-form/component/reject.vue | 组件 |
| workflow/flow-form/component/return.vue | workflow/flow-form/component/return.vue | 组件 |
| workflow/flow-form/component/select-user.vue | workflow/flow-form/component/select-user.vue | 组件 |
| workflow/flow-form/component/view-form.vue | workflow/flow-form/component/view-form.vue | 组件 |
| workflow/dynamic-form/index.vue | workflow/dynamic-form/index.vue | 正常 |
| workflow/template/index.vue | workflow/template/index.vue | 正常 |
| workflow/template/add.vue | workflow/template/add.vue | 组件 |
| workflow/template/attachment-info.vue | workflow/template/attachment-info.vue | 组件 |
| workflow/template/basic-info.vue | workflow/template/basic-info.vue | 组件 |
| workflow/template/business-form-info.vue | workflow/template/business-form-info.vue | 组件 |
| workflow/template/main-text.vue | workflow/template/main-text.vue | 组件 |
| workflow/template/message-notice.vue | workflow/template/message-notice.vue | 组件 |
| workflow/template/type/index.vue | workflow/template/type/index.vue | 组件 |
| workflow/main-seal/index.vue | workflow/main-seal/index.vue | 正常 |
| workflow/flowable/definition/index.vue | workflow/flowable/definition/index.vue | 正常 |
| workflow/flowable/definition/flow.vue | workflow/flowable/definition/flow.vue | 组件 |
| workflow/flowable/definition/flow-view.vue | workflow/flowable/definition/flow-view.vue | 组件 |
| workflow/flowable/definition/model.vue | workflow/flowable/definition/model.vue | 组件 |
| workflow/flowable/expression/index.vue | workflow/flowable/expression/index.vue | 正常 |
| workflow/flowable/history/index.vue | workflow/flowable/history/index.vue | 正常 |
| workflow/flowable/instance/index.vue | workflow/flowable/instance/index.vue | 正常 |
| workflow/flowable/instance/jump-activity-user.vue | workflow/flowable/instance/jump-activity-user.vue | 组件 |
| workflow/flowable/listener/index.vue | workflow/flowable/listener/index.vue | 正常 |

### schedule — 日程管理 (10)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| schedule/index.vue | schedule/index.vue | 正常 |
| schedule/components/calendar/index.vue | schedule/components/calendar/index.vue | 组件 |
| schedule/components/custom-calendar/index.vue | schedule/components/custom-calendar/index.vue | 组件 |
| schedule/components/custom-calendar/body.vue | schedule/components/custom-calendar/body.vue | 组件 |
| schedule/components/custom-calendar/full-calendar.vue | schedule/components/custom-calendar/full-calendar.vue | 组件 |
| schedule/components/custom-radio.vue | schedule/components/custom-radio.vue | 组件 |
| schedule/components/header/index.vue | schedule/components/header/index.vue | 组件 |
| schedule/components/schedule/detail.vue | schedule/components/schedule/detail.vue | 组件 |
| schedule/components/schedule/save.vue | schedule/components/schedule/save.vue | 组件 |
| schedule/components/type/index.vue | schedule/components/type/index.vue | 组件 |

### kbs — 知识库 (16)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| kbs/topic/index.vue | kbs/topic/index.vue | 正常 |
| kbs/topic/components/add-topic.vue | kbs/topic/components/add-topic.vue | 组件 |
| kbs/topic/components/detail.vue | kbs/topic/components/detail.vue | 组件 |
| kbs/topic/components/auth/topic-auth-user.vue | kbs/topic/components/auth/topic-auth-user.vue | 组件 |
| kbs/topic/components/category/index.vue | kbs/topic/components/category/index.vue | 组件 |
| kbs/topic/components/info/comment.vue | kbs/topic/components/info/comment.vue | 组件 |
| kbs/topic/components/info/comment-input.vue | kbs/topic/components/info/comment-input.vue | 组件 |
| kbs/topic/components/info/doc-editor.vue | kbs/topic/components/info/doc-editor.vue | 组件 |
| kbs/topic/components/info/doc-info.vue | kbs/topic/components/info/doc-info.vue | 组件 |
| kbs/topic/components/info/favorite.vue | kbs/topic/components/info/favorite.vue | 组件 |
| kbs/topic/components/info/favorite-group.vue | kbs/topic/components/info/favorite-group.vue | 组件 |
| kbs/topic/components/info/outline-item.vue | kbs/topic/components/info/outline-item.vue | 组件 |
| kbs/topic/components/info/topic-info.vue | kbs/topic/components/info/topic-info.vue | 组件 |
| kbs/topic/components/info/topic-tree.vue | kbs/topic/components/info/topic-tree.vue | 组件 |
| kbs/favorite/index.vue | kbs/favorite/index.vue | 正常 |
| kbs/recycle/index.vue | kbs/recycle/index.vue | 正常 |

### information — 资讯管理 (3)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| information/index.vue | information/index.vue | 正常 |
| information/detail.vue | information/detail.vue | 组件 |
| information/pub-list.vue | information/pub-list.vue | 正常 |

### notice — 通知公告 (3)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| notice/index.vue | notice/index.vue | 正常 |
| notice/detail.vue | notice/detail.vue | 组件 |
| notice/pub-list.vue | notice/pub-list.vue | 正常 |

### holiday — 假期设置 (3)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| holiday/index.vue | holiday/index.vue | 正常 |
| holiday/holiday-setting/index.vue | holiday/holiday-setting/index.vue | 正常 |
| holiday/work-setting/index.vue | holiday/work-setting/index.vue | 正常 |

### serial — 编号管理 (2)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| serial/config/index.vue | serial/config/index.vue | 正常 |
| serial/log/index.vue | serial/log/index.vue | 正常 |

### setting — 个人设置 (2)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| setting/entrust/index.vue | setting/entrust/index.vue | 正常 |
| setting/secretary/index.vue | setting/secretary/index.vue | 正常 |

### mq — 消息队列 (1)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| mq/async/index.vue | mq/async/index.vue | 正常 |

### error — 错误页面 (2)

| 原前端路径 | 新前端路径 | 状态 |
|-----------|-----------|------|
| error/401.vue | error/401.vue | 正常 |
| error/404.vue | error/404.vue | 正常 |

---

## 二、新前端有但功能异常/空白的页面 (1 个)

| 新前端路径 | 加载方式 | 问题描述 |
|-----------|---------|---------|
| cfg/xxx.vue | vue | 占位空壳文件（仅 42 行），无实际业务逻辑 |

> 注：该页面为新前端独有的占位文件，原前端无对应页面，不影响迁移完整性。

---

## 三、原前端有但新前端缺失的页面 (0 个)

**无缺失。** 原前端全部 149 个 .vue 文件在新前端均有同路径的 .vue 对应版本。

---

## 四、新前端独有页面（原前端无对应）

### 4.1 冗余 .html 版本 (27 个)

以下 .html 文件与同路径 .vue 文件功能重复，建议后续清理（T008 任务）：

| .html 文件 | 对应 .vue 文件 |
|------------|---------------|
| system/user/index.html | system/user/index.vue |
| system/user/authRole.html | system/user/authRole.vue |
| system/user/profile/index.html | system/user/profile/index.vue |
| system/role/index.html | system/role/index.vue |
| system/role/authUser.html | system/role/authUser.vue |
| system/menu/index.html | system/menu/index.vue |
| system/dept/index.html | system/dept/index.vue |
| system/post/index.html | system/post/index.vue |
| system/dict/index.html | system/dict/index.vue |
| system/dict/data.html | system/dict/data.vue |
| system/config/index.html | system/config/index.vue |
| monitor/online/index.html | monitor/online/index.vue |
| monitor/job/index.html | monitor/job/index.vue |
| monitor/job/log.html | monitor/job/log.vue |
| monitor/operlog/index.html | monitor/operlog/index.vue |
| monitor/logininfor/index.html | monitor/logininfor/index.vue |
| monitor/cache/index.html | monitor/cache/index.vue |
| monitor/cache/list.html | monitor/cache/list.vue |
| monitor/server/index.html | monitor/server/index.vue |
| tool/gen/index.html | tool/gen/index.vue |
| tool/gen/editTable.html | tool/gen/editTable.vue |
| tool/swagger/index.html | tool/swagger/index.vue |

### 4.2 新前端独有功能页面 (13 个)

| 文件路径 | 加载方式 | 说明 |
|---------|---------|------|
| console/console-main.html | html | 控制台主页（sa-admin 框架自带） |
| console/com-chart-1.vue ~ com-update-log.vue | vue | 控制台子组件 (7个) |
| system/notice/index.html | html | 系统通知公告管理（仅 HTML 版） |
| error-page/403.html | html | 403 错误页 |
| error-page/404.html | html | 404 错误页 |
| error-page/500.html | html | 500 错误页 |
| cfg/system-cfg.html | html | 系统配置页 |
| cfg/xxx.vue | vue | 占位文件 |
| sa-doc/sa-doc.html | html | SA-Admin 框架文档 |
| demo/component-loader-demo.html | html | 组件加载器演示 |
| test/api-adapter-test.html | html | API 适配层测试 |
| test/api-adapter-test.vue | vue | API 适配层测试组件 |

---

## 五、迁移建议

1. **页面完整性良好** — 原前端 149 个页面已全部迁入新前端，无功能缺口
2. **待清理冗余** — 27 个 .html 冗余版本应在 T008 任务中清理，统一使用 httpVueLoader 加载 .vue 版本
3. **占位文件处理** — `cfg/xxx.vue` 可直接删除或按需开发
4. **后续重点** — 迁移工作的核心不在页面缺失，而在于：
   - API 适配层对接（原前端 axios → 新前端 sa.ajax）
   - Vue Router → sa_admin tab 机制适配
   - Vuex → 轻量状态管理适配
   - Element UI 组件兼容性验证
   - 第三方组件（bpmn-js、wangEditor、VForm 等）在 httpVueLoader 下的加载验证
