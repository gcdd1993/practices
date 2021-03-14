package com.segmentfault.springbootlesson5;

/**
 * 双亲委派演示
 *
 * @author gcdd1993
 * @date 2021/3/13
 * @since 1.0.0
 */
public class Main {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        while (classLoader != null) {
            System.out.println(classLoader.getClass().getName());
            classLoader = classLoader.getParent();
        }

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader.getClass().getName());
    }
}
