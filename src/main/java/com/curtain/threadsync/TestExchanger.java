package com.curtain.threadsync;

import java.util.concurrent.Exchanger;

/**
 * @author Curtain
 * @date 2019/10/10 9:25
 */
public class TestExchanger {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(()->{
            String weapon = "装备";
            System.out.println("我是卖家，我带着"+weapon+"过来了");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("卖家到达交易地点");
            try {
                System.out.println("我是卖家，换回了"+exchanger.exchange(weapon));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            String money = "1万游戏币";
            System.out.println("我是买家，我带着"+money+"过来了");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("买家到达交易地点");
            try {
                System.out.println("我是买家，换回了"+exchanger.exchange(money));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
