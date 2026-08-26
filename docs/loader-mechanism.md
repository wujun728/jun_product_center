# httpVueLoader 加载机制说明文档

> 任务 T005 | 2026-04-03

## 1. 概述

新前端 `jun_ui_sa_admin` 基于 Sa-Admin 框架，采用 **httpVueLoader** 在浏览器运行时动态加载 `.vue` 文件，无需 webpack/vite 等构建工具。核心由三个文件协同完成：

| 文件 | 职责 |
|------|------|
| `static/libs/http-vue-loader/httpVueLoader.js` | 底层加载器：通过 XHR 获取 `.vue` 文件，解析 `<template>/<script>/<style>` 三段式结构 |
| `sa-frame/index/vue-loader-cache.js` | 缓存增强层：内存缓存 + import 语句预处理 |
| `sa-frame/index/index.js` | 框架主体：Tab 管理、自动检测 `.vue` 后缀触发组件加载 |
| `sa-frame/sa-code.js` | 配置入口：认证、用户信息、后端菜单动态加载 |

## 2. .vue 文件加载流程

### 2.1 加载触发

当用户点击菜单或调用 `sa_admin.showTab(tab)` 时，框架在 `addTab()` 方法中自动检测 URL 后缀：

```javascript
// index.js → addTab()
if (tab.view === undefined) {
    if (this.getUrlExt(tab.url).toLowerCase() == 'vue') {
        tab.view = httpVueLoaderCached(tab.url);  // .vue 文件 → 组件模式
    }
}
```

- URL 以 `.vue` 结尾 → 设置 `tab.view` 为组件对象，使用 `<component :is>` 渲染（**内嵌在父 Vue 实例中**）
- URL 以 `.html` 结尾 → `tab.view` 为空，使用 `<iframe>` 渲染（**独立隔离**）

### 2.2 渲染方式（nav-view-vessel.vue）

```html
<!-- .vue 组件模式：共享父 Vue 实例的所有依赖 -->
<component :ref="tab.id" :is="tab.view" v-if="tab.isNeedLoad"></component>

<!-- .html iframe 模式：完全隔离，需自行引入依赖 -->
<iframe :src="tab.url" v-if="tab.isNeedLoad"></iframe>
```

### 2.3 完整加载链路

```
用户点击菜单
  → sa_admin.showTab(tab)
    → addTab() 检测 .vue 后缀
      → httpVueLoaderCached(url)
        → [缓存命中?] → 直接返回缓存组件（<1ms）
        → [缓存未命中] → fetch(url) 获取 .vue 文件文本
          → preprocessImports() 转换 ES6 import 为兼容代码
          → 创建 Blob URL
          → httpVueLoader(blobUrl) 解析 template/script/style
          → 缓存结果到 componentCache
          → 返回 Vue 组件对象
    → tab 加入 tabList / viewList
    → nav-view-vessel 通过 <component :is> 渲染
```

## 3. 缓存策略

### 3.1 缓存实现（vue-loader-cache.js）

缓存层 `httpVueLoaderCached` 封装了原始 `httpVueLoader`，使用 **内存对象** 存储已加载的组件：

```javascript
var componentCache = {};  // 以 URL 为 key，组件对象为 value

// 缓存键格式: "url" 或 "url:name"
var cacheKey = url + (name ? ':' + name : '');
```

**缓存行为：**
- **首次加载**：网络请求 → 预处理 → 解析 → 存入 `componentCache`
- **二次加载**：直接从 `componentCache` 返回（Promise.resolve），无网络请求
- **加载失败**：不缓存，下次重试

### 3.2 import 语句预处理

缓存层增加了 `preprocessImports()` 功能，将 ES6 `import` 语法转换为运行时兼容代码：

| 原始写法 | 转换结果 |
|----------|----------|
| `import { a, b } from '@/api/xxx'` | `var { a, b } = importModule("@/api/xxx");` |
| `import Component from '@/components/Xxx'` | `var Component = importComponent("@/components/Xxx");` |
| `import * as xxx from '@/utils/xxx'` | `var xxx = importModule("@/utils/xxx");` |

转换后通过 Blob URL 传递给底层 httpVueLoader 解析。

### 3.3 缓存管理 API

| 方法 | 用途 |
|------|------|
| `httpVueLoaderCached.clearCache(url?)` | 清除指定/全部缓存 |
| `httpVueLoaderCached.getCacheStats()` | 获取缓存统计（数量、大小、键列表） |
| `httpVueLoaderCached.preload(urls)` | 批量预加载组件 |
| `sa_admin.refreshComponentCache(url?)` | 开发模式刷新缓存 |
| `sa_admin.showCacheStats()` | 开发模式查看缓存统计 |

### 3.4 开发模式调试

当 `window.ENV_CONFIG.mode === 'dev'` 时：
- 控制台输出 `[Cache Hit]` / `[Cache Miss]` 日志
- 暴露 `httpVueLoaderCached._cache` 供直接检查

## 4. 菜单/路由的动态注册方式

### 4.1 后端菜单加载流程（sa-code.js）

新前端**不使用 Vue Router**，路由完全由 Sa-Admin 的 Tab 机制管理。菜单数据从 RuoYi 后端动态获取：

