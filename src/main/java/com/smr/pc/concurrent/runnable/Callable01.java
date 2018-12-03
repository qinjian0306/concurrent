package com.smr.pc.concurrent.runnable;

import java.util.concurrent.*;

public class Callable01 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Callable test01 .. ";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
//
//        FutureTask task = new FutureTask(new Callable01());
//        new Thread(task).start();
//
//        System.out.println(task.get());

        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 1; i < 1000; i++) {

            final int index = i;

            pool.submit(() -> {
                map.put(index, index);
            });
        }


    }


}
