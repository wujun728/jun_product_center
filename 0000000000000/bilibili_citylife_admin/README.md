# 同城小活动笔记

## 项目需求:

制作一个同城小活动信息发布共享平台,可以及时地了解到各种新鲜的有趣的同城小活动,也可以发布自己了解的小活动,搭建一个互相交流的平台小程序.

信息发布,评论,信息展示,点赞,踩,收藏,积分等级

## 涉及技术:

1. 若依前后端分离框架 
2. springboot
3. springsecurity(jwt)
4. mybatisplus
5. redis
6. Vue.js
7. uniapp(uview)
8. 微信登录逻辑






### NoClassDefFoundError报错,在admin里面的pom.xml加入以下依赖

```xml
<!--        NoClassDefFoundError: javax/xml/bind/DatatypeConverter-->
<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-impl</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-core</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>javax.activation</groupId>
    <artifactId>activation</artifactId>
    <version>1.1.1</version>
</dependency>
```



### 前端指定@为src目录 根目录新建文件 jsconfig.json,输入以下代码

```
{
  "compilerOptions": {
    "baseUrl": "./",
    "paths": {
      "@/*": ["src/*"]
    }
  },
  "exclude": ["node_modules", "dist"]
}

```



### 微信小程序登录封装

### https://github.com/binarywang/weixin-java-miniapp-demo



