package io.github.gcdd1993.java.infrastrctural.chapter1;

/**
 * 哈希冲突
 * <p>
 * Created by gcdd1993 on 2021/3/22.
 */
public class HashConflictTest {

    public static void main(String[] args) {
        // 假设桶的大小为16
        int size = 16;
        for (int i = 0; i < 1000; i++) {
            // 因为hash存储的散列地址是固定大小的，很可能两个hash值都被分配到一个桶里
            System.out.println(hash(i) % size);
        }
    }

    private static int hash(Object v) {
        int h;
        return (v == null) ? 0 : (h = v.hashCode()) ^ (h >>> 16);
    }
}
