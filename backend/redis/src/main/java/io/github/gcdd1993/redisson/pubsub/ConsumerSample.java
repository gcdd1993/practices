package io.github.gcdd1993.redisson.pubsub;

import org.redisson.api.RedissonClient;

/**
 * redis pub/sub 是广播的形式，有几个实例（Listener）就会收到几份消息
 *
 * @author gcdd1993
 * @date 2021/3/2
 * @since 1.0.0
 */
public class ConsumerSample
        implements Runnable {
    private final RedissonClient redissonClient = Redissons.createInstance();

    @Override
    public void run() {
        var topic = redissonClient.getTopic("redisson::topic::test1");
        topic.addListener(SimpleMessage.class, (channel, msg) -> {
            System.out.println(Thread.currentThread().getName() + " receive message " + msg.toString());
        });
    }
}
