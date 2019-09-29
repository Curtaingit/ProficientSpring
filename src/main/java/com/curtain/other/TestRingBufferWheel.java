package com.curtain.other;

import java.util.concurrent.Executors;

/**
 * @author Curtain
 * @date 2019/9/27 16:23
 */
public class TestRingBufferWheel {

    public static void main(String[] args) throws InterruptedException {
        RingBufferWheel ringBufferWheel = new RingBufferWheel(Executors.newFixedThreadPool(2), 6);

        for (int i = 0; i < 20; i++) {
            RingBufferWheel.Task task = new DelayMsgJob(i);
            task.setKey(i);
            ringBufferWheel.addTask(task);
        }

        ringBufferWheel.start();
        Thread.sleep(10000000);

    }

    private static class DelayMsgJob extends RingBufferWheel.Task {
        private int msg;

        public DelayMsgJob(int msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +":"+ msg);
        }
    }
}
