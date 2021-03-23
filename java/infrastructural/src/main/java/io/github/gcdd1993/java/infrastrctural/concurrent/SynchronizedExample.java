package io.github.gcdd1993.java.infrastrctural.concurrent;

/**
 * Created by gcdd1993 on 2021/3/23.
 */
public class SynchronizedExample {

    // 同步代码块
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i + " ");
            }
        }
    }

    // 同步方法
    public synchronized void func2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i + " ");
        }
    }

    // 同步整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
    public void func3() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i + " ");
            }
        }
    }

    // 同步静态方法，作用于整个类。
    public synchronized static void func4() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i + " ");
        }
    }
}
