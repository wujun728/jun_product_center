# 原前端 API 模块清单

> 来源目录: `ruoyi-vue-oa-ui/src/api/`
> 生成时间: 2026-04-03
> 任务编号: T003

## 统计摘要

| 指标 | 数量 |
|------|------|
| API 文件总数 | 59 |
| 业务模块数 | 14 |
| 导出方法总数 | 321 |

## 模块明细

### 1. 根目录 (2 个文件, 6 个方法)

#### login.js
- `login(username, password, code, uuid)` — 用户登录
- `register(data)` — 用户注册
- `getInfo()` — 获取用户信息
- `logout()` — 退出登录
- `getCodeImg()` — 获取验证码图片

#### menu.js
- `getRouters()` — 获取路由菜单

---

### 2. file/ — 文件服务 (2 个文件, 6 个方法)

#### file/index.js
- `mergeSimpleUpload()` — 合并分块上传

#### file/operate.js
- `previewFile(param)` — 预览文件
- `downloadfile(param)` — 下载文件
- `rename(data)` — 重命名
- `sort(data)` — 排序
- `delFile(data)` — 删除文件

---

### 3. information/ — 新闻公告 (1 个文件, 9 个方法)

#### information/information.js
- `listInformation(query)` — 列表查询
- `listPubInformation(query)` — 公开列表查询
- `getInformation(id)` — 获取详情
- `addInformation(data)` — 新增
- `updateInformation(data)` — 更新
- `delInformation(id)` — 删除
- `changeStatus(id, status)` — 修改状态
- `toTop(data)` — 置顶
- `addReadNum(id)` — 增加阅读数

---

### 4. kbs/ — 知识库 (8 个文件, 39 个方法)

#### kbs/document/comment.js
- `listComment(query)` — 列表查询
- `getComment(id)` — 获取详情
- `addComment(data)` — 新增评论
- `updateComment(data)` — 更新评论
- `delComment(id)` — 删除评论
- `listCommentByParent(query)` — 按父级查询评论

#### kbs/document/document.js
- `statDocumentRelate(docId)` — 统计文档关联
- `addView(data)` — 添加浏览记录

#### kbs/document/like.js
- `addLike(data)` — 点赞
- `delLike(commentId)` — 取消点赞

#### kbs/favorite/favorite.js
- `listFavorite(query)` — 收藏列表
- `getFavorite(id)` — 获取收藏详情
- `addFavorite(data)` — 添加收藏
- `updateFavorite(data)` — 更新收藏
- `delFavorite(id)` — 删除收藏
- `statFavorite(docId)` — 统计收藏
- `getFavoriteByUser(docId)` — 按用户获取收藏
- `cancelFavorite(docId)` — 取消收藏

#### kbs/favorite/group.js
- `listGroup(query)` — 分组列表
- `getGroup(id)` — 获取分组
- `addGroup(data)` — 新增分组
- `updateGroup(data)` — 更新分组
- `delGroup(id)` — 删除分组
- `getGroupSelectList()` — 分组下拉列表

#### kbs/recycle/recycle.js
- `listRecycle(query)` — 回收站列表
- `getRecycle(id)` — 获取详情
- `addRecycle(data)` — 新增
- `updateRecycle(data)` — 更新
- `delRecycle(id)` — 删除
- `recoverRecycle(id)` — 恢复

#### kbs/topic/category.js
- `listCategory(query)` — 分类列表
- `getCategory(id)` — 获取分类
- `addCategory(data)` — 新增分类
- `updateCategory(data)` — 更新分类
- `delCategory(id)` — 删除分类
- `getCategorySelectList()` — 分类下拉列表

#### kbs/topic/info.js
- `listInfo(query)` — 专题列表
- `listByCategory(query)` — 按分类查询
- `getTopicInfo(id)` — 获取专题信息
- `addInfo(data)` — 新增专题
- `updateInfo(data)` — 更新专题
- `delInfo(id)` — 删除专题
- `docTree(query)` — 文档树
- `docInfo(id)` — 文档详情
- `addDoc(data)` — 新增文档
- `editDoc(data)` — 编辑文档
- `delDoc(id)` — 删除文档
- `resetName(data)` — 重置名称
- `reSort(data)` — 重新排序
- `getAllInfo(id)` — 获取全部信息

#### kbs/topic/user.js
- `listUser(query)` — 用户列表
- `getUser(id)` — 获取用户
- `addUser(data)` — 新增用户
- `updateUser(data)` — 更新用户
- `delUser(id)` — 删除用户

