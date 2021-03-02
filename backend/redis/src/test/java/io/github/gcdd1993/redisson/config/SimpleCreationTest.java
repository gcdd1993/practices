package io.github.gcdd1993.redisson.config;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.config.Config;

import java.io.IOException;

/**
 * @author gcdd1993
 * @date 2021/3/2
 * @since 1.0.0
 */
public class SimpleCreationTest {

    /**
     * 编程方式配置redisson
     */
    @Test
    public void test01() {
        var config = new Config();
//        config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer()
                .setAddress("redis://192.168.3.123:30001");
        var redissonClient = Redisson.create(config);
        var clientId = redissonClient.getId();
        System.out.println("redisson client id: " + clientId);
    }

    /**
     * yaml 配置文件
     */
    @Test
    public void test02() throws IOException {
        var config = Config.fromYAML(this.getClass().getClassLoader().getResourceAsStream("redisson.yml"));
        System.out.println("config: " + config);
        var redissonClient = Redisson.create(config);
        var clientId = redissonClient.getId();
        System.out.println("redisson client id: " + clientId);
    }
}
