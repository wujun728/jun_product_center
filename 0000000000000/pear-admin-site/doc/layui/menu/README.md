## 基本介绍

框架界面中的菜单 , 基于 Json 数据进行构建，你不需要去关心它是如何去构建的，只需要提供对应的数据结构即可

那么我们如何去配置菜单所需要的数据，框架中菜单的数据来源支持两种：

- 异步接口
- 静态数据

## 异步接口

```json
"menu": {
	"collaspe": false,
	"data": "admin/data/menu.json",
	"method": "GET",
	"accordion": true,
	"control": false,
	"controlWidth": 500,
	"select": "0",
	"async": false
	.....
}
```

- data : 菜单数据
- collaspe: 默认状态
- method : 请求方式 GET / POST
- accordion : 是否开启菜单手风琴
- control : 菜单模式
- controlWidth : 多系统模式下，顶部菜单宽度
- select : 默认选中菜单项 (ID)
- async: 渲染模式 true 接口方式 false 静态数据模式

## 静态数据

```json
"menu": {
	"data": [{
			"id": 10,
			"title": "控制后台",
			"icon": "layui-icon layui-icon-console",
			"type": 1,
			"openType": "_iframe",
			"href": "view/console/console1.html"
		}],
	"accordion": true,
	"control": false,
	"select": "0",
	"async": false
	.....
}
```

- data : 菜单数据
- accordion : 是否开启菜单手风琴
- control : 菜单模式
- select : 默认选中菜单项 (ID)
- async: 渲染模式 true 接口方式 false 静态数据模式

> 备注：`async` 配置为 true 时，data 属性设置为异步地址，反之为静态数据。

## 数据结构

在 异步接口 与 静态数据的情况下，我们所需要的数据结构均为如下, 即一个标准的 JSON 数据结构

```json
[{
		"id": 1,
		"title": "工作空间",
		"type": 0,
		"icon": "layui-icon layui-icon-console",
		"href": "",
		"children": [{
			"id": 10,
			"title": "控制后台",
			"icon": "layui-icon layui-icon-console",
			"type": 1,
			"openType": "_iframe",
			"href": "view/console/console1.html"
		}, {
			"id": 13,
			"title": "数据分析",
			"icon": "layui-icon layui-icon-console",
			"type": 1,
			"openType": "_iframe",
			"href": "view/console/console2.html"
		}, {
			"id": 14,
			"title": "百度一下",
			"icon": "layui-icon layui-icon-console",
			"type": 1,
			"openType": "_iframe",
			"href": "http://www.baidu.com"
		}]
	}]
```

- id: 菜单数据的唯一标识
- title: 界面中所显示的菜单标题
- icon: 图标
- type : 菜单类型 0: 目录  1: 菜单
- openType: 当 type 为 1 时，openType 生效，_iframe 正常打开 _blank 新建浏览器标签页
- href: 菜单类型下访问的页面
- children: 目录类型下，该目录下菜单的数组数据





