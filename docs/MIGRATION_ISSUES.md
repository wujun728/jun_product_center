# Vue 2 到 Vue 3 迁移问题记录

## 文档说明

本文档记录 ruoyi-vue-oa-ui (Vue 2) 迁移到 yudao-ui-admin-vue3 (Vue 3) 过程中遇到的兼容性问题、解决方案和最佳实践。

**更新时间**: 2026-04-15
**负责团队**: 前端开发组

## 问题统计

| 类别 | 已记录 | 已解决 | 待解决 | 备注 |
|------|--------|--------|--------|------|
| API变更 | 0 | 0 | 0 | Vue 2到Vue 3的API变更 |
| 组件库 | 0 | 0 | 0 | Element UI到Element Plus |
| 路由 | 0 | 0 | 0 | Vue Router 3到4 |
| 状态管理 | 0 | 0 | 0 | Vuex 3到4/Pinia |
| 第三方库 | 0 | 0 | 0 | 依赖库兼容性 |
| 构建工具 | 0 | 0 | 0 | Webpack到Vite |
| 业务逻辑 | 0 | 0 | 0 | 业务代码适配 |
| 性能优化 | 0 | 0 | 0 | 迁移后的性能问题 |

---

## 问题模板

使用以下模板记录新问题：

```markdown
### [问题编号] 问题简述

**类别**: API变更 / 组件库 / 路由 / 状态管理 / 第三方库 / 构建工具 / 业务逻辑 / 性能优化
**严重程度**: 阻塞 / 高 / 中 / 低
**影响范围**: 影响的模块或组件列表
**发现日期**: YYYY-MM-DD
**状态**: 待解决 / 进行中 / 已解决 / 已验证

#### 问题描述
详细描述问题的表现和复现步骤

#### 原代码（Vue 2）
```vue
// 贴上有问题的Vue 2代码
```

#### 错误信息
```
// 错误堆栈或控制台输出
```

#### 解决方案
详细说明解决方案和实施步骤

#### 修改后代码（Vue 3）
```vue
// 贴上修改后的Vue 3代码
```

#### 验证结果
说明验证测试的结果

#### 参考资料
- [官方文档链接]
- [相关Issue链接]

---
```

---

## 已知的通用迁移问题（待确认）

### [COMMON-001] Vue 3 API 变更清单

**类别**: API变更
**严重程度**: 高
**状态**: 待确认

#### 预期的主要变更

1. **Composition API vs Options API**
   - Vue 2主要使用Options API
   - Vue 3推荐使用Composition API（setup语法糖）
   - 需要决定迁移策略：完全重写为Composition API 或保持Options API

2. **生命周期钩子变更**
   ```javascript
   // Vue 2
   beforeDestroy() {}
   destroyed() {}

   // Vue 3
   beforeUnmount() {}
   unmounted() {}
   ```

3. **事件监听器**
   ```vue
   <!-- Vue 2 -->
   <component @click.native="handler" />

   <!-- Vue 3 -->
   <component @click="handler" />
   <!-- .native修饰符已移除 -->
   ```

4. **v-model 变更**
   ```vue
   <!-- Vue 2 -->
   <component v-model="value" />
   <!-- 等同于 :value + @input -->

   <!-- Vue 3 -->
   <component v-model="value" />
   <!-- 等同于 :modelValue + @update:modelValue -->
   ```

5. **多个 v-model**
   ```vue
   <!-- Vue 3支持多个v-model -->
   <component
     v-model:title="title"
     v-model:content="content"
   />
   ```

6. **过滤器 (filters) 移除**
   ```javascript
   // Vue 2
   filters: {
     formatDate(value) { return ... }
   }

   // Vue 3 - 使用方法或计算属性替代
   methods: {
     formatDate(value) { return ... }
   }
   ```

