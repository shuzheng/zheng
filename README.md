## zheng
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/shuzheng/zheng/pulls)
[![GitHub forks](https://img.shields.io/github/forks/shuzheng/zheng.svg?style=social&label=Fork)](https://github.com/shuzheng/zheng)

# 项目组织结构
``` lua
zheng
├── zheng-common -- 公共模块
├── zheng-admin -- 后台管理系统模板（基于bootstrap实现的响应式Material Design风格的通用后台管理系统模板）
├── zheng-upms -- 用户权限管理系统（网关）
|    ├── zheng-upms-dao -- 数据库操作，MyBatisGenerator生成
|    ├── zheng-upms-sso-client -- SSO客户端依赖包
|    ├── zheng-upms-rpc-api -- rpc接口包
|    ├── zheng-upms-rpc-service -- rpc服务提供者[端口:1112]
|    ├── zheng-upms-app1 -- SSO测试客户端1[端口:1113]
|    ├── zheng-upms-app2 -- SSO测试客户端2[端口:1114]
|    └── zheng-upms-server -- 系统及SSO服务端[端口:1111]
├── zheng-cms -- 内容管理系统
|    ├── zheng-cms-dao -- 数据库操作，MyBatisGenerator生成
|    ├── zheng-cms-rpc-api -- rpc接口包
|    ├── zheng-cms-rpc-service -- rpc服务提供者[端口:2225]
|    ├── zheng-cms-service -- 业务逻辑
|    ├── zheng-cms-search -- 搜索服务[端口:2221]
|    ├── zheng-cms-admin -- 后台管理[端口:2222]
|    ├── zheng-cms-job -- 消息队列、任务调度等[端口:2223]
|    └── zheng-cms-web -- 网站前台[端口:2224]
├── zheng-pay -- 支付系统
|    ├── zheng-pay-dao -- 数据库操作，MyBatisGenerator生成
|    ├── zheng-pay-service -- 业务逻辑
|    ├── zheng-pay-sdk -- 开发工具包
|    ├── zheng-pay-admin -- 后台管理[端口:3331]
|    └── zheng-pay-web -- 演示示例[端口:3332]
├── zheng-ucenter -- 用户系统
|    ├── zheng-ucenter-dao -- 数据库操作，MyBatisGenerator生成
|    ├── zheng-ucenter-service -- 业务逻辑
|    └── zheng-ucenter-home -- 网站前台[端口:4441]
|── zheng-wechat-mp -- 微信公众号管理系统
|    ├── zheng-wechat-mp-dao -- 数据库操作，MyBatisGenerator生成
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

# 项目模块结构图
![项目模块结构图](project-bootstrap/project.png)

# 后端技术:
* Spring Framework
* SpringMVC: MVC框架
* Spring secutity|Shiro: 安全框架
* Spring session: 分布式Session管理
* MyBatis: ORM框架
* MyBatis Generator: 代码生成
* Druid: 数据库连接池
* Jsp|Velocity|Thymeleaf: 模板引擎
* ZooKeeper: 协调服务
* Dubbo: 分布式服务框架
* TBSchedule|elastic-job: 分布式调度框架
* Redis: 分布式缓存数据库
* Quartz: 作业调度框架
* Ehcache: 缓存框架
* ActiveMQ: 消息队列
* Solr|Elasticsearch: 分布式全文搜索引擎
* FastDFS: 分布式文件系统
* Log4J: 日志管理
* Swagger2: 接口文档
* sequence: 分布式高效ID生产 [http://git.oschina.net/yu120/sequence](http://git.oschina.net/yu120/sequence "sequence")
* AliOSS|Qiniu: 云存储
* Protobuf|json: 数据传输 
* Jenkins: 持续集成工具
* Maven|Gradle: 项目构建管理

# 前端技术:
* jQuery
* Bootstrap
* jQuery EasyUI
* AngularJs
* zhengAdmin [基于bootstrap实现的响应式Material Design风格的通用后台管理系统](https://github.com/shuzheng/zhengAdmin "zhengAdmin")
* autoMail [邮箱地址自动补全插件](https://github.com/shuzheng/autoMail "autoMail")
* zheng.jprogress.js [一款模仿youtube加载进度条插件](https://github.com/shuzheng/zheng.jprogress.js "zheng.jprogress.js")
* zheng.jtotop.js [返回顶部插件(可以任意速度滑动到指定任意位置)](https://github.com/shuzheng/zheng.jtotop.js "zheng.jtotop.js")

# 开发工具:
* MySql: 数据库
* jetty: 开发服务器
* Tomcat: 应用服务器
* SVN|Git: 版本管理
* Nginx: 反向代理服务器
* Varnish: HTTP加速器
* IntelliJ IDEA: 开发IDE
* PowerDesigner: 建模工具
* Navicat for MySQL: 数据库客户端

# 项目使用方法


### 开发环境搭建:
1、前提：本机安装Jdk7、Mysql、Redis、Zookeeper、ActiveMQ并启动相关服务，使用默认配置默认端口，下面有资源下载链接（安装流程略）
2、克隆源代码到本地并打开，推荐使用IntelliJ IDEA，本地编译并安装到本地maven仓

### 开发流程

1、创建数据表（建议使用PowerDesigner）

2、配置对应dao模块的generatorConfig.xml，只需底部追加指定表的代码生成，可生成单表的CRUD功能，如：
```xml
<table tableName="xxx" domainObjectName="xxx"></table>
```
* **生成的model和example均已实现Serializable接口，支持分布式**
* **生成的mapper.xml的selectByExample方法自动包含分页参数offset和limit，分页示例：**
```java
CmsArticleExample cmsArticleExample = new CmsArticleExample();
cmsArticleExample.setOffset((page - 1) * rows);
cmsArticleExample.setLimit(rows);
```
* **已包含抽象类BaseServiceImpl，只需要继承抽象类并传入泛型参数，即可默认实现mapper接口所有方法，特殊需求直接扩展即可**
```java
public class CmsArticleServiceImpl extends BaseServiceImpl<CmsArticleMapper, CmsArticle, CmsArticleExample>
```
3、启动流程：优先rcp-service服务提供者，再启动其他webapp

### 资源下载链接
* Maven [http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi "Maven")
* Redis [https://redis.io/download](https://redis.io/download "Redis")
* ActiveMQ [http://activemq.apache.org/download-archives.html](http://activemq.apache.org/download-archives.html "ActiveMQ")
* ZooKeeper [http://www.apache.org/dyn/closer.cgi/zookeeper/](http://www.apache.org/dyn/closer.cgi/zookeeper/ "ZooKeeper")
* Dubbo [http://dubbo.io/Download-zh.htm](http://dubbo.io/Download-zh.htm "Dubbo")
* Jenkins [http://updates.jenkins-ci.org/download/war/](http://updates.jenkins-ci.org/download/war/ "Jenkins")
* dubbo-admin-2.5.4-SNAPSHOT-jdk8 [http://download.csdn.net/download/mappingsunlight/9489736](http://download.csdn.net/download/mappingsunlight/9489736 "dubbo-admin-2.5.4-SNAPSHOT-jdk8")

### 修改本地Host
* 127.0.0.1	upms.zhangshuzheng.cn
* 127.0.0.1	cms.zhangshuzheng.cn
* 127.0.0.1	pay.zhangshuzheng.cn
* 127.0.0.1	ucenter.zhangshuzheng.cn
* 127.0.0.1	wechat.zhangshuzheng.cn
* 127.0.0.1	api.zhangshuzheng.cn
* 127.0.0.1	oss.zhangshuzheng.cn

# 预览图
![预览效果图](https://github.com/shuzheng/zhengAdmin/raw/master/src/images/zheng-upms-crud.png)

# 数据模型
![数据库模型](project-datamodel/zheng.png)

# 拓扑图
![拓扑图](project-bootstrap/distributedSystem.png)
