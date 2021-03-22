package io.github.gcdd1993.java.infrastrctural.chapter1;

/**
 * 缓存池
 * <p>
 * Created by gcdd1993 on 2021/3/22.
 */
public class TypeCachePoolTest {

    public static void main(String[] args) {
        Integer x = new Integer(123); // ，每次都会新建一个对象
        Integer y = new Integer(123);
        System.out.println(x == y); // false

        Integer z = Integer.valueOf(123); // 会使用缓存池中的对象，多次调用会取得同一个对象的引用
        Integer k = Integer.valueOf(123);

        System.out.println(z == k); // true

        //     public static Integer valueOf(int i) {
        //        if (i >= IntegerCache.low && i <= IntegerCache.high)
        //            return IntegerCache.cache[i + (-IntegerCache.low)];
        //        return new Integer(i);
        //    }
    }
}
