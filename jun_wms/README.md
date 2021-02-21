# 立体仓库WMS

## 背景
deer-wms（立库版）是由南京大鹿智造科技有限公司研发，此软件已经成功使用于某制造业原料库全自动无人仓库中。
## 软件介绍
deer-wms是基于自动化输送线、机械臂、点数机、提升机、堆垛机等自动化设备和现代化仓储精益管理思想开发出来的仓库管理系统。
通过对接工厂的EBS(erp中一种)、MES（生产执行系统）、deer-wcs（设备调度系统）等系统接口，实现仓储上下游的数据打通，也是实现完全自动化的基础。

## 立体仓库软件结构
![Image text](http:////x25097913h.qicp.vip/DeerWMS/upload/images/jiagou.jpg)
## 仓库作业流程
1.  入库
![Image text](http:////x25097913h.qicp.vip/DeerWMS/upload/images/rukuliucheng.jpg)
2.  出库
![Image text](http:////x25097913h.qicp.vip/DeerWMS/upload/images/chukuliucheng.jpg)

## 软件功能
- **首页**

    - 待处理单据图形展示.
    
    - 仓库日作业统计图.
    
    - 辅助程序状态.
    
    - 数据对接列表.
    
    - 设备运行监控.

 - **仓库建模**

    - 仓库设置-子库管理-货区设置-货架设置-货位设置-出入库设置
      
    - 物料设置-容器设置

- **原料入库**

    - 通过入库工作台扫描物料外箱号，获取料号、订单号、数量、批次号等信息.
    
    - wms根据订单号向ebs请求订单信息，返回订单信息.
    
    - deer-wms向deer-wcs下发入库任务.
    
    - wcs指挥堆垛机、输送线、agv小车等设备完成入库操作.
    
    - wms库存增加并同步至ebs系统中.
    

- **生产执行**

    - deer-wms可无缝对接生产执行系统mes.
    
    - wms接收mes下发的工单，进行工单分析.
    
    - wms将工单智能转换成领料单（出库单）.
    
    - wms向wcs下发出库任务.
    
    - wcs指挥料箱车，堆垛机，agv小车，完成出库.
    
    - 出库完成，同步mes工单信息.

- **转库**
 
    - 不合格产品转库至不合格子库，同步至ebs系统.

- **可视化货位**

    - 二维货位一栏图，图形化货位展示. 

    - 颜色区分货位状态，清晰管理货位，分配资源. 

- **统计报表**    

    - 入库报表. 

    - 出库报表.

    - 转库报表.

    - 库存明细.    

    - 呆滞过期预警.      
## 项目截图
![Image text](http://x25097913h.qicp.vip/DeerWMS/upload/images/liku1.png)
![Image text](http:////x25097913h.qicp.vip/DeerWMS/upload/images/liku2.png)
![Image text](http:////x25097913h.qicp.vip/DeerWMS/upload/images/liku3.png)
![Image text](http:////x25097913h.qicp.vip/DeerWMS/upload/images/liku4.png)

#### 软件架构
注：本系统是基于码云的开源项目：若依/RuoYi 进行二次开发，感谢作者，如有需要请移步 https://gitee.com/y_project/RuoYi

#### 使用说明

1.  软件代码结构：依托诺依框架二次开发.
2.  使用注意：因为制造型企业仓储追溯性的原因，每隔一段时间需要输入：123456，点击回车，可继续使用.
3.  使用注意：部分界面需要输入工号才可使用，工号为：0000000000（十个0）.
4.  其他说明：因为是用于无人仓库，所以操作界面比较少，很多逻辑在后台处理（像盘点等功能）.

#### 其他产品，点击跳转
 - 大鹿智造deer-erp(物资管理系统-制造业erp系统):http://47.110.232.83:3302/DeerERP/back#/back/homepage/page
 - 大鹿智造官网：http://www.wmswcs.com
#### 如有疑问，请联系
郭靖勋 ：13685133739（同微信）
#### 更多仓储知识，尽在DeerWMS公众号
![Image text](http:////x25097913h.qicp.vip/DeerWMS/upload/images/qrcode_for_gh_b637f1b0adde_258.jpg)

