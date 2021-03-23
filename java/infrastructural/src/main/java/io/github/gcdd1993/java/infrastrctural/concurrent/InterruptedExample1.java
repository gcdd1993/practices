package io.github.gcdd1993.java.infrastrctural.concurrent;

/**
 * 线程终端示例
 * <p>
 * Created by gcdd1993 on 2021/3/23.
 */
public class InterruptedExample1 extends Thread {

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            System.out.println("running...");
        }
        System.out.println(Thread.currentThread().getName() + " 停止");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new InterruptedExample1();
        t1.start();

        Thread.sleep(2000);
        // 如果该线程处于阻塞、限期等待或无限期等待的状态，那么就会抛出InterruptedException
        t1.interrupt();
    }
}
