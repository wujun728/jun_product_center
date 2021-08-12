#   项目开始BEGIN
## 项目说明
基于React16.x、Ant Design4.x,react-admin
## 目录结构
```

├── config              // 项目构建配置
├── public              // 不参与构建的静态文件
├── scripts             // 构建脚本
├── src                 
│   ├── assets          // 全项目通用图片文件等
│   ├── commons         // 全项目通用js，业务相关
│   ├── components      // 全项目通用组件，业务相关
│   ├── config          // 项目构建补充配置
│   ├── layouts         // 页面框架布局组件+
│   ├── mock            // 模拟数据
│   ├── models          // 模块封装，基于redux，提供各组件共享数据、共享逻辑
│   ├── pages           // 主项目页面组件
│   ├── ├── components              // 主项目页面公共组件
│   ├── ├── ├── Header              // 主项目页面公共组件
│   ├── ├── ├── ├── index.jsx               // 主项目页面公共组件入口
│   ├── ├── ├── ├── style.less             // 主项目页面公共组件样式
│   ├── ├── style.less              // 主项目页面样式文件
│   ├── ├── index.jsx               // 主项目页面入口文件
│   ├── ├── Project                 // 子项目顶级目录
│   ├── ├── ├── demo                        // 子项目一目录
│   ├── ├── ├── ├── assets                       // 子项目一公共图片文件
│   ├── ├── ├── ├── components                   // 子项目一公共组件库
│   ├── ├── ├── ├── pages                        // 子项目一页面目录
│   ├── ├── ├── ├── ├── bussinessadmin           // 子项目一页面模块
│   ├── ├── ├── ├── ├── ├── components           // 子项目一页面模块公共组件，components里面目录结构同上
│   ├── ├── ├── ├── ├── ├── index.jsx           // 子项目一页面模块入口
│   ├── ├── ├── ├── ├── ├── style.less           // 子项目一页面模块样式
│   ├── ├── ├── ├── style                        // 子项目一样式目录
│   ├── ├── ├── ├── index.jsx                    // 子项目一入口文件
│   ├── router              // 路由
│   ├── ant.less            // 主体配置
│   ├── App.js              // 根组件
│   ├── index.css           // 全局样式 慎用
│   ├── index.dark.css       // 全局样式 慎用
│   ├── index.js            // 项目入口
│   ├── menus.js            // 菜单配置
│   ├── setupProxy.js       // 后端联调代理配置
│   └── theme.less          // 主题变量
├── package.json
├── README.md
└── yarn.lock
```
##  安装依赖
```javascript
$ yarn
```

##  开发启动
```javascript
$ yarn start

#指定端口
$ PORT=8080 yarn start

# HTTPS方式启动
$ HTTPS=true yarn start
```

