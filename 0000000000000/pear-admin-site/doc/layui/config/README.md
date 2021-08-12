## 配置文件

Pear.config.yml 框架配置文件，通过配置文件的修改，渲染出适合你的界面布局

admin.reander() 函数在初始化时会读取 pear.config.yml 配置文件构建框架

> 默认读取 index.html 同级目录下的 `pear.config.yml` 。

那么我们如何去自定义配置文件的读取路径

举例，我们在 index.html 同级目录下创建 config 目录，然后将配置文件存放到该目录中

此时你只需要在 index.html ，admin.render() 之前去设置全局配置即可，代码如下

```javascript

admin.setConfigType("yml");
admin.setConfigPath("config/pear.config.yml");
admin.render();

```

setConfigPath(..) 用于自定义配置文件的存放位置

setConfigType(..) 用于自定义配置文件的类型 [yml/json]


接下来，我们来看配置文件的配置信息都有哪些


## 基础配置

```yaml
logo: 
  title: "Pear Admin"
  image: "admin/images/logo.png"
}
```

- title : 网站名称
- image : 网站图标


## 侧边菜单

```yaml
menu: 
  collaspe: false
  data: "admin/data/menu.json"
  method: "GET"
  accordion: true
  control: false
  controlWidth: 500
  select: "10"
  async: true
}
```

- collaspe: 侧边的默认状态
- data : 菜单数据
- method: 请求方式 GET / POST
- accordion : 是否开启菜单手风琴
- control : 菜单模式 false 为常规菜单，true 为多系统菜单
- controlWidth : 顶部菜单的宽度，单位 Px 
- select : 默认选中菜单项 (ID)
- async: 渲染模式，true 为异步接口的方式, false 为静态数据

## 多选项卡

```yaml
tab: 
  muiltTab: true
  keepState: true
  session: true
  tabMax: "30"
  index: 
    id: "10" 
    href: "view/console/console1.html" 
    title: "首页" 
}
```

- muiltTab : 是否开启多标签页
- keepState : 选项卡切换时，是否刷新页面
- tabMax : 最大打开标签页数量
- index: 主页初始化数据
- session: 存储记忆，刷新浏览器时是否保持打开 Tab

## 主题配置

```yaml
theme: 
  defaultColor: "2"
  defaultMenu: "dark-theme"
  allowCustom: true
}
```

- defaultColor : 默认主题
- defaultMenu : 菜单默认颜色 (dark-theme / light-theme)
- allowCustom : 是否允许用户自行切换主题，为 false 时，缓存主题失效，强制使用配置主题

```yaml
colors: 
- id: "1"
  color: "#FF5722"
- id: "2"
  color: "#5FB878"
- id: "3"
  color: "#1E9FFF"
- id: "4"
  color: "#FFB800"
- id: "5"
  color: "darkgray"
```

- id : 主题编号
- color: 主题颜色

## 其他配置

```json
other: 
  keepLoad: "1200"
```
- keepLoad : 主页动画加载时长
