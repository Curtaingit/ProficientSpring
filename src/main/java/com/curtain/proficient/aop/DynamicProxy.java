package com.curtain.proficient.aop;

import com.curtain.proficient.aop.jdkdynamicproxy.PerformanceHandler;
import com.curtain.proficient.aop.service.WordService;
import com.curtain.proficient.aop.service.impl.WordServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author Curtain
 * @date 2018/9/12 16:36
 */
public class DynamicProxy {

    public static void main(String[] args) {
        WordService target = new WordServiceImpl();

        PerformanceHandler handler = new PerformanceHandler(target);

        WordService proxy = (WordService) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),handler);

        proxy.start("biu");

    }
}
