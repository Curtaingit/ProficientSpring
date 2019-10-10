package com.curtain.threadsync;

import java.util.concurrent.CountDownLatch;

/**
 * @author Curtain
 * @date 2019/10/10 8:40
 *
 * 倒计数同步  （latch 门闩）
 * 创建CountDownLatch  设定需要同步的个数   （门闩数）
 * 执行latch.await 表示等待 知道所有门闩以完成
 * 执行latch.countDown();   表示完成一个门闩
 *
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        //等待两个线程  传参数2
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1号准备完成 耗时1s");
            //锁计数 -1
            latch.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2号准备完成 耗时3s");
            latch.countDown();
        }).start();


        try {
            System.out.println("请1号、2号准备！");
            //等待两个线程执行后执行  (锁计数为0  后执行)
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("开始比赛！");
    }



}


