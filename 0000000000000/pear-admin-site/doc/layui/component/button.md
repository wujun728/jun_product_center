## 基本介绍

Pear Button 参考 Element UI 样式 ，提供 Button 组件


## 使用方式

#### 开发环境 

```html
<link rel="stylesheet" href="component/pear/css/pear.css">
或
<link rel="stylesheet" href="component/pear/css/pear-module/button.css">
```

#### 简单使用

```html
<button class="pear-btn"> Default Button </button>
<button class="pear-btn" dashed=""> Dashed Button </button>
<button class="pear-btn pear-btn-primary"> Primary Button </button>
<button class="pear-btn pear-btn-primary"> Button-Primary </button>
<button class="pear-btn pear-btn-success"> Button-Success </button>
```

#### 按钮大小

```html
<button class="pear-btn pear-btn-primary pear-btn-lg"> Button-Lg </button>  
<button class="pear-btn pear-btn-primary"> Button-Default </button>  
<button class="pear-btn pear-btn-primary pear-btn-sm"> Button-Sm </button>  
<button class="pear-btn pear-btn-primary pear-btn-xs"> Button-Xs </button>  
```

#### 按钮组

```html
<div class="pear-btn-group">
    <button class="pear-btn"> Button-One </button>
    <button class="pear-btn"> Button-Two </button>
    <button class="pear-btn"> Button-Three </button>
</div> 
```

#### 加载按钮

使用 `time` 属性设置按钮加载时长，`done` 为加载完成后的回调函数

```html
<button class="pear-btn" load> Default Button </button>
```

```javascript
layui.use(["button"], function() {
    var button = layui.button;
    
	button.load({
        elem:'[load]',
        time: 600,
        done: function(){
			popup.success("加载完成");
        }
    })
})
```

不存在 `time` 属性时，加载将一直执行，`load` 函数返回值对象提供 `stop` 函数，用于手动停止加载

```javascript
layui.use(["button"], function() {
    var button = layui.button;
    
	var dom = button.load({
        elem:'[load]',
    })
	
    dom.stop(function() {
        popup.failure("已停止");
    });
})
```