```
页面加载
  → 检查 Token（无则跳转 login.html）
  → GET /getInfo → 获取用户信息 + 权限列表 (permissions, roles)
    → ruoyi.setPermissions(permissions)
    → sa.setAuth(permissions)  // 同步到 Sa-Admin 权限系统
  → GET /getRouters → 获取后端路由数据
    → ruoyi.convertRoutersToMenuList(routers)  // 转换为 Sa-Admin 菜单格式
    → sa_admin.setMenuList(menuList)  // 注册菜单
    → sa_admin.init()  // 初始化框架
```

### 4.2 路由数据转换（ruoyi-util.js → convertRoutersToMenuList）

后端返回 RuoYi 标准路由格式，前端转换为 Sa-Admin 菜单格式：

| 后端字段 | 转换规则 | Sa-Admin 字段 |
|----------|----------|---------------|
| `route.path` | 拼接为 `menu-{path}` | `menu.id` |
| `route.meta.title` | 直接使用 | `menu.name` |
| `route.meta.icon` | `ruoyi.convertIcon()` 映射为 Element UI 图标 | `menu.icon` |
| `route.component` | `resolveComponentUrl()` 映射 | `menu.url` |
| `route.children` | 递归转换 | `menu.childList` |
| `route.hidden` | true 则跳过 | 不生成 |

**组件路径映射规则（resolveComponentUrl）：**

```
后端 component 值          →  前端 URL
system/user/index         →  sa-view/system/user/index.html
workflow/process/index    →  sa-view/workflow/process/index.html
Layout / ParentView       →  跳过（仅作为父级容器）
InnerLink + meta.link     →  外部链接（isBlank=true）
```

> **注意**：当前 `resolveComponentUrl()` 统一映射为 `.html` 后缀。若要使用 `.vue` 加载，需修改此函数将后缀改为 `.vue`。

### 4.3 菜单树构建

`sa_admin.setMenuList(menuList)` 内部调用 `arrayToTree()`，将扁平数组（含 `parentId`）转换为嵌套树结构（`childList`），支持最多 4 层嵌套。

### 4.4 路由导航机制

Sa-Admin 不使用传统 Vue Router，而是基于 **Tab + Hash** 的导航方式：

| 传统 Vue Router | Sa-Admin 等价方式 |
|-----------------|-------------------|
| `this.$router.push('/user')` | `sa_admin.showTab({id:'user', name:'用户', url:'sa-view/user.vue'})` |
| `<router-link to="/user">` | 菜单点击 → `showMenuById(id)` |
| URL 路径 `/user` | URL Hash `#menu-user` |
| 浏览器前进/后退 | `window.onhashchange` → `showTabByHash()` |
| 路由守卫 | Token 检查在 `sa-code.js` IIFE 中完成 |

**Hash 路由特性：**
- 当前 Tab 的 id 写入 `location.hash`
- 页面刷新时通过 hash 恢复上次打开的 Tab（`isRemeOpen: true`）
- 监听 `window.onhashchange` 实现锚点导航

## 5. .vue 文件编写规范

在 httpVueLoader 模式下，`.vue` 文件有以下约束：

```vue
<template>
    <div><!-- 必须单根元素 --></div>
</template>

<script>
// 1. 不能使用 ES6 module 的 export default，必须用 CommonJS：
module.exports = {
    // 2. data 必须是函数：
    data: function() { return { } },
    // 3. 子组件用 httpVueLoader 加载：
    components: {
        "sa-item": httpVueLoader('../../sa-frame/com/sa-item.vue'),
    },
    // 4. API 调用使用 sa.ajax()：
    methods: {
        f5: function() {
            sa.ajax('/api/endpoint', {}, function(res) {
                this.dataList = res.data;
            }.bind(this));
        }
    }
}
</script>

<style scoped>
/* scoped 样式自动隔离 */
</style>
```

**关键限制：**
- 使用 `module.exports` 而非 `export default`
- 不支持原生 ES6 import（由 vue-loader-cache.js 预处理转换）
- 无需引入 Vue/Element UI（共享父实例依赖）
- 路径使用相对路径（基于 .vue 文件自身位置）

## 6. 架构总结

```
┌──────────────────────────────────────────────────┐
│  index.html                                       │
│  ├── Vue 2 / Element UI / jQuery (CDN)           │
│  ├── httpVueLoader.js (底层解析器)                │
│  ├── vue-loader-cache.js (缓存 + import 预处理)  │
│  ├── index.js (sa_admin 主实例)                   │
│  └── sa-code.js (认证 + 菜单动态加载)            │
│                                                    │
│  加载流程:                                         │
│  Token检查 → /getInfo → /getRouters               │
│  → convertRoutersToMenuList → setMenuList → init  │
│                                                    │
│  页面渲染:                                         │
│  .vue → httpVueLoaderCached → <component :is>     │
│  .html → <iframe src>                              │
│                                                    │
│  缓存: 内存对象，首次网络加载，二次直接返回        │
│  路由: Hash-based Tab 导航，非 Vue Router          │
└──────────────────────────────────────────────────┘
```
