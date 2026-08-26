# 前端依赖对比报告

生成时间: 2026-04-15

## 概述

对比原Vue2前端（`ruoyi-vue-oa-ui`）和新Vue3前端（`yudao-ui-admin-vue3`）的package.json，识别需要补充的依赖。

## 原Vue2前端特有的关键依赖

### 1. 已在Vue3前端中存在（无需添加）

| Vue2依赖 | Vue3等价依赖 | 状态 |
|---------|------------|------|
| `vue-simple-uploader@^0.7.6` | `vue-simple-uploader@^0.7.6` | ✓ 已存在 |
| `vuedraggable@^2.24.3` | `vuedraggable@^4.1.0` | ✓ 已升级 |
| `video.js@^8.17.4` | `video.js@^7.21.5` | ✓ 已存在 |
| `@wangeditor/editor@^5.1.23` | `@wangeditor-next/editor@^5.6.46` | ✓ 已升级 |
| `@wangeditor/editor-for-vue@^1.0.2` | `@wangeditor-next/editor-for-vue@^5.1.14` | ✓ 已升级 |
| `vue-pdf@^4.2.0` | `vue-pdf-embed@^2.1.4` | ✓ 已替换 |
| `vue-esign@^1.1.4` | `vue3-signature@^0.2.4` | ✓ 已替换 |
| `vue-codemirror@^4.0.6` | `codemirror-editor-vue3@^2.8.0` | ✓ 已替换 |
| `vue-cropper@0.5.5` | `cropperjs@^1.6.1` | ✓ 已替换 |
| `splitpanes@2.4.1` | `splitpanes@^3.2.0` | ✓ 已升级 |
| `echarts@5.4.0` | `echarts@^5.5.0` | ✓ 已升级 |
| `file-saver@2.0.5` | `file-saver@^2.0.5` | ✓ 已存在 |
| `highlight.js@9.18.5` | `highlight.js@^11.9.0` | ✓ 已升级 |
| `jsencrypt@3.0.0-rc.1` | `jsencrypt@^3.3.2` | ✓ 已升级 |
| `localforage@^1.10.0` | `localforage@^1.10.0` | ✓ 已存在 |
| `nprogress@0.2.0` | `nprogress@^0.2.0` | ✓ 已存在 |
| `quill@2.0.2` | `quill@^2.0.3` | ✓ 已升级 |
| `screenfull@5.0.2` | `screenfull@^6.0.2` | ✓ 已升级 |
| `sortablejs@1.10.2` | `sortablejs@^1.15.3` | ✓ 已升级 |
| `spark-md5@^3.0.2` | `spark-md5@^3.0.2` | ✓ 已存在 |
| `vkbeautify@^0.99.3` | `vkbeautify@^0.99.3` | ✓ 已存在 |
| `fuse.js@6.4.3` | `fuse.js@^7.3.0` | ✓ 已升级 |
| `clipboard@2.0.8` | `clipboard@^2.0.11` | ✓ 已升级 |
| `snabbdom@^3.6.2` | `snabbdom@^3.6.2` | ✓ 已存在 |

### 2. 需要添加Vue3兼容版本的依赖

| Vue2依赖 | 推荐Vue3替代方案 | 优先级 | 说明 |
|---------|----------------|--------|------|
| `@riophae/vue-treeselect@0.4.0` | `vue3-treeselect@^0.1.0` | 高 | 树形选择器组件 |
| `mavon-editor@^2.10.1` | `md-editor-v3@^4.12.0` | 高 | Markdown编辑器 |
| `vue-count-to@1.0.13` | `vue3-count-to@^1.1.2` | 中 | 数字滚动动画 |
| `vue-meta@2.4.0` | `@vueuse/head@^2.0.0` | 中 | 页面元数据管理 |
| `vue-plugin-hiprint@0.0.56` | 暂无官方Vue3版本 | 低 | 打印插件（需评估） |
| `xcrud@^0.4.19` | 暂无官方Vue3版本 | 低 | CRUD组件（需评估） |

### 3. BPMN相关依赖对比

| Vue2依赖 | Vue3依赖 | 状态 |
|---------|---------|------|
| `bpmn-js@^11.1.0` | `bpmn-js@^17.9.2` | ✓ 已升级（devDependencies） |
| `bpmn-js-bpmnlint@^0.22.3` | 未发现 | ⚠ 需补充 |
| `bpmnlint@^10.3.0` | 未发现 | ⚠ 需补充 |
| `diagram-js@^11.4.1` | `diagram-js@^12.8.0` | ✓ 已升级 |

## 安装建议

### 高优先级（必须安装）

```bash
cd ../yudao-ui-admin-vue3

# 树形选择器（Vue3版本）
npm install vue3-treeselect@^0.1.0

# Markdown编辑器（Vue3版本）
npm install md-editor-v3@^4.12.0

# BPMN校验工具（与Vue版本无关）
npm install bpmn-js-bpmnlint@^0.22.3 bpmnlint@^10.3.0
```

### 中优先级（推荐安装）

```bash
# 数字滚动动画
npm install vue3-count-to@^1.1.2

# 页面元数据管理
npm install @vueuse/head@^2.0.0
```

### 低优先级（需评估）

- `vue-plugin-hiprint`: 打印插件，原Vue2版本，需评估业务是否使用
- `xcrud`: CRUD组件，需评估业务是否使用，可能需要自行适配或寻找替代方案

## 风险评估

### 高风险
- `vue-plugin-hiprint` 和 `xcrud` 没有官方Vue3版本，如果业务强依赖需要寻找替代方案

### 中风险
- `vue3-treeselect` 是社区维护版本，API可能与原版有差异，需要适配代码

### 低风险
- `md-editor-v3` 是成熟的Vue3 Markdown编辑器
- `@vueuse/head` 是VueUse生态的官方元数据管理方案

## 下一步行动

1. ✅ 生成本报告
2. ⏭ 安装高优先级依赖
3. ⏭ 安装中优先级依赖
4. ⏭ 运行 `npm run dev` 测试依赖冲突
5. ⏭ 评估低优先级依赖的业务使用情况
