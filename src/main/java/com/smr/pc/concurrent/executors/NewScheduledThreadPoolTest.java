package com.smr.pc.concurrent.executors;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个定长线程池，支持定时及周期性任务执行
 */
public class NewScheduledThreadPoolTest {


    public static void main(String[] args) {


        /**
         * schedule                 几秒后执行，只执行一次
         * scheduleAtFixedRate      是不管多晚，来了就走
         * scheduleWithFixedDealy   即使来晚了，也要再等会
         */

//        run1();
//        run2();
        run3();

    }

    /**
     * 延时执行
     */
    public static void run1() {

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
        ses.schedule(() -> {
            System.out.println(Thread.currentThread().getName());
        }, 2000L, TimeUnit.MILLISECONDS);

    }

    /**
     * 可延时并周期 在本次任务执行后 继续追加delay的延长时间去再次周期的执行任务
     */
    public static void run2() {

        System.out.println(new Date().getSeconds());

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + new Date().getSeconds() + "s");
            try {
                Thread.sleep(3000L);
            } catch (Exception e) {
            }
        }, 0L, 5000L, TimeUnit.MILLISECONDS);

    }

    /**
     * 可延时并周期 已固定的频率去执行
     * 注：情况1. 如果任务本身执行时间 < 线程池执行任务的固定频率 已固定频率去执行任务
     * 情况2. 如果任务本身执行时间 > 线程池执行任务的固定频率 需要等待该任务执行完成后才能继续开启执行任务
     */
    public static void run3() {

        System.out.println(new Date().getSeconds());

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

        //情况1
        ses.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + new Date().getSeconds() + "s");
            try {
                Thread.sleep(3000L);
            } catch (Exception e) {
            }
        }, 0L, 5000L, TimeUnit.MILLISECONDS);


        //情况2
      /*  ses.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + new Date().getSeconds() + "s");
                try {
                    Thread.sleep(10000L);
                }catch (Exception e){}
            }
        }, 0L, 5000L, TimeUnit.MILLISECONDS);*/


    }


}
