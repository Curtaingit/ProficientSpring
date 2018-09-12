package com.curtain.proficient.aop.monitor;

/**
 * @author Curtain
 * @date 2018/9/12 13:48
 * 通过Thread 来保证性能检测
 */
public class PerformanceMonitor {
    private static ThreadLocal<MethodPerformance> performanceThreadLocal = new ThreadLocal<>();

    public static void begin(String method){
        System.out.println("begin monitor..");
        MethodPerformance mp = new MethodPerformance(method);
        performanceThreadLocal.set(mp);
    }

    public static void end(){
        System.out.println("end monitor...");
        MethodPerformance mp = performanceThreadLocal.get();
        mp.printPerformance();
    }
}
