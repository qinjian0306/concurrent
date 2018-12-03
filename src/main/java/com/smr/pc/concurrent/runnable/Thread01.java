package com.smr.pc.concurrent.runnable;


/**
 * 如果一个类继承Thread，则不适合资源共享。
 * 但是如果实现了Runable接口的话，则很容易的实现资源共享。
 */
class Thread01 extends Thread {

    /**不能实现共享资源*/
    private int count = 10;

    @Override
    public void run() {

        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + "运行 count= ：" + count--);
            try {
//                Thread.currentThread().wait(1000);
            } catch (Exception e) {

            }
        }
    }


    public static void main(String[] args) {

        Thread01 tp1 = new Thread01();
        tp1.setName("线程1");
        tp1.start();

        Thread01 tp2 = new Thread01();
        tp2.setName("线程2");
        tp2.start();

    }
}

