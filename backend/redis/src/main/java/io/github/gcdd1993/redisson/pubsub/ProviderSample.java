package io.github.gcdd1993.redisson.pubsub;

import org.redisson.api.RedissonClient;

import java.util.TimerTask;

/**
 * @author gcdd1993
 * @date 2021/3/2
 * @since 1.0.0
 */
public class ProviderSample
        extends TimerTask {
    private final RedissonClient redissonClient = Redissons.createInstance();

    @Override
    public void run() {
        var topic = redissonClient.getTopic("redisson::topic::test1");
        topic.publishAsync(SimpleMessage.builder()
                .name("test01")
                .age(1)
                .gender("男")
                .build());
    }
}
