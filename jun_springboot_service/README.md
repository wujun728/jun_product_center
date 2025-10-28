# RuoYi-Plus 后台管理系统

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Vue](https://img.shields.io/badge/Vue-2.x-brightgreen.svg)](https://vuejs.org/)

> 基于若依框架二次开发的企业级后台管理系统，集成工作流、多租户、可视化大屏等企业级功能

## 📋 版本切换

集成了vue3+springboot3的版本，需要的切换分支即可。

## 🌐 演示地址

- **在线演示**: [http://154.219.105.118:17777/](http://154.219.105.118:17777/)
- **技术论坛**: [https://3ylt.cn/](https://3ylt.cn/) - 分享电影、电视剧、软件、游戏、书籍等内容
- **个人主页**: [贺秋雨个人介绍](http://154.219.105.118:11111/)

## 📖 平台简介

RuoYi-Plus 是基于若依（RuoYi）和最后的梦想脚手架进行二次开发的企业级后台管理系统。在保持原有功能完整性的基础上，集成了工作流、多租户、可视化大屏等企业级功能，为中小企业提供开箱即用的管理系统解决方案。

### ✨ 核心特性

- 🔥 **工作流引擎**: 集成 Flowable 工作流，支持复杂业务流程管理
- 📊 **可视化大屏**: 内置数据可视化大屏，支持实时数据展示
- 🏢 **多租户架构**: 支持 SaaS 模式的多租户解决方案
- 🔐 **数据权限**: 优化的数据权限控制，支持部门级和用户级权限
- 🌍 **国产化适配**: 支持达梦数据库等国产化数据库
- ☁️ **云存储**: 支持阿里云OSS、腾讯云COS、七牛云等多种云存储
- 🔑 **单点登录**: 集成 OAuth2.0 单点登录解决方案

## 🛠️ 技术架构

### 前端技术栈
- **框架**: Vue 2.x + Element UI
- **构建工具**: Webpack + Babel
- **状态管理**: Vuex
- **路由管理**: Vue Router

### 后端技术栈
- **核心框架**: Spring Boot 2.x
- **安全框架**: Spring Security + JWT
- **数据库**: MySQL / 达梦数据库
- **缓存**: Redis
- **ORM**: MyBatis-Plus
- **工作流**: Flowable
- **工具库**: Hutool + Lombok
- **接口文档**: Knife4j (Swagger)

### 集成组件
- **地图组件**: 支持地理位置相关功能
- **WebSocket**: 实时通信支持
- **文件存储**: 多云存储支持（S3协议兼容）
- **代码生成**: 智能代码生成器，支持 Lombok 和 MyBatis-Plus

## 🚀 核心功能

### 基础功能
- ✅ 用户管理：用户是系统操作者，该功能主要完成系统用户配置
- ✅ 部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限
- ✅ 岗位管理：配置系统用户所属担任职务
- ✅ 菜单管理：配置系统菜单，操作权限，按钮权限标识等
- ✅ 角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分
- ✅ 字典管理：对系统中经常使用的一些较为固定的数据进行维护

### 增强功能
- 🔄 **工作流管理**: 流程设计、流程部署、流程监控
- 📈 **数据大屏**: 可视化数据展示，支持自定义图表
- 🏗️ **多租户**: 租户管理、数据隔离、权限控制
- 📁 **文件管理**: 多云存储支持，文件上传下载
- 🔍 **系统监控**: 在线用户、数据监控、服务监控
- 📝 **代码生成**: 前后端代码自动生成，提高开发效率

### 扩展方案
- 📚 [MyBatis-Plus 数据权限解决方案](https://gitee.com/heqy65552335/ruoyi-plus/wikis/mybatis-plus%E6%96%B9%E6%A1%88/%E5%A4%9A%E7%A7%9F%E6%88%B7%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88)
- 🏢 [多租户解决方案（共享数据库）](https://gitee.com/heqy65552335/ruoyi-plus/wikis/mybatis-plus%E6%96%B9%E6%A1%88/%E5%A4%9A%E7%A7%9F%E6%88%B7%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88)
- 🗄️ [达梦数据库适配方案](https://gitee.com/heqy65552335/ruoyi-plus/wikis/%E5%9B%BD%E4%BA%A7%E5%8C%96%E9%80%82%E9%85%8D/%E9%80%82%E9%85%8D%E8%BE%BE%E6%A2%A6)
- ☁️ [OSS 文件上传插件](https://gitee.com/heqy65552335/ruoyi-plus/wikis/%E6%96%87%E4%BB%B6%E5%AD%98%E5%82%A8/%E6%96%87%E4%BB%B6%E6%9C%8D%E5%8A%A1minio%E5%AE%89%E8%A3%85)
- 🔐 [OAuth2.0 单点登录方案](https://gitee.com/heqy65552335/ruoyi-plus/wikis/%E8%8B%A5%E4%BE%9D%E6%95%B4%E5%90%88Oauth2.0%E5%8D%95%E7%82%B9/%E5%89%8D%E7%AB%AF%E4%BF%AE%E6%94%B9)

## 🚀 快速开始

### 环境要求

- **JDK**: 1.8+
- **MySQL**: 5.7+
- **Redis**: 3.0+
- **Node.js**: 12.0+
- **npm**: 6.0+

### 后端启动

1. **克隆项目**
   ```bash
   git clone https://gitee.com/heqy65552335/ruoyi-plus.git
   ```

2. **数据库配置**
   - 创建数据库 `ruoyi-plus`
   - 导入 `sql/ruoyi-flowable.sql` 文件

3. **修改配置**
   - 编辑 `ruoyi-admin/src/main/resources/application-druid.yml`
   - 配置 MySQL 和 Redis 连接信息

4. **启动应用**
   ```bash
   # 方式一：IDE 启动
   运行 ruoyi-admin/src/main/java/com/ruoyi/RuoYiApplication.java
   ```

### 前端启动

1. **安装依赖**
   ```bash
   npm install --registry=https://registry.npmmirror.com
   ```
   
2**访问系统**
   - 前端地址：http://localhost:80
   - 后端接口：http://localhost:8080
   - 默认账号：admin/admin123

## 📦 部署指南

### 生产环境部署

1. **后端部署**
   ```bash
   # 打包
   mvn clean package -Dmaven.test.skip=true
   
   # 部署
   java -jar ruoyi-admin.jar --spring.profiles.active=prod
   ```

2. **前端部署**
   ```bash
   # 构建
   npm run build:prod

   ```

2**Nginx 配置参考**
   - 详细配置请参考：[若依部署文档](https://doc.ruoyi.vip/ruoyi-vue/document/hjbs.html#nginx%E9%85%8D%E7%BD%AE)

## ⚠️ 注意事项

### 数据库相关

- **表结构要求**：新建表必须包含以下字段
  ```sql
  create_time   datetime     comment '创建时间',
  create_by     varchar(64)  comment '创建者',
  create_name   varchar(64)  comment '创建者姓名',
  update_time   datetime     comment '更新时间',
  update_by     varchar(64)  comment '更新者',
  update_name   varchar(64)  comment '更新者姓名'
  ```

- **软删除字段**：如果使用软删除，添加 `del_flag` 字段，代码生成器会自动处理

- **MySQL 配置**：需要在 `my.cnf` 中添加以下配置
  ```ini
  [mysqld]
  # 取消大小写敏感
  lower_case_table_names=1
  # 取消严格模式
  innodb_strict_mode=0
  ```

### 开发规范

- **代码组织**：新功能建议放在 `ruoyi-logic` 模块下，便于代码管理和框架升级
- **数据权限**：
  - 部门权限：表中需要 `dept_id` 字段
  - 用户权限：表中需要 `user_id` 字段
  - 在 ServiceImpl 方法上添加 `@DataScope()` 注解
  - 在 Mapper.xml 的 WHERE 子句末尾添加 `${params.dataScope}`

### 数据权限配置示例

```java
// ServiceImpl 中的方法
@DataScope(deptAlias = "d", userAlias = "u")
public List<SysUser> selectUserList(SysUser user) {
    return userMapper.selectUserList(user);
}
```

```xml
<!-- Mapper.xml 中的 SQL -->
<select id="selectUserList" parameterType="SysUser" resultMap="SysUserResult">
    SELECT u.*, d.dept_name
    FROM sys_user u
    LEFT JOIN sys_dept d ON u.dept_id = d.dept_id
    WHERE u.del_flag = '0'
    ${params.dataScope}
</select>
```

## 🤝 贡献指南

我们欢迎所有形式的贡献，包括但不限于：

- 🐛 报告 Bug
- 💡 提出新功能建议
- 📝 改进文档
- 🔧 提交代码修复

### 如何贡献

1. Fork 本仓库
2. 创建您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的修改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开一个 Pull Request

## 📄 开源协议

### 承诺与愿景

- ✅ **永久免费**：本系统永远保持开源免费，不会有任何商业盈利行为
- 🔄 **持续更新**：保证功能完整性，持续优化代码质量
- 🛡️ **长期维护**：只要作者还在从事相关工作，就会持续维护更新
- 🎯 **质量保证**：注重代码质量和用户体验，不断改进和完善


## 📸 演示图片

### 系统界面展示

<div align="center">

| 登录界面 | 系统首页 |
|:---:|:---:|
| <img src="./img/0.jpg" alt="登录界面" width="400"/> | <img src="./img/1.jpg" alt="系统首页" width="400"/> |

| 用户管理 | 角色管理 |
|:---:|:---:|
| <img src="./img/2.jpg" alt="用户管理" width="400"/> | <img src="./img/3.jpg" alt="角色管理" width="400"/> |

| 菜单管理 | 部门管理 |
|:---:|:---:|
| <img src="./img/4.jpg" alt="菜单管理" width="400"/> | <img src="./img/5.jpg" alt="部门管理" width="400"/> |

| 字典管理 | 参数设置 |
|:---:|:---:|
| <img src="./img/6.jpg" alt="字典管理" width="400"/> | <img src="./img/7.jpg" alt="参数设置" width="400"/> |

| 通知公告 | 日志管理 |
|:---:|:---:|
| <img src="./img/8.jpg" alt="通知公告" width="400"/> | <img src="./img/9.jpg" alt="日志管理" width="400"/> |

| 在线用户 | 定时任务 |
|:---:|:---:|
| <img src="./img/10.jpg" alt="在线用户" width="400"/> | <img src="./img/11.jpg" alt="定时任务" width="400"/> |

| 数据监控 | 服务监控 |
|:---:|:---:|
| <img src="./img/12.jpg" alt="数据监控" width="400"/> | <img src="./img/13.jpg" alt="服务监控" width="400"/> |

| 缓存监控 | 系统接口 |
|:---:|:---:|
| <img src="./img/14.jpg" alt="缓存监控" width="400"/> | <img src="./img/15.jpg" alt="系统接口" width="400"/> |

| 代码生成 | 系统工具 |
|:---:|:---:|
| <img src="./img/16.jpg" alt="代码生成" width="400"/> | <img src="./img/17.jpg" alt="系统工具" width="400"/> |

| 表单构建 |
|:---:|
| <img src="./img/18.jpg" alt="表单构建" width="400"/> |

</div>

## 🙏 致谢

感谢以下开源项目和社区的支持：

- 🎯 **[若依 (RuoYi)](https://gitee.com/y_project/RuoYi-Vue)** - 提供了优秀的基础框架
- 🌟 **[最后的梦想](https://gitee.com/rainsuper/RuoYi-Vue-Super)** - 提供了宝贵的功能扩展
- 👥 **若依前后端分离QQ群** - 技术交流与支持 [![加入QQ群](https://img.shields.io/badge/QQ群-136919097-blue.svg)](https://jq.qq.com/?_wv=1027&k=tKEt51dz)

## 💖 支持项目

如果这个项目对您有帮助，欢迎给个 ⭐ Star 支持一下！

您的每一份支持都是我持续维护和改进项目的动力！

### 赞助方式

<div align="center">

| 微信赞赏 | 支付宝赞赏 |
|:---:|:---:|
| <img src="./img/wechat.jpg" alt="微信赞赏" width="200"/> | <img src="./img/alipay.jpg" alt="支付宝赞赏" width="200"/> |

</div>

---

<div align="center">

**🎉 感谢您的关注和支持！**

如有问题或建议，欢迎提交 [Issue](https://gitee.com/heqy65552335/ruoyi-plus/issues) 或 [PR](https://gitee.com/heqy65552335/ruoyi-plus/pulls)

**⚡ 承接各种外包项目，技术硬，质量好，成本低！**

📧 联系方式：[个人介绍页面](http://154.219.105.118:11111/)

</div>