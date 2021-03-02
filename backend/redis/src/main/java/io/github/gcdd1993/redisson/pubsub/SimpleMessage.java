package io.github.gcdd1993.redisson.pubsub;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gcdd1993
 * @date 2021/3/2
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMessage
        implements Serializable {
    private String name;
    private int age;
    private String gender;
}
