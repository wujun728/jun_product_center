## 更新日志   :id=log

> 当前版本：`layui 3.8.7.release`，更新于：`2021-07-06`，查看 [在线演示](http://layui.pearadmin.com)。

#### 2021-07-06 （ 3.8.7.release ）   :id=log_315

- [新增] 新增 collapse 配置，支持默认侧边收缩
- [新增] 新增 pear-text / pear-back 主题，用于自定义组件的主题色跟随
- [修复] 修复 默认 collaspe 收缩状态下二级菜单悬浮事件失效
- [修复] 多系统模式下，顶部菜单超出可视区域，增加滚动切换
- [修复] 修复消息主题，layui-this 默认选中主题色
- [修复] Menu 组件高度限制，theme 属性失效问题
- [优化] Button 加载按钮禁用状态仍可点击的问题
- [优化] Layout 布局栅格媒体查询条件调整
- [依赖] 升级 layui 至 2.6.8 最新版本
- [依赖] 更新 Dtree 2.5.8 版本

> 升级替换 component / pear 目录即可

#### 2021-04-01 （ 3.7.6.release ）   :id=log_315

- [新增] 新增 Admin.jump（）函数，提供不同模式下的兼容性跳转
- [修复] 修复 layui-form-danger 样式优先级过低，导致无法正常显示
- [修复] 修复 多系统 模式下，顶部菜单默认不选中问题
- [修复] 修复 全屏样式，新增 F11 全屏监听
- [优化] 优化 tinymce 初始化缓慢问题
- [依赖] 升级 layui 至 2.6.1 最新版本
- [依赖] 升级 echarts 至 5.0.2 最新版本
- [依赖] 升级 tinymce 编辑器至 5.6.2 版本
- [变更] 移除 dropdown 组件，因 layui 2.6.0 提供
- [变更] 移除 pear.all.css / pear.all.js 文件，简化代码结构

> 升级替换 component / pear 目录即可

#### 2021-03-11 （ 3.6.5.release ）   :id=log_315

- [新增] 新增 Tab 框架右击操作菜单
- [新增] 新增 Menu 数据请求方式自定义，支持 GET / POST
- [新增] 新增 Admin.logout 方法，提供自定义注销实现，返回值 true / false
- [新增] 新增 Admin.setAvatar 方法，提供主页用户信息初始化 src 头像 str 用户名
- [新增] 新增 Common.isModile() 方法，提供客户端判断，true 移动端 false 电脑端
- [修复] 修复表格页面，搜索模块边距偏移
- [修复] 修复 权限管理 批量删除失败
- [修复] 修复 "成功","失败"输入框的边框样式失效
- [修复] 修复手机版本适配问题
- [修复] 修复 popup.js 的warning错误
- [优化] 删除多余 Json 文件 Document.json / Route.json

> 升级替换 component / pear 目录即可

#### 2021-02-24 （ 3.6.3.release ）   :id=log_315

- [修复] 修复 layui-layer-btn 样式, 提供主题色跟随
- [修复] 修复 area 省市级联组件多实例数据隔离问题
- [修复] 修复选项卡关闭，select 节点错乱问题
- [修复] 修复 tab 与 menu 联动问题
- [修复] 修复单页面模式下 refresh 全局刷新问题
- [优化] 优化 layui.msg() 窗体细节，全局适配
- [优化] 重构 Button 按钮样式，提供全新的色彩搭配
- [新增] 提供 layui.frame.change.. 方法，提供多视图与单页面下的兼容跳转
- [新增] 升级 layui.table，新增 Table 下拉 Dome

> 升级替换 component / pear 目录即可

#### 2021-02-05 （ 3.6.0.release ）   :id=log_315

- [新增] 新增全局主题，IFRAME 内部页面主页色跟随
- [新增] 新增选项卡记忆，可通过配置文件开启关闭
- [新增] 新增顶部栏主题跟随，可通过配置文件 autoHead 属性开启
- [优化] 将主题代码独立于 admin.js, 命名为 theme.js 模块，保证单一职责
- [优化] 配置文件兼容 yml / json 两种格式，通过 admin.setConfigType 切换解析方式
- [修复] 修复 iusses 部分提交问题
- [修复] 修复 area 组件多实例隔离问题

> 升级替换 component / pear 目录即可

#### 2020-12-06 （ 3.3.0.release ）   :id=log_315

- [增加] 新增 yaml.js 解析组件，提供 yml 文件解析支持
- [增加] 新增 http.js 异步请求模块，扩展原生 jquery.ajax 异常捕捉
- [优化] 替换 json 配置文件为 yaml, 增加可读性
- [优化] 调整 admin.css 部分样式
- [优化] 升级 Layui 依赖至 2.5.7 版本
- [修复] 修复 PE 登陆页面，验证码错位问题

> 升级替换 component / pear 目录即可

#### 2020-11-11 （ 3.2.5.release ）   :id=log_316

- [新增] 新增 context 组件，用于获取上下文全局变量
- [新增] 新增 convert cropper 类型转换 与 图片裁剪 组件
- [新增] 新增头像上传示例，uploadProfile 页面
- [新增] 新增 admin.setConfigPath() 允许开发者自定义 pear.config.json 的读取路径
- [新增] 菜单同时支持 async 异步接口方式 与 静态 JSON 数据模式

> 升级替换 component / pear 目录即可

#### 2020-10-01 （ 3.1.2.release ）   :id=log_317

- [优化] 解决 树状表格 层级缩进
- [优化] 优化 light-theme 菜单主题的阴影大小
- [优化] 兼容 多系统菜单 移动端下显示效果
- [优化]  解决 Ajax 同步与浏览器 UI 渲染冲突
- [新增] 新增 基础信息 与 空白布局 页面

> 升级替换 component / pear 目录即可
