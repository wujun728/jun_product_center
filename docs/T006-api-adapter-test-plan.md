# T006 API适配层测试计划

## 测试目标
验证新前端的API适配层与后端ruoyi-vue-oa完全兼容。

## 已完成的修改

### 1. 超时时间调整
- **文件**: `yudao-ui-admin-vue3/src/config/axios/config.ts`
- **修改**: `request_timeout: 30000` → `request_timeout: 60000`
- **说明**: 与原前端保持一致，设置为60秒

### 2. 防重复提交逻辑
- **文件**: `yudao-ui-admin-vue3/src/config/axios/service.ts`
- **新增**: 在request拦截器中添加防重复提交逻辑
- **功能**:
  - 检查POST/PUT请求是否在1秒内重复提交相同数据
  - 使用sessionStorage存储最近的请求信息
  - 请求数据大小超过5M时跳过检查
  - 可通过`headers.repeatSubmit = false`禁用

### 3. 601警告码支持
- **文件**: `yudao-ui-admin-vue3/src/config/axios/service.ts`
- **新增**: 在response拦截器中添加601状态码处理
- **功能**:
  - 使用`ElMessage.warning(msg)`显示警告信息
  - 返回`Promise.reject('error')`

### 4. 单元测试
- **文件**: `yudao-ui-admin-vue3/src/config/axios/service.test.ts`
- **覆盖场景**:
  - 响应状态码处理（200、401、500、601）
  - Token管理（localStorage存储、Authorization header格式）
  - 防重复提交（重复检测、时间间隔、不同数据）
  - 请求超时配置
  - 错误处理（网络错误、超时）

## 手动测试清单

### 测试1: 登录接口测试

**前置条件**: 后端服务已启动

**测试步骤**:
1. 启动新前端: `pnpm dev`
2. 访问登录页面
3. 输入用户名和密码
4. 点击登录按钮

**验证点**:
- [ ] 登录请求发送成功
- [ ] Token存储到localStorage（key: ACCESS_TOKEN）
- [ ] 请求头包含`Authorization: Bearer {token}`
- [ ] 登录成功后跳转到首页

**预期结果**:
```json
Request Headers:
{
  "Content-Type": "application/json",
  "Authorization": "Bearer xxx"
}

Response:
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "access_token": "xxx",
    "refresh_token": "xxx",
    ...
  }
}
```

---

### 测试2: 401未认证处理

**测试步骤**:
1. 清空localStorage中的token
2. 访问需要认证的页面（如用户管理）

**验证点**:
- [ ] 自动触发Token刷新机制
- [ ] 刷新失败后弹出重新登录提示
- [ ] 点击确认后跳转到登录页

---

### 测试3: 500服务器错误

**测试步骤**:
1. 登录系统
2. 触发一个后端返回500错误的操作

**验证点**:
- [ ] 显示错误消息：`ElMessage.error`
- [ ] 错误消息内容正确
- [ ] 返回`Promise.reject`

---

### 测试4: 601警告提示

**测试步骤**:
1. 登录系统
2. 触发一个后端返回601警告码的操作

**验证点**:
- [ ] 显示警告消息：`ElMessage.warning`
- [ ] 警告消息内容正确
- [ ] 返回`Promise.reject('error')`

---

### 测试5: 防重复提交

**测试步骤**:
1. 登录系统
2. 访问用户管理页面
3. 点击"新增用户"
4. 填写表单数据
5. 快速连续点击"提交"按钮两次（间隔<1秒）

**验证点**:
- [ ] 第一次提交正常发送
- [ ] 第二次提交被拦截
- [ ] 控制台输出警告：`数据正在处理，请勿重复提交`
- [ ] 页面显示错误提示

---

### 测试6: 请求超时

**测试步骤**:
1. 登录系统
2. 修改后端使某个接口延迟65秒返回
3. 调用该接口

**验证点**:
- [ ] 60秒后请求超时
- [ ] 显示超时错误消息
- [ ] 错误消息包含"timeout"关键字

---

### 测试7: Token自动刷新

**测试步骤**:
1. 登录系统
2. 等待Token接近过期（或手动修改Token过期时间）
3. 发起一个需要认证的请求

**验证点**:
- [ ] 检测到401后自动调用刷新Token接口
- [ ] 刷新成功后重新发起原请求
- [ ] 新Token存储到localStorage
- [ ] 原请求返回正确数据

---

## 自动化测试执行

### 运行单元测试（如果vitest已配置）
```bash
cd yudao-ui-admin-vue3
pnpm test
# 或
pnpm vitest
```

### 测试覆盖率
```bash
pnpm test --coverage
```

---

## 测试结果记录

### 编译测试
- **执行时间**: 2026-04-13
- **测试命令**: `pnpm build:prod`
- **结果**: ✅ 通过
- **输出**: "Build successful. Please see dist-prod directory"

### 单元测试
- **执行时间**: 待执行
- **测试框架**: Vitest + axios-mock-adapter
- **结果**: 待验证
- **覆盖率**: 待统计

### 手动测试
- **测试1 - 登录接口**: 待测试
- **测试2 - 401处理**: 待测试
- **测试3 - 500错误**: 待测试
- **测试4 - 601警告**: 待测试
- **测试5 - 防重复提交**: 待测试
- **测试6 - 请求超时**: 待测试
- **测试7 - Token刷新**: 待测试

---

## 兼容性对比

| 特性 | 原前端 | 新前端（修改前） | 新前端（修改后） | 状态 |
|------|--------|----------------|----------------|------|
| 超时时间 | 60秒 | 30秒 | 60秒 | ✅ |
| Token格式 | Bearer {token} | Bearer {token} | Bearer {token} | ✅ |
| Token存储 | Cookie | localStorage | localStorage | ✅ |
| 200成功码 | 支持 | 支持 | 支持 | ✅ |
| 401未认证 | 弹窗重登 | 自动刷新 | 自动刷新 | ✅ |
| 500错误 | Message.error | ElMessage.error | ElMessage.error | ✅ |
| 601警告 | Message.warning | ❌ | ElMessage.warning | ✅ |
| 防重复提交 | ✅ | ❌ | ✅ | ✅ |
| Token刷新 | ❌ | ✅ | ✅ | ✅ |

---

## 注意事项

1. **Token刷新机制**: 新前端保留了自动刷新Token的功能，这是相对原前端的增强特性
2. **防重复提交**: 可通过请求配置`headers.repeatSubmit = false`禁用
3. **租户支持**: 新前端支持租户功能，通过`tenant-id` header传递
4. **API加密**: 新前端支持API加密，通过`headers.isEncrypt`启用

---

## 下一步

1. 等待后端服务启动后进行完整的前后端联调测试
2. 记录所有测试场景的实际结果
3. 修复发现的任何兼容性问题
4. 更新测试文档
5. 完成T006任务验收
