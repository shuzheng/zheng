# Zheng 环境的Docker部署

实现zheng环境的docker化，通过配置好的docker-compose，一键启动所有依赖服务，并且和目前项目的默认配置匹配，项目无需进行任何更改就可以立即运行。该配置参考JHipster，将不同的服务配置成不同的 yaml 文件，然后在同一的 app.yaml 中引用所有的服务。``

整个docker-compose 由6个服务构成

1. mysql
2. redis
3. nginx
4. zookeeper
5. activemq
6. dubbo

## 单独测试独立服务

```docker
docker-compose -f docker/mysql.yml up -d
docker-compose -f docker/redis.yml up -d
docker-compose -f docker/zookeeper.yml up -d
docker-compose -f docker/activemq.yml up -d
docker-compose -f docker/nginx.yml up -d
```

## 测试dubbo

由于 dubbo 依赖于 zookeepr，所以单独有一个 dubbo 的 yaml 用来测试：

```docker
docker-compose -f docker/dubbo.yml up -d
```
## 一键启动所有的服务

```docker
docker-compose -f docker/app.yml up -d
```

## 一键关闭所有的服务

该操作会保留所有数据，因为数据会被存放在 Volume 中， 注意这些文件不会被 git 管理，请自行管理。

```docker
docker-compose -f docker/app.yml down
```
## 查看指定服务的log

其中 `-f` 表示 follow，会持续显示最新的log

```docker
docker-compose -f docker/app.yml logs -f <service_name>
```
For example:
```docker
docker-compose -f docker/app.yml logs -f zheng-mysql
docker-compose -f docker/app.yml logs -f <service_name>
```
## 重新 build 某个服务

如果对某个服务的 yaml 文件进行了更改，可以通过该命令进行重新build

```docker
docker-compose -f docker/app.yml up -d --no-deps --build <service_name>
```

For example

```docker
docker-compose -f docker/app.yml up -d --no-deps --build zheng-mysql
docker-compose -f docker/app.yml up -d --no-deps --build zheng-nginx
```

# 程序启动

分享了5个 Intellij 的执行配置文件，文件在 `.idea\runConfigurations` 下面，（.gitignore 排除了这些文件）

可以直接点击这些配置运行对应项目，按照要求应该依次启动：

1. ZhengUpmsRpcServiceApplication.xml
2. zheng_upms_server.xml
3. ZhengCmsRpcServiceApplication.xml
4. zheng_cms_admin.xml
5. zheng_cms_web.xml

希望这些能够帮助面对复杂配置望而却步的开发者快速体验。

# 遇到的主要问题及解决方法

##  ERROR 1067 (42000) at line 517: Invalid default value for 'last_login_time'

https://stackoverflow.com/a/37696251/2000468

solution:

https://github.com/docker-library/mysql/issues/149#issuecomment-217188612

## Docker MySQL execute the initial sql encoding issue

> This issue is not related with mysql, it is the issue of docker, because when MySQL try to execute the sql script, it will use the docker terminal. But the docker terminal default encode is not UTF8, so when the sql execute, the Chinese character has been broken.

see this issue solution: https://github.com/docker-library/mysql/issues/131#issuecomment-248413835 and the reason: https://github.com/docker-library/mysql/issues/131#issuecomment-248404157