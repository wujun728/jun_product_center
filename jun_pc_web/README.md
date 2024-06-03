# 📐 勾股Admin Layui
![输入图片说明](https://admin.gougucms.com/assets/gougu/images/gouguadmin.jpg)
### 📋 项目介绍
勾股Admin是一款开基于Layui最新版扩展的Web UI解决方案。封装了Layui的自身调用方法和一些常用的工具函数，整合部分第三方开源的组件。更多是为服务端程序员量身定做，为使用者提供相对完善的前端UI开发方案。

预览演示地址：https://admin.gougucms.com
开发交流QQ群：24641076

### ⭕ 开源项目
1. [开源项目系列：勾股OA —— OA协同办公系统](https://gitee.com/gougucms/office)
2. [开源项目系列：勾股CMS —— CMS内容管理系统框架](https://gitee.com/gougucms/gougucms)
3. [开源项目系列：勾股BLOG —— 个人&工作室博客系统](https://gitee.com/gougucms/blog)
4. [开源项目系列：勾股DEV —— 项目研发管理系统](https://gitee.com/gougucms/dev)
5. [开源项目系列：勾股Admin —— 基于Layui的Web UI解决方案](https://gitee.com/gouguopen/guoguadmin)

### 📚 安装使用
只需引用一个CSS文件和两个JS文件，声明需要加载的模块，然后初始化操作即可。
1.  引用CSS文件，放在页头。
```html
<link rel="stylesheet" href="/assets/gougu/css/gougu.css">
```
PS：如果是布局母模板需要引入 layout.css。
```html
<link rel="stylesheet" href="/assets/gougu/css/layout.css">
```
2.  声明需要加载的模块，并完成初始化元素操作函数等，放在页脚。
```javascript
//声明需要加载的模块
const moduleInit = ['tool', 'admin'];

//初始化操作
function gouguInit() {
	//do something here
}
```
3.  引用JS文件，放在页面的最下面。
```html
<script src="/assets/layui/layui.js"></script>
<script src="/assets/gougu/gouguInit.js"></script>
```
PS：如果是项目使用的是模板继承方式开发的话，把引入文件固定写在母模板，然后只需要在子模板声明需要加载的模块，并完成初始化元素操作函数即可。

### ✳️ 目录结构
```
┌── assets					# 静态资源
│   ├── gougu				# 核心文件
│	│	├── images			# 图片文件
│	│	├── module			# 封装或第三方组件
│	│	├── css			    # 样式
│	│	├── gouguInit.js	# Lyui调用文件方法
│	├── icon				# icon文件
│	├── layui				# layui文件
│	├── third_party			# 第三方插件文件
├── json					# 页面展示模拟数据Json格式
├── view					# 页面文件
│	├── base				# 基础组件页面
│	├── form				# 表单组件页面
│	├── home				# 推荐效果页面
│	├── table				# 数据表组件页面
├── index.html				# 入口文件

```

**PS：项目会不定时进行更新，建议⭐star⭐和👁️watch👁️一份。**

### ⭐ 参与贡献步骤

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request