7. **$children 移除**
   ```javascript
   // Vue 2
   this.$children

   // Vue 3 - 使用 ref 替代
   <template>
     <child-component ref="childRef" />
   </template>

   setup() {
     const childRef = ref(null)
     return { childRef }
   }
   ```

---

### [COMMON-002] Element UI 到 Element Plus 迁移

**类别**: 组件库
**严重程度**: 高
**状态**: 待确认

#### 预期的主要变更

1. **包名变更**
   ```javascript
   // Vue 2 + Element UI
   import { Button } from 'element-ui'

   // Vue 3 + Element Plus
   import { ElButton } from 'element-plus'
   ```

2. **组件名称变更**
   - 所有组件名称添加 `El` 前缀
   - `<el-button>` 仍然可用，但导入时需要 `ElButton`

3. **图标系统变更**
   ```vue
   <!-- Element UI -->
   <i class="el-icon-edit"></i>

   <!-- Element Plus -->
   <el-icon><Edit /></el-icon>
   ```

4. **主题定制变更**
   - SCSS变量名称可能有变化
   - CSS变量系统改进

5. **表单验证规则**
   - 基本兼容，但某些边界情况可能需要调整

6. **表格组件**
   - API基本兼容
   - 某些slot名称可能有变化

---

### [COMMON-003] Vue Router 3 到 4 迁移

**类别**: 路由
**严重程度**: 高
**状态**: 待确认

#### 预期的主要变更

1. **创建路由实例**
   ```javascript
   // Vue Router 3
   import VueRouter from 'vue-router'
   const router = new VueRouter({ routes })

   // Vue Router 4
   import { createRouter, createWebHistory } from 'vue-router'
   const router = createRouter({
     history: createWebHistory(),
     routes
   })
   ```

2. **mode 选项移除**
   ```javascript
   // Vue Router 3
   mode: 'history'

   // Vue Router 4
   history: createWebHistory()
   history: createWebHashHistory()
   history: createMemoryHistory()
   ```

3. **通配符路由**
   ```javascript
   // Vue Router 3
   { path: '*', component: NotFound }

   // Vue Router 4
   { path: '/:pathMatch(.*)*', component: NotFound }
   ```

4. **导航守卫返回值**
   ```javascript
   // Vue Router 4 - 返回 false 取消导航
   router.beforeEach((to, from) => {
     if (!isAuthenticated) return false
     // 或 return { name: 'Login' }
   })
   ```

---

### [COMMON-004] Vuex 到 Pinia 迁移（可选）

**类别**: 状态管理
**严重程度**: 中
**状态**: 待决策

#### 说明

Vue 3 推荐使用 Pinia 替代 Vuex，但 Vuex 4 仍然支持 Vue 3。

#### 选项 A：保持 Vuex 4

**优点**:
- 迁移工作量小
- 代码改动少
- 团队熟悉

**缺点**:
- 不是 Vue 3 官方推荐方案
- TypeScript 支持不如 Pinia

#### 选项 B：迁移到 Pinia

**优点**:
- Vue 3 官方推荐
- 更好的 TypeScript 支持
- API 更简洁
- 性能更好

**缺点**:
- 需要重写所有 store 模块
- 学习成本

#### 建议

建议先使用 Vuex 4 完成基础迁移，后期再逐步迁移到 Pinia。

---

### [COMMON-005] 第三方库兼容性问题

**类别**: 第三方库
**严重程度**: 高
**状态**: 待排查

#### 需要检查的关键依赖

