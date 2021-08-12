### 权限管理 :id=authorize

使用装饰器 @authorize时需要注意，该装饰器需要写在	@app.route	之后

```python
@authorize(power: str, log: bool)
```

第一个参数为权限 code

第二个参数为是否生成日志

```python
# 例如
@authorize("admin:power:remove", log=True)
```

