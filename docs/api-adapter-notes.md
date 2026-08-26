# API 适配层说明文档

> T006 - 确认新前端 API 适配层
> 生成日期: 2026-04-03

## 1. 新前端的 API 请求方式

新前端 (jun_ui_sa_admin) 有**三层** API 请求机制：

### 1.1 底层: `sa.ajax()` (sa.js)

Sa-Admin 框架原生的请求封装，基于 **jQuery $.ajax**。

```javascript
sa.ajax('/api/endpoint', {key: 'value'}, function(res) {
    // res = {code: 200, msg: 'ok', data: [...]}
    console.log(res.data);
}, {type: 'get', msg: '加载中...'});
```

**特点:**
- 依赖 jQuery + layer.js
- 回调函数风格（非 Promise）
- 自动根据 `res.code` 分发到 `success200/success500/success401/success403` 处理器
- 自动显示/隐藏 loading（layer.msg）
- 默认请求类型为 POST
- baseURL 取自 `sa.cfg.api_url`（由 config.js 注入）
- **不自动携带 JWT Token**

### 1.2 中间层: `ruoyi.*` (ruoyi-util.js)

专为对接 RuoYi 后端而封装，也基于 **jQuery $.ajax**，但增加了 RuoYi 业务逻辑。

```javascript
ruoyi.get('/system/user/list', {pageNum: 1}, function(res) { ... });
ruoyi.post('/system/user', userData, function(res) { ... });
ruoyi.put('/system/user', userData, function(res) { ... });
ruoyi.del('/system/user/' + id, function(res) { ... });
```

**特点:**
- 回调函数风格（非 Promise）
- **自动携带 JWT Token**（从 Cookie 读取 `Admin-Token`，设置 `Authorization: Bearer xxx`）
- 自动处理 401/500/601 响应码（与原前端逻辑一致）
- Content-Type 默认 `application/json;charset=utf-8`
- POST/PUT 自动 `JSON.stringify(data)`
- GET/DELETE 参数自动拼接到 URL
- baseURL 由 CONFIG.API_BASE_URL 或默认 `/prod-api` 决定
- 提供 `ruoyi.download()` 和 `ruoyi.downloadGet()` 文件下载方法

### 1.3 桥接层: `api-adapter.js`

为兼容原前端的 `import request from '@/utils/request'` + `import { listUser } from '@/api/system/user'` 模式而设计的虚拟模块系统。

```javascript
// 定义模块（替代 ES6 export）
defineModule('@/api/system/user', {
    listUser: function(query) { return ruoyi.get('/system/user/list', query); },
    getUser: function(userId) { return ruoyi.get('/system/user/' + userId); }
});

// 使用模块（替代 ES6 import）
var { listUser, getUser } = importModule('@/api/system/user');
```

**特点:**
- 提供 `window.request()` 全局函数，接受 axios 风格的配置对象 `{url, method, params, data}`
- 返回 **Promise**（将 `ruoyi.*` 的回调包装为 Promise）
- 注册了 `@/utils/request` 虚拟模块，使原前端的 `request()` 调用可工作
- `window.parseStrEmpty()` 全局函数兼容原前端工具

## 2. 原前端的 API 调用方式

原前端 (ruoyi-vue-oa-ui) 使用标准的 Vue SPA 模式：

```javascript
// @/utils/request.js — 基于 axios
import axios from 'axios'
const service = axios.create({ baseURL: process.env.VUE_APP_BASE_API, timeout: 60000 })
// 拦截器自动添加 Token、处理响应码
export default service

// @/api/system/user.js — API 模块
import request from '@/utils/request'
export function listUser(query) {
    return request({ url: '/system/user/list', method: 'get', params: query })
}

// 组件中使用
import { listUser } from '@/api/system/user'
listUser(this.queryParams).then(res => { this.userList = res.rows })
```

**特点:**
- 基于 **axios**（Promise 风格）
- Token 从 Vuex store 经 `getToken()` 获取，存储在 Cookie
- 拦截器处理 401/500/601
- GET 请求的 params 自动通过 `tansParams()` 拼接到 URL
- POST/PUT 有防重复提交逻辑（基于 sessionStorage）
- 提供 `download()` 导出函数处理 blob 下载

## 3. 关键差异对比

| 维度 | 原前端 (axios) | 新前端 (ruoyi-util.js) |
|------|---------------|----------------------|
| HTTP 库 | axios | jQuery $.ajax |
| 调用风格 | Promise (`.then()`) | 回调函数 |
| Token 存储 | Cookie (js-cookie) | Cookie (原生 document.cookie) |
| Token Header | `Authorization: Bearer xxx` | 相同 |
| baseURL | `process.env.VUE_APP_BASE_API` | `CONFIG.API_BASE_URL` |
| 响应码处理 | axios 拦截器 | `ruoyi.request()` 内部 switch |
| 超时设置 | 60s | 无（jQuery 默认） |
| 防重复提交 | sessionStorage 拦截 | 无 |
| Content-Type | `application/json;charset=utf-8` | 相同 |
| 错误提示 | Element UI (Message/Notification) | Element UI (ELEMENT.Message) |
| 模块导入 | ES6 `import/export` | `defineModule/importModule` |

