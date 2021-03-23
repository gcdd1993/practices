package io.github.gcdd1993.java.infrastrctural.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gcdd1993 on 2021/3/23.
 */
public class LockTest {

    @Test
    public void func1() throws InterruptedException {
        // 锁同一个对象
//        SynchronizedExample synchronizedExample = new SynchronizedExample();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit(synchronizedExample::func1);
//        executorService.submit(synchronizedExample::func1);

        // 锁不同对象
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(e1::func1);
        executorService.submit(e2::func1);

        Thread.sleep(3000);
    }

    @Test
    public void func2() throws InterruptedException {
//        SynchronizedExample synchronizedExample = new SynchronizedExample();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit(synchronizedExample::func2);
//        executorService.submit(synchronizedExample::func2);

        // 锁不同对象
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(e1::func2);
        executorService.submit(e2::func2);
        Thread.sleep(3000);
    }

    @Test
    public void func3() throws InterruptedException {
        // 锁不同对象
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(e1::func3);
        executorService.submit(e2::func3);
        Thread.sleep(3000);
    }

    @Test
    public void func4() throws InterruptedException {
        // 锁不同对象
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(SynchronizedExample::func4);
        executorService.submit(SynchronizedExample::func4);
        Thread.sleep(3000);
    }
}
