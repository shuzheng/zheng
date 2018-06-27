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