## 4. 从原前端迁移 API 调用时需要做的改动

### 4.1 方式一: 使用 api-adapter.js 桥接（推荐，改动最小）

对于 httpVueLoader 加载的 .vue 文件，将 ES6 import 替换为 `importModule`：

```javascript
// 原前端写法
import { listUser, getUser } from '@/api/system/user'

// 新前端写法
var { listUser, getUser } = importModule('@/api/system/user');
```

**前提:** 需要先用 `defineModule` 注册对应的 API 模块。每个原前端的 API 文件需要转写一份注册代码。

**注意:** `api-adapter.js` 中的 `window.request()` 返回 Promise，但内部调用的 `ruoyi.get/post/put/delete` 是回调风格。**当前 api-adapter.js 的 request() 实现有问题**：`ruoyi.get()` 等方法不返回 Promise，而是执行 jQuery $.ajax 并通过回调处理结果。`api-adapter.js` 试图对其返回值调用 `.then()`，这会失败。

**修复方案:** 需要修改 `api-adapter.js` 中的 `window.request()`，改用 `ruoyi.request()` 的 options 风格，在 success/error 回调中 resolve/reject Promise。

### 4.2 方式二: 直接使用 ruoyi.* 方法（改动较大但更可靠）

在 .vue 文件中直接调用 `ruoyi.get/post/put/del`：

```javascript
// 原前端写法
listUser(this.queryParams).then(res => { this.userList = res.rows })

// 新前端写法（回调风格）
ruoyi.get('/system/user/list', this.queryParams, function(res) {
    this.userList = res.rows;
}.bind(this));
```

**改动点:**
- 移除所有 `import` 语句
- 将 `xxxApi(params).then(callback)` 改为 `ruoyi.method(url, params, callback)`
- 注意 `this` 绑定，回调函数中需要 `.bind(this)` 或使用闭包
- DELETE 方法名不同：原前端 `delUser(id)` → 新前端 `ruoyi.del(url)`

### 4.3 方式三: 使用 sa.ajax（仅适合新写的简单页面）

```javascript
sa.ajax('/system/user/list', this.queryParams, function(res) {
    this.userList = res.rows;
}.bind(this), {type: 'get'});
```

**不推荐用于迁移**，因为 `sa.ajax` 不自动携带 JWT Token，需要手动在 `beforeSend` 中添加。

### 4.4 通用改动清单

无论选择哪种方式，以下改动都需要做：

| 改动项 | 说明 |
|--------|------|
| 移除 ES6 import | httpVueLoader 不支持 `import` 语法，改用 `importModule()` 或直接调用 |
| `export default` → `module.exports` | .vue 文件的 script 导出方式 |
| Vuex store 访问 | `this.$store.getters.xxx` 不可用，改用 `ruoyi.hasPermi()` / `ruoyi.getToken()` |
| `this.$modal.xxx` | 已在 ruoyi-util.js 中注册到 Vue.prototype，可直接使用 |
| `this.$download` | 改用 `ruoyi.download(url, params, filename)` |
| `this.resetForm(refName)` | 已在 ruoyi-util.js 中挂载到 Vue.prototype，可直接使用 |
| `parseTime` / `handleTree` | 已在 ruoyi-util.js 中挂载到 Vue.prototype，可直接使用 |
| `v-hasPermi` / `v-hasRole` | 已在 ruoyi-util.js 中全局注册 Vue 指令，可直接使用 |
| `this.getDicts(type)` | 改用 `ruoyi.getDicts(type, callback)` 或 `ruoyi.loadDicts(vm, types)` |
| `addDateRange` | 已在 ruoyi-util.js 中挂载，`this.addDateRange()` 可直接使用 |
| `selectDictLabel` | 已在 ruoyi-util.js 中挂载，`this.selectDictLabel()` 可直接使用 |

### 4.5 需要修复的已知问题

1. **api-adapter.js 的 Promise 包装有 bug**: `ruoyi.get/post/put/delete` 返回的是 jQuery $.ajax 对象（非标准 Promise），`apiCall.then()` 虽然 jQuery Deferred 支持 `.then()`，但语义与原生 Promise 不完全一致。建议统一改用 `ruoyi.request()` + `new Promise()` 包装。

2. **sa.cfg.api_url 与 CONFIG.API_BASE_URL 可能不一致**: `sa.js` 和 `ruoyi-util.js` 各自独立读取配置，需确保 `config.js` 正确设置两者一致。

3. **防重复提交缺失**: 原前端有基于 sessionStorage 的防重复提交机制，新前端没有，高频操作场景需注意。

## 5. 推荐迁移策略

1. **优先选择方式一**（api-adapter.js 桥接），改动最小
2. 修复 `api-adapter.js` 中 `window.request()` 的 Promise 包装问题
3. 为每个需要迁移的 API 模块编写 `defineModule` 注册文件
4. .vue 文件中将 `import` 改为 `importModule`，其余代码（`.then()` 链）基本不变
5. 逐步验证每个模块的 API 调用是否正常
