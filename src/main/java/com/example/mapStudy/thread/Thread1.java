package com.example.mapStudy.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.thread
 * @Author: Noah
 * @CreateTime: 2022-09-10  09:34
 * @Description: TODO
 * @Version: 1.0
 */
public class Thread1 {
    /**
     * 线程执行不返回是否成功
     *
     * @date 2022/9/10 9:45
     * @return: void
     */
    @Test
    public void test1() {
        //创建一个线程池对象
        /**
         * 参数信息：
         * int corePoolSize     核心线程大小
         * int maximumPoolSize  线程池最大容量大小
         * long keepAliveTime   线程空闲时，线程存活的时间
         * TimeUnit unit        时间单位
         * BlockingQueue<Runnable> workQueue  任务队列。一个阻塞队列
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10));
        //另外一种写法,与上述本质为一致：
        //ExecutorService pool = Executors.newFixedThreadPool(4);
        //执行任务
        for (int i = 0; i < 10; i++) {
            int index = i;
            pool.execute(() -> System.out.println("i:" + index + "      execute!"));
        }
        //关闭线程池
        pool.shutdown();
    }

    /**
     * 线程返回执行结果
     *
     * @date 2022/9/10 9:46
     * @return: void
     */
    @Test
    public void test2() {
        /**
         * 参数信息：
         * int corePoolSize     核心线程大小
         * int maximumPoolSize  线程池最大容量大小
         * long keepAliveTime   线程空闲时，线程存活的时间
         * TimeUnit unit        时间单位
         * BlockingQueue<Runnable> workQueue  任务队列。一个阻塞队列
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10));
        //执行任务
        Future<?> future = pool.submit(() -> System.out.println(2 / 1));
        try {
            //查看执行情况，有异常将会在此显示。
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
