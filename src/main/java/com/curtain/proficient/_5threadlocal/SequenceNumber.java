package com.curtain.proficient._5threadlocal;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author Curtain
 * @date 2018/9/26 16:12
 */
public class SequenceNumber {

    private static SimpleThreadLocal<Integer> seqNum = new SimpleThreadLocal<>();

    public int getNextNum(){
        Integer num;
        if (seqNum.get()==null){
            num = 0;
        }else {
            num = seqNum.get();
        }
        seqNum.set(num+1);
        return seqNum.get();
    }
}
