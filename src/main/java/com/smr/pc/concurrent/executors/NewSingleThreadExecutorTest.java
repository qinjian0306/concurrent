package com.smr.pc.concurrent.executors;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
 */
public class NewSingleThreadExecutorTest {

    public static void main(String[] args) {
        run1();
    }


    public static void run1() {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; i++) {
            final int index = i;
            executor.submit(() -> {
                try {
                    System.out.println("第" + index + "个线程" + Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }


}
