package io.github.gcdd1993.java.infrastrctural.concurrent;

import junit.framework.TestCase;

import java.util.concurrent.*;

/**
 * Created by gcdd1993 on 2021/3/23.
 */
public class ExecutorPoolTest extends TestCase {
    /**
     * 1、核心线程数 1
     * 2、最大线程数 2
     * 3、有界队列，1
     * <p>
     * 执行过程
     * 1、提交第1个任务，进入有界队列，线程池新建线程，并从队列中取出任务，执行。
     * 2、提交第2个任务，进入有界队列，但是没有可用线程，不执行，此时缓冲队列数为1，激活线程为1
     * 3、提交第3个任务，有缓冲队列满，线程数小于最大线程数，新建线程，并从缓冲队列取出任务执行，此时缓冲队列有空位，将新任务加入缓冲队列
     * 此时缓冲队列数1，激活任务2
     *
     * @throws InterruptedException
     */
    public void test01() throws InterruptedException {
        // 有界队列
        BlockingQueue<Runnable> workList = new ArrayBlockingQueue<>(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                10,
                TimeUnit.SECONDS,
                workList,
                r -> new Thread(r, "my-thread"),
                new ThreadPoolExecutor.AbortPolicy()
        );
        Runnable task = () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // 先提交1个任务
        executor.submit(task);
        System.out.println("第1次提交任务");
        printExecutor(executor);

        // 再提交1个任务
        executor.submit(task);
        System.out.println("第2次提交任务");
        printExecutor(executor);

        // 再提交1个任务
        executor.submit(task);
        System.out.println("第3次提交任务");
        printExecutor(executor);

        // 再次提交任务，会走拒绝策略（抛异常）
        executor.submit(task);
        System.out.println("第4次提交任务");
        printExecutor(executor);

        while (true) {
            // blocking
            printExecutor(executor);
            TimeUnit.SECONDS.sleep(1L);
        }

    }

    /**
     * 1、核心线程数 1
     * 2、最大线程数 2
     * 3、无界队列
     * <p>
     * 使用无界队列，maximumPoolSize无效，线程数会维持在核心线程数，新提交的任务都会进入缓冲队列，直到之前提交的任务结束，从缓冲队列取出任务执行
     *
     * @throws InterruptedException
     */
    public void test02() throws InterruptedException {
        // 无界队列，使用无界队列，maximumPoolSize无效，线程数会维持在核心线程数
        BlockingQueue<Runnable> workList = new LinkedBlockingQueue<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                10,
                TimeUnit.SECONDS,
                workList,
                r -> new Thread(r, "my-thread"),
                new ThreadPoolExecutor.AbortPolicy()
        );

        // 维持2秒的任务
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        // 先提交1个任务
        executor.submit(task);
        System.out.println("第1次提交任务");
        printExecutor(executor);

        // 再提交1个任务
        executor.submit(task);
        System.out.println("第2次提交任务");
        printExecutor(executor);

        // 再提交1个任务
        executor.submit(task);
        System.out.println("第3次提交任务");
        printExecutor(executor);

        // 可以无限制提交任务，只会加入到缓冲队列（无界），并不会执行，除非其中有任务结束
        for (int i = 4; i < 100; i++) {
            executor.submit(task);
            System.out.println("第" + i + "次提交任务");
            printExecutor(executor);
        }

        while (true) {
            // blocking
            printExecutor(executor);
            TimeUnit.SECONDS.sleep(1L);
        }

    }

    /**
     * 拒绝策略
     *
     * @throws InterruptedException
     */
    public void test03() throws InterruptedException {
        BlockingQueue<Runnable> workList = new ArrayBlockingQueue<>(1);
        // 抛异常
        RejectedExecutionHandler abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        // 由提交线程处理本次任务
        RejectedExecutionHandler callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        // 丢弃缓冲队列头部任务
        RejectedExecutionHandler discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        // 丢弃本次提交的任务
        RejectedExecutionHandler discardPolicy = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                10,
                TimeUnit.SECONDS,
                workList,
                r -> new Thread(r, "my-thread"),
//                abortPolicy
//                callerRunsPolicy
//                discardOldestPolicy
                discardPolicy
        );
        Runnable task = () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 4; i < 100; i++) {
            Future<?> submit = executor.submit(task);
//            submit.cancel(true);
            System.out.println("第" + i + "次提交任务");
            printExecutor(executor);
        }

        while (true) {
            // blocking
            printExecutor(executor);
            TimeUnit.SECONDS.sleep(1L);
        }

    }

    /**
     * 预先创建核心线程
     * <p>
     * {@link ThreadPoolExecutor#prestartCoreThread()}
     * {@link ThreadPoolExecutor#prestartAllCoreThreads()}
     *
     * @throws InterruptedException
     */
    public void test04() throws InterruptedException {
        BlockingQueue<Runnable> workList = new ArrayBlockingQueue<>(1);
        // 抛异常
        RejectedExecutionHandler abortPolicy = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                12,
                10,
                TimeUnit.SECONDS,
                workList,
                r -> new Thread(r, "my-thread"),
//                abortPolicy
//                callerRunsPolicy
//                discardOldestPolicy
                abortPolicy
        );

        printExecutor(executor);
        // 预先创建线程
//        executor.prestartAllCoreThreads(); 预先创建所有核心线程
        for (int i = 0; i < 12; i++) {
            executor.prestartCoreThread(); // 预先创建一个核心线程
            printExecutor(executor);
        }

    }

    /**
     * 空闲时间
     * <p>
     * 当线程数大于核心线程数时才会生效
     * 超过设置的空闲时间，且缓冲队列没有任务，线程将会销毁，直至核心线程数
     *
     * @throws InterruptedException
     */
    public void test05() throws InterruptedException {
        BlockingQueue<Runnable> workList = new ArrayBlockingQueue<>(1);
        // 丢弃本次提交的任务
        RejectedExecutionHandler discardPolicy = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                1,
                TimeUnit.SECONDS,
                workList,
                r -> new Thread(r, "my-thread"),
                discardPolicy
        );

        // 维持1秒的任务
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        printExecutor(executor);
        // 提交4个任务，使得线程数比核心线程数大
        for (int i = 0; i < 4; i++) {
            executor.submit(task);
        }
        printExecutor(executor);

        // 等待超时
        while (true) {
            printExecutor(executor);
            TimeUnit.SECONDS.sleep(1);
        }

    }

    private void printExecutor(ThreadPoolExecutor executor) {
        System.out.println("当前线程池线程数：" + executor.getPoolSize());
        System.out.println("当前缓冲队列元素数：" + executor.getQueue().size());
        System.out.println("当前激活线程数：" + executor.getActiveCount());

        System.out.println("=============================================================");
    }
}
