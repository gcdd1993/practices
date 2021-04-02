package segmentfault;

import org.springframework.context.annotation.Bean;

/**
 * Created by gcdd1993 on 2021/3/25.
 */
public class TestConfiguration {

    @Bean
    public String testBean() {
        System.out.println("我被加载了");
        return "111";
    }
}
