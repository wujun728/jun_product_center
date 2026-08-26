# Vue 2 到 Vue 3 迁移日志

## 项目概览

**原前端项目**: ruoyi-vue-oa-ui (Vue 2)
**目标前端项目**: yudao-ui-admin-vue3 (Vue 3)
**总页面数**: 149个Vue组件
**迁移开始日期**: 2026-04-15

## 迁移统计

| 状态 | 数量 | 百分比 |
|------|------|--------|
| 未开始 | 149 | 100% |
| 进行中 | 0 | 0% |
| 已完成 | 0 | 0% |
| 已验证 | 0 | 0% |

## 模块迁移清单

### 1. 仪表板模块 (Dashboard) - 5个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 1 | dashboard/BarChart.vue | dashboard/BarChart.vue | 未开始 | - | - | - | 图表组件需迁移到ECharts 5 |
| 2 | dashboard/LineChart.vue | dashboard/LineChart.vue | 未开始 | - | - | - | 图表组件需迁移到ECharts 5 |
| 3 | dashboard/PanelGroup.vue | dashboard/PanelGroup.vue | 未开始 | - | - | - | - |
| 4 | dashboard/PieChart.vue | dashboard/PieChart.vue | 未开始 | - | - | - | 图表组件需迁移到ECharts 5 |
| 5 | dashboard/RaddarChart.vue | dashboard/RaddarChart.vue | 未开始 | - | - | - | 图表组件需迁移到ECharts 5 |

### 2. 错误页面 (Error Pages) - 2个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 6 | error/401.vue | error/401.vue | 未开始 | - | - | - | - |
| 7 | error/404.vue | error/404.vue | 未开始 | - | - | - | - |

### 3. 假期管理模块 (Holiday) - 3个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 8 | holiday/holiday-setting/index.vue | holiday/holiday-setting/index.vue | 未开始 | - | - | - | - |
| 9 | holiday/index.vue | holiday/index.vue | 未开始 | - | - | - | - |
| 10 | holiday/work-setting/index.vue | holiday/work-setting/index.vue | 未开始 | - | - | - | - |

### 4. 首页模块 (Home) - 9个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 11 | home/components/FastEntrance/index.vue | home/components/FastEntrance/index.vue | 未开始 | - | - | - | - |
| 12 | home/components/News/index.vue | home/components/News/index.vue | 未开始 | - | - | - | - |
| 13 | home/components/Notice/index.vue | home/components/Notice/index.vue | 未开始 | - | - | - | - |
| 14 | home/components/Schedule/index.vue | home/components/Schedule/index.vue | 未开始 | - | - | - | - |
| 15 | home/components/Static/index.vue | home/components/Static/index.vue | 未开始 | - | - | - | - |
| 16 | home/components/Todo/Collapse.vue | home/components/Todo/Collapse.vue | 未开始 | - | - | - | - |
| 17 | home/components/Todo/Table.vue | home/components/Todo/Table.vue | 未开始 | - | - | - | - |
| 18 | home/components/Welcome/index.vue | home/components/Welcome/index.vue | 未开始 | - | - | - | - |
| 19 | home/index.vue | home/index.vue | 未开始 | - | - | - | - |

### 5. 核心视图 (Core Views) - 2个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 20 | index.vue | index.vue | 未开始 | - | - | - | 主布局组件 |
| 21 | index_v1.vue | index_v1.vue | 未开始 | - | - | - | 备用布局 |

### 6. 资讯模块 (Information) - 3个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 22 | information/detail.vue | information/detail.vue | 未开始 | - | - | - | - |
| 23 | information/index.vue | information/index.vue | 未开始 | - | - | - | - |
| 24 | information/pub-list.vue | information/pub-list.vue | 未开始 | - | - | - | - |