---

### 5. monitor/ — 系统监控 (7 个文件, 21 个方法)

#### monitor/cache.js
- `getCache()` — 获取缓存信息
- `listCacheName()` — 缓存名称列表
- `listCacheKey(cacheName)` — 缓存键列表
- `getCacheValue(cacheName, cacheKey)` — 获取缓存值
- `clearCacheName(cacheName)` — 清除指定名称缓存
- `clearCacheKey(cacheKey)` — 清除指定键缓存
- `clearCacheAll()` — 清除全部缓存

#### monitor/job.js
- `listJob(query)` — 任务列表
- `getJob(jobId)` — 获取任务
- `addJob(data)` — 新增任务
- `updateJob(data)` — 更新任务
- `delJob(jobId)` — 删除任务
- `changeJobStatus(jobId, status)` — 修改状态
- `runJob(jobId, jobGroup)` — 执行任务

#### monitor/jobLog.js
- `listJobLog(query)` — 任务日志列表
- `delJobLog(jobLogId)` — 删除日志
- `cleanJobLog()` — 清空日志

#### monitor/logininfor.js
- `list(query)` — 登录日志列表
- `delLogininfor(infoId)` — 删除日志
- `unlockLogininfor(userName)` — 解锁用户
- `cleanLogininfor()` — 清空日志

#### monitor/online.js
- `list(query)` — 在线用户列表
- `forceLogout(tokenId)` — 强制退出

#### monitor/operlog.js
- `list(query)` — 操作日志列表
- `delOperlog(operId)` — 删除日志
- `cleanOperlog()` — 清空日志

#### monitor/server.js
- `getServer()` — 获取服务器信息

---

### 6. mq/ — 消息队列 (1 个文件, 4 个方法)

#### mq/async.js
- `listAsync(query)` — 异步任务列表
- `getAsync(id)` — 获取详情
- `retry(id)` — 重试
- `delAsync(id)` — 删除

---

### 7. schedule/ — 日程管理 (3 个文件, 18 个方法)

#### schedule/parts.js
- `listParts(query)` — 参与人列表
- `getParts(id)` — 获取参与人
- `addParts(data)` — 新增参与人
- `updateParts(data)` — 更新参与人
- `delParts(id)` — 删除参与人

#### schedule/schedule.js
- `listSchedule(query)` — 日程列表
- `listMonth(query)` — 月视图
- `listDay(query)` — 日视图
- `listContainDate(query)` — 按日期范围查询
- `getSchedule(id)` — 获取日程
- `saveSchedule(data)` — 保存日程
- `delSchedule(id)` — 删除日程

#### schedule/type.js
- `listType(query)` — 类型列表
- `listAll()` — 全部类型
- `getType(id)` — 获取类型
- `addType(data)` — 新增类型
- `updateType(data)` — 更新类型
- `delType(id)` — 删除类型

---

### 8. serial/ — 编号管理 (2 个文件, 13 个方法)

#### serial/config.js
- `listConfig(query)` — 配置列表
- `listAllOptions()` — 全部选项
- `getConfig(id)` — 获取配置
- `addConfig(data)` — 新增配置
- `updateConfig(data)` — 更新配置
- `delConfig(id)` — 删除配置
- `changeEnableFlag(id, enableFlag)` — 启用/禁用
- `genSerialNo(confId)` — 生成编号

#### serial/log.js
- `listLog(query)` — 日志列表
- `getLog(id)` — 获取日志
- `addLog(data)` — 新增日志
- `updateLog(data)` — 更新日志
- `delLog(id)` — 删除日志

---

### 9. setting/ — 工作设置 (2 个文件, 12 个方法)

#### setting/entrust.js
- `listEntrust(query)` — 委托列表
- `getEntrust(id)` — 获取委托
- `addEntrust(data)` — 新增委托
- `updateEntrust(data)` — 更新委托
- `delEntrust(id)` — 删除委托
- `changeEnableFlag(id, enableFlag)` — 启用/禁用

#### setting/secretary.js
- `listSecretary(query)` — 秘书列表
- `getSecretary(id)` — 获取秘书
- `addSecretary(data)` — 新增秘书
- `updateSecretary(data)` — 更新秘书
- `delSecretary(id)` — 删除秘书
- `changeEnableFlag(id, enableFlag)` — 启用/禁用

---

### 10. system/ — 系统管理 (10 个文件, 68 个方法)

