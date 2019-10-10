package com.curtain.threadsync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Curtain
 * @date 2019/10/10 8:51
 * <p>
 * CyclicBarrier 循环等待
 * <p>
 * 创建时指定 需要等待的个数
 * 当指定个数的 barrier.await(); 执行完成后。
 * <p>
 * 如下效果
 * 两个选手在跑道上比赛   完成一轮  原地等待  直到其他选手都完成后  开始新的一轮
 */
public class TestCyclicBarrier {

    public static int countA = 1;
    public static int countB = 1;


    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2);

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("1号当前开始" + countA++ + "轮比赛");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1号到达终点！");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("2号当前开始" + countB++ + "轮比赛");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2号到达终点！");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