### 7. 知识库模块 (KBS) - 14个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 25 | kbs/favorite/index.vue | kbs/favorite/index.vue | 未开始 | - | - | - | - |
| 26 | kbs/recycle/index.vue | kbs/recycle/index.vue | 未开始 | - | - | - | - |
| 27 | kbs/topic/components/add-topic.vue | kbs/topic/components/add-topic.vue | 未开始 | - | - | - | - |
| 28 | kbs/topic/components/auth/topic-auth-user.vue | kbs/topic/components/auth/topic-auth-user.vue | 未开始 | - | - | - | - |
| 29 | kbs/topic/components/category/index.vue | kbs/topic/components/category/index.vue | 未开始 | - | - | - | - |
| 30 | kbs/topic/components/detail.vue | kbs/topic/components/detail.vue | 未开始 | - | - | - | - |
| 31 | kbs/topic/components/info/comment.vue | kbs/topic/components/info/comment.vue | 未开始 | - | - | - | - |
| 32 | kbs/topic/components/info/comment-input.vue | kbs/topic/components/info/comment-input.vue | 未开始 | - | - | - | - |
| 33 | kbs/topic/components/info/doc-editor.vue | kbs/topic/components/info/doc-editor.vue | 未开始 | - | - | - | 富文本编辑器需适配 |
| 34 | kbs/topic/components/info/doc-info.vue | kbs/topic/components/info/doc-info.vue | 未开始 | - | - | - | - |
| 35 | kbs/topic/components/info/favorite.vue | kbs/topic/components/info/favorite.vue | 未开始 | - | - | - | - |
| 36 | kbs/topic/components/info/favorite-group.vue | kbs/topic/components/info/favorite-group.vue | 未开始 | - | - | - | - |
| 37 | kbs/topic/components/info/outline-item.vue | kbs/topic/components/info/outline-item.vue | 未开始 | - | - | - | - |
| 38 | kbs/topic/components/info/topic-info.vue | kbs/topic/components/info/topic-info.vue | 未开始 | - | - | - | - |
| 39 | kbs/topic/components/info/topic-tree.vue | kbs/topic/components/info/topic-tree.vue | 未开始 | - | - | - | 树形组件需适配 |
| 40 | kbs/topic/index.vue | kbs/topic/index.vue | 未开始 | - | - | - | - |

### 8. 登录注册 (Auth) - 3个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 41 | login.vue | login.vue | 未开始 | - | - | - | 需适配新的认证机制 |
| 42 | register.vue | register.vue | 未开始 | - | - | - | - |
| 43 | redirect.vue | redirect.vue | 未开始 | - | - | - | - |

### 9. 监控模块 (Monitor) - 8个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 44 | monitor/cache/index.vue | monitor/cache/index.vue | 未开始 | - | - | - | - |
| 45 | monitor/cache/list.vue | monitor/cache/list.vue | 未开始 | - | - | - | - |
| 46 | monitor/druid/index.vue | monitor/druid/index.vue | 未开始 | - | - | - | - |
| 47 | monitor/job/index.vue | monitor/job/index.vue | 未开始 | - | - | - | - |
| 48 | monitor/job/log.vue | monitor/job/log.vue | 未开始 | - | - | - | - |
| 49 | monitor/logininfor/index.vue | monitor/logininfor/index.vue | 未开始 | - | - | - | - |
| 50 | monitor/online/index.vue | monitor/online/index.vue | 未开始 | - | - | - | - |
| 51 | monitor/operlog/index.vue | monitor/operlog/index.vue | 未开始 | - | - | - | - |
| 52 | monitor/server/index.vue | monitor/server/index.vue | 未开始 | - | - | - | - |

### 10. 消息队列 (MQ) - 1个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 53 | mq/async/index.vue | mq/async/index.vue | 未开始 | - | - | - | - |

### 11. 通知公告模块 (Notice) - 3个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 54 | notice/detail.vue | notice/detail.vue | 未开始 | - | - | - | - |
| 55 | notice/index.vue | notice/index.vue | 未开始 | - | - | - | - |
| 56 | notice/pub-list.vue | notice/pub-list.vue | 未开始 | - | - | - | - |

### 12. 日程安排模块 (Schedule) - 10个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 57 | schedule/components/calendar/index.vue | schedule/components/calendar/index.vue | 未开始 | - | - | - | 日历组件需适配 |
| 58 | schedule/components/custom-calendar/body.vue | schedule/components/custom-calendar/body.vue | 未开始 | - | - | - | - |
| 59 | schedule/components/custom-calendar/full-calendar.vue | schedule/components/custom-calendar/full-calendar.vue | 未开始 | - | - | - | - |
| 60 | schedule/components/custom-calendar/index.vue | schedule/components/custom-calendar/index.vue | 未开始 | - | - | - | - |
| 61 | schedule/components/custom-radio.vue | schedule/components/custom-radio.vue | 未开始 | - | - | - | - |
| 62 | schedule/components/header/index.vue | schedule/components/header/index.vue | 未开始 | - | - | - | - |
| 63 | schedule/components/schedule/detail.vue | schedule/components/schedule/detail.vue | 未开始 | - | - | - | - |
| 64 | schedule/components/schedule/save.vue | schedule/components/schedule/save.vue | 未开始 | - | - | - | - |
| 65 | schedule/components/type/index.vue | schedule/components/type/index.vue | 未开始 | - | - | - | - |
| 66 | schedule/index.vue | schedule/index.vue | 未开始 | - | - | - | - |

