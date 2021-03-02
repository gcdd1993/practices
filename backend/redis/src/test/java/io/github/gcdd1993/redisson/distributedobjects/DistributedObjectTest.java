package io.github.gcdd1993.redisson.distributedobjects;

import io.github.gcdd1993.redisson.pubsub.Redissons;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;

/**
 * 分布式对象
 * https://github.com/redisson/redisson/wiki/6.-distributed-objects
 *
 * @author gcdd1993
 * @date 2021/3/2
 * @since 1.0.0
 */
public class DistributedObjectTest {
    private final RedissonClient redissonClient = Redissons.createInstance();

    @Test
    public void test01() {
        var bucket = redissonClient.getBucket("anyObject");
        bucket.set("xxx");
        var obj = bucket.get();

        bucket.trySet("333");
        bucket.compareAndSet("666", "777");
        bucket.getAndSet("888");
    }

    @Test
    public void getAllBucket() {
        var buckets = redissonClient.getBuckets();
    }

}
