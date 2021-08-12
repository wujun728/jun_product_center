## 应用结构  :id=config
```应用结构
├── app                        //应用目录
│   ├── admin                  //后台应用目录
│   │   ├── controller         //控制器目录
│   │   │    ├── admin         //后台基础多级
│   │   │    ├── .....         //后台创建多级
│   │   │    ├── base.php      //通用基础文件
│   │   ├──listener            //事件目录
│   │   ├──model               //模型目录
│   │   ├──route               //路由目录  
│   │   ├──event.php           //事件定义文件
│   ├── index                  //前台目录
│   ├── install                //安装目录
│   ├── middleware             //中间件目录
│   ├── common.php             //公共函数
```

## 资源结构  :id=static
```资源结构
├── public
│   ├── static
│   │   ├── admin            //后台静态资源
│   │   │    ├── admin      //JS目录
│   │   │    │    ├── admin //基础JS目录
│   │   │    ├── css        //CSS
│   │   │    ├── images     //图片资源
│   │   │    ├── layui      //layui框架
│   │   │    ├── pear       //pear框架
```