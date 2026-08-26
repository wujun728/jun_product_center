# T007: 新前端公共组件和依赖分析

## 一、新前端 (jun_ui_sa_admin) 全局可用资源

### 1.1 全局依赖库

| 库 | 版本 | 加载方式 | 说明 |
|---|------|---------|------|
| Vue.js | 2.6.10 | 本地静态 | 核心框架 |
| Element UI | 2.13.0 | 本地静态 | UI组件库（⚠️ 原前端为 2.15.14） |
| jQuery | 3.4.1 | 本地静态 | sa.ajax 底层 |
| layer.js | 3.1.1+ | 本地静态 | 弹窗层 |
| http-vue-loader | - | 本地静态 | .vue 文件动态加载 |
| Sortable.js | - | 本地静态 | 拖拽排序 |
| vuedraggable | - | 本地静态 | Vue 拖拽组件 |

### 1.2 全局工具对象

| 对象 | 来源文件 | 主要功能 |
|------|---------|---------|
| `sa` | static/sa.js | ajax请求、弹窗(msg/alert/confirm)、认证、日期、数组操作 |
| `sa_admin` | sa-frame/index/index.js | 根Vue实例：菜单管理、Tab管理、UI状态 |
| `sa_admin_code_util` | sa-frame/index/admin-util.js | 数组转树、菜单查找等工具 |
| `ruoyi` | static/ruoyi-util.js | Token管理、HTTP请求(get/post/put/delete)、权限、角色、字典 |
| `window.CONFIG` | config/config.js | 环境配置(API_BASE_URL, WS_URL等) |

### 1.3 全局函数

| 函数 | 来源 | 说明 |
|------|------|------|
| `loadComponent(name)` | component-loader.js | 按名称加载单个组件 |
| `loadComponents(array)` | component-loader.js | 批量加载组件 |
| `registerComponent(name, path)` | component-loader.js | 注册组件别名 |
| `importModule(path)` | api-adapter.js | 虚拟ES6模块导入 |
| `defineModule(path, exports)` | api-adapter.js | 定义虚拟ES6模块 |
| `httpVueLoaderCached(path)` | vue-loader-cache.js | 带缓存的.vue加载 |

### 1.4 框架组件（sa-frame内置）

| 组件 | 路径 | 用途 |
|------|------|------|
| `sa-item` | sa-frame/com/sa-item.vue | 通用输入组件（20+类型：text/num/date/img/file/richtext等） |
| `sa-info` | sa-frame/com/sa-info.vue | 通用展示组件（sa-item的只读版） |
| `sa-td` | sa-frame/com/sa-td.vue | 通用表格列组件（增强el-table-column） |

**加载方式：** 非全局注册，需在各页面按需引入：
```javascript
components: loadComponents(['sa-item', 'sa-info', 'sa-td'])
```

### 1.5 导航组件（框架内置，已全局注册）

nav-logo / nav-menu-bar / nav-tool-bar / nav-tab-bar / nav-view-vessel / com-right-menu / com-add-tab

---

## 二、原前端 (ruoyi-vue-oa-ui) 全局资源清单

### 2.1 全局注册组件（Vue.component）

| 组件 | 路径 | 用途 |
|------|------|------|
| **Pagination** | @/components/Pagination | 分页组件 |
| **RightToolbar** | @/components/RightToolbar | 搜索切换、刷新、列显隐 |
| **DictTag** | @/components/DictTag | 字典值标签展示 |
| **Editor** | @/components/Editor | Quill富文本编辑器 |
| **FileUpload** | @/components/FileUpload | 多文件上传 |
| **ImageUpload** | @/components/ImageUpload | 图片上传+预览 |
| **ImagePreview** | @/components/ImagePreview | 图片预览 |
| **DictData** | @/components/DictData | 字典数据自动加载插件 |
| **tinymce** | @/components/tinymce | TinyMCE编辑器 |

### 2.2 全局指令

| 指令 | 用途 |
|------|------|
| `v-hasPermi` | 按钮级权限控制 |
| `v-hasRole` | 角色级权限控制 |
| `v-clipboard` | 剪贴板复制 |
| `v-dialogDrag` | 对话框拖拽 |
| `v-dialogDragWidth` | 对话框宽度调整 |
| `v-dialogDragHeight` | 对话框高度调整 |
| `v-adaptive` | 表格高度自适应 |

### 2.3 全局原型方法（Vue.prototype）

| 方法 | 用途 |
|------|------|
| `$tab` | 标签页管理 |
| `$auth` | 认证工具 |
| `$cache` | 缓存操作 |
| `$modal` | 模态弹窗(msgSuccess/msgError/confirm等) |
| `$download` | 文件下载 |
| `getDicts()` | 获取字典数据 |
| `getConfigKey()` | 获取系统配置 |
| `parseTime()` | 日期解析 |
| `resetForm()` | 表单重置 |
| `addDateRange()` | 日期范围查询 |
| `selectDictLabel()` | 字典标签选择 |
| `handleTree()` | 树形数据转换 |

### 2.4 独有第三方库

bpmn-js, echarts, @wangeditor/editor, quill, mavon-editor, vue-simple-uploader, vue-codemirror, @riophae/vue-treeselect, vue-cropper, vue-pdf, vue-esign, vue-plugin-hiprint, video.js, fuse.js, jsencrypt, Vuex, Vue Router

---