##  生产构建
```javascript
$ yarn build

// 构建输入到指定目录
$ BUILD_PATH=../dist yarn build
```
##  域名子目录发布项目
```javascript
// 开发启动
$ src/commons/PRE_ROUTER.js更改前缀 yarn start

// 开发访问
'http://localhost:XXXX/前缀/'

//生产环境 同上
$ src/commons/PRE_ROUTER.js更改前缀 yarn start
// 访问
'http://xxx.com/前缀'

// 代理
$ '生产环境代理前缀请根据实际项目情况进行配置'

//路由
$ '根据目前路由router下新建路由文件，并在主路由文件引入，单独打包目录下项目直接注释引入即可（没有的路由不会进行打包），登录可直接覆盖，前提为已修改前缀等信息'
```
# 菜单配置
```javascript
//在/src/menus.js文件中配置菜单数据，前端硬编码或异步加载菜单数据。
// 菜单支持头部、左侧、头部+左侧三种布局方式,默认左侧菜单。如需放开设置，请到'src/layouts/index.jsx'放开注释
//菜单字段说明。
字段	必须	说明
key	    是	//需要唯一
parentKey	否	//用于关联父级
path	是	//菜单对应的路由地址
text	是	//菜单标题
icon	否	//菜单图标配置
url	    否	//菜单对应会打开url对应的iframe页面，如果配置了url，path将无效
target	否	//配合url使用，菜单将为a标签 <a href={url} target={target}>{text}</a>
order	否	//菜单排序，数值越大越靠前显示
type	否	//如果菜单数据中携带功能权限配置，type==='1' 为菜单，type==='2'为功能
code	否	//功能码，如果是type==='2'，会用到此字段
```
# 页面开发
```javascript
//配置组件
import React, {Component} from 'react';
import config from 'src/commons/config-hoc';

@config({
    title: '页面title',
    ajax: true,
    ...
})
export default class SomePage extend Component {
    componentDidMount() {
        this.props.ajax
            .get(...)
            .then(...)
    }
    ...
}
```
参数|类型|默认值|说明
---|---|---|---
noFrame|boolean|false|标记当前页面为不需要导航框架的页面，比如登录页，通过脚本抓取实现
noAuth|boolean|false|标记当前页面为不需要登录即可访问的页面，通过脚本抓取实现
keepAlive|boolean|-|标记当前页面内容在页面切换之后是否保持
title|boolean 或 string 或 ReactNode 或 object 或 function(props)|true|true：当前页面显示通过菜单结构自动生成的title；false：当前页面不显示title；string：自定义title；object：{text，icon} text为显示的名称，icon为图标；function(props): 返回值作为title
breadcrumbs|boolean 或 array 或 function(props)|true|true：当前页面显示通过菜单结构自动生成的面包屑；false：当前页面不显示面包屑；object：\[{icon, text, ...}\]；function(props): 返回值作为面包屑
appendBreadcrumbs|array 或 function(props)|\[\]|在当前面包屑基础上添加；function(props): 返回值作为新添加的面包屑
pageHead|boolean|-|页面头部是否显示
side|boolean|-|页面左侧是否显示
sideCollapsed|boolean|-|左侧是否收起
ajax|boolean|true|是否添加ajax高阶组件，内部可以通过this.props.ajax使用ajax API，组件卸载时，会自动打断未完成的请求
router|boolean|false|是否添加withRouter装饰器，组件内部可以使用this.props.history等API
query|boolean|false|是否添加地址查询字符串转换高阶组件，内部可以通过this.props.query访问查询字符串
connect|boolean 或 function(state)|false|是否与redux进行连接，true：只注入了this.props.action相关方法；false：不与redux进行连接；(state) => ({title: state.page.title})：将函数返回的数据注入this.props
event|boolean|false|是否添加event高阶组件，可以使用this.props.addEventListener添加dom事件，并在组件卸载时会自动清理；通过this.props.removeEventListener移出dom事件
pubSub|boolean|false|是否添加发布订阅高阶组件，可以使用this.props.subscribe(topic, (msg, data) => {...})订阅事件，并在组件卸载时，会自动取消订阅; 通过this.props.publish(topic, data)发布事件
modal|string 或 object|false|当前组件是否是modal。string: 弹框标题；object：弹框配置

注：
- `noFrame`、`noAuth`、`keepAlive` 只有配置了`path`才有效！
- config装饰器可以用于任何组件，但是`title`、`breadcrumbs`、`appendBreadcrumbs`、`pageHead`、`side`、`sideCollapsed`最好在路由对应的页面组件中使用

```javascript
//页面保持
//页面渲染一次之后会保持状态，再次跳转到此页面不会重新创建或重新渲染
开启方式：
1.  /src/models/system.js initState.keepAlive 属性修改默认值
2.  config装饰器 keepAlive属性
```
## 页面显示/隐藏事件

`config` 装饰器为组件注入了两个事件 `onComponentWillShow`、`onComponentWillHide` ，如果页面使用了 Keep Alive功能，切换显示/隐藏时会触发

```js
@config({
    ...
})
export default class SomePage extends React.Component {
    constructor(...props) {
        super(...props);

        this.props.onComponentWillShow(() => {
            // do some thing 
        });
        
        this.props.onComponentWillHide(() => {
            // do some thing 
        });
    }
    ...
}
```
## 页面容器PageContent
系统提供了页面的跟节点PageContent，有如下特性：

- 添加了margin padding 样式；
- 添加了footer；
- 支持页面loading；
- 自动判定是否有底部工具条FixBottom组件，为底部工具条腾出空间；

是否显示footer，默认true
```js
<PageContent footer={false}>
    ...
</PageContent>
```

显示loading，有两种方式。

1. model方式
    ```js
    this.props.action.page.showLoading();
    this.props.action.page.hideLoading();
    ```
1. props方式
    ```js
    const {loading} = this.state;
    
    <PageContent loading={loading}>
        ...
    </PageContent>
    ```
#  弹框页面开发
添加、修改等场景，往往会用到弹框，antd Modal组件使用不当会产生脏数据问题（两次弹框渲染数据互相干扰）

