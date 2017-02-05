# zheng
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/shuzheng/zheng/pulls)
[![GitHub forks](https://img.shields.io/github/forks/shuzheng/zheng.svg?style=social&label=Fork)](https://github.com/shuzheng/zheng)

交流QQ群：133107819 (群内含各种工具和文档下载)

文档：[https://shuzheng.gitbooks.io/zheng/content/](https://shuzheng.gitbooks.io/zheng/content/ "文档")

## 前言

　　`zheng`项目创建于2016年10月4日。

## 项目介绍

基于Spring+SpringMVC+Mybatis分布式敏捷开发系统架构：内容管理系统（门户、博客、论坛、问答等）、统一支付中心（微信、支付宝、在线网银等）、用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）、微信管理系统、第三方登录系统、会员系统、存储系统等

### 组织结构

``` lua
zheng
├── zheng-common -- SSM框架公共模块
├── zheng-admin -- 后台管理系统模板（基于bootstrap实现的响应式Material Design风格的通用后台管理系统模板）
├── zheng-upms -- 用户权限管理系统（网关）
|    ├── zheng-upms-dao -- MyBatisGenerator代码生成模块，无需开发
|    ├── zheng-upms-client -- 集成upms依赖包，提供单点认证、授权、会话管理
|    ├── zheng-upms-rpc-api -- rpc接口包
|    ├── zheng-upms-rpc-service -- rpc服务提供者
|    └── zheng-upms-server -- 系统及SSO服务端[端口:1111]
├── zheng-cms -- 内容管理系统
|    ├── zheng-cms-dao -- MyBatisGenerator代码生成模块，无需开发
|    ├── zheng-cms-rpc-api -- rpc接口包
|    ├── zheng-cms-rpc-service -- rpc服务提供者
|    ├── zheng-cms-search -- 搜索服务[端口:2221]
|    ├── zheng-cms-admin -- 后台管理[端口:2222]
|    ├── zheng-cms-job -- 消息队列、任务调度等[端口:2223]
|    └── zheng-cms-web -- 网站前台[端口:2224]
├── zheng-pay -- 支付系统
|    ├── zheng-pay-dao -- MyBatisGenerator代码生成模块，无需开发
|    ├── zheng-pay-service -- 业务逻辑
|    ├── zheng-pay-sdk -- 开发工具包
|    ├── zheng-pay-admin -- 后台管理[端口:3331]
|    └── zheng-pay-web -- 演示示例[端口:3332]
├── zheng-ucenter -- 用户系统(包括第三方登录)
|    ├── zheng-ucenter-dao -- MyBatisGenerator代码生成模块，无需开发
|    ├── zheng-ucenter-service -- 业务逻辑
|    └── zheng-ucenter-home -- 网站前台[端口:4441]
|── zheng-wechat-mp -- 微信公众号管理系统
|    ├── zheng-wechat-mp-dao -- MyBatisGenerator代码生成模块，无需开发
|    ├── zheng-wechat-mp-service -- 业务逻辑
|    └── zheng-wechat-mp-admin -- 后台管理[端口:5551]
├── zheng-api -- 接口系统
|    ├── zheng-api-sdk -- 开发工具包
|    ├── zheng-api-doc -- 接口文档项目
|    └── zheng-api-example -- 演示示例[端口:6661]
└── zheng-oss -- 对象存储系统
     ├── zheng-oss-sdk -- 开发工具包
     └── zheng-oss-web -- 管理界面[端口:7771]
```

### 技术选型

#### 后端技术:
- Spring Framework
- SpringMVC: MVC框架
- Spring secutity|Shiro: 安全框架
- Spring session: 分布式Session管理
- MyBatis: ORM框架
- MyBatis Generator: 代码生成
- Druid: 数据库连接池
- Jsp|Velocity|Thymeleaf: 模板引擎
- ZooKeeper: 协调服务
- Dubbo: 分布式服务框架
- TBSchedule|elastic-job: 分布式调度框架
- Redis: 分布式缓存数据库
- Quartz: 作业调度框架
- Ehcache: 缓存框架
- ActiveMQ: 消息队列
- Solr|Elasticsearch: 分布式全文搜索引擎
- FastDFS: 分布式文件系统
- Log4J: 日志管理
- Swagger2: 接口文档
- sequence: 分布式高效ID生产 [http://git.oschina.net/yu120/sequence](http://git.oschina.net/yu120/sequence "sequence")
- AliOSS|Qiniu: 云存储
- Protobuf|json: 数据传输 
- Jenkins: 持续集成工具
- Maven|Gradle: 项目构建管理

#### 前端技术:
- jQuery
- Bootstrap
- jQuery EasyUI
- AngularJs
- zhengAdmin [基于bootstrap实现的响应式Material Design风格的通用后台管理系统](https://github.com/shuzheng/zhengAdmin "zhengAdmin")
- autoMail [邮箱地址自动补全插件](https://github.com/shuzheng/autoMail "autoMail")
- zheng.jprogress.js [一款模仿youtube加载进度条插件](https://github.com/shuzheng/zheng.jprogress.js "zheng.jprogress.js")
- zheng.jtotop.js [返回顶部插件(可以任意速度滑动到指定任意位置)](https://github.com/shuzheng/zheng.jtotop.js "zheng.jtotop.js")

#### 模块依赖
![模块依赖](project-bootstrap/project.png)

#### 模块介绍

> zheng-common

Spring+SpringMVC+Mybatis框架集成公共模块，包括公共配置、MybatisGenerator扩展插件、通用BaseService、工具类等。

> zheng-admin

基于bootstrap实现的响应式Material Design风格的通用后台管理系统，`zheng`项目所有系统都是使用该模块界面作为前端展示。

> zheng-upms

本系统是基于RBAC授权和基于用户授权的细粒度权限控制通用平台，并提供单点登录、会话管理和日志管理。接入的系统可自由定义组织、角色、权限、资源等。

**系统功能概述：**

- 系统组织管理：系统和组织增加、删除、修改、查询功能。
- 用户角色管理：用户和角色增加、删除、修改、查询功能。
- 资源权限管理：资源和权限增加、删除、修改、查询功能。
- 权限分配管理：提供给角色和用户的权限增加、删除、修改、查询功能。
- 单点登录(SSO)：提供统一用户单点登录认证、用户鉴权功能。
- 用户会话管理：提供分布式用户会话管理
- 操作日志管理：提供记录用户登录、操作等日志。

> zheng-oss

文件存储系统，提供三种方案：

- **阿里云** 对象存储OSS
- **腾讯云** 对象存储COS
- **七牛云** 对象存储

> zheng-api

接口系统，包括开发加密接口、接口文档等对外开放服务。

> zheng-cms

内容管理系统：支持多标签、多类目、强大评论的内容管理，有基本单页展示，菜单管理，系统设置等功能。

> zheng-pay

一站式支付解决方案，统一下单接口，支持支付宝、微信、网银等多种支付方式。不涉及业务的纯粹的支付平台。

**功能介绍：**

- 统一下单（统一下单接口、统一扫码）、订单管理、数据分析、财务报表、商户管理、渠道管理、对账系统、系统监控

> zheng-ucenter

通用用户管理系统， 实现最常用的用户注册、登录、资料管理、个人中心、第三方登录等基本需求，支持扩展二次开发。

> zheng-wechat-mp

微信公众号管理平台，除实现官网后台自动回复、菜单管理、素材管理、用户管理、消息群发等基础功能外，还有二维码推广、营销活动、微网站、会员卡、优惠券等

## 环境搭建

#### 开发工具:
- MySql: 数据库
- jetty: 开发服务器
- Tomcat: 应用服务器
- SVN|Git: 版本管理
- Nginx: 反向代理服务器
- Varnish: HTTP加速器
- IntelliJ IDEA: 开发IDE
- PowerDesigner: 建模工具
- Navicat for MySQL: 数据库客户端

#### 开发环境：

- Jdk7
- Mysql5.5
- Redis
- Zookeeper
- ActiveMQ
- Dubbo-admin

### 工具安装

[环境搭建和系统部署文档(作者：小兵)](http://git.oschina.net/shuzheng/zheng/attach_files "环境搭建和系统部署文档(作者：小兵)")

### 资源下载

- JDK7 [http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html#jdk-7u80-oth-JPR](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html#jdk-7u80-oth-JPR "JDK7")
- Maven [http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi "Maven")
- Redis [https://redis.io/download](https://redis.io/download "Redis")
- ActiveMQ [http://activemq.apache.org/download-archives.html](http://activemq.apache.org/download-archives.html "ActiveMQ")
- ZooKeeper [http://www.apache.org/dyn/closer.cgi/zookeeper/](http://www.apache.org/dyn/closer.cgi/zookeeper/ "ZooKeeper")
- Dubbo [http://dubbo.io/Download-zh.htm](http://dubbo.io/Download-zh.htm "Dubbo")
- Elastic Stack [https://www.elastic.co/downloads](https://www.elastic.co/downloads "Elastic Stack")
- Jenkins [http://updates.jenkins-ci.org/download/war/](http://updates.jenkins-ci.org/download/war/ "Jenkins")
- dubbo-admin-2.5.3 [http://download.csdn.net/detail/shuzheng5201314/9733652](http://download.csdn.net/detail/shuzheng5201314/9733652 "dubbo-admin-2.5.3")
- dubbo-admin-2.5.4-SNAPSHOT-jdk8 [http://download.csdn.net/detail/shuzheng5201314/9733657](http://download.csdn.net/detail/shuzheng5201314/9733657 "dubbo-admin-2.5.4-SNAPSHOT-jdk8")

## 开发指南:

- 1、本机安装Jdk7、Mysql、Redis、Zookeeper、ActiveMQ并启动相关服务，使用默认配置默认端口即可
- 2、克隆源代码到本地并打开，**推荐使用IntelliJ IDEA**，本地编译并安装到本地maven仓库

### 修改本地Host

- 127.0.0.1	upms.zhangshuzheng.cn
- 127.0.0.1	cms.zhangshuzheng.cn
- 127.0.0.1	pay.zhangshuzheng.cn
- 127.0.0.1	ucenter.zhangshuzheng.cn
- 127.0.0.1	wechat.zhangshuzheng.cn
- 127.0.0.1	api.zhangshuzheng.cn
- 127.0.0.1	oss.zhangshuzheng.cn

### 编译流程

zheng-admin、zheng-common => zheng-oss、zheng-api => zheng-upms => 其他

### 启动顺序

- 新建`zheng`数据库，导入`zheng.sql`

- 修改各dao模块的`redis.properties`和`jdbc.properties`数据库连接等配置信息，其中`master.redis.password`、`master.jdbc.password`、`slave.jdbc.password`密码值使用了AES加密，请使用`com.zheng.common.util.AESUtil`工具类修改这些值

- 启动 zheng-upms-rpc-service => zheng-upms-server => zheng-`xxx`-rpc-service => zheng-`xxx`-`webapp`

- 访问 [统一后台地址 http://upms.zhangshuzheng.cn:1111/ ](http://upms.zhangshuzheng.cn:1111/ "统一后台地址")，默认帐号密码：`admin / 123456`

- 登录成功后，可在右上角切换已注册系统访问

### 开发演示

- 创建数据表（建议使用PowerDesigner）

- 直接运行对应项目dao模块中的generator.main()，可自动生成单表的CRUD功能和对应的model、example、mapper、service代码

    - 生成的model和example均已实现Serializable接口，支持分布式
    - 生成的mapper.xml的selectByExample方法自动包含分页参数offset和limit
    - 已包含抽象类BaseServiceImpl，只需要继承抽象类并传入泛型参数，即可默认实现mapper接口所有方法，特殊需求直接扩展即可

- 启动流程：优先rcp-service服务提供者，再启动其他webapp

- 扩展流程：可扩展和拆分rpc-api和rpc-service模块，可按微服务拆分或场景拆分

## 演示地址

演示地址： [http://www.zhangshuzheng.cn/zhengAdmin](http://www.zhangshuzheng.cn/zhengAdmin "演示地址")

### 预览图
![login](zheng-admin/src/images/zheng-upms-login.png)
![crud](zheng-admin/src/images/zheng-upms-crud.png)
![swagger](project-bootstrap/api.png)

### 数据模型
![数据库模型](https://github.com/shuzheng/zheng/raw/master/project-datamodel/zheng.png)

### 拓扑图
![拓扑图](https://github.com/shuzheng/zheng/raw/master/project-bootstrap/distributedSystem.png)

## 附件

### 优秀文章和博客

- [创业互联网公司如何搭建自己的技术框架](http://shuzheng5201314.iteye.com/blog/2330151 "创业互联网公司如何搭建自己的技术框架")

- [单点登录原理与简单实现](http://shuzheng5201314.iteye.com/blog/2343910 "单点登录原理与简单实现")

- [ITeye论坛关于权限控制的讨论](http://www.iteye.com/magazines/82 "ITeye论坛关于权限控制的讨论")

- [RBAC新解：基于资源的权限管理(Resource-Based Access Control)](http://globeeip.iteye.com/blog/1236167 "RBAC新解：基于资源的权限管理(Resource-Based Access Control)")

- [网站架构经验随笔](http://jinnianshilongnian.iteye.com/blog/2289904 "网站架构经验随笔")

- [支付系统架构](http://shuzheng5201314.iteye.com/blog/2355431 "支付系统架构")

- [Spring整合JMS](http://elim.iteye.com/blog/1893038 "Spring整合JMS")

- [跟我学Shiro目录贴](http://jinnianshilongnian.iteye.com/blog/2018398 "跟我学Shiro目录贴")

- [跟我学SpringMVC目录汇总贴](http://jinnianshilongnian.iteye.com/blog/1752171 "跟我学SpringMVC目录汇总贴")

- [跟我学spring3 目录贴](http://jinnianshilongnian.iteye.com/blog/1482071 "跟我学spring3 目录贴")

- [跟我学OpenResty(Nginx+Lua)开发目录贴](http://jinnianshilongnian.iteye.com/blog/2190344 "跟我学OpenResty(Nginx+Lua)开发目录贴")

- [Redis中文网](http://www.redis.net.cn/ "Redis中文网")

- [读懂Redis并配置主从集群及高可用部署](http://mp.weixin.qq.com/s?__biz=MzIxNTYzOTQ0Ng==&mid=2247483668&idx=1&sn=cd31574877d38cf7ff9c047b86c9bf23&chksm=979475eda0e3fcfb6b5006bcd19c5a838eca9e369252847dbdf97820bf418201dd75c1dadda3&mpshare=1&scene=23&srcid=0117KUiiITwi2ETRan16xRVg#rd "读懂Redis并配置主从集群及高可用部署")

- [ELK(ElasticSearch, Logstash, Kibana)搭建实时日志分析平台](http://www.open-open.com/lib/view/open1451801542042.html "ELK(ElasticSearch, Logstash, Kibana)搭建实时日志分析平台")

- [Nginx基本功能极速入门](http://xxgblog.com/2015/05/17/nginx-start/ "Nginx基本功能极速入门")

- [mybatis-genarator 自定义插件](https://my.oschina.net/alexgaoyh/blog/702791 "mybatis-genarator 自定义插件")

- [Elasticsearch权威指南（中文版）](https://es.xiaoleilu.com/510_Deployment/20_hardware.html "Elasticsearch权威指南（中文版）")

- [springMVC对简单对象、Set、List、Map的数据绑定和常见问题.](http://blog.csdn.net/z_dendy/article/details/12648641 "springMVC对简单对象、Set、List、Map的数据绑定和常见问题.")

- [做个男人，做个成熟的男人，做个有城府的男人](http://shuzheng5201314.iteye.com/blog/1387820 "做个男人，做个成熟的男人，做个有城府的男人")


### 常用在线小工具

- [在线Cron表达式生成器](http://cron.qqe2.com/ "在线Cron表达式生成器")

- [在线工具 - 程序员的工具箱](http://tool.lu/ "在线工具 - 程序员的工具箱")

## 许可证

[MIT](http://opensource.org/licenses/MIT "MIT")