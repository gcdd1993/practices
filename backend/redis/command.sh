# Redis支持五种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)。
redis-cli -h localhost -p 30001

# String（字符串）
127.0.0.1:30001> SET name "redis.net.cn"
127.0.0.1:30001> GET name

# Hash（哈希）
127.0.0.1:30001> HMSET user:1 username redis.net.cn password redis.net.cn points 200
127.0.0.1:30001> HGETALL user:1
1) "username"
2) "redis.net.cn"
3) "password"
4) "redis.net.cn"
5) "points"
6) "200"
127.0.0.1:30001> HGETALL user:2
(empty list or set)

# List（列表）
127.0.0.1:30001> lpush redis.net.cn redis
(integer) 1
127.0.0.1:30001> lpush redis.net.cn mongodb
(integer) 2
127.0.0.1:30001> lpush redis.net.cn rabitmq
(integer) 3
127.0.0.1:30001> lrange redis.net.cn 0 10
1) "rabitmq"
2) "mongodb"
3) "redis"

# Set（集合）
127.0.0.1:30001> sadd redis.set.cn redis
(integer) 1
127.0.0.1:30001> sadd redis.set.cn mongodb
(integer) 1
127.0.0.1:30001> sadd redis.set.cn rabitmq
(integer) 1
127.0.0.1:30001> sadd redis.set.cn rabitmq
(integer) 0
127.0.0.1:30001> smembers redis.set.cn
1) "rabitmq"
2) "mongodb"
3) "redis"

# zset(sorted set：有序集合)
# 使用场景：排行榜等
127.0.0.1:30001> zadd redis.zset.cn 0 redis
(integer) 1
127.0.0.1:30001> zadd redis.zset.cn 1 mongodb
(integer) 1
127.0.0.1:30001> zadd redis.zset.cn 2 rabbitmq
(integer) 1
127.0.0.1:30001> zadd redis.zset.cn 3 rabbitmq
(integer) 0
127.0.0.1:30001> zrangebyscore redis.zset.cn 0 1000
1) "redis"
2) "mongodb"
3) "rabbitmq"

# ping
127.0.0.1:30001> ping
PONG