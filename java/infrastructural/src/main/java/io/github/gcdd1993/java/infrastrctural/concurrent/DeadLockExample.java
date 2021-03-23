package io.github.gcdd1993.java.infrastrctural.concurrent;

/**
 * 死锁示例
 * <p>
 * Created by gcdd1993 on 2021/3/23.
 */
public class DeadLockExample {
    // 创建两个互斥量
    private static final String A = "A";
    private static final String B = "B";

    public static class ThreadA implements Runnable {

        @Override
        public void run() {
            synchronized (A) {
                System.out.println(Thread.currentThread().getName() + " --> 获得锁A");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " --> 等待锁B");
                synchronized (B) {
                    System.out.println(Thread.currentThread().getName() + " --> 获得锁B");
                }
            }
        }
    }

    public static class ThreadB implements Runnable {

        @Override
        public void run() {
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + " --> 获得锁B");
                System.out.println(Thread.currentThread().getName() + " --> 等待锁A");
                synchronized (A) {
                    System.out.println(Thread.currentThread().getName() + " --> 获得锁A");
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread a = new Thread(new ThreadA());
        Thread b = new Thread(new ThreadB());

        a.start();
        b.start();
    }

}
