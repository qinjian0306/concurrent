package com.smr.pc.concurrent.executors;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
 */
public class NewFixedThreadPoolTest {

    public static void main(String[] args) {
        run1();
    }


    public static void run1() {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {

            final int index = i;
            executor.submit(() -> {
                try {
                    System.out.println("第" + index + "个线程" + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            });
        }
    }


}
