package com.curtain.proficient._2aop.monitor;

/**
 * @author Curtain
 * @date 2018/9/12 13:53
 * 实现性能检测
 */
public class MethodPerformance {
    private long begin;
    private long end;
    private String serviceMethod;

    public MethodPerformance(String serviceMethod) {
        this.serviceMethod = serviceMethod;
        this.begin = System.currentTimeMillis();
    }

    public void printPerformance() {
        end = System.currentTimeMillis();
        System.out.println(serviceMethod + "花费" + (end - begin) + "毫秒");
    }
}
