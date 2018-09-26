package com.curtain.proficient._2aop.jdkdynamicproxy;

import com.curtain.proficient._2aop.monitor.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Curtain
 * @date 2018/9/12 16:24
 */
public class PerformanceHandler implements InvocationHandler {

    private Object target;
    public PerformanceHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        PerformanceMonitor.begin("start");
        Object obj = method.invoke(target, args);
        PerformanceMonitor.end();
        return obj;
    }
}
