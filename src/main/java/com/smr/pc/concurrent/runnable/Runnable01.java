package com.smr.pc.concurrent.runnable;


/**
 * 如果一个类继承Thread，则不适合资源共享。
 * 但是如果实现了Runable接口的话，则很容易的实现资源共享。
 */
class Runnable01 implements Runnable {

    /**能实现共享资源*/
    private static int count = 10;

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + "运行 count= ：" + count--);
        }
    }

    public static void main(String[] args) {

        Runnable01 runnable = new Runnable01();

        Thread t1 = new Thread(runnable);
        t1.setName("线程1");
        t1.start();
        //t1.join();//

        Thread t2 = new Thread(runnable);
        t2.setName("线程2");
        t2.start();


    }
}