系统提供了基于modal封装的高阶组件，每次弹框关闭，都会销毁弹框内容，避免互相干扰
## modal高阶组件
modal高阶组件集成到了config中，也可以单独引用：`import { ModalContent } from 'src/commons/ra-lib';`

```jsx
import React from 'react';
import config from 'src/commons/config-hoc';
import { ModalContent } from 'src/commons/ra-lib';

export default config({
    modal: {
        title: '弹框标题',
    },
})(props => {
    const {onOk, onCancel} = props;

    return (
        <ModalContent
            onOk={onOk}
            onCancel={onCancel}
        >
            弹框内容
        </ModalContent>
    );
});
```
modal所有参数说明如下：

1. 如果是string，作为modal的title
1. 如果是函数，函数返回值作为 Modal参数
1. 如果是对象，为Modal相关配置，具体参考 [antd Modal组件](https://ant-design.gitee.io/components/modal-cn/#API)
1. options.fullScreen boolean 默认false，是否全屏显示弹框

## ModalContent组件
弹框内容通过 ModalContent包裹，具体参数如下：
            
参数|类型|默认值|说明
---|---|---|---
surplusSpace|boolean|false|是否使用屏幕垂直方向剩余空间 
otherHeight|number|-|除了主体内容之外的其他高度，用于计算主体高度；
loading|boolean|false|加载中
loadingTip|-|-|加载提示文案
footer|-|-|底部
okText|string|-|确定按钮文案
onOk|function|-|确定按钮事件
cancelText|string|-|取消按钮文案
onCancel|function|-|取消按钮事件
resetText|string|-|重置按钮文案
onReset|function|-|重置按钮事件
style|object|-|最外层容器样式
bodyStyle|object|-|内容容器样式

#   系统路由
系统路由使用 react-router,通过route-loader 将路由内容填充到/src/pages/page-routes.js文件，支持两种写法:

1. 常量方式
    ```js
    export const PAGE_ROUTE = '/path';
    ```
1. 页面config装饰器(推荐)
    ```js
    @config({
        path: '/path',
    })
    export default class SomePage extends React.Component {
        ...
    }
    ```
### 二级页面
    
二级页面如果要保持父级菜单的选中状态，以父级path开始并以`/_/`作为分隔符即可：`parent/path/_/child/path`

```js
// parent page 
@config({
    path: '/parent/path'
})
export default class Parent extends React.Component {
    ...
}

// child page
@config({
    path: '/parent/path/_/child/path'
})
export default class Parent extends React.Component {
    ...
}
```
# AJAX请求
系统的ajax请求基于axios封装。
基于restful规范，提供了5个方法：

- get 获取服务端数据，参数拼接在url上，以 query string 方式发送给后端
- post 新增数据，参数以body形式发送给后端
- put 修改数据，参数以body形式发送给后端
- del 删除数据，参数拼接在url上，以 query string 方式发送给后端
- patch 修改部分数据，参数以body形式发送给后端
## 调用方式 三种
```jsx
//第一种  config装饰器ajax属性（推荐）
 import React, {Component} from 'react';
    import config from 'src/commons/config-hoc';
    
    @config({
        ajax: true,
        ...
    })
    export default class SomePage extend Component {
        componentDidMount() {
            this.props.ajax
                .get(...)
                .then(...)
        }
        ...
    }
//第二种 ajax装饰器
 import React, {Component} from 'react';
    import {ajaxHoc} from 'src/commpons/ajax';

    @ajaxHoc()
    export default class SomePage extend Component {
        componentDidMount() {
            this.props.ajax
                .get(...)
                .then(...)
        }
        ...
    } 
//第三种 直接引入ajax对象    
import React, {Component} from 'react';
import {sxAjax} from 'src/commpons/ajax';
    
    export default class SomePage extend Component {
        componentDidMount() {
            sxAjax.post(...).then(...);
        
            // 组件卸载或者其他什么情况，需要打算ajax请求，可以用如下方式
            const ajax = sxAjax.get(...);
            ajax.then(...).finally(...);
            ajax.cancel();
        }
        ...
    } 
```
注：config、ajaxHoc方式做了封装，页面被卸载之后会**自动打断**未完成的请求
## 接收参数
所有的ajax方法参数统一，都能够接受三个参数：
参数|说明
---|---
url|请求地址
params|请求传递给后端的参数
options|请求配置，即axios的配置。

###   options配置
参数|说明
---|---
axios配置|可以接受axios参数
successTip|扩展的参数，成功提示
errorTip|扩展的参数，失败提示
noEmpty|扩展的参数，过滤掉 ''、null、undefined的参数，不提交给后端
originResponse|扩展参数，.then中可以拿到完整的response，而不只是response.data

注：全局默认参数可以在src/commons/ajax.js中进行配置，默认baseURL='/api'、timeout=1000 * 60。

## 请求结果提示
1.  系统对ajax失败做了自动提示，开发人员可通过src/commons/handle-error.js进行配置；
2.  成功提示默认不显示，如果需要成功提示，可以配置successTip参数，或者.then()中自行处理；
3.  成功提示在src/commons/handle-success.js中配置；
```js
this.props.ajax.del('/user/1', null, {successTip: '删除成功！', errorTip: '删除失败！', noEmpty: true});
```
## loading处理
系统扩展了promise，提供了finally方法，用于无论成功还是失败，都要进行的处理。一般用于关闭loading
```js
this.setState({loading: true});
this.props.ajax
    .get('/url')
    .then(...)
    .finally(() => this.setState({loading: false}));
```
# Mock 模拟数据
前后端并行开发，为了方便后端快速开发，不需要等待后端接口，系统提供了mock功能。基于[mockjs](http://mockjs.com/)

## 编写模拟数据
在/src/mock目录下进行mock数据编写，比如：
```js
import {getUsersByPageSize} from './mockdata/user';

export default {
    'post /mock/login': (config) => {
        const {
            userName,
            password,
        } = JSON.parse(config.data);
        return new Promise((resolve, reject) => {
            if (userName !== 'test' || password !== '111') {
                setTimeout(() => {
                    reject({
                        code: 1001,
                        message: '用户名或密码错误',
                    });
                }, 1000);
            } else {
                setTimeout(() => {
                    resolve([200, {
                        id: '1234567890abcde',
                        name: 'MOCK 用户',
                        loginName: 'MOCK 登录名',
                    }]);
                }, 1000);
            }
        });
    },
    'post /mock/logout': {},

    'get /mock/user-center': (config) => {
        const {
            pageSize,
            pageNum,
        } = config.params;


        return new Promise((resolve) => {
            setTimeout(() => {
                resolve([200, {
                    pageNum,
                    pageSize,
                    total: 888,
                    list: getUsersByPageSize(pageSize),
                }]);
            }, 1000);
        });
    },
    'get re:/mock/user-center/.+': {id: 1, name: '熊大', age: 22, job: '前端'},
    'post /mock/user-center': true,
    'put /mock/user-center': true,
    'delete re:/mock/user-center/.+': 'id',
}
```

## 简化
为了方便mock接口编写，系统提供了简化脚本(/src/mock/simplify.js)，上面的例子就是简化写法

对象的key由 method url delay，各部分组成，以空格隔开

字段|说明
---|---
method| 请求方法 get post等
url|请求的url
delay|模拟延迟，毫秒 默认1000

## 调用
系统封装的ajax可以通过以下两种方式，自动区分是mock数据，还是真实后端数据，无需其他配置

mock请求：
- url以/mock/开头的请求
- /src/mock/url-config.js中配置的请求

```js
this.props.ajax.get('/mock/users').then(...);
```
如果后端真实接口准备好之后，去掉url中的/mock即可

注：mock功能只有开发模式下开启了，生产模式不会开启mock功能，如果其他环境要开启mock 使用MOCK=true参数，比如 `MOCK=true yarn build`

# 样式
系统使用[less](http://lesscss.org/)进行样式的编写。
为了避免多人合作样式冲突，系统对src下的less文件启用了Css Module，css文件没有使用Css Module。

style.less
```less
.root{
    width: 100%;
    height: 100%;
}
```
Some.jsx
```jsx
import '/path/to/style.less';

export default class Some extends React.Component {
    render() {
        return (
            <div styleName="root"></div>            
        );
    }
}
```
注：基础组件不使用Css Module，不利于样式覆盖；
## 主题
使用less，通过样式覆盖来实现。
### 编写主题
- less文件中使用主题相关变量；
- 编写`/src/theme.less`通过[less-loader](https://github.com/webpack-contrib/less-loader)的`modifyVars`覆盖less中的变量；

注：目前每次修改了theme.less 需要重新yarn start 才能生效

### 参考
- Ant Design 主题 参考：https://ant-design.gitee.io/docs/react/customize-theme-cn

# 导航布局
为了满足不同系统的需求，提供了四种导航布局：
- 头部菜单
- 左侧菜单
- 头部+左侧菜单
- tab页方式

## 更改方式
- 用户可以通过 页面有上角用户头像 -> 设置 页面进行选择（如果您为用户提供了此页面）；
- 开发人员可以通过修改`src/models/index.js`指定布局方式；

## 不需要导航
有些页面可能不需要显示导航，可以通过如下任意一种方式进行设置：

- 页面配置高级组件
    ```js
    @config({
        noFrame: true,
    })
    ```
- 浏览器url中noFrame=true参数 
    ```
    /path/to?noFrame=true
    ```

## Tab标签页
页面头部标签，有如下特性：

1. 在当前tab标签之后打开新的tab标签；
1. 记录并恢复滚动条位置；
1. 保持页面状态（需要开启`Keep Page Alive`）；
1. tab标签右键操作；
1. tab页操作API；
1. tab标签拖拽排序；
1. 关闭一个二级页面tab，尝试打开它的父级；

### Tab操作API
system model（redux）中提供了如下操作tab页的方法：

API|说明
---|---
setCurrentTabTitle(title)|设置当前激活的 tab 标题 title: stirng 或 {text, icon}
refreshTab(targetPath)|刷新targetPath指定的tab页内容（重新渲染）
refreshAllTab()|刷新所有tab页内容（重新渲染）
closeCurrentTab()|关闭当前tab页
closeTab(targetPath)|关闭targetPath对应的tab页
closeOtherTabs(targetPath)|关闭除了targetPath对应的tab页之外的所有tab页
closeAllTabs()|关闭所有tab页，系统将跳转首页
closeLeftTabs(targetPath)|关闭targetPath对应的tab页左侧所有tab页
closeRightTabs(targetPath)|关闭targetPath对应的tab页右侧所有的tab页

使用方式：
```jsx
import config from 'src/commons/config-hoc';

@config({
    connect: true,
})
export default class SomeComponent extends React.Component {
    componentDidMount() {
        this.props.action.system.closeTab('/some/path');
    }
    ...
}
```


注：

1. tab基于页面地址，每当使用`this.props.history.push('/some/path')`，就会选中或者新打开一个tab页（`/path` 与 `/path?name=Tom`属于不同url地址，会对应两个tab页）；
1. 没有菜单对应的页面，需要单独设置title，否则tab标签将没有title;


# models(redux) 封装
基于[redux](https://redux.js.org/)进行封装，不改变redux源码，可以结合使用redux社区中其他解决方案。

注：一般情况下，用不到redux~

## models用于管理数据，解决的问题：
1. 命名空间（防止数据、方法命名冲突）：数据与方法，都归属于具体model，比如：state.userCenter.xxx，this.props.action.userCenter.xxx();
1. 如何方便的获取数据：connect与组件连接；@connect(state => ({name: state.user.name}));
1. 如何方便的修改数据：this.props.action中方法；
1. 客户端数据持久化（保存到LocalStorage中）：syncStorage配置；
1. 异步数据处理：基于promise异步封装；
1. 请求错误提示：error处理封装，errorTip配置，自动提示；
1. 请求成功提示：successTip配置，自动提示；
1. 简化写法：types actions reducers 可以在一个文件中编写，较少冲突，方便多人协作，参见[`models/page.js`](./page.js)中的写法;
1. 业务代码可集中归类：在models目录中统一编写，或者在具体业务目录中，模块化方式。


## src/models
所有的model直接在models或pages下定义：

model模块名规则：
```
/path/to/models/user-center.js --> userCenter;
/path/to/models/user.js --> user;

/path/to/pages/users/model.js --> users;
/path/to/pages/users/job.model.js --> job;
/path/to/pages/users/user-center.model.js --> userCenter;
/path/to/pages/users/user.center.model.js --> userCenter;
```

## 组件与redux进行连接
提供了多种种方式，装饰器方式、函数调用、hooks、js文件直接使用；

### 装饰器
推荐使用装饰器方式

```jsx
import {connect} from 'path/to/models';

@connect(state => {
    return {
        ...
    }
})
class Demo extends Component{
    ...
}
```

### 函数
```jsx
import {connectComponent} from 'path/to/models';

class Demo extends Component {
   ... 
}
function mapStateToProps(state) {
    return {
        ...
    };
}

export default connectComponent({LayoutComponent: Demo, mapStateToProps});
```

### hooks
```jsx
import {useSelector} from 'react-redux';
import {useAction} from 'src/models';

export default () => {
    const action = useAction();
    const show = useSelector(state => state.side.show);
    
    console.log(show);

    useEffect(() =>{
        action.side.hide()    

    }, []);

    return <div/>
}
```

**对 useSelector 的说明**：

useSelector(select) 默认对 select 函数的返回值进行引用比较 ===，并且仅在返回值改变时触发重渲染。

即：如果select函数返回一个临时对象，会多次re-render
    
    最好不要这样使用:
    const someData = useSelector(state => {
    
        // 每次都返回一个新对象，导致re-render
        return {name: state.name, age: state.age};
    })
    
    最好多次调用useSelector，单独返回数据，或者返回非引用类型数据
    const name = useSelector(state => state.firstName + state.lastName);
    const age = useSelector(state => state.age);

### js文件中使用
没有特殊需求，一般不会在普通js文件中使用

```javascript
import {action, store} from 'src/models';

// 获取数据 
const state = store.getState();

// 修改数据
action.side.hide();
```


## 简化写法
action reducer 二合一，省去了actionType，简化写法；

注意：
- 所有的reducer方法，无论是什么写法中的，都可以直接返回新数据，不必关心与原数据合并（...state），封装内部做了合并；
- 一个model中，除了initialState syncStorage actions reducers 等关键字之外的属性，都视为action reducer合并写法;

### 一个函数
一个函数，即可作为action方法，也作为reduce使用

- 调用action方法传递的数据将不会做任何处理，会直接传递给 reducer
- 只能用第一个参数接收传递过来的数据，如果多个数据，需要通过对象方式传递，如果不需要传递数据，但是要使用state，也需要定义一个参数占位
- 第二个参数固定为state，第三个参数固定为action，不需要可以缺省（一般都是缺省的）
- 函数的返回值为一个对象或者undefined，将于原state合并，作为store新的state

```js
// page.model.js
export default {
    initialState: {
        title: void 0,
        name: void 0,
        user: {},
        toggle: true,
    },
    
    setTitle: title => ({title}),
    setName: (name, state, action) => {
        const {name: prevName} = state;
        if(name !== prevName) return {name: 'Different Name'};
    },
    setUser: ({name, age} = {}) => ({user: {name, age}}),
    setToggle: (arg, state) => ({toggle: !state.toggle}),
}

// 使用
this.props.action.page.setTitle('my title');
```

### 数据同步
通过配置的方式，可以让redux中的数据自动与localStorage同步

```js
export default {
    initialState: {
        title: '',
        show: true,
        user: {},
        users: [], 
        job: {},
        total: 0,
        loading: false,
        ...
    },
    
    // initialState会全部同步到localStorage中
    // syncStorage: true,
     
    // 配置部分存数据储到localStorage中 
    syncStorage: { 
        titel: true,
        user: { // 支持对象指定字段，任意层次
            name: true,
            address: {
                city: true,
            },
        },
        job: true,
        users: [{name: true, age: true}], // 支持数组
    },
}
```

### action reducer 合并写法
如果action有额外的数据处理，并且一个action 只对应一个reducer，这种写法不需要指定actionType，可以有效简化代码；

```js
export default {
    initialState: {
        title: '',
        ...
    },
    
    arDemo: {
        // 如果是函数返回值将作为action.payload 传递给reducer，如果非函数，直接将payload的值，作为action.payload;
        payload(options) {...},
        
        // 如果是函数返回值将作为action.meta 传递给reducer，如果非函数，直接将meta的值，作为action.meta;
        meta(options) {...},
        reducer(state, action) {
            returtn {...newState}; // 可以直接返回要修改的数据，内部封装会与原state合并`{...state, ...newState}`;
        },
    },
};
```

### 异步action写法

```js

export default {
    initialState: {
        title: '',
        ...
    },
    fetchUser: {
        // 异步action payload 返回promise     
        payload: ({params, options}) => axios.get('/mock/users', params, options),
        
        // 异步action 默认使用通用异步meta配置 commonAsyncMeta，对successTip errorTip onResolve onReject onComplete 进行了合理的默认值处理，需要action以对象形式传参调用
        // meta: commonAsyncMeta, 
        // meta: {
        //     successTip: '查询成功！欧耶~',
        //     errorTip: '自定义errorTip！马丹~',
        // },
        // meta: () => {
        //    return {...};
        // },
        
        // 基于promise 异步reducer写法；
        reducer: {
            pending: (state, action) => ({loading: true}),
            resolve(state, {payload = {}}) {
                const {total = 0, list = []} = payload;
                return {
                    users: list,
                    total,
                }
            },
            complete: (state, action) => ({loading: false}),
        }
    },
};
```
调用方式：
```js
this.props.action.user
    .fetchUser({
        params, 
        options, 
        successTip, 
        errorTip,
        onResolve, 
        onReject, 
        onComplete
    });
```

参数约定为一个对象，各个属性说明如下:

参数|说明
---|---
params|请求参数
options|请求配置
successTip|成功提示信息
errorTip|错误提示信息
onResolve|成功回调
onReject|失败回调
onComplete|完成回调，无论成功、失败都会调用

### 单独定义action 和 reducer
支持这种比较传统的写法，一般也不会太用到

```js
import {createAction} from 'redux-actions';

export const types = {
    GET_MENU_STATUS: 'MENU:GET_MENU_STATUS', // 防止各个模块冲突，最好模块名开头
};

export default {
    initialState: {
        title: '',
        ...
    },
    
    // 单独action定义，需要使用actionType与reducer进行关联
    actions: {
        getMenuStatus: createAction(types.GET_MENU_STATUS),
    },
    
    // 单独reducer定义，使用了actionType，不仅可以处理当前model中的action
    // 也可以处理其他任意action（只要actionType能对应）
    reducers: {
        [types.GET_MENU_STATUS](state) {
            ...
            return {
                ...
            };
        }
    },
}    
```
# 权限控制
系统菜单、具体功能点都可以进行权限控制。

## 菜单权限
菜单由后端提供（一般系统都是后端提供），后台通过登录用户返回用户的菜单权限；页面只显示获取到的菜单；

系统提供了一个基础的菜单、权限管理页面，需要后端配合存储数据。

## 功能权限
可以通过`src/components/permission`组件对功能的权限进行控制
```js
import React, {Component} from 'react';
import Permission from 'src/components/permission';

export default class SomePage extends Component {

    render() {
        return (
            <div>
                <Permission code="USER_ADD">
                    <Button>添加用户</Button>
                </Permission>
            </div>
        );
    }
}
```
注：权限的code前端使用时会硬编码，注意语义化、唯一性。

## 角色
一般系统都会提供角色管理功能，系统中提供了一个基础的角色管理功能，稍作修改即可使用。


# 开发代理
开发时，要与后端进行接口对接，可以通过代理与后端进行连接，开发代理配置在`src/setupProxy.js`中编写

```js
const proxy = require('http-proxy-middleware');

const prefix = process.env.AJAX_PREFIX || '/api';

module.exports = function (app) {
    app.use(proxy(prefix,
        {
            target: 'http://localhost:3000/',
            pathRewrite: {
                ['^' + prefix]: '', // 如果后端接口无前缀，可以通过这种方式去掉
            },
            changeOrigin: true,
            secure: false, // 是否验证证书
            ws: true, // 启用websocket
        },
    ));
};

```

注：更多代理配置请参考[http-proxy-middleware](https://github.com/chimurai/http-proxy-middleware)


前端默认ajax前缀 /api 可以通过 AJAX_PREFIX 参数进行修改。

# nginx配置参考
这里只是参考文件，根据自己的项目需求自行配置

## 一个域名对应单个项目
### 目录结构
```
.
├── /usr/local/nginx/html                 
│   ├── static
│   ├── index.html
│   └── favicon.ico
```

### nginx 配置
```nginx
# 后端服务地址
upstream api_service {
  server xxx.xxx.xxx.xxx:xxxx;
  keepalive 2000;
}

server {
    listen      80;
    server_name www.xxxx.com xxxx.com; # 域名地址
    root        /usr/local/nginx/html; # 前端静态文件目录
    location / {
      index index.html;
      try_files $uri $uri/ /index.html; #react-router 防止页面刷新出现404
    }

    # 静态文件缓存，启用Cache-Control: max-age、Expires
    location ~ ^/static/(css|js|media)/ {
      expires 10y;
      access_log off;
      add_header Cache-Control "public";
    }

     # 代理ajax请求 前端ajax请求以 /api 开头
    location ^~/api {
       rewrite ^/api/(.*)$ /$1 break; # 如果后端接口不是统一以api开头，去掉api前缀
       proxy_pass http://api_service/;
       proxy_set_header Host  $http_host;
       proxy_set_header Connection close;
       proxy_set_header X-Real-IP $remote_addr;
       proxy_set_header X-Forwarded-Server $host;
    }
}
```

## 一个域名对应多个项目
多个项目挂载到同一个域名下，可以通过子目录方式区分

比如，如下地址各对应一个项目

- http://xxxx.com
- http://xxxx.com/project1
- http://xxxx.com/project2

前端项目构建时，添加BASE_NAME PUBLIC_URL参数
```bash
BASE_NAME=/project1 PUBLIC_URL=/project1 yarn build
```
### nginx 静态文件目录结构
```
.
├── /home/ubuntu/front-springrain                 
│   ├── build   // 主项目 静态文件目录
│   │   ├── static
│   │   ├── index.html
│   │   └── favicon.ico
│   ├── project1   // 子项目静态目录 名称与 location /project1 location ~ ^/project1/static/.*  配置对应
│   │   ├── static
│   │   ├── index.html
│   │   └── favicon.ico
```

### nginx 配置
```nginx
upstream api_service {
  server xxx.xxx.xxx.xxx:xxxx;
  keepalive 2000;
}

upstream api_service_project1 {
  server xxx.xxx.xxx.xxx:xxxx;
  keepalive 2000;
}
server {
    listen 80;
    server_name www.xxxx.com xxxx.com; # 域名地址
    # Allow file uploads
    client_max_body_size 100M;

    # 主项目配置，访问地址 http://www.xxxx.com
    location / {
        root /home/ubuntu/front-springrain/build;
        index index.html;
        try_files $uri $uri/ /index.html;
    }
    # 静态文件缓存，启用Cache-Control: max-age、Expires
    location ~ ^/static/.* {
        root /home/ubuntu/front-springrain/build;
        expires 20y;
        access_log off;
        add_header Cache-Control "public";
    }
    # 代理ajax请求 前端ajax请求以/api开头
    location ^~/api {
       rewrite ^/api/(.*)$ /$1 break; # 如果后端接口不是统一以api开头，去掉api前缀
       proxy_pass http://api_service/;
       proxy_set_header Host  $http_host;
       proxy_set_header Connection close;
       proxy_set_header X-Real-IP $remote_addr;
       proxy_set_header X-Forwarded-Server $host;
    }

    # 子项目配置 访问地址 http://www.xxxx.com/project1
    location /project1 {
        root /home/ubuntu/front-springrain;
        index index.html;
        try_files $uri $uri/ /project1/index.html;
    }
    # 静态文件缓存，启用Cache-Control: max-age、Expires
    location ~ ^/project1/static/.* {
        root /home/ubuntu/front-springrain;
        expires 10y;
        access_log off;
        add_header Cache-Control "public";
    }
    # 代理ajax请求 前端ajax请求以 /project1_api 开头
    location ^~/project1_api {
       rewrite ^/api/(.*)$ /$1 break; # 如果后端接口不是统一以api开头，去掉api前缀
       proxy_pass http://api_service_project1/;
       proxy_set_header Host  $http_host;
       proxy_set_header Connection close;
       proxy_set_header X-Real-IP $remote_addr;
       proxy_set_header X-Forwarded-Server $host;
    }
}
```
#  其他
## 页面打印
通过给元素添加相应的class，控制打印内容：

- `.just-print` 只在打印时显示
- `.no-print` 在打印时不显示 
## ESLint 说明
如果前端项目，不是git根目录，在提交的时候，会报错 `Not a git repository`

修改package.json，lint-staged 如下即可
```json
"lint-staged": {
    "gitDir": "../",
    "linters": {
        "**/*.{js,jsx}": "lint-staged:js",
        "**/*.less": "stylelint --syntax less"
    }
},
```
## Webpack 
### 使用了alias {'@': '/path/to/src', src:'/path/to/src'}
- config/webpack.config.js
- 方便路径书写，不必关心相对路径结构
- 复制粘贴到其他文件，不必修改路径

## 支持判断运算符
```js
const name = res?.data?.user?.name || '匿名';
```
## form表单  
1.  FormElement:类型有：
```
'input', 'hidden', 'number', 'textarea', 'password', 'mobile', 'email', 'select', 'select-tree', 'checkbox', 'checkbox-group', 'radio', 'radio-button', 'radio-group', 'switch', 'date', 'time', 'date-time', 'date-range', 'cascader', 'transfer', 'icon-picker'
```



