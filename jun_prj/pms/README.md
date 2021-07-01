# pms
### 基于spring boot项目管理软件。欢迎大家跟踪感兴趣的交流~~，交流群：10043620
123ttt
test1

### 开发计划：

1、增加封装服务端表单验证

系统采用spring validate验证，专门的验证类负责表单验证。个人感觉在bean中写太多的验证方法也不优雅。所有还是采用spring validate。但是目前这种方式自我感觉还是不太完美。每个controller都要增加注册校验类，
```
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.replaceValidators(new SysUserValidators());
    } 
```
  而且还要还要在验证对象前增加@ModelAttribute("testUser") @Validated。感觉太累后续希望可以改进成直接在验证对象前增加个注解搞定（后续争取搞定，也希望高人能指点）。。 