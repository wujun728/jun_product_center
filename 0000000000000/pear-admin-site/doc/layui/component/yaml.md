## 基本介绍

yaml.js 组件提供了对 yml 文件解析的支持

## 使用方式

#### 解析 yaml 字符串

```javascript
layui.use(['yaml'],function(){
	
	const yaml = layui.yaml;
	const result = yaml.parse(".....");
})
```
#### 解析 yaml 文件

```javascript
layui.use(['yaml'],function(){
	
	const yaml = layui.yaml;
	const result = yaml.load("pear.config.yml");
})
```