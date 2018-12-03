package com.smr.pc.concurrent.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 参数说明:
 * <p>
 * corePoolSize：     线程池中核心线程数的最大值
 * maximumPoolSize：  线程池中能拥有最多线程数
 * workQueue：        用于缓存任务的阻塞队列
 * RejectedExecutionHandler: 拒绝策略
 * <p>
 * 我们现在通过向线程池添加新的任务来说明着三者之间的关系。
 * <p>
 * （1）如果没有空闲的线程执行该任务且当前运行的线程数少于corePoolSize，则添加新的线程执行该任务。
 * <p>
 * （2）如果没有空闲的线程执行该任务且当前的线程数等于corePoolSize同时阻塞队列未满，则将任务入队列，而不添加新的线程。
 * <p>
 * （3）如果没有空闲的线程执行该任务且阻塞队列已满同时池中的线程数小于maximumPoolSize，则创建新的线程执行任务。
 * <p>
 * （4）如果没有空闲的线程执行该任务且阻塞队列已满同时池中的线程数等于maximumPoolSize，则根据构造函数中的handler指定的策略来拒绝新的任务。
 */
public class ThreadPoolExecutorTest {


    //池中所保存的核心线程数，包括空闲线程。
    private final transient static int corePoolSize = 5;
    //池中允许的最大线程数。
    private final transient static int maximumPoolSize = 10;
    //当线程数大于核心线程时，此为终止前多余的空闲线程等待新任务的最长时间
    private final static long keepAliveTime = 200;
    //执行前用于保持任务的队列5，即任务缓存队列
    final static ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);

    public static void main(String[] args) {

        //构建一个线程池，正常线程数量为5，最大线程数据为10，等待时间200
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.MINUTES, workQueue, new ThreadPoolExecutor.AbortPolicy());

        //线程池去执行15个任务
        for (int i = 1; i <= 15; i++) {

            final int currentTask = i;
            Runnable task = () -> {
                String name = Thread.currentThread().getName();

                System.out.println("正在执行的任务 [" + name + "] " + currentTask);
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                }
                System.out.println("任务 [" + name + "] " + currentTask + "执行完毕");
            };

            threadPoolExecutor.submit(task);

            StringBuilder sb = new StringBuilder();
            sb.append("线程池中现在的线程数目是: " + threadPoolExecutor.getPoolSize());
            sb.append(" 队列中正在等待执行的任务数量为: " + threadPoolExecutor.getQueue().size());
            System.out.println(sb.toString());

        }

        //关掉线程池
        threadPoolExecutor.shutdown();

    }
}
