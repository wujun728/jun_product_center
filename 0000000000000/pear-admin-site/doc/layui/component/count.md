## 基本介绍

Count 组件对数字类型的文本提供了滚动效果

## 使用方式

#### UP 滚动

```javascript
layui.use(['count'],function(){
	const count = layui.count;
	
	count.up("#id", {
	    time: 8000,
	    num: 4540.34,
	    bit: 2,
	    regulator: 100
	})
})
```

- #id : 需要滚动的元素 # 选择
- time: 滚动时间
- num: 显示结果
- bit: 保留小数位
- regulator: 步长 与 time 属性相结合，计算实际滚动事件