## 三、差异对比与迁移指南

### 3.1 新前端已具备（可直接使用）

| 原前端功能 | 新前端对应 | 备注 |
|-----------|-----------|------|
| Element UI 组件 | ✅ Element UI 2.13.0 | 版本略低(2.13 vs 2.15)，极少数新API不可用 |
| Vue 2 | ✅ Vue 2.6.10 | 版本略低(2.6.10 vs 2.6.14)，无实质影响 |
| Sortable/vuedraggable | ✅ 已加载 | 拖拽功能可用 |
| `this.$modal` 类弹窗 | ✅ `sa.msg/sa.alert/sa.confirm` | 需改写调用方式 |
| `$tab` 标签页管理 | ✅ `sa_admin.addTab/showTab/closeTab` | 需改写调用方式 |
| `v-hasPermi` 权限指令 | ✅ `ruoyi.hasPermission()` | 已有函数，但未注册为指令 |
| 字典数据 | ✅ `ruoyi.getDicts/getDictItemText` | API已适配 |
| 日期/树形工具 | ✅ `static/utils/ruoyi.js` | parseTime/handleTree等已有 |
| HTTP 请求 | ✅ `ruoyi.get/post/put/delete` | 或 sa.ajax |
| Token 管理 | ✅ `ruoyi.getToken/setToken` | 已实现 |

### 3.2 新前端缺少（需额外引入或开发）

#### A. 必须补充的全局组件

| 组件 | 优先级 | 建议方案 |
|------|--------|---------|
| **Pagination** | 🔴 高 | 迁移原组件或用 el-pagination 直接替代 |
| **RightToolbar** | 🔴 高 | 迁移原组件（列表页标配） |
| **DictTag** | 🔴 高 | 迁移原组件（字典展示通用） |
| **FileUpload** | 🟡 中 | 迁移原组件或用 sa-item type="file" 替代 |
| **ImageUpload** | 🟡 中 | 迁移原组件或用 sa-item type="img" 替代 |
| **Editor** (Quill) | 🟡 中 | 引入 Quill CDN + 迁移组件，或用 sa-item type="richtext" |

#### B. 必须注册的全局指令

| 指令 | 优先级 | 建议方案 |
|------|--------|---------|
| **v-hasPermi** | 🔴 高 | 基于 `ruoyi.hasPermission()` 注册 Vue.directive |
| **v-hasRole** | 🔴 高 | 基于 `ruoyi.getRoles()` 注册 Vue.directive |
| **v-dialogDrag** | 🟡 中 | 迁移原指令代码 |

#### C. 必须补充的全局方法

| 方法 | 优先级 | 建议方案 |
|------|--------|---------|
| `this.$modal` | 🔴 高 | 创建适配层映射到 sa 弹窗方法 |
| `this.getDicts()` | 🔴 高 | 挂载到 Vue.prototype 或改为 `ruoyi.getDicts()` |
| `this.resetForm()` | 🟡 中 | 挂载到 Vue.prototype |
| `this.parseTime()` | 🟡 中 | 挂载到 Vue.prototype |
| `this.handleTree()` | 🟡 中 | 挂载到 Vue.prototype |
| `this.$download` | 🟢 低 | 按需引入 |

#### D. 缺少的第三方库（按需引入CDN）

| 库 | 使用场景 | 优先级 |
|---|---------|--------|
| **echarts** | 图表/数据可视化页面 | 🟡 迁移到相关页面时引入 |
| **bpmn-js** | 流程设计器 | 🟡 迁移工作流模块时引入 |
| **Quill / wangEditor** | 富文本编辑 | 🟡 sa-item richtext可能已覆盖 |
| **vue-treeselect** | 树形选择器 | 🟡 多处使用，需CDN引入 |
| **vue-cropper** | 头像裁剪 | 🟢 仅个人中心使用 |
| **codemirror** | 代码编辑器 | 🟢 仅代码生成模块 |
| **jsencrypt** | RSA加密 | 🟢 仅登录页使用 |
| **Vuex** | 状态管理 | ❌ 不需要，新前端用 sa_admin 管理状态 |
| **Vue Router** | 路由管理 | ❌ 不需要，新前端用 Hash Tab 机制 |

### 3.3 版本差异风险

| 项目 | 原前端 | 新前端 | 风险 |
|------|--------|--------|------|
| Vue | 2.6.14 | 2.6.10 | 极低，无breaking change |
| Element UI | 2.15.14 | 2.13.0 | **低风险**：2.13→2.15 主要是bug修复，个别新组件(如 el-skeleton)不可用 |

---

## 四、迁移建议优先级

### 第一批（T008前完成）：高频通用

1. 注册 `v-hasPermi` / `v-hasRole` 全局指令
2. 挂载 `this.$modal` 适配层
3. 挂载 `this.getDicts()` / `this.parseTime()` / `this.resetForm()` / `this.handleTree()` 到 Vue.prototype
4. 迁移 Pagination / RightToolbar / DictTag 组件

### 第二批（按模块迁移时）：按需引入

5. FileUpload / ImageUpload（或评估 sa-item 替代方案）
6. Editor（Quill 或 wangEditor CDN）
7. vue-treeselect CDN
8. v-dialogDrag 指令

### 第三批（特定模块）：延后处理

9. bpmn-js（工作流设计器）
10. echarts（数据可视化）
11. vue-cropper / codemirror / vue-pdf 等
