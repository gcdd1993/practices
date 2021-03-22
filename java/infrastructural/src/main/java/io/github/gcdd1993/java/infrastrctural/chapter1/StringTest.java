package io.github.gcdd1993.java.infrastrctural.chapter1;

/**
 * 字符串
 * <p>
 * Created by gcdd1993 on 2021/3/22.
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = new String("aaa"); // "aaa"属于字符串字面量，在编译器会在StringPool中创建一个字符串对象，new String会创建一个字符串对象
        String s2 = new String("aaa"); // 创建一个字符串对象
        System.out.println(s1 == s2); // false

        String s3 = s1.intern(); // 将"aaa"字面量放入StringPool中，并返回引用
        String s4 = s1.intern(); // 从StringPool中获取引用
        System.out.println(s3 == s4); // true

        String s5 = "aaa"; // 如果是采用 "bbb" 这种字面量的形式创建字符串，会自动地将字符串放入 String Pool 中
        String s6 = "aaa";
        System.out.println(s5 == s6); // true
    }
}
