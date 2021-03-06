package io.github.gcdd1993.redisson.pubsub;

import org.junit.jupiter.api.Test;

import java.util.Timer;

/**
 * @author gcdd1993
 * @date 2021/3/2
 * @since 1.0.0
 */
class ConsumerSampleTest {

    @Test
    public void test01() throws InterruptedException {
        var consumer1 = new Thread(new ConsumerSample());
        consumer1.setName("thread-1");
        consumer1.start();
        Thread.sleep(1000L);
        var consumer2 = new Thread(new ConsumerSample());
        consumer2.setName("thread-2");
        consumer2.start();
        Thread.sleep(1000L);
        var timer = new Timer();
        timer.schedule(new ProviderSample(), 0L, 1000L);
    }
}