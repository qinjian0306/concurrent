package com.smr.pc.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 */
public class NewCachedThreadPoolTest {

    public static void main(String[] args) {
        run1();
    }


    public static void run1() {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 1; i <= 5; i++) {

            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executor.submit(() -> {
                System.out.println("第" + index + "个线程" + Thread.currentThread().getName());
            });
        }

    }


}