#### system/config.js
- `listConfig(query)` — 配置列表
- `getConfig(configId)` — 获取配置
- `getConfigKey(configKey)` — 按键获取配置
- `addConfig(data)` — 新增配置
- `updateConfig(data)` — 更新配置
- `delConfig(configId)` — 删除配置
- `refreshCache()` — 刷新缓存

#### system/dept.js
- `listDept(query)` — 部门列表
- `listDeptExcludeChild(deptId)` — 排除子部门列表
- `getDept(deptId)` — 获取部门
- `addDept(data)` — 新增部门
- `updateDept(data)` — 更新部门
- `delDept(deptId)` — 删除部门

#### system/dict/data.js
- `listData(query)` — 字典数据列表
- `getData(dictCode)` — 获取字典数据
- `getDicts(dictType)` — 按类型获取字典
- `addData(data)` — 新增
- `updateData(data)` — 更新
- `delData(dictCode)` — 删除

#### system/dict/type.js
- `listType(query)` — 字典类型列表
- `getType(dictId)` — 获取字典类型
- `addType(data)` — 新增
- `updateType(data)` — 更新
- `delType(dictId)` — 删除
- `refreshCache()` — 刷新缓存
- `optionselect()` — 选项列表

#### system/holidaySetting.js
- `listSetting(query)` — 假期设置列表
- `listHoliday(query)` — 假期列表
- `listYearHoliday(year)` — 年度假期
- `getSetting(id)` — 获取设置
- `addSetting(data)` — 新增设置
- `updateSetting(data)` — 更新设置
- `delSetting(id)` — 删除设置

#### system/holidayWorkSetting.js
- `listSetting(query)` — 调休设置列表
- `listWork(query)` — 调休列表
- `getSetting(id)` — 获取设置
- `addSetting(data)` — 新增设置
- `updateSetting(data)` — 更新设置
- `delSetting(id)` — 删除设置

#### system/menu.js
- `listMenu(query)` — 菜单列表
- `getMenu(menuId)` — 获取菜单
- `treeselect()` — 菜单树
- `roleMenuTreeselect(roleId)` — 角色菜单树
- `addMenu(data)` — 新增菜单
- `updateMenu(data)` — 更新菜单
- `delMenu(menuId)` — 删除菜单

#### system/notice.js
- `listNotice(query)` — 通知列表
- `listHomeNotice(query)` — 首页通知
- `listUserNotice(query)` — 用户通知
- `getNotice(noticeId)` — 获取通知
- `addNotice(data)` — 新增通知
- `updateNotice(data)` — 更新通知
- `delNotice(noticeId)` — 删除通知
- `changeStatus(noticeId, status)` — 修改状态
- `readNotice(noticeId)` — 标记已读

#### system/post.js
- `listPost(query)` — 岗位列表
- `getPost(postId)` — 获取岗位
- `addPost(data)` — 新增岗位
- `updatePost(data)` — 更新岗位
- `delPost(postId)` — 删除岗位

#### system/role.js
- `listRole(query)` — 角色列表
- `getRole(roleId)` — 获取角色
- `addRole(data)` — 新增角色
- `updateRole(data)` — 更新角色
- `dataScope(data)` — 数据权限
- `changeRoleStatus(roleId, status)` — 修改状态
- `delRole(roleId)` — 删除角色
- `allocatedUserList(query)` — 已分配用户列表
- `unallocatedUserList(query)` — 未分配用户列表
- `authUserCancel(data)` — 取消授权
- `authUserCancelAll(data)` — 批量取消授权
- `authUserSelectAll(data)` — 批量授权
- `deptTreeSelect(roleId)` — 部门树

#### system/user.js
- `listUser(query)` — 用户列表
- `getUser(userId)` — 获取用户
- `addUser(data)` — 新增用户
- `updateUser(data)` — 更新用户
- `delUser(userId)` — 删除用户
- `resetUserPwd(userId, password)` — 重置密码
- `changeUserStatus(userId, status)` — 修改状态
- `getUserProfile()` — 获取个人资料
- `updateUserProfile(data)` — 更新个人资料
- `updateUserPwd(oldPassword, newPassword)` — 修改密码
- `uploadAvatar(data)` — 上传头像
- `getAuthRole(userId)` — 获取授权角色
- `updateAuthRole(data)` — 更新授权角色
- `deptTreeSelect()` — 部门树
- `getUsers()` — 获取全部用户
- `corpTree(query)` — 组织树
- `listDeptUser(query)` — 部门用户列表
- `getUserDetail(userId)` — 获取用户详情

