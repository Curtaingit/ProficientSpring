package com.curtain.proficient._5threadlocal;

/**
 * @author Curtain
 * @date 2018/9/26 16:16
 */
public class TestClient extends Thread {

    private SequenceNumber sn;

    public TestClient(SequenceNumber sn){
        this.sn = sn;
    }

    public void run(){
        for (int i =0; i<10;i++){
            System.out.println("Thread[" + Thread.currentThread().getName()+"]  sn["+sn.getNextNum()+"]");
        }
    }
}
