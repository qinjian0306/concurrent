package com.smr.pc.concurrent.deadlock;

/**
 * 死锁
 * @author QJ
 */
public class DeadLock01 {

    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (LOCK_A) {
                try {
                    Thread.sleep(50L);
                } catch (Exception e) {
                }
                synchronized (LOCK_B) {
                    System.out.println("B妖怪哪里跑");
                }
            }
        }).start();


        new Thread(() -> {
            synchronized (LOCK_B) {
                synchronized (LOCK_A) {
                    System.out.println("A妖怪哪里跑");
                }
            }
        }).start();

    }


}
