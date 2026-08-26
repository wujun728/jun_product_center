# API适配层设计文档

## 1. 概述

本文档说明如何设计统一的API适配层，使新前端（yudao-ui-admin-vue3）能够与后端（ruoyi-vue-oa）完全兼容，同时保留原前端（ruoyi-vue-oa-ui）的功能特性。

**设计目标：**
- 确保新前端与后端API完全兼容
- 统一响应码处理逻辑
- 统一token存储和传递方式
- 保持防重复提交等安全特性
- 支持文件上传下载等特殊场景

---

## 2. 对比分析

### 2.1 原前端 vs 新前端

| 特性 | 原前端（ruoyi-vue-oa-ui） | 新前端（yudao-ui-admin-vue3） |
|------|--------------------------|------------------------------|
| **文件位置** | `src/utils/request.js` | `src/config/axios/service.ts` |
| **框架** | Vue 2 + JS | Vue 3 + TS + Vite |
| **HTTP库** | Axios | Axios |
| **Token存储** | Cookie (`js-cookie`) | localStorage/sessionStorage (`wsCache`) |
| **Token格式** | `Bearer {token}` | `Bearer {accessToken}` |
| **超时时间** | 60000ms (60秒) | 30000ms (30秒) |
| **成功状态码** | 200 | 200 |
| **认证失败码** | 401 | 401 |
| **服务器错误码** | 500 | 500 |
| **警告码** | 601 | - |
| **租户支持** | 无 | 有（tenant-id header） |
| **刷新Token** | 无 | 有（自动刷新机制） |
| **防重复提交** | 有（sessionStorage缓存） | 无 |
| **API加密** | 无 | 有（可选加密） |
| **参数序列化** | 自定义tansParams | qs.stringify |

---

## 3. 响应码映射规则

### 3.1 标准HTTP状态码

