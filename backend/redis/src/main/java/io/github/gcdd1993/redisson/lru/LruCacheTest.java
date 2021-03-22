package io.github.gcdd1993.redisson.lru;

/**
 * Created by gcdd1993 on 2021/3/21.
 */
public class LruCacheTest {

    public static void main(String[] args) {
        var cache = new LruCache<String, String>(5);

        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.put("5", "5");

        // {1=1, 2=2, 3=3, 4=4, 5=5}
        System.out.println(cache);

        cache.get("1");
        cache.get("2");
        cache.get("5");
        cache.get("4");
        // 3 LRU
        cache.put("10", "10");
        // {1=1, 2=2, 5=5, 4=4, 10=10}
        System.out.println(cache);
    }
}
