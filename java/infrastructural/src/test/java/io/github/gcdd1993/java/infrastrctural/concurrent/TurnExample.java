package io.github.gcdd1993.java.infrastrctural.concurrent;

/**
 * 两个线程交替打印 奇数偶数
 * <p>
 * Created by gcdd1993 on 2021/3/23.
 */
public class TurnExample {
    static class Wrapper {
        int number;
    }

    // 奇数
    public static class ThreadA implements Runnable {
        private final Wrapper wrapper;

        public ThreadA(Wrapper wrapper) {
            this.wrapper = wrapper;
        }

        @Override
        public void run() {
            synchronized (this.wrapper) {
                for (int i = 0; i < 100; i++) {
                    if (this.wrapper.number % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " --> 打印奇数 " + this.wrapper.number++);
                        this.wrapper.notifyAll();
                    } else {
                        try {
                            this.wrapper.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    // 偶数
    public static class ThreadB implements Runnable {
        private final Wrapper wrapper;

        public ThreadB(Wrapper wrapper) {
            this.wrapper = wrapper;
        }

        @Override
        public void run() {
            synchronized (this.wrapper) {
                for (int i = 0; i < 100; i++) {
                    if (this.wrapper.number % 2 == 0) {
                        synchronized (this) {
                            System.out.println(Thread.currentThread().getName() + " --> 打印偶数 " + this.wrapper.number++);
                            this.wrapper.notifyAll();
                        }
                    } else {
                        try {
                            this.wrapper.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Wrapper wrapper = new Wrapper();
        Thread a = new Thread(new ThreadA(wrapper));
        Thread b = new Thread(new ThreadB(wrapper));

        a.start();
        b.start();

        Thread.sleep(3000);
    }

}