| 依赖库 | Vue 2 版本 | Vue 3 兼容版本 | 迁移难度 | 备注 |
|--------|-----------|---------------|---------|------|
| bpmn-js | 11.1.0 | 11.1.0+ | 低 | 流程设计器，与Vue版本无关 |
| wangEditor | v5.1.23 | v5.1.23+ | 低 | 富文本编辑器，已支持Vue 3 |
| echarts | 需确认 | 5.4.0+ | 低 | 图表库，与Vue版本无关 |
| axios | 需确认 | 1.0.0+ | 低 | HTTP客户端，与Vue版本无关 |
| js-cookie | 需确认 | 3.0.0+ | 低 | Cookie操作，与Vue版本无关 |
| nprogress | 需确认 | 0.2.0+ | 低 | 进度条，与Vue版本无关 |
| screenfull | 需确认 | 6.0.0+ | 低 | 全屏API，与Vue版本无关 |
| sortablejs | 需确认 | 1.15.0+ | 中 | 拖拽排序，需要Vue 3包装器 |
| vue-simple-uploader | 需确认 | 需找Vue 3版本 | 高 | 文件上传，可能需要替换 |

#### 特别关注

1. **拖拽库 (sortablejs)**
   - 需要使用 `@shopify/draggable` 或 `vue.draggable.next`

2. **文件上传组件**
   - `vue-simple-uploader` 可能需要替换为 `@vueup/vue-quill` 或其他方案

3. **富文本编辑器**
   - wangEditor 已支持 Vue 3
   - TinyMCE 需要检查版本

---

### [COMMON-006] Webpack 到 Vite 迁移

**类别**: 构建工具
**严重程度**: 高
**状态**: 待确认

#### 主要变更

1. **配置文件**
   ```javascript
   // vue.config.js (Webpack)
   module.exports = {
     devServer: { proxy: {...} }
   }

   // vite.config.js (Vite)
   export default defineConfig({
     server: { proxy: {...} }
   })
   ```

2. **环境变量**
   ```javascript
   // Webpack
   process.env.VUE_APP_API_URL

   // Vite
   import.meta.env.VITE_API_URL
   ```

3. **静态资源导入**
   ```javascript
   // Webpack
   require('@/assets/logo.png')

   // Vite
   import logo from '@/assets/logo.png'
   // 或使用 new URL('@/assets/logo.png', import.meta.url)
   ```

4. **全局变量注入**
   - Webpack 的 DefinePlugin
   - Vite 的 define 配置

---

### [COMMON-007] 组件内部实现差异

**类别**: 业务逻辑
**严重程度**: 中
**状态**: 待排查

#### 可能遇到的问题

1. **Teleport（原Portal）**
   ```vue
   <!-- Vue 2 (使用插件) -->
   <portal to="destination">
     <p>内容</p>
   </portal>

   <!-- Vue 3 (内置) -->
   <teleport to="#destination">
     <p>内容</p>
   </teleport>
   ```

2. **Suspense（异步组件）**
   ```vue
   <!-- Vue 3 新特性 -->
   <suspense>
     <template #default>
       <async-component />
     </template>
     <template #fallback>
       <div>加载中...</div>
     </template>
   </suspense>
   ```

3. **多根节点组件**
   ```vue
   <!-- Vue 2 - 必须单根 -->
   <template>
     <div>
       <header>...</header>
       <main>...</main>
     </div>
   </template>

   <!-- Vue 3 - 支持多根 -->
   <template>
     <header>...</header>
     <main>...</main>
   </template>
   ```

---

## 迁移检查清单

### 代码级别

- [ ] 移除所有 `.native` 修饰符
- [ ] 更新生命周期钩子名称
- [ ] 移除 `$children` 使用，改用 `ref`
- [ ] 移除或替换过滤器 (filters)
- [ ] 更新 v-model 绑定
- [ ] 检查事件监听器的兼容性
- [ ] 更新 `$attrs` 和 `$listeners` 使用
- [ ] 检查自定义指令的实现
- [ ] 更新渲染函数（如果使用了 h 函数）
- [ ] 检查 transition 类名

### 组件库级别

- [ ] 更新 Element UI 到 Element Plus
- [ ] 更新图标使用方式
- [ ] 检查表单验证规则
- [ ] 检查表格组件的 slot
- [ ] 更新对话框、抽屉组件
- [ ] 检查下拉菜单、级联选择器

### 路由级别