---

### 11. tool/ — 开发工具 (1 个文件, 10 个方法)

#### tool/gen.js
- `listTable(query)` — 代码生成表列表
- `listDbTable(query)` — 数据库表列表
- `getGenTable(tableId)` — 获取生成表
- `updateGenTable(data)` — 更新生成表
- `importTable(data)` — 导入表
- `createTable(data)` — 创建表
- `previewTable(tableId)` — 预览代码
- `delTable(tableId)` — 删除表
- `genCode(tableName)` — 生成代码
- `synchDb(tableName)` — 同步数据库

---

### 12. workflow/ — 工作流核心 (14 个文件, 103 个方法)

#### workflow/attachment.js
- `listAttachment(businessId)` — 附件列表
- `addAttachment(data)` — 新增附件
- `remove(id)` — 删除附件

#### workflow/comment.js
- `listComments(query)` — 评论列表
- `addComment(data)` — 新增评论
- `updateComment(data)` — 更新评论
- `delComment(id)` — 删除评论

#### workflow/done.js
- `listDone(query)` — 已办列表
- `getDone(id)` — 获取已办
- `addDone(data)` — 新增
- `updateDone(data)` — 更新
- `delDone(id)` — 删除
- `urgeAll(data)` — 批量催办

#### workflow/draft.js
- `listDraft(query)` — 草稿列表
- `getDraft(id)` — 获取草稿
- `addDraft(data)` — 新增草稿
- `updateDraft(data)` — 更新草稿
- `delDraft(id)` — 删除草稿
- `statMyDraft()` — 统计我的草稿

#### workflow/dynamicForm.js
- `listDynamicForm(query)` — 动态表单列表
- `getDynamicForm(formId)` — 获取动态表单
- `addDynamicForm(data)` — 新增
- `updateDynamicForm(data)` — 更新
- `delDynamicForm(formId)` — 删除
- `getDynaFormOptionSelect()` — 表单选项列表

#### workflow/flowable/definition.js
- `listDefinition(query)` — 流程定义列表
- `definitionStart(procDefId, data)` — 启动流程
- `updateState(params)` — 更新状态
- `userList(query)` — 用户列表
- `roleList(query)` — 角色列表
- `expList(query)` — 表达式列表
- `readXml(deployId)` — 读取XML
- `readImage(deployId)` — 读取流程图
- `saveXml(data)` — 保存XML
- `addDeployment(data)` — 新增部署
- `updateDeployment(data)` — 更新部署
- `delDeployment(procDefKey)` — 删除部署
- `exportDeployment(query)` — 导出部署
- `getFlowOptionSelect()` — 流程选项列表
- `updateRouteXml(data)` — 更新路由XML

#### workflow/flowable/expression.js
- `listExpression(query)` — 表达式列表
- `getExpression(id)` — 获取表达式
- `addExpression(data)` — 新增
- `updateExpression(data)` — 更新
- `delExpression(id)` — 删除

#### workflow/flowable/listener.js
- `listListener(query)` — 监听器列表
- `getListener(id)` — 获取监听器
- `addListener(data)` — 新增
- `updateListener(data)` — 更新
- `delListener(id)` — 删除

#### workflow/flowable/monitor.js
- `getListProcess(query)` — 运行中流程列表
- `enableProcess(processInstanceId, enable)` — 挂起/激活
- `getListHistoryProcess(query)` — 历史流程列表
- `getListByTypeAndId(type, processInstanceId)` — 按类型和ID查询
- `getJumpActivityList(query)` — 跳转活动列表
- `getJumpActivityNode(query)` — 跳转活动节点
- `getFlowNodeTasks(query)` — 流程节点任务
- `getFinishFlowNodeTasks(query)` — 已完成节点任务

#### workflow/form.js
- `getForm(params)` — 获取表单
- `addForm(data)` — 新增表单
- `updateForm(data)` — 更新表单
- `getButtons(params)` — 获取按钮
- `preview(params)` — 预览表单

#### workflow/mainSeal.js
- `listSeal(query)` — 印章列表
- `findAllSeals()` — 全部印章
- `getSeal(id)` — 获取印章
- `previewSeal(data)` — 预览印章
- `changeEnableFlag(id, enableFlag)` — 启用/禁用
- `createSeal(data)` — 创建印章
- `updateSeal(data)` — 更新印章
- `delSeal(id)` — 删除印章

