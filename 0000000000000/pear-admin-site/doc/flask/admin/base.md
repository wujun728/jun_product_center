## model序列化 :id=Schema

- sqlalchemy查询的model对象转dict

  
```
 model_to_dicts(Schema, model)
```

Schema 是  序列化类,我把他放在了models文件里，觉得没有必要见一个文件夹叫Schema，也方便看着模型写序列化类

```python
# 例如
class DeptSchema(ma.Schema):  # 序列化类
    deptId = fields.Integer(attribute="id")
    parentId = fields.Integer(attribute="parent_id")
    deptName = fields.Str(attribute="dept_name")
    leader = fields.Str()
    phone = fields.Str()
    email = fields.Str()
    address = fields.Str()
    status = fields.Str()
    sort = fields.Str()
```

>这一部分有问题的话请看marshmallow文档

model写的是查询后的对象

```python
dept = Dept.query.order_by(Dept.sort).all()
```

进行序列化

```
res = model_to_dicts(Schema=DeptSchema, model=dept)
```

