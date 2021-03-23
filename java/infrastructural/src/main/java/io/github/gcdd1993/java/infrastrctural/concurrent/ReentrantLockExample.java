package io.github.gcdd1993.java.infrastrctural.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock实现交替打印
 * <p>
 * Created by gcdd1993 on 2021/3/23.
 */
public class ReentrantLockExample {
    static class Wrapper {
        int number;
    }

    private static class T1 implements Runnable {
        private final Wrapper wrapper;
        // 可重入锁
        private final ReentrantLock lock;
        private final Condition condition;

        private T1(Wrapper wrapper, ReentrantLock lock, Condition condition) {
            this.wrapper = wrapper;
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 获取锁");
                    if (wrapper.number % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " 打印偶数 " + wrapper.number++);
                        condition.signal();
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 等待锁");
                        condition.await(); // 释放并阻塞
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    System.out.println(Thread.currentThread().getName() + " 释放锁");
//                    lock.unlock();
                }
            }
        }
    }

    private static class T2 implements Runnable {
        private final Wrapper wrapper;
        private final ReentrantLock lock;
        private final Condition condition;

        private T2(Wrapper wrapper, ReentrantLock lock, Condition condition) {
            this.wrapper = wrapper;
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 获取锁");
                    if (wrapper.number % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " 打印奇数 " + wrapper.number++);
                        condition.signal();
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 等待锁");
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    System.out.println(Thread.currentThread().getName() + " 释放锁");
//                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Wrapper wrapper = new Wrapper();
        Thread t1 = new Thread(new T1(wrapper, lock, condition));
        Thread t2 = new Thread(new T2(wrapper, lock, condition));

        t1.start();
        t2.start();

        Thread.sleep(3000);
    }
}
