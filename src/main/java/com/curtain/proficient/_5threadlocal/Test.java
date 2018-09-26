package com.curtain.proficient._5threadlocal;

/**
 * @author Curtain
 * @date 2018/9/26 16:18
 *
 * 我们可以看到虽然每个线程都共享一个SequenceNumber
 * 但它们并没有互相干扰，而是各自产生独立的序列号
 * 这是因为通过ThreadLocal为每个线程提供了单独的副本
 */
public class Test {

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();

        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);

        t1.start();
        t2.start();
        t3.start();
    }
}
