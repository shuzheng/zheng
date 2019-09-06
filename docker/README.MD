## Test application separately

```docker
docker-compose -f docker/mysql.yml up -d
docker-compose -f docker/redis.yml up -d
docker-compose -f docker/zookeeper.yml up -d
docker-compose -f docker/activemq.yml up -d
docker-compose -f docker/nginx.yml up -d
```

## Test Dubbo
```docker
docker-compose -f docker/dubbo.yml up -d
```
## Start all dependency

```docker
docker-compose -f docker/app.yml up -d

docker-compose -f docker/app.yml down
```

## Log for specific service

```docker
docker-compose -f docker/app.yml logs -f <service_name>
```
For example:
```docker
docker-compose -f docker/app.yml logs -f zheng-mysql
docker-compose -f docker/app.yml logs -f <service_name>
```
## rebuild single service

```docker
docker-compose -f docker/app.yml up -d --no-deps --build <service_name>
```

For example

```docker
docker-compose -f docker/app.yml up -d --no-deps --build zheng-mysql
docker-compose -f docker/app.yml up -d --no-deps --build zheng-nginx
```

# Issue

##  ERROR 1067 (42000) at line 517: Invalid default value for 'last_login_time'

https://stackoverflow.com/a/37696251/2000468

solution:

https://github.com/docker-library/mysql/issues/149#issuecomment-217188612

## Docker MySQL execute the initial sql encoding issue

This issue is not related with mysql, it is the issue of docker, because when MySQL try to execute the sql script, it will use the dorker terminal. But the docker terminal default encode is not UTF8, so when the sql execute, the Chinese character has been broken.

see this issue solution: https://github.com/docker-library/mysql/issues/131#issuecomment-248413835 and the reason: https://github.com/docker-library/mysql/issues/131#issuecomment-248404157