package com.curtain.proficient._6hystrix;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Curtain
 * @date 2018/11/16 8:48
 */
public class CommandUser extends HystrixCommand<String> {

    private String userName;

    public CommandUser(String userName) {
        super(Setter.withGroupKey(
                //服务分组
                HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                //线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserPool"))
                //线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withKeepAliveTimeMinutes(5)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(10000))

                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        );

        this.userName = userName;
    }

    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName() + "userName=" + userName);
        TimeUnit.MILLISECONDS.sleep(100);
        return "userName=" + userName;
    }
}