### 13. 流水号模块 (Serial) - 2个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 67 | serial/config/index.vue | serial/config/index.vue | 未开始 | - | - | - | - |
| 68 | serial/log/index.vue | serial/log/index.vue | 未开始 | - | - | - | - |

### 14. 工作设置模块 (Setting) - 2个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 69 | setting/entrust/index.vue | setting/entrust/index.vue | 未开始 | - | - | - | - |
| 70 | setting/secretary/index.vue | setting/secretary/index.vue | 未开始 | - | - | - | - |

### 15. 系统管理模块 (System) - 16个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 71 | system/config/index.vue | system/config/index.vue | 未开始 | - | - | - | - |
| 72 | system/dept/index.vue | system/dept/index.vue | 未开始 | - | - | - | - |
| 73 | system/dict/data.vue | system/dict/data.vue | 未开始 | - | - | - | - |
| 74 | system/dict/index.vue | system/dict/index.vue | 未开始 | - | - | - | - |
| 75 | system/menu/index.vue | system/menu/index.vue | 未开始 | - | - | - | - |
| 76 | system/post/index.vue | system/post/index.vue | 未开始 | - | - | - | - |
| 77 | system/role/authUser.vue | system/role/authUser.vue | 未开始 | - | - | - | - |
| 78 | system/role/index.vue | system/role/index.vue | 未开始 | - | - | - | - |
| 79 | system/role/selectUser.vue | system/role/selectUser.vue | 未开始 | - | - | - | - |
| 80 | system/user/authRole.vue | system/user/authRole.vue | 未开始 | - | - | - | - |
| 81 | system/user/contact/detail.vue | system/user/contact/detail.vue | 未开始 | - | - | - | - |
| 82 | system/user/contact/index.vue | system/user/contact/index.vue | 未开始 | - | - | - | - |
| 83 | system/user/index.vue | system/user/index.vue | 未开始 | - | - | - | - |
| 84 | system/user/profile/index.vue | system/user/profile/index.vue | 未开始 | - | - | - | - |
| 85 | system/user/profile/resetPwd.vue | system/user/profile/resetPwd.vue | 未开始 | - | - | - | - |
| 86 | system/user/profile/userAvatar.vue | system/user/profile/userAvatar.vue | 未开始 | - | - | - | - |
| 87 | system/user/profile/userInfo.vue | system/user/profile/userInfo.vue | 未开始 | - | - | - | - |

### 16. 开发工具模块 (Tool) - 16个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 88 | tool/build/App.vue | tool/build/App.vue | 未开始 | - | - | - | 表单构建器 |
| 89 | tool/build/CodeTypeDialog.vue | tool/build/CodeTypeDialog.vue | 未开始 | - | - | - | - |
| 90 | tool/build/DraggableItem.vue | tool/build/DraggableItem.vue | 未开始 | - | - | - | 拖拽组件需适配 |
| 91 | tool/build/FormDrawer.vue | tool/build/FormDrawer.vue | 未开始 | - | - | - | - |
| 92 | tool/build/IconsDialog.vue | tool/build/IconsDialog.vue | 未开始 | - | - | - | - |
| 93 | tool/build/index.vue | tool/build/index.vue | 未开始 | - | - | - | - |
| 94 | tool/build/JsonDrawer.vue | tool/build/JsonDrawer.vue | 未开始 | - | - | - | - |
| 95 | tool/build/ResourceDialog.vue | tool/build/ResourceDialog.vue | 未开始 | - | - | - | - |
| 96 | tool/build/RightPanel.vue | tool/build/RightPanel.vue | 未开始 | - | - | - | - |
| 97 | tool/build/TreeNodeDialog.vue | tool/build/TreeNodeDialog.vue | 未开始 | - | - | - | - |
| 98 | tool/gen/basicInfoForm.vue | tool/gen/basicInfoForm.vue | 未开始 | - | - | - | 代码生成器 |
| 99 | tool/gen/createTable.vue | tool/gen/createTable.vue | 未开始 | - | - | - | - |
| 100 | tool/gen/editTable.vue | tool/gen/editTable.vue | 未开始 | - | - | - | - |
| 101 | tool/gen/genInfoForm.vue | tool/gen/genInfoForm.vue | 未开始 | - | - | - | - |
| 102 | tool/gen/importTable.vue | tool/gen/importTable.vue | 未开始 | - | - | - | - |
| 103 | tool/gen/index.vue | tool/gen/index.vue | 未开始 | - | - | - | - |
| 104 | tool/swagger/index.vue | tool/swagger/index.vue | 未开始 | - | - | - | - |

