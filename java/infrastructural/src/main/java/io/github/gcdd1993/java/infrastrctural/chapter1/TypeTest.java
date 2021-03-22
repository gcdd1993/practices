package io.github.gcdd1993.java.infrastrctural.chapter1;

/**
 * Created by gcdd1993 on 2021/3/22.
 */
public class TypeTest {
    public static void main(String[] args) {
        byte byte1 = 1; // 8 bit
        char char1 = 'A'; // 16 bit
        short short1 = 1; // 16 bit
        int int1 = 1; // 32 bit
        float float1 = 1.1F; // 64 bit
        long long1 = 1000L; // 64 bit
        double double1 = 1.1D; // 64 bit
        boolean boolean1 = true; // 1bit ~，JVM编译器会把boolean类型的数据转换为int，1表示true，0表示false

        // 包装类型
        Integer x = 2; //包装类型，自动装箱
        int y = x; // 基本类型，自动拆箱
    }
}
