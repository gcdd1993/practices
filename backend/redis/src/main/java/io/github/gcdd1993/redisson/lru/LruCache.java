package io.github.gcdd1993.redisson.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LinkedHashMap实现的LRU算法
 * <p>
 * Created by gcdd1993 on 2021/3/21.
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {
    // 缓存数量
    private final int cacheSize;

    public LruCache(int cacheSize) {
        // 最后一个true指的是让linkedHashMap按照访问顺序来进行排序，最近访问的放在头，最老访问的就在尾
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据
        return size() > cacheSize;
    }
}
