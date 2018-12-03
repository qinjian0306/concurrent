package com.smr.pc.concurrent.executors;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 静态内部类单例模式创建线程池
 * @author QJ
 */
public class ExecutorManager {
    private static final transient int cores;

    private ExecutorManager() {
    }

    private static class ExecutorManagerHolder {
        private static final ExecutorService pool = Executors.newFixedThreadPool(cores / 2);
    }

    public static ExecutorService pool() {
        return ExecutorManagerHolder.pool;
    }

    static {
        // 服务器核数
        cores = Runtime.getRuntime().availableProcessors();
    }


}
