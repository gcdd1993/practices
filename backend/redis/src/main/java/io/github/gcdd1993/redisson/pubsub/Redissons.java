package io.github.gcdd1993.redisson.pubsub;

import lombok.experimental.UtilityClass;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author gcdd1993
 * @date 2021/3/2
 * @since 1.0.0
 */
@UtilityClass
public class Redissons {
    public static RedissonClient createInstance() {
        var config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.3.123:30001");
        return Redisson.create(config);
    }
}
