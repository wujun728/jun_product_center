## 基本介绍

Context 组件提供了对全局变量的存储功能，你可以在任何页面，存取常用变量 -- 3.2.2 新增


## 使用方式

#### 存储变量

```javascript
layui.use(['context'],function(){
	const context = layui.context;
	context.put("username","Jmys")
})
```

#### 获取变量

```javascript
layui.use(['context'],function(){
	const context = layui.context;
	var username = context.get("username")
})
```