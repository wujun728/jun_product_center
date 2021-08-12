扫码加微信（微信ID：**a_java_boy3**），进 Spring Boot 讨论群。

![微信ID：a_java_boy3](http://img.itboyhub.com/2020/ajavaboy3-a.jpeg)

松哥最近正在录制新版的 Spring Boot 视频教程，讲到 Spring Boot 整合 Freemarker 时，我就在琢磨单纯讲整合和 Freemarker 用法太过于枯燥，如果能有个实际案例来展示这一技术点，就会好很多。

想来想去感觉做一个 MyBatis 逆向工程比较靠谱，一来 MyBatis 逆向工程大家在日常开发中可能多多少少都会用到，二来很多小伙伴们用 Freemarker 都是做页面模版，可能还没尝试过用它做代码模版。并且从技术上来说，这个代码模版并不难，而且比较有意思。

我们先来看一张效果图：

![](http://img.itboyhub.com/2020/20201222092719.png)

项目地址：

- GitHub：https://github.com/lenve/generate_code
- Gitee：https://gitee.com/lenve/generate_code

## 技术架构

上图中的页面是用 Vue 开发的，因为只有一个页面，所以就没有使用 SPA（单页面）的形式，因为比较折腾，前端 UI 控件使用了 ElementUI，网络请求还是 Axios。

服务端当然就是大家所熟知的 Spring Boot，服务端的操作其实并不难，主要是利用 JDBC 获取数据库中的各种元数据信息。

页面模版则是 Freemarker：

![](http://img.itboyhub.com/2020/20201222093230.png)

## 使用步骤

1. clone 项目到本地：`git clone https://github.com/lenve/generate_code.git`
2. 使用 IntelliJ IDEA/Eclipse 等工具打开项目并启动。
3. 项目启动成功后，输入 http://localhost:8080/ 访问首页。
4. 首先输入数据库信息，然后点击**连接数据库**按钮。
5. 当数据库信息读取成功后，输入要生成代码的包名，然后点击**配置**按钮。
6. 接下来表格中会展示出所有的表信息和生成的各种类名称，如需修改，可以直接修改，修改完成后，点击生成代码。
7. 代码生成后，会返回代码生成的位置，点击查看即可。

## 手把手教程

松哥为这个代码生成工具录制了一套视频教程，手把手教大家做这样一个工具，今天我们先来看前三个学习下，看看是不是真的 so easy！

[项目视频](https://mp.weixin.qq.com/s/2Ovqwn1hd40D7FtKE60HKw)

好啦，今天就先和大家分享这么多。如果小伙伴们觉得视频风格还不错，也可以看看松哥的 [Spring Boot+Vue+微人事视频教程](https://mp.weixin.qq.com/s/aIyP77WrrswWNXaueBXj7w)，这套视频中的 Spring Boot 部分新版目前正在录制。