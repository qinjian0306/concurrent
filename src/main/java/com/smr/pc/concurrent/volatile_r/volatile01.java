package com.smr.pc.concurrent.volatile_r;


/**
 * volatile关键字
 *  -- 实现共享变量的可见性
 */
class volatile01 extends Thread {

//    private boolean flag = true;
    private volatile boolean flag= true;//可见性

    @Override
    public void run() {

        System.out.println("start ..");

        while (flag) {}

        System.out.println("stop ..");

    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public static void main(String[] args) throws Exception {

        volatile01 thread = new volatile01();
        thread.start();
        thread.sleep(3000);

        //主线程 延时3s将 flag改为false
        thread.setFlag(false);



    }


}



