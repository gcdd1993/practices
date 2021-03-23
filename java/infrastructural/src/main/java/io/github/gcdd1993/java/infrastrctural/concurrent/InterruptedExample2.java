package io.github.gcdd1993.java.infrastrctural.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Executor线程终端示例
 * <p>
 * Created by gcdd1993 on 2021/3/23.
 */
public class InterruptedExample2 /*extends Thread*/ {

/*    @Override
    public void run() {
        // 无法中断
        while (!this.isInterrupted()) {
            System.out.println("running...");
            // 不加延迟的情况下中断失败 java.lang.InterruptedException: sleep interrupted
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 停止");
    }*/

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("running... " + i);
                // 不加延迟的情况下中断会失败？
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(100 * 100 - 1000);
//         无法中断
//        future.cancel(true);

        executorService.shutdown();
        System.out.println("调用了shutdown方法，但是会等待任务执行完毕");
    }
//    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<?> future = executorService.submit(() -> {
//            while (true) {
//                System.out.println("running...");
//                // 不加延迟的情况下中断会失败？
//                Thread.sleep(100);
//            }
//        });
//
//        Thread.sleep(10000);
////         无法中断
//        future.cancel(true);
//
//        executorService.shutdownNow();
//    }
}
