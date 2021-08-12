## 更新日志   :id=log

> 当前版本：`boot 1.5.0.release`，更新于：`2021-07-06`，查看 [在线演示](http://boot.pearadmin.com)。

#### 2021-07-06 （ 1.5.0.release ）   :id=log_315

- [新增] 新增 icon 30 + 图标
- [修复] 修复定时任务日志无法查询
- [修复] Notice 组件 layui-this 无故清空，无选中项
- [修复] 多系统模式下，顶部菜单超出可视区域，增加滑动切换
- [修复] 代码生成时 create_by，update_by 等字段自动关联或自动生成
- [修复] 代码生成时自定义路径生成代码时点击确定按钮弹窗不关闭
- [修复] 修改代码生成器生成xml的字段格式化关键字问题
- [修复] 修复文件丢失后页面无法删除的问题
- [依赖] 升级 Spring boot 2.4.4 版本
- [依赖] 升级 layui 2.6.8 版本
  
> 前往下载 ：[pear-admin-boot 1.5.0.Release](https://gitee.com/pear-admin/Pear-Admin-Boot/releases/1.5.0)

#### 2021-03-14 （ 1.4.0.release ）   :id=log_315

- [新增] 新增 message 组件
- [新增] 新增 Tab 框架右击操作菜单
- [新增] 新增 admin.logout 接口
- [新增] 新增用户站内消息
- [新增] 新增系统设置 保存权限限制
- [修复] 修复注销 Tab 记忆不清空问题
- [修复] 修复 Mail:data 列表权限错误
- [修复] 修复部分组件主题不跟随问题
- 
> 前往下载 ：[pear-admin-boot 1.4.0.Release](https://gitee.com/pear-admin/Pear-Admin-Boot/releases/1.4.0.RELEASE)


#### 2021-02-24 （ 1.3.5.release ）   :id=log_315

- [修复] 修复定时任务实体，因 DevTool 依赖类型转换问题
- [修复] 修复 Layui-layer-btn 主题跟随
- [修复] 修复代码生成配置修改页，选项卡无法关闭问题
- [优化] 优化 Monitor 系统监控线程阻塞时长，优化响应
- [优化] 启用本地静态资源缓存，优化响应速度
- [优化] 用户管理 -> 部门树 -> 样式定义
- [优化] 排除多余依赖，Hutool-core ..等

> 前往下载 ：[pear-admin-boot 1.3.5.Release](https://gitee.com/pear-admin/Pear-Admin-Boot/releases/1.3.5.RELEASE)

#### 2021-01-15 （ 1.2.7.release ）   :id=log_315

- [新增] 新增个人资料页面，用户头像上传功能
- [新增] 新增个人资料页面，用户登录日志记录
- [新增] 新增系统监控，Cpu，内存，磁盘， 可视化监控
- [优化] 用户列表，新增高级列表，实现侧边部门伸缩
- [优化] 前端代码增加缓存，优化页面加载速度
- [优化] 增加 common.checkField 方法，用于表格选中项获取，简化代码
- [修复] 修复账号多端登录无法挤退问题

> 前往下载 ：[pear-admin-boot 1.2.7.Release](https://gitee.com/pear-admin/Pear-Admin-Boot/releases/1.2.7.RELEASE)