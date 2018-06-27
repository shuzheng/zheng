## Test application separately

docker-compose -f docker/mysql.yml up -d
docker-compose -f docker/redis.yml up -d
docker-compose -f docker/zookeeper.yml up -d
docker-compose -f docker/activemq.yml up -d
docker-compose -f docker/nginx.yml up -d

## Test Dubbo

docker-compose -f docker/dubbo.yml up -d

## Start all dependency

docker-compose -f docker/app.yml up -d

docker-compose -f docker/app.yml down

## rebuild single service

docker-compose -f docker/app.yml up -d --no-deps --build <service_name>

docker-compose -f docker/app.yml up -d --no-deps --build zheng-mysql
docker-compose -f docker/app.yml up -d --no-deps --build zheng-nginx

# Issue

##  ERROR 1067 (42000) at line 517: Invalid default value for 'last_login_time'

https://stackoverflow.com/a/37696251/2000468

solution:

https://github.com/docker-library/mysql/issues/149#issuecomment-217188612