- [ ] 更新路由创建方式
- [ ] 更新 mode 为 history 模式
- [ ] 更新通配符路由
- [ ] 检查导航守卫返回值
- [ ] 检查路由元信息

### 状态管理级别

- [ ] 决定使用 Vuex 4 还是 Pinia
- [ ] 更新 store 创建方式
- [ ] 检查模块化 store 的兼容性
- [ ] 更新插件使用方式

### 构建工具级别

- [ ] 更新 Webpack 配置或迁移到 Vite
- [ ] 更新环境变量使用方式
- [ ] 检查静态资源导入
- [ ] 更新全局变量定义
- [ ] 更新开发服务器配置
- [ ] 更新代理配置

### 第三方库级别

- [ ] 排查所有第三方库的 Vue 3 兼容性
- [ ] 更新或替换不兼容的库
- [ ] 检查拖拽库的兼容性
- [ ] 检查文件上传组件
- [ ] 检查富文本编辑器
- [ ] 检查图表库

### 测试级别

- [ ] 单元测试适配
- [ ] 集成测试适配
- [ ] E2E测试适配
- [ ] 性能测试

---

## 最佳实践

### 1. 渐进式迁移策略

1. **阶段一：基础设施迁移**
   - 升级 Vue 3、Vue Router 4、Vuex 4/Pinia
   - 升级 Element Plus
   - 配置构建工具（Vite）

2. **阶段二：核心模块迁移**
   - 优先迁移登录、首页等核心页面
   - 建立迁移模式和最佳实践

3. **阶段三：业务模块迁移**
   - 按优先级逐个迁移业务模块
   - 每个模块迁移后进行测试

4. **阶段四：优化和清理**
   - 性能优化
   - 代码清理
   - 文档更新

### 2. 代码风格统一

```vue
<!-- 推荐使用 Composition API + setup 语法糖 -->
<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'

const count = ref(0)
const doubleCount = computed(() => count.value * 2)

onMounted(() => {
  console.log('组件已挂载')
})
</script>

<template>
  <div>{{ count }}</div>
</template>
```

### 3. TypeScript 支持

强烈建议在迁移过程中引入 TypeScript，提升代码质量和开发体验。

```typescript
// 定义组件 props 类型
interface Props {
  title: string
  count?: number
}

const props = withDefaults(defineProps<Props>(), {
  count: 0
})
```

### 4. 组件拆分原则

- 单个组件不超过 300 行
- 复杂逻辑抽取为 composables
- 公共组件放入 components 目录

### 5. 性能优化建议

- 使用 `v-memo` 优化列表渲染
- 合理使用 `defineAsyncComponent` 懒加载组件
- 使用 `shallowRef` 和 `shallowReactive` 优化大对象
- 使用 `KeepAlive` 缓存组件状态

---

## 参考资源

### 官方文档

- [Vue 3 迁移指南](https://v3-migration.vuejs.org/)
- [Element Plus 文档](https://element-plus.org/)
- [Vue Router 4 迁移指南](https://router.vuejs.org/guide/migration/)
- [Pinia 文档](https://pinia.vuejs.org/)
- [Vite 文档](https://vitejs.dev/)

### 工具

- [Vue 2 to 3 Migration Helper](https://github.com/vuejs/vue-migration-helper)
- [ESLint Plugin Vue](https://eslint.vuejs.org/)
- [Vue DevTools](https://devtools.vuejs.org/)

### 社区资源

- [Awesome Vue 3](https://github.com/vue3/awesome-vue-3)
- [Vue 3 Examples](https://github.com/vuejs/vue-next-examples)

---

## 变更历史

| 日期 | 修改内容 | 修改人 |
|------|----------|--------|
| 2026-04-15 | 初始创建，添加通用问题和检查清单 | Claude Agent |

---

## 问题反馈

遇到新问题请按照问题模板格式添加到本文档，并更新问题统计表。
