package com.curtain.proficient.aop.service.impl;

import com.curtain.proficient.aop.monitor.PerformanceMonitor;
import com.curtain.proficient.aop.service.WordService;

/**
 * @author Curtain
 * @date 2018/9/12 13:49
 * 模拟业务  其中有开始 和 结束工作
 */
public class WordServiceImpl implements WordService {

    @Override
    public void start(String name) {
//        PerformanceMonitor.begin("workService.end");
        System.out.println("模拟开始工作: "+name);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void end(String name) {
//        PerformanceMonitor.end();

        System.out.println("模拟结束工作: "+name);
    }
}
