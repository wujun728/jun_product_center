## 基本介绍

Notice 组件常用于 消息通知 场景

## 使用方式

#### 开发环境

```html
<link rel="stylesheet" href="component/pear/css/pear.css">
 或
<link rel="stylesheet" href="component/pear/css/pear-module/notice.css">
 并
<script src="component/layui/layui.js"></script>
 并
<script src="component/pear/pear.js"></script>
```

#### 简单使用

```javascript
layui.use(['notice', 'jquery', 'layer', 'code'], function() {
    var notice = layui.notice;
                         
    notice.success("成功消息")
    notice.error("危险消息")
    notice.warning("警告消息")
    notice.info("通用消息")
})

```

#### 消息移除

```javascript
layui.use(['notice', 'jquery', 'layer', 'code'], function() {
    var notice = layui.notice;
                         
    notice.clear();
})
```

notice.clear 提供屏幕