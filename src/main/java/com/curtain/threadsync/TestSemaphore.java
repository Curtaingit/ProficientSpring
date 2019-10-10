package com.curtain.threadsync;

import java.util.concurrent.Semaphore;

/**
 * @author Curtain
 * @date 2019/10/10 9:08
 * <p>
 * Semaphore 信号量
 * 线程需要运行那么需要获得信号量
 * semaphore.acquire();  阻止获得信号量
 * semaphore.release();  释放信号量
 */
public class TestSemaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(0);

        new Thread(() -> {
            for (; ; ) {
                try {
                    Thread.sleep(500);
                    semaphore.acquire();
                    System.out.println("顾客已取票");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    //假装一个小时发出新门票
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("售票处" + (i + 1) + "小时售出两张门票！");
                semaphore.release();
                semaphore.release();
            }
        }).start();


        System.out.println("开始售票");
    }
}