#### workflow/mainText.js
- `getMainInfo(param)` — 获取正文信息
- `uploadMainText(data)` — 上传正文
- `remove(business)` — 删除正文
- `stamp(data)` — 盖章
- `restoreSeal(businessId)` — 恢复印章

#### workflow/process.js
- `startFlow(data)` — 发起流程
- `commonSubmit(data)` — 通用提交
- `jumpActivity(data)` — 跳转活动
- `terminateProcess(data)` — 终止流程
- `returnFinishTask(data)` — 退回已完成任务
- `checkCompleteCondition(data)` — 检查完成条件
- `checkReturnCondition(data)` — 检查退回条件
- `checkRejectCondition(data)` — 检查驳回条件
- `getDeleteMultiTasks(query)` — 获取可删除多实例任务
- `deleteMultiTask(data)` — 删除多实例任务

#### workflow/receTemplate.js
- `getReceTemplateList()` — 收文模板列表
- `addReceTemplate(data)` — 新增收文模板

#### workflow/recycle.js
- `listRecycle(query)` — 回收站列表
- `getRecycle(id)` — 获取详情
- `addRecycle(data)` — 新增
- `updateRecycle(data)` — 更新
- `delRecycle(id)` — 删除

#### workflow/task.js
- `complete(data)` — 完成任务
- `delegateTask(data)` — 委派任务
- `assignTask(data)` — 转办任务
- `claimTask(data)` — 签收任务
- `unClaimTask(data)` — 取消签收
- `returnTask(data)` — 退回任务
- `rejectTask(data)` — 驳回任务
- `addMultiTask(data)` — 加签
- `deleteMultiInstanceExecution(data)` — 减签
- `copyTask(data)` — 抄送
- `returnList(data)` — 可退回列表
- `getNextFlowNode(data)` — 获取下一节点
- `flowTaskForm(query)` — 任务表单
- `flowRecord(data)` — 流转记录
- `flowCmts(data)` — 流转评论
- `revokeProcess(data)` — 撤回流程
- `finishProcess(data)` — 结束流程
- `urge(data)` — 催办
- `getTask(query)` — 获取任务
- `getHistoryTask(query)` — 获取历史任务
- `getProcessVariables(taskId)` — 获取流程变量
- `getFlowViewer(procInsId, executionId)` — 流程查看器
- `flowXmlAndNode(query)` — 流程XML和节点
- `returnSubmit(data)` — 退回提交

#### workflow/template.js
- `listTemplate(query)` — 模板列表
- `getTemplate(id)` — 获取模板
- `addTemplate(data)` — 新增模板
- `updateTemplate(data)` — 更新模板
- `delTemplate(id)` — 删除模板
- `changeEnable(data)` — 启用/禁用
- `getNewStartTemplateList()` — 新发起模板列表
- `getSelectTemplateList()` — 选择模板列表
- `listType(query)` — 模板类型列表
- `listEnable()` — 启用列表
- `getType(id)` — 获取类型
- `addType(data)` — 新增类型
- `updateType(data)` — 更新类型
- `delType(id)` — 删除类型
- `updateEnable(data)` — 更新启用状态

#### workflow/todo.js
- `listTodoTable(query)` — 待办表格列表
- `listTodoCollapse(query)` — 待办折叠列表
- `getTodo(id)` — 获取待办
- `addTodo(data)` — 新增待办
- `updateTodo(data)` — 更新待办
- `delTodo(id)` — 删除待办
- `readTodo(id)` — 标记已读
- `noRead()` — 未读数量
- `readCopyTodo(id)` — 标记抄送已读
- `stat(type)` — 统计

---

## 按功能分类汇总

| 模块 | 文件数 | 方法数 | 说明 |
|------|--------|--------|------|
| 根目录 | 2 | 6 | 登录认证、路由 |
| file | 2 | 6 | 文件上传/下载/操作 |
| information | 1 | 9 | 新闻公告 |
| kbs | 8 | 39 | 知识库(文档/收藏/回收/专题) |
| monitor | 7 | 21 | 缓存/定时任务/日志/在线用户/服务器 |
| mq | 1 | 4 | 异步消息队列 |
| schedule | 3 | 18 | 日程管理 |
| serial | 2 | 13 | 编号管理 |
| setting | 2 | 12 | 委托/秘书设置 |
| system | 10 | 68 | 用户/角色/菜单/部门/岗位/配置/字典/通知/假期 |
| tool | 1 | 10 | 代码生成器 |
| workflow | 14 | 103 | 工作流(流程/任务/表单/印章/模板/待办) |
| **合计** | **59** | **321** | |