后端 ruoyi-vue-oa 使用的响应格式：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {...}
}
```

### 3.2 状态码说明

| 状态码 | 含义 | 原前端处理 | 新前端处理 | 统一处理方案 |
|--------|------|-----------|-----------|-------------|
| **200** | 成功 | 直接返回data | 直接返回data | ✅ 无需适配 |
| **401** | 未认证/Token过期 | 弹窗提示重新登录 | 自动刷新Token，失败后提示登录 | ✅ 使用新前端刷新机制 |
| **403** | 无权限 | Notification.error | ElNotification.error | ✅ 统一使用ElNotification |
| **404** | 资源不存在 | Notification.error | ElNotification.error | ✅ 统一使用ElNotification |
| **500** | 服务器错误 | Message.error | ElMessage.error | ✅ 统一使用ElMessage |
| **601** | 警告 | Message.warning | 不支持 | ⚠️ 需要添加支持 |
| **901** | 演示模式 | - | ElMessage.error（特殊提示） | ℹ️ 仅新前端有 |

### 3.3 错误消息映射

**原前端 errorCode.js：**
```javascript
export default {
  '401': '认证失败，无法访问系统资源',
  '403': '当前操作没有权限',
  '404': '访问资源不存在',
  'default': '系统未知错误，请反馈给管理员'
}
```

**新前端 errorCode.ts：**
```typescript
export default {
  '401': '认证失败，无法访问系统资源',
  '403': '当前操作没有权限',
  '404': '访问资源不存在',
  default: '系统未知错误，请反馈给管理员'
}
```

✅ **结论：错误消息完全一致，无需适配**

---

## 4. Token存储和传递方式

### 4.1 Token存储对比

| 特性 | 原前端 | 新前端 | 推荐方案 |
|------|--------|--------|---------|
| **存储方式** | Cookie | localStorage/sessionStorage | localStorage（持久化） |
| **存储Key** | `Admin-Token` | `ACCESS_TOKEN` / `REFRESH_TOKEN` | 使用新前端的双Token |
| **存储库** | `js-cookie` | 自定义`wsCache` | 使用wsCache |
| **安全性** | Cookie自动携带 | 需手动添加Header | 手动控制更安全 |

### 4.2 Token传递机制

**原前端（request.js）：**
```javascript
if (getToken() && !isToken) {
  config.headers['Authorization'] = 'Bearer ' + getToken()
}
```

**新前端（service.ts）：**
```typescript
if (getAccessToken() && !isToken) {
  config.headers.Authorization = 'Bearer ' + getAccessToken()
}
```

✅ **结论：传递格式完全一致，都是 `Bearer {token}`**

### 4.3 Token刷新机制

**关键差异：**
- 原前端：无自动刷新，401直接提示重新登录
- 新前端：支持RefreshToken自动刷新AccessToken

**适配建议：**
1. **保留新前端的自动刷新机制**（更好的用户体验）
2. **后端需要支持** `/system/auth/refresh-token` 接口
3. 如果后端不支持，需要降级为原前端的直接登出逻辑

---

## 5. 适配层设计方案

### 5.1 适配层架构

```
┌─────────────────────────────────────────┐
│         业务组件（Vue Components）        │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│      API调用层（@/api/*.ts）             │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│   API适配层（adapter/request.ts）        │
│  ┌──────────────────────────────────┐   │
│  │ - 请求拦截（添加Token、租户等）    │   │
│  │ - 响应拦截（状态码映射、错误处理） │   │
│  │ - 防重复提交                      │   │
│  │ - Token刷新                       │   │
│  └──────────────────────────────────┘   │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│      Axios实例（service）                │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│    后端API（ruoyi-vue-oa）               │
└─────────────────────────────────────────┘
```

### 5.2 核心适配点

#### 5.2.1 请求拦截器适配

**需要保留的原前端特性：**
1. ✅ Token添加（已支持）
2. ✅ GET请求参数序列化（已支持qs.stringify）
3. ⚠️ **防重复提交机制**（新前端缺失，需添加）

**需要保留的新前端特性：**
1. ✅ 租户ID支持（tenant-id header）
2. ✅ API加密支持（可选）
3. ✅ Cache-Control防缓存（GET请求）

#### 5.2.2 响应拦截器适配

**需要添加的支持：**
1. ⚠️ **601警告码处理**（原前端有，新前端无）
   ```typescript
   else if (code === 601) {
     ElMessage({ message: msg, type: 'warning' })
     return Promise.reject('warning')
   }
   ```

2. ✅ Token自动刷新（已有，需验证后端支持）

3. ✅ Blob响应处理（文件下载，已支持）

#### 5.2.3 防重复提交适配

**原前端实现逻辑：**
```javascript
// 1. 检查请求头是否禁用防重复提交
const isRepeatSubmit = (config.headers || {}).repeatSubmit === false

// 2. 对POST/PUT请求进行防重复检查
if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
  // 3. 计算请求数据大小，超过5M则跳过
  const requestSize = Object.keys(JSON.stringify(requestObj)).length
  const limitSize = 5 * 1024 * 1024

  // 4. 使用sessionStorage缓存请求信息
  const sessionObj = cache.session.getJSON('sessionObj')

  // 5. 检查1秒内是否有相同URL和数据的请求
  const interval = 1000
  if (s_data === requestObj.data &&
      requestObj.time - s_time < interval &&
      s_url === requestObj.url) {
    return Promise.reject(new Error('数据正在处理，请勿重复提交'))
  }
}
```

**适配方案：**
在新前端的 `service.ts` 请求拦截器中添加防重复提交逻辑。

---

## 6. 配置项对比

### 6.1 Axios配置

| 配置项 | 原前端 | 新前端 | 推荐值 |
|--------|--------|--------|--------|
| **baseURL** | `process.env.VUE_APP_BASE_API` | `VITE_BASE_URL + VITE_API_URL` | `/dev-api` (dev) |
| **timeout** | 60000 | 30000 | 60000（文件上传需要更长时间） |
| **withCredentials** | 默认false | false | false |
| **Content-Type** | `application/json;charset=utf-8` | `application/json` | `application/json` |

### 6.2 环境变量

**原前端 .env.development：**
```env
VUE_APP_BASE_API = '/dev-api'
```

**新前端 .env.base：**
```env
VITE_BASE_URL = http://localhost:8080
VITE_API_URL = /admin-api
```

**适配建议：**
- 开发环境使用 `/dev-api` 代理到后端
- 生产环境使用完整URL或相对路径

---

## 7. 特殊功能适配

### 7.1 文件下载

**原前端（request.js）：**
```javascript
export function download(url, params, filename, config) {
  return service.post(url, params, {
    transformRequest: [(params) => { return tansParams(params) }],
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    responseType: 'blob',
    ...config
  })
}
```

**新前端：**
- 有独立的 `download.ts` 工具
- 响应拦截器已支持blob类型

**适配建议：**
在新前端添加 `download()` 导出函数，保持API一致性。

### 7.2 文件上传

**后端要求：**
- 支持multipart/form-data
- 大文件上传需要分片（原前端有配置）

**适配建议：**
使用新前端现有的上传组件，确保Content-Type正确设置。

---

## 8. 实施步骤

### 8.1 短期适配（必须）

1. **修改 `src/config/axios/service.ts`**
   - [ ] 添加 601 警告码处理
   - [ ] 添加防重复提交逻辑
   - [ ] 调整timeout为60000

2. **修改 `src/config/axios/config.ts`**
   - [ ] 确认base_url配置正确

3. **验证Token机制**
   - [ ] 确认后端是否支持refresh-token接口
   - [ ] 如不支持，禁用自动刷新逻辑

4. **添加下载函数**
   - [ ] 在service.ts导出download函数
   - [ ] 兼容原前端的调用方式

### 8.2 中期优化（建议）

1. **统一错误提示**
   - 使用Element Plus的ElMessage/ElNotification
   - 国际化支持（已有i18n）

2. **API加密**
   - 根据业务需求决定是否启用
   - 后端需要配合解密

3. **租户支持**
   - 如果系统不需要多租户，可移除相关逻辑
   - 如果需要，确保后端支持tenant-id header

### 8.3 长期改进（可选）

1. **接口Mock**
   - 添加Mock数据支持开发
   - 使用msw或vite-plugin-mock

2. **请求日志**
   - 开发环境打印详细日志
   - 生产环境只记录错误

3. **性能优化**
   - 请求去重
   - 请求取消（AbortController）
   - 请求重试机制

---

## 9. 风险评估

| 风险项 | 影响 | 缓解措施 |
|--------|------|---------|
| **后端不支持refresh-token** | 高 | 降级为直接登出，或改造后端 |
| **防重复提交逻辑差异** | 中 | 在新前端实现相同逻辑 |
| **租户功能冲突** | 低 | 确认后端是否使用tenant-id |
| **超时时间过短** | 中 | 调整为60秒，支持大文件上传 |
| **601状态码不支持** | 低 | 添加处理逻辑即可 |

---

## 10. 测试清单

### 10.1 功能测试

- [ ] 登录/登出流程
- [ ] Token自动刷新（如果后端支持）
- [ ] 401状态码跳转登录
- [ ] 500错误提示
- [ ] 601警告提示
- [ ] 防重复提交（连续点击提交按钮）
- [ ] 文件上传
- [ ] 文件下载
- [ ] 大数据量请求（超时测试）

### 10.2 兼容性测试

- [ ] 与原前端API调用方式一致
- [ ] 响应数据格式一致
- [ ] 错误处理一致

### 10.3 性能测试

- [ ] 并发请求处理
- [ ] 大文件上传/下载
- [ ] Token刷新不阻塞业务请求

---

## 11. 结论

### 11.1 核心兼容性

✅ **高度兼容：**
- Token传递格式完全一致（Bearer Token）
- 响应码定义一致（200/401/500等）
- 错误消息映射一致

⚠️ **需要适配：**
- 添加601警告码支持
- 添加防重复提交机制
- 确认refresh-token后端支持

ℹ️ **新增特性：**
- Token自动刷新（提升用户体验）
- 租户支持（如需要）
- API加密（可选）

### 11.2 推荐方案

**采用新前端的axios配置为基础，增强以下功能：**

1. 保留新前端的Token自动刷新机制
2. 添加原前端的防重复提交功能
3. 添加601警告码处理
4. 调整超时时间为60秒
5. 导出download函数保持API一致

### 11.3 后续工作

1. 按照实施步骤修改代码
2. 完成测试清单的所有测试项
3. 与后端团队确认refresh-token接口是否可用
4. 编写迁移指南，指导业务模块API调用方式

---

**文档版本：** v1.0
**创建日期：** 2026-04-13
**最后更新：** 2026-04-13
**维护人员：** Claude Code Agent
