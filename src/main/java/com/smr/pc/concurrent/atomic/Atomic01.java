package com.smr.pc.concurrent.atomic;


import com.smr.pc.concurrent.executors.ExecutorManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger类型
 * -- 实现共享变量的原子性
 */
public class Atomic01 {

    /**原子性*/
    private static AtomicInteger count = new AtomicInteger(0);

    /**
     * lamdba 表达式写法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 10; i++) {

            Runnable task = () -> {
                for (int j = 0; j < 1000; j++) {
                    count.incrementAndGet();
                }
                System.out.println("[+" + Thread.currentThread().getName() + "] - " + count);
            };

            ExecutorManager.pool().submit(task);
        }

    }


}