### 17. 工作流模块 (Workflow) - 46个组件

#### 17.1 工作流核心 - 3个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 105 | workflow/done/index.vue | workflow/done/index.vue | 未开始 | - | - | - | - |
| 106 | workflow/dynamic-form/index.vue | workflow/dynamic-form/index.vue | 未开始 | - | - | - | 动态表单需适配 |
| 107 | workflow/my-draft/index.vue | workflow/my-draft/index.vue | 未开始 | - | - | - | - |

#### 17.2 Flowable引擎 - 9个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 108 | workflow/flowable/definition/flow.vue | workflow/flowable/definition/flow.vue | 未开始 | - | - | - | BPMN流程设计器 |
| 109 | workflow/flowable/definition/flow-view.vue | workflow/flowable/definition/flow-view.vue | 未开始 | - | - | - | - |
| 110 | workflow/flowable/definition/index.vue | workflow/flowable/definition/index.vue | 未开始 | - | - | - | - |
| 111 | workflow/flowable/definition/model.vue | workflow/flowable/definition/model.vue | 未开始 | - | - | - | - |
| 112 | workflow/flowable/expression/index.vue | workflow/flowable/expression/index.vue | 未开始 | - | - | - | - |
| 113 | workflow/flowable/history/index.vue | workflow/flowable/history/index.vue | 未开始 | - | - | - | - |
| 114 | workflow/flowable/instance/index.vue | workflow/flowable/instance/index.vue | 未开始 | - | - | - | - |
| 115 | workflow/flowable/instance/jump-activity-user.vue | workflow/flowable/instance/jump-activity-user.vue | 未开始 | - | - | - | - |
| 116 | workflow/flowable/listener/index.vue | workflow/flowable/listener/index.vue | 未开始 | - | - | - | - |

#### 17.3 流程表单 - 15个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 117 | workflow/flow-form/component/complete.vue | workflow/flow-form/component/complete.vue | 未开始 | - | - | - | - |
| 118 | workflow/flow-form/component/delete-multi.vue | workflow/flow-form/component/delete-multi.vue | 未开始 | - | - | - | - |
| 119 | workflow/flow-form/component/flow-approve.vue | workflow/flow-form/component/flow-approve.vue | 未开始 | - | - | - | - |
| 120 | workflow/flow-form/component/flow-attachment.vue | workflow/flow-form/component/flow-attachment.vue | 未开始 | - | - | - | 附件上传需适配 |
| 121 | workflow/flow-form/component/flow-comment.vue | workflow/flow-form/component/flow-comment.vue | 未开始 | - | - | - | - |
| 122 | workflow/flow-form/component/flow-definition.vue | workflow/flow-form/component/flow-definition.vue | 未开始 | - | - | - | - |
| 123 | workflow/flow-form/component/flow-mainText.vue | workflow/flow-form/component/flow-mainText.vue | 未开始 | - | - | - | - |
| 124 | workflow/flow-form/component/flow-process.vue | workflow/flow-form/component/flow-process.vue | 未开始 | - | - | - | - |
| 125 | workflow/flow-form/component/main-preview.vue | workflow/flow-form/component/main-preview.vue | 未开始 | - | - | - | - |
| 126 | workflow/flow-form/component/main-stamp.vue | workflow/flow-form/component/main-stamp.vue | 未开始 | - | - | - | 电子印章需适配 |
| 127 | workflow/flow-form/component/main-upload.vue | workflow/flow-form/component/main-upload.vue | 未开始 | - | - | - | - |
| 128 | workflow/flow-form/component/reject.vue | workflow/flow-form/component/reject.vue | 未开始 | - | - | - | - |
| 129 | workflow/flow-form/component/return.vue | workflow/flow-form/component/return.vue | 未开始 | - | - | - | - |
| 130 | workflow/flow-form/component/select-user.vue | workflow/flow-form/component/select-user.vue | 未开始 | - | - | - | - |
| 131 | workflow/flow-form/component/view-form.vue | workflow/flow-form/component/view-form.vue | 未开始 | - | - | - | - |
| 132 | workflow/flow-form/index.vue | workflow/flow-form/index.vue | 未开始 | - | - | - | - |

