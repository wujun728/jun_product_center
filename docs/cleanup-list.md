# 新前端待清理页面列表

> 生成时间: 2026-04-03 (T008)
> 数据来源: inventory-new.md (T001), gap-analysis.md (T004)
> 待清理总数: 26 个文件

---

## 清理标准

| 标准 | 说明 |
|------|------|
| **冗余** | 与同路径 .vue 文件功能重复的 .html 版本，优先保留 .vue |
| **占位** | 页面代码极少，无实际业务逻辑的空壳文件 |
| **测试/Demo** | 开发调试用的测试页面，不应出现在生产环境 |

---

## 一、冗余 .html 文件（22 个）

与同路径 .vue 文件功能重复，应删除 .html 版本，统一使用 httpVueLoader 加载 .vue。

| # | 待清理文件 | 保留的 .vue 文件 | 清理原因 |
|---|-----------|-----------------|---------|
| 1 | system/user/index.html | system/user/index.vue | 冗余：与 .vue 版本重复 |
| 2 | system/user/authRole.html | system/user/authRole.vue | 冗余：与 .vue 版本重复 |
| 3 | system/user/profile/index.html | system/user/profile/index.vue | 冗余：与 .vue 版本重复 |
| 4 | system/role/index.html | system/role/index.vue | 冗余：与 .vue 版本重复 |
| 5 | system/role/authUser.html | system/role/authUser.vue | 冗余：与 .vue 版本重复 |
| 6 | system/menu/index.html | system/menu/index.vue | 冗余：与 .vue 版本重复 |
| 7 | system/dept/index.html | system/dept/index.vue | 冗余：与 .vue 版本重复 |
| 8 | system/post/index.html | system/post/index.vue | 冗余：与 .vue 版本重复 |
| 9 | system/dict/index.html | system/dict/index.vue | 冗余：与 .vue 版本重复 |
| 10 | system/dict/data.html | system/dict/data.vue | 冗余：与 .vue 版本重复 |
| 11 | system/config/index.html | system/config/index.vue | 冗余：与 .vue 版本重复 |
| 12 | monitor/online/index.html | monitor/online/index.vue | 冗余：与 .vue 版本重复 |
| 13 | monitor/job/index.html | monitor/job/index.vue | 冗余：与 .vue 版本重复 |
| 14 | monitor/job/log.html | monitor/job/log.vue | 冗余：与 .vue 版本重复 |
| 15 | monitor/operlog/index.html | monitor/operlog/index.vue | 冗余：与 .vue 版本重复 |
| 16 | monitor/logininfor/index.html | monitor/logininfor/index.vue | 冗余：与 .vue 版本重复 |
| 17 | monitor/cache/index.html | monitor/cache/index.vue | 冗余：与 .vue 版本重复 |
| 18 | monitor/cache/list.html | monitor/cache/list.vue | 冗余：与 .vue 版本重复 |
| 19 | monitor/server/index.html | monitor/server/index.vue | 冗余：与 .vue 版本重复 |
| 20 | tool/gen/index.html | tool/gen/index.vue | 冗余：与 .vue 版本重复 |
| 21 | tool/gen/editTable.html | tool/gen/editTable.vue | 冗余：与 .vue 版本重复 |
| 22 | tool/swagger/index.html | tool/swagger/index.vue | 冗余：与 .vue 版本重复 |

## 二、占位空壳文件（1 个）

| # | 待清理文件 | 行数 | 清理原因 |
|---|-----------|------|---------|
| 23 | cfg/xxx.vue | 42 | 占位：无实际业务逻辑的空壳文件，文件名无意义 |

## 三、测试/Demo 页面（3 个）

| # | 待清理文件 | 清理原因 |
|---|-----------|---------|
| 24 | test/api-adapter-test.html | 测试页面：API 适配层调试用，不应部署到生产环境 |
| 25 | test/api-adapter-test.vue | 测试页面：API 适配层测试组件，配合上述 .html 使用 |
| 26 | demo/component-loader-demo.html | Demo页面：组件加载器演示页，开发验证用 |

---

## 不清理的页面（保留说明）

以下 .html 文件虽无对应 .vue 版本，但有实际用途，**不列入清理范围**：

| 文件 | 保留原因 |
|------|---------|
| console/console-main.html | sa-admin 框架自带控制台主页，有独立功能 |
| system/notice/index.html | 通知公告管理页，无 .vue 版本，是唯一实现 |
| error-page/403.html | 403 错误页，独立 HTML 错误页面，无 .vue 对应 |
| error-page/404.html | 404 错误页，独立 HTML 错误页面，无 .vue 对应 |
| error-page/500.html | 500 错误页，独立 HTML 错误页面，无 .vue 对应 |
| cfg/system-cfg.html | 系统配置页，使用 httpVueLoader 加载，有独立功能 |
| sa-doc/sa-doc.html | SA-Admin 框架文档页，供开发参考 |

---

## 清理执行建议

1. **优先清理冗余 .html（第一类）**：直接删除 22 个文件，风险最低
2. **同步清理占位文件（第二类）**：删除 cfg/xxx.vue
3. **清理测试/demo（第三类）**：删除 test/ 和 demo/ 目录下 3 个文件
4. **清理后需检查**：确认菜单配置和路由中无硬编码引用这些 .html 文件的路径