#### 17.4 流程管理 - 4个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 133 | workflow/main-seal/index.vue | workflow/main-seal/index.vue | 未开始 | - | - | - | - |
| 134 | workflow/newstart/flow-template.vue | workflow/newstart/flow-template.vue | 未开始 | - | - | - | - |
| 135 | workflow/newstart/index.vue | workflow/newstart/index.vue | 未开始 | - | - | - | - |
| 136 | workflow/recycle/index.vue | workflow/recycle/index.vue | 未开始 | - | - | - | - |

#### 17.5 模板管理 - 8个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 137 | workflow/template/add.vue | workflow/template/add.vue | 未开始 | - | - | - | - |
| 138 | workflow/template/attachment-info.vue | workflow/template/attachment-info.vue | 未开始 | - | - | - | - |
| 139 | workflow/template/basic-info.vue | workflow/template/basic-info.vue | 未开始 | - | - | - | - |
| 140 | workflow/template/business-form-info.vue | workflow/template/business-form-info.vue | 未开始 | - | - | - | - |
| 141 | workflow/template/index.vue | workflow/template/index.vue | 未开始 | - | - | - | - |
| 142 | workflow/template/main-text.vue | workflow/template/main-text.vue | 未开始 | - | - | - | - |
| 143 | workflow/template/message-notice.vue | workflow/template/message-notice.vue | 未开始 | - | - | - | - |
| 144 | workflow/template/type/index.vue | workflow/template/type/index.vue | 未开始 | - | - | - | - |

#### 17.6 待办事项 - 5个组件

| 序号 | 原路径 | 目标路径 | 状态 | 负责人 | 开始时间 | 完成时间 | 备注 |
|------|---------|---------|------|--------|----------|----------|------|
| 145 | workflow/todo/group-collapse.vue | workflow/todo/group-collapse.vue | 未开始 | - | - | - | - |
| 146 | workflow/todo/group-table.vue | workflow/todo/group-table.vue | 未开始 | - | - | - | - |
| 147 | workflow/todo/index.vue | workflow/todo/index.vue | 未开始 | - | - | - | - |
| 148 | workflow/todo/read-list.vue | workflow/todo/read-list.vue | 未开始 | - | - | - | - |
| 149 | workflow/todo/todo-list.vue | workflow/todo/todo-list.vue | 未开始 | - | - | - | - |

## 迁移优先级建议

### P0 - 核心功能（第一批次）
1. 登录注册 (login.vue, register.vue)
2. 主布局 (index.vue)
3. 首页 (home/index.vue)
4. 错误页面 (error/401.vue, error/404.vue)

### P1 - 系统管理（第二批次）
1. 用户管理 (system/user/*)
2. 角色管理 (system/role/*)
3. 菜单管理 (system/menu/*)
4. 部门管理 (system/dept/*)

### P2 - 工作流核心（第三批次）
1. 待办列表 (workflow/todo/*)
2. 流程表单 (workflow/flow-form/*)
3. 流程定义 (workflow/flowable/definition/*)
4. 已办事项 (workflow/done/*)

### P3 - 业务模块（第四批次）
1. 知识库 (kbs/*)
2. 资讯公告 (information/*, notice/*)
3. 日程安排 (schedule/*)
4. 监控模块 (monitor/*)

### P4 - 辅助功能（第五批次）
1. 开发工具 (tool/*)
2. 假期管理 (holiday/*)
3. 流水号 (serial/*)
4. 工作设置 (setting/*)

## 变更历史

| 日期 | 修改内容 | 修改人 |
|------|----------|--------|
| 2026-04-15 | 初始创建，导入149个Vue2页面清单 | Claude Agent |

## 备注

- 状态说明：未开始、进行中、已完成、已验证、已暂停、有问题
- 每个模块迁移完成后需更新统计数据
- 遇到问题请在 MIGRATION_ISSUES.md 中记录
- 建议按优先级分批次迁移，每批次完成后进行集成测试
